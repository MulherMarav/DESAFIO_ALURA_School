package br.com.alura.school.section;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
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

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS) 
@TestMethodOrder(OrderAnnotation.class)
class SectionControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Order(1) 
    void should_add_new_section() throws Exception {
    	
        NewSectionRequest newSection = new NewSectionRequest("flutter-cores-dinamicas", 
        		"Flutter: Configurando cores dinâmicas", "alex");
        
        mockMvc.perform(post("/courses/java-1/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newSection)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/courses/java-1/sections/flutter-cores-dinamicas"));
    }
    
    @Test
    @Order(2) 
    void should_not_allow_when_the_course_doesnot_exist() throws Exception {
    	
        NewSectionRequest newSection = new NewSectionRequest("flutter-cores-dinamicas", 
        		"Flutter: Configurando cores dinâmicas", "alex");
        
        mockMvc.perform(post("/courses/java/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newSection)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @Order(3) 
    void should_not_allow_when_the_user_doesnot_exist() throws Exception {
    	
        NewSectionRequest newSection = new NewSectionRequest("flutter-cores-dinamicas", 
        		"Flutter: Configurando cores dinâmicas", "fernando");
        
        mockMvc.perform(post("/courses/java-1/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newSection)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @Order(3) 
    void should_retrieve_section_by_code() throws Exception {
        
        mockMvc.perform(get("/courses/java-1/sections/flutter-cores-dinamicas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is("flutter-cores-dinamicas")))
                .andExpect(jsonPath("$.title", is("Flutter: Configurando cores dinâmicas")))
                .andExpect(jsonPath("$.author.username", is("alex")));
    }
    
    @Test
    @Order(4) 
    void should_retrieve_all_sections() throws Exception {

        mockMvc.perform(get("/courses/java-1/sections")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].code", is("flutter-cores-dinamicas")))
                .andExpect(jsonPath("$[0].title", is("Flutter: Configurando cores dinâmicas")))
                .andExpect(jsonPath("$[0].author.username", is("alex")));
    }
    
    @Order(5) 
    @ParameterizedTest
    @CsvSource({
            "'', title, username",
            "'    ', title, username",
            "an-code-that-is-really-really-big, title, username",
            "code, '', username",
            "code, '    ', username",
            "code, an-title-that-is-really-really-big-but-very-big, username",
            "code, title, ''",
            "code, title, '    '",
            "code, title, an-video-that-is-really-really-big"
    })
    void should_validate_bad_section_requests(String code, String title, String username) throws Exception {
        NewSectionRequest newSection = new NewSectionRequest(code, title, username);
        
        mockMvc.perform(post("/courses/java-1/sections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newSection)))
                .andExpect(status().isBadRequest());
    }

}