<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Delete Risk Assessment</title>
    <%--<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">--%>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
    <%--<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
    <link rel="stylesheet" href="/pmis/resources/css/rightColumnFixed.css">--%>
    <style>
        .error {
            color: red;
        }
		.dataTables_length{
		    text-align: center;
		}
		th{    		
    		text-transform: capitalize;    		
		}
		.fw-50{
			width: 50vw !important;;
			max-width: 50vw;
		} 
		.fw-20{
			width: 20vw !important;;
			max-width: 20vw;
		}
		@media (min-width: 480px) and (max-width: 839px){
		    .mdl-cell--6-col, .mdl-cell--6-col-tablet.mdl-cell--6-col-tablet {
		        width: 100%;
		        text-align:center;
		    }
		    div.dataTables_wrapper div.dataTables_filter{
		            text-align:center;
		    }
		}
    </style>
</head>

<body>


    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Delete  Assessment</h6>
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
                    <div class="">
                     	<div class="row no-mar">
                     	 	<div class="col s12 m4 l2 offset-m3 offset-l4 input-field">
                                <p class="searchable_label">Select Sub Work</p>
                                <select id="sub_work" name="sub_work"  class="searchable" onchange="addInQueRisk(this.value);risksList();">
                                    <option value="">Select</option>	                                    
                                </select>
                           	</div>
                           	<div class="col s12 m4 l2 center-align">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 20px;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                     	</div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="risk_delete_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Sub Work</th>
                                            <th>Date</th>                                            
                                            <th>Count</th>
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


	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="sub_work" id="sub_work_id" />
    	<input type="hidden" name="date" id="date" />
    </form>
     <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
<script src="/pmis/resources/js/dataTables.fixedColumns.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    <script>
    
    var filtersMap = new Object();
    
        $(document).ready(function () { 
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
            var filters = window.localStorage.getItem("riskFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'sub_work' ){
    		        		  getSubWorkFilter(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            risksList();
        });
        
        
      function addInQueRisk(sub_work){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('sub_work')) delete filtersMap[key];
       		});
        	if($.trim(sub_work) != ''){
       	    	filtersMap["sub_work"] = sub_work;
        	}
        }
  
	  function deleteRow(date,subWork){ 
		if(date === "null"){
			date = "";
		}
		
	  	$("#sub_work_id").val(subWork);
	  	$("#date").val(date);
	  	showCancelMessage(); 
	  }
	  	
	  function showCancelMessage() {
	    	swal({
		            title: "Are you sure?",
		            text: "You will be changing the status of the record!",
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
		                $(".page-loader").show();
		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-risk');
		    	    	$('#getForm').submit();
		           }else {
		                swal("Cancelled", "Record is safe :)", "error");
		            }
		        });
	  }
	
	  function getSubWorkFilter(subWork) {
      	$(".page-loader").show();
          var sub_work = $("#sub_work").val();
  		if ($.trim(sub_work) == "") {
          	$("#sub_work option:not(:first)").remove();
          	var myParams = { sub_work: sub_work};
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getSubWorkFilterListInRiskDelete",
                  data: myParams, cache: false,async: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
                        	var selectedFlag = (subWork == val.sub_work)?'selected':'';
  	                        $("#sub_work").append('<option value="' + val.sub_work + '"'+selectedFlag+'>'   + val.sub_work +'</option>');
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
	  
	  function risksList(){
		$(".page-loader-2").show();
		getSubWorkFilter('');
		var sub_work = $("#sub_work").val();
		var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("riskFilters", filters);
			});
      	table = $('#risk_delete_table').DataTable();
  		table.destroy();
  		$.fn.dataTable.moment('DD-MMM-YYYY');
  		table = $('#risk_delete_table').DataTable({
  			"order": [],
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
                      //targets: [1],
                      //className: 'mdl-data-table__cell--non-numeric',
                      targets: 'no-sort', orderable: false,
                  },
                  {targets: [0], className: 'fw-50'},
                  {targets: [1,2], className: 'fw-20'},
                  { "width": "20px", "targets": [3] },
              ],
              // "ScrollX": true,
              "sScrollX": "100%",
               "sScrollXInner": "100%",
               paging:false,
               "bScrollCollapse": true,
               fixedColumns:   {
            	    right: 1
            	},
              initComplete: function () {
                  $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                  var input = $('.dataTables_filter input');
                  self = this.api();
                  $clearButton = $(	'<i class="fa fa-close" title="Reset">')
                      .click(function() {		input.val(''); self.search(input.val()).draw(); 	});
                  $('.dataTables_filter > label').append(	$clearButton); 
              }
          }).rows().remove().draw();
  		
  		table.state.clear();		
  		var myParams = {sub_work: sub_work};
  		$.ajax({url : "<%=request.getContextPath()%>/ajax/getRisksListInRiskDelete",type:"POST",
  			data:myParams,async: false,
  			success : function(data){    				
  				if(data != null && data != '' && data.length > 0){    					
	             		$.each(data,function(key,val){
	                        
	                        var actions = '<a href="javascript:void(0);" onclick="deleteRow(\''+val.date +'\',\'' +val.sub_work+'\');" c class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a>'
	                        var rowArray = [];    	                 
	                        
	                       	rowArray.push($.trim(val.sub_work));
	                       	rowArray.push($.trim(val.date));
	                       	rowArray.push($.trim(val.count));
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
	  function clearFilters() {
          $('#sub_work').val("");      
          $('.searchable').select2();
          window.localStorage.setItem("riskFilters",'');
      	  window.location.href="<%=request.getContextPath()%>/risk-delete"
      }
	  
</script>

</body>


</html>