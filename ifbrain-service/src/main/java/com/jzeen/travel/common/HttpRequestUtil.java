package com.jzeen.travel.common;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * httpRequest工具类
 *
 * @author Administrator
 */
public class HttpRequestUtil {

    private static Logger log = LoggerFactory.getLogger(HttpRequestUtil.class);

    /**
     * 发起http请求获取返回结果
     *
     * @param requestUrl 请求地址
     * @return
     */
    public static String httpRequest(String requestUrl) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (Exception e) {
        }
        return buffer.toString();
    }


    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new BaseX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
            httpsUrlConn.setSSLSocketFactory(ssf);

            httpsUrlConn.setDoOutput(true);
            httpsUrlConn.setDoInput(true);
            httpsUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpsUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpsUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpsUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpsUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            StringBuffer buffer = new StringBuffer();

            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpsUrlConn.disconnect();


            //判断返回的是否为xml，如果是将xml转换为json
            String returnStr  =  buffer.toString();

            if (returnStr.startsWith("<xml>")) {
                XMLSerializer xmlSerializer = new XMLSerializer();
                jsonObject = xmlSerializer.readObject(returnStr);
            } else {
                jsonObject = JSONObject.fromObject(returnStr);
            }

        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }


    /**
     * POST请求
     * @param postUrl
     * @param postHeaders
     * @param postEntity
     * @throws IOException
     * @return 提取HTTP响应报文包体，以字符串形式返回
     */
    public static String httpPost(String postUrl,Map<String, String> postHeaders, String postEntity) throws IOException {

        URL postURL = new URL(postUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) postURL.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty(" Content-Type ", " application/x-www-form-urlencoded ");

        if(postHeaders != null) {
            for(String pKey : postHeaders.keySet()) {
                httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey));
            }
        }
        if(postEntity != null) {
            DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
            out.writeBytes(postEntity);
            out.flush();
            out.close(); // flush and close
        }
        //connection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sbStr = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sbStr.append(line);
        }
        bufferedReader.close();
        httpURLConnection.disconnect();
        return new String(sbStr.toString().getBytes(),"utf-8");
    }


    /**
     * 发送http请求取得返回的输入流
     *
     * @param requestUrl 请求地址
     * @return InputStream
     */
    public static InputStream httpRequestToInputStream(String requestUrl) {
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url
                    .openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            // 获得返回的输入流
            inputStream = httpUrlConn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * utf编码
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
