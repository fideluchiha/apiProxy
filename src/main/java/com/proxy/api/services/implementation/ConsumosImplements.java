/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.services.implementation;

import com.proxy.api.DTO.EstadisticaDTO;
import com.proxy.api.Util.Utils;
import com.proxy.api.entity.TblConsumos;
import com.proxy.api.entity.TblLimitRequest;
import com.proxy.api.repository.ConsumosRepository;
import com.proxy.api.repository.LimitRequestRepository;
import com.proxy.api.services.interfaces.IConsumosServices;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fidel 
 */
@Component
public class ConsumosImplements implements IConsumosServices{
    
    
    
    @Autowired
    private ConsumosRepository consumosRepository;
    
    @Autowired
    private LimitRequestRepository limitRequestRepository;
    
    @Value("${target.api.url}") 
    private String TARGET_API_URL;
    
    
    private final String ESTADO_PERMITIDO= "permitido";
    
    private final String ESTADO_BLOQUEADO= "bloqueado";
    
    private final String BAD_REQUEST = "bad_request";
    
    @Autowired
    private RestTemplate restTemplate;
    
    Utils util = new Utils();
    
    @Override
    public ResponseEntity requestUrl(String ruta,String variable,HttpServletRequest request) {
        
        ResponseEntity<String> response = null;
        String ip = util.getClientIp(request);
        String targetUrl = util.determineTargetUrlFromRequest(TARGET_API_URL,ruta,variable);
        
        try {
            
            List<TblLimitRequest> list = limitRequestRepository.findByIp(ip);
            
            if (list.isEmpty()) {
                response = restTemplate.getForEntity(targetUrl, String.class);

                consumosRepository.save(TblConsumos.builder().
                        codidoRes(response.getStatusCodeValue())
                        .ip(ip)
                        .estado(ESTADO_PERMITIDO)
                        .ruta(ruta)
                        .variable(variable)
                        .build());
            }else{
                
                int limite = list.stream()
                        .mapToInt(TblLimitRequest::getLimite_maximo)
                        .max()
                        .orElse(0);

                List<String> rutasUnicas = list.stream()
                        .map(TblLimitRequest::getRuta)
                        .distinct()
                        .collect(Collectors.toList());

                boolean contieneRuta = rutasUnicas.contains(ruta);
                int permitido = consumosRepository.findByIpAndEstado(ip, ESTADO_PERMITIDO).size();

                if (limite <= permitido && contieneRuta) {
                    consumosRepository.save(TblConsumos.builder()
                            .codidoRes(HttpStatus.BAD_REQUEST.value())
                            .ip(util.getClientIp(request))
                            .ruta(ruta)
                            .estado(ESTADO_BLOQUEADO)
                            .variable(variable)
                            .build());

                        return response.status(HttpStatus.ACCEPTED)
                                .body("Paso el limite de consumos y esta Ruta Restringida");
                    
                }else if(contieneRuta) {
                    consumosRepository.save(TblConsumos.builder()
                            .codidoRes(HttpStatus.BAD_REQUEST.value())
                            .ip(util.getClientIp(request))
                            .ruta(ruta)
                            .estado(ESTADO_BLOQUEADO)
                            .variable(variable)
                            .build());
                    return response.status(HttpStatus.ACCEPTED)
                                .body("Ruta Restringida");
                }else if(limite <= permitido) {
                    consumosRepository.save(TblConsumos.builder()
                            .codidoRes(HttpStatus.BAD_REQUEST.value())
                            .ip(util.getClientIp(request))
                            .ruta(ruta)
                            .estado(ESTADO_BLOQUEADO)
                            .variable(variable)
                            .build());
                    return response.status(HttpStatus.ACCEPTED)
                                .body("Paso el limite de consumos");
                }
                
                response = restTemplate.getForEntity(targetUrl, String.class);

                consumosRepository.save(TblConsumos.builder().
                        codidoRes(response.getStatusCodeValue())
                        .ip(ip)
                        .ruta(ruta)
                        .estado(ESTADO_PERMITIDO)
                        .variable(variable)
                        .build());
                
            
            }
          } catch (Exception ex) {
                
                consumosRepository.save(TblConsumos.builder().
                    codidoRes(HttpStatus.BAD_REQUEST.value())
                    .ip(util.getClientIp(request))
                    .ruta(ruta)
                    .estado(BAD_REQUEST)
                    .variable(variable)
                    .build());
                return response.status(HttpStatus.BAD_REQUEST).
                    body("Error en la pagina");
            }
        return response;
    }
    
    @Override
    public ResponseEntity findByIp(String ip) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(consumosRepository.findByIp(ip));
        
    }

    @Override
    public ResponseEntity findAll() {
         return ResponseEntity.status(HttpStatus.OK).body(consumosRepository.findAll());
    }

    @Override
    public ResponseEntity estadisticas() {
        
       
        return ResponseEntity.status(HttpStatus.OK).body(EstadisticaDTO.builder()
                .rutas_solicitadas(consumosRepository.findDistinctRutas())
                .request_total(consumosRepository.countByTotal())
                .request_permitidos(consumosRepository.countByEstadoPermitido())
                .request_bloqueados(consumosRepository.countByEstadoBloquedo())
                .request_errores(consumosRepository.countByEstadoError()).build());
        
    }
    
    
    
}
