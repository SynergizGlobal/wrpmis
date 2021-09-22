<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risk : Update ATR - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
       <style>

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
        .btn.disabled,
        .btn.disabled * {
            color: #999 !important;
        }
        @media only screen and (max-width: 700px) {
            .mt-md-54 {
                margin-top: inherit;
            }
        } 
         @media only screen and (max-width: 768px) {
            .fw-111 {
                width:20vw !important;
        		max-width:20vw;
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
        .fw-150{
        	width:150px !important;
        	min-width:150px;
        }
        .fw-170{
        	width:170px !important;
        	min-width:170px;
        }
		.fw-200{
        	width:200px;
        	min-width:200px;
        }
        .fw-250{
        	width:250px;
        	min-width:250px;
        } 
        .fw-300{
        	width:300px;
        	min-width:300px;
        }
        .fw-110{
        	width:110px;
        	min-width:110px;
        }
         @media only screen and (min-width: 769px){ 
	        .fw-111{
	        	width:128px;
	        	min-width:128px;
	        }
        }
        .link-btn{
        	color:blue;
        	text-decoration:underline;
        	cursor:pointer;
        }      
		.error-msg label{color:red!important;}
		
		.dt-left{text-align: left!important;}
		.dt-center{text-align: center!important;}
	   	.clear-filters{
        	margin-top:4px;
         }
		 @media only screen and (max-width: 769px){ 			
			.dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
				max-width: 45px;
			}
			 .fw-30{
	        	width:30vw !important;
	        	min-width:30vw;
	        }
			.mob-btn{
				padding:0 12px;
			}
			.hideCOl{
				display:none;
			}
			.f-300{
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
	        .clear-filters{
	        	margin-top:14px;
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
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>


    <div class="row" style="margin-bottom:0;">
        <div class="col s12 m12 ">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Update ATR</h6>
                        </div>
                    </span>
                    <div class="">
                    <c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success} 
							</div> 
							 <br>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error} 
							</div>
							<br>
						</c:if>
                        

                        <!--if  model 2 -->
                        <div class="row no-mar">
                             <div class="col m8 l6 s12 offset-m2 offset-l4"> 
								<div class="col s6 m4 input-field">
	                                <p class="searchable_label">Work</p>
	                                  <select id="sub_work" name="sub_work" onchange="addInQueSubWork(this.value);getAssessmentDatesFilterList('');getRiskList();" class="searchable" required="required">
                                      	<option value="" >Select</option>	                                           
                                      </select>
	                            </div>
	                            
	                            <!-- <div class="col s12 m4 input-field">
	                                <p class="searchable_label">Area</p>
	                                  <select id="area" name="area" onchange="getRiskList();" class="searchable">
	                                            <option value="" >Select </option>	                                           
	                                 </select>
	                            </div> -->
	                            
	                            <div class="col s6 m4 input-field" id="assessmentDatesDropdown" style="display: none;">
	                                <p class="searchable_label">Assessment Date</p>
	                                 <select id="assessment_date" name="assessment_date" onchange="addInQueAssessmentDate(this.value);getRiskList();" class="searchable">
	                                      <!-- <option value="" >Select </option> -->	                                           
	                                 </select>
	                            </div> 
	                            <div class="col s12 m4 center-align">
	                                <button class="btn bg-s waves-effect waves-light t-c clear-filters" onclick="clearFilters()">Clear
	                                    Filters</button>
	                            </div>
                            </div> 

                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-risk" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th class="no-sort">Item No.</th>
                                            <!-- <th>Risk Id</th> -->
                                            <th>Work</th>
                                            <th class="f-300">Area</th>
                                            <th>Sub Area</th>
                                            <th>Priority</th>
                                            <th>Mitigation Plan</th>
                                            <th>Action Taken</th>
                                            <th class="nosort">Action</th>
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
    <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
    <form action="<%=request.getContextPath()%>/get-risk-assessment" name="getForm" id="getForm" method="post">
    	<input type="hidden" name="risk_id_pk" id="risk_id_pk" />
    	<input type="hidden" name="risk_revision_id" id="risk_revision_id" />
    </form>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script type="text/javascript">       
        var filtersMap = new Object();
        var pageNo = window.localStorage.getItem("riskPageNo");
        $(document).ready(function () {
	    	  $(".modal").modal();
	          $('select:not(.searchable)').formSelect();
	          $('.searchable').select2();
	          $('.tabs').tabs();
	          
	          //$('.close-message').delay(5000).fadeOut('slow');
	          
	          var sub_work = "${sub_work}";
		      var assessment_date = "${assessment_date}";
	          
	          var filters = window.localStorage.getItem("riskFilters");
	          
	          if($.trim(sub_work) != '' || $.trim(assessment_date) != ''){
	        	  getSubWorksFilterList(sub_work);
		          getAssessmentDatesFilterList(assessment_date);
		          
		          addInQueSubWork(sub_work);
		          addInQueAssessmentDate(assessment_date);
	          }else if($.trim(filters) != '' && $.trim(filters) != null){
	        	  
	        	  var temp = filters.split('^'); 
	        	  for(var i=0;i< temp.length;i++){
		        	  if($.trim(temp[i]) != '' ){
		        		  var temp2 = temp[i].split('=');
			        	  if($.trim(temp2[0]) == 'sub_work' ){
			        		  getSubWorksFilterList(temp2[1]);
			        	  }else if($.trim(temp2[0]) == 'assessment_date'){
			        		  getAssessmentDatesFilterList(temp2[1]);
			        	  }
		        	  }
		          }
	          }else{
	        	  getSubWorksFilterList(sub_work);
		          getAssessmentDatesFilterList(assessment_date);
	          }
	          
	          getRiskList();
	          
        });
        
        
        function clearFilters() {
        	$("#assessmentDatesDropdown").hide();
            $('#sub_work').val('');
            $('#assessment_date').val('');
            $('.searchable').select2();
            //window.localStorage.clear();
            window.localStorage.setItem("riskFilters",'');
            window.location.href = "<%=request.getContextPath()%>/risk-atr-update";
        	var table = $('#datatable-risk').DataTable();
        	table.draw( true );
            //getRiskList();            
        }
        
        function addInQueSubWork(sub_work){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('sub_work')) delete filtersMap[key];
	   		});
        	if($.trim(sub_work) != ''){
       	    	filtersMap["sub_work"] = sub_work;
        	}
        }
        
        function addInQueAssessmentDate(assessment_date){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('assessment_date')) delete filtersMap[key];
	   	   	});
	      	if($.trim(assessment_date) != ''){
            	filtersMap["assessment_date"] = assessment_date;
	      	}
        }
        
        function getRiskList(){
        	$(".page-loader-2").show();
        	
        	var sub_work = $("#sub_work").val();
	        var assessment_date = $("#assessment_date").val();
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("riskFilters", filters);
   			});
        	
        	table = $('#datatable-risk').DataTable();
    		table.destroy();
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-risk').DataTable({
    			 "sPaginationType": "full_numbers",
           		"bStateSave": true,  
           		fixedHeader: true,
           		 stateSave: true,
               	//Default: Page display length
   				"iDisplayLength" : 10,
   				"iData" : {
   					"start" : 52
   				},"iDisplayStart" : 0,
   				"drawCallback" : function() {
   					var info = table.page.info();
   					window.localStorage.setItem("riskPageNo", info.page);
   				},
                columnDefs: [
                   
                    { "width": "20px", "targets": [6] }, { targets: [0, 1, 4, 5, 6], className: 'hideCOl'  },
                    { orderable: false, 'aTargets': ['nosort'] },
                    { targets: [1, 2, 3, 5, 6], className: 'dt-left'  },
                    { targets: [0,4], className: 'dt-center'},
                    { targets: [0,4], className: 'fw-120'  },
                    { targets: [1,3], className: 'fw-170'  },
                    { targets: [5,6], className: 'fw-200'  },
                    { targets: [7], className: 'fw-111'  },
                    { targets: [2,3], className: 'fw-30'  },
                    { targets: [7], className: 'fw-111'  },
                    { targets: [2], className: 'fw-110'  },
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                 "sScrollXInner": "100%",
                 "ordering":false,
                 "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input')
					.unbind(), self = this.api(), $searchButton = $(
					'<i class="fa fa-search" title="Go">')
					.click(function() {
						self.search(input.val()).draw();
					}), $clearButton = $(
							'<i class="fa fa-close" title="Reset">')
					.click(function() {
						input.val('');
						$searchButton.click();
					})
					$('.dataTables_filter').append( '<div class="right-btns"></div>');
					$('.dataTables_filter div').append( $searchButton, $clearButton);
                }
            }).rows().remove().draw();
    		
    		table.state.clear();		
    		var myParams = {sub_work : sub_work,assessment_date : assessment_date};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getRiskAssessmentList",
    			type:"POST",data:myParams, cache: false,async:false,
    			success : function(data){    				
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var risk_id_pk = "'"+val.risk_id_pk+"'";
             			var risk_revision_id = "'"+val.risk_revision_id+"'";
                        var actions = '<a href="javascript:void(0);" onclick="getRisk('+ risk_id_pk +','+risk_revision_id +');" class="btn waves-effect waves-light bg-m t-c mob-btn"><i class="fa fa-pencil"></i></a>';
    					var rowArray = [];    	                 
    
                    	var workName = '';
                        if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                        
                        rowArray.push($.trim(val.area_item_no) + "." + $.trim(val.sub_area_item_no));
                       	//rowArray.push($.trim(val.risk_id));
                       	rowArray.push($.trim(val.sub_work));
                       	rowArray.push($.trim(val.area));
                       	rowArray.push($.trim(val.sub_area));
                       	rowArray.push($.trim(val.priority_fk));
                       	rowArray.push($.trim(val.mitigation_plan));
                       	rowArray.push($.trim(val.action_taken));
                       	rowArray.push($.trim(actions));   	                   	
                       	
                        table.row.add(rowArray).draw( true );
                        		                       
    				}); 
             		 if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
        	         var oTable = $('#datatable-risk').dataTable();
        	         oTable.fnPageChange( pageNo );
             		 $(".page-loader-2").hide();
    			}else{
    				$(".page-loader-2").hide();
    			}
    			
    		},error: function (jqXHR, exception) {
    			$(".page-loader-2").hide();
             	getErrorMessage(jqXHR, exception);
         }});
       }
        
        
        function getSubWorksFilterList(work) {
        	$(".page-loader").show();
        	var sub_work = $("#sub_work").val();
        	var assessment_date = $("#assessment_date").val();
        	
            //if ($.trim(sub_work) == "") {
            	$("#sub_work option:not(:first)").remove();
            	var myParams = {sub_work : sub_work};
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getSubWorksFilterListInRiskAssessmnt",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var selectedFalg = '';
                            	if($.trim(work) != ''){
                            		if($.trim(work).toLowerCase() == $.trim(val.sub_work).toLowerCase()){selectedFalg = 'selected';addInQueSubWork(val.sub_work);};
                            	}
                            	$("#sub_work").append('<option value="' + val.sub_work + '" '+selectedFalg+'>' + $.trim(val.sub_work) +'</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
     	   			      $(".page-loader").hide();
    	   	          	  getErrorMessage(jqXHR, exception);
    	   	     	  }
                });
            /* }else{
            	  $(".page-loader").hide();
            } */
            
            var sub_work = $("#sub_work").val();
            if($.trim(sub_work) != ''){
            	//$("#assessmentDatesDropdown").show();
            	getAssessmentDatesFilterList('');
            }
            
        }
        
        function getAssessmentDatesFilterList(date) {
        	$("#assessment_date option").remove();
        	$(".page-loader").show();
        	var sub_work = $("#sub_work").val();
        	var assessment_date = $("#assessment_date").val();
        	
            if ($.trim(sub_work) != "") {
            	$("#assessmentDatesDropdown").show();
            	/*$("#assessment_date option:not(:first)").remove();*/
        		var myParams = {sub_work : sub_work,assessment_date : assessment_date};
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getAssessmentDatesFilterListInRiskAssessment",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	console.log(val.assessment_date);
                            	var selectedFalg = '';
                            	if(date == val.assessment_date){
                            		selectedFalg = 'selected';
                            		addInQueAssessmentDate(val.assessment_date);
                            	}else if($.trim(date) == '' && i == 0){
                            		selectedFalg = 'selected';
                            		addInQueAssessmentDate(val.assessment_date);
                            	}
                        		
    	                        $("#assessment_date").append('<option value="' + val.assessment_date + '" '+selectedFalg+'>' + $.trim(val.assessment_date)   +'</option>');
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
        
        function getRisk(risk_id_pk,risk_revision_id){
        	$("#risk_id_pk").val(risk_id_pk);
        	$("#risk_revision_id").val(risk_revision_id);
        	$('#getForm').submit();
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
        
    </script>

</body>

</html>