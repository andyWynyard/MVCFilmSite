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
<c:if test="${actor != null}">
  <h1>Edit Actor</h1>
 </c:if>
 <c:if test="${actor == null}">
  <h1>Add Actor</h1>
 </c:if>


<form action="editActor.do" method="POST">
  <p>First Name</p>
    <input type="text" name="firstName" value="${actor.firstName}">
  <p>Last Name</p>
    <input type="text" name="lastName" value="${actor.lastName}">
    <input type="hidden" name="id" value="${actor.id}">
    <input type="submit" value="Commit Edits">
  </form>
  
  <form action="addActorToFilm.do" method="POST">
  <p>Add actor to film with ID: <input type="text" name="filmId"></p>
  <input type="hidden" name="actorId" value="${actor.id}">
    <input type="submit" value="Add to Film">
  </form>




</body>
</html>