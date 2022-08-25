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
                                <h5>Update Fortnight Plan</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath()%>/update-quarterly_plan_activities" id="getForm" name="getForm" method="post">
                        <div class="container container-no-margin">
                            <div class="row">  
                                <h5 class="center-align" style="margin-bottom: 40px;"></h5>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Work: <span class="required">*</span></p>
                                    <select id="work_id_fk" class="searchable" name="work_id_fk" onChange="changeWork();">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="workError" class="workError" style="color:red;"></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Period: </p>
                                    <select id="period" class="searchable" name="period" class="period" onChange="fortnightUpdateonSubjectiveActivities();">
                                       <option value="">Select</option>
                                   </select>
                                    <span id="periodError" class="error-msg" ></span>
                                </div>  
                                 <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Fortnight: </p>
                                    <select id="fortnight" class="searchable" name="fortnight" class="fortnight" onChange="fortnightUpdateonSubjectiveActivities();">
                                       <option value="">Select</option>
                                   </select>
                                </div>
                            </div>
                           
                            </div>

                            <div class="container-xl container-no-margin">
                             <div class="row exe-box w100" style="margin-top: 20px;">
                                <div class="col s12 m12">
                                    <div class="row fixed-width">
                                        <div class="table-inside">
                                            <table id="app_com_table" class="mdl-data-table mobile_responsible_table auto-index" style="display:none;">
                                                <thead>
                                                    <tr>
                                                        <th class="w2em">Structure </th>
                                                        <th class="w2em">fortnight </th>
                                                        <th class="w2em">TDC </th>
                                                        <th class="w2em">Units </th>
                                                        <th class="w2em">Cumulative Progress </th>
                                                        <th class="w1em">Activity</th>
                                                        <th class="w20em">Completion Status</th>
                                                        <th class="w20em">Pending Progress</th>
                                                        <th class="w20em">Reason for Shortfall</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stBody">
		                                       	 
												 </tbody>												 
                                            </table>
                                        </div>
                                    </div>
                                </div>
                             </div>
                      
                    </div>
                    <div id="errormsg" style="color:red;"></div>

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
   	getWorksFilterList($("#work_id_fk").val());
   	getPeriodFilterList($("#period").val());
	getFortnightFilterList($("#fortnight").val());

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
			

	   	document.getElementById("getForm").submit();	
   }
   
   function getPeriodFilterList(period){
   	$(".page-loader").show();
	 	var work_id_fk = $("#work_id_fk").val();
	 	var period = $("#period").val(); 
	 	var fortnight = $("#fortnight").val();
	    if ($.trim(period) == "") {
	    	$("#period option:not(:first)").remove();
	    	var myParams = {work_id_fk : work_id_fk,period : period,fortnight_date : fortnight};
           $.ajax({
               url: "<%=request.getContextPath()%>/ajax/getPeriodsListFilterInQuarterlyFortnight",
               data: myParams, cache: false,async: false,
               success: function (data) {
                  if(data != null && data != '' && data.length > 0){  
                       $.each(data, function (i, val) {
                       		var selectedFlag = (period == val.period)?'selected':'';
	                           $("#period").append('<option value="' + val.period + '"'+selectedFlag+'>' + $.trim(val.period) + '</option>');
                       });
                   }
                   $('.searchable').select2();
                   $(".page-loader").hide();
               },error: function (jqXHR, exception) {
	   			      $(".page-loader").hide();
	   	          	  getErrorMessage(jqXHR, exception);
	   	     	  }
           });
       }else{
       	  $(".page-loader").hide();
       }
	 }
   
   function getFortnightFilterList(fortnight){
	 	$(".page-loader").show();
	 	var period = $("#period").val();
	 	var work_id_fk = $("#work_id_fk").val();
	 	var fortnight = $("#fortnight").val();
	    if ($.trim(fortnight) == "") {
	    	$("#fortnight option:not(:first)").remove();
	    	var myParams = {work_id_fk : work_id_fk,period : period,fortnight_date : fortnight};
          $.ajax({
              url: "<%=request.getContextPath()%>/ajax/getFortnightListFilterInQuarterlyFortnight",
              data: myParams, cache: false,async: false,
              success: function (data) {
              	if(data != null && data != '' && data.length > 0){  
                      $.each(data, function (i, val) {
                           var selectedFlag = (fortnight == val.fortnight)?'selected':'';
	                         $("#fortnight").append('<option value="' + val.fortnight + '"'+selectedFlag+'>' + $.trim(val.fortnight)+ '</option>');
                      });
                  }
                  $('.searchable').select2();
                  $(".page-loader").hide();
              },error: function (jqXHR, exception) {
	   			      $(".page-loader").hide();
	   	          	  getErrorMessage(jqXHR, exception);
	   	     	  }
          });
      }else{
      	  $(".page-loader").hide();
      }
	 }
   
   function getWorksFilterList(work_id){
	 	$(".page-loader").show();
	 	var period = $("#period").val();
	 	var work_id_fk = $("#work_id_fk").val();
	 	var fortnight = $("#fortnight").val();
	    if ($.trim(work_id_fk) == "") {
	    	$("#work_id_fk option:not(:first)").remove();
	    	var myParams = {work_id_fk : work_id_fk,period : period,fortnight_date : fortnight};
           $.ajax({
               url: "<%=request.getContextPath()%>/ajax/getWorksListFilterInQuarterlyFortnight",
               data: myParams, cache: false,async: false,
               success: function (data) {
               	if(data != null && data != '' && data.length > 0){  
                       $.each(data, function (i, val) {
                       	 var work_short_name = '';
                            if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) }
                            var selectedFlag = (work_id == val.work_id_fk)?'selected':'';
	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk) + work_short_name + '</option>');
                       });
                   }
                   $('.searchable').select2();
                   $(".page-loader").hide();
               },error: function (jqXHR, exception) {
	   			      $(".page-loader").hide();
	   	          	  getErrorMessage(jqXHR, exception);
	   	     	  }
           });
       }else{
       	  $(".page-loader").hide();
       }
	 }
   
  
   
   function changeWork()
   {
	   $("#workError").html("");
	   	getPeriodFilterList($("#period").val());
		getFortnightFilterList($("#fortnight").val());
		$("#app_com_table").show();
		fortnightUpdateonSubjectiveActivities();
		
	}
   
   function fortnightUpdateonSubjectiveActivities()
   {
	 	var period = $("#period").val();
	 	var work_id_fk = $("#work_id_fk").val();
	 	var fortnight = $("#fortnight").val();

	    	$("#stBody tr").remove();
	    	var myParams = {work_id_fk : work_id_fk,period : period,fortnight_date : fortnight};
           $.ajax({
               url: "<%=request.getContextPath()%>/ajax/getfortnightActivities",
               data: myParams, cache: false,async: false,
               success: function (data) {
               	if(data != null && data != '' && data.length > 0){  
                       $.each(data, function (i, val) {
                       	 var html='<tr><td>'+val.structure+'</td><td>'+val.item+'</td><td>'+val.tdc_calendar+'</td>';
                       	 html+= '<td>'+val.units+'</td><td>'+val.cumulative_progress+'</td><td>'+val.activity_name+'</td>';
                       	 var pendingprogress="";
                       	 var reasonforshortfall="";
                       	    if(val.pending_progress!=undefined)
                       		 {
                       	    	pendingprogress=val.pending_progress;
                       		 }
                       	    if(val.reason_for_shortfall!=undefined)
                      		 {
                       	    	reasonforshortfall=val.reason_for_shortfall;
                      		 }
                       	    
                       	 html+='<td><input type="hidden" name="fortnight_quarterly_plan_activity_id" id="fortnight_quarterly_plan_activity_id'+i+'" value="'+val.fortnightly_plan_id+'"><input type="checkbox" id="completion_status'+i+'" name="completion_status"  placeholder="Completion Status" onchange="checkRemarks('+i+');" value="No"></td><td><input type="text" name="pending_progress" id="pending_progress'+i+'" value="'+pendingprogress+'"></td><td><input type="text" name="reason_for_shortfall" id="reason_for_shortfall'+i+'" value="'+reasonforshortfall+'"></td></tr>';
	                     $("#stBody").append(html);
                       });
                   }
                   $('.searchable').select2();
                   $(".page-loader").hide();
               },error: function (jqXHR, exception) {
	   			      $(".page-loader").hide();
	   	          	  getErrorMessage(jqXHR, exception);
	   	     	  }
           });
	   
   }
   
   function checkRemarks(rowNo)
   {
	   	   if($('#completion_status'+rowNo).is(':checked'))
		   {
	   			$("#pending_progress"+rowNo).val("Completed");
	   			$("#reason_for_shortfall"+rowNo).val("Completed");
	   			
	   			$("#pending_progress"+rowNo).prop("disabled",true);
	   			$("#reason_for_shortfall"+rowNo).prop("disabled",true);
	   			$('#chkcompletion_status'+rowNo).val("Yes");
		   }
	   	   else
   		   {
	   			$("#pending_progress"+rowNo).val("Completed");
	   			$("#reason_for_shortfall"+rowNo).val("Completed");
	   			
	   			$("#pending_progress"+rowNo).prop("disabled",false);
	   			$("#reason_for_shortfall"+rowNo).prop("disabled",false);
	   			$('#chkcompletion_status'+rowNo).val("No");
   		   }
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