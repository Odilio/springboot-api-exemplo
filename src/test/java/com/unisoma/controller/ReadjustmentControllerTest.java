package com.unisoma.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.util.Optional;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.unisoma.model.Readjustment;
import com.unisoma.service.ReadjustmentService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReadjustmentControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired                           
    private MockMvc mockMvc;  
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    ReadjustmentService service;
    
    @InjectMocks
    ReadjustmentController controller;
    
    private Readjustment readjust15;
    
    private Readjustment readjust12;
    
    @BeforeEach
    void setUp() {
    	readjust15 = new Readjustment(0.0, 400.00, 15l);
    	readjust12 = new Readjustment(400.01, 800.00, 12l);
    }
    
    @Test
    @DisplayName("Test Controller Readjustment get by salary 400 sucess")
    public void getReadjustmentBySalary400Integration() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
			new URL("http://localhost:" + port + "/readjustment/salary/400").toString(), String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\r\n" + 
        		"  \"newSalary\" : \"460,00\",\r\n" + 
        		"  \"readjustment\" : \"60,00\",\r\n" + 
        		"  \"percent\" : 15\r\n" + 
        		"}", response.getBody());

    }
    
    @Test
    @DisplayName("Test Controller Readjustment get by salary 900 sucess")
    public void getReadjustmentBySalary900Integration() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
			new URL("http://localhost:" + port + "/readjustment/salary/900").toString(), String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\r\n" + 
        		"  \"newSalary\" : \"990,00\",\r\n" + 
        		"  \"readjustment\" : \"90,00\",\r\n" + 
        		"  \"percent\" : 10\r\n" + 
        		"}", response.getBody());

    }
    
    @Test
    @DisplayName("Test Controller Readjustment get by salary 400 Unit Mock Service ")
    public void getReadjustmentBySalary400Unit() throws Exception {

        Double salary = 400.00;
        
        Mockito.when(service.getBySalary(salary)).thenReturn(Optional.of(readjust15));
        this.mockMvc.perform(get("/readjustment/salary/{salary}", salary))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.newSalary", is("460,00")))
                .andExpect(jsonPath("$.readjustment", is("60,00")));
    }
    
    @Test
    @DisplayName("Test Controller Readjustment get by salary 800 Unit Mock Service ")
    public void getReadjustmentBySalary800Unit() throws Exception {

        Double salary = 800.00;
        
        Mockito.when(service.getBySalary(salary)).thenReturn(Optional.of(readjust12));
        this.mockMvc.perform(get("/readjustment/salary/{salary}", salary))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.newSalary", is("896,00")))
                .andExpect(jsonPath("$.readjustment", is("96,00")));
    }

}