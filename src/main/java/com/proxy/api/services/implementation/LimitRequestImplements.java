/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.services.implementation;

import com.proxy.api.DTO.LimitRequestDTO;
import com.proxy.api.DTO.LimitResponseDTO;
import com.proxy.api.entity.TblConsumos;
import com.proxy.api.entity.TblLimitRequest;
import com.proxy.api.repository.LimitRequestRepository;
import com.proxy.api.services.interfaces.ILimitRequestServices;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author fidel
 */
@Component
public class LimitRequestImplements implements ILimitRequestServices{
    
    @Autowired
    private LimitRequestRepository limitRequestRepository;

    @Override
    public ResponseEntity save(LimitRequestDTO Dto) {
        
        try {
            TblLimitRequest r = limitRequestRepository.save(TblLimitRequest.builder()
                    .ip(Dto.getIp())
                    .ruta(Dto.getRuta())
                    .limite_maximo(Dto.getLimite_maximo())
                    .build());

            return ResponseEntity.status(HttpStatus.CREATED).body(r);
        } catch (Exception ex) {
                
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ex.getMessage());
        }
        
    }

    @Override
    public ResponseEntity findByIp(String ip) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(limitRequestRepository.findByIp(ip));
        
    }

    @Override
    public ResponseEntity deleteByIpAndRuta(LimitRequestDTO Dto) {
         
         ResponseEntity response = null;
         try {
            List<TblLimitRequest> list = limitRequestRepository.findByIpAndRuta(Dto.getIp(),Dto.getRuta());

            if(!list.isEmpty()){
                limitRequestRepository.deleteAll(list);
                response = ResponseEntity.status(HttpStatus.OK).body("restriccion borrada");
            }else{

             response = ResponseEntity.status(HttpStatus.CREATED).body("no se encontro resultados para borrar");
            }
         } catch (Exception ex) {
                
            return response.status(HttpStatus.BAD_REQUEST).
                    body(ex.getMessage());
        }
         
    return response;
    }

    @Override
    public ResponseEntity findByIpLimits(String ip) {
        
        ResponseEntity response = null;
        List<TblLimitRequest> list = limitRequestRepository.findByIp(ip);
        
        if (list.isEmpty()) {
            
            response = ResponseEntity.
                    status(HttpStatus.CREATED).body("no tiene limites");
        }else{
            int limite = list.stream()
                            .mapToInt(TblLimitRequest::getLimite_maximo)
                            .max()
                            .orElse(0);

            List<String> rutasUnicas = list.stream()
                    .map(TblLimitRequest::getRuta)
                    .distinct()
                    .collect(Collectors.toList());
            
            response = ResponseEntity.status(HttpStatus.OK)
                    .body(LimitResponseDTO.builder()
                            .limite_maximo(limite)
                            .rutasRestringuidas(list)
                            .build());
            
        }        
        return response;
    }
    
}
