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
	//��ѯѧ�����ڻ�������
	public Student getStudentById(String sid) {
		return studentMapper.getStudentById(sid);
	}

	public void saveStudentForEdit(Student student) {
		studentMapper.saveStudentForEdit(student);
		
	}

	public PageBean<Student> findPageBeanData(int curPage) {
		PageBean<Student> pageBeanData=new PageBean<Student>();
		//���õ�ǰҳprivate int curPage;
		pageBeanData.setCurPage(curPage);
		//����ÿҳ��ʾ�ļ�¼��private int pageSize;
		int pageSize=5;
		pageBeanData.setPageSize(pageSize);
		//�����ܼ�¼��private int totalCount;
		int totalCount=studentMapper.selectTotalCount();
		pageBeanData.setTotalCount(totalCount);
		//������ҳ��private int totalPage;
		
		double totalPage = Math.ceil((double)totalCount/pageSize);//����ȡ�����ܼ�¼�����Ե�ǰÿҳ��ʾ��
		System.out.println(totalPage);
		System.out.println(new Double(totalPage).intValue());
		pageBeanData.setTotalPage(new Double(totalPage).intValue());
		
		//����ÿҳ��ʾ������private List<T> lists;
		//����HashMap��װ��ҳ��Ϣ  ����limit��ҳ
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("start", (curPage-1)*pageSize);
		map.put("size", pageSize);
		List<Student> studentList=studentMapper.findPageBeanData(map);
		pageBeanData.setLists(studentList);
		
		return pageBeanData;
	}
}
