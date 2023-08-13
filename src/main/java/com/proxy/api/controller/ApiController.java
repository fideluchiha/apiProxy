/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.controller;

import com.proxy.api.services.interfaces.IConsumosServices;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fidel
 */
@RestController
@CrossOrigin("*")
public class ApiController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private IConsumosServices iConsumosServices;

       @GetMapping(value ="/{ruta}/{variable}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> proxyRequest(@PathVariable String ruta, @PathVariable String variable, HttpServletRequest request) {
        return iConsumosServices.requestUrl(ruta, variable, request);
    }

    
    
}
