/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.request.dto;

/**
 *
 * @author CATr
 */
public enum ChargeType {
    T("transaction"), 
    S("subscription"), 
    R("recurring"),
    U("unsubscription"), 
    N("notify"), 
    F("free");
    
    private final String desc;
    
    ChargeType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
    
}
