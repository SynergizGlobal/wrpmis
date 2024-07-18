<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fortnight Report - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
      <style>            		
		.error-msg label{color:red!important;} 
		
		@media only screen and (min-width:787px){
			.pt-md-5{
				padding-top:5px !important;
			}
		}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
 
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
							
                    <div class="">
                    <div class="center-align m-1 close-message">${error}</div>
			            <form action="<%=request.getContextPath() %>/generate-fortnight-report" id="FortnightReportForm" name="FortnightReportForm" method="post" target="_blank">	                              
                <div class="card ">
                    <div class="card-content ">
						<div class="m-n1" style="text-align:right;">
					        <a href="/pmis/fortnight-upload-remarks" class="btn waves-effect waves-light bg-s t-c"> <strong>Upload</strong></a>
					    </div>                  
                        <h6 class="mar-top center-align">Fortnight Plan Remarks Data</h6>
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
	                            <div class="col m12 s12">
	                                <table id="datatable-p6-new-data" class="mdl-data-table">
	                                    <thead>
	                                        <tr>
	                                            <th>Uploaded File</th>
	                                            <th>Contract Short Name</th>
	                                            <th>Uploaded By</th>
	                                            <th>Uploaded Date</th>
	                                            <th>Download</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
                                            <c:forEach var="obj" items="${FortnightUploadList}">
                                            	<tr><td>${obj.filename }</td><td>${obj.contract_short_name }</td><td>${obj.uploaded_by }</td><td>${obj.uploaded_date }</td>
                                            	
                                            	<td><a href="/pmis/FORTNIGHT_PLAN_UPLOAD_REMARKS_PATH/${obj.filename }" class="btn waves-effect waves-light bg-m t-c mob-btn"><i class="fa fa-download"></i></a></td>
                                            	</tr>
                                             </c:forEach>	                                    
	                                    </tbody>
	                                </table>
	                            </div>
	                        </div>
                    </div>
                </div>                       		
                        </form>
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
    <!-- <script src="/pmis/resources/js/datepickerDepedency.js"></script> -->
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    <script>
    var filtersMap = new Object();
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
                
        $(document).ready(function(){
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal();
            $('.collapsible').collapsible();
		});    

    </script>

</body>

</html>