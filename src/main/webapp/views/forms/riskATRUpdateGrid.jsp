<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risk : Update ATR</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
      <style>

        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        .tabs .tab a:not(.active) {
            color: #999999;
        }

        .tabs .tab a:focus.active {
            background-color: #e5e5e5;
        }

        .tabs .tab a.active {
            color: #444;
            background-color: #dadada;
            border-bottom: 1px solid #444;
        }

        .tabs .indicator {
            background-color: #888;
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
       
        td {
        	word-break: break-word;
    		white-space: initial;
		}
		.fw-120{
        	width:120px !important;
        	max-width:120px;
        }
		.fw-200{
        	width:200px;
        	max-width:200px;
        }
        .fw-250{
        	width:250px;
        	max-width:250px;
        }
      
		.error-msg label{color:red!important;}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>


    <div class="row" style="margin-bottom:0;">
        <div class="col s12 m12">
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
							<div class="center-align m-1 close-message">	
							   ${updateSuccess}
							</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>
                        

                        <!--if  model 2 -->
                        <div class="row no-mar">
                            <div class="col m3 hide-on-small-only"></div>
                             <div class="col m6 s12"> 
								<div class="col s12 m4 input-field">
	                                <p class="searchable_label">Sub Work</p>
	                                  <select id="sub_work" name="sub_work" onchange="getRiskList();" class="searchable" required="required">
                                      	<option value="" >Select</option>	                                           
                                      </select>
	                            </div>
	                            <div class="col s12 m4 input-field">
	                                <p class="searchable_label">Area</p>
	                                  <select id="area" name="area" onchange="getRiskList();" class="searchable">
	                                            <option value="" >Select </option>	                                           
	                                 </select>
	                            </div>
	                            <div class="col s12 m4 input-field">
	                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                    style="margin-top: 6px;width: 100%;" onclick="clearFilters()">Clear
	                                    Filters</button>
	                            </div>
                            </div> 
                            <div class="col m3 hide-on-small-only"></div>

                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-risk" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Item No.</th>
                                            <th>Risk Id</th>
                                            <th>Sub Work</th>
                                            <th>Area</th>
                                            <th>Sub Area</th>
                                            <th>Action</th>
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
    </form>

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
    	  $(".modal").modal();
          $('select:not(.searchable)').formSelect();
          $('.searchable').select2();
          $('.tabs').tabs();
          getRiskList();
      	});
        
        
        function clearFilters() {
            $('#sub_work').val('');
            $('#area').val('');
            getRiskList();
            $('.searchable').select2();
        }
        
        
        function getRiskList(){
        	$(".page-loader-2").show();
        	var sub_work = $("#sub_work").val();
        	var area = $("#area").val();
        	getSubWorksFilterList();
         	getAreasFilterList();
        	table = $('#datatable-risk').DataTable();
    		table.destroy();
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-risk').DataTable({
        		"bStateSave": false,
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
    		var myParams = {sub_work : sub_work,area : area};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getRiskAssessmentList",type:"POST",data:myParams,success : function(data){    				
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var risk_id_pk = "'"+val.risk_id_pk+"'";
                        var actions = '<a href="javascript:void(0);"  onclick="getRisk('+ risk_id_pk +');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>';
    					var rowArray = [];    	                 
    
                    	var workName = '';
                        if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                        
                        rowArray.push($.trim(val.area_item_no) + "." + $.trim(val.sub_area_item_no));
                       	rowArray.push($.trim(val.risk_id));
                       	rowArray.push($.trim(val.sub_work));
                       	rowArray.push($.trim(val.area));
                       	rowArray.push($.trim(val.sub_area));
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
        
        
        function getSubWorksFilterList() {
        	$(".page-loader").show();
        	var sub_work = $("#sub_work").val();
        	var area = $("#area").val();
            if ($.trim(sub_work) == "") {
            	$("#sub_work option:not(:first)").remove();
            	var myParams = {sub_work : sub_work,area : area};
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getSubWorksFilterListInRiskAssessmnt",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	$("#sub_work").append('<option value="' + val.sub_work + '">' + $.trim(val.sub_work) +'</option>');
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
        
        function getAreasFilterList() {
        	$(".page-loader").show();
        	var sub_work = $("#sub_work").val();
        	var area = $("#area").val();
            if ($.trim(area) == "") {
            	$("#area option:not(:first)").remove();
        		var myParams = {sub_work : sub_work,area : area};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getAreasFilterListInRiskAssessment",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#area").append('<option value="' + val.risk_area_fk + '">' + $.trim(val.risk_area_fk)   +'</option>');
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
        
        function getRisk(risk_id_pk){
        	$("#risk_id_pk").val(risk_id_pk);
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