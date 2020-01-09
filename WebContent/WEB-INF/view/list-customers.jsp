<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Customers</title>
<!-- Reference our style sheet -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			
			<!-- ADD A BUTTON HERE -->
			<input class="add-button" type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;"/>
			
			<!-- Add Search field -->
			<form:form action="search" method="get">Search by Name<input type="text" name="theSearchName">
			<input type="submit" value="search" class="add-button">
				
			</form:form>
			
			<!-- Add all the customers list on a table -->
			<!-- Table headers -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<!-- Looping over to get data into Table data (Customers) -->
				<c:forEach var="tempCustomer" items="${customers}">
				
				<!-- Construct an "UPDATE" link with customer id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<!-- Display the "Update link" on every row -->
						<td>
							<a href="${updateLink}">Update</a>
						|
						<!-- Display DELETE link on every row -->
						
							<a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>
						
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>