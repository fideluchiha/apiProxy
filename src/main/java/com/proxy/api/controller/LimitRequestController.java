/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.controller;

import com.proxy.api.DTO.LimitRequestDTO;
import com.proxy.api.services.interfaces.ILimitRequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fidel
 */
@RestController
@RequestMapping("limit")
@CrossOrigin("*")
public class LimitRequestController {
    
    @Autowired
    private ILimitRequestServices services;
    
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveLimit(@RequestBody LimitRequestDTO request){
        
        return services.save(request);
    }
    
    @DeleteMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteLimit(@RequestBody LimitRequestDTO request){
        
        return services.deleteByIpAndRuta(request);
    }
    
     @GetMapping(value ="/{ip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByIp(@PathVariable("ip") String ip) {
        
        return this.services.findByIp(ip);
    
    }
    
     @GetMapping(value ="/view/{ip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByIpLimit(@PathVariable("ip") String ip) {
        
        return this.services.findByIpLimits(ip);
    
    }
    
}
