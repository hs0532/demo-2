package com.haoshuang.sso.demosso.common;

public enum ResultEnm {

        /**成功*/
        SUCCESS(0,"SUCCESS"),

        /**失败*/
        ERROR(1,"ERROR"),

        /**需要登录*/
        NEED_LOGIN(10,"NEED_LOGIN"),

        /**需要登录*/
        NEED_MOBILE_AUTH(3,"NEED_MOBILE_AUTH"),

        /**非法的参数*/
        ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

        private final int code;
        private final String desc;


        ResultEnm(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }


}
