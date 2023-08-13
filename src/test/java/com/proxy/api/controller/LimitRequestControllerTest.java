package com.proxy.api.controller;

import com.proxy.api.DTO.LimitRequestDTO;
import com.proxy.api.services.interfaces.ILimitRequestServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LimitRequestControllerTest {

    @Mock
    private ILimitRequestServices services;

    @InjectMocks
    private LimitRequestController limitRequestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveLimit() {
        
        when(services.save(LimitRequestDTO.builder().build())).thenReturn(ResponseEntity.ok("Test Response"));
        ResponseEntity response = limitRequestController.saveLimit(LimitRequestDTO.builder().build());
        verify(services, times(1)).save(LimitRequestDTO.builder().build());
        assertEquals("Test Response", response.getBody());
    }

    @Test
    public void testDeleteLimit() {
        
        when(services.deleteByIpAndRuta(LimitRequestDTO.builder().build())).thenReturn(ResponseEntity.ok("Test Response"));
        ResponseEntity response = limitRequestController.deleteLimit(LimitRequestDTO.builder().build());
        verify(services, times(1)).deleteByIpAndRuta(LimitRequestDTO.builder().build());
        assertEquals("Test Response", response.getBody());
        
    }

    @Test
    public void testFindByIp() {
        
        when(services.findByIp("IP")).thenReturn(ResponseEntity.ok("Test Response"));

        ResponseEntity response = limitRequestController.findByIp("IP");
        verify(services, times(1)).findByIp("IP");
        assertEquals("Test Response", response.getBody());
    }

    @Test
    public void testFindByIpLimit() {
        
        when(services.findByIpLimits("testIp")).thenReturn(ResponseEntity.ok("Test Response"));
        ResponseEntity response = limitRequestController.findByIpLimit("testIp");
        verify(services, times(1)).findByIpLimits("testIp");
        assertEquals("Test Response", response.getBody());
        
    }
}
