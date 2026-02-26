<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Quarterly Plan</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-grid-template.css" />
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
                                <h5 id="tleName">Add Quarterly Plan</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath()%>/insert-fortnightly-plan" id="getForm" name="getForm" method="post">
                        <div class="container container-no-margin">
                            <div class="row">  
                                <h5 class="center-align" style="margin-bottom: 40px;"></h5>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Period <span class="required">*</span></p>
                                    <select id="period" class="searchable" name="period" onChange="getFortnights(0);">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="periodError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="structure" maxlength="30" data-length="30" name="structure" type="text" class="validate w80 pdr4em" value="">
                                     <label for="structure">Category<span class="required">*</span></label>
                                     <span id="structureError" class="error-msg" ></span>
                                </div>  
                                
                                
                            </div>
                            <div class="row" style="margin-top: 20px;">
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Item <span class="required">*</span></p>
                                    <input id="item" maxlength="150" data-length="150" name="item" type="text" class="validate w80 pdr4em" value="">
                                    <span id="itemError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Criticality <span class="required">*</span></p>
                                    <select id="criticality" class="searchable" name="criticality">
                                        <option value="">Select</option>
                                         <option value="Yes">Yes</option>
                                          <option value="No">No</option>
                                    </select>
                                     <span id="criticalityError" class="error-msg" ></span>
                                </div> 
                                <div class="col s6 m2 input-field">
                                	<input id="tdc_calendar" name="tdc_calendar" type="text" class="validate datepicker" value="">
                                             <button type="button" id="tdc_calendar_icon" class="datepicker-button"><i
                                                    class="fa fa-calendar"></i></button>
                                             <label for="tdc_calendar_mrvc">TDC</label>
                                             <span id="tdc_calendarError" class="error-msg" ></span>
                                </div>
							     <div class="col s6 m2 input-field">
							         <a href="javascript:addTdc();" class="btn waves-effect waves-light bg-s t-c">
                                     <strong>Add TDC</strong></a>
							    </div>                                 
                            </div>
                            <div class="row" style="margin-top: 20px;">
                                <div class="col s6 m4 input-field">
                                    <textarea id="scope_of_work_quarterly" name="scope_of_work_quarterly" class="pmis-textarea pdr4em w85 my-valid-class" data-length="150" maxlength="150"></textarea>
                                     <label for="scope_of_work_quarterly">Scope Of Work <span class="required">*</span></label>
                                     <BR><span id="scope_of_work_quarterlyError" class="error-msg" ></span>
                                </div>
                                  <div class="col s6 m4 input-field">
                                    <input type="text" id="cumulative_progress" name="cumulative_progress" data-length="150" maxlength="150">
                                     <label for="cumulative_progressError">Cumulative Progress <span class="required">*</span></label>
                                     <BR><span id="cumulative_progressError" class="error-msg" ></span>
                                </div>
                                
                                   <div class="col s6 m4 input-field">
                                    <input type="text" id="revised_tdc" name="revised_tdc" data-length="150" maxlength="150">
                                     <label for="revised_tdcError" id="revisedtdc">Revised TDC </label>
                                     <BR><span id="revised_tdcError" class="error-msg" ></span>
                                </div>                                                               
                                
                            </div>

                             <div class="row" style="margin-top: 20px;">
                                <div class="col s6 m4 input-field">
                                    <textarea id="backlog_from_previous_months" name="backlog_from_previous_months" class="pmis-textarea pdr4em w85 my-valid-class" data-length="150" maxlength="150"></textarea>
                                     <label for="backlog_from_previous_months">Backlog from Previous Months</label>
                                     <BR><span id="backlog_from_previous_monthsError" class="error-msg" ></span>
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
                                                        <th class="w20em">Planned Activity </th>
                                                        <th class="w15em">Units </th>
                                                        <th class="w1em"></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stBody">
                                                <input type="hidden" id="sNo" value="1">
                                                       <tr>
                                                        <td data-head="S No">1</td>
                                                        <td data-head="Fortnight" class="input-field">
                                                            <select id="fortnight0" class="searchable" name="fortnight" class="fortnight">
						                                        <option value="">Select</option>
						                                    </select>
                                                        </td>
                                                        <td data-head="Activity" class="input-field">
                                                        <input type="text" id="activity0" name="activity" data-length="200" maxlength="200">
                                                        </td>
                                                        <td data-head="Units" class="input-field">
                                                        <input type="text" id="units0" name="units" data-length="50" maxlength="50">
                                                        </td>                                                        
                                                        <td class="input-field mobile_btn_close">

                                                        </td>
                                                    </tr>
                                             
                                                </tbody>
                                            </table>

  
    <!-- Modal Structure -->
   <div id="tdcModal" class="modal">
       <div class="modal-content">
           <h5 class="modal-header"> TDC Revisions
	           <span class="right modal-action modal-close">
	           <span class="material-icons">close</span></span>
           </h5>
               <div class="row no-mar" id="amendment_not_required_in_contract_Div">
                            <div class="container container-no-margin">
                             <div class="row exe-box">
                                <div class="col s12 m12">
                                    <div class="row">
                                       <!--  <h5 class="center-align marob">Appointment of Committee</h5> -->
                                        <div class="table-inside">
                                            <table id="app_com_tableRevision" class="mdl-data-table mobile_responsible_table">
                                                <thead>
                                                    <tr>
                                                    	<th class="w1em">Revision No. </th>
                                                        <th class="w1em">TDC Date </th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stTDCBody">
                                                <input type="hidden" id="sNoRevision" value="1">
                                                       <tr>
                                                       		<td style="width:100px;"><input id="revisionno0" name="revisionno" type="text" value="R1"></td>
                                                        	<td style="width:300px;"><input id="tdc_revisiondate0" name="tdc_revisiondate" type="text" class="validate datepicker" value=""></td>
                                                        	<td></td>
                                                        </tr>
                                            
                                                </tbody>
                                            </table>
                                             <table class="mdl-data-table table-add bd-none">
                                                <tbody class="bd-none">
                                                    <tr class="bd-none">
                                                        <td colspan="3" class="bd-none"><button
                                                            type="button"
                                                            class="btn waves-effect waves-light bg-m t-c add-align" id="add-align"
                                                            onclick="addTDCRow()"> <i
                                                                class="fa fa-plus"></i>
                                                        </button>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <div id="errorRevision" style="color:red;"></div>
                                             <table>
                                            	<tr><td><button type="button" name="btnRevision" id="btnRevision" onClick="addRevision();" class="btn btn-primary">Add</button></td></tr>
                                            </table>                                           
                                        </div>
                                    </div>
                                </div>
                             </div>
                         
                    </div>
                    

               </div>        
       </div>
   </div>                                          
                                            
                                             <table class="mdl-data-table table-add bd-none">
                                                <tbody class="bd-none">
                                                    <tr class="bd-none">
                                                        <td colspan="3" class="bd-none"><button
                                                            type="button"
                                                            class="btn waves-effect waves-light bg-m t-c add-align" id="add-align"
                                                            onclick="addStRow()"> <i
                                                                class="fa fa-plus"></i>
                                                        </button>
                                                    </tr>
                                                </tbody>
                                            </table>
                                                
                                                
                                                <input type="hidden" id="fortnightly_plan_id"  name="fortnightly_plan_id" value="" />
                                                    
                                                        <input type="hidden" id="rowNoRevision"  name="rowNoRevision" value="0" />
                                                    
                                                        <input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                                    
                                            

                                        </div>
                                    </div>
                                </div>
                             </div>
                      <div id="errormsg" style="color:red;"></div>
                    </div>
 <div class="row">                                
                                <div class="col s12 m6 right-align">
	                                <div class="m-1">
	                                     
										  
					                       <button type="button" onclick="addFortnightQuarterly();" class="btn waves-effect waves-light bg-m" style="min-width:90px" id="btnAdd">Add</button>
										                                 
	                                </div>
                                </div>
                                 <div class="col s12 m6">
                                	<div class="m-1">
                                     	 <button class="btn waves-effect waves-light bg-s" id="btnCancel">Cancel</button>
                                    </div>
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
    </form>
    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/datepickerDepedency.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
    
   
	   $("#work_id_fk").val("${FortnightPlan[0].work_id_fk}");
	   $("#structure").val("${FortnightPlan[0].structure}");
	   $("#item").val("${FortnightPlan[0].item}");
	   
	   $("#criticality").val("${FortnightPlan[0].criticality}");
	   $("#tdc_calendar").val("${FortnightPlan[0].tdc_calendar}");
	   $("#scope_of_work_quarterly").val("${FortnightPlan[0].scope_of_work_quarterly}");
	   $("#cumulative_progress").val("${FortnightPlan[0].cum_progress}");
	   $("#fortnightly_plan_id").val("${FortnightPlan[0].fortnightly_plan_id}");
	   $("#backlog_from_previous_months").val("${FortnightPlan[0].backlog_from_previous_months}");
	   
	   
	   
	   
   	if("${(fn:length(FortnightPlan))}">0)
	{
   		var i=0;	  			 
 		<c:forEach var="tempobj" items="${FortnightPlan}">
	   		if(i>0)
			   {
	   			addStRow();
	   			
	   	        $('#add-align').prop('disabled', true);
	   	        $('.remove').prop('disabled', true);
	   	     
			   }	
	   		
 	 		   $("#units"+i).val("${tempobj.unit}");
 	 		   $("#cumulative_progress"+i).val("${tempobj.cum_progress}");
 	 		   $("#activity"+i).val("${tempobj.activity_name}");		   		

 		  i++;
        </c:forEach> 
        
        	if("${sessionScope.USER_ROLE_NAME}"!='IT Admin')
        	{
		        $('#getForm :input').prop('disabled', true);
			    $('#btnCancel').prop('disabled', false);
			    $('#tleName').html('Update Quarterly Plan');
			    $('#btnAdd').html('Update');
        	}
        	else
       		{
		        $('#getForm :input').prop('disabled', false);
			    $('#btnCancel').prop('disabled', false);
			    $('#tleName').html('Update Quarterly Plan');
			    $('#btnAdd').html('Update');       		
       		}
	}  
   	
   	function addRevision()
   	{
		var rowLr=$("#app_com_tableRevision tbody tr").length;
		for(var k=0;k<rowLr;k++)
		{
			if($("#revisionno"+k).val()=="")
			{
				$("#errorRevision").html("Please enter Revision No.");
				return false;
			}
			else
			{
				$("#errorRevision").html("");
			}
			if($("#tdc_revisiondate"+k).val()=="")
			{
				$("#errorRevision").html("Please enter Revision Date.");
				return false;			
			}
			else
			{
				$("#errorRevision").html("");
			}			
		}
		$("#revised_tdc").val($("#tdc_revisiondate"+$("#rowNoRevision").val()).val());
		$("#revisedtdc").html("");
		 $("#tdcModal").modal("close");
   	}
   	
   	function addTdc()
   	{
   		$("#tdcModal").modal("open"); 
   	}
    
    
    $(document).ready(function() {
    	$('.modal').modal();
 	   $("[data-length]").each(function(i,val){
 	    	$('#'+val.id).characterCounter();
 	    });
        $(".num").keypress(function() {
            if ($(this).val().length == $(this).attr("maxlength")) {
                return false;
            }
        });
        var today=new Date();
        $('#tdc_calendar').datepicker({                   
	    	  minDate: new Date(today.getTime() + 24 * 60 * 60 * 1000),
	    	  format:'dd-mm-yyyy',
	    	  //perform click event on done button
	    	  onSelect: function () {
	    	  }
	        });       
    });
 	 
 	 
    $(document).ready(function () {
        $('select:not(.searchable)').formSelect();
        $( ".searchable" ).each(function( index,val ) {
 $( this ).select2({                placeholder:      $( this ).attr('placeholder')       });
 });
        
        var DateShort=new Date().getFullYear();
        	DateShort=DateShort.toString();
        	DateShort=DateShort.substring(2,4);

        $('#period').append('<option value="1st January,'+DateShort+'  - 31st March,'+DateShort+'">1st January,'+DateShort+'  - 31st March,'+DateShort+'</option>');
        $('#period').append('<option value="1st April,'+DateShort+'  - 30th June,'+DateShort+'">1st April,'+DateShort+'  - 30th June,'+DateShort+'</option>'); 
        $('#period').append('<option value="1st July,'+DateShort+'  - 30th September,'+DateShort+'">1st July,'+DateShort+'  - 30th September,'+DateShort+'</option>'); 
        $('#period').append('<option value="1st October,'+DateShort+'  - 31st December,'+DateShort+'">1st October,'+DateShort+'  - 31st December,'+DateShort+'</option>'); 
 	    $("#period").val("${FortnightPlan[0].period}");
 	    
 	   
 	   
 	   	if("${(fn:length(FortnightPlan))}">0)
 		{
 	   		var i=0;	  			 
 	 		<c:forEach var="tempobj" items="${FortnightPlan}">
 	 			getFortnights(i);
 	 			var SplitStr="${tempobj.fortnight_date}";
 	 			SplitStr=SplitStr.toString();
 	 			SplitStr=SplitStr.replace(/,/g, '__');
  	 		   $("#fortnight"+i).val(SplitStr);
 	 		  i++;
 	        </c:forEach>
 	       getTDCRevisions();
 		} 	   
    });
    
    function addTDCRow()
    {

        var rowNo = $("#rowNoRevision").val();
        var rowNo1 = $("#sNoRevision").val();
        var rNo = Number(rowNo)+1;
        var sNoRevision = Number(rowNo1)+1;
        var rowLr=Number(rNo)+1;
        var html = '<tr id="actionRevisionStRow' + rNo + '"><td><input id="revisionno' + rNo + '" name="revisionno" type="text" value="R'+ rowLr + '"></td>'

           +'<td data-head="Fortnight" class="input-field">'
           +'<input id="tdc_revisiondate' + rNo + '" name="tdc_revisiondate" type="text" class="validate datepicker" value=""></td>'
           +'<td class="input-field mobile_btn_close"><button type="button" onclick="removeRevisionStActions(' + rNo + ');" class="btn waves-effect waves-light red t-c remove"><i class="fa fa-close"></i></button></td>'
           +'</tr>';
    
        $('#stTDCBody').append(html);
        $("#rowNoRevision").val(rNo);
        var rowCount = $("#app_com_tableRevision tbody tr").length;
        $("#sNoRevision").val(rowCount);    
        $('.searchable').select2();
    	
    }
    
    function removeStActions(rowNo)
    {
    	$("#actionStRow"+rowNo).remove();
    }
    
    
    function removeRevisionStActions(rowNoRevision)
    {
    	$("#actionRevisionStRow"+rowNoRevision).remove();
    }   
  
    function getTDCRevisions()
    {
	        	var myParams = { fortnightly_plan_id:$("#fortnightly_plan_id").val()};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getTDCRevisions",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) 
	                    {
                				$("#revisionno0").val(data[0]["revision_no"]);
                				$("#tdc_revisiondate0").val(data[0]["tdc_date"]);
                			
                        		for(var i=1;i<data.length;i++)
                        		{
                        			addTDCRow();
                        		}
                        		for(var i=1;i<data.length;i++)
                        		{
                        			$("#revisionno"+i).val(data[i]["revision_no"]);
                        			$("#tdc_revisiondate"+i).val(data[i]["tdc_date"]);
                        		}
                        		$('#revisedtdc').html("");
                        		$("#revised_tdc").val($("#tdc_revisiondate"+$("#rowNoRevision").val()).val());

	                    }
	                }
	            });
        
    }     
    
    
    function addFortnightQuarterly()
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
			
			if($("#period").val()=="")
			{
   				$("#periodError").html("Period Required.");
   				$("#period").css('border-color', 'red');
   				return false;
			}
			else
			{
				$("#periodError").html("");
				$("#period").css('border-color', '');	   				
			}
			
			if($("#structure").val()=="")
			{
   				$("#structureError").html("Structure Required.");
   				$("#structure").css('border-color', 'red');
   				return false;
			}
			else
			{
				$("#structureError").html("");
				$("#structure").css('border-color', '');	   				
			}
			if($("#item").val()=="")
			{
   				$("#itemError").html("Item Required.");
   				$("#item").css('border-color', 'red');
   				return false;
			}
			else
			{
				$("#itemError").html("");
				$("#item").css('border-color', '');	   				
			}
			
			
			
			if($("#criticality").val()=="")
			{
   				$("#criticalityError").html("Select Criticality");
   				$("#criticality").css('border-color', 'red');
   				return false;
			}
			else
			{
				$("#criticalityError").html("");
				$("#criticality").css('border-color', '');	   				
			} 
			
			
			if($("#scope_of_work_quarterly").val()=="")
			{
   				$("#scope_of_work_quarterlyError").html("Scope of Work Required.");
   				$("#scope_of_work_quarterly").css('border-color', 'red');
   				return false;
			}
			else
			{
				$("#scope_of_work_quarterlyError").html("");
				$("#scope_of_work_quarterly").css('border-color', '');	   				
			}
			
			if($("#cumulative_progress").val()=="")
			{
   				$("#cumulative_progressError").html("Cumulative Progress Required.");
   				$("#cumulative_progress").css('border-color', 'red');
   				return false;
			}
			else
			{
				$("#cumulative_progressError").html("");
				$("#cumulative_progress").css('border-color', '');	   				
			}			
				
			
		   	   for(var r=0;r<$('#app_com_table tbody tr').length;r++)
			   {
			   			if($("#fortnight"+r).val()=="")
			   			{
			   				$("#errormsg").html("Select fortnight");
			   				$("#fortnight"+r).css('border-color', 'red');
			   				return false;
			   			}
			   			else
		   				{
			   				$("#errormsg").html("");
			   				$("#fortnight"+r).css('border-color', '');	   				
		   				}
						
						if($("#units"+r).val()=="")
						{
			   				$("#errormsg").html("Units Required.");
			   				$("#units"+r).css('border-color', 'red');
			   				return false;
						}
						else
						{
							$("#errormsg").html("");
							$("#units"+r).css('border-color', '');	   				
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
			   }
			
			
			
			
			
    	document.getElementById('getForm').submit();
    }
    
    var DateShort=new Date().getFullYear();
	DateShort=DateShort.toString();
	DateShort=DateShort.substring(2,4);   
    
    
    var fortnightArray=
   	 [
       	["1st January__"+DateShort+" - 15th January__"+DateShort+"","16th January__"+DateShort+" - 31st January__"+DateShort+"","1st February__"+DateShort+" - 15th February__"+DateShort+"","16th February__"+DateShort+" - 28th February__"+DateShort+"","1st March__"+DateShort+" - 15th March__"+DateShort+"","16th March__"+DateShort+" - 31st March__"+DateShort+""],
       	["1st April__"+DateShort+" - 15th April__"+DateShort+"","16th April__"+DateShort+" - 31st April__"+DateShort+"","1st May__"+DateShort+" - 15th May__"+DateShort+"","16th May__"+DateShort+" - 31st May__"+DateShort+"","1st June__"+DateShort+" - 15th June__"+DateShort+"","16th June__"+DateShort+" - 30th June__"+DateShort+""],
       	["1st July__"+DateShort+" - 15th July__"+DateShort+"","16th July__"+DateShort+" - 31st July__"+DateShort+"","1st August__"+DateShort+" - 15th August__"+DateShort+"","16th August__"+DateShort+" - 31st August__"+DateShort+"","1st September__"+DateShort+" - 15th September__"+DateShort+"","16th September__"+DateShort+" - 30th September__"+DateShort+""],
       	["1st October__"+DateShort+" - 15th October__"+DateShort+"","16th October__"+DateShort+" - 31st October__"+DateShort+"","1st November__"+DateShort+" - 15th November__"+DateShort+"","16th November__"+DateShort+" - 30th November__"+DateShort+"","1st December__"+DateShort+" - 15th December__"+DateShort+"","16th December__"+DateShort+" - 31st December__"+DateShort+""]
        ];    
        
        function getFortnights(Iteration)
        {
        
        		if($("#period").val()!="")
        			{
			        	$('#fortnight'+Iteration+' option').not(':first').remove();
			        	var periodIndex=$("select[name='period'] option:selected").index();
			        	
			        	periodIndex=periodIndex-1;
			
			        	    for (var j = 0; j<fortnightArray[periodIndex].length; j++) 
			        	    {
			        	    		var fortnights=fortnightArray[periodIndex][j].replace(/__/g, ',');
			     					$('#fortnight'+Iteration).append('<option value="'+fortnightArray[periodIndex][j]+'">'+fortnights+'</option>');
			        	    }
        	    
        			}

        	
  		   
        }
       



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
 
 function daysInMonth(month, year) {
	    return new Date(year, month, 0).getDate();
	} 

   function addStRow(){
        var rowNo = $("#rowNo").val();
        var rowNo1 = $("#sNo").val();
        var rNo = Number(rowNo)+1;
        var sNo = Number(rowNo1)+1;
        var html = '<tr id="actionStRow' + rNo + '"><td>' + (rNo+1) + '</td>'

           +'<td data-head="Fortnight" class="input-field">'
           +'<select id="fortnight' + rNo + '" class="fortnight searchable"  name="fortnight" value="">' 
           +'<option value="">Select</option></select></td>'

           +'<td data-head="Activity" class="input-field">'
           +'<textarea id="activity' + rNo +'" name="activity" class="pmis-textarea pdr4em w85 my-valid-class" data-length="200" maxlength="200"></textarea> </td><td><input type="text" id="units' + rNo + '" name="units"  data-length="50" maxlength="50"></td>'

           +'<td class="input-field mobile_btn_close"><button type="button" onclick="removeStActions(' + rNo + ');" class="btn waves-effect waves-light red t-c remove"><i class="fa fa-close"></i></button></td>'
           +'</tr>';
    
        $('#stBody').append(html);
        $("#rowNo").val(rNo);
        getFortnights(rNo);
        var rowCount = $("#stBody tr").length;
        $("#sNo").val(rowCount);    
        $('#activity'+rNo).characterCounter();
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