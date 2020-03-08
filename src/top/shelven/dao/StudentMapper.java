package top.shelven.dao;

import java.util.HashMap;
import java.util.List;

import top.shelven.bean.Student;

public interface StudentMapper {
	public void register(Student student);
	
	public Student login(String username,String password);
	
	public Student queryUserNameExist(String username);

	public List<Student> getStudentList();

	public void saveStudent(Student student);

	public void delStudent(String sid);

	public Student getStudentById(String sid);

	public void saveStudentForEdit(Student student);

	public int selectTotalCount();

	public List<Student> findPageBeanData(HashMap<String, Object> map);
	
}
