<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Access Denied</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
  <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
  <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
  <link rel="stylesheet" href="/wrpmis/resources/css/activity.css">
  <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
  <style type="text/css">
  	.error{color:red;}
  	.timeline_body {
	    display: block;
	    margin: 0 auto;
	    border: none;
	    /*width: 1231px;*/
	    width: 100%;
	    height: 560px;
	}
	.card .card-content {
	    padding: 0px;
	    border-radius: 0 0 2px 2px;
	}
  </style>
</head>

<body>
  <!-- header  -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

  	<div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
	               	 <div class="row">
                         <div class="col s12 m12 input-field">
              				<div class="center">You don't have access.</div>
                         </div>
                     </div>
                </div>
            </div>
        </div>
    </div>


  <!-- footer  -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
  
  <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/wrpmis/resources/js/materialize-v.1.0.min.js" ></script>
  
</body>

</html>