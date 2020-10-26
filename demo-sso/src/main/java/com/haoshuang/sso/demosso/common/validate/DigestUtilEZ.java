package com.haoshuang.sso.demosso.common.validate;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DigestUtilEZ {
    public enum ALGORITHM_NAME {
        MD2, MD5, SHA_1, SHA_256, SHA_384, SHA_512,BASE_64,Decode_BASE_64
    }

    /**
     * 编码字符串
     *
     * @param sourceStr    需要编码的字符串String
     * @param algorithmsName 算法名称(如:MD2,MD5,SHA1,SHA256,SHA384,SHA512)
     * @return
     */
    public static String digestString(String sourceStr, ALGORITHM_NAME algorithmsName) {
        String password = null;
        switch (algorithmsName) {
            case MD2:
                password = DigestUtils.md2Hex(sourceStr);
                break;
            case MD5:
                password = DigestUtils.md5Hex(sourceStr);
                break;
            case SHA_1:
                password = DigestUtils.sha1Hex(sourceStr);
                break;
            case SHA_256:
                password = DigestUtils.sha256Hex(sourceStr);
                break;
            case SHA_384:
                password = DigestUtils.sha384Hex(sourceStr);
                break;
            case SHA_512:
                password = DigestUtils.sha512Hex(sourceStr);
                break;
            case BASE_64:
                password = Base64.getEncoder()
                        .encodeToString(sourceStr.getBytes(StandardCharsets.UTF_8));
                break;
            case Decode_BASE_64:
                password = new String(Base64.getDecoder().decode(sourceStr), StandardCharsets.UTF_8);
        }
        return password;
    }

}
