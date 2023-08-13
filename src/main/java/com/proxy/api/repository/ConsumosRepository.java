/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.repository;

import com.proxy.api.entity.TblConsumos;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fidel
 */
@Repository
public interface ConsumosRepository extends CrudRepository<TblConsumos, Long>{
    
    List<TblConsumos> findByIpAndEstado(String ip,String estado);
    List<TblConsumos> findByIp(String ip);
    
    @Query("SELECT DISTINCT t.ruta FROM TblConsumos t")
    List<String> findDistinctRutas();
    
    @Query("SELECT COUNT(t) FROM TblConsumos t WHERE t.estado = 'permitido'")
    Long countByEstadoPermitido();
    
    @Query("SELECT COUNT(t) FROM TblConsumos t WHERE t.estado = 'bloqueado'")
    Long countByEstadoBloquedo();
    
    @Query("SELECT COUNT(t) FROM TblConsumos t WHERE t.estado = 'bad_request'")
    Long countByEstadoError();
    
    @Query("SELECT COUNT(t) FROM TblConsumos t")
    Long countByTotal();
}
