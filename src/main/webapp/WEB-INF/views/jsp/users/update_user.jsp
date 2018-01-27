<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			input[type=text], input[type=email]{
				box-sizing: border-box;
				border: 2px solid #ccc;
				border-radius: 4px;
				font-size: 16px;
				background-color: white;
				background-position: 10px 10px;
				background-repeat: no-repeat;
				padding: 12px 20px 12px 40px;
			}
			input[type=text],input[type=email]:focus{
				background-color:lightblue;
			}
			input[type=submit]{
				background-color: #4CAF50;
				color: white;
				padding: 12px 20px;
				border: none;
				border-radius: 4px;
				cursor: pointer;
				float: right;
			}
		</style>
	</head>
	<body>
		<form action="updateUser" method="POST">
			ID:read only <input type="text" name="id" value="${id}" readonly>
			Name: <input type="text" name="name" value="${name}" >
			Email: <input type="text" name="email" value="${email}" >
			<input type="submit" value="update"/>
		</form>
	</body>
</html>