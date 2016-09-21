package com.jzeen.travel.admin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;





import com.jzeen.travel.core.util.CipherUtil;
import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Member;
import com.jzeen.travel.data.entity.Menu;
import com.jzeen.travel.data.entity.QMember;
import com.jzeen.travel.data.entity.Roles;
import com.jzeen.travel.data.repository.MemberRepository;
import com.jzeen.travel.data.repository.MenuRepository;
import com.jzeen.travel.data.repository.RolesRepository;
import com.mysema.query.types.Predicate;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("/member")
public class MemberController
{
    //自动装配
    @Autowired
    MemberRepository _memberRepository;

    @Autowired
    RolesRepository _rolesRepository;

    @Autowired
    MenuRepository _menuRepository;
    StringBuffer s=new StringBuffer();

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/member/index";
    }

    /**
     * 登录初始化
     * 
     * @return 登录页面login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginInit(Model model, HttpServletRequest request)
    {
        //获得页面的returnURL
        String returnUrl = request.getParameter("returnUrl");
        model.addAttribute("returnUrl", returnUrl);

        return "/member/login";
    }

    /**
     * 根据邮箱和密码进行登录
     * 
     * @param member
     *            用户对象
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute Member member, Model model, HttpServletRequest request, HttpSession session)
    {
      
        //获取页面传过来的邮箱和密码
        String email =request.getParameter("email");
        String password= request.getParameter("password");
       
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        
        //得到验证码的Session字符串
        String randCheckCode=(String) session.getAttribute("randCheckCode");
        System.out.println(randCheckCode+"验证码+++++++++");
        //得到页面用户输入的验证码字符串
        String yanzheng=request.getParameter("yanzheng");   
        //进行比较，如果相同，走中间内容，如果不同跳转到登录页面，返回错误信息
        /**
         * 验证码为空-------------------------------------------------------------------------------------
         */
        if(yanzheng=="" || yanzheng == null){
        
        //根据邮箱和密码（加密：工具在core中的CipherUtil）查询用户
        Member memberSession = _memberRepository.findByEmailAndPassword(email, CipherUtil.generatePassword(password));
        //判断用户是否输入正确
        Boolean valid = (memberSession != null);
        if (valid == false)
        {
           s.append("s");
           int length = s.length();
           if(length==3){
               model.addAttribute("visitime", s.length());
               s.delete(0, s.length());
           }
              
         
           
            model.addAttribute("message", "邮箱或密码错误，请重新填写。");
            return "/member/login";
        }
        
        //后台打印用户相关信息
        System.out.println("memberSession信息" + memberSession.getId() + "\t" + memberSession.getEmail() + "\t"
                + memberSession.getPassword() + "\t" + memberSession.getLastName() + memberSession.getLastName());
        //根据用户ID，查询用户菜单
        List<Menu> menuSession=_menuRepository.getByMemberId(memberSession.getId());
       
        List<Menu> menus=new ArrayList<Menu>();
        //根据菜单状态，获得菜单列表，进行存放在Session中
        for (Menu menu : menuSession)
        {
            if(menu.getState()=="启用" || menu.getState().equals("启用")){
                menus.add(menu);
            }
        }
        //存放用户信息到Session中
        WebUtils.setSessionAttribute(request, "menu", menus);
        WebUtils.setSessionAttribute(request, "member", memberSession);

        //接受页面传过来的地址
        String returnUrl = request.getParameter("returnUrl");
        //判断地址并返回地址
        if (StringUtils.isNullOrEmpty(returnUrl))
        {
            returnUrl = "/member/memberinfo";
        }
        try
        {
            //必须得有个人信息页面的权限,要不永远进不去后台
            returnUrl = "/member/memberinfo";
            returnUrl = URLDecoder.decode(returnUrl, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        returnUrl = "redirect:" + returnUrl;
        return returnUrl;
        }
        /**
         * ------------------------------------------------------------------验证码不为空
         */
        //进行比较，如果相同，走中间内容，如果不同跳转到登录页面，返回错误信息
     else if(yanzheng!=""||yanzheng != null){
          
            if(yanzheng.equalsIgnoreCase(randCheckCode)){
               
        //根据邮箱和密码（加密：工具在core中的CipherUtil）查询用户
        Member memberSession = _memberRepository.findByEmailAndPassword(email, CipherUtil.generatePassword(password));
        //判断用户是否输入正确
        Boolean valid = (memberSession != null);
        if (valid == false)
        {
            model.addAttribute("message", "邮箱或密码错误，请重新填写。");
            model.addAttribute("visitime", 3);
            return "/member/login";
        }
        
        //后台打印用户相关信息
        System.out.println("memberSession信息" + memberSession.getId() + "\t" + memberSession.getEmail() + "\t"
                + memberSession.getPassword() + "\t" + memberSession.getLastName() + memberSession.getLastName());
        //根据用户ID，查询用户菜单
        List<Menu> menuSession=_menuRepository.getByMemberId(memberSession.getId());
       
        List<Menu> menus=new ArrayList<Menu>();
        //根据菜单状态，获得菜单列表，进行存放在Session中
        for (Menu menu : menuSession)
        {
            if(menu.getState()=="启用" || menu.getState().equals("启用")){
                menus.add(menu);
            }
        }
        //存放用户信息到Session中
        WebUtils.setSessionAttribute(request, "menu", menus);
        WebUtils.setSessionAttribute(request, "member", memberSession);

        //接受页面传过来的地址
        String returnUrl = request.getParameter("returnUrl");
        //判断地址并返回地址
        if (StringUtils.isNullOrEmpty(returnUrl))
        {
            returnUrl = "/member/memberinfo";
        }
        try
        {
            //必须得有个人信息页面的权限,要不永远进不去后台
            returnUrl = "/member/memberinfo";
            returnUrl = URLDecoder.decode(returnUrl, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        returnUrl = "redirect:" + returnUrl;
        return returnUrl;
        }
            else{
                model.addAttribute("yanzhengmessage", "验证码有误，请重新填写。");
                model.addAttribute("visitime",3);
                return "/member/login";
            }
            
        }
        return null;
        
    }

    /**
     * @return 用户的所有菜单集合
     */
    @ResponseBody
    @RequestMapping(value = "/loginmenu", method = RequestMethod.POST)
    public List<Menu> login(HttpServletRequest request, HttpSession session)
    {
        
        //根据Session中的菜单显示
        List<Menu> menus=(List<Menu>)session.getAttribute("menu");
        String str=menus.toString();
        System.out.println(str);
//         List<Menu> menu=(List<Menu>)
//         request.getSession().getAttribute("menu");
//        List<Menu> menus = new ArrayList<Menu>();
//        Member member = (Member) session.getAttribute("member");
        

        
        
        
//        根据用户ID查询用户菜单（Session中取得用户ID）
//        menus=_menuRepository.getByMemberId(member.getId());
        
        
        
        
//        根据多重循环得到当前用户的菜单，首先根据用户ID查询用户角色，在根据用户角色，查询用户菜单
//        List<Roles> list = _memberRepository.findOne(member.getId()).getRoles();
//        for (int i = 0; i < list.size(); i++)
//        {
//            Roles roles = _rolesRepository.findOne(list.get(i).getId());
//            for (int j = 0; j < roles.getMenus().size(); j++)
//            {
//                Menu menu = _menuRepository.findOne(roles.getMenus().get(j).getId());
//                if (menu.getState().equals("启用"))
//                {
//                    menus.add(menu);
//                }
//            }
//        }
        return menus;
    }

    /**
     * 查询所有用户
     * 
     * @param request
     * @return DataTable
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<Member, Integer> search(HttpServletRequest request)
    {
        // 使用QueryDsl构造查询条件
        String keyword = request.getParameter("search[value]");
        QMember member = QMember.member;

        //根据以下条件查询
        Predicate predicate = member.firstName.containsIgnoreCase(keyword).or(member.lastName.containsIgnoreCase(keyword)).or(member.email.containsIgnoreCase(keyword)).or(member.telephone.containsIgnoreCase(keyword)).or(member.age.like(keyword));
        DataTable<Member, Integer> dataTable = DataTable.fromRequest(request, _memberRepository, predicate);

        return dataTable;
    }

    /**
     * 用于添加用户前初始化
     * 
     * @param member
     *            用户对象
     * @return String
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute Member member)
    {
        return "/member/create";
    }

    /**
     * 新增用户
     * 
     * @param member
     *            用户对象
     * @param bindingResult
     *            验证
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Member member, BindingResult bindingResult,HttpServletRequest request)
    {
        if (bindingResult.hasErrors())
        {
            return "/member/create";
        }
 
        String password=request.getParameter("password");

  
        //对密码进行加密保存(调用travel-core中的CipherUtil工具类)
        member.setPassword(CipherUtil.generatePassword(password));
        member.setCreateTime(new Date());
        _memberRepository.save(member);

        return "redirect:/member";
    }

    // --------------------------------------------------------------------
    /**
     * 根据ID查询所有菜单初始化
     * 
     * @param id
     *            用户ID
     * @param model
     * @return
     */
    @RequestMapping(value = "/rolesmenu/{id}", method = RequestMethod.GET)
    public String rolesmenuInit(@PathVariable int id, Model model)
    {
        Member member = _memberRepository.findOne(id);
        model.addAttribute("member", member);
        //以下代码对已有角色、没有角色、所有角色进行保存
        List<Roles> roles = _memberRepository.findOne(id).getRoles();
        List<Roles> role = _rolesRepository.findAll();
        List<Roles> rol = new ArrayList<Roles>();
        for (Roles roles1 : role)
        {
            if (!roles.contains(roles1))
            {
                rol.add(roles1);
            }
        }
        //没有的角色
        model.addAttribute("rol", rol);
        //已有角色
        model.addAttribute("roles", roles);
        //所有角色
        model.addAttribute("role", role);
        return "/member/rolesmenu";
    }

    /**
     * 功能：给用户分配角色
     * @param member            用户对象
     * @param bindigResult      判断
     * @param request             页面取值
     * @return
     */
    @RequestMapping(value = "/rolesmenu", method = RequestMethod.PUT)
    public String rolesmenu(@Valid Member member, BindingResult bindigResult, HttpServletRequest request)
    {
        //获得页面Id
        Integer id = Integer.parseInt(request.getParameter("id"));
        //根据ID查询用户
        member = _memberRepository.findOne(id);
        //创建一个角色集合
        List<Roles> list = new ArrayList<Roles>();
        //获取页面角色的集合
        String[] roles = request.getParameterValues("roles");
        //如果角色不为空，就把角色放入当前member中
        if (roles != null)
        {
            for (int i = 0; i < roles.length; i++)
            {
                Integer j = Integer.parseInt(roles[i]);
                Roles r = _rolesRepository.findOne(j);
                list.add(r);
            }
            member.setRoles(list);
        }
        else
        {
            //不为空时，就把空角色给member
            member.setRoles(null);
        }
        if (bindigResult.hasErrors())
        {
            //报错返回rolesmenu页面
            return "/member/rolesmenu";
        }
        //保存member对象
        _memberRepository.save(member);
        return "redirect:/member";
    }

    // --------------------------------------------------------------------

    /**
     * 根据ID显示用户信息初始化(修改)
     * 
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        Member member = _memberRepository.findOne(id);
        model.addAttribute("members", member);
        return "/member/update";
    }

    /**
     * 用户信息修改
     * @param member            用户
     * @param bindingResult            验证
     * @return 用户列表主页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid Member member, BindingResult bindingResult, HttpServletRequest request,Model model)
    {
//        if (bindingResult.hasErrors())
//        {
//            return "/member/update";
//        }
        System.out.println(member.getRoles() + "个角色+++++++++++++++=" + member.getId());
        
        //得到原密码
        String pwd=_memberRepository.findOne(member.getId()).getPassword();
        System.out.println("此用户的原密码为："+pwd);
        
        //得到页面的密码
        String password=request.getParameter("password");
        System.out.println("页面的password的值为："+password+"\t"+"原来的密码为："+pwd);
        if(password==pwd || password.equals(pwd)){
            member.setPassword(pwd);
        }else{
            String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
            if(password.matches(regex)){
//            if(pwd.matches(".*?[^a-zA-Z\\d]+.*?") && pwd.matches(".*?[a-z]+.*?") && pwd.matches(".*?[A-Z]+.*?") && pwd.matches(".*?[\\d]+.*?")){
            member.setPassword(CipherUtil.generatePassword(password));
            System.out.println("更改后的密码加密后为："+member.getPassword());                
            }else{              
                model.addAttribute("members", member);
                model.addAttribute("message", "密码不符合要求,必须包含字母和数字");
                return "/member/update";
            }
        }
            
//        member.setPassword(CipherUtil.generatePassword(member.getPassword()));
        //把当前用户的角色保存给当前用户，根据当前用户的ID
        member.setRoles(_memberRepository.findOne(member.getId()).getRoles());
        System.out.println(member.getRoles().size()+"保存后的角色");
        member.setCreateTime(new Date());
        _memberRepository.save(member);

        return "redirect:/member";
    }

    /**
     * 根据用户id删除用户信息
     * @param id            用户ID
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _memberRepository.delete(id);

    }

    /**
     * 根据用户ID查询用户详细信息
     * @param id
     */
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String detailInit(@PathVariable int id, Model model)
    {
        Member member = _memberRepository.findOne(id);
        model.addAttribute("member", member);

        return "/member/details";
    }

    /**
     * 根据用户的SessionID查询用户信息
     */
    @RequestMapping(value = "/memberinfo", method = RequestMethod.GET)
    public String memberinfo(HttpServletRequest request, Model model)
    {
        Member member = (Member) request.getSession().getAttribute("member");
        System.out.println("member的ID是" + member.getId());
        // Integer memberId = 33;// member.getId();
        Member memberinfobyid = _memberRepository.findOne(member.getId());

        model.addAttribute("member", memberinfobyid);
        return "/member/memberinfo";
    }

    
    
    /**
     * 根据用户ID修改用户密码
     */
    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public String updatepasswordInIt(HttpServletRequest request, Model model)
    {
        Member member = (Member) request.getSession().getAttribute("member");
        // Integer memberId = 33;// member.getId();
        Member memberinfobyid = _memberRepository.findOne(member.getId());

        model.addAttribute("members", memberinfobyid);
        return "member/changepassword";
    }

    /**
     * 修改当前用户密码
     * @return
     */
    @RequestMapping(value = "/changepassword", method = RequestMethod.PUT)
    public String changepassword(@Valid Member members, HttpServletRequest request)
    {
        String password = request.getParameter("password");
        Member member = _memberRepository.findOne(members.getId());
        member.setPassword(CipherUtil.generatePassword(password));
//        if (bindingResult.hasErrors())
//        {
//            return "/home/changepassword";
//        }
        // System.out.println(member.getFirstName()+"\t"+member.getLastName()+"\t"+member.getId()+"\t"+member.getRoles().size());
        _memberRepository.save(member);
        return "/home/index";
    }
    
    
    
    
    

    /**
     * 判断页面填写的密码是否与原密码一直
     */
    @ResponseBody
    @RequestMapping(value = "/passwordistrue", method = RequestMethod.POST)
    public Boolean passwordistrue(String oldpassword ,HttpSession session)
    {
        //得到异步得到的原密码并且是加密之后
        String cipherPassword=CipherUtil.generatePassword(oldpassword);
        //获取Session中的Member对象
        Member member = (Member) session.getAttribute("member");
        //判断密码真假
        if (member.getPassword() == cipherPassword || member.getPassword().equals(cipherPassword))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * 退出后台,跳转到登录页面
     */
    @RequestMapping(value="/loginout",method=RequestMethod.GET)
    public String loginout(Model model,HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute("member");
        session.removeAttribute("menu");
        System.out.println(request.getSession().getId());
        return "/member/login";
    }
}
