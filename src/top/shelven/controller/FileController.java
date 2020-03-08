package top.shelven.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {
	//�������ϴ���������  �����ǽ�������ת���������
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public ModelAndView upload(MultipartFile photo,String pedc,HttpServletRequest request) throws IOException {
		//��ȡapplication��
		ServletContext application = request.getServletContext();
		//��ȡupload�ļ��е���ʵ·��
		String realPath = application.getRealPath("/upload");
		//�ж�upload�ļ����Ƿ����
		File file=new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		//�γɲ�ͬ���ļ���
		String fileName=UUID.randomUUID().toString().replace("-", "")+photo.getOriginalFilename();
		//��ȡ�����
		FileOutputStream fos=new FileOutputStream(realPath+"/"+fileName);
		//��ȡ������
		InputStream fis = photo.getInputStream();
		IOUtils.copy(fis, fos);
		fos.close();
		fis.close();
		//��·�����浽���ݿ�
		//ʹ��modelandview��ͼƬЯ����ҳ��
		ModelAndView mv=new ModelAndView("success");
		mv.addObject("path", fileName);
		return mv;
	}
	
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException{
		//��ȡapplication��
		ServletContext application = request.getServletContext();
		//��ȡ����·��
		String fileName="ƽ����ʹ.mp3";
		String realPath = application.getRealPath("WEB-INF/"+fileName);
		
		//���������
		FileInputStream fis=new FileInputStream(new File(realPath));
		
		//��������ֵ
		//fis.available(),�������Ĵ�С
		byte[] body=new byte[fis.available()];
		//ʹ��������byte�����������
		fis.read(body);
		
		MultiValueMap<String, String> headers=new HttpHeaders();
		//����ļ�����������
		fileName=new String(fileName.getBytes("gbk"),"iso8859-1");
		//������Ӧͷ������������� ������������
		headers.add("Content-Disposition","attachment;filename="+fileName);
		
		HttpStatus statusCode=HttpStatus.OK;
		
		ResponseEntity<byte[]> result=new ResponseEntity<byte[]>(body, headers, statusCode);
		return result;
	}
}
