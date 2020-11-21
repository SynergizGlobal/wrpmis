<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PMIS Progress Form</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
      <style>
        .mdl-data-table {
            border: 1px solid #ccc;
        }

        .hiddendiv.common {
            width: 99vw !important;
        }

        thead th input[type="checkbox"]+span:not(.lever):before {
            border: 2px solid #fff;
        }

        thead th input[type="checkbox"]:checked+span:not(.lever):before {
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }

        .input-field .searchable_label {
            font-size: .9rem;
        }

        td,
        .date-holder {
            position: relative;
        }

        .date-holder .datepicker~button {
            top: 30%;
            right: 0;
        }

        td .datepicker~button {
            top: 30px;
        }

        td .datepicker {
            font-size: 0.87rem !important;
        }

        td .datepicker-table thead tr,
        td .datepicker-table thead tr:hover,
        td .datepicker-table tbody tr,
        td .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }

        td .datepicker-table td:first-of-type,
        td .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        td .datepicker-table th,
        td .datepicker-table td {
            padding: 0;
            border: 0;
        }

        @media only screen and (max-width: 700px) {
            div.dataTables_wrapper div.dataTables_filter input {
                width: 90% !important;
            }
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
		 .page-loader-2 {
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
            <div class="card">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>PMIS Progress Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath() %>/update=pmis-progrss-form" id="progressForm" name="progressForm" method="post" >
                        <div class="row">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col m10 s12">

                                <div class="row ">
                                    <div class="col m3 hide-on-small-only"></div>
                                    <div class="col m6 s12">
                                        <div class="row" style="margin-bottom: 0;">
                                            <div class="col m2 hide-on-small-only"></div>
                                            <div class="col s12 m4 input-field">
                                                <p class="searchable_label">Milestone</p>
                                                <select id="contract_id_fk" name="contract_id_fk" onchange="getMileStoneFilterList();" class="searchable">
                                                    <option value="">Select</option>
                                                    
                                                </select>
                                            </div>
                                            <div class="col s12 m4 input-field">
                                                <button class="btn bg-m waves-effect waves-light t-c clear-filters "  onclick="clearFilter();"
                                                    style="margin-top: 8px;width: 100%;">Clear Filters</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col m3 hide-on-small-only"></div>
                                </div>
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col m8 s12 center-align" style="margin-top: 10px;">
                                        <a class="btn waves-effect bg-m" onclick="updateActual()">Finish
                                            Activities</a>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>

                                <div class="row fixed-width" style="margin-bottom: 30px;">
                                    <div class="table-inside">
                                        <table class="mdl-data-table" id="datatable-table">
                                            <thead>
                                                <tr>
                                                    <th>
                                                        <p>
                                                            <label>
                                                                <input type="checkbox" name="select-all"
                                                                    id="select-all" />
                                                                <span></span>
                                                            </label>
                                                        </p>
                                                    </th>
                                                    <th>Milestone</th>
                                                    <th>Activity</th>
                                                    <th>Planned <br> Start Date</th>
                                                    <th>Planned <br> Finish Date</th>
                                                    <th>Actual <br> Start Date </th>
                                                    <th>Actual <br> Finish Date</th>
                                                    <th>Scope</th>
                                                    <th>Completed</th>
                                                    <th class="no-sort">Actual</th>
                                                </tr>
                                            </thead>
                                            <tbody id="filerList">
                                                <!-- <tr>
                                                    <td>
                                                        <p>
                                                            <label>
                                                                <input type="checkbox" name="activity_check"
                                                                    id="check_1" />
                                                                <span></span>
                                                            </label>
                                                        </p>
                                                    </td>
                                                    <td>Submission of Project Charter </td>
                                                    <td>Software Requirements Specifications (Submission)</td>
                                                    <td>11/12/2019</td>
                                                    <td>13/10/2020</td>
                                                    <td>
                                                        <div class="date-holder">
                                                            <input id="start_date" type="text"
                                                                class="validate datepicker" placeholder="Start Date"
                                                                value="11/12/2019">
                                                            <button type="button" id="start_date_icon" class="white"><i
                                                                    class="fa fa-calendar"></i></button>
                                                    </td>
                                    </div>
                                    <td>

                                        <div class="date-holder">
                                            <input id="finish_date" type="text" class="validate datepicker"
                                                placeholder="Finish Date" value="13/10/2020">
                                            <button type="button" id="finish_date_icon" class="white"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                    </td>
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
                            <div class="row">
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">

                                    <input type="hidden" id="strip_chart_id" name="strip_chart_id" />
                                    <div class="row">
                                        <!-- <div class="col s12 m4">
                                                <div class="center-align m-1">
                                                    <button type="button" class="btn waves-effect waves-light bg-m"
                                                        style="width: 100%;" onclick="updateActual()">Finish
                                                        Activities</button>
                                                </div>
                                            </div> -->
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="button" onclick="updateProgress();" class="btn waves-effect waves-light bg-m"
                                                    style="width: 100%;">Update</button>
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
                        </div>

                </div>
                </form>
            </div>
        </div>
        <!-- form ends  -->
    </div>

    <!-- Page Loader starts-->
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
    
    <!-- Page Loader ends-->
    
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
	    $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    });
        $(document).ready(function () {
            $('.searchable').select2();
           // $('#start_date,#finish_date').datepicker();
            $('#start_date_icon').click(function () {
                event.stopPropagation();
                $('#start_date').click();
            });
            $('#finish_date_icon').click(function () {
                event.stopPropagation();
                $('#finish_date').click();
            });

            $('#datatable-table').DataTable({
                columnDefs: [
                    {
                        // targets: [0, 1, 2],
                        // className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [6] },
                ],
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
                 paging: true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
            getMileStoneFilterList();
        });
        function clearFilter(){
        	$(".page-loader").show();
            $("#contract_id_fk").val("");
        	$('.searchable').select2();
        	getMileStoneFilterList();
        }

        function getMileStoneList() {
        	$(".page-loader").show();
        	 var contract_id_fk = $("#contract_id_fk").val();
    		if ($.trim(contract_id_fk) == "") {
            	$("#contract_id_fk option:not(:first)").remove();
            	var myParams = { contract_id_fk: contract_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractMileStonesFilterList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '">' + $.trim(val.milestone_name)   +'</option>');
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
        
        
        function getMileStoneFilterList(){
       	 $(".page-loader-2").show();
       	 var html = '';
       	 getMileStoneList();
       	 $("#filerList").html('');
       	 $("#select-all").prop('checked', false);
       	 var contract_id_fk = $("#contract_id_fk").val();
       	table = $('#datatable-table').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-table').DataTable({
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
  	     var myParams = {contract_id_fk : contract_id_fk };
  	     $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getMileStoneList",
            data: myParams, cache: false,
            success: function (data) {
  	            	
  	                if (data.length > 0) {
  	                    $.each(data, function (i, val) {
	  	                    var num = i;
	  	                  	var rowArray = []; 
	  	                    var checkBox = 	'<tr id="row'+num+'"><td><p><label><input type="checkbox" class="check" name="activity_check" id="check_'+num+'"/><span></span></label></p></td>';
		  	                    			'<input type="hidden" name="ids"  id="ids'+num+'"  value="' + $.trim(val.id) + '" /></td>';
	                    	var milestone_name =  '<td>' + $.trim(val.milestone_name) + '</td>';
	                    	var activity_description =	'<td>' + $.trim(val.activity_description) + '</td>';
	                    	var planned_start =	'<td>' + $.trim(val.planned_start) + '</td>';
	                    	var planned_finish = '<td>' + $.trim(val.planned_finish) + '</td>';
	                    	var actual_starts =	'<td> <div class="date-holder"><input id="actual_starts'+num+'" name="actual_starts" type="text" class="validate datepicker" placeholder="Start Date" value="' + $.trim(val.actual_start) + '">'
                            					+'<button type="button" id="start_date_icon'+num+'" class="white"><i class="fa fa-calendar"></i></button></td>';
                           	var actual_finishs =  '<td> <div class="date-holder"><input id="actual_finishs'+num+'" name="actual_finishs" type="text" class="validate datepicker" placeholder="Finish Date" value="' + $.trim(val.actual_finish) + '">'
                          						+'<button type="button" id="finish_date_icon'+num+'" class="white"><i class="fa fa-calendar"></i></button></td>';
                          	var totalScope =	'<td><span>' + $.trim(val.total_scope) + '</span>'
          	 								+'<input type="hidden" name="totalScopes"  id="totalScopes'+num+'"  value="' + $.trim(val.total_scope) + '" /></td>';
          	 				var completed = '<td><span>' + $.trim(val.completed) + '</span>'
          	 								+'<input type="hidden" name="completedScopes"  id="completedScopes'+num+'"  value="' + $.trim(val.completed) + '" /></td>';
          	 				var actual = '<td class="input-field"><input type="text" name="actualScopes" id="actualScopes'+num+'" readonly></td></tr>';
                   			rowArray.push(checkBox);
                   			rowArray.push(milestone_name);
                   			rowArray.push(activity_description);
                   			rowArray.push(planned_start);
                   			rowArray.push(planned_finish);
                   			rowArray.push(actual_starts);
                   			rowArray.push(actual_finishs);
                   			rowArray.push(totalScope);
                   			rowArray.push(completed);
                   			rowArray.push(actual);
  	                    			 
                    			 
                    		//$("#filerList").append(html);
                    		 table.row.add(rowArray).draw( true );
                    	 	
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
  	               $(".page-loader-2").hide();
  	            }
  	        });
  	    
       
       }
     
        
        // update actual function for single value with ids
        function updateActual(){
         $('input[name="activity_check"]').each(function(){
             if($(this).prop('checked')){    
                 let no = $(this).attr('id').split("_")[1];
                 //alert($('#totalScopes'+no).val())
                 var scope = $('#totalScopes'+no).val();
                 var completed = $('#completedScopes'+no).val();
                 
                 if($.trim(scope) != '' && $.trim(completed) != '' ){
                	 let remaining = parseFloat(scope)- parseFloat(completed);
                     $('#actualScopes'+no).val(remaining);
                 }
                 
             }
         })           
     }
  

        // select or deselect all checkboxes 
        $('#select-all').change(function () {
            var _this = this;
            $('input[name="activity_check"]').each(function () {
                if ($(_this).is(':checked')) {
                    $(this).prop('checked', true);
                } else {
                    $(this).prop('checked', false);
                }
            });
        });

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
        
      
        //update button functionality
        function updateProgress(){
       	 if(validator.form()){ // validation perform
   	        	$(".page-loader").show();	    		
   	   			document.getElementById("progressForm").submit();	
        	}
        }
        
        
        var validator = $('#progressForm').validate({
       	 ignore: ":hidden:not(.validate-dropdown)",
       	 rules: {
       		  "activity_check" :{
       			 required: true
       		  },"contract_id_fk": {
   		 		required: true
   		 	  },"actualScopes": {
   		 		 required: function(element){
   		             return $(".check").is(':checked');
   		         }
   		 	  }
       	 },
               messages: {
                    "activity_check": {
                       required: "You must check at least 1 box"
                    },"project_id": {
   			 		required: 'Select project'
   			 	 },"contract_id_fk": {
   		 			required: 'Select contract'
   		 	  	 }
       	 },errorPlacement:function(error, element){
     		 	        if(element.attr("name") == "activity_check" ){
   					     document.getElementById("checkBoxError").innerHTML="";
   				 	     error.appendTo('#checkBoxError');
     		 	        }else if (element.attr("id") == "contract_id_fk" ){
   			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
   			 			 error.appendTo('#contract_id_fkError');
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