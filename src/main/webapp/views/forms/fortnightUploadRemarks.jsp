<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fortnight Report - PMIS</title>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
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
                       		
                        </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
      <div id="upload_template" class="modal">
		  <form action="<%=request.getContextPath() %>/upload-fortnight-remarks" method="post" enctype="multipart/form-data" id="frmUpload">
            <div class="modal-content">
                <h6 class="modal-header">Upload Fortnight Plan Remarks <span
                        class="right modal-action modal-close" onclick="resetFields('add')"> <span class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col s12">
                        <div class="row no-mar">
                             <div class="table-inside">
                              <div class="center-align">
					        	<span id="mainErrorAdd" style="align: center;" class="my-error "></span>
					        </div>
					            <table id="addExecutivesTable" class="mdl-data-table mobile_responsible_table" >
					                <thead>
					                    <tr>
					                        <th>Contract Short Name</th>
											<th>Attachment </th>
					                    </tr>
					                </thead>
					                
								    <tbody id="addExecutivesBody">									               
					                    <tr id="AexecutiveRow0">
					                        <td data-head="Work" class="input-field">
			                                    <select id="contract_short_name" class="searchable" name="contract_short_name" required="required" onChange='$("#contractError").html("");'>
			                                        <option value="">Select</option>
			                                         <c:forEach var="obj" items="${contractList }">
			                                            <option value= "${obj.contract_short_name}">${obj.contract_short_name }</option>
			                                        </c:forEach>                                       
			                                    </select>
			                                    <span id="contractError" style="color:red;"></span>
					                        </td>
					                        <td data-head="Responsible Executives" class="input-field h-auto">
		                                        <div class="btn bg-m">
		                                            <input type="file" id="fortnightPlanFile" name="fortnightPlanFile" required="required">
		                                        </div>

					                        </td>
							                        
					                    </tr>
					             
					                </tbody>								                

								</table>
                                                  
					        </div>
					       
					        
                        </div>
                        
                        <div class="row">
                        	<div class="col s12 m8 offset-m2">
                        		<div class="row">
			                        <div class="col s12 m6 mt-brdr">
			                            <div class="center-align m-1">
			                                <button type="button" class="btn waves-effect waves-light bg-m" onClick="checkValidation();">Update</button>
			                            </div>
			                        </div>
			                        <div class="col s12 m6 mt-brdr">
			                            <div class="center-align m-1">
			                                <button type="button" class="btn waves-effect waves-light bg-s" onclick="window.location.href='/pmis/fortnight-upload-list'">Cancel</button>
			                            </div>
			                        </div>
                        		</div>
                        	</div>
                           
                        </div>
                    </div>
                </div>

            </div>

        </form>
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
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
            $('.collapsible').collapsible();
			$("#upload_template").modal('open');
		});    
        
        function checkValidation()
        {
        		if($("#contract_short_name").val()=="")
        		{
        			$("#contractError").html("Contract short name is required");
        			return false;
        		}
        		document.getElementById('frmUpload').submit();

        }

    </script>

</body>

</html>