package com.jzeen.travel.admin.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Video;
import com.jzeen.travel.data.repository.VideoReponsitory;

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
        String rootPath = _fileUploadSetting.getRootPath();
        ServletOutputStream stream = response.getOutputStream();// 得到向客户端输出二进制数据的对象
        FileInputStream fis = new FileInputStream(rootPath + filePath); // 以byte流的方式打开文件
        byte data[] = new byte[1000];
        while (fis.read(data) > 0)
        {
            stream.write(data);
        }
        fis.close();
        response.setContentType("video/*"); // 设置返回的文件类型
        stream.write(data); // 输出数据
        stream.close();
    }
}
