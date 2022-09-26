package br.com.alura.school.section;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.school.section.request.NewSectionRequest;
import br.com.alura.school.section.request.NewVideoRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class VideoControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Order(0) 
    void should_add_new_section() throws Exception {
    	
        NewSectionRequest newSection = new NewSectionRequest("flutter-cores-dinamicas", 
        		"Flutter: Configurando cores dinâmicas", "alex");
        
        mockMvc.perform(post("/courses/java-1/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newSection)));
                //.andExpect(status().isCreated())
                //.andExpect(header().string("Location", "/courses/java-1/sections/flutter-cores-dinamicas"));
    }
    
    @Test
    @Order(1) 
    void should_add_new_video() throws Exception {
    	
        NewVideoRequest newVideo = new NewVideoRequest("https://www.youtube.com/watch?v=gI4-vj0WpKM");
        
        mockMvc.perform(post("/courses/java-1/sections/flutter-cores-dinamicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newVideo)))
                .andExpect(status().isCreated());
    }
    
    @Test
    @Order(2) 
    void should_not_allow_duplication_of_video() throws Exception {
    	
        NewVideoRequest newVideo = new NewVideoRequest("https://www.youtube.com/watch?v=gI4-vj0WpKM");
        
        mockMvc.perform(post("/courses/java-1/sections/flutter-cores-dinamicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newVideo)))
                .andExpect(status().isBadRequest());
    }
    
    
    @Test
    @Order(3) 
    void should_not_allow_when_the_course_doesnot_exist() throws Exception {
    	
    	NewVideoRequest newVideo = new NewVideoRequest("https://www.youtube.com/watch?v=gI4-vj0WpKM");
        
        mockMvc.perform(post("/courses/java/sections/flutter-cores-dinamicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newVideo)))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @Order(4) 
    void should_not_allow_when_the_section_doesnot_exist() throws Exception {
    	
        NewVideoRequest newVideo = new NewVideoRequest("https://www.youtube.com/watch?v=gI4-vj0WpKM");
        
        mockMvc.perform(post("/courses/java-1/sections/flutter-cores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newVideo)))
                .andExpect(status().isNotFound());
    }
    
    @Order(5) 
    @ParameterizedTest
    @CsvSource({
            "''",
            "'    '",
            "an-video-that-is-really-really-big-but-very-big-really"
    })
    void should_validate_bad_video_requests(String video) throws Exception {
    	  NewVideoRequest newVideo = new NewVideoRequest(video);
          
          mockMvc.perform(post("/courses/java-1/sections/flutter-cores-dinamicas")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonMapper.writeValueAsString(newVideo)))
                  .andExpect(status().isBadRequest());
    }
}