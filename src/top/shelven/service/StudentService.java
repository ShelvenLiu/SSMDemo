package top.shelven.service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.shelven.bean.PageBean;
import top.shelven.bean.Student;
import top.shelven.dao.StudentMapper;

@Service
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;
	
	public boolean queryUserNameExist(String username) {
		Student queryUserNameExist = studentMapper.queryUserNameExist(username);
		return queryUserNameExist==null?false:true;
	}
	
	public void register(Student student) {
		
		studentMapper.register(student);
	}
	
	public Student login(String username,String password) {
		Student student = studentMapper.login(username, password);
		return student;
	}

	public List<Student> getStudentList() {
		return studentMapper.getStudentList();
	}

	public void saveStudent(Student student) {
		student.setSid(UUID.randomUUID().toString());
		studentMapper.saveStudent(student);
	}

	public void delStudent(String sid) {
		studentMapper.delStudent(sid);
		
	}
	//查询学生用于回显数据
	public Student getStudentById(String sid) {
		return studentMapper.getStudentById(sid);
	}

	public void saveStudentForEdit(Student student) {
		studentMapper.saveStudentForEdit(student);
		
	}

	public PageBean<Student> findPageBeanData(int curPage) {
		PageBean<Student> pageBeanData=new PageBean<Student>();
		//设置当前页private int curPage;
		pageBeanData.setCurPage(curPage);
		//设置每页显示的记录数private int pageSize;
		int pageSize=5;
		pageBeanData.setPageSize(pageSize);
		//设置总记录数private int totalCount;
		int totalCount=studentMapper.selectTotalCount();
		pageBeanData.setTotalCount(totalCount);
		//设置总页数private int totalPage;
		
		double totalPage = Math.ceil((double)totalCount/pageSize);//向上取整，总记录数除以当前每页显示数
		System.out.println(totalPage);
		System.out.println(new Double(totalPage).intValue());
		pageBeanData.setTotalPage(new Double(totalPage).intValue());
		
		//设置每页显示的数据private List<T> lists;
		//创建HashMap封装分页信息  用于limit分页
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("start", (curPage-1)*pageSize);
		map.put("size", pageSize);
		List<Student> studentList=studentMapper.findPageBeanData(map);
		pageBeanData.setLists(studentList);
		
		return pageBeanData;
	}
}
