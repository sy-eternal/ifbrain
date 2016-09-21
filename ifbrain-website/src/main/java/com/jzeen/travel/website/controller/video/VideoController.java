package com.jzeen.travel.website.controller.video;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.data.entity.Video;
import com.jzeen.travel.data.repository.VideoReponsitory;
import com.jzeen.travel.website.setting.FileUploadSetting;

@Controller
@RequestMapping("/video")
public class VideoController
{
    @Autowired
    private VideoReponsitory _videoRepository;

    @Autowired
    private FileUploadSetting _fileUploadSetting;

    @RequestMapping(value = "/play/{id}", method = RequestMethod.GET)
    public void show(@PathVariable Integer id, HttpServletResponse response) throws IOException
    {
    	Video video = _videoRepository.findOne(id);
        String filePath = video.getVideoPath();
        /*String pre = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
        String predecode = java.net.URLDecoder.decode(pre,"utf-8"); 
        String last=filePath.substring(0, filePath.lastIndexOf("/")+1);
        System.out.println("---------------"+ last+predecode+"------------");*/
        String rootPath = _fileUploadSetting.getRootPath();
         ServletOutputStream stream = response.getOutputStream();// 得到向客户端输出二进制数据的对象
        FileInputStream fis = new FileInputStream(rootPath + filePath); // 以byte流的方式打开文件
        byte data[] = new byte[1000];
        while (fis.read(data) > 0)
        {
            stream.write(data);
        }
        fis.close();
        response.setContentType("video/mpeg4"); // 设置返回的文件类型
        stream.write(data); // 输出数据
        stream.close();
    	/*InputStream in = null;
        ServletOutputStream out = null;
        try {
            in = new FileInputStream(rootPath+filePath);
            out = response.getOutputStream();
            byte[] buffer = new byte[4 * 1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件读取失败,文件不存在");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件流输出异常");
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                System.out.println("文件流关闭异常");
                e.printStackTrace();
            }
        }*/
    }
}
