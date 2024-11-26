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
public class ResponseDocumentBase extends ResponseGeneric {
    
    @JsonProperty("documentBase64Field")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String documentBase64Field;

    public ResponseDocumentBase() {
    }

    public String getDocumentBase64Field() {
        return documentBase64Field;
    }

    public void setDocumentBase64Field(String documentBase64Field) {
        this.documentBase64Field = documentBase64Field;
    }
}
