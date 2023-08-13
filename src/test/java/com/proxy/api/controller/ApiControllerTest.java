/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.controller;

import com.proxy.api.services.interfaces.IConsumosServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author fidel
 */
public class ApiControllerTest {
    
    @Mock
    private IConsumosServices iConsumosServices;
    
    @InjectMocks
    private ApiController apiController;
    
     @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testProxyRequest() {
        
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(iConsumosServices.requestUrl("ruta", "variable", request))
            .thenReturn(ResponseEntity.ok("Test Response"));
        ResponseEntity<String> response = apiController.proxyRequest("ruta", "variable", request);

        
        verify(iConsumosServices, times(1)).requestUrl("ruta", "variable", request);
        assertEquals("Test Response", response.getBody());
    }
    
}
