<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Activities Bulk Update - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" />
     <style>
     	.m-3{margin-top: 0.45em;}
     	.h3em{height:3em;}
     	.w-60p::after{
			content: attr(count);
		    position: absolute;
		    font-size: .85rem;
		    color: #000;
		    margin-left:1rem;
		    border-radius: 1rem;
		    padding: 0 0.6rem;
		    background-color: #cce3de;
		    box-shadow: 2px 2px 3px rgba(0,0,0,.1), -1px -1px 1px rgba(0,0,0,.1);
		    line-height: 25px;
   			margin-top: 8px;
		}
     	.MultiFile-title{
     		color: #000 !important;
     	}
     	.MultiFile-list{
     		line-height:30px;
     	}
     	/* hover pop up style */
     	.circle-container {
		   position: absolute;
		    max-width: 26em;
		    max-height: 14em;
		    min-width: 7em;
		    min-width: 13em;
		    /* border-radius: 50%; */
		    padding: 5px;
		    list-style: none;
		    margin: 5em auto 0;
		    display: none;
		    top: -2em;
    		left: 0em;
		    z-index: 10;
		    background-color: #fff;
		    border: 2px solid #ededed;
		    overflow: auto;
		    border-top-right-radius: 30px;
		    border-bottom-right-radius: 30px;
		    border-bottom-left-radius: 30px;
		}
		.circle-container > * {
			 display: inline-block;
			 position: relative;
			 
			 width: 6em;
			 height: 6em;
			
		}
		 .file-field .btn, .file-field .btn-large, .file-field .btn-small{
		 	float:initial;
		 }
		 .btn, .btn-large, .btn-small, .btn-flat{
		 	vertical-align: initial;
		 	text-align: inherit;
		 	
		 }
		 .MultiFile-remove{color:#ff4081 !important;}
		 .circle-container img {
			 display: block;
			 max-width: 100%;
			 border-radius: 50%;
			 transition: 0.15s;
			 height: 5.5em;
		}
		 .circle-container img {
			 filter: grayscale(0);
		}
		.circle-container li:hover ~ .pop-img{opacity: 0.7 !important;}
		.img-remove{display:none;}
		.circle-container li:hover>.img-remove{display:block !important}
		.img-remove{
			color: #000 !important;
		    position: absolute;
			top: -13%;
		    font-size: 26px;
		    left: 0%;
		    font-weight: bolder;
		    z-index: 1;
		    background: rgb(255, 255, 255, 0.5);
		    padding: 32px;
		}
		#btn-fl{z-index: 11;width:12em;}
		#btn-fl:hover .circle-container{display:inline-block !important;}
     	/* float action button */
     	
		 /* .circle-container {
			 position: relative;
			 width: 20em;
			 height: 20em;
			 border-radius: 50%;
			 padding: 0;
			 list-style: none;
			 margin: 5em auto 0;
		   /display:none; 
		    margin-top: -12em;
		    margin-left: -7em;
		    z-index:10;
		    
		}
		
		 .circle-container > * {
			 display: block;
			 position: absolute;
			 top: 50%;
			 left: 50%;
			 margin: -3em;
			 width: 6em;
			 height: 6em;
			
		}
		 .file-field .btn{width: 8em !important;}
		 .circle-container img {
			 display: block;
			 max-width: 100%;
			 border-radius: 50%;
			 transition: 0.15s;
		}
		 .circle-container img:hover {
			 filter: grayscale(0);
		}
		#btn-fl{z-index: 11;}
		#btn-fl:hover ~ .circle-container{display:block !important;}
		  .circle-container > *:nth-of-type(1) {
			 transform: rotate(0deg) translate(1em) rotate(0deg);
		   transition-timing-function: cubic-bezier(0.175, 0.885, 0.32, 1.275);
		}
		 .circle-container > *:nth-of-type(2) {
			 transform: rotate(45deg) translate(1em) rotate(-45deg);
		   transition-duration: .35s;
		}
		 .circle-container > *:nth-of-type(3) {
			 transform: rotate(90deg) translate(1em) rotate(-90deg);
		}
		 .circle-container > *:nth-of-type(4) {
			 transform: rotate(135deg) translate(1em) rotate(-135deg);
		}
		 .circle-container > *:nth-of-type(5) {
			 transform: rotate(180deg) translate(1em) rotate(-180deg);
		}
		 .circle-container > *:nth-of-type(6) {
			 transform: rotate(225deg) translate(1em) rotate(-225deg);
		}
		 .circle-container > *:nth-of-type(7) {
			 transform: rotate(270deg) translate(1em) rotate(-270deg);
		}
		 .circle-container > *:nth-of-type(8) {
			 transform: rotate(315deg) translate(1em) rotate(-315deg);
		}
		 
		.slide-top {
			-webkit-animation: slide-top 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
			        animation: slide-top 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-top {
		  0% {
		    -webkit-transform: translateY(0);
		            transform: translateY(0);
		  }
		  100% {
		    -webkit-transform: translateY(-100px);
		            transform: translateY(-100px);
		  }
		}
		@keyframes slide-top {
		  0% {
		    -webkit-transform: translateY(0);
		            transform: translateY(0);
		  }
		  100% {
		    -webkit-transform: translateY(-100px);
		            transform: translateY(-100px);
		  }
		}
		.slide-tr {
			-webkit-animation: slide-tr 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
			        animation: slide-tr 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-tr {
		  0% {
		    -webkit-transform: translateY(0) translateX(0);
		            transform: translateY(0) translateX(0);
		  }
		  100% {
		    -webkit-transform: translateY(-80px) translateX(80px);
		            transform: translateY(-80px) translateX(80px);
		  }
		}
		@keyframes slide-tr {
		  0% {
		    -webkit-transform: translateY(0) translateX(0);
		            transform: translateY(0) translateX(0);
		  }
		  100% {
		    -webkit-transform: translateY(-80px) translateX(80px);
		            transform: translateY(-80px) translateX(80px);
		  }
		}
		.slide-right {
			-webkit-animation: slide-right 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
			        animation: slide-right 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-right {
		  0% {
		    -webkit-transform: translateX(0);
		            transform: translateX(0);
		  }
		  100% {
		    -webkit-transform: translateX(100px);
		            transform: translateX(100px);
		  }
		}
		@keyframes slide-right {
		  0% {
		    -webkit-transform: translateX(0);
		            transform: translateX(0);
		  }
		  100% {
		    -webkit-transform: translateX(100px);
		            transform: translateX(100px);
		  }
		}
		.slide-br {
			-webkit-animation: slide-br 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
			        animation: slide-br 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-br {
		  0% {
		    -webkit-transform: translateY(0) translateX(0);
		            transform: translateY(0) translateX(0);
		  }
		  100% {
		    -webkit-transform: translateY(80px) translateX(80px);
		            transform: translateY(80px) translateX(80px);
		  }
		}
		@keyframes slide-br {
		  0% {
		    -webkit-transform: translateY(0) translateX(0);
		            transform: translateY(0) translateX(0);
		  }
		  100% {
		    -webkit-transform: translateY(80px) translateX(80px);
		            transform: translateY(80px) translateX(80px);
		  }
		}
		.slide-bottom {
			-webkit-animation: slide-bottom 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
			        animation: slide-bottom 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-bottom {
		  0% {
		    -webkit-transform: translateY(0);
		            transform: translateY(0);
		  }
		  100% {
		    -webkit-transform: translateY(100px);
		            transform: translateY(100px);
		  }
		}
		@keyframes slide-bottom {
		  0% {
		    -webkit-transform: translateY(0);
		            transform: translateY(0);
		  }
		  100% {
		    -webkit-transform: translateY(100px);
		            transform: translateY(100px);
		  }
		}
		.slide-bl {
			-webkit-animation: slide-bl 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
			        animation: slide-bl 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-bl {
		  0% {
		    -webkit-transform: translateY(0) translateX(0);
		            transform: translateY(0) translateX(0);
		  }
		  100% {
		    -webkit-transform: translateY(80px) translateX(-80px);
		            transform: translateY(80px) translateX(-80px);
		  }
		}
		@keyframes slide-bl {
		  0% {
		    -webkit-transform: translateY(0) translateX(0);
		            transform: translateY(0) translateX(0);
		  }
		  100% {
		    -webkit-transform: translateY(80px) translateX(-80px);
		            transform: translateY(80px) translateX(-80px);
		  }
		}
		.slide-left {
			-webkit-animation: slide-left 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
			        animation: slide-left 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-left {
		  0% {
		    -webkit-transform: translateX(0);
		            transform: translateX(0);
		  }
		  100% {
		    -webkit-transform: translateX(-100px);
		            transform: translateX(-100px);
		  }
		}
		@keyframes slide-left {
		  0% {
		    -webkit-transform: translateX(0);
		            transform: translateX(0);
		  }
		  100% {
		    -webkit-transform: translateX(-100px);
		            transform: translateX(-100px);
		  }
		}
		.slide-tl {
			-webkit-animation: slide-tl 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
			        animation: slide-tl 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-tl {
		  0% {
		    -webkit-transform: translateY(0) translateX(0);
		            transform: translateY(0) translateX(0);
		  }
		  100% {
		    -webkit-transform: translateY(-80px) translateX(-80px);
		            transform: translateY(-80px) translateX(-80px);
		  }
		}
		@keyframes slide-tl {
		  0% {
		    -webkit-transform: translateY(0) translateX(0);
		            transform: translateY(0) translateX(0);
		  }
		  100% {
		    -webkit-transform: translateY(-80px) translateX(-80px);
		            transform: translateY(-80px) translateX(-80px);
		  }
		} */
     	
		/* Chrome, Safari, Edge, Opera */
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		
		/* Firefox */
		input[type=number] {
		  -moz-appearance: textfield;
		}
        .hiddendiv.common {
            width: 99vw !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::after {
            background-color: #2E58AD !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::before,
        [type="radio"].with-gap:checked+span::after {
            border: 2px solid #2E58AD !important;
        }
        .primary-text {
            color: #2E58AD;
            font-weight: 500;
        }
        .table-inside{
        	margin-bottom:25px;
        }

        /* dots related styling  */

        /* horizontal line*/

        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            position: relative;
        }

        /* selects toggle class */

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        #dotgroup1 .dotgroup-scroll {
            width: 100%;
            max-width: 100%;
            height: 100px;
            padding-top: 30px;
            overflow-x: auto;
            white-space: nowrap;

        }

        #dotgroup1 .horizontal-line {
            border: 1px solid #777;
            position: relative;
            bottom: -19px;
            height: 8px;
            box-shadow: 0 0 3px inset #555;
        }

        #dotgroup1 .dot-container {
            position: relative;
            display: inline-block;
        }
        .dot-container{
			min-width:55px;
		}		  
		 #component_circles .dot-container:first-of-type a{
            margin-left: -10px;
        }  

        #dotgroup1 .dot-line {
            width: inherit;
            min-width:30px;
            border: 2px solid #777;
            position: absolute;
            top: 14px;
            left: -17px;
            z-index: 0;
        }
        
        #dotgroup1 .dot-container:first-of-type >.dot-line{
        	left:4px;
        }

        #dotgroup1 .dot {
            height: 30px;
            width: 30px;
            z-index: 1;
            background-color: #bbb;
            color: #333;
            border-radius: 50%;
            border: 1px solid #bbb;
            display: inline-block;
            position: relative;
            margin: 0 12px;
        }

        .dot.active {
            box-shadow: 0px 0px 14px 6px #444, 0px 0px 25px 1px #777;
            border: 1px solid black !important;
        }

        .dot.active .project {
            font-weight: bold;
        }

        #dotgroup1 .project::before {
            content: none;
        }

        #dotgroup1 a .project.odd {
            position: relative;
            top: 30px;
            /* left: 4px; */
            font-size: 0.75rem;
        }

        #dotgroup1 a .project.even {
            position: relative;
            bottom: 20px;
            /* left: 4px; */
            font-size: 0.75rem;
        }

        #dotgroup1 .dot.not-started {
            background-color: #fff;
        }

        #dotgroup1 .dot.in-progress {
            background-color: #FFFF00;
        }


        #dotgroup1 .dot.completed {
            background-color: #05a705;
        }


        #dotgroup1 .dot.delayed {
            background-color: #f00;
        }
        
        .page-loader-1 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
        
        .page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
       
		.page-loader-3 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}

		.page-loader-4 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
		.page-loader-5 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
        .error-msg label {
            color: red !important;
        }

        .input-field .searchable_label {
            font-size: .85rem;
        }
        .fixed-width {
            width: 100%;
            overflow: auto;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        thead th input[type="checkbox"]+span:not(.lever):before{
            border: 2px solid #fff;
        }
        thead th input[type="checkbox"]:checked+span:not(.lever):before{
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }
        .mobile_responsible_table>tbody > tr:not(.datepicker-row) >td{
        	height:auto;
        }
            
		 .datepicker~button ,
		 .datepicker-max-today~button{
		    bottom: 1.5rem;
		} 
		
.datepicker-max~button {
    position: absolute;
    right: 15px;
    top: 15px;
    border: 0;
    opacity: 0.7;
    cursor: pointer;
    background-color: transparent;
     bottom: 1.5rem;
}		       
        
    </style>
     <style>
       
        .legends {
            padding: 2px;
        }

        .box,
        .description {
            display: inline-block;
            margin-left: 3px;
            margin-right: 3px;
            vertical-align: middle;
        }

        .box {
            width: 20px;
            height: 20px;
            border-radius:50%;
            background-color: #fff;
            border: 1px solid #ccc;
        }

        .box.not-started {
            background-color: #fff;
        }

        .box.in-progress {
            background-color: #FFFF00;
        }

        .box.completed {
            background-color: #05a705;
        }

        .box.delayed {
            background-color: #f00;
        }
		@media(max-width: 1024px){
			#btn-fl{
				width: 10em;
			}
			.file-field span{
				margin-left: -0.5em;
			}
		}
        @media only screen and (max-width: 820px) {
           .fixed-width .table-inside {
	    		overflow: hidden;
			}
			.mt0{margin-top:0 !important;}
        }
        @media only screen and (max-width: 700px) {
            .legends .col {
                text-align: left;
            }
        }
        @media(max-width: 575px){
        .row .col{margin: 10px auto}
        .m-3{margin-top: 0;}
        }
       
        fieldset.brdr {
        	padding-bottom: 2rem !important;
        	border:1px solid #ccc;
        	margin-bottom:20px;
        }
        fieldset.brdr legend{		    
		    padding: 0 5px;
	    }
        .w65{
        	width:65% !important;
        }
    </style>
</head>
<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
   <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Modify Actuals</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <c:if test="${not empty success}">
				        <div class="center-align m-1 close-message">	
						   ${success}
						</div>
				    </c:if>
				    <c:if test="${not empty error }">
						<div class="center-align m-1 close-message">
						   ${error}
						</div>
				    </c:if>
                    <form action="<%=request.getContextPath() %>/update-modify-actuals-bulk" id="ModifyActualsUpdateForm" name="ModifyActualsUpdateForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                    <div class="container container-no-margin">
                        <div class="row">                          
                                <div class="col m12 s12 offset-m1">

                                            <div class="row" style="padding-top: 4px;">
                                                  Action  <label>
                                                        <input class="with-gap" id="radio_action1" name="radio_action" type="radio"
                                                            value="completedscopeoractual" />
                                                        <span>Completed Scope / Actual Zero by Task Code</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" id="radio_action2" name="radio_action" type="radio"
                                                            value="deleteactivitiesbytaskcode" />
                                                        <span>Delete activities by task code</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" id="radio_action3" name="radio_action" type="radio"
                                                            value="deleteactivitiesbycontract" />
                                                        <span>Delete activities by contract </span>
                                                    </label>
                                            </div>     
                                            <input type="hidden" name="pending" id="pending" value="0">                               
								</div>
						</div>
						<br>
						<div class="row">
				
                         <div class="col m4 s4 input-field" id="contractOption" style="display:none;">
                              <p class="searchable_label">Contract <span class="required">*</span></p>
                              <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" data-placeholder="Select"
                                  onchange="addInQueContract(this.value);getNewActivitiesUpdateTaskCodes();onLoadMethod();">
                                   <option value=""></option> 
                                  <c:forEach var="obj" items="${contractsList }">
                                  	<option name="${obj.work_id_fk }" value="${obj.contract_id }" <c:if test="${obj.contract_id eq activitiesData.contract_id }">selected</c:if>>${obj.contract_short_name}</option>
                                  </c:forEach>
                              </select>
                              <span id="contract_id_fkError" class="error-msg" ></span>
                          </div>						
                       <%--  <div class="col m4 s4 input-field" id="firstOption" style="display:none;">                          
                              <p class="searchable_label">Task Code <span class="required">*</span></p>
                              <select id="p6_task_code" name="p6_task_code" class="searchable validate-dropdown" data-placeholder="Select" onchange="removeErrorMsg();getNewActivitiesUpdateActivitiesList(this.value);">
                                   <option value=""></option> 
                                  <c:forEach var="obj" items="${taskCodesList }">
                                  	<option name="${obj.p6_task_code }" value="${obj.p6_task_code }">${obj.p6_task_code}</option>
                                  </c:forEach> 
                              </select>
                              <span id="p6_task_codeError" class="error-msg" ></span>
                              
						</div>--%>
						<div class="col m4 s4 input-field" id="hdnStructure" style="display:none;">
                                       <p class="searchable_label">Structure <span class="required">*</span></p>
                                      <select id="strip_chart_structure_id_fk" name="strip_chart_structure_id_fk" data-placeholder="Select"
                                           class="searchable validate-dropdown" onchange="ClearComponents();getNewActivitiesUpdateActivitiesList(this.value);onLoadMethod();">
                                           <option value=""></option>
                                       </select>
                                       <span id="strip_chart_structure_id_fkError" class="error-msg" ></span>
					     </div> 
					     
                                         <div class="col m4 s4 input-field" >
                                          <div class="center-align m-3" style="float:right;">
                                                <button type="button" onclick="updateProgress();" id="btn1" class="h3em btn waves-effect waves-light bg-m" >Update</button>
                                       	  </div>
                                        </div>						
					
						</div>
						
						
						
						<br><br>
						
                                <div class="row fixed-width col m12 s12" id="table_show" style= "display:none;">					 <!-- style= "display:none;" -->
                                        <div class="table-inside">
                                            <table class="mdl-data-table mobile_responsible_table" id="table">
                                                <thead>
                                                    <tr>
		                                                <th class="no-sort" style=" text-align: left; vertical-align: bottom;">
		                                                    <p>
		                                                        <label>
		                                                            <input type="checkbox" name="pending_select-all"
		                                                                id="pending_select-all" />
		                                                            <span></span>
		                                                        </label>
		                                                    </p>
		                                                </th>                                                    
                                                        <th>Task Code</th>
                                                        <th style="width: 350px">Activity</th>
                                                      
                                                        <th>Scope</th>
                                                        <th>Completed</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="filerList">
                                                  
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>						
                         <div class="row" id="actionBtns" style="display:none;">
                             <div class="col s12 m12 mt-brdr center-align">
                                 <div class=" m-12">
                                     <button type="button" onclick="return updateProgress();" id="updatebtn" class="btn waves-effect waves-light bg-m"  style="width:100px;">Update</button>
                                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                     <button type="reset" onClick="window.location.reload(); clearFilters();" class="btn waves-effect waves-light bg-s"  style="width:100px;">Reset</button>
                                 </div>
                             </div>
                         </div>	
                         <br><br><br>						
						</div>

                            </form>
                    </div>
                    <!-- form ends  -->
                </div>

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
    
    <div class="page-loader-2" style="display: none;">
	  <div class="preloader-wrapper big active">
	    <div class="spinner-layer spinner-blue-only">
	      <div class="circle-clipper left">
	        <div class="circle"></div>
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 
	
	<div class="page-loader-3" style="display: none;">
	  <div class="preloader-wrapper big active">
	    <div class="spinner-layer spinner-blue-only">
	      <div class="circle-clipper left">
	        <div class="circle"></div>
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 
	
	<div class="page-loader-4" style="display: none;">
	  <div class="preloader-wrapper big active">
	    <div class="spinner-layer spinner-blue-only">
	      <div class="circle-clipper left">
	        <div class="circle"></div>
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 
	<div class="page-loader-5" style="display: none;">
	  <div class="preloader-wrapper big active">
	    <div class="spinner-layer spinner-blue-only">
	      <div class="circle-clipper left">
	        <div class="circle"></div>
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 
    
      <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>    
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
   <!--  <script src="/pmis/resources/js/datepickerDepedency.js"></script> -->
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
    $(document).ready(function () {
   	    $('.modal').modal();   
    
        $(".num").keypress(function() {
            if ($(this).val().length == $(this).attr("maxlength")) {
                return false;
            }
        });
        
        $("#btn1").hide();
        
        $('input[type=radio]').click(function(){
        	
    		document.getElementById("contract_id_fk").value="";
    		document.getElementById("select2-contract_id_fk-container").innerHTML="Select";
    		
    		//document.getElementById("p6_task_code").value="";
    		//document.getElementById("select2-p6_task_code-container").innerHTML="Select";
    		
    		document.getElementById("strip_chart_structure_id_fk").value=""; 
    		document.getElementById("select2-strip_chart_structure_id_fk-container").innerHTML="Select";
    		
    		
        	if($(this).val()=="completedscopeoractual")
       		{
        		$("#actionBtns").show();
        		$("#firstOption").show();
        		$("#hdnStructure").hide();
        		$("#contractOption").show();
        		
        		$("#filerList").html('');
        		
        		$("#pending").val(1);
       		
       		}
        	else if($(this).val()=="deleteactivitiesbytaskcode")
       		{
        		$("#actionBtns").show();
        		$("#firstOption").show();
        		$("#hdnStructure").hide();
        		$("#contractOption").show();
        		
        		$("#filerList").html('');
        		
        		$("#pending").val(2);
       		}
        	else if($(this).val()=="deleteactivitiesbycontract")
       		{
        		$("#actionBtns").show();
        		$("#firstOption").hide();
        		$("#hdnStructure").show();
        		$("#contractOption").show();
        		$("#pending").val(3);
        		$("#filerList").html('');
        		$("#table").show();
        		
       		}        	
        });       
        
    });
    
    $('#pending_select-all').change(function () {
        var _this = this;
        $('input[name="pending_activity_check"]').each(function () {
            if ($(_this).is(':checked')) 
            {
            	$("input:checkbox").each(function () {
            		var id=$(this).attr("id");
            		var isDisabled =$('#'+id).is(':disabled');
            		if(isDisabled == false)
            		{
                		$(this).prop('checked', true);
            		}
                  

            	});
                $('#btn1').removeClass('disabled');
                $('#updatebtn').removeClass('disabled');
                $('.ids').val(1);
            } else 
            {
                $(this).prop('checked', false);
                $('#btn1').addClass('disabled');
                $('#updatebtn').addClass('disabled');
                $('.ids').val(0);
            }
        });
    });
    
    function removeErrorMsg()
    {
    	//document.getElementById("p6_task_codeError").innerHTML="";
    }
    
 
	  function getNewActivitiesUpdateTaskCodes(value) {
      	  $(".page-loader-4").show();
      	  var contract_id_fk = $("#contract_id_fk").val();
      	  
      	document.getElementById("contract_id_fkError").innerHTML="";
      	
      	  var strip_chart_structure_id_fk = value;
      	  
    	 	 if($('#radio_action1').is(':checked') || $('#radio_action2').is(':checked'))
       		 {
    	 		 $(".page-loader-4").hide();
    	 		getNewActivitiesUpdateActivitiesList();
      		  }
    	 	 else
   	 		 {
    	          $("#strip_chart_structure_id_fk option:not(:first)").remove();
    	          
    	          if ($.trim(contract_id_fk) != "") {
    	          	var myParams = { contract_id_fk: contract_id_fk};
    	              $.ajax({
    	                  url: "<%=request.getContextPath()%>/ajax/getContractStructures",
    	                  data: myParams, cache: false,async: false,
    	                  success: function (data) {
    	                  	var id1 = "";
    	                  	var id2 = "";
    	                      if (data.length > 0) {
    	                          $.each(data, function (i, val) {

    		                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '">' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
    	                          });
    	                      }
    	                      $('.searchable').select2();
    	                      $(".page-loader-4").hide();
    	                  }
    	              });
    	          }else{
    	          	$(".page-loader-4").hide();
    	          }   	 		 
   	 		 }
      }

    
    
	 $("[data-length]").each(function(i,val){
     	$('#'+val.id).characterCounter();;
     });
    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }
    
    	var cid = getUrlVars()["contract_id"];
	    if(cid!="")
	    {
	    	$("#contract_id_fk").val(cid);
	    }  
	    

	    	if(sessionStorage.getItem("contract_id_fk")!="")
	    	{
		    	$("#contract_id_fk").val(sessionStorage.getItem("contract_id_fk"));
	    	}
	    
    
    	var monthShortCode=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	    var datePickerSelectAddClass = function () {
	        var self = this;
	        setTimeout(function () {
	            var selector = self.el;
	            if (!selector) {
	                selector = ".datepicker"
	            }
	            $(selector).siblings(".datepicker-modal")
	                .find(".select-dropdown.dropdown-trigger")
	                .each((index, item) => {
	                    var dateDropdownID = $(item).attr("data-target");
	                    var dropdownUL = $('#' + dateDropdownID);
	                    dropdownUL.children("li").on("click", () => {
	                        datePickerSelectAddClass();
	                    });
	                    dropdownUL.addClass("datepicker-dropdown-year-month")
	                });
	        }, 500);
	    };
	    $(document).on('focus', '.datepicker-max', function () {        	 
			var id = $(this).attr('id');
				var dt = this.value.split('-');
			    this.value = "";
			    var options = {
			    	//maxDate: new Date(),
			    	format: 'dd-mmm-yy',
			        autoClose: true,
			        onOpen: datePickerSelectAddClass,
			        showClearBtn: true,
			        onClose: function () {
			            if (!$(this.el).val()) {
			                $(this.el).siblings('label').removeClass('active');
			            }
			        }
			    };
			    if (dt.length > 1) {			    	
			        var year=(dt[2] < 80)?Number(dt[2])+2000:Number(dt[2])+1900;
			        var month=monthShortCode.indexOf(dt[1]);
			        options.setDefaultDate = true,
			        options.defaultDate = new Date(year, month, dt[0])
			    }
			    M.Datepicker.init(this, options);		       
		 });
	    
		 
		 $(document).on('focus', '.datepicker-max-button', function () { 
			 var id = $(this).attr('id').split('_i')[0];
		     $('#'+id+'_icon').click(function () {
		         event.stopPropagation();
		         $('#'+id).focus().click();
		     });
		 }); 	    
	    
	    
	    $(document).on('focus', '.datepicker-max-today', function () {        	 
			var id = $(this).attr('id');
				var dt = this.value.split('-');
			    this.value = "";
			    var options = {
			    	maxDate: new Date(),
			    	format: 'dd-mmm-yy',
			        autoClose: true,
			        onOpen: datePickerSelectAddClass,
			        showClearBtn: true,
			        onClose: function () {
			            if (!$(this.el).val()) {
			                $(this.el).siblings('label').removeClass('active');
			            }
			        }
			    };
			    if (dt.length > 1) {			    	
			        var year=(dt[2] < 80)?Number(dt[2])+2000:Number(dt[2])+1900;
			        var month=monthShortCode.indexOf(dt[1]);
			        options.setDefaultDate = true,
			        options.defaultDate = new Date(year, month, dt[0])
			    }
			    M.Datepicker.init(this, options);		       
		 });
	    
	    
		 $(document).on('focus', '.datepicker-max-today-button', function () { 
			 var id = $(this).attr('id').split('_i')[0];
		     $('#'+id+'_icon').click(function () {
		         event.stopPropagation();
		         $('#'+id).focus().click();
		     });
		 }); 
		 

		 
	    var filtersMap = new Object();
	    var structureVal = "";
	    var glb="";
	    var glbID="";
        $(document).ready(function () {
      
	        
        	
            $('.searchable').select2();
            

       
            
            var filters = window.localStorage.getItem("BulkFilters");
            if($.trim(filters) != '' && $.trim(filters) != null){
          	   var temp = filters.split('^'); 
          	   for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
						  if($.trim(temp2[0]) == 'contract_id_fk'){
    		        	  }    		        	  
    	        	  }
    	        }
             }
            

        
        });

        
        function onLoadMethod(){
	        $(".page-loader").show();
	       
	       
	       	var filters = '';
	       	Object.keys(filtersMap).forEach(function (key) {
	       		//alert(filtersMap[key]);
	       		filters = filters + key +"="+filtersMap[key] + "^";
	       		window.localStorage.setItem("BulkFilters", filters);
	   			});
	        $(".page-loader").hide();
       }
        function addInQueContract(contract_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('contract_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(contract_id_fk) != ''){
            	filtersMap["contract_id_fk"] = contract_id_fk;
	      	}
        }
        

      
        
       
        function clearFilters(){
        	window.localStorage.setItem("BulkFilters",'');
        }
        
        function ClearComponents(){
        	glb="";
        	glbID="";
        }

	

	//geting contracts list    
	function getNewActivitiesUpdateContractsList(work_id_fk) {
		$(".page-loader").show();
	    $("#contract_id_fk option:not(:first)").remove();
	    
	    $("#strip_chart_structure_id_fk option:not(:first)").remove();
		$('.searchable').select2();
		
		
	    if ($.trim(work_id_fk) != "") {
	        var myParams = { work_id_fk: work_id_fk };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getNewActivitiesUpdateContractsList",
	            data: myParams, cache: false,async: false,
	            success: function (data) {
	            	var id1 = "";
                	var id2 = "${activitiesData.contract_id}";                         
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var contract_name = '';
	                        if ($.trim(val.contract_short_name) != '') { contract_name =  $.trim(val.contract_short_name) }
	                        if ($.trim(id2) != '' && val.contract_id == $.trim(id2)) {
	                        	id1 = val.contract_id;
	                            $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '" selected>'  + $.trim(contract_name) + '</option>');
	                        } else {
	                            $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '">' + $.trim(contract_name) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader").hide();

	            }
	        });
	    }else{
	    	$(".page-loader").hide();
	    }
	}
	

    
    
	



	



	 
	
     function getNewActivitiesUpdateActivitiesList(componentId){
    	 $(".page-loader-4").show();
    	 
    	 $("#btn1").show();
    	 $('#btn1').addClass('disabled');
    	 $('#updatebtn').addClass('disabled');
    	 
    	 document.getElementById("contract_id_fkError").innerHTML="";
    	 //document.getElementById("p6_task_codeError").innerHTML="";
    	 document.getElementById("strip_chart_structure_id_fkError").innerHTML="";
    	 
    	 $( ".dot" ).removeClass( "active" );
     	 
    	 $("#table_show").show();
    	 var html = '';
    	 $("#filerList").html('');
    	
    	 var strip_chart_activity_id = $("#strip_chart_activity_id").val();
    	 var contract_id_fk = $("#contract_id_fk").val();
    	 
    	 if ($.trim(contract_id_fk) != "") {
 	        var myParams = {  strip_chart_activity_id: strip_chart_activity_id, contract_id_fk : contract_id_fk };
 	        $.ajax({
 	            url: "<%=request.getContextPath()%>/ajax/getNewActivitiesfiltersList",
 	            data: myParams, cache: false,
 	            success: function (data) { 	            	
 	                if (data.length > 0) {
 	                	$("#filerList").html('');
 	                    $.each(data, function (i, val) {
 	                    	 var num = $('#table tbody tr').length;
 	                    	 html = '<tr id="row'+num+'"><td><p><label><input type="checkbox" class="filled-in"  name="pending_activity_check" class="check" id="pending_activity_check_'+num+'" /><span></span></label></p></td><td>'+$.trim(val.p6_task_code)+'</td><input type="hidden" name="p6_task_codes"  id="p6_task_codes'+num+'"  value="' + $.trim(val.p6_task_code) + '" />'
 	            	 			+'<td data-head="Activity" class="input-field"><div>' + $.trim(val.strip_chart_activity_name) +' ('+$.trim(val.unit_fk)+' )<input type="hidden" name="activity_ids"  id="activity_id'+num+'"  value="' + $.trim(val.activity_id) + '" /></div></td>';
 	            	 			
         	 					var disDisabled="";
 	            	 					if($.trim(val.scope)==$.trim(val.completed)){
            	 							disDisabled="readonly";
            	 						}
 	            	 			
 	            	 			html +='<td data-head="Scope" class="input-field" style="width:200px;"><span>' + Number($.trim(val.scope)) + '</span><span id="scopesError'+num+'" name="scopesError" class=" scopesError" style="color:red"></span>';
 	            	 			
 	            	 			
 	            	 			html +='<input type="hidden" name="totalScopes"  id="totalScopes'+num+'"  value="' + $.trim(val.scope) + '" /></td>'
 	            	 			+'<td data-head="Completed" class="input-field"><span>' + Number($.trim(val.completed)) + '</span>'
 	            	 			+'<input type="hidden" name="ids"  id="ids'+num+'"  value="" class="ids" /></td>'
 	            	 			+'<input type="hidden" name="completedScopes"  id="completedScopes'+num+'"  value="' + $.trim(val.completed) + '" /></td>'
 	            	 			+' </tr>';
 	                    		$("#filerList").append(html);	
 	                    		
 	                    		
 	            	 			
 	                    	 	
 	                    	 	$("#select-all").on('change', function(){
 	                    	 		if( $("#select-all").prop('checked') ){
	                    	 		}else{
	                    	 			 $('#scopesError'+num).html("");
	                    	 			 $('#btn').prop('disabled',true);
	                    	 			 $('#btn1').prop('disabled',true);
	                    	 		}
 	                    	 	});
 	                    	 	
        	                    $('#pending_activity_check_'+num).change(function () {
        	                        var _this = this;
        	                            if ($('#pending_activity_check_'+num).is(':checked')) 
        	                            {
        	                            	$('#pending_activity_check_'+num).prop('checked', true);
        	                                $('#btn').removeClass('disabled');
        	                                $('#updatebtn').removeClass('disabled');
        	                                $('#ids'+num).val(1);
        	                                $('#btn1').removeClass('disabled');
        	                            } 
        	                            else 
        	                            {

        	                            	var t=0;
        	                            	
        	                            	$('input[name="pending_activity_check"]').each(function () {
        	                            		if ($(this).is(':checked')) 
        	                                    {
            	                                    t++;
        	                                    } 
        	                                }); 
        	                            	
												if(t>=1)
												{
	            	                            	$('#pending_activity_check_'+num).prop('checked', false);
	            	                                $('#btn').removeClass('disabled');
	            	                                $('#updatebtn').removeClass('disabled');
	            	                                $('#btn1').removeClass('disabled');
	            	                                $('#ids'+num).val(1);
												}
												else
												{
	            	                                $('#btn').addClass('disabled');
	            	                                $('#updatebtn').addClass('disabled');
	            	                                $('#btn1').addClass('disabled');
	            	                                $('#ids'+num).val(0);
												}
        	                            }
        	                    });           	 	

 	                     });
 	                }
 	                $(".page-loader-4").hide();
 	            }
 	        });
 	    }else{
 	    	$(".page-loader-4").hide();
 	    }
      
    }


  
     
     function updateProgress()
     {
    	 
    	 
   	 	 if($('#radio_action1').is(':checked') || $('#radio_action2').is(':checked'))
   		 {
				if($('#contract_id_fk').val()=="")
				{
					document.getElementById("contract_id_fkError").innerHTML="Please select Contract";
					return false;
				}
				else
				{
					document.getElementById("contract_id_fkError").innerHTML="";
				}
				
   	 		 
				/*if($('#p6_task_code').val()=="")
				{
					document.getElementById("p6_task_codeError").innerHTML="Please enter Task Code";
					return false;
				}
				else
				{
					document.getElementById("p6_task_codeError").innerHTML="";
				}*/
   		 }
   	 	 else
  	 		 {
				if($('#contract_id_fk').val()=="")
				{
					document.getElementById("contract_id_fkError").innerHTML="Please select Contract";
					return false;
				}
				else
				{
					document.getElementById("contract_id_fkError").innerHTML="";
				}
				
   	 		 
				if($('#strip_chart_structure_id_fk').val()=="")
				{
					document.getElementById("strip_chart_structure_id_fkError").innerHTML="Please select Structure";
					return false;
				}
				else
				{
					document.getElementById("strip_chart_structure_id_fkError").innerHTML="";
				}	 		 
  	 		 }
   	 	$("#ModifyActualsUpdateForm").submit();
     }
  
 
     
     $('select').change(function(){
 	    if ($(this).val() != ""){
 	        $(this).valid();
 	    }
 	});
     
     $('input').change(function(){
 	    if ($(this).val() != ""){
 	        $(this).valid();
 	    }
 	});
    </script>
</body>
</html>