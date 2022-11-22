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
    <title>Contract Executives</title>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
</head>
<style>
    	.my-error{
    		text-transform: uppercase;
    		font-size: 1rem;
    		color:red;
    	}
    </style>
<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5"> 
                            <h6>Contract Executives</h6>
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
                            <div class="col m12 s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Executives</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="contract_executives_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Work</th>
                                            <th>Executives</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="obj" items="${executivesDetails}" varStatus="indexs">
											<tr>
											<td>
												<input type="hidden" id="contract_work_id_fk${indexs.count}" value="${obj.work_id_fk }" /> 
												${obj.work_short_name }</td>
											<td>
												<input type="hidden" id="contract_user_id${indexs.count}" value="${obj.user_id }" /> 
												${obj.user_name }</td>
									
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger " href="#"> <i class="fa fa-pencil" ></i></a>
 											</td></tr>											 
 									   </c:forEach>
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

    <!-- Modal Structure -->
    <div id="addUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/add-contract-executives" id="addRrExecutivesForm" name="addRrExecutivesForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Add Executives <span
                        class="right modal-action modal-close" onclick="resetFields('add')"> <span class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col s12">
                        <div class="row no-mar">
                             <div class="table-inside">
                              <div class="center-align">
					        	<span id="mainErrorAdd" style="align: center;" class="my-error "></span>
					        </div>
					            <table id="addExecutivesTable" class="mdl-data-table mobile_responsible_table" >
					                <thead>
					                    <tr>
					                        <th>Work</th>
											<th>Responsible Executives </th>
											<%-- <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}"> --%>
											<%-- </c:if> --%>
					                    </tr>
					                </thead>
					                
								    <tbody id="addExecutivesBody">									               
					                    <tr id="AexecutiveRow0">
					                        <td data-head="Work" class="input-field">
					                             <select class="searchable validate-dropdown work" name="work_id_fks" id="work_id_fk0" onChange="getResponsibleUsers(this.value);">
					                                	<option value="" >Select</option>  	
					                                	<c:forEach var="obj" items="${workDetails}" >
					                                		  <option value= "${obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
					                                	
					                                	</c:forEach>														         
					                              </select>
					                              <span id="workError0" class="my-error"></span>
					                        </td>
					                        <td data-head="Responsible Executives" class="input-field h-auto">
					                        <input type="hidden"  id="executive_user_id_fk0" name="executive_user_id_fks" />
					                            <select class="searchable validate-dropdown" name="executive_user_id_fk" onchange="getRowsCount('0');"
					                                id="responsible_executives_id_fks0" multiple="multiple" >
					                                <option >Select</option>			
					                                  <c:forEach var="obj" items="${usersDetails}" >
					                                		  <option value= "${obj.user_id}">${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>
					                                	
					                                	</c:forEach>											 			 		                             	
					                            </select>
					                            <span id="executiveError0" class="my-error"></span>
					                        </td>
							                        
					                    </tr>
					             
					                </tbody>								                

								</table>
                                                  
					        </div>
					       
					        
                        </div>
                        
                        <div class="row">
                        	<div class="col s12 m8 offset-m2">
                        		<div class="row">
                        			 <div class="col s12 m6">
		                                <div class="center-align m-1">
		                                    <button style="width: 100%;" type="button" onclick="addRrExecutives()" class="btn waves-effect waves-light bg-m">Add </button>
		                                </div>
		                            </div>
		                            <div class="col s12 m6">
		                                <div class="center-align m-1">
		                                  
		                                         <a href="<%=request.getContextPath()%>/contract-executives"
											     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
		                                </div>
		                            </div>
                        		</div>
                        	</div>
                           
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
    
     <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-contract-executives" id=updateRrExecutivesForm name="updateRrExecutivesForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header bg-m">Update Executives <span class="right modal-action modal-close" onclick="resetFields('update')"><span
                            class="material-icons" >close</span></span></h6>
                <div class="row">
                    <div class="col m12 s12">
                        <div class="table-inside">
                        	<div class="center-align">
					        	 <span id="mainError" class="my-error my-error"></span>
					        </div>
					            <table id="updateExecutivesTable" class="mdl-data-table mobile_responsible_table" >
					                <thead>
					                    <tr>
					                        <th>Work</th>
											<th>Responsible Executives </th>
					                    </tr>
					                </thead>
					                
								    <tbody id="updateExecutivesBody">									               
					                    <tr id="UexecutiveRow0">
					                        <td data-head="Work" class="input-field">
					                        
					                         <input type="hidden"  id="work_id_fk_old" name="work_id_fk_old" />
					                             <select class="searchable validate-dropdown work2" name="work_id_fks" id="u_work_id_fk0" >
					                                	<option value="" >Select</option>  
					                                	<c:forEach var="obj" items="${workDetails}" >
					                                		  <option value= "${obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
					                                	
					                                	</c:forEach>																	         
					                              </select>
					                              <span id="u_workError0" class="my-error"></span>
					                        </td>
					                        <td data-head="Responsible Executives" class="input-field h-auto">
					                         <input type="hidden"  id="u_executive_user_id_fk0" name="executive_user_id_fks" />
					                            <select class="searchable validate-dropdown" name="executive_user_id_fk" onchange="getRowsCountU('0');"
					                                id="u_responsible_executives_id_fks0" multiple="multiple" >
					                                <option >Select</option>	
					                                <c:forEach var="obj" items="${usersDetails}" >
					                                		  <option value= "${obj.user_id}">${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>
					                                	
					                                	</c:forEach>														 			 		                             	
					                            </select>
					                            <span id="u_executiveError0" class="my-error"></span>
					                        </td>
					                    </tr>
					             
					                </tbody>								                

								</table>
                                                  
					        </div>
					        
					       
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button type="button" style="width: 100%;" onclick="updateRrExecutives()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/contract-executives"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>

    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="contract_sub_location" id="id" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.fixedColumns.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>

    <script>
        $(document).ready(function () {
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
            $('#remarks').characterCounter();
            // adding table data into table start
          /*   
            // adding table data into table ends */

            var table = $('#contract_executives_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging: false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
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
            });
        });

        function addRrExecutives(){
         	 //if(validator.form()){   
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			var flag = validateRRAdd();
	        	if(flag){
	        		document.getElementById("addRrExecutivesForm").submit();	
	        	}else{
	        		 $('#addUpdateModal').modal('open');
	        		 $('#mainErrorAdd').text('select atleast one row!');
	        		 $(".page-loader").hide();
	        	}
     			
          //}
       }
        
        function getResponsibleUsers(work_id)
        {
           $("#responsible_executives_id_fks0 option:not(:first)").remove();
  		  var myParams2 = { work_id_fk: work_id };
		  $.ajax({
              url: "<%=request.getContextPath()%>/ajax/getWorkWiseContractResponsibleUsers",
              data: myParams2, cache: false,
              success: function (data) {
                  if (data.length > 0) {
                      $.each(data, function (i, val) {
              	    	$("#responsible_executives_id_fks0").append('<option value="' + val.user_id + '">' + $.trim(val.designation) +'-'+ $.trim(val.user_name) + '</option>');
                      });
                  }
              }
          });        	
        }
        
        function updateRrExecutives(){
        	 //if(validator1.form()){ 
    			$(".page-loader").show();
    			$("#onlyUpdateModal").modal();
    			var flag = validateRRUpdate();
    			if(flag){
    				document.getElementById("updateRrExecutivesForm").submit();	
    			}else{
	        		 $('#onlyUpdateModal').modal('open');
	        		 $('#mainError').text('select atleast one row!');
	        		 $(".page-loader").hide();
	        	}
    			
         //}
      }
      
    /*    var validator = $('#addRrExecutivesForm').validate({
        ignore: ":hidden:not(.validate-dropdown)",
      	 rules: {
      		 	"contract_location_fk": {
  			 		  required: true
      			 },"contract_sub_location": {
  			 		  required: true
      			 }
  			},messages: {
  		 		 "contract_location_fk": {
  			 		  required: 'Required'
  			 	 }, "contract_sub_location": {
  			 		  required: 'Required'
  			 	 }
  	        },econtractorPlacement:function(econtractor, element){
  	        	 if(element.attr("id") == "contract_location_fk" ){
  				     document.getElementById("contract_location_fkEcontractor").innerHTML="";
  			 	     econtractor.appendTo('#contract_location_fkEcontractor');
  			   }else if(element.attr("id") == "contract_sub_location" ){
  				     document.getElementById("contract_sub_locationEcontractor").innerHTML="";
  			 	     econtractor.appendTo('#contract_sub_locationEcontractor');
  			   }
  	        }
       });
      
       var validator1 = $('#updateRrExecutivesForm').validate({
           ignore: ":hidden:not(.validate-dropdown)",
         	 rules: {
         		 	"contract_location_fk_new": {
     			 		  required: true
         			 },"value_new": {
     			 		  required: true
         			 }
     			},messages: {
     		 		 "contract_location_fk_new": {
     			 		  required: 'Required'
     			 	 }, "value_new": {
     			 		  required: 'Required'
     			 	 }
     	        },econtractorPlacement:function(econtractor, element){
     	        	 if(element.attr("id") == "contract_location_fk_new" ){
     				     document.getElementById("contract_location_fk_newEcontractor").innerHTML="";
     			 	     econtractor.appendTo('#contract_location_fk_newEcontractor');
     			   }else if(element.attr("id") == "value_new" ){
     				     document.getElementById("value_newEcontractor").innerHTML="";
     			 	     econtractor.appendTo('#value_newEcontractor');
     			   }
     	        }
          }); */
       
       $('input').change(function(){
  	           if ($(this).val() != ""){
  	               $(this).valid();
  	           }
  	  });

      $('select').change(function(){
          if ($(this).val() != ""){
              $(this).valid();
          }
      });
      function updateRow(no) {

          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #work_id_fk'+no).val($.trim('')).focus(); 
          var work_id_fk = $('#contract_work_id_fk'+no).val();
         $('#work_id_fk_old').val(work_id_fk);
          var user_id = $('#contract_user_id'+no).val();
          //$('#onlyUpdateModal #value_new').val($.trim(rr_sub_location)).focus();
          $('#onlyUpdateModal #u_work_id_fk0').val($.trim(work_id_fk)).focus(); 
          $('#onlyUpdateModal #u_responsible_executives_id_fks'+no).val($.trim(user_id)).focus(); 
          $('#u_work_id_fk0 option[value="'+ work_id_fk +'"]').attr("selected","selected");
          if(user_id != ''){
        	  $("#u_responsible_executives_id_fks0 option:selected").removeAttr("selected");
        	  $('#u_executive_user_id_fk0').val(user_id);
        	  var len = $("#updateExecutivesBody tr").length
       /*  	  if(len > 1){
        		  var arr = []; 
        		  for(var i = 0; i < len; i++){
        			  arr[i] =  $('#u_executive_user_id_fk'+i).val();
        			  var array = arr[i].split(",").map(item => item.trim());
                      for(var j=0; j< array.length; j++){ 
                    	    $('#u_responsible_executives_id_fks'+i+' option[value='+array[j]+']').attr("selected", "selected");
                    	}
        		  }
        		  
        	  }else{ */
        		  var array = user_id.split(",").map(item => item.trim());
                  $('#u_responsible_executives_id_fks0').val(array).trigger("change");
        	  //}
        	 
          }
          $('.searchable').select2();
      }

      function addAExecutiveRow(){
    	   	 var rowNo = $("#addExecutivesBody tr:last-of-type").attr('id').split('executiveRow')[1];
    			 var rNo = Number(rowNo)+1;
    			 var no = 0;    			 
    					 
    			 var html = '<tr id="AexecutiveRow'+rNo+'">'
    				   +'<td data-head="Department" class="input-field">'+
    				 /*   <c:choose>
    			        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">   */                               
    			         '<select  class="searchable validate-dropdown work" name="work_id_fks" id="work_id_fk'+rNo+'"> '
    	         		 +'<option value="">Select</option>'+
    	                 <c:forEach var="obj" items="${workDetails}">
    				 		'<option  value="${obj.work_id_fk }" >${obj.work_id_fk } - ${obj.work_short_name }</option>'+
    	                 </c:forEach>
		    	         '</select>'+
		    	        /*  </c:when>
		    	         <c:otherwise>
		    	         '<select  class="searchable validate-dropdown" name="work_id_fks" id="work_id_fk'+rNo+'" >'+
		    	         		 '<option value="">Select</option>'+
		    	                 <c:forEach var="obj" items="${usersDetails}">
		    				 		'<option  value="${obj.work_id_fk }">${obj.work_id_fk } - ${obj.contract_short_name }</option>'
		    	                 </c:forEach>
		    	         +'</select>' +                               
		    	         </c:otherwise>
		    	         </c:choose>			   */ 
		    	         '</td>'
    				   +'<td data-head="Responsible Executives" class="input-field h-auto">'+    				   
	    		   /*  <c:choose>
	    	         <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">  */ 
	    	         ' <input type="hidden"  id="executive_user_id_fk'+rNo+'" name="executive_user_id_fks" />'+ 
	    	          '<select  class="searchable validate-dropdown"  name="executive_user_id_fk" id="responsible_executives_id_fks'+rNo+'"  multiple="multiple"  onchange="getRowsCount('+rNo+');">'+
	    	          '<option >Select</option>'+
	    	          <c:forEach var="obj" items="${usersDetails}">
	    			  			 '<option value="${obj.user_id }"> ${obj.designation} - ${obj.user_name}</option>'+
	    	          </c:forEach> 
	    	        '</select>'+
	    	        /*  </c:when>
	    	         <c:otherwise>
	    	         ' <input type="hidden"  id="responsible_executives_id_fk'+rNo+'" name="executive_user_id_fks" />'+
	    			 '<select  class="searchable validate-dropdown"  name="executive_user_id_fks" id="executive_user_id_fks'+rNo+'" multiple="multiple" >'+
	    	                                          
	    	          '<option value="" disabled="disabled">Select</option>'+
	    	           <c:forEach var="obj" items="${usersDetails}">
	    			  			 '<option value="${obj.user_id }" > ${obj.designation} - ${obj.user_name}</option>'+
	    	          </c:forEach> 
	    	         '</select>'+  
	    	         
	    	          </c:otherwise>
	    	         </c:choose>   			 */   
    				   
    				   		'</td>'
    				   +'<td class="mobile_btn_close"> <a onclick="deleteAExecutiveRow('+rNo+');deleteRowsCount('+rNo+')" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
    				   +'</tr>';
    			
    				 $('#addExecutivesBody').append(html); 
    				// $("#structureContractRowNo").val(rNo);
    				 $('.searchable:not(.units)').select2();  				 

    	   }    
    	        	    
   	    function deleteAExecutiveRow(rowNo){
   	    	$("#AexecutiveRow"+rowNo).remove();
   	    }
      function updateUExecutiveRow(){
    	   	 var rowNo = $("#updateExecutivesBody tr:last-of-type").attr('id').split('executiveRow')[1];
    			 var rNo = Number(rowNo)+1;
    			 var no = 0;    			 
    					 
    			 var html = '<tr id="UexecutiveRow'+rNo+'">'
    				   +'<td data-head="Department" class="input-field">'+
    				   /* <c:choose>
    			        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">   */                               
    			         '<select  class="searchable validate-dropdown work2" name="work_id_fks" id="u_work_id_fk'+rNo+'"> '
    	         		 +'<option value="">Select</option>'+
    	                 <c:forEach var="obj" items="${workDetails}">
    				 		'<option  value="${obj.work_id_fk }" >${obj.work_id_fk } - ${obj.work_short_name }</option>'+
    	                 </c:forEach>
		    	         '</select>'+
		    	        /*  </c:when>
		    	         <c:otherwise>
		    	         '<select  class="searchable validate-dropdown" name="work_id_fks" id="u_work_id_fk'+rNo+'" >'+
		    	         		 '<option value="">Select</option>'+
		    	                 <c:forEach var="obj" items="${usersDetails}">
		    				 		'<option  value="${obj.work_id_fk }">${obj.designation } - ${obj.user_name }</option>'
		    	                 </c:forEach>
		    	         +'</select>' +                               
		    	         </c:otherwise>
		    	         </c:choose>	 */		   
		    	         '</td>'
    				   +'<td data-head="Responsible Executives" class="input-field h-auto">'+    				   
	    		   /*  <c:choose>
	    	         <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">   
	    	         ' <input type="hidden"  id="u_responsible_executives_id_fk'+rNo+'" name="executive_user_id_fks" />'+ */
	    	         ' <input type="hidden"  id="u_executive_user_id_fk'+rNo+'" name="executive_user_id_fks" />'+ 
	    	          '<select  class="searchable validate-dropdown"  name="executive_user_id_fk" id="u_responsible_executives_id_fks'+rNo+'"  multiple="multiple" onchange="getRowsCountU('+rNo+');">'+
	    	          '<option >Select</option>'+
	    	          <c:forEach var="obj" items="${usersDetails}">
	    			  			 '<option value="${obj.user_id }"> ${obj.designation} - ${obj.user_name}</option>'+
	    	          </c:forEach> 
	    	        '</select>'+
	    	        /*   </c:when>
	    	        <c:otherwise>
	    	         ' <input type="hidden"  id="u_responsible_executives_id_fk'+rNo+'" name="executive_user_id_fks" />'+
	    			 '<select  class="searchable validate-dropdown"  name="executive_user_id_fks" id="u_responsible_executives_id_fks'+rNo+'" multiple="multiple" >'+
	    	                                          
	    	          '<option value="" disabled="disabled">Select</option>'+
	    	           <c:forEach var="obj" items="${usersDetails}">
	    			  			 '<option value="${obj.user_id }" > ${obj.designation} - ${obj.user_name}</option>'+
	    	          </c:forEach> 
	    	         '</select>'+  
	    	         
	    	          </c:otherwise>
	    	         </c:choose>  */  			   
    				   
    				   		'</td>'
    				   +'<td class="mobile_btn_close"> <a onclick="deleteUExecutiveRow('+rNo+');deleteRowsCountU('+rNo+')" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
    				   +'</tr>';
    			
    				 $('#updateExecutivesBody').append(html); 
    				// $("#structureContractRowNo").val(rNo);
    				 $('.searchable:not(.units)').select2();  				 

    	   }    
    	        	    
   	    function deleteUExecutiveRow(rowNo){
   	    	$("#UexecutiveRow"+rowNo).remove();
   	    }
   	    
   	 function getRowsCount(count){
  	   var len = $("#addExecutivesBody tr").length
  	   var vals = [];
  	   $('#addExecutivesBody #responsible_executives_id_fks'+count).each(function(i,val){vals.push($(this).val());   });
  	   vals = vals.join(',_,');
  	   vals = vals.replace(/,_,\s*$/, '');
  	   $('#executive_user_id_fk'+count).val(vals);
     }
     function deleteRowsCount(count){
  	   var len = $("#addExecutivesBody tr").length
  	   var vals = [];
  	   $('#addExecutivesBody #responsible_executives_id_fks'+count).each(function(i,val){vals.push($(this).val());   });
  	   vals = vals.join(',_,');
  	   vals = vals.replace(/,_,\s*$/, '');
  	   $('#executive_user_id_fk'+count).val(vals);
  	   
     }
     function getRowsCountU(count){
    	   var len = $("#updateExecutivesBody tr").length
    	   var vals = [];
    	   $('#updateExecutivesBody #u_responsible_executives_id_fks'+count).each(function(i,val){vals.push($(this).val());   });
    	   vals = vals.join(',');
    	   vals = vals.replace(/,\s*$/, '');
    	   $('#u_executive_user_id_fk'+count).val(vals);
       }
       function deleteRowsCountU(count){
    	   var len = $("#updateExecutivesBody tr").length
    	   var vals = [];
    	   $('#updateExecutivesBody #u_responsible_executives_id_fks'+count).each(function(i,val){vals.push($(this).val());   });
    	   vals = vals.join(',');
    	   vals = vals.replace(/,\s*$/, '');
    	   $('#u_executive_user_id_fk'+count).val(vals);
    	   
       }
       function validateRRUpdate(){
			var flag = false;
			$(".work2").each(function(){
				var idNo = (this.id).replace('u_work_id_fk','');
				var u_work_id_fk = $("#u_work_id_fk"+idNo).val();
				var u_responsible_executives_id_fks = $("#u_responsible_executives_id_fks"+idNo).val();
				if($.trim(u_work_id_fk) != "" && $.trim(u_responsible_executives_id_fks) != ""){
					flag = true;
					return flag;
					}
				
			});
			
			return flag;
		}
       function validateRRAdd(){
			var flag = false;
			$(".work").each(function(){
				var idNo = (this.id).replace('work_id_fk','');
				var u_work_id_fk = $("#work_id_fk"+idNo).val();
				var u_responsible_executives_id_fks = $("#responsible_executives_id_fks"+idNo).val();
				if($.trim(u_work_id_fk) != "" && $.trim(u_responsible_executives_id_fks) != ""){
					
					flag = true;
					return flag;
				}
			});
			
			return flag;
		}
       function resetFields(flag){
    	   var naming='';
    	   if(flag=='add'){
    		    naming='addExecutivesBody';
				$('#'+naming+' [name="work_id_fks"]').each(function(i,val){
					$(val).val('');
				});
				$('#'+naming+' [name="executive_user_id_fk"]').each(function(i,val){
					$(val).val(null);
				});
				$('.searchable:not(.units)').select2(); 
    	   }    	   
    	   if(flag=='update'){
    		   naming='updateExecutivesBody';
    		   $('#'+naming+' [name="work_id_fks"]').each(function(i,val){
					$(val).val('');
			   });
    		   $('#u_work_id_fk0').val($('#work_id_fk_old').val());
    		   $('#'+naming+' tr:not("#UexecutiveRow0")').remove();
    		   /* $('#'+naming+' [name="executive_user_id_fk"]').each(function(i,val){
					$(val).val(null);
				}); 
    		   var executive=$('#u_executive_user_id_fk0').val().split(',');
    		    $(executive).each(function(i,val){
	    		   var a=$('#u_responsible_executives_id_fks0 option[value="'+val+'"]').attr('selected','selected');    
    		   }); */
    	   }   
    	   $('.searchable:not(.units)').select2();  

       }
    </script>

    </body>


</html>