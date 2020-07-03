package com.unisoma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.unisoma.repository.ReadjustmentRepository;
import com.unisoma.service.ReadjustmentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReadjustmentServiceMockTest {

    @Mock
    private ReadjustmentRepository readjustmentRepository;

    @InjectMocks // auto inject readjustmentRepository
    private ReadjustmentService readjustmentService = new ReadjustmentService();

    @BeforeEach
    void setMockOutput() {
        //when(readjustmentRepository.get()).thenReturn("Hello Mockito From Repository");
    }

    @DisplayName("Test Mock helloService + readjustmentRepository")
    @Test
    void testGet() {
        //assertEquals("Hello Mockito From Repository", readjustmentService.get());
    }

}