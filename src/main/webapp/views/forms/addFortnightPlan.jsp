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
		.my-error-class {
			color: red;
		}
		
		.my-valid-class {
			color: green;
		}
		.images-show{
			display: flex;
		    flex-wrap: wrap;
		    justify-content: flex-start;
		}
		.images-show img,.images-show video{
			width:100px;
			height:100px;
			margin:5px;
		}
		
		.input-field {
		    margin-top: .35rem;
		    margin-bottom: .35rem;
		}
		.input-field>textarea+label:not(.label-icon).active{
			margin-top: 8px;
		}         
		.images-show img:first-of-type{			
			margin-left:0;
		}
		.images-show img:last-of-type{
			margin-right:0;
		}
		.select2-container--default .select2-selection--single{
			background-color:transparent;
		}
		
		.w7em{width: 7em;}
		
		@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -5.5em;
   					 margin-left: 11%;}
   		.bd-none{border: none;}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:18%;}
    	}
    	@media(max-width: 820px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
    	}
		@media only screen and (max-width: 600px) {
		  .images-show{
		    justify-content: space-evenly;
		  }
		}
		.col.input-field>textarea+label:not(.label-icon).active{
		    margin-top:0;
		}
		.input-field p.searchable_label{
			margin-top: -10px !important;
			color :#8585ad;
		}
		.datepicker-table th,
        .datepicker-table td {
            padding: 0 !important;
        }
     
		.datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom: none !important;
        }
        .mobile_responsible_table .datepicker~button {
            top: 26px;
        }        
        
        /* new css code starts from here */ 
		.col.issue-mar,
		.issue-mar{
			margin-top:40px;
		 }
		 .mobile-prio,
		 .col.mobile-prio{
		 	margin-top:30px;
		 }
		.mb-md-20{
			margin-bottom:20px !important;
		}
		.mt-md-0{
			margin-top:0 !important;
		}
		.lh{
			line-height:inherit;
		}
		@media  only screen and (min-width: 769px)  {
			/*  #created_date0_icon{
			 	top:30px;
			 	right:20px;
			 } */
			 .md-pt-2px{
			 	padding-top:2px !important;
			 }
			 .md-mt-0{
			 	margin-top:0 !important;
			 }
		}
		
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
			.mobile_responsible_table tbody tr .input-field .datepicker~button {
			    position: relative;
			    top: 0;
			    right: 26px;
			}			
			.mobile_responsible_table tbody tr td .select2-container {
			    width: 100% !important;
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td::before {
			    vertical-align: middle;
			}
			.mobile_responsible_table tbody {
			    overflow-x:hidden;
			}
			.mt-brdr{
				margin-top: auto !important;
    			border: none !important;
			}
			.mt-brdr .btn{
   				width: 100% !important;
			}
			.mobile-prio{
				margin-top:14px;
				margin-bottom:14px;
			}
			.mobile-prio >.prio{
				text-align:center;
				margin-bottom:5px;
			}
			.col.issue-mar,
			.issue-mar{
				margin-top:25px;
				text-align:center;
			}
			/* input fields styles for mobile version  */
			div.input-field {
			    margin-top: 1rem;
			    margin-bottom: 1rem;
			}
			.input-field p.searchable_label {
			    margin-top: -20px !important;
   				margin-bottom: -4px;
			}
			.input-field>.datelike ~ label:not(.label-icon).active, 
			.input-field>input[type='text']:not(.datepicker) ~ label:not(.label-icon).active,
			.input-field>label:not(.label-icon).active {
			    -webkit-transform: translateY(-18px) scale(0.95);
			    transform: translateY(-18px) scale(0.95);
			}			
			.mb-md-20	{
				margin-bottom:0 !important;
			}				
			.fs-sm-8rem{
				font-size:.8rem !important;
			}	
			.input-field>.datepicker~button{
				top:10px;
				right:6px;
			}			
			.mt-md-0{
				margin-top: -14px !important;
			} 
			.mobile_responsible_table>tbody tr:not(.datepicker-row):not(.mobile_hide_row) {
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row)> td> div.btn{
				float:none;
				position:relative;
			}
			td.cell-disp-inb .file-path-wrapper {
			    visibility: visible;
			    width: 200%;
			    display: block !important;
			}
		}
			.input-field>input[type='text'].datepicker ~ label:not(.label-icon).active{
				-webkit-transform: translateY(-20px) scale(0.95);
			    transform: translateY(-20px) scale(0.95);
			}	
		@media(max-width: 575px){
		.row .col{margin: 6px auto !important;}
		}
		 #compensation_unitsError{
	   		float:right;	
	    }
	   .character-counter {
		  background-color: smoke;
		  position: absolute;
		  top: 25%;
		  right: 1.5em;
		}
		.pdr3em{
			padding-right: 3em !important;
		} 
		.pdr4em{
			padding-right: 4em !important;
		}
		.pdr5em{
			padding-right: 5em !important;
		}
		.w85{
			width: 85% !important;
		}
		.w80{
			width: 80% !important;
		}
		.w75{
			width: 75% !important;
		}
		.w70{
			width: 70% !important;
		}
		
[type="checkbox"]:not(:checked), [type="checkbox"]:checked {
   position: absolute !important;
   opacity: 5 !important;
   pointer-events: inherit !important;
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
                    <form action="<%=request.getContextPath()%>/update-fortnightly-plan" id="getForm" name="getForm" method="post">
                        <div class="container container-no-margin">
                            <div class="row">  
                                <h5 class="center-align" style="margin-bottom: 40px;"></h5>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Work: <span class="required">*</span></p>
                                    <select id="work_id_fk" class="searchable" name="work_id_fk" onChange="changeWork();">
                                        <option value="">Select</option>
                                       	<c:forEach var="obj" items="${FortnightPlanWorkList }">
                                      	   	<option value= "${obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                    	 </c:forEach>                                                                               
                                    </select>
                                    <span id="workError" class="workError" style="color:red;"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Category: <span class="required"></span></p>
                                    <select id="category" class="searchable" name="category" disabled>
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${FortnightPlanCategoryList }">
                                            <option value= "${obj.module_name}" <c:if test="${obj.module_name eq 'Others' }">selected</c:if>>${obj.module_name}</option>
                                        </c:forEach>                                        
                                    </select>
                                </div>   
                                 <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Critical Item: </p>
                                    <input type="text" class="input-field" id="critical_item" name="critical_item" maxlength="50"  placeholder="Critical Item" style="padding-top:0px;"/>
                                </div>
                            </div>
                            
                             <div class="row" class="col s12 m12 input-field">
                             	<div class="col s4 m4 input-field"></div>
                                 <div class="col s4 m4 input-field">
                                    <p class="searchable_label"> Period: </p>
                                    <input type="text" id="period" name="period" disabled>
                                </div>
                                <div class="col s4 m4 input-field"></div>  
                            </div>                           
                        
							<div>
							
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
                                                        <th class="w2em">Activity </th>
                                                        <th class="w2em">Scope of Work </th>
                                                        <th class="w2em">Planned Progress on Last Fortnight </th>
                                                        <th class="w2em">Actual Progress on Last Fortnight </th>
                                                        <th class="w1em">Plan for the current Fortnight</th>
                                                        <th class="w20em">Completion Status</th>
                                                        <th class="w20em">Remarks</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stBody">
		                                       	 <tr id="fortnightplanRow0">
													<td data-head="Financial Year" class="input-field">
															1
													</td>
													<td class="input-field">
															 <input type="text" id="structure0"  name="structure" placeholder="structure" value="N / A" maxlength="25"> 
													</td>
													<td class="input-field">
															 <input type="text" id="activity0" name="activity"   placeholder="activity" maxlength="100">
													</td>
													<td class="input-field">
															 <input type="text" id="scope_of_work0" name="scope_of_work"   placeholder="Scope of work" maxlength="100">
													</td>
													<td class="input-field">
															 <input type="text" id="planned_progress_on_last_fortnight0" name="planned_progress_on_last_fortnight"  placeholder="planned progress on last fortnight" maxlength="100">
													</td>
													<td class="input-field">
															 <input type="text" id="actual_progress_on_last_fortnight0" name="actual_progress_on_last_fortnight"  placeholder="Actual progress on last fortnight" maxlength="100">
													</td>
													<td class="input-field">
															 <input type="text" id="plan_for_the_current_fortnight0" name="plan_for_the_current_fortnight"  placeholder="Plan for the current fortnight" maxlength="100">
													</td>
													<td class="input-field" style="text-align:center;">
															 <input type="checkbox" id="completion_status0" name="completion_status"  placeholder="Completion Status" onchange="checkRemarks(0);" value="No">
													</td>	
													<td class="input-field">
															 <input type="text" id="remarks0" name="remarks" placeholder="remarks" maxlength="100">
															 <input type="hidden" id="chkcompletion_status0" name="chkcompletion_status" maxlength="100" value="No">
													</td>																									
													<td class="mobile_btn_close "></td><input type="hidden" name="fortnightly_plan_update_id" id="fortnightly_plan_update_id">
												 </tr>
												 </tbody>												 
                                            </table>
                                        </div>
                                    </div>
                                </div>
                             </div>
                      
                    </div>
                    <div id="errormsg" style="color:red;"></div>
                              <div class="row">  
 								<p id="fyError" style="color:red;"></p>
								 <table class="mdl-data-table">
                                       <tbody>                                          
	                                    <tr>
  										   <td colspan="6" class="mobile_btn_add" > <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addFortnightPlan()"> <i
	                                                            class="fa fa-plus"></i></a>
	                                    </tr>
                                       </tbody>
                               </table> 
                           </div> 

                                 		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                           
                    
                            <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s12 m6 l6 mt-brdr ">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-m" onClick="UpdateValidation();" type="button">Update</button>
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
   
   
   function UpdateValidation()
   {
	   
			if($("#work_id_fk").val()=="")
   			{
	   				$("#workError").html("Work ID Required.");
	   				$("#work_id_fk").css('border-color', 'red');
	   				return false;
   			}
   			else
			{
   				$("#workError").html("");
   				$("#work_id_fk").css('border-color', '');	   				
			}
			
	   		var completionstatus="";
	   	   for(var r=0;r<$('#app_com_table tbody tr').length;r++)
		   {
		   			if($("#structure"+r).val()=="")
		   			{
		   				$("#errormsg").html("Structure Required.");
		   				$("#structure"+r).css('border-color', 'red');
		   				return false;
		   			}
		   			else
	   				{
		   				$("#errormsg").html("");
		   				$("#structure"+r).css('border-color', '');	   				
	   				}
		   			if($("#activity"+r).val()=="")
		   			{
		   				$("#errormsg").html("Activity Required.");
		   				$("#activity"+r).css('border-color', 'red');
		   				return false;
		   			}
		   			else
	   				{
		   				$("#errormsg").html("");
		   				$("#activity"+r).css('border-color', '');	   				
	   				}		   			
		   			if($("#scope_of_work"+r).val()=="")
		   			{
		   				$("#errormsg").html("Scope of work Required.");
		   				$("#scope_of_work"+r).css('border-color', 'red');
		   				return false;
		   			}
		   			else
	   				{
		   				$("#errormsg").html("");
		   				$("#scope_of_work"+r).css('border-color', '');	   				
	   				}		   			
		   			if($("#planned_progress_on_last_fortnight"+r).val()=="")
		   			{
		   				$("#errormsg").html("Planned progress on last fortnight Required.");
		   				$("#planned_progress_on_last_fortnight"+r).css('border-color', 'red');
		   				return false;
		   			}
		   			else
	   				{
		   				$("#errormsg").html("");
		   				$("#planned_progress_on_last_fortnight"+r).css('border-color', '');	   				
	   				}		   			
		   			if($("#actual_progress_on_last_fortnight"+r).val()=="")
		   			{
		   				$("#errormsg").html("Actual progress on last fortnight Required.");
		   				$("#actual_progress_on_last_fortnight"+r).css('border-color', 'red');
		   				return false;
		   			}
		   			else
	   				{
		   				$("#errormsg").html("");
		   				$("#actual_progress_on_last_fortnight"+r).css('border-color', '');	   				
	   				}		   			
		   			if($("#plan_for_the_current_fortnight"+r).val()=="")
		   			{
		   				$("#errormsg").html("Plan for the current fortnight Required.");
		   				$("#plan_for_the_current_fortnight"+r).css('border-color', 'red');
		   				return false;
		   			}
		   			else
	   				{
		   				$("#errormsg").html("");
		   				$("#plan_for_the_current_fortnight"+r).css('border-color', '');	   				
	   				}		   			
		   			if($("#remarks"+r).val()=="")
		   			{
		   				$("#errormsg").html("Remarks Required.");
		   				$("#remarks"+r).css('border-color', 'red');
		   				return false;
		   			}
		   			else
	   				{
		   				$("#errormsg").html("");
		   				$("#remarks"+r).css('border-color', '');	   				
	   				}
		 	   	   if($('#completion_status'+r).is(':checked'))
				   {
		 	   			$('#chkcompletion_status'+r).val("Yes");
				   }
			   	   else
		   		   {
			   		$('#chkcompletion_status'+r).val("No");
		   		   }
		 	   	  $('#category').prop('disabled',false);
		 	   	  $('#period').prop('disabled',false);
		   }
	   	document.getElementById("getForm").submit();	
   }
   
   function addFortnightPlan()
   {
	    var rowNo = $("#rowNo").val();
        var rNo = Number(rowNo)+1;
        var value = rNo+1;
    	var html ='<tr id="fortnightplanRow0">';
    	 html += '<td data-head="Financial Year" class="input-field">'+value;
    	 html +=	'</td>';
    		 html +=	'<td class="input-field">';
    		 html += '<input type="text" id="structure'+rNo+'"  name="structure" placeholder="structure" value="N / A" maxlength="25"> ';
    		 html +='</td>';
    		 html +='<td class="input-field">';
    		 html +=		 '<input type="text" id="activity'+rNo+'" name="activity"   placeholder="activity" maxlength="100">';
    		 html +='</td>';
    		 html +='<td class="input-field">';
    		 html += '<input type="text" id="scope_of_work'+rNo+'" name="scope_of_work"   placeholder="Scope of work" maxlength="100">';
    		 html +='</td>';
    		 html +='<td class="input-field">';
    		 html += '<input type="text" id="planned_progress_on_last_fortnight'+rNo+'" name="planned_progress_on_last_fortnight"  placeholder="planned progress on last fortnight" maxlength="100">';
    		 html +='</td>';
    		 html +='<td class="input-field">';
    		 html +='<input type="text" id="actual_progress_on_last_fortnight'+rNo+'" name="actual_progress_on_last_fortnight"  placeholder="Actual progress on last fortnight" maxlength="100">';
    		 html +='</td>';
    		 html +='<td class="input-field">';
    		 html +='<input type="text" id="plan_for_the_current_fortnight'+rNo+'" name="plan_for_the_current_fortnight"  placeholder="Plan for the current fortnight" maxlength="100">';
    		 html +='</td>';
    		 html +='<td class="input-field" style="text-align:center;">';
    		 html +=		 '<input type="checkbox" id="completion_status'+rNo+'" name="completion_status"  placeholder="Completion Status" onchange="checkRemarks('+rNo+');" value="No">';
    		 html +='</td>';	
    		 html +='<td class="input-field">';
    		 html +=		 '<input type="text" id="remarks'+rNo+'" name="remarks" placeholder="remarks" maxlength="100"><input type="hidden" id="chkcompletion_status'+rNo+'" name="chkcompletion_status" maxlength="100" value="No">';
    		 html +='</td>';																								
    		 html +='<td class="mobile_btn_close "></td>';
    		 html +='</tr>';
        $('#stBody').append(html);
		$("#rowNo").val(rNo);
        $('.searchable').select2();	   
   }
   
   function changeWork()
   {
	   $("#workError").html(""); 
   }
   
   function checkRemarks(rowNo)
   {
	   	   if($('#completion_status'+rowNo).is(':checked'))
		   {
	   			$("#remarks"+rowNo).val("Completed");
	   			$('#chkcompletion_status'+rowNo).val("Yes");
		   }
	   	   else
   		   {
	   			$("#remarks"+rowNo).val("");
	   			$('#chkcompletion_status'+rowNo).val("No");
   		   }
   }
   
   function reDirectUrl(Param1,Param2,Param3,Param4)
   {
	   sessionStorage.clear();
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