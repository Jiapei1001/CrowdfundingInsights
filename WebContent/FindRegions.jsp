<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>Find a Region</title>
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark bg-primary" style="margin-bottom: 40px">
  <a class="navbar-brand" href="/CrowdfundingInsights" style="text-align: center">Crowdfunding<br>Insights</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav ml-auto" style="padding-right: 50px">
      <li class="nav-item">
        <a class="nav-link" href="/CrowdfundingInsights">Home
          <span class="sr-only">(current)</span>
        </a>
      </li>
        
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Loans</a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="findloans">Search Loans</a>
          <a class="dropdown-item" href="finddurations">Search Duration</a>
          <a class="dropdown-item" href="findloanthemes">Search Themes</a>
          <a class="dropdown-item" href="loananalysis">Analyze Funding</a>
        </div>
      </li>
        
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Partners</a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="findpartners">Search Partners</a>
          <a class="dropdown-item" href="partnercreate">Create Partner</a>
          <a class="dropdown-item" href="partnerrecommender">Partner Recommender</a>
<!--           <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Separated link</a> -->
        </div>
      </li>
        
        <li class="nav-item active">
          <a class="nav-link" href="findregions">Regions</a>
        </li>
    </ul>
  </div>
</nav>

    <div class="container theme-showcase" role="main">
	<form action="findregions" method="post">
		 <h4>Search for a Region</h4>
		 <br/>
		<div class="search-bar">
			<label for="region">Region</label>
			<input id="region" type="string" name="region" value="${fn:escapeXml(param.region)}">
			<input type="submit" value="Search" class="btn btn-sm btn-primary" style="margin-right: 10px">
			<!-- <a href="regioncreate"><button type="button" class="btn btn-outline-primary btn-sm">Create Region</button></a> -->
		</div>
		
		
	</form>
	<br/>
	<div class="alert alert-info" role="alert">
	<h6><span id="successMessage"><b>Note: Empty input for Region will render all regions<br><br>${messages.success}</b></span></h6>
	</div>
	<br/>
	<h5>Matching Region</h5>
        <table class="table table-striped">
            <thead><tr>
                <th>Region</th>
                <th>Country</th>
                <th>ISO</th>
                <th>WorldRegion</th>
                <th>MPI</th>
                <th>Latitude</th>
                <th>Longitude</th>
				<!-- <th>Delete Region</th> -->
                <!-- <th>Update Region</th> -->
            </tr></thead>
            <c:forEach items="${regions}" var="region" >
            	<tbody><tr>
                	<td><c:out value="${region.getRegion()}" /></td>
                	<td><c:out value="${region.getCountry()}" /></td>
                	<td><c:out value="${region.getIso()}" /></td>
                	<td><c:out value="${region.getWorldRegion()}" /></td>
                	<td><c:out value="${region.getMpi()}" /></td>
                	<td><c:out value="${region.getLatitude()}" /></td>
                	<td><c:out value="${region.getLongitude()}" /></td>
                	<%-- <td><c:if test="${!messages.hideDeleteUpdate}"><a href="regiondelete?region=<c:out value="${region.getRegion()}"/>">Delete</a></c:if></td> --%>
                	<%-- <td><c:if test="${!messages.hideDeleteUpdate}"><a href="regionupdate?region=<c:out value="${region.getRegion()}"/>">Update</a></c:if></td> --%> 
            	</tr></tbody>
            </c:forEach>
       </table>
       
    </div>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>
