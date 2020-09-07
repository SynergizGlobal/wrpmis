<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome to PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
   <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
   
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">

   
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    
   
   
    <style>
        .input-field .select2-container--default {
            width: 100% !important;
        }

        /* searchable dropdown styling */
        .select2-container--default .select2-selection--single {
            background-color: lightgrey;
            border: 1px solid #aaa;
            height: 32px;
            /* border-radius: 2px; */
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }

        /* datatable styling starts here*/
        .dataTables_filter label::after {
            position: relative;
            content: '\f002';
            color: #6C587B;
            right: 15px;
            font: normal normal normal 14px/1 FontAwesome;
        }

        .dataTables_filter label {
            color: #fff;
        }

        /* datatable stylings ends here*/
        
        
          h4 {
		    font-size: 2.28rem;
		    line-height: 110%;
		    margin: 0 0 .912rem 0;
		}
		
		.card .card-content {
		    padding: 5px 24px;
		    border-radius: 0 0 2px 2px;
		}
.no-mar{
	margin-bottom:0;
}
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

  <div class="row no-mar">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content" style="height: 660px;">
                    <span class="card-title ">
                        <div class="center-align p-2">
                            <h4> Welcome to MRVC PMIS </h4>
                            <img src="/pmis/resources/images/mrvclogo.png" alt="lgo" style="width: 10%;">
                        </div>
                    </span>
                </div>
            </div>
        </div>
    </div>
    


  <!-- footer included -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
    		
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>

</body>

</html>