package com.jzeen.travel.admin.controller.moviematerial;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.FreeFile;
import com.jzeen.travel.data.entity.FreeVideo;
import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.IfbrainTask;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.FreeFileRepository;
import com.jzeen.travel.data.repository.FreeVideoRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;


@Controller
@RequestMapping("/moviematerial")
public class MovieMaterialController {
	@Autowired
    FileUploadSetting _fileUploadSetting;
	@Autowired
    private CourseCodeRepository _cCourseCodeRepository;
	@Autowired
	private CourseReponsitory _courseReponsitory;
	@Autowired
	FreeVideoRepository   _freeVideoRepository;
	@Autowired
	FreeFileRepository  _freeFileRepository;
	@Autowired
	MaterialTypeRepository  _materialTypeRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
		List<Course> courseList = _courseReponsitory.findAll();
		List<CourseCode> coursecodeList = _cCourseCodeRepository.findAll();
		List<MaterialType> materialtypeList = _materialTypeRepository.findAll();
		model.addAttribute("materialtypeList", materialtypeList);
		model.addAttribute("courseList", courseList);
		model.addAttribute("coursecodeList", coursecodeList);
         return "/moviematerial/create";
    }
	
	//视频资料信息列表
		@RequestMapping(value = "/list", method = RequestMethod.GET)
	    public String moviematerialist(HttpServletRequest request, Model model)
	    {
			List<FreeVideo> list = _freeVideoRepository.findAll();
			model.addAttribute("list", list);
	        return "/moviematerial/list";
	    }
	//上传视频资料
	  @RequestMapping(value = "/createinfo", method = RequestMethod.POST)
	    public String create(Model model,HttpServletRequest request) throws Exception
	    {
		  //获得课程级别
		  String Course = request.getParameter("Course");
		  Course course = _courseReponsitory.findOne(Integer.parseInt(Course));
		  //获得课程节数
		   String CourseCode = request.getParameter("CourseCode");
		   CourseCode courseCode = _cCourseCodeRepository.findOne(Integer.parseInt(CourseCode));
		   //获得章节
		   String materialtypeid = request.getParameter("materialtype");   
		   MaterialType materialtype = _materialTypeRepository.findOne(Integer.parseInt(materialtypeid));
		   //获得上传视频的路径
		   String filepath = request.getParameter("filepath");
		  /* //获得免费视频中文名
		   String videoCName = request.getParameter("videoCName");*/
		  //String freevidoePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getFreevideoPath();
	        
	      //  String freevidoematerialPath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getFreevideomaterialPath();
	       /* if(videofile.length>0){
				 for(int i = 0;i<videofile.length;i++){  
			       if (!videofile[i].isEmpty())
			       {*/
			           try
			           {
			              /* byte[] bytes = videofile[i].getBytes();
			               File directory = new File(freevidoePath);
			               if (!directory.exists())
			               {
			                   directory.mkdirs();
			               }*/
			              //String  videoname=videofile[i].getOriginalFilename();
			              //freevidoePath += videoname;
			              //String lujing=_fileUploadSetting.getFreevideoPath()+filepath;
			              FreeVideo freevideo=new FreeVideo();
			              //freevideo.setVideoName(filepath);
			              freevideo.setCreateTime(new Date());
			              freevideo.setVideoPath(filepath);
			              freevideo.setCourseLevel(course);
			              freevideo.setCourseCode(courseCode);
			              freevideo.setMaterialType(materialtype);
			              //freevideo.setVideoCName(videoCName);
			              _freeVideoRepository.save(freevideo);
			             /*  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(freevidoePath)));
			               stream.write(bytes);
			               stream.close();*/
			           }
			           catch (Exception e)
			           {
			               e.printStackTrace();
			           }
			      /* }
			       }
			 }*/
	       /* if(materialfile.length>0){
				        File directory1= new File(freevidoematerialPath);
				        if (!directory1.exists())
				        {
				            directory1.mkdirs();
				        }
				       for(int i=0;i<materialfile.length;i++){
				       // 获得原始文件名
				         String fileName = materialfile[i].getOriginalFilename();
				         String substring = fileName.substring(0,fileName.lastIndexOf("."));
				         //新的名字
				         String newname=fileName;
				         if (!materialfile[i].isEmpty()) {
				             try {
				               //String path=freevidoematerialPath+File.separator + newname;
				            	 String path=_fileUploadSetting.getRootPath()+fileName;
				                 FreeFile  freefile=new FreeFile();
						         freefile.setCourseCode(courseCode);
						         freefile.setCourseLevel(course);
						         freefile.setCreateTime(new Date());
						         freefile.setFileName(substring);
						         freefile.setFilePath(path);
						         _freeFileRepository.save(freefile);
				            	 byte[] bytes = materialfile[i].getBytes();
				                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
				                 stream.write(bytes);
				                 stream.close();
				             } catch (Exception e) {
				                 e.printStackTrace();
				             }
				         }
				      }
	        }*/
	        return "redirect:/moviematerial/list";
	    }
	  //下载文件
		    @ResponseBody
		    @RequestMapping(value = "/downloads")
		    public  void download(HttpServletRequest request,HttpServletResponse response) {
		        try  {
		        	String id = request.getParameter("id");
		        	FreeFile freeFile = _freeFileRepository.findOne(Integer.parseInt(id));
		        	String filePath = freeFile.getFilePath();
		            request.setCharacterEncoding("UTF-8");  
		            BufferedInputStream bis = null;  
		            BufferedOutputStream bos = null;  
		            File file  =   new  File(filePath);
		            String filename  =  file.getName();
		            //获取文件的长度
		            long fileLength = file.length();  
		            //设置文件输出类型
		            response.setContentType("application/octet-stream");  
		            response.setHeader("Content-disposition", "attachment; filename="  
		                    + new String(filename.getBytes("utf-8"), "ISO8859-1")); 
		            //设置输出长度
		            response.setHeader("Content-Length", String.valueOf(fileLength));  
		            //获取输入流
		            bis = new BufferedInputStream(new FileInputStream(filePath));  
		            //输出流
		            bos = new BufferedOutputStream(response.getOutputStream());  
		            byte[] buff = new byte[2048];  
		            int bytesRead;  
		            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
		                bos.write(buff, 0, bytesRead);  
		            }  
		            //关闭流
		            bis.close();  
		            bos.close();
		           
		       }catch(IOException ex) {
		           ex.printStackTrace();
		       }
		   }
		
		    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		    public String delete(HttpServletRequest request,@PathVariable int id)
		    {
		    	_freeVideoRepository.delete(id);
		    	return  "redirect:/moviematerial/list";
		    }
		    
		    
		    
		    
		  //提交判断所选是否重复
			@ResponseBody
			@RequestMapping(value = "/findByCouseLevelAndOrdinalNumberAndMaterialType", method = RequestMethod.GET)
		    public boolean findByCouseLevelAndOrdinalNumberAndMaterialType(HttpServletRequest request, Model model)
		    {
				String courseid = request.getParameter("course");
				String CourseCodeid = request.getParameter("amp;CourseCode");
				String materialtypeid=request.getParameter("amp;materialtypeid");
			     List <FreeVideo> checktrue=_freeVideoRepository.findByCouseLevelAndOrdinalNumberAndMaterialType(Integer.parseInt(courseid),Integer.parseInt(CourseCodeid), Integer.parseInt(materialtypeid));
				if(checktrue.size()>0){
					return false;
				}else{
				return true;
				}
		    }
}
