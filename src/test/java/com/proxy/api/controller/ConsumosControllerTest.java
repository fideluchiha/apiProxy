package com.proxy.api.controller;

import com.proxy.api.services.interfaces.IConsumosServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumosControllerTest {

    @Mock
    private IConsumosServices services;

    @InjectMocks
    private ConsumosController consumosController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByIp() {
       
        when(services.findByIp("IP")).thenReturn(ResponseEntity.ok("Test Response"));
        ResponseEntity response = consumosController.findByIp("IP");
        verify(services, times(1)).findByIp("IP");
        assertEquals("Test Response", response.getBody());
        
    }

    @Test
    public void testFindAll() {
        
        when(services.findAll()).thenReturn(ResponseEntity.ok("Test Response"));
        ResponseEntity response = consumosController.findAll();
        verify(services, times(1)).findAll();
        assertEquals("Test Response", response.getBody());
    }
    
    
    @Test
    public void testFindEstadisticas() {
        
        when(services.estadisticas()).thenReturn(ResponseEntity.ok("Test Response"));
        ResponseEntity response = consumosController.findEstadisticas();
        verify(services, times(1)).estadisticas();
        assertEquals("Test Response", response.getBody());
    }
}
