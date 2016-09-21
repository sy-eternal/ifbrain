package com.jzeen.travel.admin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownLoadController{

    @ResponseBody
    @RequestMapping(value = "/downloads")
    public  void download(@RequestParam("path") String path, @RequestParam("tbu") String tbu, HttpServletRequest request,HttpServletResponse response) {
        try  {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;  
            File file  =   new  File(path);
            String filename = new String(file.getName().getBytes("ISO-8859-1"),"utf-8" );


            //淘宝订单号特殊处理
            if (tbu != null && tbu != "") {
                //截取后缀.zip
                String postfix = filename.substring(filename.lastIndexOf("."), filename.length());
                //截取.zip之前的
                String prefix = filename.substring(0, filename.lastIndexOf("."));

                filename = prefix + tbu + postfix;
            }

            //获取文件的长度
            long fileLength = file.length();  
            //设置文件输出类型
            response.setContentType("application/octet-stream");  
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String(filename.getBytes("utf-8"), "ISO8859-1")); 
            //设置输出长度
            response.setHeader("Content-Length", String.valueOf(fileLength));  
            //获取输入流
            bis = new BufferedInputStream(new FileInputStream(path));  
            //输出流
            bos = new BufferedOutputStream(response.getOutputStream());  
            byte[] buff = new byte[2048];  
            int bytesRead;  
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
            }  
            //关闭流
            bis.close();  
            bos.close();
           
       }  catch  (IOException ex) {
           ex.printStackTrace();
       }
   }


}
