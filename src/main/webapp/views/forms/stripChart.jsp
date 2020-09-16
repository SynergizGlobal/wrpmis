<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Strip Chart</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
   <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
   
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link rel="stylesheet" href="/pmis/resources/css/la.css">

   
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    
   
   
    <style>
        .input-field .select2-container--default {
            width: 100% !important;
        }

        /* searchable dropdown styling */
        .select2-container--default .select2-selection--single {
            background-color: lightgrey;
            border: 1px solid #aaa;
            height: 32px;
            /* border-radius: 2px; */
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }

        /* datatable styling starts here*/
        .dataTables_filter label::after {
            position: relative;
            content: '\f002';
            color: #6C587B;
            right: 15px;
            font: normal normal normal 14px/1 FontAwesome;
        }

        .dataTables_filter label {
            color: #fff;
        }

        /* datatable stylings ends here*/
        
        
          h4 {
		    font-size: 2.28rem;
		    line-height: 110%;
		    margin: 0 0 .912rem 0;
		}
		
		.card .card-content {
		    padding: 5px 24px;
		    border-radius: 0 0 2px 2px;
		}
		.no-mar{
			margin-bottom:0;
		}
    
        .hiddendiv.common {
            width: 99vw !important;
        }

        .dot {
            height: 20px;
            width: 20px;
            background-color: #bbb;
            color: #333;
            border-radius: 50%;
            border: 1px solid #bbb;
            /* margin: 0 4px; */
            margin: 0 1.7px;
            display: inline-block;
            position: relative;
        }

        .dot:not(:first-child):before,
        .dot:not(:last-child)::after {
            content: '-';
            position: absolute;
            color: #aaa;
            color: inherit;
            bottom: -0%;
        }

        .dot::before {
            left: -6px;
        }

        .dot::after {
            right: -6px;
        }

        .dot.not-started {
            background-color: #fff;
        }

        .dot.in-progress {
            background-color: #f60;
        }

        .dot.completed {
            background-color: #05a705;
        }

        .dot.delayed {
            background-color: #f00;
        }

        .dot.no-focus {
            opacity: 0.3;
        }

        .dot .project::before {
            content: attr(data-project);
            position: absolute;
            font-size: 0.9rem;
            bottom: -20px;
            color: inherit
        }

        .dotgroup-scroll {
            width: 100%;
            max-width: 100%;
            height: 60px;
            overflow-x: auto;
            white-space: nowrap;
        }

        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            position: relative;
        }
        
         /* selects toggle class */
        .arr {
            position: absolute;
            right: -1%;
            top: 20%;
            cursor: pointer;
        }

        #toggle-selects {
            display: none;
        }

        #toggle-selects.open {
            display: block;
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
                                <h6>Strip Chart Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <div class="row">
                            <form action="<%=request.getContextPath()%>/update-stripchart" id="stripChartForm" name="stripChartForm" enctype="multipart/form-data">
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">
                                    <div class="row">
                                        <div class="col m4 s12 input-field">
                                            <p>Project</p>
                                            <select class="searchable" id="project_id" name="project_id" onchange="getWorksList(this.value);">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${projectsList }">
                                                	<option value="${obj.project_id }" <c:if test="${obj.project_id eq sessionScope.globalProjectId}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if>${obj.project_name }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col m8 s12 input-field">
                                            <p>Work</p>
                                            <select class="searchable" id="work_id_fk" name="work_id_fk" onchange="getContractsList(this.value);">
                                                <option value="" selected>Select</option>
                                            </select>
                                        </div>
                                        <div class="col m12 s12 input-field">
                                            <p>Contract</p>
                                            <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="getComponentIdsList(this.value);">
                                                <option value="">Select</option>
                                                <%-- <c:forEach var="obj" items="${contractsList }">
                                                	<option value="${obj.contract_id }" <c:if test="${obj.contract_id eq ''}">selected</c:if>>${obj.contract_name}</option>
                                                </c:forEach> --%>
                                            </select>
                                            <span class="arr"><i class="fa fa-plus-circle" style="margin-top: 15px" id="hide-btn"></i></span>
                                        </div>
                                    </div>
                                    <div class="row" id="toggle-selects">
                                        <!-- row 1  -->
                                        <div class="col m4 s12 input-field">
                                            <p>Structure</p>
                                            <select id="strip_chart_structure_id_fk" name="strip_chart_structure_id_fk" class="searchable">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${structuresList }">
                                                	<option value="${obj.strip_chart_structure }" <c:if test="${obj.strip_chart_structure eq ''}">selected</c:if>>${obj.strip_chart_structure}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p>Line</p>
                                            <select id="strip_chart_line_id_fk" name="strip_chart_line_id_fk" class="searchable">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${linesList }">
                                                	<option value="${obj.strip_chart_line }" <c:if test="${obj.strip_chart_line eq ''}">selected</c:if>>${obj.strip_chart_line}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col m4 s12 input-field">
                                            <p>Section</p>
                                            <select id="strip_chart_section_id_fk" name="strip_chart_section_id_fk" class="searchable">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${sectionsList }">
                                                	<option value="${obj.strip_chart_section_id }" <c:if test="${obj.strip_chart_section_id eq ''}">selected</c:if>>${obj.strip_chart_section_name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-bottom: 20px;">
                                        <div class="col m12 s12">
                                            <p>Project Id</p>
                                            <div class="dotgroup-scroll">
                                                <div id="dotgroup">
                                                    <a href="javascript:void(0);" class="dot"><span class="project" data-project="P2"></span></a>
                                                    <a href="#" class="dot in-progress"><span class="project" data-project="A2"></span></a>
                                                    <a href="#" class="dot completed no-focus"><span class="project" data-project="P1"></span></a>
                                                    <a href="#" class="dot delayed"><span class="project" data-project="P2"></span></a>
                                                    <a href="#" class="dot not-started"><span class="project" data-project="P3"></span></a>
                                                    <a href="#" class="dot"><span class="project" data-project="P2"></span></a>
                                                    <a href="#" class="dot in-progress"><span class="project" data-project="A2"></span></a>
                                                    <a href="#" class="dot completed"><span class="project" data-project="P1"></span></a>
                                                    <a href="#" class="dot delayed"><span class="project" data-project="P2"></span></a>
                                                    <a href="#" class="dot not-started"><span class="project" data-project="P3"></span></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">                                       
                                        <div class="col m6 s12 input-field">
                                            <input type="text" class="" id="strip_chart_component_fk" name="strip_chart_component_fk">
                                            <label for="planned_start">Component</label>
                                        </div>
                                        <div class="col m6 s12 input-field">
                                            <p>Activity</p>
                                            <select id="strip_chart_activity_id_fk" name="strip_chart_activity_id_fk" class="searchable" onchange="getStripChartDetails(this.value);">
                                                <option value="">Select</option>
                                                <%-- <c:forEach var="obj" items="${activitiesList }">
                                                	<option value="${obj.strip_chart_activity_id }" <c:if test="${obj.strip_chart_activity_id eq ''}">selected</c:if>>${obj.strip_chart_activity_name}</option>
                                                </c:forEach> --%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m6 s12 input-field">
                                            <input type="text" class="datepicker" id="planned_start" name="planned_start">
                                            <label for="planned_start">Planned Start</label>
                                            <button type="button" id="planned_start_icon"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                        <div class="col m6 s12 input-field">
                                            <input type="text" class="datepicker" id="planned_finish" name="planned_finish">
                                            <label for="planned_finish">Planned Finish</label>
                                            <button type="button" id="planned_finish_icon"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m6 s12 input-field">
                                            <input type="text" class="validate" id="scope" name="scope">
                                            <label for="scope">Scope</label>
                                            <span class="units unit_fk"></span>
                                        </div>
                                        <div class="col m6 s12 input-field">
                                            <input type="text" class="validate" id="completed" name="completed">
                                            <label for="completed_scope">Completed</label>
                                            <span class="units unit_fk"></span>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col m6 s12 input-field">
                                            <input id="progress_date" name="progress_date" type="text" class="validate datepicker">
                                            <label for="progress_date">Progress Date</label>
                                            <button type="button" id="progress_date_icon"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                        <div class="col m6 s12 input-field">
                                            <input id="progress" name="progress" type="text" class="validate">
                                            <label for="progress">Progress</label>
                                            <span class="units unit_fk"></span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m12 s12 input-field">
                                            <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="500"></textarea>
                                            <label for="remarks" class="">Remarks</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m12 s12">
                                            <div class="file-field input-field">
                                                <div class="btn bg-m">
                                                    <span>Attachment</span>
                                                    <input type="file" id="stripChartFile" name="stripChartFile">
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="submit" class="btn waves-effect waves-light bg-m" style="width: 100%;">Update</button>
                                            </div>
                                        </div>
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="reset" class="btn waves-effect waves-light bg-s" style="width: 100%;">Reset</button>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>
    


  <!-- footer included -->
  <jsp:include page="../layout/footer.jsp"></jsp:include>
    		
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>
  <script>
        // code is not required, but to add many buttons I added this 
        var dotgroup = document.getElementById('dotgroup');

        for (let i = 0; i <= 27; i++) {
            let ele = document.createElement('a');
            ele.classList.add('dot');
            let it = document.createElement('span');
            it.classList.add('project');
            ele.appendChild(it);
            dotgroup.appendChild(ele);
        }
  </script>

  <script>
        $(document).ready(function () {
            // $('select').formSelect();
            $('.searchable').select2();
            // hide or display selects by clicking 
            $(".arr").click(function () {
                $('#toggle-selects').toggleClass("open");
                $('#hide-btn').toggleClass("fa-plus-circle");
                $('#hide-btn').toggleClass("fa-minus-circle");

            });
            // $(".datepicker").datepicker();
            $("#planned_finish,#planned_start,#progress_date").datepicker();
            $('#planned_finish_icon').click(function () {
                event.stopPropagation();
                $('#planned_finish').click();
            });
            $('#planned_start_icon').click(function () {
                event.stopPropagation();
                $('#planned_start').click();
            });
            $('#progress_date_icon').click(function () {
                event.stopPropagation();
                $('#progress_date').click();
            });
            $('#remarks').characterCounter();
            $('.sidenav').sidenav();
            $('#textarea1').characterCounter();
            $('#textarea2').characterCounter();
            $('#textarea3').characterCounter();
            
            var globalProjectId = "${sessionScope.globalProjectId}";
    		if($.trim(globalProjectId) != ''){
    			getWorksList(globalProjectId);
    		}
        });
        
        
      //geting works list from database    
      function getWorksList(projectId){
    		$("#work_id_fk option:not(:first)").remove();
    		
    		if($.trim(projectId) != ""){
    			var myParams = {project_id_fk : projectId}; 
    			$.ajax({
    				url:"<%=request.getContextPath()%>/ajax/getWorksList",
    				data:myParams,cache: false,
    				success:function(data){
    					if(data.length > 0){
    						 $.each(data, function(i,val) {
    							var workName = '';
    							if($.trim(val.work_name) != ''){workName = ' - ' + $.trim(val.work_name)}
    				            var globalWorkId = "${sessionScope.globalWorkId}";
    							if($.trim(globalWorkId) != '' && val.work_id == $.trim(globalWorkId)){
    								$("#work_id_fk").append('<option value="'+val.work_id+'" selected>'+$.trim(val.work_id) + $.trim(workName)+'</option>');
    					        }else{
    					        	$("#work_id_fk").append('<option value="'+val.work_id+'">'+$.trim(val.work_id) + $.trim(workName)+'</option>');
    					        }
    				         });		
    		 			}
    					$('.searchable').select2();
    				}	 
    			});
    		}
      }
      
      //geting contracts list    
      function getContractsList(workId){
    		$("#contract_id_fk option:not(:first)").remove();    		
    		if($.trim(workId) != ""){
    			var myParams = {work_id_fk : workId}; 
    			$.ajax({
    				url:"<%=request.getContextPath()%>/ajax/getContractsList",
    				data:myParams,cache: false,
    				success:function(data){
    					if(data.length > 0){
    						 $.each(data, function(i,val) {
    							$("#contract_id_fk").append('<option value="'+val.contract_id+'">'+$.trim(val.contract_name)+'</option>');
    					     });		
    		 			}
    					$('.searchable').select2();
    				}	 
    			});
    		}
      }
      
      //geting contracts list    
      function getComponentIdsList(contractId){
    		var html = "";
    		if($.trim(contractId) != ""){
    			var myParams = {contract_id_fk : contractId}; 
    			$.ajax({
    				url:"<%=request.getContextPath()%>/ajax/getComponentIdsList",
    				data:myParams,cache: false,
    				success:function(data){    					
    					if(data.length > 0){
    						 $.each(data, function(i,val) {
    							 var componentId = "'"+val.strip_chart_component_id+"'";
    							 var componentName = "'"+val.strip_chart_component_name+"'";
    							 html = html + '<a href="javascript:void(0);" class="dot" onclick="getStripChartActivitiesList('+componentId+'","'+componentName+');"><span class="project" data-project="'+val.strip_chart_component_name+'"></span></a>';
    					     });		
    		 			}
    					$("#dotgroup").html(html);
    				}	 
    			});
    		}
      }
      
      function getStripChartActivitiesList(componentId,componentName){    	  
    	    $("#strip_chart_component_fk").val(componentName);
    	  	$("#strip_chart_activity_id_fk option:not(:first)").remove();    		
	  		if($.trim(componentId) != ""){
	  			var myParams = {strip_chart_component_id : componentId,}; 
	  			$.ajax({
	  				url:"<%=request.getContextPath()%>/ajax/getStripChartActivitiesList",
	  				data:myParams,cache: false,
	  				success:function(data){
	  					if(data.length > 0){
	  						 $.each(data, function(i,val) {
	  							$("#strip_chart_activity_id_fk").append('<option value="'+val.strip_chart_activity_id+'">'+$.trim(val.strip_chart_activity_name)+'</option>');
	  					     });		
	  		 			}
	  					$('.searchable').select2();
	  				}	 
	  			});
	  		}
      }
      
      function getStripChartDetails(activitiId){
    	  var componentId = $("#strip_chart_component_id_fk").val();
    	  if($.trim(strip_chart_activity_id_fk) != ""){
	  		 var myParams = {strip_chart_component_id_fk : componentId,strip_chart_activity_id_fk : activitiId}; 
	  		 $.ajax({
	  			url:"<%=request.getContextPath()%>/ajax/getStripChartDetails",
	  			data:myParams,cache: false,
	  			success:function(data){
	  					
	  			}	 
	  		 });
	  	  }
      }
      
      $('form').on('reset', function() {
    	  $(".searchable").trigger("change");
   	  });
      
    </script>
</body>

</html>