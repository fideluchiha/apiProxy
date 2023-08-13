package com.proxy.api.services.implementation;

import com.proxy.api.DTO.LimitRequestDTO;
import com.proxy.api.DTO.LimitResponseDTO;
import com.proxy.api.entity.TblLimitRequest;
import com.proxy.api.repository.LimitRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LimitRequestImplementsTest {

    @Mock
    private LimitRequestRepository limitRequestRepository;

    @InjectMocks
    private LimitRequestImplements limitRequestImplements;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        
        TblLimitRequest re = TblLimitRequest.builder().
                ip("ip")
                    .ruta("ruta")
                    .limite_maximo(1).build();
        
        LimitRequestDTO reDTO = LimitRequestDTO.builder().
                ip("ip")
                    .ruta("ruta")
                    .limite_maximo(1).build();
        when(limitRequestRepository.save(re)).thenReturn(re);

        // Call the method
        ResponseEntity response = limitRequestImplements.save(reDTO);

        // Verify the interactions and assertions
        verify(limitRequestRepository, times(1)).save(re);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(re, response.getBody());
    }
    
    
    @Test
    public void testSaveFail() {
        
        
        ResponseEntity response = limitRequestImplements.save(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
    }

    @Test
    public void testFindByIp() {
        // Mocking required objects and setting up expectations
        List<TblLimitRequest> limitRequestList = new ArrayList<>();
        when(limitRequestRepository.findByIp("testIp")).thenReturn(limitRequestList);

        // Call the method
        ResponseEntity response = limitRequestImplements.findByIp("testIp");

        // Verify the interactions and assertions
        verify(limitRequestRepository, times(1)).findByIp("testIp");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(limitRequestList, response.getBody());
    }

    @Test
    public void testDeleteByIpAndRuta() {
        
        List<TblLimitRequest> limitRequestList = new ArrayList<>();
        limitRequestList.add(TblLimitRequest.builder().id(1L).build());
        when(limitRequestRepository.findByIpAndRuta(anyString(), anyString())).thenReturn(limitRequestList);
        ResponseEntity response = limitRequestImplements.
                deleteByIpAndRuta(LimitRequestDTO.builder().ip("ip").ruta("ruta").build());

        // Verify the interactions and assertions
        verify(limitRequestRepository, times(1)).findByIpAndRuta(anyString(), anyString());
        verify(limitRequestRepository, times(1)).deleteAll(limitRequestList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("restriccion borrada", response.getBody());
    }
    
    @Test
    public void testDeleteByIpAndRutaFail() {
        
        ResponseEntity response = limitRequestImplements.
                deleteByIpAndRuta(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testFindByIpLimits() {
        
        List<TblLimitRequest> limitRequestList = new ArrayList<>(); // Initialize with required values
        when(limitRequestRepository.findByIp("testIp")).thenReturn(limitRequestList);

        // Call the method
        ResponseEntity response = limitRequestImplements.findByIpLimits("testIp");

        // Verify the interactions and assertions
        verify(limitRequestRepository, times(1)).findByIp("testIp");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("no tiene limites", response.getBody());
    }
    
    @Test
    void testFindByIpLimitsWithLimits() {
        
        String ip = "127.0.0.1";
        List<TblLimitRequest> mockList = new ArrayList<>();
        TblLimitRequest limitRequest = new TblLimitRequest();
        limitRequest.setLimite_maximo(10);
        limitRequest.setRuta("/ruta1");
        mockList.add(limitRequest);

        when(limitRequestRepository.findByIp(ip)).thenReturn(mockList);

         ResponseEntity response = limitRequestImplements.findByIpLimits(ip);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof LimitResponseDTO);

        LimitResponseDTO limitResponseDTO = (LimitResponseDTO) response.getBody();
        assertEquals(10, limitResponseDTO.getLimite_maximo());
        assertEquals(mockList, limitResponseDTO.getRutasRestringuidas());
    }
    
    

}
