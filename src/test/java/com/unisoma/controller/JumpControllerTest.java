package com.unisoma.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisoma.model.Jump;
import com.unisoma.model.Result;
import com.unisoma.model.dto.JumpDTO;
import com.unisoma.service.JumpService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JumpControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired                           
    private MockMvc mockMvc;  
    
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    JumpService service;
    
    @InjectMocks
    JumpController controller;
    
    private static Jump jump1;
    
    private static Jump jump2;
    
    private static Jump jump3;
    
    private static JumpDTO jumpDto;
    
    static List<Jump> jumps = new ArrayList<Jump>();
    
    static List<Jump> jumpsErrorNotes = new ArrayList<Jump>();
    
    static List<JumpDTO> results = new ArrayList<JumpDTO>();
    
    static List<Double> notes = new ArrayList<Double>();

    static List<Double> notes2 = new ArrayList<Double>();
    
    static List<Double> notes3 = new ArrayList<Double>();
    
    static Result result;
 
    @BeforeAll
    static void setMockOutput() {
    	notes = List.of(10.0,5.0,8.0, 9.0, 6.0,5.4, 7.1);
    	notes2 = List.of(10.0,5.0,8.0, 9.0, 6.0,5.4, 7.1);
    	notes3 = List.of(10.0,5.0,8.0, 9.0, 6.0);

    	jump1 = new Jump("Gabi", 2.0, notes, 0.0);
    	jump2 = new Jump("Julia", 3.0, notes2, 0.0);
    	jump3 = new Jump("Rafaela", 3.0, notes3, 0.0);
    	
    	jumpDto = new JumpDTO("Gabriela", 71.0);
    	
    	jumps.add(jump1);
    	jumps.add(jump2);
    	jumpsErrorNotes.add(jump3);

    	results.add(jumpDto);
    	
    	result = new Result(results);
    }
    
    @Test
    @DisplayName("Test Controller Jump success ")
    public void getJumpSuccess() throws Exception {

        Mockito.when(service.getResults(jumps)).thenReturn(Optional.of(result));
        this.mockMvc.perform(post("/jump/resuts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(jumps)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].name", is("Gabi")))
                .andExpect(jsonPath("$.results[0].result", is(71.0)));
    }
    
    @Test
    @DisplayName("Test Controller Jump validation failed")
    public void getJumpFailed() throws Exception {

        Mockito.when(service.getResults(jumpsErrorNotes)).thenReturn(Optional.of(result));
        this.mockMvc.perform(post("/jump/resuts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(jumpsErrorNotes)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("getResult.jumps[0].notes: Quantidade de notas informadas deve ser 7")));
    }

}