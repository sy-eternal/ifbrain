package com.jzeen.travel.website.controller;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.*;
import com.jzeen.travel.website.setting.FileUploadSetting;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/visaOrder")
public class VisaOrderController
{
	@Autowired
	private VisaOrderRepository _visaOrderRepository;
	@Autowired
	ApplicationInfoRepository  _applicationInfoRepository;
	@Autowired
	private ImageRepository _imageRepository;
	@Autowired
	private InstructionFileRelateRepository _InstructionFileRelateRepository;
	@Autowired
	private DocumentRepository _documentRepository;
	@Autowired
	private VisaInstructionRepository _vVisaInstructionRepository;
	

	@Autowired
    private FileUploadSetting _fileUploadSetting;
	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model)
	{
		User user =(User)WebUtils.getSessionAttribute(request, "user");
		if(user==null){
			return"redirect:/user/login";
		}
		return "/visaorder/visaOrder";
	}
	
	/**
	 * 我的签证订单
	 */
	/*@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public DataTable<VisaOrder, Integer> search(HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("user");
		String keyword = request.getParameter("search[value]");
		QVisaOrder visaOrder=QVisaOrder.visaOrder;
		Predicate predicate=visaOrder.orderNumber.containsIgnoreCase(keyword).and(visaOrder.user.id.eq(user.getId()));
		DataTable<VisaOrder, Integer> dataTable = DataTable.fromRequest(request, _visaOrderRepository,predicate);
		return dataTable;
	}*/
	
	/**
	 * 签证代办订单详情
	 * 
	 */
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id,HttpServletRequest request,Model model)
	{
        VisaOrder visaOrder = _visaOrderRepository.findById(id);
        model.addAttribute("visaOrder",visaOrder);
        List<ApplicationInfo> applicationInfos = _applicationInfoRepository.findByVisaOrder(visaOrder);
        if(applicationInfos!=null){
        	 String names="";
             for (int i = 0; i < applicationInfos.size(); i++) {
     			names+=applicationInfos.get(i).getName()+",";
     		}
             model.addAttribute("names",names);
        }else{
        	 model.addAttribute("names","");
        }
       
		return "/visaorder/visaOrderDetail";
	}
	
	/**
	 * 预约签证面试详情
	 */
	@RequestMapping(value = "/{id}/interview", method = RequestMethod.GET)
	public String detailA(@PathVariable("id") Integer id,HttpServletRequest request,Model model)
	{
        VisaOrder visaOrder = _visaOrderRepository.findById(id);
        model.addAttribute("visaOrder",visaOrder);
		return "/visaorder/interview";
	}
	
	/*@RequestMapping(value="/{id}/postAddress",method=RequestMethod.GET)
	public String address(@PathVariable("id") Integer id,HttpServletRequest request,Model model){
		model.addAttribute("visaOrderId",id);
		return "/visaorder/postAddress";
	}*/
	
	/**
	 * 添加护照邮寄地址
	 */
	/*@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postAddress(@RequestParam("visaOrderId") Integer visaOrderId,HttpServletRequest request,Model model)
	{
		VisaOrder visaOrder = _visaOrderRepository.findById(visaOrderId);
		String receiverName = request.getParameter("receiverName");
		String postAddress = request.getParameter("postAddress");
		String teleNumber = request.getParameter("teleNumber");
		visaOrder.setReceiverName(receiverName);
		visaOrder.setPostAddress(postAddress);
		visaOrder.setTeleNumber(teleNumber);
	    _visaOrderRepository.save(visaOrder);
		return "/visaorder/visaOrder";
	}*/
	 /**
	    * 根据签证订单id获得数量 
	    * @param visaorderId
	    * @param model
	    * @return
	    */
	    @RequestMapping(value = "/findheadcount", method = RequestMethod.GET)
	    public String find(@RequestParam Integer visaorderId,Model model)
	    {
	        VisaOrder visaOrder = _visaOrderRepository.findOne(visaorderId);
	        //List<ApplicationInfo> listApplicationInfo = _applicationInfoRepository.findByVisaOrder(visaOrder);
	        
	        List<ApplicationInfo> listApplicationInfo = _applicationInfoRepository.findByOrderId(visaOrder.getId());
	        
	       if(listApplicationInfo.size()>0){
	           model.addAttribute("listApplicationInfo", listApplicationInfo);
	       } 
	        Integer headCount = visaOrder.getHeadCount();
	        model.addAttribute("headCount", headCount);
	        model.addAttribute("orderid", visaorderId);
	        model.addAttribute("visaOrder", visaOrder);
	        return "/visaorder/appmaterials";
	    }
	    
	    
	   /**
	    * 客户上传资料 
	    * @param model
	    * @param request
	    * @param files
	    * @return
	    * @throws Exception
	    */
	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(Model model,HttpServletRequest request,@RequestParam("file") MultipartFile files[]) throws Exception {
			//获得订单
			String orderId = request.getParameter("orderId");
			VisaOrder visaOrder = _visaOrderRepository.findOne(Integer.parseInt(orderId));
			 //材料已提交
            visaOrder.setOrderStatus(2);
            _visaOrderRepository.save(visaOrder);
			//获得提交资料的集合
            List<ApplicationInfo> listApplicationInfo = _applicationInfoRepository.findByVisaOrder(visaOrder);
            
               if(listApplicationInfo.size()>0){
                for(int j=0;j<listApplicationInfo.size();j++){
                    
                    ApplicationInfo applicationInfo = listApplicationInfo.get(j);
                    _documentRepository.delete(applicationInfo.getDocument());
                    
                    //linux
                    String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getVisaPath();
                    //windows
                   // String filePath = _fileUploadSetting.getRootPath() ;
                    File directory = new File(filePath);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    int y = files.length;

//                    for (int i = 0; i < files.length; i++) {
                        // 获得原始文件名
                        String fileName = files[j].getOriginalFilename();
                        String substring = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                        //新的名字
                        String newname = applicationInfo.getName() + "_" +visaOrder.getTaobaoOrderNumber() +substring;

                        System.out.println("原始文件名:" + fileName);
                        System.out.println("分割后的名字" + applicationInfo.getName());
                        System.out.println("新的文件名字:" + newname);


                        if (!files[j].isEmpty()) {
                            try {
                                String newnamea = new String(newname.getBytes("UTF-8"),"iso-8859-1");
                                //linux
                               String path = filePath  + newnamea;
                                //windows
                                //String path = filePath  +File.separator+ newnamea;

                                //保存数据到Document里面
                                Document document=new Document();
                                document.setCreateTime(new Date());
                                document.setFileName(newname);
                               document.setFilePath(path);
                              //  document.setFilePath(path);
                                _documentRepository.save(document);

                               
                                applicationInfo.setCreateTime(new Date());
                                applicationInfo.setDocument(document);
                                _applicationInfoRepository.save(applicationInfo);


                                byte[] bytes = files[j].getBytes();
                                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
                                stream.write(bytes);
                                stream.close();
                                fileName = "";
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    
//                }
                
            }
                return "/visaorder/visaOrder";
 }
		


			String allname = request.getParameter("allname");

			String[] splitname = allname.split(",");
			//windows
          //String filePath = _fileUploadSetting.getRootPath();
//            linux
			String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getVisaPath();

			File directory = new File(filePath);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			int y = files.length;

			for (int i = 0; i < files.length; i++) {
				// 获得原始文件名
				String fileName = files[i].getOriginalFilename();
				String substring = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				//新的名字
				String newname = splitname[i] + "_"+ substring;

				System.out.println("原始文件名:" + fileName);
				System.out.println("分割后的名字" + splitname[i]);
				System.out.println("新的文件名字:" + newname);


				if (!files[i].isEmpty()) {
					try {
					    String newnamea = new String(newname.getBytes("UTF-8"),"iso-8859-1");
					    //linux
						String path = filePath + newnamea;
					    
					    //windows
					   // String path = filePath +File.separator +newnamea;

						//保存数据到Document里面
						Document document=new Document();
						document.setCreateTime(new Date());
						document.setFileName(newname);
						document.setFilePath(path);
						//document.setFilePath(path);
						_documentRepository.save(document);

						ApplicationInfo applicationInfo = new ApplicationInfo();
						applicationInfo.setCreateTime(new Date());
						applicationInfo.setVisaOrder(visaOrder);
						applicationInfo.setName(splitname[i]);
						applicationInfo.setDocument(document);
						_applicationInfoRepository.save(applicationInfo);


						byte[] bytes = files[i].getBytes();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(bytes);
						stream.close();
						fileName = "";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return "/visaorder/visaOrder";
		}
	 
    //点击面试所需带资料
	    @RequestMapping(value = "/instructionFileRelate",method = RequestMethod.GET)
	    public String Imageinterview(HttpServletRequest request,Model model){
	    	 VisaInstruction visainstruction4 = _vVisaInstructionRepository.findById(3);
	         Image imageaInterview = visainstruction4.getImage();
	         model.addAttribute("imageaInterview", imageaInterview);
	    	 return "/visaorder/instructionFileRelate";
	    }
	   

}
