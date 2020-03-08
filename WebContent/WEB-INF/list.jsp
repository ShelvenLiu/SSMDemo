<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.7.2.js"> </script>
<script type="text/javascript">
	$(function(){
		/*给所有a标签加事件  a标签id属性值已delete开头的*/
		$("a[id^=delete]").click(function(){
			var res=window.confirm("你确认删除吗？");
			if(res){
				return ture;
			}
			return false;
		});
	});

</script>
<style type="text/css">
	td{text-align: center;}
	.td2{text-align: right;}
 	.table1{
            border:1px solid #ddd;
            width:900px;            
        }
        thead{
            background-color:lightblue;
        }
</style>

<title>学生列表</title>
</head>
<body>
	<!-- 将get请求转换成delete请求 -->
	<form id="form" action="" method="post">
		<input type="hidden" name="_method" value="delete"/>
	</form>
	
	<h1 align="center">学生列表CRUD</h1>
	<h3 style="margin-left:590px;">欢迎您，${stu.username }</h3>
	<h3 style="margin-left:590px;"><a href="${pageContext.request.contextPath }/addStudent">添加学生</a></h3>
	<table align="center" border="1px" cellpadding="10px" cellspacing="0px">
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>密码</th>
			<th>性别</th>
			<th>爱好</th>
			<th>简介</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pageBeanData.lists }" var="student" varStatus="vs">
			<tr>
				<td>${vs.count } </td>
				<td>${student.username } </td>
				<td>${student.password } </td>
				<td>${student.gender==true?"男":"女"  } </td>
				<td>${student.hobby } </td>
				<td>${student.mark } </td>
				<td>
					<a href="${pageContext.request.contextPath }/prepareInfoForEdit/${student.sid}">编辑</a>
					<a id="delete_${vs.count }" href="${pageContext.request.contextPath }/delStudent?id=${student.sid}">删除</a>
				</td>
			</tr>
		
		</c:forEach>
		<!-- 分页信息 -->
		<tr>
			<td colspan="7">
				<span>第${pageBeanData.curPage }/ ${pageBeanData.totalPage}页</span>&nbsp;&nbsp;
	   			<span>总记录数：${pageBeanData.totalCount }&nbsp;&nbsp;每页显示:${pageBeanData.pageSize}</span>&nbsp;&nbsp;
				<span>
					 <c:if test="${pageBeanData.curPage==1 }">
					 	<a href="javascript:void(0)">[上一页]</a>&nbsp;&nbsp;
					 </c:if>
					 <c:if test="${pageBeanData.curPage!=1 }">
			         	<a href="${pageContext.request.contextPath }/list?curPage=${pageBeanData.curPage-1}">[上一页]</a>&nbsp;&nbsp;
					 </c:if>
					 <c:if test="${pageBeanData.curPage==pageBeanData.totalPage }">
					 	<a href="javascript:void(0)">[下一页]</a>&nbsp;&nbsp;
					 </c:if>
					 <c:if test="${pageBeanData.curPage!=pageBeanData.totalPage }">
			         	<a href="${pageContext.request.contextPath }/list?curPage=${pageBeanData.curPage+1}">[下一页]</a>&nbsp;&nbsp;
					 </c:if>
	   			</span>
			</td>
		</tr>
	</table>
	<h1 align="center">上传文件区域</h1>
	<form align="center" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/upload">
		描述：<input type="text" name="pedc">
		文件：<input type="file" name="photo">
		    <input type="submit" value="提交">
	</form>
	
	<h1 align="center">下载文件区域</h1>
	<h4 align=center><a href="${pageContext.request.contextPath }/download">平凡天使.mp3</a></h4>
	
</body>
</html>