package com.haoshuang.sso.demosso.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}