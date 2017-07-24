<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Film</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</head>
<body>

  <h1>Edit Film</h1>
 


<form action="editFilm.do" method="POST">
  <p>Title</p>
    <input type="text" name="title" value="${film.title}">
  <p>Description</p>
    <input type="text" name="description" value="${film.description}">
    <p>Release Year</p>
 		<input type="number" min="1850" max="2050" value="${film.releaseYear}" name="releaseYear">
   
    
  <!--   <input type="text" name="releaseYear"> -->
    <p>Language</p>
    <select name="language">
  		<option value="1">English</option>
  		<option value="2">Italian</option>
  		<option value="3">Japanese</option>
  		<option value="4">Mandarin</option>
  		<option value="5">French</option>
  		<option value="6">German</option>
	</select>

 
    <p>Rental Duration (Days)</p>
    
      
  		<input type="number" min="1" max="30" value="${film.rentalDuration}" name="rentalDuration">
  		
    <p>Rental Rate ($)</p>
    
    <select name="rentalRate">
  		<option value="0.00">0.00</option>
  		<option value="0.99">0.99</option>
  		<option value="1.98">1.98</option>
  		<option value="1.99">1.99</option>
  		<option value="2.99">2.99</option>
  		<option value="3.98">3.98</option>
  		<option value="3.99">3.99</option>
  		<option value="4.99">4.99</option>
  		<option value="5.98">5.98</option>
  		<option value="5.99">5.99</option>
  		<option value="6.99">6.99</option>
  		<option value="7.98">7.98</option>
  		<option value="7.99">7.99</option>
  		<option value="8.97">8.97</option>
  		<option value="8.99">8.99</option>
  		<option value="9.98">9.98</option>
  		<option value="9.99">9.99</option>
  		<option value="10.99">10.99</option>
  		<option value="11.99">11.99</option>
	</select>
    
    
   
    <p>Length (minutes)</p>
    <input type="number" min="1" max="360" value="${film.length}" name="length">
     <p>Replacement Cost ($)</p>
     
     
     <select name="replacementCost">
  		<option value="9.99">9.99</option>
  		<option value="10.99">10.99</option>
  		<option value="11.99">11.99</option>
  		<option value="12.99">12.99</option>
  		<option value="13.99">13.99</option>
  		<option value="14.99">14.99</option>
  		<option value="15.99">15.99</option>
  		<option value="16.99">16.99</option>
  		<option value="17.99">17.99</option>
  		<option value="17.99">17.99</option>
  		<option value="18.99">18.99</option>
  		<option value="19.99">19.99</option>
  		<option value="20.81">20.81</option>
  		<option value="20.99">20.99</option>
  		<option value="21.99">21.99</option>
  		<option value="22.99">22.99</option>
  		<option value="23.99">23.99</option>
  		<option value="24.99">24.99</option>
  		<option value="25.99">25.99</option>
  		<option value="26.99">26.99</option>
  		<option value="27.99">27.99</option>
  		<option value="28.99">28.99</option>
  		<option value="29.99">29.99</option>
	</select>
     
  
    <p>Rating</p>
    
    <select name="rating">
  		<option value="G">G</option>
  		<option value="PG">PG</option>
  		<option value="PG-13">PG-13</option>
  		<option value="R">R</option>
  		<option value="NC-17">NC-17</option>
  	
	</select>
    
  
    <p>Special Features</p>
		<input type="hidden" name="specialFeatures" value="">
  		<p><input type="checkbox" name="specialFeatures" value="Trailers">Trailers</p>
  		<p><input type="checkbox" name="specialFeatures" value="Commentaries">Commentaries</p>
  		<p><input type="checkbox" name="specialFeatures" value="Deleted Scenes">Deleted Scenes</p>
  		<p><input type="checkbox" name="specialFeatures" value="Behind the Scenes">Behind the Scenes</p>
  	
  		
	
    <input type="hidden" name="id" value="${film.id}">
    <input type="submit" value="Make Changes">
    
    <!-- <input type="text" name="search">
    <input type="submit" value="Search Films"> -->
  </form>




</body>
</html>