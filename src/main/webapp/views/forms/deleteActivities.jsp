<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Activities - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
     <style>
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
        .table-inside{
        	margin-bottom:25px;
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
        .dot-container{
			min-width:55px;
		}		  
		 #component_circles .dot-container:first-of-type a{
            margin-left: -10px;
        }  

        #dotgroup1 .dot-line {
            width: inherit;
            min-width:30px;
            border: 2px solid #777;
            position: absolute;
            top: 14px;
            left: -17px;
            z-index: 0;
        }
        
        #dotgroup1 .dot-container:first-of-type >.dot-line{
        	left:4px;
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
            background-color: #FFFF00;
        }


        #dotgroup1 .dot.completed {
            background-color: #05a705;
        }


        #dotgroup1 .dot.delayed {
            background-color: #f00;
        }
        
        .page-loader-1 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
        
        .page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
       
		.page-loader-3 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}

		.page-loader-4 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
		.page-loader-5 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
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
        .mobile_responsible_table>tbody > tr:not(.datepicker-row) >td{
        	height:auto;
        }
            
		 .datepicker~button ,
		 .datepicker-max-today~button{
		    bottom: 1.5rem;
		} 
        thead th input[type="checkbox"]+span:not(.lever):before {
            border: 2px solid #fff;
        }
		.dataTables_filter label {
		    color: transparent;
		}
        thead th input[type="checkbox"]:checked+span:not(.lever):before {
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }	
		
.datepicker-max~button {
    position: absolute;
    right: 15px;
    top: 15px;
    border: 0;
    opacity: 0.7;
    cursor: pointer;
    background-color: transparent;
     bottom: 1.5rem;
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

        .mdl-data-table td, .mdl-data-table th{
        	padding:12px 12px;
        }
        thead th input[type="checkbox"]+span:not(.lever):before {
            border: 2px solid #fff;
        }
		.dataTables_filter label {
		    color: transparent;
		}
        thead th input[type="checkbox"]:checked+span:not(.lever):before {
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }
        
        .box.not-started {
            background-color: #fff;
        }

        .box.in-progress {
            background-color: #FFFF00;
        }

        .box.completed {
            background-color: #05a705;
        }

        .box.delayed {
            background-color: #f00;
        }

        @media only screen and (max-width: 768px) {
           .fixed-width .table-inside {
	    		overflow: hidden;
			}
        }
        @media only screen and (max-width: 700px) {
            .legends .col {
                text-align: left;
            }
        }
        @media(max-width: 575px){
        .row .col{margin: 10px auto}
        }
       
        fieldset.brdr {
        	padding-bottom: 2rem !important;
        	border:1px solid #ccc;
        	margin-bottom:20px;
        }
        fieldset.brdr legend{		    
		    padding: 0 5px;
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
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Delete Activities</h6>
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
                    <form action="<%=request.getContextPath() %>/delete-activities-bulk" id="DeleteActivitiesForm" name="DeleteActivitiesForm" method="post" >
                    <div class="container container-no-margin">
                        <div class="row">                          
                                <div class="col m10 s12 offset-m1">
                                    <div class="row">
                                        <div class="col m4 s6 input-field">
                                            <p class="searchable_label">Project</p>
                                            <select class="searchable validate-dropdown" id="project_id" name="project_id" data-placeholder="Select"
                                                onchange="addInQueProject(this.value);getNewActivitiesUpdateWorksList(this.value);onLoadMethod();">
                                               <option value="" ></option> 
                                                <c:forEach var="obj" items="${projectsList }">
                                                    <option value="${obj.project_id }"><%-- ${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> --%> ${obj.project_name }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="project_idError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m8 s6 input-field">
                                            <p class="searchable_label">Work</p>
                                            <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" data-placeholder="Select"
                                                onchange="addInQueWork(this.value);getNewActivitiesUpdateContractsList(this.value);onLoadMethod();">
                                                 <option value=""></option> 
                                                <c:forEach var="obj" items="${worksList }">
                                                    <option value="${obj.work_id }"><%-- ${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> --%> ${obj.work_short_name }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="work_id_fkError" class="error-msg" ></span>
                                        </div>
                                       <div class="col m12 s12 input-field">
                                            <p class="searchable_label">Contract <span class="required">*</span></p>
                                            <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" data-placeholder="Select"
                                                onchange="addInQueContract(this.value);getStructureTypesListFilter(this.value);resetWorksAndProjectsDropdowns(null);onLoadMethod();">
                                                 <option value=""></option> 
                                                <c:forEach var="obj" items="${contractsList }">
                                                	<option name="${obj.work_id_fk }" value="${obj.contract_id }" ><%-- ${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if >--%>${obj.contract_short_name}</option>
                                                </c:forEach>
                                            </select>
                                            <span id="contract_id_fkError" class="error-msg" ></span>
                                        </div>
                                    </div>
                                    <div class="row" id="toggle-selects">
                                    
                                        <div class="col m3 s6 input-field" >
											<p class="searchable_label">Structure Type <span class="required">*</span></p>
	                                        <select id="structure_type_fk" name="structure_type_fk" class="searchable" onchange="addInQueStructureType(this.value);getNewActivitiesUpdateStructures();" class="searchable">
	                                            <option value="">Select</option>
	                                        </select>
                                            <span id="structure_type_fkError" class="error-msg" ></span>
                                        </div>
                                                                            
                                        <div class="col m3 s6 input-field" >
                                            <p class="searchable_label">Structure <span class="required">*</span></p>
                                           <select id="strip_chart_structure_id_fk" name="strip_chart_structure_id_fk" data-placeholder="Select"
                                                class="searchable validate-dropdown" onchange="addInQueStructure(this.value);ClearComponents();getComponentsList(this.value);getNewActivitiesUpdateActivitiesList(this.value);onLoadMethod();">
                                                <option value=""></option>
                                            </select>
                                            <span id="strip_chart_structure_id_fkError" class="error-msg" ></span>
                                        </div>
                                       
                                        <div class="col m3 s6 input-field">
                                            <p class="searchable_label">Component</p>
                                             <select class="searchable validate-dropdown" data-placeholder="Select" id="strip_chart_component" name="strip_chart_component" onchange="addInQueComponent(this.value);getComponentIdsList(this.value);onLoadMethod();">
                                                <option value=""></option>
                                            </select>
                                            <span id="strip_chart_componentError" class="error-msg" ></span>
                                        </div>
                                        
                                         <div class="col m3 s12 input-field">
                                            <p class="searchable_label">Component ID</p>
                                             <select class="searchable validate-dropdown" data-placeholder="Select" id="strip_chart_component_id" name="strip_chart_component_id" onchange="addInQueComponentID(this.value);getNewActivitiesUpdateActivitiesList(this.value);onLoadMethod();">
                                                <option value=""></option>
                                            </select>
                                            <span id="strip_chart_component_idError" class="error-msg" ></span>
                                        </div>
                                    </div>
									<span id="checkBoxError" class="error-msg" style="text-align:center"></span>
									<!-- <span id="actualScopesError" class="error-msg" style="text-align:center"></span> -->
								</div>
                                    <div class="row">
                                        <div class="col s12 m12 right-align">
                                            <div class=" m-1">
                                                <button type="button" onclick="deleteActivities();" id="updatebtn" class="btn waves-effect waves-light bg-m" >Delete</button>
                                            </div>
                                        </div>
                                    </div>							
                                <div class="row fixed-width " id="table_show" style= "display:none;">					 <!-- style= "display:none;" -->
                                        <div class="table-inside">
                                            <table class="mdl-data-table mobile_responsible_table" id="table">
                                                <thead>
                                                    <tr>
		                                                <th class="no-sort" style=" text-align: left; vertical-align: bottom;">
		                                                    <p>
		                                                        <label>
		                                                            <input type="checkbox" name="pending_select-all"
		                                                                id="pending_select-all" />
		                                                            <span></span>
		                                                        </label>
		                                                    </p>
		                                                </th>
		                                                <th>Component</th>                                                    	
                                                        <th style="width: 350px">Activity</th>
                                                        <th>Planned Start</th>
                                                        <th>Planned Finish</th>
                                                        <th>Scope</th>
                                                        <th>Completed</th>
                                                        <th style="width: 100px">Units</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="filerList">
                                                
                                                  
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
						</div>
						</div>
								<div class="container container-no-margin" >
								<div class="row">
                                <div class="col m10 s12 offset-m1">
                                    <div class="row">

                                    </div>
                                    <input type="hidden" id="activity_id" name="activity_id" />
                                    <div class="row">
                                        <div class="col s6 m6 mt-brdr center-align">
                                            <div class=" m-1">
                                                <button type="button" onclick="deleteActivities();" id="btn" class="btn waves-effect waves-light bg-m" >Delete</button>
                                            </div>
                                        </div>
                                        <div class="col s6 m6 mt-brdr center-align">
                                            <div class=" m-1">
                                                <button type="reset" onClick="window.location.reload(); clearFilters();" class="btn waves-effect waves-light bg-s">Reset</button>
                                            </div>
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
    
    <div class="page-loader-2" style="display: none;">
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
	
	<div class="page-loader-3" style="display: none;">
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
	
	<div class="page-loader-4" style="display: none;">
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
	<div class="page-loader-5" style="display: none;">
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
   <!--  <script src="/pmis/resources/js/datepickerDepedency.js"></script> -->
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
    
    $('#btn').addClass('disabled');
    $('#updatebtn').addClass('disabled');
    
    	var monthShortCode=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	    var datePickerSelectAddClass = function () {
	        var self = this;
	        setTimeout(function () {
	            var selector = self.el;
	            if (!selector) {
	                selector = ".datepicker"
	            }
	            $(selector).siblings(".datepicker-modal")
	                .find(".select-dropdown.dropdown-trigger")
	                .each((index, item) => {
	                    var dateDropdownID = $(item).attr("data-target");
	                    var dropdownUL = $('#' + dateDropdownID);
	                    dropdownUL.children("li").on("click", () => {
	                        datePickerSelectAddClass();
	                    });
	                    dropdownUL.addClass("datepicker-dropdown-year-month")
	                });
	        }, 500);
	    };
	    $(document).on('focus', '.datepicker-max', function () {        	 
			var id = $(this).attr('id');
				var dt = this.value.split('-');
			    this.value = "";
			    var options = {
			    	//maxDate: new Date(),
			    	format: 'dd-mmm-yy',
			        autoClose: true,
			        onOpen: datePickerSelectAddClass,
			        showClearBtn: true,
			        onClose: function () {
			            if (!$(this.el).val()) {
			                $(this.el).siblings('label').removeClass('active');
			            }
			        }
			    };
			    if (dt.length > 1) {			    	
			        var year=(dt[2] < 80)?Number(dt[2])+2000:Number(dt[2])+1900;
			        var month=monthShortCode.indexOf(dt[1]);
			        options.setDefaultDate = true,
			        options.defaultDate = new Date(year, month, dt[0])
			    }
			    M.Datepicker.init(this, options);		       
		 });
	    
		 
		 $(document).on('focus', '.datepicker-max-button', function () { 
			 var id = $(this).attr('id').split('_i')[0];
		     $('#'+id+'_icon').click(function () {
		         event.stopPropagation();
		         $('#'+id).focus().click();
		     });
		 }); 
		 
		 
         $('#pending_select-all').change(function () {
             var _this = this;
             $('input[name="pending_activity_check"]').each(function () {
                 if ($(_this).is(':checked')) 
                 {
                 	$("input:checkbox").each(function () {
                 		var id=$(this).attr("id");
                 		var isDisabled =$('#'+id).is(':disabled');
                 		if(isDisabled == false)
                 		{
                     		$(this).prop('checked', true);
                 		}

                 	});
                     $('#btn').removeClass('disabled');
                     $('#updatebtn').removeClass('disabled');
                 } else 
                 {
                     $(this).prop('checked', false);
                     $('#btn').addClass('disabled');
                     $('#updatebtn').addClass('disabled');
                 }
             });
         });		 
	    
	    
	    $(document).on('focus', '.datepicker-max-today', function () {        	 
			var id = $(this).attr('id');
				var dt = this.value.split('-');
			    this.value = "";
			    var options = {
			    	maxDate: new Date(),
			    	format: 'dd-mmm-yy',
			        autoClose: true,
			        onOpen: datePickerSelectAddClass,
			        showClearBtn: true,
			        onClose: function () {
			            if (!$(this.el).val()) {
			                $(this.el).siblings('label').removeClass('active');
			            }
			        }
			    };
			    if (dt.length > 1) {			    	
			        var year=(dt[2] < 80)?Number(dt[2])+2000:Number(dt[2])+1900;
			        var month=monthShortCode.indexOf(dt[1]);
			        options.setDefaultDate = true,
			        options.defaultDate = new Date(year, month, dt[0])
			    }
			    M.Datepicker.init(this, options);		       
		 });
	    
	    
		 $(document).on('focus', '.datepicker-max-today-button', function () { 
			 var id = $(this).attr('id').split('_i')[0];
		     $('#'+id+'_icon').click(function () {
		         event.stopPropagation();
		         $('#'+id).focus().click();
		     });
		 }); 
		 
		 
	    var filtersMap = new Object();
	    var structureVal = "";
	    var glb="";
	    var glbID="";
        $(document).ready(function () {
            $('.searchable').select2();
      
            
            var filters = window.localStorage.getItem("BulkFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++)
          	  {
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
    		        		  getNewActivitiesUpdateWorksList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
    		        		  getNewActivitiesUpdateContractsList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
    		        		  resetWorksAndProjectsDropdowns(temp2[1]);
    		        	  }
      	        		  else if($.trim(temp2[0]) == 'structure_type_fk'){
        		        		getStructureTypesListFilter(temp2[1]);
        		        	  }      		        	  
    		        	  else if($.trim(temp2[0]) == 'strip_chart_structure_id_fk'){
    		        		  getNewActivitiesUpdateStructures(temp2[1]);
    		        		  structureVal = temp2[1];
    		        	  }
    		        	  else if($.trim(temp2[0]) == 'strip_chart_component'){
    		        		  //getComponentsList(temp2[1]);
    		        		  glb=temp2[1];
    		        	  }  
    		        	  else if($.trim(temp2[0]) == 'strip_chart_component_id'){
    		        		  getComponentIdsList(temp2[1]);
    		        		  glbID=temp2[1];
    		        	  }      		        	  
    	        	  }
    	          }
              }
            
        
        });
        function onLoadMethod(){
	        $(".page-loader").show();
	       
	       
	       	var filters = '';
	       	Object.keys(filtersMap).forEach(function (key) {
	       		//alert(filtersMap[key]);
	       		filters = filters + key +"="+filtersMap[key] + "^";
	       		window.localStorage.setItem("BulkFilters", filters);
	   			});
	        $(".page-loader").hide();
       }
        
        function addInQueProject(project_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('project_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(project_id_fk) != ''){
       	    	filtersMap["project_id_fk"] = project_id_fk;
        	}
        }
        
        function addInQueWork(work_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
          	}
        }
        function addInQueContract(contract_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('contract_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(contract_id_fk) != ''){
            	filtersMap["contract_id_fk"] = contract_id_fk;
	      	}
        }
        
        function addInQueStructureType(structure_type_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('structure_type_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(structure_type_fk) != ''){
            	filtersMap["structure_type_fk"] = structure_type_fk;
	      	}
        }       
        function addInQueStructure(strip_chart_structure_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('strip_chart_structure_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(strip_chart_structure_id_fk) != ''){
            	filtersMap["strip_chart_structure_id_fk"] = strip_chart_structure_id_fk;
	      	}
        }
        
        function addInQueComponent(strip_chart_component){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('strip_chart_component')) delete filtersMap[key];
	   	   	});
	      	if($.trim(strip_chart_component) != ''){
            	filtersMap["strip_chart_component"] = strip_chart_component;
	      	}
        }
        function addInQueComponentID(strip_chart_component_id){
        	
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('strip_chart_component_id')) delete filtersMap[key];
	   	   	});
	      	if($.trim(strip_chart_component_id) != ''){
            	filtersMap["strip_chart_component_id"] = strip_chart_component_id;
	      	}
        }       
        
       
        function clearFilters(){
        	window.localStorage.setItem("BulkFilters",'');
        }
        
        function ClearComponents()
        {
        	glb="";
        	glbID="";
        }
        
    	

        
        function getStructureTypesListFilter(fob) {
        	$("#structure_type_fk option:not(:first)").remove();
        	
       		var work_id_fk = $("#work_id_fk").val();
           	var contract_id_fk = $("#contract_id_fk").val();
           	var structure_type_fk = $("#structure_type_fk").val();
           	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk} ;
      	       
         	$(".page-loader").show();

            if ($.trim(structure_type_fk) == "") {
                $("#structure_type_fk option:not(:first)").remove();
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStructureTypesInDeleteActivities",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
	                            	var selectedFlag = (fob == val.structure_type)?'selected':'';
	                            	$("#structure_type_fk").append('<option value="' + val.structure_type + '"'+selectedFlag+'>' + $.trim(val.structure_type) +'</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
     	   			  $(".page-loader").hide();
   	   	          	  getErrorMessage(jqXHR, exception);
  	   	     	  }
                });
            }else{
            	  $(".page-loader").hide();
            }
        }          

	
	function getNewActivitiesUpdateWorksList(projectId) { 
		$(".page-loader-1").show();
		$("#contract_id_fk option:not(:first)").remove();    	
	    $("#work_id_fk option:not(:first)").remove();
	    
	    $("#strip_chart_structure_id_fk option:not(:first)").remove();
	    $("#strip_chart_line_id_fk option:not(:first)").remove();
	    $("#strip_chart_section_name option:not(:first)").remove();
		
		$('.searchable').select2();
		clearComponentCircle();
		
	
	    if ($.trim(projectId) != "") {
	        var myParams = { project_id_fk: projectId };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getDeleteActivitiesWorksList",
	            data: myParams, cache: false,async: false,
	            success: function (data) {
	            	var id1 = "";
	                var id2 = "";
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var workName = '';
	                        if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) }
	                        if ($.trim(id2) != '' && val.work_id == $.trim(id2)) {
	                        	id1 = val.work_id;
	                            $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(workName) + '</option>');
	                        } else {
	                            $("#work_id_fk").append('<option value="' + val.work_id + '">'  + $.trim(workName) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader-1").hide();
	                
	                if ($.trim(id1) != '' && $.trim(id2) != '') {
	                	getNewActivitiesUpdateContractsList(id2);
	                }
	            }
	        });
	    }else{
	    	$(".page-loader-1").hide();
	    }
	}
	
	//geting contracts list    
	function getNewActivitiesUpdateContractsList(work_id_fk) {
		$(".page-loader").show();
	    $("#contract_id_fk option:not(:first)").remove();
	    
	    $("#strip_chart_structure_id_fk option:not(:first)").remove();
	    $("#strip_chart_line_id_fk option:not(:first)").remove();
	    $("#strip_chart_section_name option:not(:first)").remove();
		$('.searchable').select2();
		clearComponentCircle();
		
	    if ($.trim(work_id_fk) != "") {
	        var myParams = { work_id_fk: work_id_fk };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getDeleteActivitiesContractsList",
	            data: myParams, cache: false,async: false,
	            success: function (data) {
	            	var id1 = "";
	            	var id2 = "";                        
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var contract_name = '';
	                        if ($.trim(val.contract_short_name) != '') { contract_name =  $.trim(val.contract_short_name) }
	                        if ($.trim(id2) != '' && val.contract_id == $.trim(id2)) {
	                        	id1 = val.contract_id;
	                            $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '" selected>'  + $.trim(contract_name) + '</option>');
	                        } else {
	                            $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '">' + $.trim(contract_name) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader").hide();
	                
	                if ($.trim(id1) != '' && $.trim(id2) != '') {
	                	getNewActivitiesUpdateStructures(id2);
	                }
	            }
	        });
	    }else{
	    	$(".page-loader").hide();
	    }
	}
	
	function resetWorksAndProjectsDropdowns(contract){
		$(".page-loader-1").show();
		clearComponentCircle();
		
		
		var projectId = '';
		var workId = ''
			var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
			var contract_id_fk = $("#contract_id_fk").val();
			if(contract_id_fk == ""){
				contract_id_fk = contract;
			}
			if($.trim(contract_id_fk) != ''){        			
				workId = $("#contract_id_fk").find('option:selected').attr("name");
				if(workId == null){
					workId =  contract_id_fk.substring(0, 6); 
				}
				projectId = workId.substring(0, 3);    
				//workId = workId.substring(3, work_id.length);
				$("#project_id").val(projectId);
				$("#contract_id_fk").val(contract_id_fk);
				
			
				$("#project_id").select2();
				$("#contract_id_fk").select2();
			}
			
			if ($.trim(projectId) != "") {
				$("#work_id_fk option:not(:first)").remove();
	        var myParams = { project_id_fk: projectId };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getDeleteActivitiesWorksList",
	            data: myParams, cache: false,async: false,
	            success: function (data) {
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var workName = '';
	                        if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) }
	                        if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
	                            $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' +  $.trim(workName) + '</option>');
	                            getNewActivitiesUpdateStructures(structureVal);
	                        } else {
	                            $("#work_id_fk").append('<option value="' + val.work_id + '">' +  $.trim(workName) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader-1").hide();
	            }
	        });
	        $('.searchable').select2();
	    }
			
	}
	
    function clearComponentCircle(){        	
    	
     	$('.searchable').select2();
     	
         $("#component_circles").html('');
         $("#component_circles_row").hide();
         $("#legends").hide();  
         $("#table_show").hide(); 
     } 
	
	  function getNewActivitiesUpdateStructures(value) {
      	  $(".page-loader-4").show();
      	  var contract_id_fk = $("#contract_id_fk").val();
      	  var structure_type_fk = $("#structure_type_fk").val();
      	
      	  var strip_chart_structure_id_fk = value;
          $("#strip_chart_structure_id_fk option:not(:first)").remove();
          $("#strip_chart_component option:not(:first)").remove();
          $("#strip_chart_component_id option:not(:first)").remove();
          
          if ($.trim(contract_id_fk) != "") {
          	var myParams = { contract_id_fk: contract_id_fk,structure_type_fk:structure_type_fk };
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getDeleteActivitiesStructures",
                  data: myParams, cache: false,async: false,
                  success: function (data) {
                  	var id1 = "";
                  	var id2 = "";
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
	                            if ($.trim(id2) != '' && val.strip_chart_structure_id_fk == $.trim(id2)) {
	                            	id1 = val.strip_chart_structure_id_fk;
	                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '" selected>' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            }else if (val.strip_chart_structure_id_fk == value) {
	                            	 var selectedFlag = (strip_chart_structure_id_fk != null)?'selected':'';
	                                 $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '" selected>' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            	 id2 = strip_chart_structure_id_fk;
	                            } else {
	                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '">' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            }
                          });
                      }
                      $('.searchable').select2();
                      $(".page-loader-4").hide();
                      
                      if (id2!='') 
                      {
                    	  getComponentsList(id2);
                      }
                  }
              });
          }else{
          	$(".page-loader-4").hide();
          }
      }

	
	function getComponentsList(structure_id){
		 clearComponentCircle();
     	 $(".page-loader-5").show();
         $("#strip_chart_component option:not(:first)").remove();
         $("#strip_chart_component_id option:not(:first)").remove();
         
         var contract_id_fk = $("#contract_id_fk").val();
         var structureId = $("#strip_chart_structure_id_fk").val();
         var strip_chart_line_id_fk = $("#strip_chart_line_id_fk").val();
         var strip_chart_section_name = $("#strip_chart_section_name").val();
         var myParams = { contract_id_fk: contract_id_fk, strip_chart_structure_id_fk: structure_id, strip_chart_line_id_fk: strip_chart_line_id_fk, strip_chart_section_name: strip_chart_section_name };
         
         if ($.trim(structure_id) != "") {
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getDeleteActivitiesComponentsList",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) 
                         {
                        	 if(val.strip_chart_component==glb)
                        		 {
                            			$("#strip_chart_component").append('<option value="' + val.strip_chart_component + '" selected>' + $.trim(val.strip_chart_component) + '</option>');
                        		 }
                        	 else
                        		 {
                     				$("#strip_chart_component").append('<option value="' + val.strip_chart_component + '">' + $.trim(val.strip_chart_component) + '</option>');
                      		 
                        		 }
                         });
                     }
                     $('.searchable').select2();
                     $(".page-loader-5").hide();
                     
                     if (glb!='') 
                     {
                    	 getComponentIdsList(glb);
                     }                    
                 }
             });
         }else{
         	$(".page-loader-5").hide();
         }        
     }

	 function getComponentIdsList(component) {

     	$(".page-loader-3").show();
     	
     	
       	 var strip_chart_component = $("#strip_chart_component").val();
    	 
     	clearComponentCircle();
     	
     	$("#strip_chart_component_id option:not(:first)").remove();
         
         var contract_id_fk = $("#contract_id_fk").val();
         var structureId = $("#strip_chart_structure_id_fk").val();
         var laneId = $("#strip_chart_line_id_fk").val();
         var sectionId = $("#strip_chart_section_name").val();
         
         var myParams = { contract_id_fk: contract_id_fk, strip_chart_structure_id_fk: structureId, strip_chart_line_id_fk: laneId, strip_chart_section_name: sectionId, strip_chart_component : component };
         var html = '';

         if ($.trim(contract_id_fk) != "" && $.trim(structureId) != "" && $.trim(component) ) {                
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getDeleteActivitiesComponentIdsList",
                 data: myParams, cache: false,async:false,
                 success: function (data) {
                 	var id1 = "";
                 	var id2 = "";
                     var strip_chart_component = "";
                     
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                         	 //var componentIdAndName = "'" + val.strip_chart_component_id + "','" +component+ "'";
                         	 var componentId = "'" + val.strip_chart_component_id + "'";
                             var className = "odd";
                             if(i%2 == 0){
                             	className = "even";
                             }
                             var tempId = val.strip_chart_component_id;
                             tempId = tempId.replace(/\s/g, "_");
                             tempId = tempId.replace(/\//g, "_");
                             tempId = tempId.replace(/\./g, "_");
                             
                             var pointerEvent = "";
                             if(val.component_id_color == "completed"){
                             	pointerEvent = "pointer-events: none;";
                             	html = html + '<div class="dot-container" id="dd'+tempId+'">'
                                 + '<a href="javascript:void(0);" data-some="completed" id="'+tempId+'" style="'+pointerEvent+'" onclick="getNewActivitiesUpdateActivitiesList('+componentId+');" class="dot '+val.component_id_color+' clearData" >'
                                 + '<span class="project '+className+'" >'+val.strip_chart_component_id+'</span></a>';
                                // if(i != 0){
                                 	html = html + '<span class="dot-line"></span>';
                                // }
                                 html = html + '</div>';
                             	
                             	$("#strip_chart_component_id").append('<option value="' + val.strip_chart_component_id + '">' + $.trim(val.strip_chart_component_id) + '</option>');
                             } else {                
                             	
                             	html = html + '<div class="dot-container" id="dd'+tempId+'">'
                                 + '<a href="javascript:void(0);" id="'+tempId+'" style="'+pointerEvent+'" onclick="getNewActivitiesUpdateActivitiesList('+componentId+');" class="dot '+val.component_id_color+' clearData" >'
                                 + '<span class="project '+className+'">'+val.strip_chart_component_id+'</span></a>';
                                // if(i != 0){
                                 	html = html + '<span class="dot-line"></span>';
                                // }
                                 html = html + '</div>';
                             	if (val.strip_chart_component_id == glbID) {
                             		id1 = val.strip_chart_component_id;
 	                            	$("#strip_chart_component_id").append('<option value="' + val.strip_chart_component_id + '" selected>' + $.trim(val.strip_chart_component_id) + '</option>');
 	                            } else {
 	                            	$("#strip_chart_component_id").append('<option value="' + val.strip_chart_component_id + '">' + $.trim(val.strip_chart_component_id) + '</option>');
 	                            }
                             }                                
                         });
                         
                         $('.searchable').select2();
                         getNewActivitiesUpdateActivitiesList('');
                     }
                     $("#component_circles").html(html);
                     $("#component_circles_row").show();
                     $('#legends').show();
                     //dynamic width based on content
                     $.each($('.dot-container'), function (i, val) {
                     	$(val).css("width",$(val).find('a').children().width());
                     });
                     $(".page-loader-3").hide();
                     
                     
                     if ($.trim(id1) != '' && $.trim(id2) != '') {
                     	getNewActivitiesUpdateActivitiesList(id2);
                     }
                 }
             });
         }else{
         	 $(".page-loader-3").hide();
         	 $("#component_circles").html(html);
             $("#component_circles_row").hide();
             $('#legends').hide();
         }
     }
	 
	
     function getNewActivitiesUpdateActivitiesList(componentId){
    	 $(".page-loader").show();
    	 
    	 $("#strip_chart_component_id").select2();
    	 
    	 $( ".dot" ).removeClass( "active" );
     	 
    	 $("#table_show").show();
    	 var html = '';
    	 $("#filerList").html('');
    	
    	 var strip_chart_component_id = $("#strip_chart_component_id").val();
    	 var strip_chart_activity_id = $("#strip_chart_activity_id").val();
    	 var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
    	 var strip_chart_component = $("#strip_chart_component").val();
    	 var contract_id_fk = $("#contract_id_fk").val();
    	 var structure_type_fk = $("#structure_type_fk").val();
    	 
    	 if ($.trim(strip_chart_structure_id_fk) != "") {
 	        var myParams = { strip_chart_component_id: strip_chart_component_id, strip_chart_activity_id: strip_chart_activity_id,strip_chart_structure_id_fk : strip_chart_structure_id_fk, contract_id_fk : contract_id_fk,strip_chart_component : strip_chart_component,structure_type_fk:structure_type_fk };
 	        $.ajax({
 	            url: "<%=request.getContextPath()%>/ajax/getDeleteActivitiesfiltersList",
 	            data: myParams, cache: false,
 	            success: function (data) {
 	            	
 	                if (data.length > 0) {
 	                    $.each(data, function (i, val) {
 	                    	
 	                    	 var num = $('#table tbody tr').length;
 	                    	 html = '<tr id="row'+num+'"><td><p><label><input type="checkbox" class="filled-in"  name="pending_activity_check" class="check" id="pending_activity_check_'+num+'" /><span></span></label></p></td><td>' + $.trim(val.strip_chart_component) + '</td>'
 	            	 			+'<td data-head="Activity" class="input-field"><div>' + $.trim(val.strip_chart_activity_name) +' ('+$.trim(val.unit_fk)+' )<input type="hidden" name="activity_ids"  id="activity_id'+num+'"  value="' + $.trim(val.activity_id) + '" /></div></td>';

		 	            	 			html +='<td data-head="Planned Start" class="input-field">' + $.trim(val.planned_start) + '</td>'
		 	            	 			+'<td data-head="Planned Finish" class="input-field">' + $.trim(val.planned_finish) + '</td>'
 	            	 			
 	            	 			html +='<td data-head="Scope" class="input-field"><span>'+$.trim(val.scope)+'</span>';
 	            	 			
 	            	 			
 	            	 			html +='<input type="hidden" name="totalScopes"  id="totalScopes'+num+'"  value="' + $.trim(val.scope) + '" /></td>'
 	            	 			+'<td data-head="Completed" class="input-field"><span>' + Number($.trim(val.completed)) + '</span>'
 	            	 			+'<input type="hidden" name="ids"  id="ids'+num+'"  value="" /></td>'
 	            	 			+' <td data-head="Actual" class="input-field">'+$.trim(val.unit_fk)+'</td></tr>';
 	                    		$("#filerList").append(html);	
 	                    		
	                    	 	
 	                    	 	
 	                    	 	$("#select-all").on('change', function(){
 	                    	 		if( $("#select-all").prop('checked') ){
	                    	 			 $('#actualScopes'+num).prop('readonly', false);
	                    	 		}else{
	                    	 			 $('#actualScopes'+num).prop('readonly', true);
	                    	 			 $('#actualScopesError'+num).html("");
	                    	 			 $('#btn').prop('disabled',true);
	                    	 			 $('#btn1').prop('disabled',true);
	                    	 		}
 	                    	 	});

 	                    	 	$("#check_"+num).on('change', function(){
	                    	 		if($("#check_"+num).is(':unchecked')){
	                    	 			$('#actualScopesError'+num).html("");
                    	 			}
                    	 		})
        	                    $('#pending_activity_check_'+num).change(function () {
        	                        var _this = this;
        	                            if ($('#pending_activity_check_'+num).is(':checked')) 
        	                            {
        	                            	$('#pending_activity_check_'+num).prop('checked', true);
        	                                $('#btn').removeClass('disabled');
        	                                $('#updatebtn').removeClass('disabled');
        	                                $('#ids'+num).val(1);
        	                            } 
        	                            else 
        	                            {

        	                            	var t=0;
        	                            	
        	                            	$('input[name="pending_activity_check"]').each(function () {
        	                            		if ($(this).is(':checked')) 
        	                                    {
            	                                    t++;
        	                                    } 
        	                                }); 
        	                            	
												if(t>=1)
												{
	            	                            	$('#pending_activity_check_'+num).prop('checked', false);
	            	                                $('#btn').removeClass('disabled');
	            	                                $('#updatebtn').removeClass('disabled');
	            	                                $('#ids'+num).val(1);
												}
												else
												{
	            	                                $('#btn').addClass('disabled');
	            	                                $('#updatebtn').addClass('disabled');
	            	                                $('#ids'+num).val(0);
												}
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
  
     
     function deleteActivities()
     {
    	 if (confirm("Are you sure you want to delete activities")) 
    	 {  
    	 	$(".page-loader").show();
	   		document.getElementById("DeleteActivitiesForm").submit();
    	 }
 			 
     }

     var validator = $('#DeleteActivitiesForm').validate({
    	 ignore: ":hidden:not(.validate-dropdown)",
    	 rules: {
    		  "project_id": {
			 	required: false
			  },"work_id_fk": {
		 		required: false
		 	  },"contract_id_fk": {
		 		required: true
		 	  },"structure_type_fk": {
		 		required: true
		 	  },"strip_chart_structure_id_fk": {
		 		required: true
		 	  },"strip_chart_component": {
		 		required: true
		 	  },"strip_chart_component_id": {
		 		required: false
		 	  },"strip_chart_activity_id": {
		 		required: false
		 	  },"actualScopes": {
		 		required: false,
		 		min:0
	   		  }
    	 },
            messages: {
                  "project_id": {
			 		required: 'Select project'
			 	 },"work_id_fk": {
		 			required: 'Select work'
		 	  	 },"contract_id_fk": {
		 			required: 'Select contract'
		 	  	 },"structure_type_fk": {
		 			required: 'Select Structure Type'
		 	  	 },"strip_chart_structure_id_fk": {
		 	  		required: 'Select Structure'
			 	 },"strip_chart_component": {
		 			required: 'Select component'
		 	  	 },"strip_chart_component_id": {
		 			required: 'Select component id'
		 	  	 },"strip_chart_activity_id": {
		 			required: 'Select Activity'
		 	  	 },"actualScopes": {
   		 			required: 'click on finish activities'
   		 	  	 }
    	 },errorPlacement:function(error, element){
  		 	         if (element.attr("id") == "project_id" ){
			 		     document.getElementById("project_idError").innerHTML="";
 			 			 error.appendTo('#project_idError');
 			 	    }else if (element.attr("id") == "work_id_fk" ){
			 		     document.getElementById("work_id_fkError").innerHTML="";
			 			 error.appendTo('#work_id_fkError');
			 	    }else if (element.attr("id") == "contract_id_fk" ){
			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
			 			 error.appendTo('#contract_id_fkError');
			 	    }else if (element.attr("id") == "structure_type_fk" ){
			 	    	 document.getElementById("structure_type_fkError").innerHTML="";
			 			 error.appendTo('#structure_type_fkError');
			 	    }
			 	    else if (element.attr("id") == "strip_chart_structure_id_fk" ){
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
			 	    }else if (element.attr("name") == "actualScopes" ){
  			 	    	 document.getElementById("actualScopesError").innerHTML="";
   			 			 error.appendTo('#actualScopesError');
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
		    	//form.submit();
		     }
     });
     $.validator.addMethod("dateFormat",
     	    function(value, element) {
     	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
     	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
     	    	//return dtRegex.test(value);
     	    },
     	    //"Date format (Aug 02,2020)"
     	    "Date format (DD-MM-YYYY)"
     	);
     
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