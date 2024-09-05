/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.win.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Pablo Porras
 */
public class ResponseGeneric {

    @JsonProperty("codeField")
    String codeField;
    @JsonProperty("messageField")
    String messageField;

    @JsonProperty("messageErrorField")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String messageErrorField;
    @JsonProperty("PropertyChanged")
    @JsonInclude(JsonInclude.Include.NON_NULL) // Incluir solo si no es null
    String PropertyChanged;

    public ResponseGeneric() {
    }

    public void setCodeField(String codeField) {
        this.codeField = codeField;
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

    public String getMessageField() {
        return messageField;
    }

    public String getPropertyChanged() {
        return PropertyChanged;
    }

}
