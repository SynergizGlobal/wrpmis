<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Safety Instruction </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
 	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css"> 
    <link rel="stylesheet" href="/pmis/resources/css/safety.css">
   <!--  <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	 -->	    
     <style>
        .fixed-width {
            width: 100%;
            margin: 0 !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        .mdl-data-table {
            border: 1px solid #ddd;
        }

        .filevalue {
            display: block;
            margin-top: 10px;
        }

        .fw-45p {
            width: 45%;
            max-width: 45%;
        }
          .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		.preloader-wrapper{top: 45%!important;left:47%!important;}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
  <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Safety Instruction</h6>
                            </div>
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
                    </div>
                    <!-- form start-->
                   	<form action="<%=request.getContextPath() %>/update-safety-instructions" id="safetyInstructionsForm" name="safetyInstructionsForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                        <div class="container container-no-margin" style="margin-top:20px;margin-bottom:20px">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row fixed-width">
                                        <div class="table-inside">
                                            <table id="safety-instruction" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="fw-45p">Name</th>
                                                        <th class="fw-45p">Attachment</th>
                                                        <th class="no-sort">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="safetyTableBody">
                                       				 <c:forEach var="sObj" items="${safetyInstructionsList }" varStatus="index"> 
                                                    <tr id="safetyRow${index.count }">
                                                        <td>
                                                        <input type="hidden" name= "safety_instructions_ids" id="safety_instructions_ids${index.count }" value="${sObj.safety_instructions_id}" />
                                                            <input id="document_names${index.count }" name="document_names" type="text" class="validate" value="${sObj.document_name }"
                                                            
                                                                placeholder="Name">
                                                        </td>
                                                        <td>
                                                    <div class="">
                                                        <input type="file" name="SafetyFile" id="SafetyFile${index.count }" onchange="getFileName('${index.count }')"   
                                                            style="display:none" />
                                                        <label for="SafetyFile${index.count }" class="btn bg-m"><i
                                                                class="fa fa-paperclip"></i></label>
                                                        <a href="<%=CommonConstants.SAFETY_INSTRUCTIONS_FILES %>/${sObj.document_url }" id="fileVal${index.count }" class="filevalue" download >${sObj.document_url }</a>
                                                    </div>
                                                    <input type="hidden" id="safetyEquipmentFileNames${index.count }" name="safetyEquipmentFileNames" value="${sObj.document_url }">
                                                </td>
                                                <td>
                                                    <a onclick="removeSafety('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                                    </tr>
                                                     </c:forEach>
                                       				
                                                </tbody>
                                            </table>
                                          <table class="mdl-data-table">
                                       	 <tbody id="safetyBody">                                          
			                                    <tr>
			  										 <td colspan="3" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-s t-c " onclick="addSafetyRow()"> <i
			                                                            class="fa fa-plus"></i></a>
			                                    </tr>
                                        </tbody>
                                    </table>
										<c:choose>
                                        <c:when test="${not empty safetyInstructionsList && fn:length(safetyInstructionsList) gt 0 }">
                                    		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(safetyInstructionsList) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose> 
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>


                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button style="width: 100%;" onclick="updateSafetyInstructions();"
                                            class="btn waves-effect waves-light bg-m black-text">Update</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/safety-instructions" class="btn waves-effect waves-light bg-s black-text"
                                       style="width:100%">Cancel</a>
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

 <!-- Page Loader -->
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
   <!--  <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> -->
    <!-- <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> -->

	    <script>
        $(document).ready(function () {

        });
        function addSafetyRow() {
     	    var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var html = '<tr id="safetyRow' + rNo + '">' + 
           	    '<td><input id="document_names' + rNo + '" name="document_names" type="text" class="validate" placeholder = "Name" ></td > ' 
           	 	+'<td><div class=""> <input type="file" name="SafetyFile" id="SafetyFile'+rNo+'" style="display:none" onchange="getFileName('+rNo+')" /> '
		   	     +'<label for="SafetyFile'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
		   	     +'<input type="hidden" id="safetyEquipmentFileNames'+rNo+'" name="safetyEquipmentFileNames">'
                +'<span id="fileVal'+rNo+'" class="filevalue" ></span> </div></td>'
                +'<td><a  onclick="removeSafety('+rNo+');"  class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td>' + '</tr>';
            $('#safetyTableBody').append(html);
            $("#rowNo").val(rNo);
        }

        function getFileName(rowNo){
			var filename = $('#SafetyFile'+rowNo)[0].files[0].name;
		    $('#fileVal'+rowNo).html(filename);
		}
        
        function removeSafety(rowNo){
        	$("#safetyRow"+rowNo).remove();
        }
        
        function updateSafetyInstructions(){
        	//if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	        	$('form input[name=document_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=safetyEquipmentFileNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			document.getElementById("safetyInstructionsForm").submit();	
        	//}
        }
    </script>
</body>

</html>