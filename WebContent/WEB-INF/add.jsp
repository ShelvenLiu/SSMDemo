<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>插入学生信息</title>
</head>
<body>
<h1 align="center">添加学生</h1>
<form action="${pageContext.request.contextPath }/saveStudent" method="post" >
	姓名:<input type="text" name="username"/><br><br>
	密码:<input type="password" name="password"/><br><br>
	性别:<input type="radio" name="gender" value="1"/>男
		<input type="radio" name="gender" value="0"/>女<br><br>
	爱好:
		<input type="checkbox" name="hobby" value="唱歌"/>唱歌
		<input type="checkbox" name="hobby" value="跳舞"/>跳舞
		<input type="checkbox" name="hobby" value="摄影"/>摄影<br><br>
	简介:<textarea rows="10" cols="30" name="mark"></textarea><br><br>
		<input type="submit" value="提交"/>
</form>
</body>
</html>