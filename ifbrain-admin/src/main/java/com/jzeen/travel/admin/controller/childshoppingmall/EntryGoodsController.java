package com.jzeen.travel.admin.controller.childshoppingmall;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.CommodityMall;
import com.jzeen.travel.data.entity.DemandLevel;
import com.jzeen.travel.data.entity.IfbrainNumber;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.MaterialImage;
import com.jzeen.travel.data.entity.ShoppingmallCommodity;
import com.jzeen.travel.data.entity.ShoppingmallCommodityImage;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.ChildShoppingmallRepository;
import com.jzeen.travel.data.repository.CommodityMallRepository;
import com.jzeen.travel.data.repository.DemandLevelRepository;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.IfbrainNumberReponsitory;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.ShoppingmallCommodityImageRepository;
import com.jzeen.travel.data.repository.ShoppingmallCommodityRepository;
import com.jzeen.travel.data.repository.UserRepository;

@Controller
@RequestMapping("/entrygoods")
public class EntryGoodsController {
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
	@Autowired
	private ShoppingmallCommodityRepository  _sShoppingmallCommodityRepository;
	@Autowired
    FileUploadSetting _fileUploadSetting;
	@Autowired
	ImageRepository  _imageRepository;
	@Autowired
	ShoppingmallCommodityImageRepository _shoppingmallCommodityImageRepository;
	@Autowired
	private CommodityMallRepository  _CommodityMallRepository;
		//信息列表
			@RequestMapping(value = "/list", method = RequestMethod.GET)
		    public String list(HttpServletRequest request, Model model)
		    {
			   return "/entrygoods/list";
		    }
		//datatable列表显示
			@ResponseBody
		    @RequestMapping(value = "/search", method = RequestMethod.GET)
		    public List<ShoppingmallCommodity> search()
		    {
		        List<ShoppingmallCommodity> data = _sShoppingmallCommodityRepository.findAll();
		        return data;
		    }
			//新增商品信息页面		
			 @RequestMapping(value = "/create", method = RequestMethod.GET)
			    public String createInit(@ModelAttribute ShoppingmallCommodity entrygoods, Model model,HttpServletRequest request)
			    {
			
				List<DemandLevel> demandLevels = _dDemandLevelRepository.findAll();
				List<CommodityMall> commodityMall = _CommodityMallRepository.findAll();
				model.addAttribute("demandLevels", demandLevels);
				model.addAttribute("commodityMall", commodityMall);
			        return "/entrygoods/create";

			    }
			//新增商品信息实现
			 @RequestMapping(value = "/create", method = RequestMethod.POST)
			    public String create(Model model, HttpServletRequest request,@RequestParam("file") MultipartFile[] file) throws ParseException, Exception
			    {
			       String commodityName=request.getParameter("commodityName");
			       String commodityMallid=request.getParameter("commodityMallid");
			       String demandName=request.getParameter("demandName");
			       String price=request.getParameter("price");
			       String commodityQuantity=request.getParameter("commodityQuantity");
			       String description = request.getParameter("description");
			       
			       ShoppingmallCommodity entrygoods =new ShoppingmallCommodity();
			       entrygoods.setDescription(description);
			       entrygoods.setPrice(new BigDecimal(price));
			       entrygoods.setCommodityQuantity(Integer.parseInt(commodityQuantity));
			       entrygoods.setCommodityName(commodityName);
			       entrygoods.setCommodityMall(_CommodityMallRepository.findOne(Integer.parseInt(commodityMallid)));
			       entrygoods.setDemandLevel(_dDemandLevelRepository.findOne(Integer.parseInt(demandName)));
			       entrygoods.setCreateTime(new Date());
			       _sShoppingmallCommodityRepository.save(entrygoods);
			       String  filename="";
			       for(int i = 0;i<file.length;i++){  
			    	   String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getBasicCommodityPath();
			    	   if (!file[i].isEmpty()){
					         try {
								byte[] bytes = file[i].getBytes();
								  File directory = new File(filePath);
								   if (!directory.exists())
								   {
								     directory.mkdirs();
								   }
								      filename=file[i].getOriginalFilename();
								    filePath += filename;
								    String lujing=_fileUploadSetting.getBasicCommodityPath()+filename;
								   /* Image  image=new Image();
								    image.setCreateTime(new Date());
								    image.setFileName(filename);
								    image.setFilePath(lujing);
								    _imageRepository.save(image); */
								    ShoppingmallCommodityImage image=new ShoppingmallCommodityImage();
								    image.setCreateTime(new Date());
								    image.setFileName(filename);
								    image.setFilePath(lujing);
								    image.setShoppingmallCommodity(_sShoppingmallCommodityRepository.findOne(entrygoods.getId()));
								   _shoppingmallCommodityImageRepository .save(image); 
								    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
								    stream.write(bytes);
								    stream.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			          }
			       }
			     
					return "redirect:/entrygoods/list";       
			    }
			 
			//进入修改页面 
			 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
			    public String updateInit(@PathVariable int id, @ModelAttribute IfbrainNumber ifbrainNumber,Model model)
			    {
				 	ShoppingmallCommodity entrygoods = _sShoppingmallCommodityRepository.findOne(id);
			        model.addAttribute("entrygoods",entrygoods);
			        return "/entrygoods/update";
			    }
			 //修改信息实现
			 @RequestMapping(value = "/update", method = RequestMethod.POST)
			    public String update(@Valid ShoppingmallCommodity entrygoods, HttpServletRequest request,@RequestParam("file") MultipartFile [] file) throws ParseException, IOException
			    {   
				 ShoppingmallCommodity shoppingmallCommodity = _sShoppingmallCommodityRepository.findOne(entrygoods.getId());
			       String price=request.getParameter("price");
			       String commodityQuantity=request.getParameter("commodityQuantity");
			       String description = request.getParameter("description");
			       String  filename="";
			        _shoppingmallCommodityImageRepository.deleteByShoppingmallCommodityId(shoppingmallCommodity.getId());
			       for(int i = 0;i<file.length;i++){  
			    	   String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getBasicCommodityPath();
			    	   if (!file[i].isEmpty()){
					         try {
								byte[] bytes = file[i].getBytes();
								  File directory = new File(filePath);
								   if (!directory.exists())
								   {
								     directory.mkdirs();
								   }
								     filename=file[i].getOriginalFilename();
								    
								    filePath += filename;
								    String lujing=_fileUploadSetting.getBasicCommodityPath()+filename;
								    ShoppingmallCommodityImage image=new ShoppingmallCommodityImage();
								    image.setCreateTime(new Date());
								    image.setFileName(filename);
								    image.setFilePath(lujing);
								    image.setShoppingmallCommodity(shoppingmallCommodity);
								   _shoppingmallCommodityImageRepository .save(image); 
								    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
								    stream.write(bytes);
								    stream.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			          }
			       }
			        shoppingmallCommodity.setPrice(new BigDecimal(price));
			        shoppingmallCommodity.setDescription(description);
		            shoppingmallCommodity.setCommodityQuantity(Integer.parseInt(commodityQuantity));
		            shoppingmallCommodity.setCommodityName(entrygoods.getCommodityName());
		            shoppingmallCommodity.setCommodityMall(_CommodityMallRepository.findIdByCommodityType(entrygoods.getCommodityMall().getCommodityType()));
		            shoppingmallCommodity.setDemandLevel(_dDemandLevelRepository.findIdByDemandName(entrygoods.getDemandLevel().getDemandName()));
		            shoppingmallCommodity.setCreateTime(entrygoods.getCreateTime());
				    _sShoppingmallCommodityRepository.save(shoppingmallCommodity);
			       
			       
			       
			       
			       
			       
			       
			       
			       
			       
			       
			       
			       
			       
			       
			       
			       
			     /*  String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getBasicCommodityPath();
				      byte[] bytes = file.getBytes();
			          File directory = new File(filePath);
			           if (!directory.exists())
			           {
			             directory.mkdirs();
			           }
				        String  filename=file.getOriginalFilename();
				        filePath += filename;
				        String lujing=_fileUploadSetting.getBasicCommodityPath()+filename;*/
			       /*if(shoppingmallCommodity.getShoppingmallCommodityImage().size()==0){*/
				       /* ShoppingmallCommodityImage image=new ShoppingmallCommodityImage();
					      image.setCreateTime(new Date());
					      image.setFileName(filename);
					      image.setFilePath(lujing);
					        _shoppingmallCommodityImageRepository.save(image); 
					        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				            stream.write(bytes);
				            stream.close();
				            shoppingmallCommodity.setPrice(new BigDecimal(price));
				         
				            shoppingmallCommodity.setCommodityQuantity(Integer.parseInt(commodityQuantity));
				            shoppingmallCommodity.setCommodityName(entrygoods.getCommodityName());
				            shoppingmallCommodity.setCommodityMall(_CommodityMallRepository.findIdByCommodityType(entrygoods.getCommodityMall().getCommodityType()));
				            shoppingmallCommodity.setDemandLevel(_dDemandLevelRepository.findIdByDemandName(entrygoods.getDemandLevel().getDemandName()));
				            shoppingmallCommodity.setCreateTime(entrygoods.getCreateTime());
						       _sShoppingmallCommodityRepository.save(shoppingmallCommodity);*/
			       /*}*//*else{
			    	   ShoppingmallCommodityImage image = shoppingmallCommodity
			    	    Image  image=shoppingmallCommodity.getImage();
				        image.setCreateTime(new Date());
				        image.setFileName(filename);
				        image.setFilePath(lujing);
				        _imageRepository.save(image); 
				        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			            stream.write(bytes);
			            stream.close();
			            shoppingmallCommodity.setPrice(new BigDecimal(price));
			            shoppingmallCommodity.setImage(image);
			            shoppingmallCommodity.setCommodityQuantity(Integer.parseInt(commodityQuantity));
			            shoppingmallCommodity.setCommodityName(entrygoods.getCommodityName());
			            shoppingmallCommodity.setCommodityMall(_CommodityMallRepository.findIdByCommodityType(entrygoods.getCommodityMall().getCommodityType()));
			            shoppingmallCommodity.setDemandLevel(_dDemandLevelRepository.findIdByDemandName(entrygoods.getDemandLevel().getDemandName()));
			            shoppingmallCommodity.setCreateTime(entrygoods.getCreateTime());
					       _sShoppingmallCommodityRepository.save(shoppingmallCommodity); 
			       }*/
			     
			        return "redirect:/entrygoods/list";
			    }
			 //删除商品信息
			  @ResponseBody
			    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
			    public void delete(HttpServletRequest request)
			    {
			    	String id = request.getParameter("id");
			    	_sShoppingmallCommodityRepository.delete(Integer.parseInt(id));
			    }
}
