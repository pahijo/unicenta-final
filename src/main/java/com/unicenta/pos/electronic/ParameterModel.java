/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.pos.electronic;

import java.io.Serializable;

/**
 *
 * @author Pablo Porras
 */
public class ParameterModel implements Serializable {
    
    private static final long serialVersionUID = 9083257536521L;
    public ParameterModel(){
    
    }

    public ParameterModel(Integer Id) {
        this.Id = Id;
        this.code = "";
        this.description = "";
        this.isActive = 1;
        this.value = "";
    }
    
    private String code;
    private String description;
    private String value;
    private int isActive;
    
    private Integer Id;

    public Integer getId() {
        return Id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    
    
}
