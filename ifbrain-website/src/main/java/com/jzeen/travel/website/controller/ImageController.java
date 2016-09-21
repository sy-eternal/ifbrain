package com.jzeen.travel.website.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.data.entity.CommodityType;
import com.jzeen.travel.data.entity.HeadPortrait;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.ShoppingmallCommodity;
import com.jzeen.travel.data.entity.ShoppingmallCommodityImage;
import com.jzeen.travel.data.entity.WeChatUser;
import com.jzeen.travel.data.repository.CommodityTypeRepository;
import com.jzeen.travel.data.repository.HeadPortraitRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.ShoppingmallCommodityImageRepository;
import com.jzeen.travel.data.repository.WeChatUserRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;

@Controller
@RequestMapping("/image")
public class ImageController
{
    @Autowired
    private ImageRepository _imageRepository;

    @Autowired
    CommodityTypeRepository _commodityTypeRepository;

    @Autowired
    WeChatUserRepository _wChatUserRepository;
    @Autowired
    private FileUploadSetting _fileUploadSetting;
    
    @Autowired
    private HeadPortraitRepository _headPortraitRepository;

    @Autowired
    private ShoppingmallCommodityImageRepository _shoppingmallCommodityImageRepository;
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public void show(@PathVariable Integer id, HttpServletResponse response) throws IOException
    {
        Image image = _imageRepository.findOne(id);
        String filePath = image.getFilePath();
        String rootPath = _fileUploadSetting.getRootPath();
        String path=rootPath + filePath;
        ServletOutputStream stream = response.getOutputStream();// 得到向客户端输出二进制数据的对象
        FileInputStream fis = new FileInputStream(rootPath + filePath); // 以byte流的方式打开文件
        byte data[] = new byte[1000];
        while (fis.read(data) > 0)
        {
            stream.write(data);
        }
        fis.close();
        response.setContentType("image/*"); // 设置返回的文件类型
        stream.write(data); // 输出数据
        stream.close();
    }
    @RequestMapping(value = "/showType/{id}", method = RequestMethod.GET)
    public void showType(@PathVariable Integer id, HttpServletResponse response) throws IOException
    {
         CommodityType type = _commodityTypeRepository.findOne(id);
        String filePath = type.getFilepath();
        String rootPath = _fileUploadSetting.getRootPath();
        ServletOutputStream stream = response.getOutputStream();// 得到向客户端输出二进制数据的对象
        FileInputStream fis = new FileInputStream(rootPath + filePath); // 以byte流的方式打开文件
        byte data[] = new byte[1000];
        while (fis.read(data) > 0)
        {
            stream.write(data);
        }
        fis.close();
        response.setContentType("image/*"); // 设置返回的文件类型
        stream.write(data); // 输出数据
        stream.close();
    }
    @RequestMapping(value = "/showwechat/{id}", method = RequestMethod.GET)
    public void showwechat(@PathVariable Integer id, HttpServletResponse response) throws IOException
    {
         WeChatUser wechat = _wChatUserRepository.findOne(id);
        String filePath = wechat.getHeadimgurl();
        String rootPath = _fileUploadSetting.getRootPath();
        ServletOutputStream stream = response.getOutputStream();// 得到向客户端输出二进制数据的对象
        FileInputStream fis = new FileInputStream(rootPath + filePath); // 以byte流的方式打开文件
        byte data[] = new byte[1000];
        while (fis.read(data) > 0)
        {
            stream.write(data);
        }
        fis.close();
        response.setContentType("image/*"); // 设置返回的文件类型
        stream.write(data); // 输出数据
        stream.close();
    }
    
    @RequestMapping(value = "/shownavibar/{id}", method = RequestMethod.GET)
    public void shownavibar(@PathVariable Integer id, HttpServletResponse response) throws IOException
    {
         HeadPortrait headPortrait = _headPortraitRepository.findOne(id);
        String filePath = headPortrait.getFilePath();
        String rootPath = _fileUploadSetting.getRootPath();
        ServletOutputStream stream = response.getOutputStream();// 得到向客户端输出二进制数据的对象
        FileInputStream fis = new FileInputStream(rootPath + filePath); // 以byte流的方式打开文件
        byte data[] = new byte[1000];
        while (fis.read(data) > 0)
        {
            stream.write(data);
        }
        fis.close();
        response.setContentType("image/*"); // 设置返回的文件类型
        stream.write(data); // 输出数据
        stream.close();
    }
    
    @RequestMapping(value = "/shoppingmallCommodity/{id}", method = RequestMethod.GET)
    public void shoppingmallCommodity(@PathVariable Integer id, HttpServletResponse response) throws IOException
    {
    	ShoppingmallCommodityImage shoppingmallCommodity = _shoppingmallCommodityImageRepository.findOne(id);
        String filePath = shoppingmallCommodity.getFilePath();
        String rootPath = _fileUploadSetting.getRootPath();
        ServletOutputStream stream = response.getOutputStream();// 得到向客户端输出二进制数据的对象
        FileInputStream fis = new FileInputStream(rootPath + filePath); // 以byte流的方式打开文件
        byte data[] = new byte[1000];
        while (fis.read(data) > 0)
        {
            stream.write(data);
        }
        fis.close();
        response.setContentType("image/*"); // 设置返回的文件类型
        stream.write(data); // 输出数据
        stream.close();
    }
}
