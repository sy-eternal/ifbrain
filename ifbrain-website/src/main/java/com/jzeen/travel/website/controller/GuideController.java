package com.jzeen.travel.website.controller;

import com.jzeen.travel.core.util.CipherUtil;
import com.jzeen.travel.core.util.MailContentUtil;
import com.jzeen.travel.core.util.MailUtil;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Document;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideImageRelate;
import com.jzeen.travel.data.entity.GuideTags;
import com.jzeen.travel.data.entity.HotelTags;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.InstructionFileRelate;
import com.jzeen.travel.data.entity.RentalCar;
import com.jzeen.travel.data.entity.RentalPlan;
import com.jzeen.travel.data.entity.RentalRate;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.GuideImageRelateRepository;
import com.jzeen.travel.data.repository.GuideRepository;
import com.jzeen.travel.data.repository.GuideTagsRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;
import com.jzeen.travel.website.setting.MailSetting;
import com.jzeen.travel.website.setting.WebUrlSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/guide")
public class GuideController
{
    @Autowired
    CityRepository _cityRepository;
    @Autowired
    ImageRepository _imageRepository;
    @Autowired
    UserRepository _userRepository;
    @Autowired
    FileUploadSetting _fileUploadSetting;
    @Autowired
    MailSetting _mailSetting;
    @Autowired
    WebUrlSetting _webUrlSetting;
    @Autowired
    CodeRepository _codeRepository;
    @Autowired
    GuideRepository _GuideRepository;
    @Autowired
    GuideTagsRepository _GuidetagsRepository;
    @Autowired
    GuideImageRelateRepository _GuideImageRelateRepository;
    StringBuffer s=new StringBuffer();
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerInit(@ModelAttribute Guide guide, Model model)
    {
        
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys",citys);
       //导游支付方式
        String type="支付方式";
        List<Code> code = _codeRepository.findByType(type);
        model.addAttribute("code", code);
        model.addAttribute("guide", guide);
        return "/guide/register";
    }

    /*
     * daoyou注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid  User user,@Valid Guide guides, BindingResult bindingResult, Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1,@RequestParam("file4") MultipartFile file4,@RequestParam("file2") MultipartFile[] file2,@RequestParam("file3") MultipartFile[] file3) throws IOException, ParseException {
        //获得性别
       Guide guide =new Guide();
  
        String sex = request.getParameter("sex");
        Integer sexInt = Integer.parseInt(sex);
        String messageInfo = "";
        Guide guideA=_GuideRepository.findByUser(user.getEmail(),2);
        if (guideA != null) {
            messageInfo = "邮箱已存在，请重新输入";
            model.addAttribute("messageInfo", messageInfo);
            return "/guide/register";
        }
        int authCode = (int) (Math.random() * 9000 + 1000);
        String activeCode = "a" + authCode;
        user.setActivitycode(activeCode);
        //获取图片上传
        String filePath = _fileUploadSetting.getRootPath();
        if (!file.isEmpty()&&!file1.isEmpty()&&file2.length>0&&file3.length>0) {
          //获得支付方式
            String payMethodCode = request.getParameter("payMethodCode");
            Integer payMethodCodeInt = Integer.parseInt(payMethodCode);
            //是否愿意到其他地方
            String radio = request.getParameter("radio");
            int radioInt = Integer.parseInt(radio);
          //激活码有效期至当前日期的明天
            Date date = new Date();
            Date date1 = new Date();
             Calendar calendar = new GregorianCalendar();
             calendar.setTime(date); 
             calendar.add(calendar.DATE, 1);//
         //   把日期往后增加一天.整数往后推,负数往前移动 
             date1 = calendar.getTime(); // 这个时间就是日期往后推一天的结果
            //获得该导游所用的支付方式，从代码表中获得，代码表和导游用户是一对一
            //用户
            User users=new User();
            users.setActivityValidity(date1);
            users.setUserType(2);
            users.setEmail(user.getEmail());
            users.setCreateTime(date);
            users.setFirstName(request.getParameter("firstName"));
            users.setLastName(request.getParameter("lastName"));
            String passwrod = user.getPassword();
            users.setPassword(CipherUtil.generatePassword(passwrod));
            users.setActiveStatus(0);
            users.setMobile(request.getParameter("mobile"));
            users.setActivitycode(activeCode);
            users.setWechat(request.getParameter("wechat"));
            _userRepository.save(users);
            //导游
            guide.setPaymethodcode(payMethodCodeInt);
            guide.setSex(sexInt);
            guide.setToothercity(radioInt);
            guide.setCreatetime(date);
            guide.setApprovalstatus(1);
            guide.setCreatetime(date);
            Calendar calendar1 = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar1.DATE, 1);
            date = calendar.getTime();
//            guide.setImage(image);
            guide.setUser(users);
            guide.setHostCity(guides.getHostCity());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            guide.setBirthday(sdf.parse(request.getParameter("birthday")));
            guide.setNationality(request.getParameter("nationality"));
            guide.setDrivelicensecode(request.getParameter("driveLicenseCode"));
            guide.setPassportcode(request.getParameter("passportCode"));
            guide.setMotto(request.getParameter("motto"));
            guide.setStayduration(Integer.parseInt(request.getParameter("stayduration")));
            guide.setSelfdescription(request.getParameter("selfdescription"));
            guide.setDrivelicensecode(request.getParameter("drivelicensecode"));
            guide.setCitydescription(request.getParameter("citydescription"));
         
            //保存
            _GuideRepository.save(guide);
         
           String[] guidetags =request.getParameterValues("tag");
            for (int i = 0; i < guidetags.length; i++) {
                if(!(guidetags[i].equals(null)) && (!(guidetags[i].equals("")))){
                    GuideTags guidetag =new GuideTags();
                    guidetag.setGuide(guide);
                    guidetag.setTag(guidetags[i]);
                    guidetag.setCreateTime(new Date());
                    _GuidetagsRepository.save(guidetag);
                }
            }
            // 配上当地风景照
            for(int i=0;i<file2.length;i++){
                try
                {
                    byte[] bytes = file2[i].getBytes();
                    File directory = new File(_fileUploadSetting.getRootPath()+filePath);
                    
                    if (!directory.exists())
                    {
                        directory.mkdirs();
                    }
                    String fileName =file2[i].getOriginalFilename();
                    String nowpath=filePath + fileName;
                    String path=File.separator + fileName;
                    //图片上传
                    Image image = new Image();
                    image.setCreateTime(new Date());
                    image.setFileName(fileName);
                    image.setFilePath(nowpath);
                    _imageRepository.save(image);
                    
                    GuideImageRelate guideimage =new GuideImageRelate();
                    guideimage.setGuide(guide);
                    guideimage.setImage(image);
                    guideimage.setCreatetime(new Date());
                    guideimage.setType(4);
                    _GuideImageRelateRepository.save(guideimage);
                    //写文件
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + nowpath)));
                    stream.write(bytes);
                    stream.close();
                    
                }catch(Exception e){
                    _userRepository.delete(guide.getUser().getId());
                    _GuideRepository.delete(guide);
                    
                    model.addAttribute("errorMessage1", "上传图片失败");
                    return "/guide/register";
                }
              
            }
            //  上传个人生活照
            for(int i=0;i<file3.length;i++){
                try
                {
                    byte[] bytes = file3[i].getBytes();
                    File directory = new File(_fileUploadSetting.getRootPath()+filePath);
                    
                    if (!directory.exists())
                    {
                        directory.mkdirs();
                    }
                    String fileName =file3[i].getOriginalFilename();
                    String nowpath=filePath + fileName;
                    String path=File.separator + fileName;
                    //图片上传
                    Image image = new Image();
                    image.setCreateTime(new Date());
                    image.setFileName(fileName);
                    image.setFilePath(nowpath);
                    _imageRepository.save(image);
                    
                    GuideImageRelate guideimage =new GuideImageRelate();
                    guideimage.setGuide(guide);
                    guideimage.setImage(image);
                    guideimage.setCreatetime(new Date());
                    guideimage.setType(5);
                    _GuideImageRelateRepository.save(guideimage);
                    //写文件
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + nowpath)));
                    stream.write(bytes);
                    stream.close();
                    
                }catch(Exception e){
                    _userRepository.delete(guide.getUser().getId());
                    _GuideRepository.delete(guide);
                    
                    model.addAttribute("errorMessage1", "上传图片失败");
                    return "/guide/register";
                }
              
            }
         
            byte[] bytes = file.getBytes();
            try {
                File directory = new File(filePath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                //驾照扫描
                String fileName = file.getOriginalFilename();
                filePath += File.separator + fileName;
                String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
                Image image = new Image();
                image.setCreateTime(new Date());
                image.setFileName(fileName);
                image.setFilePath(lujing);
                _imageRepository.save(image);
                
                
                GuideImageRelate guideimage =new GuideImageRelate();
                guideimage.setGuide(guide);
                guideimage.setImage(image);
                guideimage.setType(2);
                guideimage.setCreatetime(new Date());
                _GuideImageRelateRepository.save(guideimage);
                //  选择封面照
                byte[] bytes1 = file1.getBytes();
                String fileName1 = file1.getOriginalFilename();
                filePath += File.separator + fileName1;
                Image image1 = new Image();
                image1.setCreateTime(new Date());
                image1.setFileName(fileName1);
                image1.setFilePath(lujing);
                _imageRepository.save(image1);
                
                GuideImageRelate guideimage1 =new GuideImageRelate();
                guideimage1.setGuide(guide);
                guideimage1.setImage(image1);
                guideimage1.setType(3);
                guideimage1.setCreatetime(new Date());
                _GuideImageRelateRepository.save(guideimage1);
                
                
                //身份证护照
                byte[] bytes4 = file4.getBytes();
                String fileName4 = file4.getOriginalFilename();
                filePath += File.separator + fileName1;
                Image image4 = new Image();
                image4.setCreateTime(new Date());
                image4.setFileName(fileName4);
                image4.setFilePath(lujing);
                _imageRepository.save(image4);
                
                GuideImageRelate guideimage2 =new GuideImageRelate();
                guideimage2.setGuide(guide);
                guideimage2.setImage(image4);
                guideimage2.setType(1);
                guideimage2.setCreatetime(new Date());
                _GuideImageRelateRepository.save(guideimage2);
                messageInfo = "导游注册成功";
                model.addAttribute("messageInfo", messageInfo);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + lujing)));
                stream.write(bytes);
                stream.write(bytes1);
                stream.write(bytes4);
                stream.close();
                return "/guide/register";
            } catch (Exception e) {
                _userRepository.delete(guide.getUser().getId());
                _GuideRepository.delete(guide);
                model.addAttribute("errorMessage3", "上传文件失败");
                return "/guide/register";
            }
        } else {
            //激活码有效期至当前日期的明天
            Date date = new Date();
            guide.setSex(sexInt);
            guide.setCreatetime(date);
            user.setActiveStatus(0);
            guide.setApprovalstatus(2);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, 1);
            date = calendar.getTime();
            user.setActivityValidity(date);
            String passwrod = user.getPassword();
            user.setPassword(CipherUtil.generatePassword(passwrod));
            //保存
            _userRepository.save(user);
            _GuideRepository.save(guide);
            messageInfo = "注册成功，请点击邮箱中的激活链接进行激活";
            MailUtil mailUtil = new MailUtil(_mailSetting.getHost(), _mailSetting.getPort(), _mailSetting.getUsername(), _mailSetting.getPassword());
            String to = user.getEmail();
            String webRoot = _webUrlSetting.getRootUrl();
            String url = webRoot + "/guide/active?activeCode=" + activeCode + "&&&&&&&&&%%%%%%&email=" + user.getEmail() + "";
            String subject = "西游网帐号激活，开始您的浪漫旅程";
            String text = MailContentUtil.buildUserRegMailContent(user.getFirstName() + " " + user.getLastName(), webRoot, url);
            mailUtil.sendHtmlMail(_mailSetting.getFrom(), to, subject, text);
            model.addAttribute("messageInfo", messageInfo);
            return "/guide/register";

        }
    }
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public String active(HttpServletRequest request) {
        //通过邮箱查找对应信息
        String activeCode = request.getParameter("activeCode");
        String email = request.getParameter("email");
        User userA = _userRepository.findByEmail(email);
        if (userA != null) {
            //判断该条信息是否已被激活
            if (userA.getActiveStatus() == 0) {
                //判断激活码是否过期
                Date currentTime = new Date();
                currentTime.before(userA.getCreateTime());
                if (currentTime.before(userA.getActivityValidity())) {
                    if (activeCode.equals(userA.getActivitycode())) {
                        userA.getGuide().setApprovalstatus(1);
                        userA.setActiveStatus(1);
                        _userRepository.save(userA);
                    } else {
                        _userRepository.delete(userA);
                    }
                }
            } else {
                return "redirect:/guide/login";
            }
        }
        return "redirect:/guide/login";
    }
   
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginInit(Model model, HttpServletRequest request)
    {
        String returnUrl = request.getParameter("returnUrl");
        model.addAttribute("returnUrl", returnUrl);
        return "/guide/login";
    }
//导游登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request, HttpSession session) {
        //获取页面传过来的邮箱和密码的值
        model.addAttribute("email", request.getParameter("email"));
        model.addAttribute("password", request.getParameter("password"));
        String randCheckCode = (String) session.getAttribute("randCheckCode");
        String validCode = request.getParameter("yanzheng");
        user.setUserType(2);
        //验证码空=========================================
        if (validCode == "" || validCode == null || validCode.equalsIgnoreCase(randCheckCode)) {

            User userInDb = _userRepository.findByUserTypeAndEmailAndPassword(user.getUserType(), user.getEmail(), CipherUtil.generatePassword(user.getPassword()));
            if (userInDb != null) {
                if (userInDb.getActiveStatus() == 1) {

                    //  request.getSession().setAttribute("user",userInDb);
                    WebUtils.setSessionAttribute(request, "user", userInDb);
                    WebUtils.setSessionAttribute(request, "guide", userInDb.getGuide());
                    WebUtils.setSessionAttribute(request, "usertyoe", userInDb.getUserType());
                    WebUtils.setSessionAttribute(request, "usertyoes", userInDb.getFirstName() + userInDb.getLastName());
                    session.setMaxInactiveInterval(40 * 60);
                    return "redirect:/";
                } else {
                    model.addAttribute("message", "抱歉，用户没有进行激活无法登录");
                    return "/guide/login";
                }
            } else {
                if (validCode == "" || validCode == null) {
                    // 不输入验证码
                    s.append("s");
                    if (s.length() == 3) {
                        model.addAttribute("visitime", s.length());
                        s.delete(0, s.length());
                    }
                } else {
                    // 输入验证码正确
                    model.addAttribute("visitime", 3);
                }

                model.addAttribute("message", "邮箱或密码错误，请重新填写。");

                return "/guide/login";
            }
        } else {
            //验证码错误
            model.addAttribute("yanzhengmessage", "验证码有误，请重新填写。");
            model.addAttribute("visitime", 3);
            return "/guide/login";
        }
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotPassword(Model model) {
        model.addAttribute("message", "");
        return "/user/forgotPassword";
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public String forgotPassword(String email, Model model)
    {
        // 生成随机密码
        UUID uuid = UUID.randomUUID();
        String newPassword = uuid.toString();
        System.out.println("得到的新密码:"+newPassword);
        String CipherNewPassword=CipherUtil.generatePassword(newPassword);
        System.out.println("加密之后的密码:"+CipherNewPassword);
        // 通过邮箱找到用户
        User user = _userRepository.findByEmail(email);
        if (user == null) {
            model.addAttribute("message", "不存在邮箱为" + email + "的用户！");
            return "/user/forgotPassword";
        }

        user.setPassword(CipherNewPassword);
        _userRepository.save(user);

        // 将新密码通过邮件发送给用户
        MailUtil mailUtil = new MailUtil(_mailSetting.getHost(), _mailSetting.getPort(), _mailSetting.getUsername(),
                _mailSetting.getPassword());
        String to = email;
        String subject = "Travel - 找回密码";
        String text = "你的密码已经被重置，新的密码为：" + newPassword + "，请使用新密码进行登录。";
        mailUtil.sendHtmlMail(_mailSetting.getFrom(), to, subject, text);

        model.addAttribute("message", "新的密码已经发到您的邮箱：" + email + "，请使用新密码进行登录。");

        return "/user/forgotPassword";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPasswordInit() {
        return "/user/resetPassword";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.PUT)
    public String resetPassword(HttpServletRequest request, Model model) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !oldPassword.equals(user.getPassword())) {
            model.addAttribute("message", "旧密码错误，请重新填写！");
            return "/user/resetPassword";
        }

        user.setPassword(newPassword);
        _userRepository.save(user);

        model.addAttribute("message", "密码修改成功！");

        return "/user/resetPassword";
    }

}

