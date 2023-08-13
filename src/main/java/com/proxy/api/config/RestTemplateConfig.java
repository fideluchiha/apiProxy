/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author fidel
 */
@Configuration
public class RestTemplateConfig {
    
     @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}