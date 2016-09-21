package com.jzeen.travel.admin.controller.childshoppingmall;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.ChildShoppingmall;
import com.jzeen.travel.data.entity.DemandLevel;
import com.jzeen.travel.data.entity.IfbrainNumber;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.ChildShoppingmallRepository;
import com.jzeen.travel.data.repository.DemandLevelRepository;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.IfbrainNumberReponsitory;
import com.jzeen.travel.data.repository.UserRepository;

@Controller
@RequestMapping("/ifbrainnumber")
public class IfbrainNumberController {
	@Autowired
	private IfbrainIndexReponsitory  _ifbrainIndexReponsitory;
	@Autowired
	private IfbrainNumberReponsitory  _iIfbrainNumberReponsitory;

	@Autowired
	private UserRepository  _userRepository;

	@Autowired
	private ChildRepository  _ChildRepository;
	@Autowired
	private ChildShoppingmallRepository  _ChildShoppingmallRepository;
	@Autowired
	private DemandLevelRepository  _dDemandLevelRepository;
		//信息列表
			@RequestMapping(value = "/list", method = RequestMethod.GET)
		    public String list(HttpServletRequest request, Model model)
		    {
			   return "/childshoppingmall/childifbrainnumberlist";
		    }
		//datatable列表显示
			@ResponseBody
		    @RequestMapping(value = "/search", method = RequestMethod.GET)
		    public List<IfbrainNumber> search()
		    {
		        List<IfbrainNumber> data = _iIfbrainNumberReponsitory.findAll();
		        return data;
		    }
		//新增财脑编号信息页面		
		 @RequestMapping(value = "/create", method = RequestMethod.GET)
		    public String createInit(@ModelAttribute IfbrainNumber ifbrainNumber, Model model,HttpServletRequest request)
		    {
			 List<User> userList = _userRepository.findAll();
				model.addAttribute("userList", userList);
		        return "/childshoppingmall/childifbrainnumbercreate";

		    }
			//新增财脑编号信息实现
		 @RequestMapping(value = "/create", method = RequestMethod.POST)
		    public String create(Model model, HttpServletRequest request) throws ParseException
		    {
		        String brainnumber=request.getParameter("brainnumber");
		        IfbrainNumber ifbrainNumber =new IfbrainNumber();
		        ifbrainNumber.setBrainnumber(brainnumber);
		        ifbrainNumber.setCreateTime(new Date());
		        ifbrainNumber.setChild(_ChildRepository.findById(Integer.parseInt(request.getParameter("ChildName"))));
		        _iIfbrainNumberReponsitory.save(ifbrainNumber);
				return "redirect:/ifbrainnumber/list";       
		    }
		 
		//根据家长Id查询孩子
			@ResponseBody
			@RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
		    public List<Child> findByUserId(HttpServletRequest request, Model model)
		    {
				String id = request.getParameter("id");
				User user = _userRepository.findOne(Integer.parseInt(id));
				List<Child> childList = user.getChild();
				return childList;
		    }
		 //进入修改页面 
		 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
		    public String updateInit(@PathVariable int id, @ModelAttribute IfbrainNumber ifbrainNumber,Model model)
		    {
			    List<User> userList = _userRepository.findAll();
				model.addAttribute("userList", userList);
		        return "/childshoppingmall/childifbrainnumberupdate";
		    }
		 //修改信息实现
		 @RequestMapping(value = "/update", method = RequestMethod.POST)
		    public String update(@Valid IfbrainNumber ifbrainNumber, HttpServletRequest request) throws ParseException
		    {   
			    String brainnumber=request.getParameter("brainnumber");
		        ifbrainNumber.setBrainnumber(brainnumber);
		        ifbrainNumber.setCreateTime(new Date());
		        ifbrainNumber.setChild(_ChildRepository.findById(Integer.parseInt(request.getParameter("ChildName"))));
		        _iIfbrainNumberReponsitory.save(ifbrainNumber);
		        return "redirect:/ifbrainnumber/list";
		    }
		 
		 //已购买清单
		    @RequestMapping(value = "/findpurchasegoods", method = RequestMethod.GET)
		    public String searchfindpurchasegoods(Model model, HttpServletRequest request)
		    {
		    	String child=request.getParameter("childid");
		    	String demandname =request.getParameter("amp;demandname");
		    	DemandLevel demandLevel = _dDemandLevelRepository.findByDemandName(demandname);
		        List<ChildShoppingmall> purchasegoods = _ChildShoppingmallRepository.findByDemandlevelIdAndChildIds(Integer.parseInt(child),demandLevel.getId(),"1",1);
		        model.addAttribute("purchasegoods", purchasegoods);
		        DemandLevel demandnames = _dDemandLevelRepository.findById(demandLevel.getId());
		        model.addAttribute("demandname", demandnames.getDemandName());
		        return "/childshoppingmall/purchasedgoods";
		    }
}
