<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Look Up Film By ID</title>
</head>
<body>

	<form action="callAddActorPage.do" method="GET">
    			<input type="submit" value="Add Actor">
  		</form>
 	<form action="callAddFilmPage.do" method="GET">
    			<input type="submit" value="Add Film">
  		</form>

  <h3>Search Films by ID</h3>
  <form action="getTitle.do" method="GET">
    <input type="text" name="filmId">
    <input type="submit" value="Look Up Film">
  </form>
  <br>
  <h3>Search Films by Keyword</h3>
  <form action="searchTitle.do" method="GET">
    <input type="text" name="search">
    <input type="submit" value="Search Films">
  </form>
  
  <br>
  <h3>Search Actor by Keyword</h3>
  <form action="searchActor.do" method="GET">
    <input type="text" name="searchActor">
    <input type="submit" value="Search for Actor">
  </form>
  
 <c:if test="${alert != null}">
  <p><strong>"${alert}"</strong></p>
 </c:if>
  
  <c:if test="${name != null}">
  <p><strong>"${name}"</strong></p>
 </c:if>
  
  <c:choose>
  <c:when test="${actorList != null}">
  <c:forEach items = "${actorList}" var = "actor">
        
    <ul>
      <li><c:out value="${actor.firstName}" /> <c:out value="${actor.lastName}" />
      <form action="callEditPage.do" method="GET">
    			<input type="submit" value="Edit this Actor" name="${actor.id}">
    			<input type="hidden" value="${actor.id}" name="actorId">
  		</form>
  		<form action="deleteActor.do" method="POST">
    			<input type="submit" value="Delete this Actor">
    			<input type="hidden" value="${actor.id}" name="actorId">
  		</form>
  		</li>


    </ul>

    
    </c:forEach>
 	</c:when>
  </c:choose>
  
  
  
  
  
  <c:choose>
  <c:when test="${filmTitle != null}">
    <h3>${filmTitle.title}</h3>
    <ul>
          <li>Description: ${filmTitle.description}</li>
          <li>Rating: ${filmTitle.rating}</li>
          <li>Film Length (minutes): ${filmTitle.length}</li>
          <li>Release Year: ${filmTitle.releaseYear}</li>
          <li>Rental Duration (days): ${filmTitle.rentalDuration}</li>
          <li>Rental Rate: ${filmTitle.rentalRate}</li>
          <li>Replacement Cost: ${filmTitle.replacementCost}</li>
   </ul>
    <h3>${filmTitle.title} Cast</h3>
    <ul>
         <c:forEach items = "${filmTitle.cast}" var = "actor">
    <li>${actor.firstName} ${actor.lastName}</li>
         </c:forEach>
    </ul>
    
    
     
      <form action="callEditFilmPage.do" method="GET">
    			<input type="submit" value="Edit this Film" name="${filmTitle.id}">
    			<input type="hidden" value="${filmTitle.id}" name="filmId">
  		</form>
  		<form action="deleteFilm.do" method="POST">
    			<input type="submit" value="Delete this Film">
    			<input type="hidden" value="${filmTitle.id}" name="filmId">
  		</form>
  		
    
  </c:when>
  <c:when test="${filmList != null}">
    <c:forEach items = "${filmList}" var = "film">
         <h2><c:out value="${film.title}" /></h2>
    <ul>
          <li>Description: ${film.description}</li>
          <li>Rating: ${film.rating}</li>
          <li>Film Length (minutes): ${film.length}</li>
          <li>Release Year: ${film.releaseYear}</li>
          <li>Rental Duration (days): ${film.rentalDuration}</li>
          <li>Rental Rate: ${film.rentalRate}</li>
          <li>Replacement Cost: ${film.replacementCost}</li>
<%--           <li>Fil${filmTitle.id}</li>
 --%>    </ul>
       <h3>${film.title} Cast</h3>
        <ul>
         <c:forEach items = "${film.cast}" var = "actor">
         <li>${actor.firstName} ${actor.lastName}</li>
         </c:forEach>
       </ul>
      <br>
      
      <form action="callEditFilmPage.do" method="GET">
    			<input type="submit" value="Edit this Film" name="${film.id}">
    			<input type="hidden" value="${film.id}" name="filmId">
  		</form>
  		<form action="deleteFilm.do" method="POST">
    			<input type="submit" value="Delete this Film">
    			<input type="hidden" value="${film.id}" name="filmId">
  		</form>
    </c:forEach>
    
    
  </c:when>
</c:choose>

</body>
</html>