package com.jzeen.travel.website.controller.moviematerial;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.FreeVideo;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.NavigationbarModule;
import com.jzeen.travel.data.entity.Publicmaterial;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.FreeFileRepository;
import com.jzeen.travel.data.repository.FreeVideoRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;


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
	@Autowired 
	private 	NavigationbarModuleRepository _NavigationbarModuleRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/offlinecourse", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
		List<Course> course = _courseReponsitory.findByType("0");
		model.addAttribute("course", course);
		
		    String type =request.getParameter("type");
		   String navigationbartrype =request.getParameter("navigationbartrype");
		   NavigationbarModule navigationBar=_NavigationbarModuleRepository.findByNavigationBarTypeAndType(Integer.parseInt(navigationbartrype), Integer.parseInt(type));
		   	if(navigationBar!=null){
		   		model.addAttribute("material", navigationBar);
		   	}
		      else{
		   		model.addAttribute("material", "");
		   	 }
         return "/moviematerial/offlinecourse";
    }
	
	//点击级别播放列表页面
		@RequestMapping(value = "/showmovie", method = RequestMethod.GET)
	    public String showmovie(HttpServletRequest request, Model model)
	    {
			String courseid =request.getParameter("courseid");
			List<Course> course = _courseReponsitory.findByType("0");
			model.addAttribute("course", course);
			model.addAttribute("courseid", courseid);
			Course coursecode = _courseReponsitory.findByCourseLevelList(Integer.parseInt(courseid),"0");
			model.addAttribute("coursecode", coursecode.getCourseLevel());
			Integer courselevelid = Integer.parseInt(request.getParameter("courseid"));
			List < FreeVideo> freevideoshow = _freeVideoRepository.findByCouseLevel(courselevelid,"0");
			if(freevideoshow.size()>0){
				   model.addAttribute("freevideoshow", freevideoshow.get(0));
			}else{
				   model.addAttribute("freevideoshow", null);
			}
		 
			model.addAttribute("freevideolist", freevideoshow);
	         return "/moviematerial/materialtypelist";
	    }
		
		//点击列表列表页面
		@RequestMapping(value = "/showmovielist", method = RequestMethod.GET)
	    public String showmovielist(HttpServletRequest request, Model model)
	    {
			Integer materialtypeid = Integer.parseInt(request.getParameter("materialtypeid"));
			Integer courselevelid = Integer.parseInt(request.getParameter("courselevelid"));
			Integer coursecodeid = Integer.parseInt(request.getParameter("coursecodeid"));
			Course coursecode = _courseReponsitory.findByCourseLevelList(courselevelid,"0");
			model.addAttribute("coursecode", coursecode.getCourseLevel());
		    List < FreeVideo> freevideoshow = _freeVideoRepository.findByCouseCodeAndCourseId(courselevelid,coursecodeid,materialtypeid);
		    if(freevideoshow.size()>0){
		    	 model.addAttribute("freevideoshow", freevideoshow.get(0));
		    }else{
				   model.addAttribute("freevideoshow", null);
			}
		   
		    List < FreeVideo> freevideoshows = _freeVideoRepository.findByCouseLevel(courselevelid,"0");
		    model.addAttribute("freevideolist", freevideoshows);
	         return "/moviematerial/materialtypelist";
	    }

	

	
	 //播放视频
	@RequestMapping(value = "/play/{id}", method = RequestMethod.GET)
    public void show(@PathVariable Integer id, HttpServletResponse response) throws IOException
    {
    	FreeVideo freeVideo = _freeVideoRepository.findOne(id);
        String filePath = freeVideo.getVideoPath();
        String rootPath = _fileUploadSetting.getRootPath();
        ServletOutputStream stream = response.getOutputStream();// 得到向客户端输出二进制数据的对象
        FileInputStream fis = new FileInputStream(rootPath + filePath); // 以byte流的方式打开文件
        byte data[] = new byte[1000];
        while (fis.read(data) > 0)
        {
            stream.write(data);
        }
        fis.close();
        response.setContentType("video/*"); // 设置返回的文件类型
        stream.write(data); // 输出数据
        stream.close();
    }

}
