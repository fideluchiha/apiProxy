/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.controller;

import com.proxy.api.services.interfaces.IConsumosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fidel
 */
@RestController
@RequestMapping("consumos")
@CrossOrigin("*")
public class ConsumosController {
    
    @Autowired
    private IConsumosServices services;
    
     @GetMapping(value ="/{ip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByIp(@PathVariable("ip") String ip) {
        
        return this.services.findByIp(ip);
    
    }
    
    @GetMapping(value ="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAll() {
        
        return this.services.findAll();
    
    }
    
     @GetMapping(value ="/estadisticas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findEstadisticas() {
        
        return this.services.estadisticas();
    
    }
}
