<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>update</th>
				<th>delete</th>
			</tr>
				<tr>
					<td> ${usermodel.id}</td>
					<td> ${usermodel.name} </td>
					<td> ${usermodel.email} </td>
					<td>
						<form action="update" method="POST" >
							<input type="hidden" name="id" value="${usermodel.id}" >
							<input type="submit" value="update" >
						</form>
					</td>
					<td>
						<form action="deleteUser" method="POST" >
							<input type="hidden" name="id" value="${usermodel.id}">
							<input type="submit" value="delete" >
						</form>
					</td>
				</tr>
		</table>

		<input type="button" onclick="location.href='add'" value="add new user">
		<input type="button" onclick ="location.href='searchAllUsers'" value="see all users">
	</body>
</html>