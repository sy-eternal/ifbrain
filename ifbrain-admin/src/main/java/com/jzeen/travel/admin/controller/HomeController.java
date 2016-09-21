package com.jzeen.travel.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.admin.setting.MailSetting;
import com.jzeen.travel.core.util.FileUtil;
import com.jzeen.travel.core.util.MailUtil;

@Controller
public class HomeController
{
    @Autowired
    private MailSetting _mailSetting;

    @Autowired
    private FileUploadSetting _fileUploadSetting;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index()
    {
        return "/home/index";
    }

    @RequestMapping(value = "/home/sendMail", method = RequestMethod.GET)
    public String sendMail()
    {
        MailUtil mailUtil = new MailUtil(_mailSetting.getHost(), _mailSetting.getPort(), _mailSetting.getUsername(),
                _mailSetting.getPassword());
        String to = "test@hotmail.com";
        String subject = "Spring 发送邮件";
        String text = "点击跳转：<a href=\"http://www.baidu.com\" target=\"_blank\">百度</a>";
        mailUtil.sendHtmlMail(_mailSetting.getFrom(), to, subject, text);

        return "/home/index";
    }

    /**
     * 测试上传文件
     */
    @ResponseBody
    @RequestMapping(value = "/home/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file)
    {
        String folderName = _fileUploadSetting.getRootPath();
        String filePath = FileUtil.getUploadFolder(folderName);
        if (!file.isEmpty())
        {
            try
            {
                byte[] bytes = file.getBytes();
                File directory = new File(filePath);
                if (!directory.exists())
                {
                    directory.mkdirs();
                }

                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileName = FileUtil.getUniqueName() + suffix;
                filePath += File.separator + fileName;

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(bytes);
                stream.close();

                return "You successfully uploaded " + filePath + "!";
            }
            catch (Exception e)
            {
                return "You failed to upload " + filePath + " => " + e.getMessage();
            }
        }
        else
        {
            return "You failed to upload " + filePath + " because the file was empty.";
        }
    }

    @RequestMapping(value = "/home/angular")
    public String angular()
    {
        return "/home/angular";
    }
}
