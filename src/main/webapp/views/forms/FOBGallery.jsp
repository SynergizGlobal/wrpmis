
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome to PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
  <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">   
     <link href = "https://fonts.googleapis.com/icon?family=Material+Icons" rel = "stylesheet">
  
<style>
.row.slider-center {
    position: absolute;
    top:50%;
    width:100%;
}
i#next {
      position: absolute;
    right: 10px;
    color: #fff;
    background: #634e4e99;
    font-size: 35px;
    font-weight: 800;
    border-radius: 50px;
    border: 1px solid;
    
}

i#prev {
    position: absolute;
    left: 20px;
      color: #fff;
    background: #634e4e99;
    font-size: 35px;
    font-weight: 800;
    border-radius: 50px;
    border: 1px solid;
}
</style>
</head>
<body>



 <div class="container no-mar" style="width:424px;height:267px;" >
	<div class="row">
		<div class="col s12 m12" style="width:424px;height:267px;">
			<div class="carousel carousel-slider">
				<c:forEach var="fObj" items="${fobGalleryList }" varStatus="index">
				
					<a class="carousel-item" href="javascript:void(0);">
										<span>${fObj.created_date}</span><br>
					
						<img src="<%=CommonConstants2.FOB_GALLERY%>${fObj.fob_id_fk }/${fObj.attachment}" alter="${fObj.attachment}" width="423" height="247"/>
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
<script>


$('.carousel').carousel({
            fullWidth: true,
            indicators: true
        });

 $('i#prev').click(function() {
    $('.carousel').carousel('prev');
});

$('i#next').click(function() {
    $('.carousel').carousel('next');
});


    </script>
</html>