<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>View Actor</title>
</head>
<body>

  <h1>Add Actor</h1>
 


<form action="addActor.do" method="POST">
  <p>First Name</p>
    <input type="text" name="firstName">
  <p>Last Name</p>
    <input type="text" name="lastName">
    <input type="hidden" name="id" value=12>
    <input type="submit" value="Add">
    
    <!-- <input type="text" name="search">
    <input type="submit" value="Search Films"> -->
  </form>




</body>
</html>