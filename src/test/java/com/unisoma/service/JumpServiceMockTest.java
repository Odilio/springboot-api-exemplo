package com.unisoma.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.unisoma.model.Jump;
import com.unisoma.model.Result;
import com.unisoma.model.dto.JumpDTO;

@SpringBootTest
public class JumpServiceMockTest {


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
 
    private static JumpService jumpService = new JumpService();

    @BeforeAll
    static void setMockOutput() {
    	notes = List.of(10.0,5.0,8.0, 9.0, 6.0,5.4, 7.1);
    	notes2 = List.of(10.0,5.0,8.0, 9.0, 6.0,5.4, 7.1);
    	notes3 = List.of(10.0,5.0,8.0, 9.0, 6.0);

    	jump1 = new Jump("Gabriela", 2.0, notes, 0.0);
    	jump2 = new Jump("Camila", 3.0, notes2, 0.0);
    	jump3 = new Jump("Rafaela", 3.0, notes3, 0.0);
    	
    	jumpDto = new JumpDTO("Gabriela", 71.0);
    	
    	jumps.add(jump1);
    	jumps.add(jump2);
    	jumpsErrorNotes.add(jump3);

    	results.add(jumpDto);
    }
    
    @DisplayName("Test jumpService jumps sucess")
    @Test
    void testResultsSucess() {
    	Optional<Result> resultado = jumpService.getResults(jumps);
        assertEquals(2, resultado.get().getResults().size());
    }
    
    @DisplayName("Test jumpService jumps Error")
    @Test
    void testResultsError() {
    }

}