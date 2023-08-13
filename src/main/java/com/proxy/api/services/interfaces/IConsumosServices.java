/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.services.interfaces;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author fidel
 */
@Service
public interface IConsumosServices {
    
    ResponseEntity requestUrl(String ruta,String variable,HttpServletRequest request);
    
    ResponseEntity findByIp(String ip);
    
    ResponseEntity findAll();
    
    ResponseEntity estadisticas();
    
}
