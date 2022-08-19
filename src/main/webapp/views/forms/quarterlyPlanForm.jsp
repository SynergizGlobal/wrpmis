<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Quarterly Plan</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style type="text/css">
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
        .w20em{width: 20em;}
        .w15em{width: 15em;}
        .w10em{width: 10em;}
        .w60{
            width: 60px;
        }
        .w2em{
        	width: 2em;
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
    </style>
</head>

<body>

    <!-- header  starts-->
	<jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

  <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h5>Add Quarterly Plan</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin">
                            <div class="row">  
                                <h5 class="center-align" style="margin-bottom: 40px;"></h5>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Work: </p>
                                     <select id="work_id" class="searchable" name="work_id">
                                        <option value="">Select</option>
                                    </select>
                                    
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Period: </p>
                                    <select id="qp_period" class="searchable" name="qp_period">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="qp_structure" maxlength="30" data-length="30" name="qp_structure" type="text" class="validate w80 pdr4em" value="">
                                     <label for="qp_structure">Structure</label>
                                     <span id="qp_structureError" class="error-msg" ></span>
                                </div>  
                                
                                
                            </div>
                            <div class="row" style="margin-top: 20px;">
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Item: </p>
                                    <select id="qp_item" class="searchable" name="qp_item">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                 <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Criticality: </p>
                                    <select id="qp_criticality" class="searchable" name="qp_criticality">
                                        <option value="">Select</option>
                                         <option value="yes">Yes</option>
                                          <option value="no">No</option>
                                    </select>
                                </div> 
                                <div class="col s6 m4 input-field">
                                	<input id="tdc_calendar" name="tdc_calendar" type="text" class="validate datepicker" value="">
                                             <button type="button" id="tdc_calendar_icon" class="datepicker-button"><i
                                                    class="fa fa-calendar"></i></button>
                                             <label for="tdc_calendar_mrvc">TDC Calendar </label>
                                             <span id="tdc_calendarError" class="error-msg" ></span>
                                </div> 
                                <div class="col s6 m4 input-field">
                                    <textarea id="scope_of_work" name="scope_of_work" class="pmis-textarea pdr4em w85 my-valid-class" data-length="150" maxlength="150"></textarea>
                                     <label for="scope_of_work">Scope Of Work</label>
                                     <span id="scope_of_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                <textarea id="cumulative_progress" name="cumulative_progress" class="pmis-textarea pdr4em w85 my-valid-class" data-length="150" maxlength="150"></textarea>
                                     <label for="cumulative_progress">Cumulative Progress</label>
                                     <span id="cumulative_progressError" class="error-msg" ></span>
                                </div> 
                            </div>

                            
                            </div>

                            <div class="container container-no-margin">
                             <div class="row exe-box" style="margin-top: 20px;">
                                <div class="col s12 m12">
                                    <div class="row fixed-width">
                                       <!--  <h5 class="center-align marob">Appointment of Committee</h5> -->
                                        <div class="table-inside">
                                            <table id="app_com_table" class="mdl-data-table mobile_responsible_table auto-index">
                                                <thead>
                                                    <tr>
                                                        <th class="w1em">S No </th>
                                                        <th class="w15em">Fortnight </th>
                                                        <th class="w20em">Activity </th>
                                                        <th class="w1em">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stBody">
                                                <input type="hidden" id="sNo" value="1">
                                                       <tr>
                                                        <td data-head="S No">&nbsp;</td>
                                                        <td data-head="Fortnight" class="input-field">
                                                            <select id="qp_fortnight" class="searchable" name="qp_fortnight">
						                                        <option value="">Select</option>
						                                    </select>
                                                        </td>
                                                        <td data-head="Activity" class="input-field">
                                                        <textarea id="qp_activity" name="qp_activity" class="pmis-textarea pdr4em w85 my-valid-class" data-length="200" maxlength="200"></textarea>
                                                        </td>
                                                        
                                                        
                                                        <td class="input-field mobile_btn_close">
                                                            <a href="#" onclick="removeStActions('0');" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                             
                                                </tbody>
                                            </table>
                                             <table class="mdl-data-table table-add bd-none">
                                                <tbody class="bd-none">
                                                    <tr class="bd-none">
                                                        <td colspan="3" class="bd-none"><a
                                                            type="button"
                                                            class="btn waves-effect waves-light bg-m t-c add-align"
                                                            onclick="addStRow()"> <i
                                                                class="fa fa-plus"></i>
                                                        </a>
                                                    </tr>
                                                </tbody>
                                            </table>
                                                
                                                    
                                                        <input type="hidden" id="rowNo"  name="rowNo" value="" />
                                                    
                                                        <input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                                    
                                            

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
                                        <button class="btn waves-effect waves-light bg-m">Add /
                                            Edit</button>
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
    
 <form action="<%=request.getContextPath()%>/get-rr-bses" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="rrbses_id" id="rrbses_id"/>
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
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
    });
 	 
 	 
    $(document).ready(function () {
        $('select:not(.searchable)').formSelect();
        $( ".searchable" ).each(function( index,val ) {
 $( this ).select2({                placeholder:      $( this ).attr('placeholder')       });
 });
       // $('').select2({                placeholder            });
    });

 $(function() {
 $('input[type="radio"]').click(function() {
   if($(this).attr('id') == 'approval_yes') {
        $('#show-me').show();           
   }

   else {
        $('#show-me').hide();   
   }
 });
 });

   function addStRow(){
        var rowNo = $("#rowNo").val();
        var rowNo1 = $("#sNo").val();
        var rNo = Number(rowNo)+1;
        var sNo = Number(rowNo1)+1;
        var html = '<tr id="actionStRow' + rNo + '"><td></td>'

           +'<td data-head="Fortnight" class="input-field">'
           +'<select id="qp_fortnight' + rNo + '" class="searchable"  name="qp_fortnight" value="">' 
           +'<option value="">Select</option></select></td>'

           +'<td data-head="Activity" class="input-field">'
           +'<textarea id="qp_activity' + rNo +'" name="qp_activity" class="pmis-textarea pdr4em w85 my-valid-class" data-length="200" maxlength="200"></textarea> </td>'

           +'<td class="input-field mobile_btn_close"><a onclick="removeStActions(' + rNo + ');" class="btn waves-effect waves-light red t-c remove"><i class="fa fa-close"></i></a></td>'
           +'</tr>';
    
        $('#stBody').append(html);
        $("#rowNo").val(rNo);
        var rowCount = $("#stBody tr").length;
        $("#sNo").val(rowCount);    
        $('#qp_activity'+rNo).characterCounter();
        $('.searchable').select2();
    }
 	$(function(){
 		
 		$(document).on('click', '.remove', function() {
 			var trIndex = $(this).closest("tr").index();
 				if(trIndex>1) {
 				$(this).closest("tr").remove();
 			} else {
 				
 			}
 		});
 	});  
 	<%-- function getResponsible(hod){
 	   	$(".page-loader").show();
 	    $("#mrvc_responsible_person option:not(:first)").remove();
 	    if ($.trim(hod) != "") {
 	        var myParams = { hod: hod };
 	        $.ajax({
 	            url: "<%=request.getContextPath()%>/ajax/getResponsibleListInRRBSES",
 	            data: myParams, cache: false,
 	            success: function (data) {
 	                if (data.length > 0) {
 	                    $.each(data, function (i, val) {
 	                        var workName = '';
 	                        if ($.trim(val.user_name) != '') { workName = ' - ' + $.trim(val.user_name) }
 	                        var user_id = "${rrDetails.mrvc_responsible_person}";
 	                        if ($.trim(user_id) != '' && val.user_id == $.trim(user_id)) {
 	                            $("#mrvc_responsible_person").append('<option  value="' + val.user_id + '" selected>' + $.trim(val.designation) + $.trim(workName) + '</option>');
 	                        } else {
 	                            $("#mrvc_responsible_person").append('<option  value="' + val.user_id + '">' + $.trim(val.designation) + $.trim(workName) + '</option>');
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
 		
 	} --%>
 /* 	  function addStRow(){
 	       var rowNo = $("#rowNo").val();
 	       var rowNo1 = $("#sNo").val();
 	       var rNo = Number(rowNo)+1;
 	       var sNo = Number(rowNo1)+1;
 	       var html = '<tr id="actionStRow' + rNo + '"><td></td>'

 	          +'<td data-head="Date of Appointment" class="input-field">'
 	          +'<input id="appointment_date_committee' + rNo + '" type="text" placeholder="Date" class="validate datepicker" name="date_of_appointments" value="">'              
 	          +'<button type="button" id="appointment_date_committee_icon' + rNo + '" class="datepicker-button"><i class="fa fa-calendar"></i></button>'
 	          +'<span id="appointment_date_committee' + rNo + 'Error" class="error-msg"></span></td>'

 	          +'<td data-head="Name Of Representative" class="input-field">'
 	          +'<input type="text" placeholder="Name"  maxlength="50" data-length="50" id="name_representative' + rNo + '" class="validate w70 pdr4em"  name="name_of_representatives" onchange="executivesToStringMethod('+rNo+');" value="">' 
 	          +'<span id="name_of_representative' + rNo + 'Error" class="error-msg"></span> </td>'

 	          +'<td data-head="Contact Number" class="input-field">'
 	          +'<input type="number" placeholder="Number" maxlength="10" data-length="10" id="contact_number_rep' + rNo + '" class="validate w70 pdr4em num"  name="phone_nos" value="">' 
 	          +'<span id="contact_number_rep' + rNo + 'Error" class="error-msg"></span> </td>'

 	          +'<td data-head="Email" class="input-field">'
 	          +'<input type="email" placeholder="Email"  maxlength="50" data-length="50" id="rep_email' + rNo + '" class="validate w70 pdr4em"  name="email_ids" value="">' 
 	          +'<span id="rep_email' + rNo + 'Error" class="error-msg"></span> </td>'

 	          +'<td class="input-field mobile_btn_close"><a onclick="removeStActions(' + rNo + ');" class="btn waves-effect waves-light red t-c remove"><i class="fa fa-close"></i></a></td>'
 	          +'</tr>';
 	   
 	       $('#stBody').append(html);
 	       $("#rowNo").val(rNo);   
 	       var rowCount = $("#stBody tr").length;
 	       $("#sNo").val(rowCount);    
 	       $('#name_representative'+rNo).characterCounter();
 			 $('#contact_number_rep'+rNo).characterCounter();
 			 $('#rep_email'+rNo).characterCounter();
 			 $('#contact_number_rep'+rNo).keypress(function() {
 		           if ($(this).val().length == $(this).attr("maxlength")) {
 		               return false;
 		           }
 		       });
 	       $('.searchable').select2();
 	   }
 		$(function(){
 			
 			$(document).on('click', '.remove', function() {
 				var trIndex = $(this).closest("tr").index();
 					if(trIndex>0) {
 					$(this).closest("tr").remove();
 				} else {
 					
 				}
 			});
 		}); */ 
 	   

 /* 		   function addRR(){
 		   	 if(validator.form()){ // validation perform
 		       	$(".page-loader").show();	
 		       	    $('form input[name=date_of_appointments]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 		 			$('form input[name=name_of_representatives]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 		 			$('form input[name=phone_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 		 			$('form input[name=email_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 		  			document.getElementById("RandRForm").submit();			
 			 	 }
 		   }
 		   function updateRR(){
 		   	if(validator.form()){ // validation perform
 		       	$(".page-loader").show();	
 		       	$('form input[name=financial_year_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 		        $('form input[name=date_of_appointments]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 	 			$('form input[name=name_of_representatives]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 	 			$('form input[name=phone_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 	 			$('form input[name=email_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 		  			document.getElementById("RandRForm").submit();	
 		   	}
 		   }
 	     

 		   var validator =	$('#RandRForm').validate({
 				 errorClass: "my-error-class",
 				 validClass: "my-valid-class",
 				 ignore: ":hidden:not(.validate-dropdown)",
 		 		    rules: {
 		 		 		   "work_id_fk": {
 		 			 		  required: true
 		 			 	  },"hod": {
 		 			 		  required: true
 		 			 	  }	,"mrvc_responsible_person"	:{
 		 			 		  required:true
 		 			 	  },"bses_agency_name"	:{
 		 			 		  required:true
 		 			 	  }
 		 		 	},
 		 		    messages: {
 		 		 		   "work_id_fk": {
 		 			 		  required: 'Required'
 		 			 	  },"hod": {
 		 			 		  required: 'Required'
 		 			 	  }	,"mrvc_responsible_person"	:{
 		 			 		  required:'Required'
 		 			 	  },"bses_agency_name"	:{
 		 			 		  required:'Required'
 		 			 	  }
 			   		},
 			   		errorPlacement:function(error, element){
 			   		 	  if(element.attr("id") == "work_id_fk" ){
 						     document.getElementById("work_id_fkError").innerHTML="";
 					 	     error.appendTo('#work_id_fkError');
 						 }else if(element.attr("id") == "hod" ){
 						     document.getElementById("hodError").innerHTML="";
 					 	     error.appendTo('#hodError');
 						 }else if(element.attr("id") == "mrvc_responsible_person" ){
 						     document.getElementById("mrvc_responsible_personError").innerHTML="";
 					 	     error.appendTo('#mrvc_responsible_personError');
 						 }else if(element.attr("id") == "bses_agency_name" ){
 						     document.getElementById("bses_agency_nameError").innerHTML="";
 					 	     error.appendTo('#bses_agency_nameError');
 						 }else{
 							 error.insertAfter(element);
 				        } 
 			   		 	  
 			   		},invalidHandler: function (form, validator) {
 		                var errors = validator.numberOfInvalids();
 		                if (errors) {
 		                    var position = validator.errorList[0].element;
 		                    jQuery('html, body').animate({
 		                        scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
 		                    }, 1000);
 		                }
 		            },
 			   		submitHandler:function(form){
 				    	//form.submit();
 				    }
 				});   
 		  	 $.validator.addMethod("dateFormat",
 		   	    function(value, element) {
 		   	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
 		   	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
 		   	    	//return dtRegex.test(value);
 		   	    },
 		   	    //"Date format (Aug 02,2020)"
 		   	    "Date format (DD-MM-YYYY)"
 		   	);
 		      $('select').change(function(){
 		          if ($(this).val() != ""){
 		              $(this).valid();
 		          }
 		      });

 		      $('input').change(function(){
 		          if ($(this).val() != ""){
 		              $(this).valid();
 		          }
 		      }); */

    </script>

</body>

</html>