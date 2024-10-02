/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.pos.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Pablo Porras
 */
public class TransactionDto {

    @JsonProperty("transaccionID")
    String transaccionID;

    public TransactionDto() {
    }
    
    public String getTransaccionID() {
        return transaccionID;
    }

    public void setTransaccionID(String transaccionID) {
        this.transaccionID = transaccionID;
    }

}
