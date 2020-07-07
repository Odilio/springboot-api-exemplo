package com.unisoma.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisoma.model.Result;
import com.unisoma.service.BalancedService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BalancedControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired                           
    private MockMvc mockMvc;  
    
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    BalancedService service;
    
    @InjectMocks
    BalancedController controller;
    
    static List<String> expressions = new ArrayList<String>();
    
    static List<String> expressionsError = new ArrayList<String>();
    
    static List<String> results = new ArrayList<String>();
    
    static List<String> noResults = new ArrayList<String>();
 
    @BeforeAll
    static void setMockOutput() {
    	expressions.add("a+(b*c)-2-a");
    	expressions.add("(a+b*(2-c)-2+a)*2");
    	expressions.add("2*(3-a))");
    	expressions.add(")3+b*(2-c)(");
    	expressions.add("(a*b-(2+c)");
    	expressions.add("()");
    	
    	results.add("correct");
    	results.add("correct");
    	results.add("incorrect");
    	results.add("incorrect");
    	results.add("incorrect");
    	results.add("correct");
    	
    	expressionsError.add("");
    }
    
    @Test
    @DisplayName("Test Controller Balanced success ")
    public void getBalancedSuccess() throws Exception {

        Mockito.when(service.getBalanced(expressions)).thenReturn(results);
        this.mockMvc.perform(post("/balanced/balanced").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(expressions)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is("correct")))
                .andExpect(jsonPath("$[2]", is("incorrect")));
    }
    
    @Test
    @DisplayName("Test Controller Balanced failed, 0 expression ")
    public void getBalancedFailed() throws Exception {

        Mockito.when(service.getBalanced(expressionsError)).thenReturn(noResults);
        this.mockMvc.perform(post("/balanced/balanced").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(expressionsError)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("getResult.expressions[0].<list element>: size must be between 1 and 1000")));
    }
    
}