/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.repository;

import com.proxy.api.entity.TblLimitRequest;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fidel
 */
@Repository
public interface LimitRequestRepository extends CrudRepository<TblLimitRequest, Long>{
    
    List<TblLimitRequest> findByIp(String ip);
    
    List<TblLimitRequest> findByIpAndRuta(String ip,String ruta);
    
    TblLimitRequest save(TblLimitRequest tblLimitRequest);
    
}
