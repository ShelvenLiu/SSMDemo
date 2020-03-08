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
				model.addAttribute("msg", "�û����Ѵ��ڣ��뻻һ�����ԣ�");
				System.out.println("�û����Ѵ��ڣ��뻻һ�����ԣ�");
				return "error";
			}else {
				student.setSid(UUID.randomUUID().toString());
				studentservice.register(student);
				model.addAttribute("msg", "ע��ɹ�!");
				System.out.println("ע��ɹ�!");
				return "success";
			}
			
		}else {
			model.addAttribute("msg", "�û��������벻��Ϊ�գ�");
			System.out.println("�û��������벻��Ϊ�գ�");
			return "error";
		}
	}
	
	@RequestMapping("/login")
	public String login(Student student,HttpServletRequest request,ModelMap model) {
		if(!StringUtils.isEmpty(student.getUsername())&&!StringUtils.isEmpty(student.getPassword())) {
			Student stu = studentservice.login(student.getUsername(), student.getPassword());
			if(stu!=null) {
				//model.addAttribute("msg", "��¼�ɹ���");
				System.out.println("��¼�ɹ���");
				HttpSession session = request.getSession();
				session.setAttribute("stu", stu);
				return "redirect:/list";
			}else {
				model.addAttribute("msg", "�û��������벻��ȷ��");
				System.out.println("�û��������벻��ȷ��");
				return "error";
			}
			
		}else {
			model.addAttribute("msg", "�û��������벻��Ϊ�գ�");
			System.out.println("�û��������벻��Ϊ�գ�");
			return "error";
		}
	}
	
	/*
	 * //��ѯ����ѧ��
	 * 
	 * @RequestMapping(value="/list",method = RequestMethod.GET) public ModelAndView
	 * getStudentList() { List<Student> studentList=studentservice.getStudentList();
	 * ModelAndView mv=new ModelAndView("list"); mv.addObject("studentList",
	 * studentList); return mv;
	 * 
	 * }
	 */
	
	//��ѯ��ҳ����
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String saveStudent(@RequestParam(value="curPage",defaultValue = "1",required = false)int curPage,Model model) {
		PageBean<Student> pageBeanData =studentservice.findPageBeanData(curPage);
		model.addAttribute("pageBeanData", pageBeanData);
		return "list";
	}
	
	
	//����ѧ�� �������Ӻͱ༭
	@RequestMapping(value="/saveStudent",method = RequestMethod.POST)
	public String saveStudent(Student student) {
		studentservice.saveStudent(student);
		return "redirect:/list";
	}
	
	//ɾ��ѧ��
	@RequestMapping(value="/delStudent")
	//��ס���Ͳ�һ�´����Ŀ�  ǰ�˴���������String
	public String delStudent(String id) {
		System.out.println(id);
		studentservice.delStudent(id);
		return "redirect:/list";
	}
	
	//׼�����ݻ���
	@RequestMapping(value="/prepareInfoForEdit/{sid}",method = RequestMethod.GET)
	public String prepareInfoForEdit(@PathVariable("sid") String sid,Map<String,Object> map) {
		System.out.println(sid);
		Student student=studentservice.getStudentById(sid);
		map.put("student", student);
		return "edit";
	}
	
	//����༭�������
	@RequestMapping(value="/saveStudentForEdit",method = RequestMethod.PUT)
	public String saveStudentForEdit(Student student) {
		studentservice.saveStudentForEdit(student);
		return "redirect:/list";
	}
}
