package com.haoshuang.sso.demosso.config.ValidateCode.validateInterface;

public enum ValidateCodeType {

    SMS{
        @Override
        public String getParaNameOnValidate() {
            return "smsCode";
        }
    },
    EMAIL{
        @Override
        public String getParaNameOnValidate() {
            return "emailCode";
        }
    },
    IMAGE{
        @Override
        public String getParaNameOnValidate() {
            return "imageCode";
        }
    };
    public abstract String getParaNameOnValidate();
}
