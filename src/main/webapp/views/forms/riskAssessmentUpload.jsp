
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants2"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risk Assessment - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
	<link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
	
      <style>
		p a{
			color:blue;
		}
        .input-field .searchable_label {
            font-size: 0.9rem;
        }
        .btn.disabled,
        .btn.disabled * {
            color: #999 !important;
        }
       
        td {
        	word-break: break-word;
    		white-space: initial;
		}
		@media only screen and (min-width: 820px){ 
	 		.fw-250{
	    	 	width:250px !important;
	    	 	max-width:250px;
	    	 }
	    	 .fw-120{
	    	 	width:120px !important;
	    	 	max-width:120px;
	    	 }
	    	 .fw-90{
	    	 	width:90px !important;
	    	 	max-width:90px;
	    	 }
	    	 tbody tr td:nth-child(1),
	    	 thead tr th:nth-child(1){
	    	 	max-width:250px !important;
	    	 }
    	 }
		.error-msg label{color:red!important;}
		.mt-1{
			margin-top:.5rem !important;
		}
		.mt-2{
			margin-top:1rem !important;
		}
		.b-text p,.b-text{
			font-weight:600;
		}
		.dt-left{text-align: left!important;}
		.dt-center{text-align: center!important;}
		
		.btn.bg-s{
			padding:0 .5rem;
		}
		.w10em{width: 10em !important;}
		.w20em{width:  23em !important;}
		@media screen and (max-width: 1300px) and (min-width: 1150px) {
			.btn.bg-s{
				font-size:1.5vmin;
			}			
		}    
		@media screen and (max-width: 1149px) and (min-width: 1024px) {
			.btn.bg-s{
				font-size:1.28vmin;
			}		
			.btn.bg-s.fs-sm-8rem{
				font-size:1.5vmin;
			}	
		}    
		@media screen and (max-width: 1023px) and (min-width: 993px) {
			.btn.bg-s{
				font-size:1.2vmin;
			}			
		}    
		@media screen and (max-width: 992px) and (min-width: 820px) {
			.btn.bg-s.fs-sm-8rem{
				font-size:1.5vmin;
			}			
		} 
          @media only screen and (max-width:820px) { 
			.btn.bg-s.fs-sm-8rem{
				font-size:1.35vmin;
			}	
			.card .card-content {
			    padding-left: 10px;
			    padding-right: 10px;
			}
			.btn-holder .btn+.btn{
				margin-left:0;
				margin-top:10px;
			} 
	        .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        }
	        .mob-50{
	        	width:48vw !important;
	        	max-width:48vw ;
	        }
	        .dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
				max-width: 45px;
			}
			.mob-btn{
				padding:0 12px;
			}
			.hideCOl{
				display:none;
			}
        }
		@media only screen and (max-width: 700px) {
            .mt-md-54 {
                margin-top: inherit;
            }
            .file-field .file-path-wrapper{
	        	padding-left:1px;
	        }
	        .mt-2{
				margin-top:0 !important;
			}
        }
        
         .modal-header {
            text-align: center;
            background-color: #2E58AD;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        .last-column .btn+.btn {
            margin-left: 15px;
        }
        .dataTables_filter label::after{
         	content:'';
         }
         .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
         }
         .fw-all{
         		width:12vw !important;
        		max-width:12vw;
         }
           @media only screen and (max-width: 820px){ 
			
			.dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
				max-width: 250px;
			}
			.mob-btn{
				padding:4px 12px;
			}
			.hideCOl{
				display:none;
			} 
			.r-300{
				width:30vw !important;
        		max-width:30vw;
			}
			 .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px; 
	        }
	        .fw-111{
	        	width:20vw;
	        	min-width:20vw;
	        	
	        }
	        .break{
	        	text-align:center;
	        	width:26vw;
    			min-width:26vw;
			    margin-left: auto;
			    margin-right: auto;
			    
	        }
	        .break a{
			   word-break: break-word;
			   height: auto;
			   white-space: normal;
			   line-height: inherit;
	        }
	        .mob-center{
	        	text-align:center;
	        }
		}
		@media(max-width: 360px){
		
		 .pad0p36{padding:0 !important;}
		}
		
		.my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		
		/* new code for modal and its contents starts  */
		.row.no-mar{
			margin-bottom:0;
		}
		.radioClass,
		#confirmBox input[type="radio"],
		#cvDocBox input[type="radio"] {
			opacity:2;
			position: inherit;
		}		
		#confirmBox ,#cvDocBox,#noAtrDiv {
			text-align:center;
			font-size: 1.25rem;
		}		
		.modal-content label,
		.modal-content [type="checkbox"]+span:not(.lever) {
		    font-size: 1.25rem; 
		    color: #9e9e9e;
		}
		.modal-content [type="radio"]:not(:checked)+span, [type="radio"]:checked+span{
			padding-left:25px;
		}
		/* new code for modal and its contents ends  */
		.btn-holder .btn+.btn{
			margin-left:20px;
		}       
    .no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
	}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <div class="row no-mar" >
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Risk Assessment</h6>
                        </div>
                    </span>
					<div class="row no-mar">
						<div class="col m2 hide-on-small-only"></div>



<div id="myModal" class="modal">
       <div class="modal-content">
           <h5 class="modal-header"> Risk Assessment 
	           <span class="right modal-action modal-close">
	           <span class="material-icons">close</span></span>
           </h5>
               <div class="row no-mar" id="amendment_not_required_in_contract_Div" style="display: block;">
                   <div class="input-field col s12 m10 offset-m1 center-align">
			                   <p>The date of assessment on the Risk Assessment form is same as that of the last assessment date.			                    </p>
			                   <h5>Do you wish to overwrite ?</h5>
			                </div>
               </div>        
                           

				
                <div class="row no-mar col s12 m12 center-align btn-holder" >
                		   <button type="button" style="width: auto;" id="btnYes"
                               class="btn waves-effect waves-light bg-m" onclick="submitData();">Yes</button>
                           <button type="button" style="width: auto;" id="btnNo"
                               class="btn waves-effect waves-light bg-m modal-close" onclick="cancelData();">No</button>
                           
                  
               </div> 
               <br><br>
       </div>
   </div>
   

					
						
						<div class="col m8 l12">
							<div class="">
								<c:if test="${not empty success }">
									<div class="center-align m-1 close-message">${success}</div>
								</c:if>
		
								<c:if test="${not empty updateSuccess }">
									<div class="center-align m-1 close-message">${updateSuccess}</div>
								</c:if>
		
								<c:if test="${not empty error }">
									<div class="center-align m-1 close-message">${error}</div>
								</c:if>
							</div>
						</div>
						<div class="col m2 hide-on-small-only"></div>
					</div>
					<div class="container container-no-margin">
						<form
							action="<%=request.getContextPath()%>/upload-risk-assessment"
							id="riskUploadForm" name="riskUploadForm" method="post"
							enctype="multipart/form-data" >
							<div class="row">
								<div class="col m10 l12">
								
									<div class="row">
										<div class="col l2 m2 s12 mob-center input-field b-text pad0p36">
											<p class="mt-2">Step 1 :</p>
										</div>
										<div class="col l5 m5 s6 mob-center input-field">
											<p class="mt-2 b-text">Select the Work</p>
										</div>
										<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' }">
											<div class="col l5 m5 s6 mob-center input-field">
												<p class="searchable_label left-align">Work</p>
												<select id=sub_work name="sub_work"
													class="searchable validate-dropdown">
													<option value="">Select</option>
													<c:forEach var="obj" items="${subWorksList}">
														<option name="${obj.sub_work }" value="${obj.sub_work}" 
														<c:if test="${sub_work eq obj.sub_work }">selected</c:if>>
														${obj.sub_work}</option>
													</c:forEach>
													
												</select> <span id="sub_workError" class="error-msg"></span>
											</div>
										</c:if>
										<c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' }">
											<div class="col m5 s6 input-field">
												<p class="searchable_label left-align">Work</p>
												<select id="sub_work" name="sub_work"
													class="searchable validate-dropdown">
													<option value="">Select</option>
													<c:forEach var="obj" items="${workHodList}">
														<option name="${obj.sub_work }" value="${obj.sub_work}" 
														<c:if test="${sub_work eq obj.sub_work }">selected</c:if>>
														${obj.sub_work}</option>
													</c:forEach>
												</select> <span id="sub_workError" class="error-msg"></span>
											</div>
										</c:if>
									</div>
									
									<div class="row">
										<div class="col l2 m2 s3 mob-center input-field b-text pad0p36">
											<p class="mt-1">Step 2 :</p>
										</div>
										<div class="col l5 m10 s9 mob-center input-field">
											<a id="lastRiskAssessmentForm" href="javascript:noRecordFound();" class="btn waves-effect waves-light bg-s t-c" download style="width: 100%;letter-spacing: 0px;text-transform: unset;">Click
												here for last Risk Assessment Form</a>
										</div>
										<div class="col l5 m10 offset-m2 s9 offset-s3 mob-center input-field">											
											<a class="btn waves-effect waves-light bg-s t-c"
												href="/pmis/Risk_Template.xlsx" download style="width: 100%;letter-spacing: 0px;text-transform: unset;">Click
												here for blank Risk Assessment Form</a>
										</div>
									</div>
									<div class="row">
										<div class="col m2 s3 mob-center input-field b-text pad0p36">
											<p class="">Step 3 :</p>
										</div>
										<div class="col m10 s9 mob-center input-field">
											<p class="b-text">Assess risk offline on the downloaded form</p>
										</div>
									</div>
									
									<div class="row">
										<div class="col m2 s3 mob-center input-field b-text pad0p36">
											<p class="mt-2">Step 4 :</p>
										</div>
										<div class="col m10 l5 s9 mob-center input-field ">
											<p class="mt-2 b-text">Upload the Completed Risk Assessment form</p>
										</div>
										<div class="col m10 offset-m2 l5 s10 offset-s2 input-field file-field">
											<input type="hidden" id="work_short_name"
												name="work_short_name" />
											<!--  <div class="col s12 m6 file-field input-field"> -->
											<div class="btn bg-m t-c disabled" id="uploadRiskBtn">
												<span>Upload Risk Assessment</span> <input type="file"
													name="riskAssessmentFile" id="riskAssessmentFile" class="fileClass" 
													accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate" type="text" id="riskfile">
											</div>
											<span id="riskAssessmentFileError" class="error-msg"></span>
										</div>
									</div>
									<div class="row">
										<div class="col m2 s3 input-field b-text pad0p36">
											<p class="mt-1">Step 5 :</p>
										</div>
										<!-- <div class="col m5 s9 input-field"></div> -->
										<div class="col m5 s6 input-field">
											<button type="button"
												class="btn waves-effect waves-light bg-s t-c disabled"
												id="uploadRisk" >
												<strong><i class="fa arrow-circle-up"></i>Submit</strong>
											</button>
										</div>
										<!-- <div class="col m5 s9 input-field">
											<button type="button"
												class="btn waves-effect waves-light bg-s t-c" onclick="addDisabled()" style="min-width:85px" >
												<strong><i class="fa arrow-circle-up"></i>Reset</strong>
											</button>
										</div> -->
									</div>
									
									<div class="row">
										<div class="col m2 s12 mob-center input-field b-text pad0p36">
											<p class="mt-1">Step 6 :</p>
										</div>
										<div class="col m10 s12 mob-center input-field">
											<a class="btn waves-effect waves-light bg-s t-c fs-sm-8rem" onclick="openRiskATRUpdateForm();"
												href="javascript:void(0);" style="width: 100%;text-transform: none;">Click
												here to update ATR on the Mitigation Plan of prioritized risks</a>
										</div>
									</div>
									
									<%-- <div class="row">
										<div class="col m2 s3 input-field b-text">
										</div>
										<div class="col m10 s9 input-field b-text">
											<!-- <p class="mt-1 center-align">In case of any Changes or Modification in information after uploading the Excel file by clicking  "submit"  . User can modify the desired information in only in " Input 4 to input 8" and upload the form again as per step 1 to Step-5.</p> 
 											<p class="mt-1 center-align">Do not make any change in Input-1 to Input -4 , else it  shall be treated as New assessment</p> -->
											<p class="mt-1 center-align">To update ATR on the
												Mitigation Plan of prioritized risks, go to <a href="<%=request.getContextPath()%>/risk-atr-update" target="_blank">Update Forms > Risk >
												Update ATR </a></p>
										</div>
									</div> --%>
								</div>
								<div class="col m1 hide-on-small-only"></div>
							</div>
						</form>
					</div>

					<!-- <div class="row plr-1 no-mar">
                            <div class="col s12 m3 l-align"> </div>
                            <div class="col s12 m6 c-align">                            
                                <div class="m-1">
                                	 <form action="<%=request.getContextPath()%>/upload-risk-assessment" id="riskUploadForm" name="riskUploadForm" method="post" enctype="multipart/form-data">
	                                    <div class="row">
	                                        <div class="col s12 m4 input-field">
	                                        	<p class="searchable_label left-align">Work</p>
	                                            <select id="work_id_fk" name="work_id_fk" class="searchable validate-dropdown">
	                                            	<option value="" >Select</option>	  
	                                            	<c:forEach var="obj" items="${worksList}">
	                                            		<option name="${obj.work_short_name }" value="${obj.work_id}" >${obj.work_id} - ${obj.work_short_name}</option>	  
	                                            	</c:forEach>                                         
	                                            </select>
	                                            <span id="work_id_fkError" class="error-msg"></span>
	                                        </div>
	                                        <input type="hidden" id="work_short_name" name="work_short_name" />
	                                        <div class="col s12 m6 file-field input-field">
										      <div class="btn bg-m t-c disabled" id="uploadRiskBtn">
										        <span>Upload Risk Assessment</span>
										        <input type="file" name="riskAssessmentFile" id="riskAssessmentFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
										      </div>
										      <div class="file-path-wrapper">
										        <input class="file-path validate" type="text">
										      </div>
										      <span id="riskAssessmentFileError" class="error-msg"></span>
                                         	 <!--  <p style="padding-top:.7rem; text-align:left"> Click <a href="/pmis/Risk_Template.xlsx" download>here</a> for the template</p>  -->
	                                        <!-- </div>
	                                        <div class="col s12 m2 input-field">
	                                            <button type="button" class="btn waves-effect waves-light bg-s t-c disabled" id="uploadRisk" style="margin-top:5px;">
	                                            	<strong><i class="fa arrow-circle-up"></i>Submit</strong>
	                                            </button>	                                            
	                                        </div>
	                                    </div>
                                    </form>
                                </div> 
                            </div>
                            <div class="col s12 m3 r-align">     </div>
                        </div> -->
                        
                        
                 
                    </div>
                </div>
                
                <c:if test="${USER_ROLE_CODE eq 'IT' }">
                <div class="card">
	                <div class="card-content">
	                    <span class="card-title headbg">
	                        <div class="center-align bg-m p-2 m-b-5">
	                            <h6>Update Risk Assessment</h6>
	                        </div>
	                    </span>
	                    <div class="">
	                        <div class="row no-mar" >
	                                    <div class="col s6 m3 input-field offset-m4">
	                                        <p class="searchable_label">Work</p>
	                                        <select id="sub_workfilter" name="sub_work" class="searchable" onchange="getRiskUploadsList(this.value);">
	                                            <option value="">Select</option>
	                                        </select>
	                                    </div>                                 
	                                    <div class="col s6 m3">
	                                        <button class="btn bg-s waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 12px;" onclick="clearFilters()">Clear
	                                            Filters</button>
	                                    </div>                          
	                        </div>
	
	                        <div class="row">
	                            <div class="col m12 s12">
	                                <table id="datatable-risk-uploads" class="mdl-data-table">
	                                    <thead>
	                                        <tr>                                            
	                                            <th class="mob-50 no-sort">Work</th>											
												<th class="mob-50">Uploaded File</th>
												<th class="hideCOl">Status</th>
												<th class="hideCOl w20em">Remarks</th>
												<th class="hideCOl">Assessment date</th>
												<th class="hideCOl">Uploaded by </th>
												<th class="hideCOl">Uploaded On</th>
	                                        </tr>
	                                    </thead>
										<tbody>
											<!-- <tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr> -->
	
										</tbody>
	
									</table>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
                </c:if>
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
    <jsp:include page="../layout/footer.jsp"></jsp:include>

	<form action="<%=request.getContextPath()%>/risk-atr-update" id="riskATRUpdateForm" name="riskATRUpdateForm" target="_blank" method="post">
		<input type="hidden" id="sub_work_atr_update" name="sub_work"/>
		<input type="hidden" id="assessment_date_atr_update" name="assessment_date"/>
	</form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
  
      <script>
          var pageNo = window.localStorage.getItem("risksPageNo");
	      $(document).ready(function () 
	    		  {
	          $('select:not(.searchable)').formSelect();
	          $('.searchable').select2();
	         // $('.tabs').tabs();
	          
	          $("#sub_work").change(function () {
	        	  var sub_work = $("#sub_work").val();
	              if (sub_work == '') {
	                  $("#uploadRiskBtn").addClass('disabled');
	                  $("#lastRiskAssessmentForm").attr("href", 'javascript:noRecordFound();');
	              } else {
	                  $("#uploadRiskBtn").removeClass('disabled');
	                  getLastUpdatedRiskAssessmentFile(sub_work);
	              }
	          });
	          
	          var sub_work = '${sub_work}';
	          if($.trim(sub_work) != ''){
	        	  getLastUpdatedRiskAssessmentFile(sub_work);
	          }
	          
	          $("input[name='riskAssessmentFile']").change(function () {
	              if ($("input[name='riskAssessmentFile']").val() == '') {
	                  $("#uploadRisk").addClass('disabled');
	              } else {
	                  $("#uploadRisk").removeClass('disabled');
	              }
	          });
	          var sub_work_from_alert = "${sub_work}";
	          if($.trim(sub_work_from_alert) != ''){
	        	  $("#uploadRiskBtn").removeClass('disabled');
	          }
	          
	          getSubWorksFilterList();
	          getRiskUploadsList('');
	        

	      });
	      
      	  function noRecordFound(){
      		    var errorMessage = "No record available. \nPlease download a blank Risk Assessment Form.";
      		    swal('',errorMessage);
      	  }
	      
	      function getLastUpdatedRiskAssessmentFile(sub_work){
	    	  	$(".page-loader").show();
	           	var myParams = {sub_work : sub_work};
	           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getLastUpdatedRiskAssessmentFile",
                   data: myParams, cache: false,
                   success: function (data) {	         
                	  if($.trim(data.attachment) != ''){
                		  var filePath = "<%=CommonConstants2.RISK_ASSESSMENT_UPLOADED_FILES%>"+ data.attachment;
                		  $("#lastRiskAssessmentForm").attr("href", filePath);
                	  }else{
                		  $("#lastRiskAssessmentForm").attr("href", 'javascript:noRecordFound();');
                	  }
                      $(".page-loader").hide();
                   },error: function (jqXHR, exception) {
    	   			  $(".page-loader").hide();
   	   	          	  getErrorMessage(jqXHR, exception);
   	   	     	  }
               });
	      }
	      
	      $("#uploadRisk").on("click",function(){
	    	  $(".page-loader-2").show();
	    	  var flag = $("#riskUploadForm").valid();
	    	  if(flag)
	    	  {
	    		  var fd = new FormData();

	              fd.append( "riskAssessmentFile", $("#riskAssessmentFile")[0].files[0]);
	              fd.append( "sub_work", $("#sub_work").val());
	              
		           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/checkRiskAssessment",
	                   method: 'post',
	                   processData: false,
	                   contentType: false,
	                   cache: false,
	                   data: fd,
	                   success: function (data) 
	                   {
	                	   
	                	   //$(".alert-message").html(data);
	                	   var Str=data.toString();
	                	   var checkIndex=Str.indexOf("The date of assessment on the Risk Assessment form is same as that of the last assessment date");
	                	   if(checkIndex!=-1)
                		   {
	         	    	      $("#myModal").modal();
	      	    		  	  $("#myModal").modal('open');              		   	
                		   }
	                	   else
	                	   {
	                		   	$('#riskUploadForm').submit();
	                	   }
	                	   
	                	   $(".page-loader-2").hide();
	                   }	                	  
	               });
		           
	    	  }else{
	    		  $(".page-loader-2").hide();
	    	  }
	      });
	      
	      function submitData()
	      {
	    	  var flag = $("#riskUploadForm").valid();
	    	  if(flag)
	    	  {
	    		  $('#riskUploadForm').submit();
	    	  }
	      }
	      
	      function cancelData()
	      {

	    	  $("#myModal").hide();
	      }      
	      
        
       	  var validator = $('#riskUploadForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "riskAssessmentFile":{
				   		required: true
				   	  },
				   	  "sub_work":{
				   		required: true
				   	  }
			 	},
			   messages: {
    				 "riskAssessmentFile":{
    					 required: 'Required'
   				   	 },
				   	 "sub_work":{
				   		required: 'Required'
				   	 }	      
		       },
			  	errorPlacement:
			 	function(error, element){
    				if (element.attr("id") == "riskAssessmentFile" ){
  			 		     document.getElementById("riskAssessmentFileError").innerHTML="";
  			 			 error.appendTo('#riskAssessmentFileError');
  			 	    }else if (element.attr("id") == "sub_work" ){
  			 		     document.getElementById("sub_workError").innerHTML="";
  			 			 error.appendTo('#sub_workError');
  			 	    }
			   },submitHandler: function(form) {
				    // do other things for a valid form
				    form.submit();
				    //return true;
			   }
			});
       	  
       	  
       	  
       	function clearFilters() {
            $('#sub_workfilter').val('');
            window.localStorage.setItem("risksFilters",'');
        	window.location.href="<%=request.getContextPath()%>/risk-assessment"
        	var table = $('#datatable-risk-uploads').DataTable();
        	table.draw( true );
            $('.searchable').select2();
        }
        
        
        function getRiskUploadsList(sub_work){
        	$(".page-loader-2").show();
        	table = $('#datatable-risk-uploads').DataTable();
    		table.destroy();
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-risk-uploads').DataTable({
    			"order": [],
          		"bStateSave": true,  
          		fixedHeader: true,
          		 stateSave: true,
              	//Default: Page display length
  				"iDisplayLength" : 10,
  				"iData" : {
  					"start" : 52
  				},"iDisplayStart" : 0,
  				"drawCallback" : function() {
  					var info = table.page.info();
  					window.localStorage.setItem("risksPageNo", info.page);
  				},
                columnDefs: [
                   // { orderable: false, 'aTargets': ['nosort'] },
                    //{ targets: [0, 1], className: 'dt-left'  },  
                    //{ targets: [2], className: 'dt-center hideCOl'},
                    { targets: [0], className: 'dt-left'},
                    { targets: [3], className: 'hideCOl dt-left'},
                    { targets: [2], className: 'fw-90 hideCOl w10em'},
                    { targets: [3,1], className: 'fw-250 dt-left'},
                    { targets: [5], className: 'fw-120 hideCOl dt-left'},
                    { targets: [4,6], className: 'hideCOl w10em dt-center fw-120'},
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                 "sScrollXInner": "100%",
                 "ordering":false,
                 "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input')
					.unbind()
					.bind('keyup',function(e){
					    if (e.which == 13){
					    	self.search(input.val()).draw();
					    }
					}), self = this.api(), $searchButton = $(
					'<i class="fa fa-search" title="Go" id="save_post">')
					.click(function() {
						self.search(input.val()).draw();
					}), $clearButton = $(
							'<i class="fa fa-close" title="Reset">')
					.click(function() {
						input.val('');
						$searchButton.click();
					})
					$('.dataTables_filter').append( '<div class="right-btns"></div>');
					$('.dataTables_filter div').append( $searchButton, $clearButton);
                }
            }).rows().remove().draw();
    		
    		table.state.clear();		
    		var myParams = {sub_work : sub_work};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getRiskAssessmentUploadsList",type:"POST",data:myParams,success : function(data){    				
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var risk_id_pk = "'"+val.risk_id_pk+"'";
                        var filePath = "";
                        
                        if($.trim(val.attachment) != ''){
                        	filePath = '<a href="<%=CommonConstants2.RISK_ASSESSMENT_UPLOADED_FILES%>'+ val.attachment +'" download>'+val.attachment + '</a>';
                        }
                        var rowArray = [];                   
                			rowArray.push($.trim(val.sub_work));
                           	rowArray.push(filePath);
                           	rowArray.push($.trim(val.status));
                           	rowArray.push($.trim(val.remarks));
                           	rowArray.push($.trim(val.assessment_date));
                           	rowArray.push($.trim(val.uploaded_by));
                           	rowArray.push($.trim(val.uploaded_on));                 		
                       
                        table.row.add(rowArray).draw( true );
                        		                       
    				});
             		 if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
        	         var oTable = $('#datatable-risk-uploads').dataTable();
        	         oTable.fnPageChange( pageNo );
             		 $(".page-loader-2").hide();
    			}else{
    				$(".page-loader-2").hide();
    			}
    			
    		},error: function (jqXHR, exception) {
    			$(".page-loader-2").hide();
             	getErrorMessage(jqXHR, exception);
         }});
       }
      // $(window).resize(getRiskUploadsList(sub_work));
               
        function getSubWorksFilterList() {
        	$(".page-loader").show();
           	$("#sub_workfilter option:not(:first)").remove();
           	var myParams = {};
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getSubWorksListFromRiskUploads",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                           	$("#sub_workfilter").append('<option value="' + val.sub_work + '">' + $.trim(val.sub_work) +'</option>');
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
        
        //This function is used to get error message for all ajax calls
        function getErrorMessage(jqXHR, exception) {
        	    var msg = '';
        	    if (jqXHR.status === 0) {
        	        msg = 'Not connect.\n Verify Network.';
        	    } else if (jqXHR.status == 404) {
        	        msg = 'Requested page not found. [404]';
        	    } else if (jqXHR.status == 500) {
        	        msg = 'Internal Server Error [500].';
        	    } else if (exception === 'parsererror') {
        	        msg = 'Requested JSON parse failed.';
        	    } else if (exception === 'timeout') {
        	        msg = 'Time out error.';
        	    } else if (exception === 'abort') {
        	        msg = 'Ajax request aborted.';
        	    } else {
        	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
        	    }
        	    console.log(msg);
         }
      function addDisabled(){
    	  $('#sub_work').val('');
    	  $('.searchable').select2();
    	  $('#uploadRiskBtn ~ .file-path-wrapper > input[type="text"]').val('');
    	  $('#uploadRiskBtn, #uploadRisk').addClass('disabled')
      }
      
      function openRiskATRUpdateForm(){
    	  var sub_work =  $('#sub_work').val();
    	  $("#sub_work_atr_update").val(sub_work);
    	  
    	  var assessment_date =  '${assessment_date}';
    	  $("#assessment_date_atr_update").val(assessment_date);
    	  
    	  $("#riskATRUpdateForm").submit();
      }
      
    </script>

</body>

</html>