<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R & R - Update Forms - PMIS</title>
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
        .w20em{width: 20em;}
        .w60{
            width: 60px;
        }
        .w7em{width: 7em;}
        .bd-none{border:none !important;background: transparent}
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
                  
       <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h5>R&R Agency</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin">
                            <div class="row">  
                                <h5 class="center-align" style="margin-bottom: 40px;">BSES Consultant Details</h5>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Work </p>
                                    <select id="work_id" class="searchable" name="work_id">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> MRVC HOD </p>
                                    <select id="mrvc_hod" class="searchable" name="mrvc_hod">
                                        <option value="">Select</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> MRVC Responsible Person </p>
                                    <select id="mrvc_res_person" class="searchable" name="mrvc_res_person">
                                        <option value="">Select</option>
                                        <option value="Govt">Govt </option>
                                    </select>
                                </div>  
                                
                                
                            </div>
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                     <input id="bses_agency_name" maxlength="100" data-length="100" name="bses_agency_name" type="text" class="validate w80 pdr4em" value="">
                                     <label for="bses_agency_name">BSES Agency Name</label>
                                     <span id="bses_agency_nameError" class="error-msg" ></span>
                                 </div> 
                                 <div class="col s6 m4 l4 input-field">
                                     <input id="res_person_agency" maxlength="50" data-length="50" name="res_person_agency" type="text" class="validate w80 pdr4em" value="">
                                     <label for="res_person_agency">Responsible Person From Agency</label>
                                     <span id="res_person_agencyError" class="error-msg" ></span>
                                 </div> 
                                
                                <div class="col s12 m4 input-field">
                                    <input id="contact_number" maxlength="10" data-length="10" name="contact_number" type="number" class="validate num w80 pdr4em">
                                    <label for="contact_number">Contact Number </label>
                                    <span id="contact_numberError" class="error-msg"></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                     <input id="bses_email" maxlength="100" data-length="100" name="bses_email" type="email" class="validate w80 pdr4em" value="">
                                     <label for="bses_email">Email ID</label>
                                     <span id="bses_emailError" class="error-msg" ></span>
                                 </div> 
                                 <div class="col s6 m4 input-field">
                                     <input id="submission_date_report_ca" name="submission_date_report_ca" type="text" class="validate datepicker" value="">
                                     <button type="button" id="submission_date_report_ca_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                     <label for="submission_date_report_ca">Submission date of report as per CA <!-- <span class="required" id="verification_date_req"></span> --></label>
                                     <span id="submission_date_report_caError" class="error-msg" ></span>
                                </div> 
                                
                                <div class="col s6 m4 input-field">
                                     <input id="actual_date_report_mrvc" name="actual_date_report_mrvc" type="text" class="validate datepicker" value="">
                                     <button type="button" id="actual_date_report_mrvc_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                     <label for="actual_date_report_mrvc">Actual Submission date of BSES report to MRVC </label>
                                     <span id="actual_date_report_mrvcError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <h5 class="center-align">MRVC Verification</h5>
                                <div class="col s12 m12">
                                    <div class="row">
                                        <!-- row 7 -->
                                        <div class="col s5 m5 input-field">
                                            <p style="margin-top: 12px;">Approval by MRVC Responsible Person</p>
                                        </div>
                                        <div class="col s5 m7 input-field">
                                            <p class="radiogroup" style="padding-bottom: 10px;padding-top: 10px;">
                                                <label>
                                                    <input id="approval_yes" class="with-gap" name="mrvc_approval" type="radio"
                                                        value="yes" />
                                                    <span>Yes</span>
                                                </label> &nbsp; <label>
                                                    <input class="with-gap" name="mrvc_approval" type="radio"
                                                        value="no" checked/>
                                                    <span>No</span>
                                                </label>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div id='show-me' style='display:none;margin-top: 20px;'>
                                    <div class="row">
                                        <div class="col s6 m4 input-field offset-m2">
                                             <input id="submission_date_report_mrvc" name="submission_date_report_mrvc" type="text" class="validate datepicker" value="">
                                             <button type="button" id="submission_date_report_mrvc_icon" class="datepicker-button"><i
                                                    class="fa fa-calendar"></i></button>
                                             <label for="submission_date_report_mrvc">Report Submission Date to MRVC </label>
                                             <span id="submission_date_report_mrvcError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s6 m4 input-field">
                                             <input id="approval_date_mrvc" name="approval_date_mrvc" type="text" class="validate datepicker" value="">
                                             <button type="button" id="approval_date_mrvc_icon" class="datepicker-button"><i
                                                    class="fa fa-calendar"></i></button>
                                             <label for="approval_date_mrvc">Approval Date by MRVC </label>
                                             <span id="approval_date_mrvcError" class="error-msg" ></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </div>

                            <div class="container container-no-margin">
                             <div class="row exe-box w100" style="margin-bottom: 20px;">
                                <div class="col s12 m12">
                                    <div class="row fixed-width">
                                        <h5 class="center-align marob">Appointment of Committee</h5>
                                        <div class="table-inside">
                                            <table id="app_com_table" class="mdl-data-table mobile_responsible_table auto-index">
                                                <thead>
                                                    <tr>
                                                        <th class="w60">S No </th>
                                                        <th class="w20em">Date of Appointment </th>
                                                        <th class="w20em">Name of Representative </th>
                                                        <th class="w20em">Phone No </th>
                                                        <th class="w20em">Email ID </th>
                                                        <th class="w60">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stBody">
                                                <input type="hidden" id="sNo" value="1">
                                                       <tr id="actionStRow0">
                                                        <td>&nbsp;</td>
                                                        <td data-head="Date of Appointment" class="input-field">
                                                            <input id="appointment_date_committee" name="appointment_date_committee" type="text" class="validate datepicker" value="">
                                                                 <button type="button" id="appointment_date_committee_icon" class="datepicker-button"><i
                                                                        class="fa fa-calendar"></i></button>
                                                                 <span id="appointment_date_committeeError" class="error-msg" ></span>
                                                        </td>
                                                        <td data-head="Name of Representative" class="input-field">
                                                        <input id="name_representative" maxlength="50" data-length="50" name="name_representative" type="text" class="validate w70 pdr4em" value="">
                                                         <span id="name_representativeError" class="error-msg" ></span>
                                                        </td>
                                                        <td data-head="Contact Number" class="input-field">
                                                            <input id="contact_number_rep" maxlength="10" data-length="10" name="contact_number_rep" type="number" class="validate num w70 pdr4em">
                                                            <span id="contact_number_repError" class="error-msg"></span>
                                                        </td>
                                                        <td data-head="Email" class="input-field">
                                                        <input id="rep_email" maxlength="50" data-length="50" name="rep_email" type="email" class="validate w70 pdr4em" value="">
                                                         <span id="rep_emailError" class="error-msg" ></span>
                                                        </td>
                                                        <td class="input-field mobile_btn_close">
                                                            <a onclick="removeStActions('0');" class="btn waves-effect waves-light red t-c ">
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

          +'<td data-head="Date of Appointment" class="input-field">'
          +'<input id="appointment_date_committee' + rNo + '" type="text" class="validate datepicker" name="appointment_date_committee" value="">'              
          +'<button type="button" id="appointment_date_committee_icon' + rNo + '" class="datepicker-button"><i class="fa fa-calendar"></i></button>'
          +'<span id="appointment_date_committee' + rNo + 'Error" class="error-msg"></span></td>'

          +'<td data-head="Name Of Representative" class="input-field">'
          +'<input type="text"  maxlength="50" data-length="50" id="name_representative' + rNo + '" class="validate w70 pdr4em"  name="name_representative" onchange="executivesToStringMethod('+rNo+');" value="">' 
          +'<span id="name_representative' + rNo + 'Error" class="error-msg"></span> </td>'

          +'<td data-head="Contact Number" class="input-field">'
          +'<input type="number"  maxlength="10" data-length="10" id="contact_number_rep' + rNo + '" class="validate w70 pdr4em num"  name="contact_number_rep" value="">' 
          +'<span id="contact_number_rep' + rNo + 'Error" class="error-msg"></span> </td>'

          +'<td data-head="Email" class="input-field">'
          +'<input type="email"  maxlength="50" data-length="50" id="rep_email' + rNo + '" class="validate w70 pdr4em"  name="rep_email" value="">' 
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
				if(trIndex>1) {
				$(this).closest("tr").remove();
			} else {
				
			}
		});
	}); 
   
  
     
   </script>

</body>
</html>