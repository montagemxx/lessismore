<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			table{
				border-collapse: collapse;
				width:100%;
			}
			th,td{
				text-align:left;
				padding: 8px;
			}
			tr:nth-child(even){
				background-color: #f2f2f2
			}
			th{
				background-color: #4CAF50;
				color: white;
			}
		</style>
	</head>
	<body>
		<form action="searchUser" method="POST">
			<input type="text" name="id">
			<input type="submit" value="search">
		</form>
		<h3>Users table</h3>
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>update</th>
				<th>delete</th>
			</tr>
			<c:forEach var ="user" items= '${usersmodel}'>
				<tr>
					<td><c:out value='${user.id}'/></td>
					<td><c:out value='${user.name}'/> </td>
					<td> <c:out value='${user.email}'/> </td>
					<td>
						<form action="update" method="POST" >
							<input type="hidden" name="id" value=<c:out value="${user.id}"/> >
							<input type="submit" value="update" >
						</form>
					</td>
					<td>
						<form action="deleteUser" method="POST" >
							<input type="hidden" name="id" value=<c:out value="${user.id}"/> >
							<input type="submit" value="delete" >
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<input type="button" onclick="location.href='add'" value="add new user">
		
	</body>
</html>