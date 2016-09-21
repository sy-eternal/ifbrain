package com.jzeen.travel.admin.controller;

import java.util.ArrayList;
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
import com.jzeen.travel.data.entity.Roles;
import com.jzeen.travel.data.repository.MemberRepository;
import com.jzeen.travel.data.repository.MenuRepository;
import com.jzeen.travel.data.repository.RolesRepository;

@Controller
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    MemberRepository _memberRepository;
    
	@Autowired
	RolesRepository _rolesRepository;
	
	@Autowired
	MenuRepository _menuRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "/roles/list";
	}

	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public DataTable<Roles, Integer> search(HttpServletRequest request) {
		DataTable<Roles, Integer> dataTable = DataTable.fromRequest(request,
				_rolesRepository);

		return dataTable;
	}

	/**
	 * 新增角色初始化
	 * @param roles
	 * @return 角色新增页面
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createInit(@ModelAttribute Roles roles) {
		return "/roles/create";
	}

	/**
	 * 新增角色
	 * @param bindingResult 将错误信息返回到页面中
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid Roles roles, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/roles/create";
		}

		roles.setCreateTime(new Date());
		_rolesRepository.save(roles);

		return "redirect:/roles";
	}

	
	/**
	 * 初始化修改页面
	 * @return 角色修改页面
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateInit(@PathVariable int id, Model model) {
		Roles roles = _rolesRepository.findOne(id);
		model.addAttribute("roles", roles);
		return "/roles/update";
	}

	/**
	 * 修改角色信息
	 * @return 返回到角色主页
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@Valid Roles roles, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "/roles/update";
		}
		roles.setMenus(_rolesRepository.findOne(roles.getId()).getMenus());
		roles.setCreateTime(new Date());
		_rolesRepository.save(roles);

		return "redirect:/roles";
	}

	/**
	 * 根据角色ID删除角色，前提是判断用户是否匹配了此角色，如果没匹配才可以删除
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
	    System.out.println(_rolesRepository.findOne(id).getMembers().size());
	    if(_rolesRepository.findOne(id).getMembers()==null || _rolesRepository.findOne(id).getMembers().size()==0){	        
	        _rolesRepository.delete(id);
	    }

	}
	
	
	
	//--------------------------------------------------------------------------------------------------------------
	/**
	 * 菜单授权初始化，显示所有菜单，在已有菜单前面画钩
	 * @param id
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/menus/{id}",method=RequestMethod.GET)
    public String menusInit(@PathVariable int id,Model model){
        //根据菜单ID查询角色
        Roles roles=_rolesRepository.findOne(id);
        //把角色放入模型
        model.addAttribute("roles", roles);
        //根据ID查找角色的菜单集合，放入menu
        List<Menu> menu=_rolesRepository.findOne(id).getMenus();
        //查找所有的菜单集合
        List<Menu> menuall= _menuRepository.findAll();
        //没有的菜单集合
        List<Menu> newmenu=new ArrayList<Menu>();
        for(Menu mm:menuall){
            if(!menu.contains(mm)){
                newmenu.add(mm);
            }
        }
        model.addAttribute("menu", menu);
        model.addAttribute("newmenu", newmenu);
        model.addAttribute("menuall", menuall);
        System.out.println(menu.size()+"当前角色已有的菜单+++++++++++++++");
        System.out.println(menuall.size()+"所有的菜单+？？？？？？？？？？？？？？？");
        System.out.println(newmenu.size()+"没有的角色的菜单================");
        
        return "/roles/menus";
    }
    
    
    /**
     * 
     * @param roles
     * @param bindigResult
     * @param request
     * @return
     */
    @RequestMapping(value = "/menus", method = RequestMethod.PUT)
    public String menus(@Valid Roles roles,BindingResult bindigResult,HttpServletRequest request){
        Integer id=Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        roles=_rolesRepository.findOne(id);
        List<Menu> list=new ArrayList<Menu>();
        String[] menus=request.getParameterValues("menu");
        if(menus!=null){
        for(int i=0;i<menus.length;i++)
        {
            int j=Integer.parseInt(menus[i]);
            Menu m=_menuRepository.findOne(j);
            list.add(m);
        }
        roles.setMenus(list);
        }else{
            roles.setMenus(null);
        }
        if(bindigResult.hasErrors()){
            return "/roles/menus";
        }
        _rolesRepository.save(roles);
        return "redirect:/roles";
    }
    
}
