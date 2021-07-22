
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
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
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
	/* 	.fw-120{
        	width:120px !important;
        	max-width:120px;
        }
		.fw-200{
        	width:200px;
        	max-width:200px;
        }
        .fw-250{
        	width:250px;
        	max-width:250px;
        }      */ 
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
          @media only screen and (max-width:769px) {    		
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
						<div class="col m8">
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
							enctype="multipart/form-data">
							<div class="row">
								<div class="col m1 hide-on-small-only"></div>
								<div class="col m10">
									<div class="row">
										<div class="col m2 s12 mob-center input-field b-text">
											<p class="mt-1">Step 1 :</p>
										</div>
										<div class="col m10 s12 mob-center input-field">
											<a class="btn waves-effect waves-light bg-s t-c"
												href="/pmis/Risk_Template.xlsx" download style="width: 100%">Click
												here for the Risk Assessment Form</a>
										</div>
									</div>
									<div class="row">
										<div class="col m2 s12 mob-center input-field b-text">
											<p class="">Step 2 :</p>
										</div>
										<div class="col m10 s12 mob-center input-field">
											<p class="b-text">Assess risk offline on the downloaded form</p>
										</div>
									</div>
									<div class="row">
										<div class="col m2 s12 mob-center input-field b-text">
											<p class="mt-2">Step 3 :</p>
										</div>
										<div class="col m5 s6 mob-center input-field">
											<p class="mt-2 b-text">Select the Work</p>
										</div>
										<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' }">
											<div class="col m5 s6 mob-center input-field">
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
										<div class="col m2 s12 mob-center input-field b-text">
											<p class="mt-2">Step 4 :</p>
										</div>
										<div class="col m5 s12 mob-center input-field ">
											<p class="mt-2 b-text">Upload the Completed Risk Assessment form</p>
										</div>
										<div class="col m5 s12 input-field file-field">
											<input type="hidden" id="work_short_name"
												name="work_short_name" />
											<!--  <div class="col s12 m6 file-field input-field"> -->
											<div class="btn bg-m t-c disabled" id="uploadRiskBtn">
												<span>Upload Risk Assessment</span> <input type="file"
													name="riskAssessmentFile" id="riskAssessmentFile"
													accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate" type="text">
											</div>
											<span id="riskAssessmentFileError" class="error-msg"></span>
										</div>
									</div>
									<div class="row">
										<div class="col m2 s6 input-field b-text">
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
										<div class="col m2 s12 mob-center input-field b-text">
											<p class="mt-1">Step 6 :</p>
										</div>
										<div class="col m10 s12 mob-center input-field">
											<a class="btn waves-effect waves-light bg-s t-c fs-sm-67rem" onclick="openRiskATRUpdateForm();"
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
	                            <div class="col m5 hide-on-small-only"></div>
	                        </div>
	
	                        <div class="row">
	                            <div class="col m12 s12">
	                                <table id="datatable-risk-uploads" class="mdl-data-table">
	                                    <thead>
	                                        <tr>                                            
	                                            <th class="mob-50">Work</th>											
												<th class="mob-50">Uploaded File</th>
												<th class="hideCOl">Status</th>
												<th class="hideCOl">Remarks</th>
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
	</form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
      <script>
      
	      $(document).ready(function () {
	    	  $(".modal").modal();
	          $('select:not(.searchable)').formSelect();
	          $('.searchable').select2();
	         // $('.tabs').tabs();
	          
	          $("#sub_work").change(function () {
	              if ($("#sub_work").val() == '') {
	                  $("#uploadRiskBtn").addClass('disabled');
	              } else {
	                  $("#uploadRiskBtn").removeClass('disabled');
	              }
	          });
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
	      
	      $("#uploadRisk").on("click",function(){
	    	  var flag = $("#riskUploadForm").valid();
	    	  if(flag){
	    		  $('#riskUploadForm').submit();
	    	  }
	      });
        
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
            getRiskUploadsList('');
            $('.searchable').select2();
        }
        
        
        function getRiskUploadsList(sub_work){
        	$(".page-loader-2").show();
        	table = $('#datatable-risk-uploads').DataTable();
    		table.destroy();
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-risk-uploads').DataTable({
    			"order": [],
        		"bStateSave": false,
        		fixedHeader: true,
                "fnStateSave": function (oSettings, oData) {
                    localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
                },
                "fnStateLoad": function (oSettings) {
                    return JSON.parse(localStorage.getItem('MRVCDataTables'));
                },
                columnDefs: [
                   // { orderable: false, 'aTargets': ['nosort'] },
                    { targets: [0, 1], className: 'dt-left'  }, 
                    { targets: [2,5,6], className: 'dt-center hideCOl'},
                    { targets: [3,4], className: 'hideCOl dt-left'},
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                 "sScrollXInner": "100%",
                 "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
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
                        	filePath = '<a href="<%=CommonConstants2.RISK_ASSESSMENT_UPLOADED_FILES%>'+ val.attachment +'">'+val.attachment + '</a>';
                        }
                        var rowArray = []; 
                        
                        if(window.matchMedia("(max-width: 769px)").matches){
                        	rowArray.push($.trim(val.sub_work));
                        	rowArray.push(filePath);
                        	rowArray.push('');
                        	rowArray.push('');
                        	rowArray.push('');
                        	rowArray.push('');
                        	rowArray.push();
                	      	
                		 } else{
                			rowArray.push($.trim(val.sub_work));
                           	rowArray.push(filePath);
                           	rowArray.push($.trim(val.status));
                           	rowArray.push($.trim(val.remarks));
                           	rowArray.push($.trim(val.assessment_date));
                           	rowArray.push($.trim(val.uploaded_by));
                           	rowArray.push($.trim(val.uploaded_on)); 
                		 }
                       
                        table.row.add(rowArray).draw( true );
                        		                       
    				});
             		
             		$(".page-loader-2").hide();
    			}else{
    				$(".page-loader-2").hide();
    			}
    			
    		},error: function (jqXHR, exception) {
    			$(".page-loader-2").hide();
             	getErrorMessage(jqXHR, exception);
         }});
       }
       $(window).resize(getRiskUploadsList(sub_work));
               
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
    	  $("#riskATRUpdateForm").submit();
      }
      
    </script>

</body>

</html>