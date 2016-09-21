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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Member;
import com.jzeen.travel.data.entity.Menu;
import com.jzeen.travel.data.entity.QMenu;
import com.jzeen.travel.data.repository.MemberRepository;
import com.jzeen.travel.data.repository.MenuRepository;
import com.jzeen.travel.data.repository.RolesRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/menu")
public class MenuContoller
{
    @Autowired
    MenuRepository _menuRepository;

    @Autowired
    MemberRepository _memberRepository;

    @Autowired
    RolesRepository _rolesRepository;

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<Menu, Integer> search(HttpServletRequest request)
    {
        // 使用QueryDsl构造查询条件
        String keyword = request.getParameter("search[value]");
        QMenu menu = QMenu.menu;
        Predicate predicate = menu.name.containsIgnoreCase(keyword).or(menu.url.containsIgnoreCase(keyword)).or(menu.displayOrder.like(keyword)).or(menu.parentId.like(keyword));
        DataTable<Menu, Integer> dataTable = DataTable.fromRequest(request, _menuRepository, predicate);
        return dataTable;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/menu/index";
    }

    
    /**
     * 根据父节点ID，新增菜单(不能增加父节点，如果要增加父节点，必须要从数据库中修改)
     * @return
     */
    
    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public String createInit(@PathVariable int id, @ModelAttribute Menu menu, Model model)
    {
        model.addAttribute("parentId", id);
        return "/menu/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Menu menu, BindingResult bindingResult,HttpServletRequest request)
    {
        if (bindingResult.hasErrors())
        {
            return "/menu/create";
        }
       Integer parentId=Integer.parseInt(request.getParameter("parentId"));
       String state=request.getParameter("state");
       menu.setParentId(parentId);
        menu.setState(state);
        menu.setCreateTime(new Date());
        _menuRepository.save(menu);

        return "redirect:/menu";
    }

    
    /**
     * 修改初始化，根据ID查出此菜单信息
     * @return 菜单修改页面
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        Menu menu = _menuRepository.findOne(id);
        model.addAttribute("menu", menu);
        return "/menu/update";
    }

    /**
     * 对菜单进行修改
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid Menu menu, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors())
        {

            return "/menu/update";
        }

        menu.setCreateTime(new Date());
        _menuRepository.save(menu);

        return "redirect:/menu";
    }

    
    //菜单删除功能暂时有问题，会连通角色一起删除，但是暂时此功能不用
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _menuRepository.delete(id);
//        _menuRepository.deleteByMenuId(id);
        
    }


    // 查询当前用户有权限的菜单信息
    @ResponseBody
    @RequestMapping(value = "/getByMember", method = RequestMethod.GET)
    public List<Menu> getByMember(HttpServletRequest request)
    {
        Member member = (Member) request.getSession().getAttribute("member");
//        Integer memberId = 33;// member.getId();
        //根据用户ID查询用户菜单信息
        List<Menu> menus = _menuRepository.getByMemberId(member.getId());
        System.out.println(menus);
        return menus;

    }

    /**
     * 用于Ztree返回JSON字符串，根据用户ID显示用户菜单JSON字符串
     * @param request
     * @return 菜单集合字符串
     */
    @ResponseBody
    @RequestMapping(value="/tree",method=RequestMethod.GET)
    public List<Menu> treeInit(HttpServletRequest request){
            Member member = (Member) request.getSession().getAttribute("member");
//            Integer memberId = 33;// member.getId();
            List<Menu> menus = _menuRepository.getByMemberId(member.getId());
            System.out.println(menus);

            return menus;
    }
}
