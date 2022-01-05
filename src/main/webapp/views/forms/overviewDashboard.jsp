<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Overview Dashboard - Reports - PMIS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet"	href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<style>
		.p-0{
			padding:0;			
		}
		.mb-0{
			margin-bottom:0;
		}
	</style>
</head>
<body>
	<!-- header included -->
	<%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>
	
	<div class="container">
    <div class="row">
        <div class="col s3">
            <ul class="collapsible">
                <li class="active">
                    <div class="collapsible-header">Logo Goes Here</div>
                    <div class="collapsible-body p-0">
                        <ul class="collapsible mb-0">
                            <li>
                                <div class="collapsible-header"><i class="material-icons">filter_drama</i>First</div>
                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                            </li>
                            <li>
                                <div class="collapsible-header"><i class="material-icons">place</i>Second</div>
                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                            </li>
                            <li>
                                <div class="collapsible-header"><i class="material-icons">whatshot</i>Third</div>
                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>

        </div>
        <div class="col s9">
            <p> content goes here </p>
        </div>
    </div>
</div>

	
	
		<div class="page-loader" style="display: none;">
		<div class="preloader-wrapper big active">
			<div class="spinner-layer spinner-blue-only">
				<div class="circle-clipper left">
					<div class="circle"></div>
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer included -->
<%-- 	<jsp:include page="../layout/footer.jsp"></jsp:include> --%>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
	<script>
		$(document).ready(function(){
		    $('.collapsible').collapsible();
		  });
	</script>
	</body>
	</html>