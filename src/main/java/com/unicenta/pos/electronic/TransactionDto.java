/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.pos.electronic;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Pablo Porras
 */
public class TransactionDto {

    String TransacctionID;
   
    public String getTransacctionID() {
        return TransacctionID;
    }

    public void setTransacctionID(String TransacctionID) {
        this.TransacctionID = TransacctionID;
    }
}
