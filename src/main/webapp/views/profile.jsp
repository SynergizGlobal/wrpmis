<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile - User Details - PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
  <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">   
  <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
  <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
  <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
  <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
  
       
     <style>
     .dataTables_filter label input[type="search"]::placeholder {
  color: #6C587B !important;
  color: rgb(0 122 122 / 80%) !important;
}

.dataTables_filter label {
  color: #fff;
  font-size:.1px;
}

.right-btns .fa {
  position: relative;
  top: -35px;
  right: .75rem;
}

.right-btns .fa:hover {
  color: #007A7A;
}

.right-btns .fa+.fa {
  right: 0 !important;
}
     .theme-change {
		  position: fixed;
		  z-index: 2;
		  right: -20px;
		  top: 60px;
		  border: 0;
		  cursor: pointer;
		  font-size: 1.5rem;
		  transition: all 1s ease-in-out;
		}
		.theme-change .fa-moon-o {
		    color: white;
		}
		@media (hover: hover) and (pointer: fine){
			.theme-change:hover {
			    transform: translateX(-20px);
			}		
		}
      .card-title {
            background-color: #DFE6F6;
        }
       .text-capitalize{
       		text-transform:capitalize;
       }
		.mdl-grid.dt-table >div,
		.table-inside {
		  width: 100%;
		  overflow-x: auto;
		}
       .table-plus-btn{
       	    text-align: center;
    		margin-top: .5rem;
       }
        .custom-margin{
        	margin: 1.35rem 0 2.5rem 0;
        }
	    table:not(.datepicker-table) thead tr {
	        background-color: #2E58AD;
	        background-color: #007a7a;
	     }
	     .profile_info table tbody tr td:first-of-type{
	     	width:30%;
	     }
	     table:not(.datepicker-table) td{
	        word-break: break-word;
	    	word-wrap: break-word;
	   		white-space: initial;  
	     }
	   /*   .mdl-data-table tr td{	     
	   		text-align:center !important;  
	   	}*/
	     thead th{
   			color: #fff !important;
	     } 
        .card-title.headbg {
            padding: 15px;
            margin: -24px;
            text-align: center;
        }
		.m-1 {
		    margin: .5rem auto;
		}
		.mt-20px{
			margin-top:20px;
		}
		.row.no-mar{margin-bottom:0;}
		div.center-align.m-1 button.bg-m, div.center-align.m-1 button.bg-s{width:auto;}
        /* left side code  */
        .profile_photo img {
            width: 250px;
            height: 250px;
            border-radius: 50%;
                        
            width: 50px;
		    height: 50px;
		    border-radius: 50%;
		    position: absolute;
		    right: -6px;
		    top: -10px;
        }
        .profile_photo .hideOrShow{
        	position:relative;
        }
       .profile_name {
		    /* font-size: 2rem;
		    margin: 20px 0;
		    font-weight: bold;
		    text-transform: uppercase;
		    line-height: 1; */
		    display:inline-block;
		} 

        .profile_designation {
            font-size: 1.5rem;
            font-weight: 400;
            margin: 20px 0;
            text-transform: capitalize;
        }

        .profile_role {
            font-size: 1rem;
            font-weight: 300;
            text-transform: capitalize;
        }
		.disp-inflex{
			display:inline-flex;
		}
		.disp-inflex.hidden{
			display:flex;
		}
        /* right side code  */
        .profile_info {
            text-align: left;
            /* padding: 20px; */
        }
       
        .card .card {
            -webkit-box-shadow: 0 8px 17px 2px rgba(0, 0, 0, 0.14), 0 3px 14px 2px rgba(0, 0, 0, 0.12), 0 5px 5px -3px rgba(0, 0, 0, 0.2);
            box-shadow: 0 8px 17px 2px rgba(0, 0, 0, 0.14), 0 3px 14px 2px rgba(0, 0, 0, 0.12), 0 5px 5px -3px rgba(0, 0, 0, 0.2);
        }
		
		.dataTables_filter{
			max-height:40px;
		}
		.dataTables_wrapper tbody tr >td,
		.dataTables_wrapper  thead tr >th{
			text-align:left !important;
		}
		.dataTables_wrapper tbody tr >td> .btn{
			height: 1.7rem;
		    line-height: 1.75rem;
		    padding: 0 0.5rem;
		    font-size: .97rem;
		}		
		.dataTables_wrapper tbody tr >td.last-column,
		.dataTables_wrapper thead tr >th:last-of-type {
			padding-left:5px;
			padding-right:5px;
			max-width:80px !important;
			min-width:70px;
		}
		.dataTables_wrapper tbody tr >td.no-pad,
		.dataTables_wrapper thead tr >th.no-pad{
			padding:0;
		}
		.dataTables_wrapper tbody tr >td>.btn i{
		    font-size: .9rem;
		}
		.dataTables_wrapper .mdl-button {
		    min-width: inherit;
		    text-transform: capitalize;
		    padding: 0 8px;
		}
		.dataTables_wrapper .dataTables_length label{
			position:relative;
			top:.6rem;
		}
		.dataTables_wrapper select.form-control.input-sm {
			display: inline-block;
		    width: auto;
		    height: auto;
		}
		.dataTables_scrollBody{
			height:auto !important;
		}
		.mdl-button--raised.mdl-button--colored,
		.mdl-button--raised.mdl-button--colored:hover{
			background-color:#007a7a;
		}
		td.fw-120{
			width: 140px !important;
			max-width:140px !important;
			padding-right:5px;
			padding-left:14px !important;
		}
        @media only screen and (max-width: 769px) {
			.m-no-pad{
				padding-left:0 !important;
				padding-right:0 !important;
			}
			.m-no-pad > .card-title.headbg{
				margin: -24px 0 8px;
			} 
			.dataTables_filter label input {
			    width: 92% !important;
			}
			.dataTables_length{
				text-align:center;
			}
			.mdl-grid:not(.dt-table){
				justify-content:center;
			}
        }  
        @media only screen and (max-width: 600px) {

            .dataTables_info,
            .pagination {
                text-align: center;
            }
        }  
        .error-msg label{color:green!important;}   
		.profile_name .error-msg {
		    text-transform: capitalize;
		    color: red;
		    text-align: left;
		    font-size: 13px;
		    font-weight: 400;
		}
		
        .main .left i{
        	margin:0 5px;
        	transition:color .3s linear;
        }
        .main i:hover{
        	color:#007A7A;
        }
        .main .hidden{
        	display:none;
        }
        .bg-m{
	    	background-color:#007a7a;
	    	text-transform:Capitalize;    
	    }
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		input[type=number] {
		  -moz-appearance: textfield;
		}
		.no-sort.sorting_asc:before,
		.no-sort.sorting_asc:after{
			opacity:0 !important;
			content:'' !important;
		}
		.error-msg {
		    color: red;
		    font-size: .9rem;
		}	
		select {
     display: block; 
}	
		
	</style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

    <div class="row">
        <div class="col s12 m12">
            <div class="card custom-margin">
                <div class="card-content"> 
                <form action="<%=request.getContextPath() %>/update-profile" method="POST" id="profileForm" name="profileForm" class="form-horizontal" role="form" enctype="multipart/form-data">
                	<span class="card-title headbg main">
                		<div class="profile_name">
                         	 <span class="hideOrShow">${ userDetails.user_id } -${ userDetails.designation } - ${ userDetails.user_name } </span>
                         	 <span class="hideOrShow input-field hidden disp-inflex">${ userDetails.user_id } &nbsp; 
                         	 	<input name="user_name" id="user_name" type="text" class="validate"  value="${ userDetails.user_name }"/> 
                         	 	<span id="user_nameError" class="error-msg"></span>
                         	 </span>
                         </div>  
                		<span class="left">
                		<a class="btn bg-m editing" onclick="toggleEditing()">Edit</a> 
                		<i class="fa fa-save saving hidden" onclick='profileFormSubmit()'></i>
                		<i class="fa fa-close closing hidden" onclick="toggleEditing()"></i>
                		</span>
                		<span class="right">
               			  <div class="profile_photo">
                              <span class="hideOrShow">
                              	<img src="<%=CommonConstants2.USER_IMAGES %>${userDetails.user_image}" onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';" >
                              </span> 
                              <span class="hideOrShow hidden"> 
								<div class="file-field input-field">
									<div class="btn bg-m">
										<span>Change Image</span> <input type="file" name="userImageFile" id="userImageFile" accept='image/*'>
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" name="user_image" type="text" value="${ userDetails.user_image }">
									</div>
								</div> 
							 </span>
							</div>
                		</span>
                	</span>
                	 <c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
					</c:if>
					<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
				  </c:if>
                    <div class="row">                   
                        <%-- <div class="col m6 l4 s12 center-align">
                            <div class="card">
                                <div class="card-content">
                                   <!--  <span class="card-title headbg">Basic</span> -->
                                    <div class="profile_photo">
                                        <span class="hideOrShow">
                                        	<img src="<%=CommonConstants2.USER_IMAGES %>${userDetails.user_image}" onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';" >
                                        </span> 
                                        <span class="hideOrShow hidden"> <!--  <form> -->
											<div class="file-field input-field">
												<div class="btn bg-m">
													<span>Change Image</span> <input type="file" name="userImageFile" id="userImageFile" 
														accept='image/*'>
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" name="user_image" type="text" value="${ userDetails.user_image }">
												</div>
											</div> <!-- </form> -->
										</span>
									</div> 
                                    <div class="profile_name">
                                    	 <span class="hideOrShow">${ userDetails.user_name }</span>
                                    	 <span class="hideOrShow input-field hidden">
                                    	 	<input name="user_name" id="user_name" type="text" class="validate"  value="${ userDetails.user_name }"/>
                                    	 	<span id="user_nameError" class="error-msg"></span>
                                    	 </span>
                                    </div> 
                                    <div class="profile_designation">${ userDetails.designation }
                                        <span class="profile_role">( ${ userDetails.user_role_name_fk } )</span>
                                    </div> 
                                </div>
                            </div>
                        </div>  --%>
                        <input type="hidden"  name="user_id" id="user_id" value="${ userDetails.user_id }" />
                        <div class="col m4">
                            <div class="card">
                                <div class="card-content">
                                    <span class="card-title headbg">User Details</span>
                                    <div class="profile_info">
                                        <div class="row">                                        
											<table>
											    <tbody>
											        <tr>
											            <td>User Role</td>
											            <td>: &nbsp; <%-- ${ userDetails.user_id } -  --%>${ userDetails.user_role_name_fk }</td>
											        </tr>
											        <tr>
											            <td>User Type</td>
											            <td>: &nbsp; ${ userDetails.user_type_fk }</td>
											        </tr>
											         <tr>
											            <td>Email</td>
											            <td> 
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.email_id }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="email_id" id="email_id" type="email" class="validate" value="${ userDetails.email_id }">
											            		<span id="email_idError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Department</td>
											            <td>: &nbsp; ${ userDetails.department_name }</td>
											        </tr>
											        <tr>
											            <td>Reporting To</td>
											            <td>: &nbsp; ${ userDetails.reporting_to_designation }</td>
											            <%-- <td>: &nbsp; ${ userDetails.reporting_to_name } <c:if test="${not empty userDetails.designation }">(${ userDetails.reporting_to_designation }) </c:if> </td> --%>
											        </tr>
											        <tr>
											            <td>Mobile No</td>
											            <td>											            	
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.mobile_number }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="mobile_number" id="mobile_number" type="number" class="validate" value="${ userDetails.mobile_number }">
											            		<span id="mobile_numberError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Personal Contact Number</td>
											            <td>											            
											           		<span class="hideOrShow">: &nbsp; ${ userDetails.personal_contact_number }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="personal_contact_number" id="personal_contact_number" type="number" class="validate" value="${ userDetails.personal_contact_number }">
											            		<span id="personal_contact_numberError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Land line</td>
											            <td>
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.landline }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="landline" id="landline" type="number" class="validate" value="${ userDetails.landline }">
											            		<span id="landlineError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Extension</td>
											            <td>
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.extension }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="extension" id="extension" type="number" class="validate" value="${ userDetails.extension }">
											            		<span id="extensionError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											         <tr>
											            <td>PMIS Key</td>
											            <td>: &nbsp; ${ userDetails.pmis_key_fk }</td>
											        </tr>
											     											        
											    </tbody>
											</table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="modulesCnt">
                         <div class="col m8">
                            <div class="card row">
                                <div class="card-content">
                                    <span class="card-title headbg">Leave Responsibility</span>
                                    <div class="row" style="padding-left:80px;padding-right:80px;">
									    <div class="col s12" style="text-align:center;">
									        <form action="">
									            <div id="StatusMsg"  style="text-align:center;color:green;"></div>
									           <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> <div class="row no-mar" id="itadminDiv" style="text-align:left;"><div class="input-field col s4">Apply for</div></div></c:if>
									            <div id="leaveResponsibleDiv">
									            <div id="datesDiv" class="row no-mar">
									             <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">	<div class="input-field col s4">
														<select name="apply_for" id="apply_for" class="validate-dropdown searchable" onchange="getResponsiblePersonUsers();">
					                                        <option value="0">Self</option>
					                                         <c:forEach var="obj" items="${usersList }">
					                                      	   <option  value= "${obj.user_id}">${obj.designation}-${obj.user_name}</option>
					                                         </c:forEach>												                    
                                         				</select>									            	
									            	</div>
									            </c:if>
									            	<div class="input-field col s4">
										                <input type="text" class="validate datepicker" id="from_date" placeholder="From Date">
										                <button type="button" id="from_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
										                <span id="from_dateError" class="error-msg"></span>
										            </div>
										            <div class="input-field col s4">
										                <input type="text" class="validate datepicker" id="to_date" placeholder="To Date">
										                <button type="button" id="to_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
										                <span id="to_dateError" class="error-msg"></span>
										            </div>
									            </div>
									            <input type="hidden" name="user_leave_id" id="user_leave_id" value="0">
									            <br>
									            <div class="row no-mar" id="responsibleDiv">
												    <h6 class="center-align">Assign Responsibility</h6>
												    <div class="table-inside">   
													    <table class="mdl-data-table mobile_responsible_table" id="responsibility_table">
													        <thead>
													            <tr>
													                <th style="text-align:center;width:45%">Module</th>
													                <th style="text-align:center;width:45%">Responsible Person</th> 
													                <th>Action</th>
													            </tr>
													        </thead>
													        <tbody id="responsibilityBody">
													            <tr id="tableRow0">
													                <td data-head="Module" class="input-field">
									                                    <select class="searchable" id="module0" name="modules" onChange="selectModule(this.value);">
									                                        <option value="">All</option>
									                                         <c:forEach var="obj" items="${modulesList }">
									                                      	    <option value= "${ obj.module_name_fk}" <c:if test="${formDetails.module_name_fk eq obj.module_name_fk}">selected</c:if>>${obj.module_name_fk}</option>
									                                          </c:forEach>
									                                    </select>
													                </td>
													                <td data-head="Responsible Person" class="input-field">
													                    <select name="responsible_person" id="responsible_person0" class="validate-dropdown searchable" onChange="selectModule(this.value);">
                                        <option value=""></option>
                                         <c:forEach var="obj" items="${usersList }">
                                      	   <option  value= "${obj.user_id}">${obj.designation}-${obj.user_name}</option>
                                         </c:forEach>												                    </select>
													                </td>
													                <td class="mobile_btn_close">
													                    <a onclick="removeRow('0');" class="btn red">
													                        <i class="fa fa-close"></i></a>
													                </td>
													            </tr>
													        </tbody>
													    </table>
													    <span id="tableErrorRemove" class="error-msg"></span>
													    
													    <div class="table-plus-btn" id="addBtnRow">
													    	<input type="hidden" id="rowNo" name="rowNo" value="0" />
													        <a type="button" class="btn waves-effect waves-light bg-m t-c add-align" onclick="addNewRow()"> <i
													                class="fa fa-plus"></i>
													        </a>
													    </div>
													  </div>
												</div>
<div class="row" id="btnDiv">
	                                    <div class="col s4 m6 input-field" style="text-align:right;">
	                                        <button type="button" class="btn btn-primary" id="btnCreate" onclick="createResponsibility();"> Create</button>
	                                    </div>			                        
			                            <div class="col s4 m6 input-field" style="text-align:left;;">
												<button type="button" class="btn btn-danger" id="btnReset" style="background-color:#f44336;" onclick="clearFilters();">Reset</button>
			                             </div>
			                        </div>
		                        
					                            </div>
									        </form>
									    </div>
									</div>
                            </div>
                            
	                           
                            
                        </div> 
                        <div class="row card">
                        
 							<div class="card-content">
					        	<span class="card-title headbg">Past Leaves</span>

					                <table id="past-leaves-table" class="mdl-data-table" style="padding:0px;">
					                    <thead>
					                        <tr>
					                            <th class="fw-120">From Date</th>
					                            <th class="fw-120">To Date</th>
					                            <th class="fw-120">Module -> Responsible Person</th>
					                            <th class="fw-120">Action</th>
					                        </tr>
					                    </thead>
					                    <tbody>

					                    </tbody>
					                </table>

							</div>                        
                        </div>
                    </div>
                  </form>
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
	
	
  <!-- footer included -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
    		
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>
  <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	
	  <script>
     $(document).ready(function () {
    	 
    	// $('#module0 option:eq(0)').prop('selected',true);
    	 $('.searchable').select2();
    	 
    	 var date = new Date();
    	 
    	 $("#from_date").datepicker({
        	 format:'dd-mm-yyyy',
           	// minDate: new Date(date.getFullYear(), date.getMonth(), (date.getDate() + 1)),
           	 onSelect: function () {
           		    $('.confirmation-btns .datepicker-done').click();
           			$("#from_dateError").html("");  
           				if($("#to_date").val()!="")
           				{
           					var d1=$("#from_date").val().split("-").reverse().join("-");
           					var d2=$("#to_date").val().split("-").reverse().join("-");
           						if(new Date(d1)>new Date(d2))
           						{
           							$("#from_dateError").html("From date should not be greater than To date");  
           							return false;
           						}
           						else
      							{
      								$("#to_dateError").html("");  
      							}          						
           				}
    	    	 }
        });  
    	 
    	 $("#to_date").datepicker({
        	 format:'dd-mm-yyyy',
        	// minDate: new Date(date.getFullYear(), date.getMonth(), (date.getDate() + 1)),
           	 onSelect: function () 
           	 {
           		$('.confirmation-btns .datepicker-done').click();
           		$("#to_dateError").html("");
   				if($("#from_date").val()!="")
   				{
   					var d1=$("#from_date").val().split("-").reverse().join("-");
   					var d2=$("#to_date").val().split("-").reverse().join("-");
   					
   						if(new Date(d2)<new Date(d1))
   						{
   							$("#to_dateError").html("To date should not be less than From date");  
   							return false;
   						}
   						else
  							{
  								$("#from_dateError").html("");  
  							}
   				}          		
    	    }
        });   	 
    	
    	 getPastLeaves();
    	 $('#select2-module0-container').html("All");

    	 $("#addBtnRow").hide();  	 
   	 		if(document.getElementById('past-leaves-table').rows.length<=1)
    		 {
    		 	$('#past-leaves-table').append("<tr><td colspan='4' style='text-align:center;font-size:20px;color:#000000;'>No Past Leaves</td></tr>");
    		 } 
   	 		
   	 		getModulesCount();
        	
     });
     function removeLeave(leave_id,row)
     {
    	 if (confirm("Are you sure you want to delete this leave responsibility")) 
    	 {  
    		 if(deleteLeaveResponsibility(leave_id,row)==true)
   			 {
    			 $("#StatusMsg").html("Leave Responsibility deleted.");
    			 getPastLeaves();
    			 
					$('#select2-module0-container').html("All");
					
	        		 if(($("#modulesCnt").val()==Number($("#rowNo").val())+1 || $("#module0").val()==""))
	              	 {
	               		 $("#addBtnRow").hide(); 
	              	 }
	               	 else
	              	 {
	              		$("#addBtnRow").show(); 
	              	 }	    			 
   			 }
    	 }  
     }
     function getResponsiblePersonUsers()
     {
    	 if($("#apply_for").val()!=0)
   		 {
   	 		$("#responsibilityBody").find("tr:gt(0)").remove();
    	 
   	 		$("#rowNo").val("0");
   	 		$("#addBtnRow").hide();
   	 		getUsers();
   		 }
   	 	else
	 	{
   	 		$("#responsibilityBody").find("tr:gt(0)").remove();
	   	 	$("#responsible_person0").empty();
	       	 $('select[name="responsible_person"]').val("");
	    	 $('#select2-responsible_person0-container').html("");
    	 
            <c:forEach var="obj" items="${usersList }">
            		$("#responsible_person0").append('<option  value= "${obj.user_id}">${obj.designation}-${obj.user_name}</option>');
        	</c:forEach>	  	 		
	 	}
     }
     
     function getNewRowUsers(index)
     {
    	 var myParams = {user_id:$("#apply_for").val()};
    	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getResponsiblePersonUsers",
  			type:"POST",
  			data:myParams,cache: false,async:false,
  			success : function(data)
  			{    	
				if(data != null && data != '' && data.length > 0)
				{ 
					$("#responsible_person"+index).append('<option value= ""></option>');
					
	        		$.each(data,function(key,val){
	        					
	        			
                        $("#responsible_person"+index).append('<option  value= "'+val.user_id+'">'+val.designation+'-'+val.user_name+'</option>');

	                   		                       
					});
        		
				}
			
			}
    	 });   	    	 
     }
     
     function getUsers()
     {
    	 $("#module0").val("");
    	 $('#select2-module0-container').html("All");
    	 $("#responsible_person0").empty();
    	 $('select[name="responsible_person"]').val("");
    	 $('#select2-responsible_person0-container').html("");
    	 
    	 var myParams = {user_id:$("#apply_for").val()};
    	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getResponsiblePersonUsers",
  			type:"POST",
  			data:myParams,cache: false,async:false,
  			success : function(data)
  			{    	
				if(data != null && data != '' && data.length > 0)
				{ 
					$("#responsible_person0").append('<option  value= ""></option>');
					
	        		$.each(data,function(key,val){
	        					
	        			
                        $("#responsible_person0").append('<option  value= "'+val.user_id+'">'+val.designation+'-'+val.user_name+'</option>');

	                   		                       
					});
        		
				}
			
			}
    	 });   	 
     }
     
     function deleteLeaveResponsibility(leave_id,row)
     {
 	    var myParams = {user_leave_id:leave_id};
    	var bool = false;
       	 $.ajax({
             url: "<%=request.getContextPath()%>/ajax/deleteLeaveResponsibility",
             type:"POST",
             data:myParams,cache: false,async:false,
             success: function (data) 
             {
            	 if (data == true) 
            	 {
                     bool = true;
                 }
             }
         });
       	return trueOrFalse(bool);
     }    
     
     function getLeave(leave_id,row) 
     {
	     	$("#from_dateError").html("");
	    	$("#to_dateError").html("");
	    	$("#StatusMsg").html("");
	    	$("#tableErrorRemove").html("");
	    	 $('#select2-module0-container').html("");
 	    	 $('#select2-responsible_person0-container').html("");      	

    	     $("#addBtnRow").show();  	 
    	     var rowNo=$(row).closest('td').parent()[0].sectionRowIndex;
    	 
    	 	 $("#rowNo").val("0");
    	 	 $("#user_leave_id").val(leave_id);
    	 	 
    	 	 $("#btnCreate").html("Update");
    	 	 $("#btnReset").html("Cancel");
    		 rowNo=rowNo+1;
    		 var myTab = document.getElementById('past-leaves-table');

             var objCells = myTab.rows.item(rowNo).cells;
			 var FD=objCells.item(0).innerHTML;
			 FD=FD.split("-").reverse().join("-");
			 var TD=objCells.item(1).innerHTML;
			 TD=TD.split("-").reverse().join("-");			 	 
             $("#from_date").val(FD);
             $("#to_date").val(TD);
             
             var ModResp = objCells.item(2).innerHTML;
             var spltBr=ModResp.toString();
             var spltBrEach=spltBr.split("<br>");
             var totalLength=spltBrEach.length-1;
             
             var resPersonsArray=new Array();
             
             for(var r=0;r<totalLength;r++)
             {
               	 var eachmodule1=spltBrEach[r].toString();
            	 var eachrow1=eachmodule1.split("-&gt;");
            	 var replaceStr1=eachrow1[1].replaceAll('&amp;','&');
            	 
            	 	if(resPersonsArray.indexOf(replaceStr1)==-1)
            		 {
            	 		resPersonsArray.push(replaceStr1);
            		 }
             }
 	 	 	 $("#responsibilityBody").find("tr:gt(0)").remove();
             if(resPersonsArray.length==1 && totalLength!=1)
           	 {
            		$("#module0").val("");
            		$("#responsible_person0 option:contains('"+resPersonsArray[0]+"')").attr('selected', true);
            		$("#responsible_person0 option:contains('"+resPersonsArray[0]+"')").prop('selected', true);
            		
            		$("#addBtnRow").hide();
           	 }
             else
             {
            	 $("#addBtnRow").show(); 
  	 	 	 
     	 	 	 
	             for(var r=0;r<totalLength;r++)
	             {
	            	 var eachmodule1=spltBrEach[r].toString();
	            	 var eachrow1=eachmodule1.split("-&gt;");
	            	 
				     var replaceStr=eachrow1[0].replaceAll('&amp;','&');
				     $("#module"+r).val(replaceStr);
				     var replaceStr1=eachrow1[1].replaceAll('&amp;','&');
	            	 $("#responsible_person"+r+" option:contains('"+replaceStr1+"')").attr('selected', true);
	            	 $("#responsible_person"+r+" option:contains('"+replaceStr1+"')").prop('selected', true);

					 addNewRow();
	             }
	             deleterow("responsibility_table");
            }
             
             $('.searchable').select2();
           	 if(($("#modulesCnt").val()==Number($("#rowNo").val())+1 || $("#module0").val()==""))
          	 {
           		 $("#addBtnRow").hide(); 
          	 }
           	 else
          	 {
          		$("#addBtnRow").show(); 
          	 }
            
 	 }  
     
     function deleterow(tableID) 
     {

   	    	if($("#rowNo").val()!=0)
   	    	{
   	    	    var table = document.getElementById(tableID);
   	    	    var rowCount = table.rows.length;

   	    	    table.deleteRow(rowCount -1);
   	    	    var rowVal=Number($("#rowNo").val())-1;   	    		
   	    		$("#rowNo").val(rowVal);
   	    	}
    	}
     
     function clearFilters()
     {
    	 $('#select2-apply_for-container').html("Self");
    	 $("#from_date").val("");
    	 $("#to_date").val("");
    	 $("#apply_for").val("0");
    	 $("#responsibilityBody").find("tr:gt(0)").remove();
    	 $('select[name="modules"]').val("");
    	 $('select[name="responsible_person"]').val("");
    	 $('#select2-responsible_person0-container').html("");
    	
     }
     
     
     function getModulesCount()
     {
     	var bool = false;
      	 $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getModulesCount",
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (data) 
            {
				$("#modulesCnt").val(data);
            }
        });    	 
     }
     
     function getPastLeaves()
     {
    	 $("#past-leaves-table").find("tr:gt(0)").remove();
    	 clearFilters();

      	var from_date = $("#from_date").val();
     	var to_date = $("#to_date").val();  
  	 	var myParams = {from_date : from_date, to_date : to_date};
  		$.ajax({url : "<%=request.getContextPath()%>/ajax/getPastLeaves",
 	    			type:"POST",
 	    			data:myParams,cache: false,async:false,
 	    			success : function(data)
 	    			{    	
  					if(data != null && data != '' && data.length > 0){    					
  	         		$.each(data,function(key,val)
  	         				{
  	         					var d1=new Date();
  	         					var d2 = new Date(val.from_date);
  	                         var actions = '';
  	                    
 	                    	//if(d2.getTime()>=d1.getTime() || new Date(val.from_date)>=d1)
 	                    	//{
 	     	                    actions = '<a href="javascript:void(0);"  onclick="getLeave('+val.user_leave_id+',this);" class="btn waves-effect waves-light bg-m t-c mob-btn" title="Edit"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="removeLeave('+val.user_leave_id+',this);" class="btn waves-effect waves-light bg-s t-c"><i class="fa fa-trash"></i></a>';    	                   	
 	                    	//}
 	                    	//else
	                    	//{
 	     	                   //actions = '<a href="javascript:void(0);"  onclick="getLeave('+val.user_leave_id+',this);" class="btn waves-effect waves-light bg-m t-c mob-btn" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
	                    	//}

  	                   	var rowArray = [];    	                  
  	                   	var user_leave_id = '';
  	                   	
  	                   	//rowArray.push( user_leave_id);
  	                   	
  	                   	var modulessplit=val.modules;
  	                        modulessplit=modulessplit.toString();
  	                    var responsiblepersonssplit=val.responsible_persons;
  	                   		responsiblepersonssplit=responsiblepersonssplit.toString();
  	                   	var SpltModulesArray=modulessplit.split(",");
  	                    var SpltResPersonsArray=responsiblepersonssplit.split(",");
  	                    var concatModuleResponsiblePersons="";
  	                    
 	                    	for(var f=0;f<SpltModulesArray.length;f++)
 	                    	{
 	                    		concatModuleResponsiblePersons=concatModuleResponsiblePersons+""+SpltModulesArray[f]+"->"+SpltResPersonsArray[f]+"<br>";
 	                    	}
  	                   	
/*  	                    	var index = concatModuleResponsiblePersons.lastIndexOf("<br>");
 	                    	concatModuleResponsiblePersons = concatModuleResponsiblePersons.substring(0, index); 
  	                   	
  	                   	rowArray.push($.trim(val.from_date));
  	                   	rowArray.push($.trim(val.to_date));
  	                   	rowArray.push($.trim(concatModuleResponsiblePersons));
  	                   	rowArray.push($.trim(actions));   	                   	
  	                   	
  	                    table.row.add(rowArray).draw( false );*/
  	                  	//clearFilters();
  	                  	
  	                  $('#past-leaves-table').append("<tr><td>"+val.from_date+"</td><td>"+val.to_date+"</td><td>"+concatModuleResponsiblePersons+"</td><td>"+actions+"</td></tr>");
  	                    		                       
  					});
  	         		$('#past-leaves-table').DataTable();
  	         		
  	         		$(".page-loader").hide();
  				}else{
  					$(".page-loader").hide();
  				}
  				
  			},error: function (jqXHR, exception) {
  				$(".page-loader").hide();
  	         	getErrorMessage(jqXHR, exception);
  	     }});
	 		if(document.getElementById('past-leaves-table').rows.length<=1)
   		 	{
   		 		$('#past-leaves-table').append("<tr><td colspan='4' style='text-align:center;font-size:22px;color:#000000;'>No Past Leaves</td></tr>");
   		 	} 		
  		
     }
     
     
     function selectModule(SelectedValue)
     {
    	 selectedModulesArray=[];
  	 	 if($("#module0").val()=="")
  		 {
  		 	$("#addBtnRow").hide();
  		 }
  	 	 else
 	 	 {
  	 		$("#addBtnRow").show(); 
 	 	 }
    	 
    	 
    	 $("#tableErrorRemove").html("");
   	 	 if(SelectedValue=="")
   		 {
   	 		$("#responsibilityBody").find("tr:gt(0)").remove();
   	 		$("#rowNo").val("0");
   	 		$("#addBtnRow").hide();
   		 }
   	 	else
	 	{
   	 		
   	 			if(document.getElementById('responsibility_table').rows.length>2)
   	 			{
		       		for(var f=0;f<document.getElementById('responsibility_table').rows.length-2;f++)
		       		{
		      	 		if(selectedModulesArray.indexOf($("#module"+f).val())==-1)
		  	 			{
		  	 				selectedModulesArray.push($("#module"+f).val());
		  	 			}
		       		}
					if(selectedModulesArray.indexOf(SelectedValue)!=-1)
					{
			    		$("#tableErrorRemove").html("You have already selected this module.");
			    		$("#module"+$("#rowNo").val()).val("");
			       	 	$('#select2-module'+$("#rowNo").val()+'-container').html("");
			    		return false;	 	 			
					} 
   	 			}
	 	}
   	 	 
   	  if(($("#modulesCnt").val()==Number($("#rowNo").val())+1 || $("#module0").val()==""))
      	 {
       		 $("#addBtnRow").hide(); 
      	 }
       	 else
      	 {
      		$("#addBtnRow").show(); 
      	 }  	 	 
     }
        function toggleEditing(){
        	$('.main .fa,.main .editing').toggleClass('hidden');
        	$('.hideOrShow').toggleClass('hidden');      	       	
        }
        var selectedModulesArray=new Array();

        function addNewRow()
        {
        	
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            
    		var html='<tr id="tableRow' + rNo + '"> <td data-head="Module" class="input-field">'+
            '<select name="modules" id="module' + rNo + '" class="validate-dropdown searchable" onChange="selectModule(this.value);"><option value=""></option>'+
            
			 <c:forEach var="obj" items="${modulesList }">
			    '<option value= "${ obj.module_name_fk}">${obj.module_name_fk}</option>'+
			  </c:forEach>                    
            
            '</select></td> <td data-head="Responsible Person" class="input-field">'
            +'<select name="responsible_person" id="responsible_person' + rNo + '" class="validate-dropdown searchable"><option value=""></option>'+
            <c:forEach var="obj" items="${usersList }">
       	   '<option  value= "${obj.user_id}">${obj.designation}-${obj.user_name}</option>'+
          </c:forEach>	                    
            '</select> </td> <td class="mobile_btn_close"> <a onclick="removeRow(' + rNo + ');" class="btn red"> <i class="fa fa-close"></i></a>'
            +'</td></tr>';	
			$('#responsibilityBody').append(html);            

            if(("${sessionScope.USER_ROLE_NAME}"!="IT Admin") || (("${sessionScope.USER_ROLE_NAME}"=="IT Admin" && $("#apply_for").val()==0)))
            {

        	}
            else
           	{
            	$("#responsible_person"+rNo).empty();
            	getNewRowUsers(rNo);
            	//getUsers();
           	}

    		$('.searchable').select2();
        	
    		
            $("#rowNo").val(rNo);          	
            if(($("#modulesCnt").val()==Number($("#rowNo").val())+1 || $("#module0").val()==""))
          	 {
           		 $("#addBtnRow").hide(); 
          	 }
           	 else
          	 {
          		$("#addBtnRow").show(); 
          	 }           
            
        }
        
        function removeRow(rowNo)
        {
        		if(document.getElementById('responsibility_table').rows.length>2)
        		{
                 	$("#tableRow"+rowNo).remove();
                 	var rNo=Number($("#rowNo").val())-1;
                 	$("#rowNo").val(rNo);    
        		}
        		else
       			{
        			$("#tableErrorRemove").html("We should not delete single row.");
       			}
        		 if(($("#modulesCnt").val()==Number($("#rowNo").val())+1 || $("#module0").val()==""))
              	 {
               		 $("#addBtnRow").hide(); 
              	 }
               	 else
              	 {
              		$("#addBtnRow").show(); 
              	 }         		
        } 
        
        function createResponsibility()
        {
        	$("#tableErrorRemove").html("");
        	if($("#from_date").val()=="")
       		{
        		$("#from_dateError").html("Please select From Date");
        		return false;
       		}
        	else
       		{
        		$("#from_dateError").html("");
       		}
        	if($("#to_date").val()=="")
       		{
        		$("#to_dateError").html("Please Select To Date");
        		return false;
       		}  
        	else
       		{
        		$("#to_dateError").html("");
       		}
        	
       		for(var f=1;f<document.getElementById('responsibility_table').rows.length;f++)
       		{
       				if($("#module"+f).val()=="")
       				{
       					$("#tableErrorRemove").html("Please Select Module in row "+(f+1));
       					return false;
       				}
       		}
       		
       		for(var f=0;f<document.getElementById('responsibility_table').rows.length;f++)
       		{
   				if($("#responsible_person"+f).val()=="")
   				{
   					$("#tableErrorRemove").html("Please Select Responsible Person in row "+(f+1));
   					return false;
   				}      		
       		}      		
        	
        	        	
			var modules = $('select[name="modules"]').map(function() {
	            return $(this).val();
	        }).get().join(",");
			
			var responsiblepersons = $('select[name="responsible_person"]').map(function() {
	            return $(this).val();
	        }).get().join(",");		

			if(checkLeaveResponsibility()==false)
					{
        				var myParams = {user_leave_id:$("#user_leave_id").val(),from_date : $("#from_date").val(), to_date : $("#to_date").val(),modules:modules,responsible_persons:responsiblepersons};
        			
        				if($("#apply_for").val()==0)
        				{
	        				 myParams = {user_id:"${sessionScope.USER_ID}",user_leave_id:$("#user_leave_id").val(),from_date : $("#from_date").val(), to_date : $("#to_date").val(),modules:modules,responsible_persons:responsiblepersons};
        				}
        				else
        					{
	        				 myParams = {user_id:$("#apply_for").val(),user_leave_id:$("#user_leave_id").val(),from_date : $("#from_date").val(), to_date : $("#to_date").val(),modules:modules,responsible_persons:responsiblepersons};
							}
						
			        	$.ajax({url : "<%=request.getContextPath()%>/ajax/insertLeaveResponsibility",
			    			type:"POST",
			    			data:myParams,cache: false,async:false,
			    			success : function(data) 
			    			{
			    					if($("#user_leave_id").val()>0)
			    					{
			    						$("#StatusMsg").html("Leave Responsibility updated");
			    					}
			    					else
			    					{
			    						$("#StatusMsg").html("Leave Responsibility created");
			    					}
			    					$("#tableErrorRemove").html("");
			    					$("#user_leave_id").val("0");
			    					$("#rowNo").val("0");
			    					
			    		    	 	 $("#btnCreate").html("Create");
			    		    	 	 $("#btnReset").html("Reset");
			    		    	 	 
			    				    getPastLeaves();
			    					$('#select2-module0-container').html("All");
			    					
			    	        		 if(($("#modulesCnt").val()==Number($("#rowNo").val())+1 || $("#module0").val()==""))
			    	              	 {
			    	               		 $("#addBtnRow").hide(); 
			    	              	 }
			    	               	 else
			    	              	 {
			    	              		$("#addBtnRow").show(); 
			    	              	 }			    					

			                 }
			             });
					}
					else
					{
						$("#StatusMsg").html("You have already created Leave Responsibility in between these dates.");
					}
				
			
       	
        }
        
        function checkLeaveResponsibility()
        {
        	    var myParams = {user_leave_id:$("#user_leave_id").val(),from_date : $("#from_date").val(), to_date : $("#to_date").val()};
	        	var bool = false;
	           	 $.ajax({
	                 url: "<%=request.getContextPath()%>/ajax/checkLeaveResponsibility",
	                 type:"POST",
	                 data:myParams,cache: false,async:false,
	                 success: function (data) 
	                 {
	                	 if (data == true) {
	                         bool = true;
	                     }
	                 }
	             });
	           	return trueOrFalse(bool);
        }
        
        
        function trueOrFalse(bool){
            return bool;
    	}       
        
        
        
        function removeRest(){
        	if($('#module0').val()=='all'){
        		$('#responsibilityBody > tr:not("#tableRow0")').hide();
        		$('.table-plus-btn > .btn').attr('disabled',true);
           	}else{
           		$('#responsibilityBody > tr:not("#tableRow0")').show();
        		$('.table-plus-btn > .btn').attr('disabled',false);
        	}
        }
        function profileFormSubmit(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	
	   			document.getElementById("profileForm").submit();	
        	}
        }
        
        var validator =	$('#profileForm').validate({
	  		    rules: {
	  		 		   "user_name": {
	  			 		  required: true
	  			 	  },"email_id": {
	  			 		  required: false
	  			 	  },"mobile_number": {
	  			 		  required: false,
		  			 	  minlength:10,
		  			 	  maxlength:10,
		  			 	  number: true
	  			 	  },"personal_contact_number": {
	  			 		  required: false,
		  			 	  minlength:10,
		  			 	  maxlength:10,
		  			 	  number: true
	  			 	  }	,"landline": {
	  			 		  required: false,
	  			 		  number: true
	  			 	  }	,"extension": {
	  			 		  required: false
	  			 	  }		
	  		 	},
	  		    messages: {
	  		 		   "user_name": {
	  			 		  required: 'Required'								
	  			 	  },"email_id": {
	  			 		  required: 'Required'
	  			 	  }	,"mobile_number": {
	  			 		  required: "Enter your mobile no"
	  			 	  },"personal_contact_number": {
	  			 		  required: "Enter your mobile no"
	  			 	  },"landline": {
	  			 		  required: "Enter your landline no"
	  			 	  },"extension": {
	  			 		  required: 'Required'
	  			 	  }	
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "user_name" ){
					     document.getElementById("user_nameError").innerHTML="";
				 	     error.appendTo('#user_nameError');
					 }else if(element.attr("id") == "email_id" ){
					     document.getElementById("email_idError").innerHTML="";
				 	     error.appendTo('#email_idError');
					 }else if(element.attr("id") == "mobile_number" ){
					     document.getElementById("mobile_numberError").innerHTML="";
				 	     error.appendTo('#mobile_numberError');
					 }else if(element.attr("id") == "personal_contact_number" ){
					     document.getElementById("personal_contact_numberError").innerHTML="";
				 	     error.appendTo('#personal_contact_numberError');
					 }else if(element.attr("id") == "landline" ){
					     document.getElementById("landlineError").innerHTML="";
				 	     error.appendTo('#landlineError');
					 }else if(element.attr("id") == "extension" ){
					     document.getElementById("extensionError").innerHTML="";
				 	     error.appendTo('#extensionError');
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
               },submitHandler:function(form){
			    	form.submit();
			    }
			});  
    </script>
	
</body>

</html>