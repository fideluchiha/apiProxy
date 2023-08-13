/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.DTO;

import com.proxy.api.entity.TblLimitRequest;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author fidel
 */
@Data
@Builder
public class LimitResponseDTO {
    
    private int limite_maximo;
    private List<TblLimitRequest> rutasRestringuidas;
}
