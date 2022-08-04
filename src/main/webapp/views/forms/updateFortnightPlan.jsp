<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fortnightly Plan - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" />
	
    <style>
       	.cf .character-counter{
       		position: relative;
    		right: 1.5em;
       	}
       	.cf2 .character-counter{
       		position: relative;
    		right: 1.5em;
    		top: 4em;
       	}
       	.cf3 .character-counter{
       		position: relative;
    		right: 1.5em;
    		top: 0em;
       	}
        .mdl-data-table{
        	border:1px solid #ccc;	
        }
  		.error-msg label{color:red!important;}   
		
		span.required {
		    font-size: inherit;
		}
        .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }

        .datepicker-table td:first-of-type,
        .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        .datepicker-table th,
        .datepicker-table td {
            padding: 0;
        }

        .radiogroup {
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }

        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
      
        .error-msg label{color:red!important;}
        /*table with fixed header & height start */
		.max-h{
			max-height:400px;
			height:auto;
			overflow:auto;			
		}	
		.max-h tr{
			position:relative;
		}
		.max-h thead th{
			position:sticky;
			top:0;
			z-index:1;
		}
		/*table with fixed header & height ends */
        #userPermissionsTableBody .select2-container{
        	max-width:290px;
        	text-align:left;
        }
        .mdl-data-table {
        	border:1px solid #ccc;
        }
          .input-field .searchable_label{
        	font-size:0.85rem;
        } 
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
		.input-field .searchable_label.mb-8 {
		    margin-bottom: 5px !important;
    		margin-top: -11px !important;
    	}
        .fixed-width {
            width: 100%;
			margin: auto !important;            
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        td{
        	position:relative
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
        .fs-9{
        	font-size:.9rem;
        }
.input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        .fixed-width {
            width: 100%;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

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

        .fw-8p {
            width: 8%;
        }

        #dashboard_form_table td>.btn {
            padding: 0 12px;
        }

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .required {
            color: red;
            font-size: 1.3rem;
            vertical-align: middle;
        }
        .input-field>label{
        	font-size: .9rem !important;
        }
        .w20em{width: 15em;}
        .w60{
            width: 40px;
        }
        .w2em{
        	width: 3em;
        }
        .w1em{
        	width: 1em;
        }
        .w7em{width: 7em;}
        .bd-none{border:none !important;background: transparent}
        .container-xl{
        	margin: 0 auto;
    		max-width: 1280px;
        }
		@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -5.3em;
   					 margin-left: 9%;}
   		.bd-none{border: none;background: transparent}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:17%;}
    	}
    	@media only screen and (min-width: 993px){
    		.container-xl{
    			width: 85%;
    		}
    	}
    	@media only screen and (min-width: 601px){
    		.container-xl{
    			width: 100%;
    		}
    	}
    	@media(max-width: 912px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
    	.per-box-list li {
			    width: 49.5%;
			    margin: 10px 0;
			}
		.mobile_responsible_table>tbody tr td.mobile_btn_close a {
		    float: right;
		    margin-right: -25px;
		    margin-top: 1em;
		}
		.no-bd{
			border: 0 !important;
		}
		.mdl-data-table td, .mdl-data-table th {
		    text-align: center;
		}
		.mobile_responsible_table tbody tr td .select2-container{
			width: 90% !important;
		}
    	}
		 @media only screen and (max-width:820px) {          
			.input-field input[type="email"]{
				box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px #fff !important;
				width: -webkit-fill-available;
			    background-color: transparent;
			    padding-left: 10px;
			}  			
		} 
		 @media only screen and (max-width:601px) {     
			.col.s12.max-h{
				width: 110%;
			}
		}
		
		 .select2-container--default .select2-selection--multiple .select2-selection__choice__display{
			white-space: pre-wrap;
			word-break: break-word;
		}
		.select2-container--default .select2-selection--multiple .select2-selection__choice{
			display: inherit;
		}
		/*.select2-selection__rendered li{
			display: block;
			float: left;
		} */
			#app_com_table {
		counter-reset: serial-number;  /* Set the serial number counter to 0 */
		}
		#app_com_table td:first-child:before {
		counter-increment: serial-number;  /* Increment the serial number counter */
		content: counter(serial-number);  /* Display the counter */
		}
		#app_com_table .datepicker-table td:first-child:before {
		    content: none !important;
		}
		@media(max-width: 575px){
			.per-box-list li {
			    width: 100%;
			    margin: 10px 0;
			}
			.mobile_responsible_table tbody tr td .select2-container{
				width: 100% !important;
			}
		}
		
.col .row {
    margin-left: -.25rem !important;
    margin-right: -.25rem !important;
}		
		
    </style>
</head>
<body>
 <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
                  
       <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h5>Update Fortnightly Plan</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath()%>/update-FortnightPlan" id="getForm" name="getForm" method="post">
                        <div class="container container-no-margin">
                            <div class="row">  
                                <h5 class="center-align" style="margin-bottom: 40px;"></h5>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Work: </p>
                                    <select id="work_id_fk" class="searchable" name="work_id_fk" disabled>
                                        <option value="">Select</option>
                                       	<c:forEach var="obj" items="${FortnightPlanWorkList }">
                                      	   	<option value= "${obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                    	 </c:forEach>                                                                               
                                    </select>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Contract: </p>
                                    <select id="contract_id_fk" class="searchable" name="contract_id_fk" disabled>
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${FortnightPlanContractList }">
                                            <option value= "${obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                        </c:forEach>                                       
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Category: </p>
                                    <select id="category" class="searchable" name="category" disabled>
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${FortnightPlanCategoryList }">
                                            <option value= "${obj.module_name}">${obj.module_name}</option>
                                        </c:forEach>                                        
                                    </select>
                                </div>   
                                
                                
                            </div>
                            <div class="row" style="margin-top: 20px;">
                                 <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Critical Item: </p>
                                    <select id="critical_item" class="searchable" name="critical_item" disabled>
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${FortnightPlanCriticalItemList }">
                                            <option value= "${obj.critical_item}">${obj.critical_item}</option>
                                        </c:forEach>                                       
                                    </select>
                                </div>
                                 <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Period: </p>
                                    <input type="text" id="period" name="period" disabled>
                                </div>  
                            </div>

                            
                            </div>

                            <div class="container-xl container-no-margin">
                             <div class="row exe-box w100" style="margin-top: 20px;">
                                <div class="col s12 m12">
                                    <div class="row fixed-width">
                                        <div class="table-inside">
                                            <table id="app_com_table" class="mdl-data-table mobile_responsible_table auto-index">
                                                <thead>
                                                    <tr>
                                                        <th class="w60">S No </th>
                                                        <th class="w2em">Structure </th>
                                                        <th class="w2em">Component </th>
                                                        <th class="w2em">Cumulative Planned Last Fortnight </th>
                                                        <th class="w2em">Cumulative Actual Last Fortnight </th>
                                                        <th class="w2em">Current Fortnight Planned </th>
                                                        <th class="w1em">Navigation</th>
                                                        <th class="w20em">Remarks</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stBody">
                                                	<c:set var="count" value="1" />
					                                   <c:forEach var="obj" items="${FortnightPlan}">
					           					  			 <tr>
						           					  			 <td></td>
						           					  			 <td><input type="text" name="structure" id="structure" value="${obj.structure}" disabled></td>
						           					  			 <td><input type="text" name="component" id="component" value="${obj.component}" disabled></td>
						           					  			 <td>${obj.cum_planned_last_structure}</td>
						           					  			 <td>${obj.planned_current_structure}</td>
						           					  			 <td>${obj.cum_actual_last_structure}</td>
		                                                        <td data-head="Navigation" class="input-field">
		                                                            <a onclick="reDirectUrl('${obj.contract_id_fk}','${obj.critical_item}','${obj.structure}','${obj.component}');" class="btn waves-effect waves-light t-c ">
		                                                                <i class="fa fa-link" aria-hidden="true"></i></a>
		                                                        </td>
		                                                        <td data-head="Remarks" class="input-field">
		                                                        <textarea id="remarks" name="remarks" class="pmis-textarea pdr4em w85 my-valid-class" data-length="1000" maxlength="1000"></textarea>
		                                                        <input type="hidden" name="fortnightly_plan_update_id" id="fortnightly_plan_update_id" value="${obj.fortnightly_plan_update_id}">
		                                                        </td>
					           					  			 </tr>
					           					  			 <c:set var="count" value="${count+1}" />  
					                                   </c:forEach>
                                             		
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                             </div>
                      
                    </div>
                            <!-- </div> -->
                            <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s12 m6 l6 mt-brdr ">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-m">Update</button>
                                    </div>
                                </div>
                                <div class="col s12 m6 l6 mt-brdr ">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s">Cancel</button>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </form>
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
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

   <script>
   $(document).ready(function() {
	   
	   $("#work_id_fk").val("${FortnightPlan[0].work_id_fk}");
	   $("#contract_id_fk").val("${FortnightPlan[0].contract_id_fk}");
	   $("#category").val("${FortnightPlan[0].category}");
	   $("#critical_item").val("${FortnightPlan[0].critical_item}");
	   
	   
	   $("[data-length]").each(function(i,val){
	    	$('#'+val.id).characterCounter();
	    });
       $(".num").keypress(function() {
           if ($(this).val().length == $(this).attr("maxlength")) {
               return false;
           }
       });
       
       var today = new Date();
       var dd = today.getDate();
       var mm = today.getMonth() + 1;
 
       var yyyy = today.getFullYear();
       
       if (dd < 10) {
           dd = '0' + dd;
       }
       if (mm < 10) {
           mm = '0' + mm;
       }
       var today = dd + '/' + mm + '/' + yyyy;
       
       var dateFrom = "01/"+ mm + '/' + yyyy;
       var dateTo = "15/"+ mm + '/' + yyyy;
       
       var dateFrom1 = "16/"+ mm + '/' + yyyy;
       var dateTo1 = daysInMonth(mm,yyyy)+"/"+ mm + '/' + yyyy;      
       
       
       var dateCheck = today;

       var d1 = dateFrom.split("/");
       var d2 = dateTo.split("/");
       var c = dateCheck.split("/");

       var from = new Date(d1[2], parseInt(d1[1])-1, d1[0]);  
       var to   = new Date(d2[2], parseInt(d2[1])-1, d2[0]);
       var check = new Date(c[2], parseInt(c[1])-1, c[0]);
       
       
       
       var d11 = dateFrom1.split("/");
       var d21 = dateTo1.split("/");
       var c1 = dateCheck.split("/");

       var from1 = new Date(d11[2], parseInt(d11[1])-1, d11[0]);  
       var to1   = new Date(d21[2], parseInt(d21[1])-1, d21[0]);
       var check = new Date(c1[2], parseInt(c1[1])-1, c1[0]);     

       		
       		var months = ["January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"];


  
		if(check > from && check < to)   
	    {
			
			
			  var dateNow = from;
			  var yearNow = dateNow.getFullYear();
			  var monthNow = months[dateNow.getMonth()];
			  var dayNow = dateNow.getDate();
			  var daySuffix;

			  switch (dayNow) {
			      case 1:
			      case 21:
			      case 31:
			          daySuffix = "st";
			          break;
			      case 2:
			      case 22:
			          daySuffix = "nd";
			          break;
			      case 3:
			      case 23:
			          daySuffix = "rd";
			          break;
			      default:
			          daySuffix = "th";
			          break;
			  }			
			var strPeriod=dayNow+daySuffix+" "+monthNow+","+yearNow.toString().substring(yearNow.toString().length-2)+" - "+"15th"+" "+monthNow+","+yearNow.toString().substring(yearNow.toString().length-2);
			
			
	   		$("#period").val(strPeriod);
	    }
   		else if(check > from1 && check < to1)
 		{
			  var dateNow = from1;
			  var yearNow = dateNow.getFullYear();
			  var monthNow = months[dateNow.getMonth()];
			  var dayNow = dateNow.getDate();
			  var daySuffix;

			  switch (dayNow) {
			      case 1:
			      case 21:
			      case 31:
			          daySuffix = "st";
			          break;
			      case 2:
			      case 22:
			          daySuffix = "nd";
			          break;
			      case 3:
			      case 23:
			          daySuffix = "rd";
			          break;
			      default:
			          daySuffix = "th";
			          break;
			  }	
			  
			  var dateNow1 = to1;
			  var yearNow1 = dateNow1.getFullYear();
			  var monthNow1 = months[dateNow1.getMonth()];
			  var dayNow1 = dateNow1.getDate();
			  var daySuffix1;

			  switch (dayNow1) {
			      case 1:
			      case 21:
			      case 31:
			          daySuffix1 = "st";
			          break;
			      case 2:
			      case 22:
			          daySuffix1 = "nd";
			          break;
			      case 3:
			      case 23:
			          daySuffix1 = "rd";
			          break;
			      default:
			          daySuffix1 = "th";
			          break;
			  }			  
			  
			var strPeriod=dayNow+daySuffix+" "+monthNow+","+yearNow.toString().substring(yearNow.toString().length-2)+" - "+dayNow1+daySuffix1+" "+monthNow+","+yearNow.toString().substring(yearNow.toString().length-2);
	   		$("#period").val(strPeriod);
 		}   		
       		
       		
       
       
   });
   
   function reDirectUrl(Param1,Param2,Param3,Param4)
   {
	   sessionStorage.clear();
	   sessionStorage.setItem("contract_id_fk",Param1);
	   sessionStorage.setItem("critical_item",Param2);
	   sessionStorage.setItem("structure",Param3);
	   sessionStorage.setItem("component",Param4);
	   window.open("/pmis/new-activities-update", '_blank');
   }
   
   function daysInMonth(month, year) {
	    return new Date(year, month, 0).getDate();
	}  
	 
	 
   $(document).ready(function () {
       $('select:not(.searchable)').formSelect();
       $( ".searchable" ).each(function( index,val ) {
$( this ).select2({                placeholder:      $( this ).attr('placeholder')       });
});
      
   });




  
     
   </script>

</body>
</html>