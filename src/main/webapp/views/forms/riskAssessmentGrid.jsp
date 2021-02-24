<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risk Assessment</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
      <style>
		p a{
			color:blue;
		}
        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        .tabs .tab a:not(.active) {
            color: #999999;
        }

        .tabs .tab a:focus.active {
            background-color: #e5e5e5;
        }

        .tabs .tab a.active {
            color: #444;
            background-color: #dadada;
            border-bottom: 1px solid #444;
        }

        .tabs .indicator {
            background-color: #888;
        }

        .btn.disabled,
        .btn.disabled * {
            color: #999 !important;
        }

        @media only screen and (max-width: 700px) {
            .mt-md-54 {
                margin-top: inherit;
            }
            .file-field .file-path-wrapper{
	        	padding-left:1px;
	        }
        }
       
        td {
        	word-break: break-word;
    		white-space: initial;
		}
		.fw-120{
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
        }      
		.error-msg label{color:red!important;}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>


    <div class="row" style="margin-bottom:0;">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Risk Assessment</h6>
                        </div>
                    </span>
                    <div class="">
                    	<c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						
						<c:if test="${not empty updateSuccess }">					        
							<div class="center-align m-1 close-message">	
							   ${updateSuccess}
							</div>
						</c:if>
						
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>
						
						  <div class="row plr-1 no-mar">
                            <div class="col m3  hide-on-small-only"> </div>
                            <div class="col s12 m6 c-align"> 
                            <div class="row no-mar">
	                            <!--  <div class="col hide-on-small-only m3"></div> -->
	                             <div class="col s12 m12 input-field">
	                              <a class="btn waves-effect waves-light bg-s t-c" href="/pmis/Risk_Template.xlsx" download style="width:100%">Click here for the Risk Assessment Form</a> 	                              	
	                             </div>
	                        </div>
	                        </div>
	                     </div>
	                              
                        <div class="row plr-1">
                            <div class="col s12 m3 l-align"> </div>
                            <div class="col s12 m6 c-align">                            
                                <div class="m-1">
                                	 <form action="<%=request.getContextPath() %>/upload-risk-assessment" id="riskUploadForm" name="riskUploadForm" method="post" enctype="multipart/form-data">
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
	                                        </div>
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
                        </div>
                 
                    </div>
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
    <jsp:include page="../layout/footer.jsp"></jsp:include>

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
	          $('.tabs').tabs();
	          
	          $("#work_id_fk").change(function () {
	              if ($("#work_id_fk").val() == '') {
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
	      });
	      
	      $("#uploadRisk").on("click",function(){
	    	  var flag = $("#riskUploadForm").valid();
	    	  if(flag){
	    		  var work_short_name = $("#work_id_fk").find('option:selected').attr("name");
	    		  $("#work_short_name").val(work_short_name);
	    		  $('#riskUploadForm').submit();
	    	  }
	      });
        
       	  var validator = $('#riskUploadForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "riskAssessmentFile":{
				   		required: true
				   	  },
				   	  "work_id_fk":{
				   		required: true
				   	  }
			 	},
			   messages: {
    				 "riskAssessmentFile":{
    					 required: 'Required'
   				   	 },
				   	 "work_id_fk":{
				   		required: 'Required'
				   	 }	      
		       },
			  	errorPlacement:
			 	function(error, element){
    				if (element.attr("id") == "riskAssessmentFile" ){
  			 		     document.getElementById("riskAssessmentFileError").innerHTML="";
  			 			 error.appendTo('#riskAssessmentFileError');
  			 	    }else if (element.attr("id") == "work_id_fk" ){
  			 		     document.getElementById("work_id_fkError").innerHTML="";
  			 			 error.appendTo('#work_id_fkError');
  			 	    }
			   },submitHandler: function(form) {
				    // do other things for a valid form
				    form.submit();
				    //return true;
			   }
			});

    </script>

</body>

</html>