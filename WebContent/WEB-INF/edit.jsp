<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑学生信息</title>
</head>
<body>
<h1 align="center">添加学生</h1>
<form action="${pageContext.request.contextPath }/saveStudentForEdit" method="post" >
	<input type="hidden" name="_method" value="put"/>
	<input type="hidden" name="sid" value="${student.sid }"/>
	姓名:<input type="text" name="username" value="${student.username }"/><br><br>
	姓名:<input type="text" name="password" value="${student.password }"/><br><br>
	性别:
	<c:if test="${student.gender==true }">
		<input type="radio" name="gender" value="1" checked="checked"/>男
		<input type="radio" name="gender" value="0"/>女<br><br>
	</c:if>
	<c:if test="${student.gender==false }">
		<input type="radio" name="gender" value="1" />男
		<input type="radio" name="gender" value="0" checked="checked"/>女<br><br>
	</c:if>
	
	爱好:
		<input type="checkbox" name="hobby" value="唱歌" <c:if test="${student.hobby.contains('唱歌') }">checked</c:if>/>唱歌
		<input type="checkbox" name="hobby" value="跳舞" <c:if test="${student.hobby.contains('跳舞') }" >checked</c:if>/>跳舞
		<input type="checkbox" name="hobby" value="摄影" <c:if test="${student.hobby.contains('摄影') }" >checked</c:if>/>摄影<br><br>
	简介:<textarea rows="10" cols="30" name="mark">${student.mark }</textarea><br><br>
		<input type="submit" value="提交"/>
</form>
</body>
</html>