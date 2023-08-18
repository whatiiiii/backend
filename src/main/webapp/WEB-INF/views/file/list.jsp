<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../login/login_check_modul.jsp"%>
<% session.setAttribute("forward_url", "file/list.do");%>


<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>Title</title>
</head>
<body>
<center>
			<h3>파일리스트</h3>
			<a href="upload.do">업로드폼</a><br/>

			<h4>(1) 이미지파일(png)만 출력</h4>
			<c:forEach items="${fileUps}"  var="fileUp">
				<c:if test="${fileUp.orgnm.substring(fileUp.orgnm.lastIndexOf('.')) == '.png'}">
					<img src="/file/images/${fileUp.id}" width="150" height="150">
					<p>${fileUp.orgnm}</p>
				</c:if>
			</c:forEach><br/>

			<h4>(2) 일반파일 다운로드</h4>
			<c:forEach items="${fileUps}"  var="fileUp">
				<a href="/file/attach/${fileUp.id}">${fileUp.orgnm}</a><br/>
			</c:forEach>

</center>
</body>
</html>