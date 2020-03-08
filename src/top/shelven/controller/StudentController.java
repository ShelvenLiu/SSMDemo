package top.shelven.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.pool.PreparedStatementPool.MethodType;

import top.shelven.bean.PageBean;
import top.shelven.bean.Student;
import top.shelven.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentservice;
	
	@RequestMapping("/register")
	public String register(Student student,ModelMap model) {
		if(student.getUsername()!=null&&student.getPassword()!=null) {
			boolean queryUserNameExist = studentservice.queryUserNameExist(student.getUsername());
			if(queryUserNameExist) {
				model.addAttribute("msg", "用户名已存在，请换一个再试！");
				System.out.println("用户名已存在，请换一个再试！");
				return "error";
			}else {
				student.setSid(UUID.randomUUID().toString());
				studentservice.register(student);
				model.addAttribute("msg", "注册成功!");
				System.out.println("注册成功!");
				return "success";
			}
			
		}else {
			model.addAttribute("msg", "用户名或密码不能为空！");
			System.out.println("用户名或密码不能为空！");
			return "error";
		}
	}
	
	@RequestMapping("/login")
	public String login(Student student,HttpServletRequest request,ModelMap model) {
		if(!StringUtils.isEmpty(student.getUsername())&&!StringUtils.isEmpty(student.getPassword())) {
			Student stu = studentservice.login(student.getUsername(), student.getPassword());
			if(stu!=null) {
				//model.addAttribute("msg", "登录成功！");
				System.out.println("登录成功！");
				HttpSession session = request.getSession();
				session.setAttribute("stu", stu);
				return "redirect:/list";
			}else {
				model.addAttribute("msg", "用户名或密码不正确！");
				System.out.println("用户名或密码不正确！");
				return "error";
			}
			
		}else {
			model.addAttribute("msg", "用户名或密码不能为空！");
			System.out.println("用户名或密码不能为空！");
			return "error";
		}
	}
	
	/*
	 * //查询所有学生
	 * 
	 * @RequestMapping(value="/list",method = RequestMethod.GET) public ModelAndView
	 * getStudentList() { List<Student> studentList=studentservice.getStudentList();
	 * ModelAndView mv=new ModelAndView("list"); mv.addObject("studentList",
	 * studentList); return mv;
	 * 
	 * }
	 */
	
	//查询分页数据
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String saveStudent(@RequestParam(value="curPage",defaultValue = "1",required = false)int curPage,Model model) {
		PageBean<Student> pageBeanData =studentservice.findPageBeanData(curPage);
		model.addAttribute("pageBeanData", pageBeanData);
		return "list";
	}
	
	
	//保存学生 用于增加和编辑
	@RequestMapping(value="/saveStudent",method = RequestMethod.POST)
	public String saveStudent(Student student) {
		studentservice.saveStudent(student);
		return "redirect:/list";
	}
	
	//删除学生
	@RequestMapping(value="/delStudent")
	//记住类型不一致带来的坑  前端传过来的是String
	public String delStudent(String id) {
		System.out.println(id);
		studentservice.delStudent(id);
		return "redirect:/list";
	}
	
	//准备数据回显
	@RequestMapping(value="/prepareInfoForEdit/{sid}",method = RequestMethod.GET)
	public String prepareInfoForEdit(@PathVariable("sid") String sid,Map<String,Object> map) {
		System.out.println(sid);
		Student student=studentservice.getStudentById(sid);
		map.put("student", student);
		return "edit";
	}
	
	//保存编辑后的数据
	@RequestMapping(value="/saveStudentForEdit",method = RequestMethod.PUT)
	public String saveStudentForEdit(Student student) {
		studentservice.saveStudentForEdit(student);
		return "redirect:/list";
	}
}
