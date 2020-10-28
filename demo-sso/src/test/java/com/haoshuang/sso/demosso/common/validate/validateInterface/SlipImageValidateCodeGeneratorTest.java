package com.haoshuang.sso.demosso.common.validate.validateInterface;

import com.haoshuang.sso.demosso.common.validate.ValidateCodeBean.VerifyImageCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.jvm.hotspot.utilities.Assert;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SlipImageValidateCodeGeneratorTest {

    @Autowired
    SlipImageValidateCodeGenerator slipImageValidateCodeGenerator;

    @Test
    void getVerifyImage() {
        try {
           VerifyImageCode verifyImageCode = slipImageValidateCodeGenerator.getVerifyImage("/Users/haoshuang/IdeaProjects/demo-2/demo-sso/target/classes/static/static/img/verifyImages/1.jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}