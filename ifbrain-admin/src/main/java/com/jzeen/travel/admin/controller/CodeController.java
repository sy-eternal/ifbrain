package com.jzeen.travel.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.QCode;
import com.jzeen.travel.data.repository.CodeRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/code")
public class CodeController
{
   
    @Autowired
    CodeRepository _codeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/code/index";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<Code, Integer> search(HttpServletRequest request)
    {
     // 使用QueryDsl构造查询条件
        String keyword = request.getParameter("search[value]");        
        QCode qCode = QCode.code;
        //可以根据Code类中的哪列属性进行查找,把想查找的内容放到DataTable中
        Predicate predicate = qCode.type.containsIgnoreCase(keyword).or(qCode.classs.containsIgnoreCase(keyword)).or(qCode.value.like(keyword));
        DataTable<Code, Integer> dataTable = DataTable.fromRequest(request, _codeRepository, predicate);
        return dataTable;
    }

    /**
     * 初始化修改页面
     * @param id  根据地址中取得ID值
     * @param model 模型层
     * @return  返回到update页面
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        //根据Code的ID查找
        Code code = _codeRepository.findOne(id);
        //把code放入模型，命名为"code"
        model.addAttribute("code", code);
        //返回到update页面
        return "/code/update";
    }

    /**
     * @param code 类
     * @return 如果错误，返回到update页面，如果正确完成修改，跳转到code查询主页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid Code code, BindingResult bindingResult)
    {
        //判断是否错误，如果错误，返回到update页面，如果正确继续执行
        if (bindingResult.hasErrors())
        {
            return "/code/update";
        }
        
        //把当前时间赋值给code中的时间属性
        code.setCreateTime(new Date());
        //保存整个code对象
        _codeRepository.save(code);
        //跳转到code的主页
        return "redirect:/code";
    }

    /**
     * code新增初始化
     * @param code 对象
     * @return  返回到create页面
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute Code code)
    {
        //返回到create页面
        return "/code/create";
    }

    /**
     * 新增code
     * @param code  对象
     * @param bindingResult 将错误信息返回到页面中
     * @return  如果错误返回到create页面，如果成功，返回到code首页
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Code code, BindingResult bindingResult)
    {
        //判断是否错误，如果错误，返回
        if (bindingResult.hasErrors())
        {
            return "/code/create";
        }
        //在code的时间属性中插入当前时间
        code.setCreateTime(new Date());
        //保存code对象
        _codeRepository.save(code);

        return "redirect:/code";
    }

    
    /**
     * 根据ID删除
     * @param id URL中的ID
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _codeRepository.delete(id);
    }

    /**
     * 根据类型和值查询代码表单条信息
     * @param type  类型
     * @param value 值
     * @return code主页
     */
    @ResponseBody
    @RequestMapping(value = "/findByTypeAndValue", method = RequestMethod.GET)
    public Code findByTypeAndValue(@RequestParam String type, @RequestParam Integer value)
    {
        Code code = _codeRepository.findByTypeAndValue(type, value);
        return code;
    }
    
    
    /**
     * 增加时，对添加的类型和值进行判断，看是否在数据库中有存在
     * (数据库中只能有一条相应数据，不可以有多条相同数据，如果有多条，会报javax.persistence.NonUniqueResultException异常)
     * @param type  类型
     * @param value 值
     * @return  真或假
     */
    @ResponseBody
    @RequestMapping(value="/valueistrue",method=RequestMethod.POST)
    public Boolean valueistrue(String type,int value){
        //根据类型和值查询
        Code code=_codeRepository.findByTypeAndValue(type, value);
        if(code==null){
            return true;
        }else{
            return false;
        }
      
    }
}
