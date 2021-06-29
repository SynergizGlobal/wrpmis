<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Land Acquisition - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        td:last-child {
            white-space: inherit;
        }
        
        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }
        .fs-350 {
		    width: 350px;
		    max-width: 350px;
		}
		 .dataTables_filter label::after{
         	content:'';
         }
          .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
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
			.mb-md-2{
				margin-bottom:2rem;
			}
			.dataTables_filter label input {
			    width: 100% !important;
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
	        	width:30vw;
	        	min-width:30vw;
	        }
		}
		@media only screen and (min-width: 576px) and (max-width: 839px){
			.fs-md-74rem{
			    font-size: .74rem;
			}
		}

    </style>
</head>

<body>

    <!-- header  starts-->
      <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12 hide-on-med-and-down">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Land Acquisition</h6>
                        </div>
                    </span>
                    <div class="">
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
                         <div class="row plr-1">
                            <div class="col s12 m4">
                               <!--  <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem">Click <a href="#"> here </a>for the template</p>
                                </div> -->
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath() %>/add-land-acquisition-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Land Acquisition</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                             <!--    <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div> -->
                            </div>
                        </div> 
              		</div>
              	</div>
                </div>
             </div>
       <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6 class="hide-on-med-and-down">Update Land Acquisition</h6>
							<h6 class="hide-on-large-only">Land Acquisition</h6>	
                        </div>
                    </span>          
                   		<div class="col s12 hide-on-large-only mb-md-2 center-align">
                             <a href="<%=request.getContextPath() %>/add-land-acquisition-form" class="btn waves-effect waves-light bg-s t-c">
                                 <strong><i class="fa fa-plus-circle"></i> Add Land Acquisition</strong></a>
                        </div>
                        <div class="row no-mar" >
                            <div class="col s6 m4 l2 input-field">
                                <p class="searchable_label">Select Project</p>
                                <select id="project_id_fk" class="searchable" name="project_id_fk" onchange="addInQueProject(this.value);getLandAcquisitionList();">
                                    <option value="" >Select Project</option>
                                   
                                </select>
                            </div>
                             <div class="col s6 m4 l2 input-field">
                                <p class="searchable_label">Select Work</p>
                                <select id="work_id_fk" class="searchable" name="work_id_fk" onchange="addInQueWork(this.value);getLandAcquisitionList();">
                                    <option value="" >Select Work</option>
                                   
                                </select>
                            </div>
                            <div class="col s6 m4 l2 input-field">
                                <p class="searchable_label">Select Village</p>
                                <select id="village" class="searchable" name="village" onchange="addinQueVillage(this.value);getLandAcquisitionList();"> 
                                    <option value="" >Select Village</option>
                                   
                                </select>
                            </div>
                            <div class="col s6 m4 l2 input-field">
                                <p class="searchable_label fs-md-74rem">Select Type of Land</p>
                                <select id="type_of_land" class="searchable" name="type_of_land" onchange="addinQueType(this.value);getLandAcquisitionList();">
                                    <option value="" >Select Type of Land</option>
                                   
                                </select>
                            </div>
                            <div class="col s6 m4 l2 input-field">
                                <p class="searchable_label fs-md-74rem">Select Sub Category</p>
                                <select id="sub_category_of_land" class="searchable" name="sub_category_of_land" onchange="addInQueSubCategory(this.value);getLandAcquisitionList();">
                                    <option value="" >Select Sub Category</option>
                                   
                                </select>
                            </div>
                            <div class="col s12 m4 l2 center-align input-field">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 10px;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="land-acquisition-datatable" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th> Survey <br>Number</th>
                                            <th class="fs-350"> Work </th>
                                            <th> Village</th>
                                            <th> Type of Land</th>
                                            <th> Sub Category <br>of Land</th>
                                            <th> Area of Plot</th>                                           
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
  
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="la_id" id="la_id" />
    </form>
    <script>
    
    	var filtersMap = new Object();
    
        $(document).ready(function () {
        	  $('select:not(.searchable)').formSelect();
              $('.searchable').select2();
              $('.close-message').delay(3000).fadeOut('slow')
              
              var filters = window.localStorage.getItem("laFilters");
	          
              if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
  	        	  if($.trim(temp[i]) != '' ){
  	        		  var temp2 = temp[i].split('=');
  		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
  		        		getProjectsFilterList(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
  		        		getWorksFilterList(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'village'){
  		        		getvillagesFilterList(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'type_of_land'){
  		        		getTypesOfLandsFilterList(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'sub_category_of_land'){
  		        		getSubCatogoryFilterList(temp2[1]);
  		        	  }
  	        	  }
  	          }
              }
              
      	      getLandAcquisitionList();
      	      
      	  	 if(window.matchMedia("(max-width: 769px)").matches){
      		    	$('tbody.web').removeAttr('id');
      		        $('#mobView').css({'display':'block'});
      		      	
      		    } else{
      		    	$('#webView').css({'display':'block'});
      		    }
        });

        function clearFilters() {
        	$("#project_id_fk").val("");
            $('#work_id_fk').val("");
            $('#village').val("");
            $('#type_of_land').val("");
            $('#sub_category_of_land').val("");
            $('.searchable').select2();
            window.localStorage.setItem("laFilters",'');
            window.location.href = "<%=request.getContextPath()%>/land-acquisition";
            //getLandAcquisitionList();
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
        
        function addinQueVillage(village){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('village')) delete filtersMap[key];
	   		});
        	if($.trim(village) != ''){
       	    	filtersMap["village"] = village;
        	}
        }
        
        function addinQueType(type_of_land){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('type_of_land')) delete filtersMap[key];
	   	   	});
	      	if($.trim(type_of_land) != ''){
            	filtersMap["type_of_land"] = type_of_land;
	      	}
        }
        
        function addInQueSubCategory(sub_category_of_land){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('sub_category_of_land')) delete filtersMap[key];
	   		});
        	if($.trim(sub_category_of_land) != ''){
       	    	filtersMap["sub_category_of_land"] = sub_category_of_land;
        	}
        }
        
        function getLandAcquisitionList(){
        	$(".page-loader-2").show();

        	getProjectsFilterList('');
        	getWorksFilterList('');
         	getvillagesFilterList('');
         	getTypesOfLandsFilterList('');
         	getSubCatogoryFilterList('');
         	
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var village = $("#village").val();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("laFilters", filters);
   			});
        	table = $('#land-acquisition-datatable').DataTable();
			 
			table.destroy();
			
			$.fn.dataTable.moment('DD-MMM-YYYY');
      	
			var myParams = "project_id_fk="+ project_id_fk+"&work_id_fk="+ work_id_fk+"&village="+ village+"&type_of_land="+ type_of_land+"&sub_category_of_land="+ sub_category_of_land ;
  		 
		    /***************************************************************************************************/   
		        
	        $("#land-acquisition-datatable").DataTable( {
			        "bProcessing": true,
			        "bServerSide": true,
			        "sort": "position",
			        //bStateSave variable you can use to save state on client cookies: set value "true" 
			        "bStateSave": false,
			        //Default: Page display length
			        "iDisplayLength": 10,
			        "iData":{"start":52},
			        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
			        "iDisplayStart": 0,
			        "fnDrawCallback": function () {
			            //Get page numer on client. Please note: number start from 0 So
			            //for the first page you will see 0 second page 1 third page 2...
			            //Un-comment below alert to see page number
			        	//alert("Current page number: "+this.fnPagingInfo().iPage);
			        },   				        
			        //"sDom": 'l<"toolbar">frtip',
   	                "initComplete": function () {
   	                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px ', 'display': 'inline-block' });
   	                   
   	                  var input = $('.dataTables_filter input').unbind(),
		   	            self = this.api(),
		   	            $searchButton = $('<i class="fa fa-search" title="Go">')
		   	                       //.text('Go')
		   	                       .click(function() {
		   	                          self.search(input.val()).draw();
		   	                       }),
		   	            $clearButton = $('<i class="fa fa-close" title="Reset">')
		   	                       //.text('X')
		   	                       .click(function() {
		   	                          input.val('');
		   	                          $searchButton.click(); 
		   	                       }) 
		   	                    $('.dataTables_filter').append('<div class="right-btns"></div>');
			   	          $('.dataTables_filter div').append($searchButton, $clearButton);
			   	          
   	                    /* var input = $('.dataTables_filter input').unbind(),
		   	            self = this.api(),
		   	            $searchButton = $('<i class="fa fa-search">')
		   	                       //.text('Go')
		   	                       .click(function() {			   	                    	 
		   	                          self.search(input.val()).draw();
		   	                       })			   	        
			   	          $('.dataTables_filter label').append($searchButton); */	
   	                },
	   	            columnDefs: [
	                     {
	                         "targets": 'no-sort',
	                         "orderable": false,
	                     },{targets:[0,2,3,5],
		                       className: 'hideCOl'}
	                ],
	   	            "sScrollX": "100%",
	                "sScrollXInner": "100%",
	                "bScrollCollapse": true,
	                "language": {
	                	 "info": "_START_ - _END_ of _TOTAL_",
	                	 paginate: {
	                		 next: '<i class="fa fa-angle-right"></i>', // or '→'
	                		 previous: '<i class="fa fa-angle-left"></i>' // or '←' 
	                	 }
	                }, 
		            "bDestroy": true,
			        "sAjaxSource": "<%=request.getContextPath()%>/ajax/get-land-acquisition?"+myParams,
			        "aoColumns": [
			            { "mData": function(data,type,row){
			            	
	                     	if($.trim(data.survey_number) == ''){ return '-'; }else{ return data.survey_number  }
            			} },   				            
            			 { "mData": function(data,type,row){
 			            	var workName = '';
 			            	if ($.trim(data.work_short_name) != '') { workName = ' - ' + $.trim(data.work_short_name) } 	
 	                     	if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk + workName; }
             			} },
			         	{ "mData": function(data,type,row){
			            	if($.trim(data.village) == ''){ return '-'; }else{ return data.village; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.type_of_land) == ''){ return '-'; }else{ return data.type_of_land; }
			            } },
			         	{ "mData": function(data,type,row){
			            	if($.trim(data.sub_category_of_land) == ''){ return '-'; }else{ return data.sub_category_of_land; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.area_of_plot) == ''){ return '-'; }else{ return data.area_of_plot; }
			            } },
			         
			         	{ "mData": function(data,type,row){
			         		var la_id = "'"+data.la_id+"'";
		                    var actions = '<a href="javascript:void(0);"  onclick="getLandAcquisition('+la_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
			            	return actions;
			            } }
			            
			        ]
			    });
        	
	  $(".page-loader-2").hide();  		     
      	
     }
        
        
        function getLandAcquisitionList2(){
        	$(".page-loader-2").show();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var village = $("#village").val();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
        	getProjectsFilterList();
        	getWorksFilterList();
         	getvillagesFilterList();
         	getTypesOfLandsFilterList();
         	getSubCatogoryFilterList();
        	table = $('#land-acquisition-datatable').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#land-acquisition-datatable').DataTable({
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
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric'
                    },
                    { orderable: false, 'aTargets': ['nosort'] }
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
    	 	var myParams = {project_id_fk : project_id_fk,village : village, work_id_fk : work_id_fk, type_of_land : type_of_land,sub_category_of_land :sub_category_of_land};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-land-acquisition",type:"POST",data:myParams,success : function(data){    				
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var la_id = "'"+val.la_id+"'";
                        var actions = '<a href="javascript:void(0);"  onclick="getLandAcquisition('+la_id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
    /*                     			  +'<a onclick="deleteLandAcquisition('+la_id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
     */                   	var rowArray = [];    	                 
                       	
                    	var workName = '';
                        if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                        
                       	rowArray.push($.trim(val.survey_number));
                       	rowArray.push($.trim(val.work_id_fk) + workName);
                       	rowArray.push($.trim(val.village));
                       	rowArray.push($.trim(val.type_of_land));
                       	rowArray.push($.trim(val.sub_category_of_land));
                       	rowArray.push($.trim(val.area_of_plot));
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
        
        function getProjectsFilterList(project) {
        	$(".page-loader").show();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var village = $("#village").val();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(project_id_fk) == "") {
            	$("#project_id_fk option:not(:first)").remove();
        	 	var myParams = {project_id_fk : project_id_fk,village : village, work_id_fk : work_id_fk, type_of_land : type_of_land,sub_category_of_land :sub_category_of_land};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInLandAcquisition",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var projectName = '';
                                 if ($.trim(val.project_name) != '') { projectName = ' - ' + $.trim(val.project_name) }
                                 var selectedFlag = (project == val.project_id_fk)?'selected':'';
    	                         $("#project_id_fk").append('<option value="' + val.project_id_fk + '"'+selectedFlag+'>' + $.trim(val.project_id_fk)   + projectName +'</option>');
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
        
        function getWorksFilterList(work) {
        	$(".page-loader").show();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var village = $("#village").val();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(work_id_fk) == "") {
            	$("#work_id_fk option:not(:first)").remove();
        	 	var myParams = {project_id_fk : project_id_fk,village : village, work_id_fk : work_id_fk, type_of_land : type_of_land,sub_category_of_land :sub_category_of_land};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInLandAcquisition",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var workShortName = '';
                                 if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
                                 var selectedFlag = (work == val.work_id_fk)?'selected':'';
    	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk)   + workShortName +'</option>');
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
        
        function getvillagesFilterList(village_name) {
        	$(".page-loader").show();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var village = $("#village").val();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(village) == "") {
            	$("#village option:not(:first)").remove();
        	 	var myParams = {project_id_fk : project_id_fk,village : village, work_id_fk : work_id_fk, type_of_land : type_of_land,sub_category_of_land :sub_category_of_land};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getVillagesFilterListInLandAcquisition",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var selectedFlag = (village_name == val.village)?'selected':'';
                            	$("#village").append('<option value="' + val.village + '"'+selectedFlag+'>' + $.trim(val.village) +'</option>');
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
        
        function getTypesOfLandsFilterList(type) {
        	$(".page-loader").show();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var village = $("#village").val();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(type_of_land) == "") {
            	$("#type_of_land option:not(:first)").remove();
        	 	var myParams = {project_id_fk : project_id_fk,village : village, work_id_fk : work_id_fk, type_of_land : type_of_land,sub_category_of_land :sub_category_of_land};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getTypesOfLandsFilterListInLandAcquisition",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var selectedFlag = (type == val.type_of_land)?'selected':'';
    	                           $("#type_of_land").append('<option value="' + val.type_of_land + '"'+selectedFlag+'>' + $.trim(val.type_of_land) +'</option>');
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
        
        function getSubCatogoryFilterList(subCategory) {
        	$(".page-loader").show();
        	var project_id_fk = $("#project_id_fk").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var village = $("#village").val();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(sub_category_of_land) == "") {
            	$("#sub_category_of_land option:not(:first)").remove();
        	 	var myParams = {project_id_fk : project_id_fk,village : village, work_id_fk : work_id_fk, type_of_land : type_of_land,sub_category_of_land :sub_category_of_land};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getSubCategoryFilterListInLandAcquisition",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            		var selectedFlag = (subCategory == val.sub_category_of_land)?'selected':'';
    	                            $("#sub_category_of_land").append('<option value="' + val.sub_category_of_land + '"'+selectedFlag+'>' + $.trim(val.sub_category_of_land) +'</option>');
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
        
        
        function getLandAcquisition(la_id){
        	$("#la_id").val(la_id);
        	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-land-acquisition');
        	$('#getForm').submit();
        }
        function deleteLandAcquisition(la_id){
        	$("#la_id").val(la_id);
        	showCancelMessage();
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
        
        
      	
        function showCancelMessage() {
        	swal({
                title: "Are you sure?",
                text: "You will be able to change the status of record!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, delete it!",
                cancelButtonText: "No, cancel it!",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                   // swal("Deleted!", "Record has been deleted", "success");
                	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-land-acquisition');
        	    	$('#getForm').submit();
               }else {
                    swal("Cancelled", "Record is safe :)", "error");
                }
            });
        }
    </script>
</body>

</html>