package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Title: X2OUR_TRAVEL
 * Description: 请求检验工具类
 * Date: 2015年 08月 04日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class SignUtil {

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String tmpStr = genSHA(WeChatConts.token, timestamp, nonce);

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 绑定用户时签名验证
     *
     * @param signature
     * @param timestamp
     * @param openId
     * @return
     */
    public static boolean checkBindSignature(String signature, String timestamp, String openId) {

        String tmpStr = genSHA(WeChatConts.bindToken, timestamp, openId);

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    // 三个参数进行字典序排序，返回SHA后的值
    public static String genSHA(String strA, String strB, String strC) {
        String[] arr = new String[]{strA, strB, strC};

        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;

        return tmpStr;
    }

    // 三个参数进行字典序排序，返回SHA后的值
    public static String genSHADoubleStr(String strA, String strB) {
        String[] arr = new String[]{strA, strB};

        // 将token、openId 两个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;

        return tmpStr;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    /**
     * main方法，用于测试
     * @param args
     */
    public static void main(String[] args){

        String signature = SignUtil.genSHADoubleStr(WeChatConts.bindToken, "oQDRrtxvHg6dVCI4axRxO5QxB-i8");

        System.out.println(signature);

    }
}
