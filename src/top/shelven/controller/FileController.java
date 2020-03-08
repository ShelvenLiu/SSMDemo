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
	//无论是上传还是下载  本质是将输入流转换成输出流
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public ModelAndView upload(MultipartFile photo,String pedc,HttpServletRequest request) throws IOException {
		//获取application域
		ServletContext application = request.getServletContext();
		//获取upload文件夹的真实路径
		String realPath = application.getRealPath("/upload");
		//判断upload文件夹是否存在
		File file=new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		//形成不同的文件名
		String fileName=UUID.randomUUID().toString().replace("-", "")+photo.getOriginalFilename();
		//获取输出流
		FileOutputStream fos=new FileOutputStream(realPath+"/"+fileName);
		//获取输入流
		InputStream fis = photo.getInputStream();
		IOUtils.copy(fis, fos);
		fos.close();
		fis.close();
		//把路径保存到数据库
		//使用modelandview把图片携带到页面
		ModelAndView mv=new ModelAndView("success");
		mv.addObject("path", fileName);
		return mv;
	}
	
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException{
		//获取application域
		ServletContext application = request.getServletContext();
		//获取音乐路径
		String fileName="平凡天使.mp3";
		String realPath = application.getRealPath("WEB-INF/"+fileName);
		
		//获得输入流
		FileInputStream fis=new FileInputStream(new File(realPath));
		
		//产生返回值
		//fis.available(),输入流的大小
		byte[] body=new byte[fis.available()];
		//使输入流和byte数组产生关联
		fis.read(body);
		
		MultiValueMap<String, String> headers=new HttpHeaders();
		//解决文件名乱码问题
		fileName=new String(fileName.getBytes("gbk"),"iso8859-1");
		//设置响应头不让浏览器解析 当做附件下载
		headers.add("Content-Disposition","attachment;filename="+fileName);
		
		HttpStatus statusCode=HttpStatus.OK;
		
		ResponseEntity<byte[]> result=new ResponseEntity<byte[]>(body, headers, statusCode);
		return result;
	}
}
