package com.proxy.api.services.implementation;

import com.proxy.api.DTO.EstadisticaDTO;
import com.proxy.api.DTO.LimitRequestDTO;
import com.proxy.api.entity.TblConsumos;
import com.proxy.api.entity.TblLimitRequest;
import com.proxy.api.repository.ConsumosRepository;
import com.proxy.api.repository.LimitRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumosImplementsTest {

    @Mock
    private ConsumosRepository consumosRepository;

    @Mock
    private LimitRequestRepository limitRequestRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ConsumosImplements consumosImplements;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRequestUrl() {
       
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("testIp");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Test Response");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        List<TblLimitRequest> limitRequestList = new ArrayList<>();
        limitRequestList.add(TblLimitRequest.builder().limite_maximo(20).build());
        when(limitRequestRepository.findByIp("testIp")).thenReturn(limitRequestList);
        
        List<TblConsumos> list = new ArrayList<>();
        
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        when(consumosRepository.findByIpAndEstado("testIp", "permitido")).thenReturn(list);
        ResponseEntity<String> response = consumosImplements.requestUrl("rutaValue", "variableValue", request);
        verify(limitRequestRepository, times(1)).findByIp("testIp");
        verify(consumosRepository, times(1)).findByIpAndEstado("testIp", "permitido");
        verify(consumosRepository, times(1)).save(any(TblConsumos.class));
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(String.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Response", response.getBody());
    }
    
    @Test
    public void testRequestUrlElse() {
       
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("testIp");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Test Response");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        List<TblLimitRequest> limitRequestList = new ArrayList<>();
        when(limitRequestRepository.findByIp("testIp")).thenReturn(limitRequestList);
        
        List<TblConsumos> list = new ArrayList<>();
        
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        when(consumosRepository.findByIpAndEstado("testIp", "permitido")).thenReturn(list);
        ResponseEntity<String> response = consumosImplements.requestUrl("rutaValue", "variableValue", request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Response", response.getBody());
    }
    
    @Test
    public void testRequestUrlElse2() {
       
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("testIp");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Test Response");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        List<TblLimitRequest> limitRequestList = new ArrayList<>();
        limitRequestList.add(TblLimitRequest.builder().limite_maximo(1).build());
        when(limitRequestRepository.findByIp("testIp")).thenReturn(limitRequestList);
        
        List<TblConsumos> list = new ArrayList<>();
        
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        when(consumosRepository.findByIpAndEstado("testIp", "permitido")).thenReturn(list);
        ResponseEntity<String> response = consumosImplements.requestUrl("ruta", "variableValue", request);
        
        
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Paso el limite de consumos", response.getBody());
    }
    
     @Test
    public void testRequestUrlElse3() {
       
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("12312");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Test Response");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        List<TblLimitRequest> limitRequestList = new ArrayList<>();
        limitRequestList.add(TblLimitRequest.builder().limite_maximo(1).ruta("ruta").build());
        when(limitRequestRepository.findByIp(anyString())).thenReturn(limitRequestList);
        
        List<TblConsumos> list = new ArrayList<>();
        
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        when(consumosRepository.findByIpAndEstado(anyString(), anyString())).thenReturn(list);
        ResponseEntity<String> response = consumosImplements.requestUrl("ruta", "variableValue", request);
        
        
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Paso el limite de consumos y esta Ruta Restringida", response.getBody());
    }
    
    @Test
    public void testRequestUrlElse4() {
       
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("12312");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Test Response");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        List<TblLimitRequest> limitRequestList = new ArrayList<>();
        limitRequestList.add(TblLimitRequest.builder().limite_maximo(5).ruta("ruta").build());
        when(limitRequestRepository.findByIp(anyString())).thenReturn(limitRequestList);
        
        List<TblConsumos> list = new ArrayList<>();
        
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        list.add(TblConsumos.builder().ip("12312").ruta("ruta").build());
        when(consumosRepository.findByIpAndEstado(anyString(), anyString())).thenReturn(list);
        ResponseEntity<String> response = consumosImplements.requestUrl("ruta", "variableValue", request);
        
        
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Ruta Restringida", response.getBody());
    }
    
    @Test
    public void testRequestUrlFail() {
       
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("12312");

       ResponseEntity<String> response = consumosImplements.requestUrl(null, null, request);
        
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error en la pagina", response.getBody());
    }

    @Test
    public void testFindByIp() {
        
        List<TblConsumos> consumosList = new ArrayList<>();
        when(consumosRepository.findByIp("testIp")).thenReturn(consumosList);
        ResponseEntity response = consumosImplements.findByIp("testIp");

        verify(consumosRepository, times(1)).findByIp("testIp");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(consumosList, response.getBody());
    }

    @Test
    public void testFindAll() {
        
        List<TblConsumos> consumosList = new ArrayList<>();
        when(consumosRepository.findAll()).thenReturn(consumosList);
        ResponseEntity response = consumosImplements.findAll();
        verify(consumosRepository, times(1)).findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consumosList, response.getBody());
    }
    
    @Test
    public void testEstadisticas() {
        
        List<String> rutas = Arrays.asList("ruta1", "ruta2");
        when(consumosRepository.findDistinctRutas()).thenReturn(rutas);
        when(consumosRepository.countByTotal()).thenReturn(100L);
        when(consumosRepository.countByEstadoPermitido()).thenReturn(80L);
        when(consumosRepository.countByEstadoBloquedo()).thenReturn(10L);
        when(consumosRepository.countByEstadoError()).thenReturn(10L);

        ResponseEntity response = consumosImplements.estadisticas();

        verify(consumosRepository, times(1)).findDistinctRutas();
        verify(consumosRepository, times(1)).countByTotal();
        verify(consumosRepository, times(1)).countByEstadoPermitido();
        verify(consumosRepository, times(1)).countByEstadoBloquedo();
        verify(consumosRepository, times(1)).countByEstadoError();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        EstadisticaDTO estadisticaDTO = (EstadisticaDTO) response.getBody();
        assertNotNull(estadisticaDTO);
        assertEquals(rutas, estadisticaDTO.getRutas_solicitadas());
        assertEquals(100L, estadisticaDTO.getRequest_total());
        assertEquals(80L, estadisticaDTO.getRequest_permitidos());
        assertEquals(10L, estadisticaDTO.getRequest_bloqueados());
        assertEquals(10L, estadisticaDTO.getRequest_errores());
    }
}
