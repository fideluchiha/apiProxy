/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.services.interfaces;

import com.proxy.api.DTO.LimitRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author fidel
 */
@Service
public interface ILimitRequestServices {
    
    
    ResponseEntity save(LimitRequestDTO Dto);
    
    ResponseEntity findByIp(String ip);
    
    ResponseEntity deleteByIpAndRuta(LimitRequestDTO Dto);
    
    ResponseEntity findByIpLimits(String ip);
    
}
