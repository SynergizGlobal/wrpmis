<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Progress Bulk Update</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
     <style>
        /* body {
            overflow-x: hidden;
        } */

        .hiddendiv.common {
            width: 99vw !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::after {
            background-color: #2E58AD !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::before,
        [type="radio"].with-gap:checked+span::after {
            border: 2px solid #2E58AD !important;
        }

        .primary-text {
            color: #2E58AD;
            font-weight: 500;
        }

        /* dots related styling  */

        /* horizontal line*/

        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            position: relative;
        }

        /* selects toggle class */

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        #dotgroup1 .dotgroup-scroll {
            width: 100%;
            max-width: 100%;
            height: 100px;
            padding-top: 30px;
            overflow-x: auto;
            white-space: nowrap;

        }

        #dotgroup1 .horizontal-line {
            border: 1px solid #777;
            position: relative;
            bottom: -19px;
            height: 8px;
            box-shadow: 0 0 3px inset #555;
        }

        #dotgroup1 .dot-container {
            position: relative;
            display: inline-block;
        }

        #dotgroup1 .dot-line {
            width: 30px;
            border: 2px solid #777;
            position: absolute;
            top: 14px;
            left: -17px;
            z-index: 0;
        }

        #dotgroup1 .dot {
            height: 30px;
            width: 30px;
            z-index: 1;
            background-color: #bbb;
            color: #333;
            border-radius: 50%;
            border: 1px solid #bbb;
            display: inline-block;
            position: relative;
            margin: 0 12px;
        }

        .dot.active {
            box-shadow: 0px 0px 14px 6px #444, 0px 0px 25px 1px #777;
            border: 1px solid black !important;
        }

        .dot.active .project {
            font-weight: bold;
        }

        #dotgroup1 .project::before {
            content: none;
        }

        #dotgroup1 a .project.odd {
            position: relative;
            top: 30px;
            /* left: 4px; */
            font-size: 0.75rem;
        }

        #dotgroup1 a .project.even {
            position: relative;
            bottom: 20px;
            /* left: 4px; */
            font-size: 0.75rem;
        }

        #dotgroup1 .dot.not-started {
            background-color: #fff;
        }

        #dotgroup1 .dot.in-progress {
            background-color: #FD7E29;
        }


        #dotgroup1 .dot.completed {
            background-color: #05a705;
        }


        #dotgroup1 .dot.delayed {
            background-color: #f00;
        }

        .page-loader {
            background: #332e2ec2 !important;
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
        }

        .preloader-wrapper {
            top: 45% !important;
            left: 47% !important;
        }

        .error-msg label {
            color: red !important;
        }

        .input-field .searchable_label {
            font-size: .85rem;
        }
        .fixed-width {
            width: 100%;
            overflow: auto;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        thead th input[type="checkbox"]+span:not(.lever):before{
            border: 2px solid #fff;
        }
        thead th input[type="checkbox"]:checked+span:not(.lever):before{
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }
    </style>
     <style>
       
        .legends {
            padding: 2px;
        }

        .box,
        .description {
            display: inline-block;
            margin-left: 3px;
            margin-right: 3px;
            vertical-align: middle;
        }

        .box {
            width: 20px;
            height: 20px;
            border-radius:50%;
            background-color: #fff;
            border: 1px solid #ccc;
        }

        .box.not-started {
            background-color: #fff;
        }

        .box.in-progress {
            background-color: #f60;
        }

        .box.completed {
            background-color: #05a705;
        }

        .box.delayed {
            background-color: #f00;
        }

        @media only screen and (max-width: 700px) {
            .legends .col {
                text-align: left;
            }
        }
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
                                <h6>Progress Bulk Update Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <c:if test="${not empty success}">
				        <div class="center-align m-1 close-message">	
						   ${success}
						</div>
				    </c:if>
				    <c:if test="${not empty error }">
						<div class="center-align m-1 close-message">
						   ${error}
						</div>
				    </c:if>
                    <form action="<%=request.getContextPath() %>/update-progressBulk" id="ProgressBulkUpdateForm" name="ProgressBulkUpdateForm" method="post" >
                    <div class="container container-no-margin">
                        <div class="row">                          
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">
                                    <div class="row">
                                        <div class="col m4 s12 input-field">
                                            <p>Project</p>
                                            <select class="searchable validate-dropdown" id="project_id" name="project_id"
                                                onchange="getProgressBulkUpdateWorksList(this.value);">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${projectsList }">
                                                    <option value="${obj.project_id }">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="project_idError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m8 s12 input-field">
                                            <p>Work</p>
                                            <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                                onchange="getProgressBulkUpdateContractsList(this.value);">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${worksList }">
                                                    <option value="${obj.work_id }">${obj.work_id}<c:if test="${not empty obj.work_name}"> - </c:if> ${obj.work_name }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="work_id_fkError" class="error-msg" ></span>
                                        </div>
                                       <div class="col m12 s12 input-field">
                                            <p>Contract</p>
                                            <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown"
                                                onchange="resetWorksAndProjectsDropdowns();getProgressBulkUpdateStructures(); getProgressBulkUpdateLines(); getProgressBulkUpdateSections();">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${contractsList }">
                                                	<option name="${obj.work_id_fk }" value="${obj.contract_id }" >${obj.contract_id}<c:if test="${not empty obj.contract_name}"> - </c:if>${obj.contract_name}</option>
                                                </c:forEach>
                                            </select>
                                            <span id="contract_id_fkError" class="error-msg" ></span>
                                        </div>
                                    </div>
                                    <div class="row" id="toggle-selects">
                                        <div class="col m4 s12 input-field" >
                                            <p class="searchable_label">Structure</p>
                                           <select id="strip_chart_structure_id_fk" name="strip_chart_structure_id_fk"
                                                class="searchable validate-dropdown" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_structure_id_fkError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m4 s12 input-field" id="strip_chart_line_id_fkDiv" style="display: none;">
                                            <p class="searchable_label">Line</p>
                                            <select id="strip_chart_line_id_fk" name="strip_chart_line_id_fk"
                                                class="searchable validate-dropdown" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field" id="strip_chart_section_id_fkDiv" style="display: none;">
                                            <p class="searchable_label">Section</p>
                                            <select id="strip_chart_section_id_fk" name="strip_chart_section_id_fk"
                                                class="searchable validate-dropdown" onchange="getComponentIdsList();">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                    </div>
                                    
                                    <div class="row" style="margin-bottom: 20px;display:none;" id="component_circles_row">
                                        <div class="col m12 s12" id="dotgroup1">
                                            <div class="dotgroup-scroll">
                                                <div id="component_circles" style="padding: 10px;">
                                                    <!-- <div class="dot-container">
                                                        <a href="javascript:void(0);" class="dot"
                                                            style="margin-left: 0;">
                                                            <span class="project odd">P2P4P2P4</span></a>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot in-progress">
                                                            <span class="project even">A2A4A2A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot completed"><span
                                                                class="project odd">P1P4P1P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot delayed"><span
                                                                class="project even">P2P4P2P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot not-started active"><span
                                                                class="project odd">P3A4P3A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot in-progress">
                                                            <span class="project even">A2A4A2A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot completed"><span
                                                                class="project odd">P1P4P1P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot delayed"><span
                                                                class="project even">P2P4P2P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot not-started"><span
                                                                class="project odd">P3A4P3A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot in-progress">
                                                            <span class="project even">A2A4A2A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot completed"><span
                                                                class="project odd">P1P4P1P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot delayed"><span
                                                                class="project even">P2P4P2P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot not-started"><span
                                                                class="project odd">P3A4P3A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot in-progress">
                                                            <span class="project even">A2A4A2A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot completed"><span
                                                                class="project odd">P1P4P1P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot delayed"><span
                                                                class="project even">P2P4P2P4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div>
                                                    <div class="dot-container">
                                                        <a href="#" class="dot not-started"><span
                                                                class="project odd">P3A4P3A4</span></a>
                                                        <span class="dot-line"></span>
                                                    </div> -->

                                                </div>
                                            </div>
                                        </div>
                                    </div> 

									<div class="row legends" id="legends" style="display:none;">
                                        <div class="col m3 s6 center-align">
                                            <span class="box not-started"></span>
                                            <span class="description">Not Started</span>
                                        </div>
                                        <div class="col m3 s6 center-align">
                                            <span class="box in-progress"></span>
                                            <span class="description">In Progress</span>
                                        </div>
                                        <div class="col m3 s6 center-align">
                                            <span class="box completed"></span>
                                            <span class="description">Completed</span>
                                        </div>
                                        <div class="col m3 s6 center-align">
                                            <span class="box delayed"></span>
                                            <span class="description">Delayed</span>
                                        </div>
                                    </div>

                                    <div class="row">                                     
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Component ID</p>
                                             <select class="searchable validate-dropdown" id="strip_chart_component_id" name="strip_chart_component_id" onchange="getComponentAndActivitiesList(this.value);">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_component_idError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Component</p>
                                            <input id="strip_chart_component" name="strip_chart_component" type="text" style="height: 2rem;" readonly="readonly">
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p class="searchable_label">Activity</p>
                                            <select id="strip_chart_activity_id" name="strip_chart_activity_id"
                                                class="searchable validate-dropdown"
                                                onchange="getStripChartfilterList();">
                                                <option value="">Select</option>
                                            </select>
                                            <span id="strip_chart_activity_idError" class="error-msg"></span>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col m2 hide-on-small-only"></div>
                                        <div class="col m8 s12 center-align" style="margin-bottom: 30px;margin-top: 10px;">
                                            <a class="btn waves-effect bg-m" onclick="updateActual()">Finish Activities</a>
                                        </div>
                                        <div class="col m2 hide-on-small-only"></div>
                                    </div>
									<span id="checkBoxError" class="error-msg" style="text-align:center"></span>
                                    <div class="row fixed-width" id="table_show" style= "display:none;">					 <!-- style= "display:none;" -->
                                        <div class="table-inside">
                                            <table class="mdl-data-table" id="table">
                                                <thead>
                                                    <tr>
                                                        <th>
                                                            <p>
                                                                <label>
                                                                  <input type="checkbox" name="select-all" id="select-all"/>
                                                                  <span></span>
                                                                </label>
                                                              </p>
                                                        </th>
                                                        <th>Component <br>ID</th>
                                                        <th>Component</th>
                                                        <th>Activity</th>
                                                        <th>Planned <br> Start</th>
                                                        <th>Planned <br> Finish</th>
                                                        <!-- <th>A S</th>
                                                        <th>A F</th> -->
                                                        <th>Scope</th>
                                                        <th>Completed</th>
                                                        <th>Actual</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="filerList">
                                                <!-- 
                                                    <tr >
                                                        <td>
                                                            <p>
                                                                <label>
                                                                  <input type="checkbox" name="activity_check" id="check_1"/>
                                                                  <span></span>
                                                                </label>
                                                              </p>
                                                        </td>
                                                        <td>1</td>
                                                        <td>2</td>
                                                        <td>3</td>
                                                        <td>4</td>
                                                        <td>5</td>
                                                        <td>6</td>
                                                        <td><span id="scope1">10</span></td>
                                                        <td><span id="completed1">7</span></td>                                                       
                                                        <td class="input-field">
                                                            <input type="text" id="actual1" readonly>
                                                        </td> 
                                                    </tr> -->
                                                  
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
								<div class="container container-no-margin" >
                                    <div class="row">
                                        <div class="col m12 s12 input-field">
                                            <textarea id="remarks" name="remarks" class="materialize-textarea"
                                                data-length="500"></textarea>
                                            <label for="remarks" class="">Remarks</label>
                                        </div>
                                    </div>
                                    <input type="hidden" id="strip_chart_id" name="strip_chart_id" />
                                    <div class="row">
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="button" onclick="updateProgress();" class="btn waves-effect waves-light bg-m"
                                                    style="width: 100%;" >Update</button>
                                            </div>
                                        </div>
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="reset" class="btn waves-effect waves-light bg-s"
                                                    style="width: 100%;">Reset</button>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                        </div>
                            </form>
                    </div>
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
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
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
            $('.searchable').select2();
            
            $('#progress_date_icon').click(function () {
                event.stopPropagation();
                $('#progress_date').click();
            });

            $('#progress_date').datepicker({
                maxDate: new Date(),
                format: 'dd-mm-yyyy',
                //perform click event on done button
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            });

            $('#remarks').characterCounter();

        });
        // update actual function for single value with out ids
       /*  function updateActual(){
            $('input[name="activity_check"]').each(function(){
                if($(this).prop('checked')){  
                    var scope_val=parseInt($(this).parent().closest('tr').find('td:last-of-type').prev().prev().children().text());
                    var completed_val=parseInt($(this).parent().closest('tr').find('td:last-of-type').prev().children().text());
                    var remaining=scope_val-completed_val;
                    var actual_val=$(this).parent().closest('tr').find('td:last-of-type').children().val(remaining);
                }
            })           
        } */

       
	// select or deselect all checkboxes 
	$('#select-all').change(function() {
	    var _this = this;
	    $('input[name="activity_check"]').each(function() { 
		    if ($(_this).is(':checked')) {
		        $(this).prop('checked', true);
		    } else {
		        $(this).prop('checked', false);
		    }
	    });
	});
	
	function getProgressBulkUpdateWorksList(projectId) { 
		$(".page-loader").show();
		$("#contract_id_fk option:not(:first)").remove();    	
	    $("#work_id_fk option:not(:first)").remove();
	    
	    $("#strip_chart_structure_id_fk option:not(:first)").remove();
	    $("#strip_chart_line_id_fk option:not(:first)").remove();
	    $("#strip_chart_section_id_fk option:not(:first)").remove();
		
		$('.searchable').select2();
		clearComponentCircle();
		
	
	    if ($.trim(projectId) != "") {
	        var myParams = { project_id_fk: projectId };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateWorksList",
	            data: myParams, cache: false,
	            success: function (data) {
	            	var id1 = "";
	                var id2 = "";
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var workName = '';
	                        if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
	                        if ($.trim(id2) != '' && val.work_id == $.trim(id2)) {
	                        	id1 = val.work_id;
	                            $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
	                        } else {
	                            $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader").hide();
	                
	                if ($.trim(id1) != '' && $.trim(id2) != '') {
	                	getProgressBulkUpdateContractsList(id2);
	                }
	            }
	        });
	    }else{
	    	$(".page-loader").hide();
	    }
	}
	
	//geting contracts list    
	function getProgressBulkUpdateContractsList(work_id_fk) {
		$(".page-loader").show();
	    $("#contract_id_fk option:not(:first)").remove();
	    
	    $("#strip_chart_structure_id_fk option:not(:first)").remove();
	    $("#strip_chart_line_id_fk option:not(:first)").remove();
	    $("#strip_chart_section_id_fk option:not(:first)").remove();
		$('.searchable').select2();
		clearComponentCircle();
		
	    if ($.trim(work_id_fk) != "") {
	        var myParams = { work_id_fk: work_id_fk };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateContractsList",
	            data: myParams, cache: false,
	            success: function (data) {
	            	var id1 = "";
	            	var id2 = "";                        
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var contract_name = '';
	                        if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
	                        if ($.trim(id2) != '' && val.contract_id == $.trim(id2)) {
	                        	id1 = val.contract_id;
	                            $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '" selected>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
	                        } else {
	                            $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader").hide();
	                
	                if ($.trim(id1) != '' && $.trim(id2) != '') {
	                	getProgressBulkUpdateStructures(id2);
	                }
	            }
	        });
	    }else{
	    	$(".page-loader").hide();
	    }
	}
	
	function resetWorksAndProjectsDropdowns(){
		$(".page-loader").show();
		clearComponentCircle();
		
		
		var projectId = '';
		var workId = ''
			var contract_id_fk = $("#contract_id_fk").val();
			if($.trim(contract_id_fk) != ''){        			
				workId = $("#contract_id_fk").find('option:selected').attr("name");
				projectId = workId.substring(0, 3);    
				//workId = workId.substring(3, work_id.length);
				$("#project_id").val(projectId);
				$("#project_id").select2();
			}
			
			if ($.trim(projectId) != "") {
				$("#work_id_fk option:not(:first)").remove();
	        var myParams = { project_id_fk: projectId };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateWorksList",
	            data: myParams, cache: false,
	            success: function (data) {
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var workName = '';
	                        if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
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
	        $('.searchable').select2();
	    }
			
	}
	
    function clearComponentCircle(){        	
     	$("#strip_chart_component").attr("readonly", false); 
     	$("#strip_chart_component").val('');
     	$("#strip_chart_component").attr("readonly", true);
     	
     	$("#strip_chart_component_id option:not(:first)").remove();
     	$("#strip_chart_activity_id option:not(:first)").remove();
     	
     	$('.searchable').select2();
     	
         $("#component_circles").html('');
         $("#component_circles_row").hide();
         $("#legends").hide();  
         $("#table_show").hide();    	
     } 
	
	  function getProgressBulkUpdateStructures() {
      	$(".page-loader").show();
      	var contract_id_fk = $("#contract_id_fk").val();
          $("#strip_chart_structure_id_fk option:not(:first)").remove();
          if ($.trim(contract_id_fk) != "") {
          	var myParams = { contract_id_fk: contract_id_fk };
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateStructures",
                  data: myParams, cache: false,
                  success: function (data) {
                  	var id1 = "";
                  	var id2 = "";
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
	                            if ($.trim(id2) != '' && val.strip_chart_structure_id_fk == $.trim(id2)) {
	                            	id1 = val.strip_chart_structure_id_fk;
	                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '" selected>' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            } else {
	                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '">' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            }
                          });
                      }
                      $('.searchable').select2();
                      $(".page-loader").hide();
                      
                      
                      if ($.trim(id1) != '' && $.trim(id2) != '') {
                      	getComponentIdsList(id2);
                      }
                  }
              });
          }else{
          	$(".page-loader").hide();
          }
      }
	  
	  
	function getProgressBulkUpdateLines() {
		var contract_id_fk = $("#contract_id_fk").val();
	    $("#strip_chart_line_id_fk option:not(:first)").remove();
	    if ($.trim(contract_id_fk) != "") {
	    	var myParams = { contract_id_fk: contract_id_fk};
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateLines",
	            data: myParams, cache: false,
	            success: function (data) {
	                if (data.length > 0) {
	                	$("#strip_chart_line_id_fkDiv").show();
	                    $.each(data, function (i, val) {
	                        $("#strip_chart_line_id_fk").append('<option value="' + val.strip_chart_line_id_fk + '">' + $.trim(val.strip_chart_line_id_fk) + '</option>');
	                    });
	                }else{
	                	$("#strip_chart_line_id_fkDiv").hide();
	                }
	                $('.searchable').select2();
	            }
	        });
	    }
	}
	
	function getProgressBulkUpdateSections() {
		var contract_id_fk = $("#contract_id_fk").val();
	    $("#strip_chart_section_id_fk option:not(:first)").remove();
	    if ($.trim(contract_id_fk) != "") {
	    	var myParams = { contract_id_fk: contract_id_fk};
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateSections",
	            data: myParams, cache: false,
	            success: function (data) {
	                if (data.length > 0) {
	                	$("#strip_chart_section_id_fkDiv").show();
	                    $.each(data, function (i, val) {
	                        $("#strip_chart_section_id_fk").append('<option value="' + val.strip_chart_section_id_fk + '">' + $.trim(val.strip_chart_section_name) + '</option>');
	                    });
	                }else{
	                	$("#strip_chart_section_id_fkDiv").hide();
	                }
	                $('.searchable').select2();
	            }
	        });
	    }
	}

	 function getComponentIdsList() {   
     	$(".page-loader").show();
     	
     	clearComponentCircle();
         
         var contract_id_fk = $("#contract_id_fk").val();
         var structureId = $("#strip_chart_structure_id_fk").val();
         var laneId = $("#strip_chart_line_id_fk").val();
         var sectionId = $("#strip_chart_section_id_fk").val();
         var myParams = { contract_id_fk: contract_id_fk, strip_chart_structure_id_fk: structureId, strip_chart_line_id_fk: laneId, strip_chart_section_id_fk: sectionId };
         var html = '';

         if ($.trim(contract_id_fk) != "" && $.trim(structureId) != "" ) {                
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateComponentIdsList",
                 data: myParams, cache: false,
                 success: function (data) {
                 	var id1 = "";
                 	var id2 = "";
                     var strip_chart_component = "";
                     
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                         	var componentIdAndName = "'" + val.strip_chart_component_id + "','" +val.strip_chart_component+ "'";
                             var className = "odd";
                             if(i%2 == 0){
                             	className = "even";
                             }
                             
                             var pointerEvent = "";
                             if(val.component_id_color == "completed"){
                             	pointerEvent = "pointer-events: none;";
                             	html = html + '<div class="dot-container" id="dd'+val.strip_chart_component_id+'">'
                                 + '<a href="javascript:void(0);" id="'+val.strip_chart_component_id+'" style="'+pointerEvent+'" onclick="getProgressBulkUpdateActivitiesList('+componentIdAndName+');" class="dot '+val.component_id_color+' clearData" >'
                                 + '<span class="project '+className+'" >'+val.strip_chart_component_id_name+'</span></a>';
                                 if(i != 0){
                                 	html = html + '<span class="dot-line"></span>';
                                 }
                                 html = html + '</div>';
                             	
                             	$("#strip_chart_component_id").append('<option name="' + val.strip_chart_component + '" value="' + val.strip_chart_component_id + '" disabled>' + $.trim(val.strip_chart_component_id_name) + '</option>');
                             } else {                
                             	
                             	html = html + '<div class="dot-container" id="dd'+val.strip_chart_component_id+'">'
                                 + '<a href="javascript:void(0);" id="'+val.strip_chart_component_id+'" style="'+pointerEvent+'" onclick="getProgressBulkUpdateActivitiesList('+componentIdAndName+');" class="dot '+val.component_id_color+' clearData" >'
                                 + '<span class="project '+className+'">'+val.strip_chart_component_id_name+'</span></a>';
                                 if(i != 0){
                                 	html = html + '<span class="dot-line"></span>';
                                 }
                                 html = html + '</div>';
                             	
                             	if ($.trim(id2) != '' && val.strip_chart_component_id == $.trim(id2)) {
                             		id1 = val.strip_chart_component_id;
 	                            	$("#strip_chart_component_id").append('<option name="' + val.strip_chart_component + '" value="' + val.strip_chart_component_id + '" selected>' + $.trim(val.strip_chart_component_id_name) + '</option>');
 	                            } else {
 	                            	$("#strip_chart_component_id").append('<option name="' + val.strip_chart_component + '" value="' + val.strip_chart_component_id + '">' + $.trim(val.strip_chart_component_id_name) + '</option>');
 	                            }
                             }                                
                         });
                         
                         $('.searchable').select2();
                         getStripChartfilterList();
                     }
                     $("#component_circles").html(html);
                     $("#component_circles_row").show();
                     $('#legends').show();
                     $(".page-loader").hide();
                     
                     
                     if ($.trim(id1) != '' && $.trim(id2) != '') {
                     	getProgressBulkUpdateActivitiesList(id2,strip_chart_component);
                     }
                 }
             });
         }else{
         	$(".page-loader").hide();
         	$("#component_circles").html(html);
             $("#component_circles_row").hide();
             $('#legends').hide();
         }
     }
	 
	 function getProgressBulkUpdateActivitiesList(componentId,componentName) {
     	$( ".dot" ).removeClass( "active" );
     	$( "#"+componentId ).addClass( "active" );
     	
     	/* $("#strip_chart_component option:not(:first)").remove();
     	$("#strip_chart_component").append('<option value="' + componentName + '" selected>' + $.trim(componentName) + '</option>');
     	$('.searchable').select2(); */
     	
     	$("#strip_chart_component").attr("readonly", false); 
     	$("#strip_chart_component").val(componentName);
     	$("#strip_chart_component").attr("readonly", true); 
     	
     	$("#strip_chart_component_id").val(componentId);
     	$(".page-loader").show();
         $("#strip_chart_activity_id option:not(:first)").remove();
         
         var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
         var strip_chart_line_id_fk = $("#strip_chart_line_id_fk").val();
         var strip_chart_section_id_fk = $("#strip_chart_section_id_fk").val();
         
         if ($.trim(componentId) != "") {
             var myParams = { strip_chart_component_id: componentId,strip_chart_component : componentName,
             		strip_chart_line_id_fk : strip_chart_line_id_fk,strip_chart_structure_id_fk : strip_chart_structure_id_fk,
             		strip_chart_section_id_fk : strip_chart_section_id_fk };
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateActivitiesList",
                 data: myParams, cache: false,
                 success: function (data) {
                 	var id1 = "";
                 	var id2 = "";
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
	                            if ($.trim(id2) != '' && val.strip_chart_activity_id == $.trim(id2)) {
	                            	id1 = val.strip_chart_activity_id;
	                                $("#strip_chart_activity_id").append('<option value="' + val.strip_chart_activity_id + '" selected>' + $.trim(val.strip_chart_activity_name) + '</option>');
	                            } else {
	                                $("#strip_chart_activity_id").append('<option value="' + val.strip_chart_activity_id + '">' + $.trim(val.strip_chart_activity_name) + '</option>');
	                            }
                         });
                     }
                     $('.searchable').select2();
                     $(".page-loader").hide(); 
                     getStripChartfilterList();
                 }
             });
         }else{
         	$(".page-loader").hide();
         }
     }
	 
	 function getComponentAndActivitiesList(componentId){
		getStripChartfilterList();
     	$( ".dot" ).removeClass( "active" );
     	$( "#"+componentId ).addClass( "active" );
     	
     	var $scroller = $('.dotgroup-scroll');
         var childs=$scroller.children().children().length;
         var indexing=$(".dot-container").index($("#dd"+componentId));
        	var scrollTo=Math.round((indexing*($scroller[0].scrollWidth/childs))-childs);           
         $scroller.animate({'scrollLeft': scrollTo}, 1000);  
                     
     	var componentName = $("#strip_chart_component_id").find('option:selected').attr("name");
     	
     	/* $("#strip_chart_component option:not(:first)").remove();
     	$("#strip_chart_component").append('<option value="' + componentName + '" selected>' + $.trim(componentName) + '</option>');
     	$('.searchable').select2(); */
     	$("#strip_chart_component").attr("readonly", false); 
     	$("#strip_chart_component").val(componentName);
     	$("#strip_chart_component").attr("readonly", true);
     	
     	$(".page-loader").show();
         $("#strip_chart_activity_id option:not(:first)").remove();
         
         var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
         var strip_chart_line_id_fk = $("#strip_chart_line_id_fk").val();
         var strip_chart_section_id_fk = $("#strip_chart_section_id_fk").val();
         
         if ($.trim(componentId) != "") {
             var myParams = { strip_chart_component_id: componentId,strip_chart_component : componentName,
             		strip_chart_line_id_fk : strip_chart_line_id_fk,strip_chart_structure_id_fk : strip_chart_structure_id_fk,
             		strip_chart_section_id_fk : strip_chart_section_id_fk };
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getProgressBulkUpdateActivitiesList",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                             $("#strip_chart_activity_id").append('<option value="' + val.strip_chart_activity_id + '">' + $.trim(val.strip_chart_activity_name) + '</option>');
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
     
	
     function getStripChartfilterList(){
    	 $(".page-loader").show();
    	 $("#table_show").show();
    	 var html = '';
    	 $("#filerList").html('');
    	 
    	 $("#select-all").prop('checked', false);
    	 
    	 var strip_chart_component_id = $("#strip_chart_component_id").val();
    	 var strip_chart_activity_id = $("#strip_chart_activity_id").val();
    	 var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
    	 var contract_id_fk = $("#contract_id_fk").val();
    	 if ($.trim(strip_chart_structure_id_fk) != "") {
 	        var myParams = { strip_chart_component_id: strip_chart_component_id, strip_chart_activity_id: strip_chart_activity_id,strip_chart_structure_id_fk : strip_chart_structure_id_fk, contract_id_fk : contract_id_fk };
 	        $.ajax({
 	            url: "<%=request.getContextPath()%>/ajax/getStripChartfilterList",
 	            data: myParams, cache: false,
 	            success: function (data) {
 	            	
 	                if (data.length > 0) {
 	                    $.each(data, function (i, val) {
 	                    	
 	                    	 var num = $('#table tbody tr').length;
 	                    	 html = '<tr id="row'+num+'"><td><p><label><input type="checkbox" class="check" name="activity_check" id="check_'+num+'"/><span></span></label></p></td>'
 	                    		+'<input type="hidden" name="strip_chart_ids"  id="strip_chart_id'+num+'"  value="' + $.trim(val.strip_chart_id) + '" /></td>'
 	            	 			+'<td>' + $.trim(val.strip_chart_component_id_name) + '</td>'
 	            	 			+'<td>' + $.trim(val.strip_chart_component) + '</td>'
 	            	 			+'<td>' + $.trim(val.strip_chart_activity_name) + '</td>'
 	            	 			+'<td>' + $.trim(val.planned_start) + '</td>'
 	            	 			+'<td>' + $.trim(val.planned_finish) + '</td>'
 	            	 			+'<td><span>' + $.trim(val.scope) + '</span>'
 	            	 			+'<input type="hidden" name="totalScopes"  id="totalScopes'+num+'"  value="' + $.trim(val.scope) + '" /></td>'
 	            	 			+'<td><span>' + $.trim(val.completed) + '</span>'
 	            	 			+'<input type="hidden" name="completedScopes"  id="completedScopes'+num+'"  value="' + $.trim(val.completed) + '" /></td>'
 	            	 			+' <td class="input-field"><input type="text" name="actualScopes" id="actualScopes'+num+'" readonly></td></tr>';
 	                    		$("#filerList").append(html);	  
 	                    	 	
 	                    	 	/* $(document).on('change', '#strip_chart_component_id ,#strip_chart_activity_id', function() {  $('#filerList').empty(html); });
 	                    	 	$(document).on('click', '.clearData', function() {  $('#filerList').empty(html); }); */
 	                    	 	
 	                    	 	$("#check_"+num).change(function() {
 	                    	 		//alert("#actualScopes"+num)
 	                    	 		$("#actualScopes"+num).val('');
 	                    	 	})
 	                    	 	$("#select-all").change(function() { 
 	                    	 		if($("#check_"+num).is(':unchecked')){
 	                    	 			$("#actualScopes"+num).val('');
 	                    	 		}
 	                    	 	})
 	                    	 //	var noOfBoxes = document.getElementsByClassName("check")
	 	                    	   
	 	                    	$("input[type='checkbox'].check").change(function(){
	                    		    var a = $("input[type='checkbox'].check");
	                    		    if(a.length == a.filter(":checked").length){
	                    		    	$("#select-all").prop('checked', true);
	                    		    }
	                    		    else {
	                   		       	    $("#select-all").prop('checked', false);
	                   		   		 }
	                    		});
 	                   });
 	                }
 	                $(".page-loader").hide();
 	            }
 	        });
 	    }else{
 	    	$(".page-loader").hide();
 	    }
      
    }
    
    	
     // update actual function for single value with ids
     function updateActual(){
         $('input[name="activity_check"]').each(function(){
             if($(this).prop('checked')){    
                 let no = $(this).attr('id').split("_")[1];
                 //alert($('#totalScopes'+no).val())
                 var scope = $('#totalScopes'+no).val();
                 var completed = $('#completedScopes'+no).val();
                 if($.trim(completed) == null || completed == ''){
                	 completed = 0;
                 }
                 if($.trim(scope) != '' && $.trim(completed) != '' ){
                	 let remaining = parseInt(scope)- parseInt(completed);
                     $('#actualScopes'+no).val(remaining);
                 }
                 
             }
         })           
     }
  
     //update button functionality
     function updateProgress(){
    	 if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	   			document.getElementById("ProgressBulkUpdateForm").submit();	
     	}
     }
  
     var validator = $('#ProgressBulkUpdateForm').validate({
    	 ignore: ":hidden:not(.validate-dropdown)",
    	 rules: {
    		  "activity_check" :{
    			 required: true
    		  },"project_id": {
			 	required: false
			  },"work_id_fk": {
		 		required: false
		 	  },"contract_id_fk": {
		 		required: true
		 	  },"strip_chart_structure_id_fk": {
		 		required: true
		 	  },"strip_chart_component": {
		 		required: false
		 	  },"strip_chart_component_id": {
		 		required: true
		 	  },"strip_chart_activity_id": {
		 		required: false
		 	  }
    	 },
            messages: {
                 "activity_check": {
                    required: "You must check at least 1 box"
                 },"project_id": {
			 		required: 'Select project'
			 	 },"work_id_fk": {
		 			required: 'Select work'
		 	  	 },"contract_id_fk": {
		 			required: 'Select contract'
		 	  	 },"strip_chart_structure_id_fk": {
		 	  		required: 'Select Structure'
			 	 },"strip_chart_component": {
		 			required: 'Select component'
		 	  	 },"strip_chart_component_id": {
		 			required: 'Select component id'
		 	  	 },"strip_chart_activity_id": {
		 			required: 'Select Activity'
		 	  	 }
    	 },errorPlacement:function(error, element){
  		 	        if(element.attr("name") == "activity_check" ){
					     document.getElementById("checkBoxError").innerHTML="";
				 	     error.appendTo('#checkBoxError');
				    }else if (element.attr("id") == "project_id" ){
			 		     document.getElementById("project_idError").innerHTML="";
 			 			 error.appendTo('#project_idError');
 			 	    }else if (element.attr("id") == "work_id_fk" ){
			 		     document.getElementById("work_id_fkError").innerHTML="";
			 			 error.appendTo('#work_id_fkError');
			 	    }else if (element.attr("id") == "contract_id_fk" ){
			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
			 			 error.appendTo('#contract_id_fkError');
			 	    }else if (element.attr("id") == "strip_chart_structure_id_fk" ){
   			 	    	 document.getElementById("strip_chart_structure_id_fkError").innerHTML="";
			 			 error.appendTo('#strip_chart_structure_id_fkError');
		 	    	}else if (element.attr("id") == "strip_chart_component" ){
			 		     document.getElementById("strip_chart_componentError").innerHTML="";
			 			 error.appendTo('#strip_chart_componentError');
			 	    }else if (element.attr("id") == "strip_chart_component_id" ){
			 		     document.getElementById("strip_chart_component_idError").innerHTML="";
			 			 error.appendTo('#strip_chart_component_idError');
			 	    }else if (element.attr("id") == "strip_chart_activity_id" ){
			 		     document.getElementById("strip_chart_activity_idError").innerHTML="";
			 			 error.appendTo('#strip_chart_activity_idError');
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
 	});
    </script>
</body>
</html>