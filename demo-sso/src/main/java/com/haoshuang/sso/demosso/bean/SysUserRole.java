package com.haoshuang.sso.demosso.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRole implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}