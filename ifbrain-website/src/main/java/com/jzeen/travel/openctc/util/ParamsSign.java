package com.jzeen.travel.openctc.util;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 31日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ParamsSign {
    private static char[] base64EncodeChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    public ParamsSign() {
    }

    public static String value(TreeMap<String, String> params, String app_secret) {
        try {
            String e = "";

            String hmacSHA1Bytes;
            for (Iterator var4 = params.keySet().iterator(); var4.hasNext(); e = e + "&" + hmacSHA1Bytes + "=" + (String) params.get(hmacSHA1Bytes)) {
                hmacSHA1Bytes = (String) var4.next();
            }

            byte[] hmacSHA1Bytes1 = hmacSHA1Encrypt(e.substring(1), app_secret);
            return URLEncoder.encode(encryptBASE64(hmacSHA1Bytes1), "UTF-8");
        } catch (Exception var5) {
            var5.printStackTrace();
            return "error";
        }
    }

    private static byte[] hmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        String MAC_NAME = "HmacSHA1";
        String ENCODING = "UTF-8";
        byte[] data = encryptKey.getBytes(ENCODING);
        SecretKeySpec secretKey = new SecretKeySpec(data, MAC_NAME);
        Mac mac = Mac.getInstance(MAC_NAME);
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        return mac.doFinal(text);
    }

    private static String encryptBASE64(byte[] key) throws Exception {
        return new String(base64Encode(key));
    }

    private static String base64Encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;

        while (i < len) {
            int b1 = data[i++] & 255;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 3) << 4]);
                sb.append("==");
                break;
            }

            int b2 = data[i++] & 255;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 3) << 4 | (b2 & 240) >>> 4]);
                sb.append(base64EncodeChars[(b2 & 15) << 2]);
                sb.append("=");
                break;
            }

            int b3 = data[i++] & 255;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[(b1 & 3) << 4 | (b2 & 240) >>> 4]);
            sb.append(base64EncodeChars[(b2 & 15) << 2 | (b3 & 192) >>> 6]);
            sb.append(base64EncodeChars[b3 & 63]);
        }

        return sb.toString();
    }
}
