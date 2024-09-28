/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.win.service;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Pablo Porras
 */
public class ResponseTrasactionID extends ResponseGeneric{

        public ResponseTrasactionID() {
        }

        @JsonProperty("transactionIDField")
        String transactionIDField;


        public void setTransactionIDField(String transactionIDField) {
            this.transactionIDField = transactionIDField;
        }

        public String getTransactionIDField() {
            return transactionIDField;
        }

    }
