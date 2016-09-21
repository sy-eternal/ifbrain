package com.jzeen.travel.admin.controller.analysisofexamination;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.Exam;
import com.jzeen.travel.data.entity.IfbrainTask;
import com.jzeen.travel.data.entity.ItemManagement;
import com.jzeen.travel.data.entity.QExam;
import com.jzeen.travel.data.entity.QIfbrainTask;
import com.jzeen.travel.data.entity.Question;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.ExamRepository;
import com.jzeen.travel.data.repository.ExamScoresRepository;
import com.jzeen.travel.data.repository.QuestionRepository;
import com.mysema.query.types.Predicate;

//试卷分析
@Controller
@RequestMapping("/analysisofexamination")
public class AnalysisOfExaminationController {
	@Autowired
	private CourseReponsitory _courseReponsitory;
	@Autowired
	ExamScoresRepository  _examScoresRepository;
	 @Autowired
	 ExamRepository _examRepository;
	 @Autowired
	 QuestionRepository _questionRepository;
	 //试卷查询页面
		@RequestMapping(value = "/searchanalysisofexamination", method = RequestMethod.GET)
	    public String search(HttpServletRequest request, Model model)
	    {
			List<Course> courseList = _courseReponsitory.findAll();
			model.addAttribute("courselist", courseList);
	        return "/searchanalysisofexamination/search";
	     }
	    //试卷查询
		@ResponseBody
	    @RequestMapping(value = "/search", method = RequestMethod.GET)
	    public Iterable<Exam> search(HttpServletRequest request)
	    {
			String courseid=request.getParameter("courseid");
			String unit=request.getParameter("unit");
			QExam exam=QExam.exam;
			 Predicate predicate=exam.unit.eq(unit).and(exam.course.id.eq(Integer.parseInt(courseid)));
	       Iterable<Exam> data = _examRepository.findAll(predicate);
	       return data;
	    }
		//试卷列表
		@RequestMapping(value = "/list", method = RequestMethod.POST)
	    public String list(HttpServletRequest request, Model model)
	    {
			String courseid = request.getParameter("courseid");
			String unit = request.getParameter("unit");
			model.addAttribute("courseid", courseid);
			model.addAttribute("unit", unit);
	        return "/searchanalysisofexamination/list";
	     }
		//试卷分析
		@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	    public String  anylysis(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			Exam exam = _examRepository.findOne(Integer.parseInt(id));
			//做题的总人数
			Integer count = _examScoresRepository.anylysisallsize(exam.getId());
			//每一道做对的人数
			List<Question> questionlist = _questionRepository.questionlist(exam.getId() );
			
			for(int i=0;i<questionlist.size();i++){
				Integer anylysissuresize = _examScoresRepository.anylysissuresize(exam.getId(),questionlist.get(i).getId());
			   /* int percent = (new BigDecimal(anylysissuresize).divide(new BigDecimal(count))).setScale(2,  BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();*/
				//小数点膨胀
				MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN); 
				int percent = (new BigDecimal(anylysissuresize).divide(new BigDecimal(count),mc)).multiply(new BigDecimal(100)).intValue();
				/* a.divide(o,2, BigDecimal.ROUND_DOWN).doubleValue() */ 
				 questionlist.get(i).setRate(percent+"%");
			        questionlist.get(i).setQuestionNameContent(questionlist.get(i).getQuestionNameContent().replace("<p>", "").replace("</p>", ""));
			}
			model.addAttribute("questionlist", questionlist);
			model.addAttribute("exam", exam);
	        return "/searchanalysisofexamination/result";
	     }
}
