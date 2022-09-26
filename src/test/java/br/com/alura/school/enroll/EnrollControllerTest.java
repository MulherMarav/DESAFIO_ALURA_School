package br.com.alura.school.enroll;

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

import br.com.alura.school.enroll.request.NewEnrollRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class EnrollControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @Order(1) 
    void should_add_new_enroll() throws Exception {        
        NewEnrollRequest newEnroll = new NewEnrollRequest("alex");

        mockMvc.perform(post("/courses/java-1/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnroll)))
        .andExpect(status().isCreated());
    }
    
    @Test
    @Order(2) 
    void should_not_allow_duplication_of_enroll() throws Exception {        
        NewEnrollRequest newEnroll = new NewEnrollRequest("alex");

        mockMvc.perform(post("/courses/java-1/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnroll)))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    @Order(3) 
    void should_not_allow_when_the_course_doesnot_exist() throws Exception {        
        NewEnrollRequest newEnroll = new NewEnrollRequest("alex");

        mockMvc.perform(post("/courses/java/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnroll)))
        .andExpect(status().isNotFound());
    }

    @Test
    @Order(4) 
    void should_not_allow_when_the_user_doesnot_exist() throws Exception {        
        NewEnrollRequest newEnroll = new NewEnrollRequest("fernando");

        mockMvc.perform(post("/courses/java-1/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnroll)))
        .andExpect(status().isNotFound());
    }
    
    @Order(5) 
    @ParameterizedTest
    @CsvSource({
            "''",
            "'    '",
            "an-username-that-is-really-really-big"
    })
    void should_validate_bad_enroll_requests(String username) throws Exception {
        NewEnrollRequest newEnroll = new NewEnrollRequest(username);

        mockMvc.perform(post("/courses/java-1/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnroll)))
                .andExpect(status().isBadRequest());
    }
   
}