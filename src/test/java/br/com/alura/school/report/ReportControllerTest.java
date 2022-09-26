package br.com.alura.school.report;

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
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.school.enroll.request.NewEnrollRequest;
import br.com.alura.school.section.request.NewSectionRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class ReportControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1) 
    void should_retrieve_no_content_without_section_and_enroll() throws Exception {

        mockMvc.perform(get("/sectionByVideosReport")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
    @Test
    @Order(2) 
    void should_add_new_enroll() throws Exception {        
        NewEnrollRequest newEnroll = new NewEnrollRequest("alex");

        mockMvc.perform(post("/courses/java-1/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnroll)))
        .andExpect(status().isCreated());
    }
    
    @Test
    @Order(3) 
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
    @Order(4) 
    void should_retrieve_all_section_by_videos_report() throws Exception {

        mockMvc.perform(get("/sectionByVideosReport")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].courseName", is("Java OO")))
                .andExpect(jsonPath("$[0].sectionTitle", is("Flutter: Configurando cores dinâmicas")))
                .andExpect(jsonPath("$[0].authorName", is("alex")))	
                .andExpect(jsonPath("$[0].totalVideos", is(0)));
    }
    
}