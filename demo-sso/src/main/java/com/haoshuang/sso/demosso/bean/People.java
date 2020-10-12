package com.haoshuang.sso.demosso.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class People implements Serializable {
    private Integer id;

    private String name;

    private Integer age;

    private static final long serialVersionUID = 1L;
}