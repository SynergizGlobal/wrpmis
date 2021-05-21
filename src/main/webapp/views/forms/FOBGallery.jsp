
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome to PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
  <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">   
<style>
</style>
</head>
<body>

<div align="center">
	<div class="row">
		<div class="col s12 m12">
			<div class="carousel carousel-slider">
				<c:forEach var="fObj" items="${fobGalleryList }">
					<a class="carousel-item" href="javascript:void(0);">
						<img src="/pmis/fob-gallery/${fObj.attachment}" alter="${fObj.attachment}"/>
					</a>
				</c:forEach>
				<div class="row slider-center"><i id="next" class="material-icons">chevron_right</i> <i id="prev" class="material-icons">chevron_left</i></div>
			</div>
   		</div>
   	</div>

</div>

</body>
<script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
<script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
<script type="text/javascript">

$(document).ready(function(){
	$('i#prev').click(function() {
		  $('.carousel').carousel('prev');
	  	//$(this).parent().parent().carousel('prev');
	  });
	
	  $('i#next').click(function() {
	      $('.carousel').carousel('next');
	  	//$(this).parent().parent().carousel('next');
	  });
});
	  
 </script>
</html>