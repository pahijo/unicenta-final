/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.pos.electronic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Pablo Porras
 */
public class ResponseWinService {

        public ResponseWinService() {
        }

        @JsonProperty("codeField")
        String codeField;
        @JsonProperty("transactionIDField")
        String transactionIDField;
        @JsonProperty("messageField")
        String messageField;
        @JsonProperty("PropertyChanged")
        @JsonInclude(JsonInclude.Include.NON_NULL) // Incluir solo si no es null
        String PropertyChanged;

        public void setCodeField(String codeField) {
            this.codeField = codeField;
        }

        public void setTransactionIDField(String transactionIDField) {
            this.transactionIDField = transactionIDField;
        }

        public void setMessageField(String messageField) {
            this.messageField = messageField;
        }

        public void setPropertyChanged(String PropertyChanged) {
            this.PropertyChanged = PropertyChanged;
        }

        public String getCodeField() {
            return codeField;
        }

        public String getTransactionIDField() {
            return transactionIDField;
        }

        public String getMessageField() {
            return messageField;
        }

        public String getPropertyChanged() {
            return PropertyChanged;
        }

    }
