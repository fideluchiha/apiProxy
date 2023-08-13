/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.DTO;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author fidel
 */
@Data
@Builder
public class EstadisticaDTO {
    
    private Long request_total;
    private Long request_permitidos;
    private Long request_bloqueados;
    private Long request_errores;
    private List<String> rutas_solicitadas;
    
}
