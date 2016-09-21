package com.jzeen.travel.admin.controller.studentscorequery;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.ExamSumScores;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.ExamSumScoresRepository;

//查询学生成绩
@Controller
@RequestMapping("/studentscorequery")
public class StudentScoreQueryController {
	@Autowired
	private CourseReponsitory _courseReponsitory;
	@Autowired
	private ExamSumScoresRepository _ExamSumScoresRepository;
		@RequestMapping(value = "/searchstudentscore", method = RequestMethod.GET)
	    public String search(HttpServletRequest request, Model model)
	    {
			List<Course> courseList = _courseReponsitory.findAll();
			model.addAttribute("courselist", courseList);
			if(request.getParameter("courseid")==null&&request.getParameter("amp;unit")==null){
				
				List<ExamSumScores> examSumScores= _ExamSumScoresRepository.findByexamSumScoresasc(courseList.get(0).getId(), "1");
				if(examSumScores.size()>0){
					model.addAttribute("examSumScores", examSumScores);
				}else{
					model.addAttribute("examSumScores", null);
				}
			}else{
				
				List<ExamSumScores> examSumScores= _ExamSumScoresRepository.findByexamSumScoresasc(Integer.parseInt(request.getParameter("courseid")), request.getParameter("amp;unit"));
				if(examSumScores.size()>0){
					model.addAttribute("examSumScores", examSumScores);
				}else{
					model.addAttribute("examSumScores", null);
				}
			}
			
			return "/searchstudentscore/search";
	     }
	       
		/*@RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String index(HttpServletRequest request, Model model)
	    {
			List<Course> courseList = _courseReponsitory.findAll();
			model.addAttribute("courselist", courseList);
			
			List<ExamSumScores> examSumScores= _ExamSumScoresRepository.findByexamSumScoresasc(courseList.get(0).getId(), "1", 1);
			if(examSumScores.size()>0){
				model.addAttribute("examSumScores", examSumScores);
			}else{
				model.addAttribute("examSumScores", null);
			}
			return "/searchstudentscore/search";
	     }*/
	          
}
