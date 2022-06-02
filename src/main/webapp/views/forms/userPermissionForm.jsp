<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 <c:if test="${action eq 'edit'}">Update Budget</c:if>
		 <c:if test="${action eq 'add'}"> Add Budget</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/budget.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" > 
    <style>
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
        .w20em{width: 20em;}
        .center, .center-align {
            text-align: center !important;
        } 
		
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

       <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h5>UserId - Name - Designation</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <!-- <div class="container container-no-margin">
                            <div class="row">                              
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Dashboard :</p>
                                    <p>DashBoard Name</p>  
                                </div> 
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Module :</p>
                                    <p>Module Name</p>
                                    <input type="hidden" id="work_id_fk" name="work_id_fk" value="${risk.work_id_fk}" />
                                </div>
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Dashboard Type :</p>
                                    <p>Dashboard name</p>
                                    <input type="hidden" id="work_id_fk" name="work_id_fk" value="${risk.work_id_fk}" />
                                </div>
                                
                            </div>
                            <div class="row">
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Folder :</p>
                                    <p>Folder Name</p>  
                                </div> 
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Work :</p>
                                    <p>Work Name</p>
                                    <input type="hidden" id="work_id_fk" name="work_id_fk" value="${risk.work_id_fk}" />
                                </div>
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Contract Type :</p>
                                    <p>Contract name</p>
                                    <input type="hidden" id="work_id_fk" name="work_id_fk" value="${risk.work_id_fk}" />
                                </div>
                            </div>
                            <div class="row">
                                
                                <div class="col s12 m4 input-field">
                                    <input id="priority" name="priority" type="number" class="validate">
                                    <label for="priority">priority </label>
                                    <span id="priorityError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Status </p>
                                    <select id="status" class="searchable" name="status">
                                        <option value="">Select</option>
                                        <option value="Govt">Govt </option>
                                    </select>
                                </div>
                                <div class="col s12 m4">
                                    <div class="row"> -->
                                        <!-- row 7 -->
                                        <!-- <div class="col s5 m5 input-field">
                                            <p style="margin-top: 12px;">Mobile View ?</p>
                                        </div>
                                        <div class="col s5 m7 input-field">
                                            <p class="radiogroup" style="padding-bottom: 10px;padding-top: 10px;">
                                                <label>
                                                    <input class="with-gap" name="mobile_view" type="radio"
                                                        value="yes" />
                                                    <span>Yes</span>
                                                </label> &nbsp; <label>
                                                    <input class="with-gap" name="mobile_view" type="radio"
                                                        value="no" />
                                                    <span>No</span>
                                                </label>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </div> -->

                            <div class="row no-container" style="margin-bottom: 20px;">
                                <div class="col s12 m4">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Contract Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="contract_permission" class="searchable"
                                                                name="user_type" multiple placeholder="Contract">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Structure Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="str_dep" class="searchable"
                                                                name="user_type" multiple placeholder="Structure">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Risk Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="risk_work" class="searchable"
                                                                name="user_type" multiple placeholder="Work">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                            </div>
                                <div class="row">
                                    <div class="col s12 m4">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Land Acquisition Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="la_work" class="searchable"
                                                                name="user_type" multiple placeholder="Work">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Utility Shifting Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="us_work" class="searchable"
                                                                name="user_type" multiple placeholder="Work">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">R&R Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="rr_work" class="searchable"
                                                                name="user_type" multiple placeholder="Work">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

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
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
  <script>
  $(document).ready(function () {
      $('select:not(.searchable)').formSelect();
      $( ".searchable" ).each(function( index,val ) {
$( this ).select2({                placeholder:      $( this ).attr('placeholder')       });
});
     // $('').select2({                placeholder            });
  });
	  <%-- function selectFile(no){
		    files = $("#budgetFiles"+no)[0].files;
		    var html = "";
			var count = no - 1;
				
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=budgetFilesNames'+no+'>'
				 + '<a href="#" class="filevalue"> '+$(this).get(0).files[i].name+'<span onclick="removeFile('+no+','+count+')" class="attachment-remove-btn">X</span></a>'
				 + '</div>'
				 + '<div style="clear:both;" id="hide'+no+'"><input id="fileCounts'+no+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles").append(html);
		   
		    $('#budgetFilesDiv'+no).hide();
		    
			var fileIndex = Number(no)+1;
			var lastfieldsid = $('#hide'+no+' input').last().val(no);
			var s = $('#hide'+count+' input').val();
			 $('#hideVal'+count+' input').removeAttr('name');
			if(s != null){
				$('#hide'+count+' input').removeAttr('name');
				
			}
			
			moreFiles(fileIndex);
		}
	  function selectFileUpdate(no,bNo){
		    files = $("#budgetFiles"+no)[0].files;
		    var html = "";
			var count = no - 1;
		    var s = null;
		    var fileIndex = Number(no)+1;
			var id = (fileIndex/10);
		    var str=id.toString();
		    var splt = str.split('.')[1];
		    var spli1 = str.split('.')[0];
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=budgetFilesNames'+fileIndex+'>'
				 + '<a href="#" class="filevalue"> '+$(this).get(0).files[i].name+' <span onclick="removeFileUpdate('+fileIndex+','+bNo+')" class="attachment-remove-btn">X</span></a>'
				 + '<input type="hidden" id="budgetFileNames'+fileIndex+'" name="budgetFileNames" value="'+$(this).get(0).files[i].name+'" >'
				 + '</div>'
				 + '<div style="clear:both;" class="hide" id="hide'+fileIndex+'"><input id="fileCounts'+fileIndex+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles"+bNo).append(html);
		   
		    $('#budgetFilesDiv'+no).hide();
		    var num = (splt-spli1);
		    var posNum = (num < 0) ? num * -1 : num; // if num is negative multiple by negative one ... 
		    var attr = $('#hide'+no+' input').attr('name');
		    
		    if (typeof attr == 'undefined' || attr == false) {
		      	   s = 1;
				    $('#hide'+fileIndex+' input').last().val(s);

		    }else{
		    	  s = $('#hide'+no+' input').val();
				    if(s == null){
				    	s = 0;
				    }
				    var d = $("#hide"+no+" input").attr("id");
				    if(d != null){
				    	 var v = $("#"+d).val();
						 var splt2 = d.split('s')[1];
				    }else{
				    	v = 0;
				    }
				    var lastfieldsid = $('#hide'+fileIndex+' input').last().val(Number(v)+1);

		    }
		    $('#hide'+splt2+' input').removeAttr('name');
		    $('#hideVal'+bNo+' input').removeAttr('name');
			
			//$('#budgetFileNames'+bNo).removeAttr('name');
			if(s != null){
				$('#hide'+count+' input').removeAttr('name');
				
			}
			
			moreFilesUpdate(no,bNo);
		}
		
		function moreFilesUpdate(no,bNo){
			var html = "";
			var count = no;
			 var fileIndex = Number(no)+1;
			/* if(no >1 ){
				var rNo=(no-1)
				$("#fileCounts"+rNo).removeAttr('value');
			} */
			html =  html + '<div class="file-field input-field" id="budgetFilesDiv'+fileIndex+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="budgetFiles'+fileIndex+'" name="budgetFiles"  onchange="selectFileUpdate('+fileIndex+','+bNo+')">'
			

			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput"+bNo).append(html);
		}
		function moreFiles(no){
			var html = "";
			var count = no;
			/* if(no >1 ){
				var rNo=(no-1)
				$("#fileCounts"+rNo).removeAttr('value');
			} */
			html =  html + '<div class="file-field input-field" id="budgetFilesDiv'+no+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="budgetFiles'+no+'" name="budgetFiles"  onchange="selectFile('+no+')">'
			

			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput").append(html);
		}
		
		function removeFileUpdate(no,bNo){			
	   	//$('#budgetFilesDiv'+no).remove();
	   	$('#budgetFilesNames'+no).remove();
	   	var id = Number(no)-1;
	   	$('#hide'+id+' input').attr('name', 'filecounts');
	   	var bId = $('#hide'+id+' input').val();
	   	if(bId == null){
			var html = '<input id="fileCounts'+bNo+'"  name="filecounts" value="0" type="hidden">'
	   		$('#hideVal'+bNo).append(html);
	   	}
	   	$('#hide'+no+' input').removeAttr('name');
	   	
	  } 
		function removeFile(no,bNo){			
		   	$('#budgetFilesDiv'+no).remove();
		   	$('#budgetFilesNames'+no).remove();
		   	var id = Number(no)-1;
		   	$('#hide'+id+' input').attr('name', 'filecounts');
		   	var bId = $('#hide'+id+' input').val();
		   	if(bId == null){
		   		$('#hideVal'+id+' input').attr('name', 'filecounts');
		   	}
		   	$('#hide'+no+' input').removeAttr('name');
		   	
		  } 
		
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
           // $('#remarks').characterCounter();
            
            /* var projectId = "${budgetDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            } */
        });
     
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForBudgetForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var workId = "${budgetDetails.work_id_fk}";
                                if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
        }
        
        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
        }

        function addBudgetRow(){
    	    var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var value = rNo+1;
        	var html ='<tr id="budgetRow'+rNo+'"><td data-head="Financial Year" class="input-field"> <input type="hidden" name="budget_ids" id="budget_ids'+rNo+'" />'
		        		+'<select  name="financial_year_fks" id="financial_year_fks'+rNo+'" class="validate-dropdown searchable" >'
							+'<option value="">Select Financial Year</option>' 
							<c:forEach var="obj" items="${financialYearList}">
								+'<option value="${obj.financial_year }">${obj.financial_year }</option>'
							</c:forEach>
						+'</select></td>'
			            +'<td data-head="Budget Estimate (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="budget_estimates'+rNo+'" type="number" name="budget_estimates"'
						    +'class="validate" placeholder="Amount" min="0.01" step="0.01"></td>'
			            +'<td data-head="Revised Estimate (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="revised_estimates'+rNo+'" name="revised_estimates" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount" ></td>'
			            +'<td data-head="Final Estimate (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="final_estimates'+rNo+'" name="final_estimates" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'
			            +'<td data-head="Budget Grant (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="budget_grants'+rNo+'" name="budget_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'
			            +'<td data-head="Revised Grant (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="revised_grants'+rNo+'" name="revised_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'
			            +'<td data-head="Final Grant (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="final_grants'+rNo+'" name="final_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'
			            +'<td data-head="Attachment" class="cell-disp-inb">  <div id="selectedFilesInput'+rNo+'"><div class="file-field input-field" id="budgetFilesDivs'+rNo+1+'" >' 
			            + '<div class="btn bg-m t-c"> <span>Attach Files</span>'
                            +'<input type="hidden"  name="budgetFileNames" value=""><input type="file" id="budgetFiles'+rNo+1+'" name="budgetFiles"   onchange="selectFiles('+rNo+1+','+rNo+')"></div>'
                            +' <div class="file-path-wrapper">'
                        +' <input class="file-path validate" type="text">'
                        +' </div></div></div><div id="selectedFiles'+rNo+'" class="disp-in"><div class="hide" id="hideVal'+rNo+'"> <input id="fileCounts'+rNo+'"  name="filecounts"  type="hidden" value="0"></div></div></td>'
			            +'<td class="mobile_btn_close "><a onclick="removeBudget('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
            $('#budgetTableBody').append(html);
			$("#rowNo").val(rNo);
            $('.searchable').select2();
        }
        function selectFiles(no,rNo){
		    files = $("#budgetFiles"+no)[0].files;
		    var id = (no/10);
		    var str=id.toString();
		    var splt = str.split('.')[1];
		    var count = splt;
			var fNo = no - 1
		    var html = "";
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=budgetFilesNames'+no+'>'
				 + '<a href="#" class="filevalue" >'+$(this).get(0).files[i].name+'<span onclick="removeFiles('+no+','+rNo+')" class="attachment-remove-btn">X</span></a>'
				 + '<input type="hidden" id="budgetFileNames'+no+'" name="budgetFileNames" value="'+$(this).get(0).files[i].name+'" >'
				 + '</div>'
				 + '<div style="clear:both;" class="hide" id="hide'+no+'"><input id="fileCounts'+no+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles"+rNo).append(html);
		    
		    $('#budgetFilesDivs'+no).hide();
		    
			var fileIndex = Number(no)+1;
			var lastfieldsid = $('#hide'+no+' input').last().val(count);
			$('#hideVal'+rNo+' input').removeAttr('name');
			//$('#budgetFiles'+fNo).removeAttr('name');
			var s = $('#hide'+fNo+' input').val();
			if(s != null){
				$('#hide'+fNo+' input').removeAttr('name');
				
			}
			moreFiles1(fileIndex,rNo);
		}
		
		function moreFiles1(no,rNo){
			var html = "";
			html =  html + '<div class="file-field input-field" id="budgetFilesDivs'+no+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="budgetFiles'+no+'" name="budgetFiles"  onchange="selectFiles('+no+','+rNo+')"></div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput"+rNo).append(html);
		}
		
		function removeFiles(no,rNo){			
	   	$('#budgetFilesDiv'+no).remove();
	   	$('#budgetFilesNames'+no).remove();
		var id = Number(no)-1;
	    $('#hide'+id+' input').attr('name', 'filecounts');
		var bId = $('#hide'+id+' input').val();
	   	if(bId == null){
	   		var html = '<input id="fileCounts'+rNo+'"  name="filecounts" value="0" type="hidden">'
	   		$('#hideVal'+rNo).append(html);
	   	}
		
	   	$('#hide'+no+' input').removeAttr('name');
	   	
	   } 
		
		function removeBudget(rowNo){
			$("#budgetRow"+rowNo).remove();
		}
		
		
        function addBudget(){
        	 if(validator.form()){ // validation perform
	        	$(".page-loader").show();	
	        	$('form input[name=financial_year_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=budget_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=final_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=budget_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=final_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	   			document.getElementById("budgetForm").submit();			
   	 	 }
        }
        function updateBudget(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	
	        	$('form input[name=financial_year_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=budget_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=final_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=budget_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=final_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	   			document.getElementById("budgetForm").submit();	
        	}
        }
        
      
        var validator =	$('#budgetForm').validate({
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		   "project_id_fk": {
	  			 		  required: true
	  			 	  },"work_id_fk": {
	  			 		  required: true
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		   "project_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"work_id_fk": {
	  			 		  required: 'Required'
	  			 	  }		
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "project_id_fk" ){
					     document.getElementById("project_id_fkError").innerHTML="";
				 	     error.appendTo('#project_id_fkError');
					 }else if(element.attr("id") == "work_id_fk" ){
					     document.getElementById("work_id_fkError").innerHTML="";
				 	     error.appendTo('#work_id_fkError');
					 }else{
	 					 error.insertAfter(element);
			        } 
		   		},submitHandler:function(form){
			    	//form.submit();
			    }
			});   
      
	       $('select').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	       });
	
	       $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	       }); --%>
	       
    </script>


</body>

</html>