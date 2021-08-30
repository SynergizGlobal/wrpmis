<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html >
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Reference Forms - Admin - PMIS</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-grid-template.css" />

    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        th:last-child,
        td:last-child {
            text-align: center !important;
        }

        td:last-child {
            white-space: inherit;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }
		 div.dataTables_wrapper div.dataTables_info {
		    white-space: break-spaces;
		} 
        .input-field .searchable_label {
            font-size: 0.85rem;
        }
 @media only screen and (max-width: 769px){ 
			
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
	         .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        }
	         .last-column .btn+.btn {
	            margin-left: 10px;
	        }
		}
		.no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
	}
    </style>
</head>

<body>

    <!-- header  starts-->
        <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-2">
                            <h6>Reference Forms</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1">
                            <div class="col s12 m4 offset-m4 center-align">
                                <div class="m-1 ">
                                    <a  href="<%=request.getContextPath()%>/add-reference-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Form</strong></a>
                                </div>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col s12 m2 input-field offset-m5">
                                <p class="searchable_label">Module</p>
                                <select id="module_fk" name="module_fk" onchange="getModuleFilter();" class="searchable">
                                    <option value="">Select</option>
                                    <c:forEach var="obj" items="${referenceForm}">
                						    <option value="${obj.module_fk }" >${obj.module_fk}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- <div class="col s12 m2">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div> -->
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
	                                <table id="Reference_forms_table" class="mdl-data-table">
	                                    <thead>
	                                        <tr>
	                                            <th class="no-sort">Name</th>
	                                            <th>Module</th>
	                                            <th class="no-sort">Action</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                   
	                                    </tbody>
	
	                                </table>
                            </div>
                        </div>
                    </div>
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
    <!-- footer  -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
    <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="reference_forms_id" id="reference_forms_id" />
    </form>
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
     <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	
    <script>
       /*  $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#Reference_forms_table').DataTable({
                columnDefs: [
                    {
                        targets: [2],
                        className: 'last-column',
                        targets: 'nosort', orderable: false,
                    },
                    { "width": "20px", "targets": [2] },
                ],
                "scrollCollapse": true,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
            
        }); */
        $(document).ready(function () {
      	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
      	   $('.close-message').delay(3000).fadeOut('slow');
      	
      	getModuleFilter();
       });
        function clearFilters() {
            $('#module_fk').val("");
            $('.searchable').select2();
            getModuleFilter();
        }
        
        function getModuleFilter(){
        	$(".page-loader-2").show();
        	var module_fk = $("#module_fk").val();
        	
      	    table = $('#Reference_forms_table').DataTable();
     		 
      		table.destroy();
      		
      		$.fn.dataTable.moment('DD-MMM-YYYY');
      		table = $('#Reference_forms_table').DataTable({
          		"bStateSave": true,
          		fixedHeader: true,
                  "fnStateSave": function (oSettings, oData) {
                      localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
                  },
                  "fnStateLoad": function (oSettings) {
                      return JSON.parse(localStorage.getItem('MRVCDataTables'));
                  },
                  columnDefs: [
                      {
                          targets: [2],
                          className: 'last-column'
                      },
                      {targets: [0,1,2], className: 'fw-111'},
                      { "width": "20px", "targets": [2] },
                      { orderable: false, 'aTargets': ['nosort'] }
                  ],
                  // "ScrollX": true,
                  "sScrollX": "100%",
                  "ordering":false,
                   "sScrollXInner": "100%",
                   "bScrollCollapse": true,
                  initComplete: function () {
                      $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                  }
              }).rows().remove().draw();
      		
      		table.state.clear();		
      	 	var myParams = {module_fk : module_fk};
      	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-reference-forms-list",type:"POST",data:myParams,success : function(data){    				
      			if(data != null && data != '' && data.length > 0){    					
               		$.each(data,function(key,val){
               			var reference_forms_id = "'"+val.reference_forms_id+"'";
               			var form_url = "'"+val.form_url+"'";
                          var actions = '<a href="javascript:void(0);"  onclick="getReferenceForm('+reference_forms_id+');" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-pencil"></i></a>'
                          			  +'<a href="javascript:void(1);" onclick="gotoPath('+form_url+');" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-share"></i></a>'
                        	var rowArray = [];    	                 
                    
                          if($.trim(val.name) == ''){ name =  '-'; }else{ name =  $.trim(val.name); }
                          if($.trim(val.module_fk) == ''){ module_fk =  '-'; }else{ module_fk =  $.trim(val.module_fk); }
                          
                          
                          rowArray.push(name);
                         	rowArray.push(module_fk);
                         	
                         	rowArray.push($.trim(actions));   	                   	
                         	
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
      
        function gotoPath(path){
          	 if ($.trim(path) != '') { 
          		  console.log(path)
          		  window.location.href = "<%=request.getContextPath()%>/"+path
        		      
         		 }
           }

        function getReferenceForm(reference_forms_id){
	    	$("#reference_forms_id").val(reference_forms_id);
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-reference-form');
	    	$('#getForm').submit();
	    }
    </script>
</body>

</html>