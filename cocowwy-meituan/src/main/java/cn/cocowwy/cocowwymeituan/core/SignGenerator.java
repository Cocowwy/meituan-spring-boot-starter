package cn.cocowwy.cocowwymeituan.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author cocowwy.cn
 * @create 2021-12-12-12:03
 */
public class SignGenerator {
    public SignGenerator() {
    }

    public static String genSig(String baseUrl) throws RuntimeException {
        String str = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            str = byte2hex(md.digest(baseUrl.getBytes()));
            return str;
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException("系统异常");
        }
    }

    private static String byte2hex(byte[] b) {
        StringBuffer buf = new StringBuffer();

        for (int offset = 0; offset < b.length; ++offset) {
            int i = b[offset];
            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }
}