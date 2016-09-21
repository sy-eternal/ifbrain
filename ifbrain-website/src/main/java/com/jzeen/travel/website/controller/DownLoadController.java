package com.jzeen.travel.website.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.website.setting.FileUploadSetting;



@Controller
public class DownLoadController{
    
    @Autowired
    private FileUploadSetting _fileUploadSetting;
    
    
    
    
    @ResponseBody
    @RequestMapping(value = "/downloads")
    public  void download(String path, HttpServletRequest request,HttpServletResponse response) {
        
       
        try  {
            _fileUploadSetting.getRootPath();
            request.setCharacterEncoding("UTF-8");  
            BufferedInputStream bis = null;  
            BufferedOutputStream bos = null;  
            File file  =   new  File(path);
            String nowpath=_fileUploadSetting.getRootPath()+path;
            String filename  =  file.getName();
            //获取文件的长度
            long fileLength = file.length();  
            //设置文件输出类型
          /*  response.setContentType("application/octet-stream");  
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String(filename.getBytes("utf-8"), "ISO8859-1")); */
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes("gbk"),"iso-8859-1") + "\"");    
               response.setContentType("application/octet-stream;charset=UTF-8");  
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
