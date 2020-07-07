package com.unisoma.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.unisoma.model.Readjustment;
import com.unisoma.repository.ReadjustmentRepository;

@SpringBootTest
public class ReadjustmentServiceMockTest {

    @Mock
    private ReadjustmentRepository readjustmentRepository;

    private Readjustment readjust15;
    
    private Readjustment readjust12;

    @InjectMocks // auto inject readjustmentRepository
    private ReadjustmentService readjustmentService = new ReadjustmentService();

    @BeforeEach
    void setMockOutput() {
    	readjust15 = new Readjustment(0.0, 400.00, 15l);
    	readjust12 = new Readjustment(400.01, 800.00, 12l);
    }
    
    @DisplayName("Test Mock readjustmentRepository salary 300 return 15 percent")
    @Test
    void testReajustment15Percent() {
    	Mockito.when(readjustmentRepository.findBySalary(300.00)).thenReturn(Optional.of(readjust15));
        assertEquals(15, readjustmentService.getBySalary(300.00).get().getPercent());
        assertEquals("345,00", readjustmentService.getBySalary(300.00).get().getNewSalary());
    }
    
    @DisplayName("Test Mock readjustmentRepository salary 700 return 12 percent")
    @Test
    void testReajustment12Percent() {
    	Mockito.when(readjustmentRepository.findBySalary(700.00)).thenReturn(Optional.of(readjust12));
        assertEquals(12, readjustmentService.getBySalary(700.00).get().getPercent());
        assertEquals("784,00", readjustmentService.getBySalary(700.00).get().getNewSalary());
    }

}