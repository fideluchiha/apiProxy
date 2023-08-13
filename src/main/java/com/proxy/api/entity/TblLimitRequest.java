/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proxy.api.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author fidel
 */
@Entity
@Table(name = "tbl_limit_request")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TblLimitRequest {
    // LimitRequestServices
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "limite_maximo")
    private int limite_maximo;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    
     @PrePersist
    public void prePersist (){
        this.created = new Date();
    }
    
    @PreUpdate
    public void preUpdate(){
            this.updated= new Date();
    }
    
    
    
}
