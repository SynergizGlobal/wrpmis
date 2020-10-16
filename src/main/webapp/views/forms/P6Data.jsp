<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>P6 Data</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/p6data.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <style>
     .text-primary p a:not(.btn) {
            color: blue;
        }

        #test1 p,
        #test2 p {
            margin-bottom: 0;
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
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> P6 Data </h6>
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
                    <ul class="tabs">
                        <li class="tab col s6"><a href="#baseline">Add Baseline</a></li>
                        <li class="tab col s6"><a class="active" href="#existing">Update Existing</a></li>
                    </ul>
                </div>
                <div class="" id="baseline">
                    <div class="container">
                        <form action="#">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p> <label>Contract ID</label></p>
                                     <select id="contract_id_fk0" name="contract_id_fk"  class="searchable">
                                            <option value="" >Select Contarct</option>
                                            <c:forEach var="obj" items="${contractsList}">
                       						  <option value="${obj.contract_id_fk }" >${obj.contract_id_fk }</option>
                                             </c:forEach>
                                     </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p> <label>FOB ID</label></p>
                                     <select id="fob_id_fk0" name="fob_id_fk"  class="searchable">
                                            <option value="" >Select FOB</option>
                                            <c:forEach var="obj" items="${fobsList}">
                       						  <option value="${obj.fob_id_fk }" >${obj.fob_id_fk }</option>
                                             </c:forEach>
                                     </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                    <input id="data_date1" type="text" name="data_date" class="validate datepicker">
                                    <label for="data_date1"> Data Date</label>
                                    <button type="button" id="data_date1_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4">
                                    <div class="file-field input-field">
                                        <div class="btn btn-outline">
                                            <span>Upload P6 Export File</span>
                                            <input type="file" name="p6_file_path">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col s12 center-align">
                                    <div style="display: inline-block;">
                                        <!-- <input type="submit" value="" > -->
                                        <button type="button" class="btn waves-effect waves-light bg-m f-w-b">
                                            Upload Activities
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="container-no-margin">
                        <div class="row center-align">
                            <div class="col m12 text-primary">
                                <p style="margin-bottom: 20px;"><strong>Note :</strong> Please make sure the uploading
                                    P6 data file will be in
                                    the given format. Click <a href="/pmis/Baseline.xlsx" download>here</a> for
                                    the file format</p>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="" id="existing">
                    <div class="container">
                        <form action="<%=request.getContextPath() %>/upload-p6data" name="p6dataFrom" id="p6dataFrom" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p> <label>Contract ID</label></p>
                                     <select id="contract_id_fk" name="contract_id_fk"  class="searchable" onchange="getFobList(this.value);">
                                            <option value="" >Select Contarct</option>
                                            <c:forEach var="obj" items="${contractsList}">
                       						  <option value="${obj.contract_id_fk }" >${obj.contract_id_fk }</option>
                                             </c:forEach>
                                     </select>
                                     <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                   <p> <label>FOB ID</label></p>
                                   <select id="fob_id_fk" name="fob_id_fk"  class="searchable">
                                            <option value="" >Select FOB</option>
                                   </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                    <input id="data_date2" type="text" name="data_date" class="validate datepicker">
                                    <label for="data_date2"> Data Date</label>
                                    <button type="button" id="data_date2_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4">
                                    <div class="file-field input-field">
                                        <div class="btn btn-outline">
                                            <span>Upload P6 Export File</span>
                                            <input type="file" name="p6dataFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col s12 center-align">
                                    <div style="display: inline-block;">
                                        <a href="javascript:void(0);" onclick="openUploadP6DataModal();"
                                        class="btn waves-effect waves-light bg-m f-w-b">
                                            Upload Activities
                                        </a>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="container-no-margin">
                        <div class="row center-align">
                            <div class="col m12 text-primary">
                                <p style="margin-bottom: 20px;"><strong>Note :</strong> Please make sure the uploading
                                    P6 data file will be in
                                    the given format. Click <a href="/pmis/P6DataFile.xlsx" download>here</a> for
                                    the file format</p>
                            </div>

                        </div>
                    </div>
                </div>


            </div>
        </div>
</div>

        <div class="row">
            <div class="col s12 m12">
                <div class="card ">
                    <div class="card-content ">
                        <span class="card-title">
                            <h6 class="mar-top center-align">P6 DATA HISTORY</h6>
                        </span>
                        <div class="row">
                            <div class="col m12 s12">
                                <table id="p6datatable" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract ID</th>
                                            <th>FOB ID</th>
                                            <th>Data Type</th>
                                            <th>Data Date </th>
                                            <th>Is Active</th>
                                            <th>P6 File</th>
                                            <th>Created By</th>
                                            <th>Created Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>Baseline</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>Update</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
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



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	
	
    <script>
    function  openUploadP6DataModal() {
		$("#p6dataFile").val('');
		$(".page-loader").show();	    		
		document.getElementById("p6dataFrom").submit();
	}
    
    $(document).on('focus', '.datepicker',function(){
        $(this).datepicker({
        	format:'dd-mm-yyyy',
   	    	onSelect: function () {
   	    	   $('.confirmation-btns .datepicker-done').click();
   	    	}
        })
    });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
          /*   $('.datepicker,#data_date1,#data_date2').datepicker(); */
            $('.tabs').tabs();
            $('#data_date1_icon').click(function () {
                event.stopPropagation();
                $('#data_date1').click();
            });
            $('#data_date2_icon').click(function () {
                event.stopPropagation();
                $('#data_date2').click();
            });
            $('#p6datatable').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric'
                    }
                ], 
                //"scrollCollapse": true,
                fixedHeader: true,
               // "sScrollY": 400,
               "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
            
            var contract_id_fk = "${val.contract_id_fk}";
            if ($.trim(contract_id_fk) != '') {
            	getFobList(contract_id_fk);
            }
        });
        
        function getFobList(contract_id_fk) {
        	$(".page-loader").show();
            $("#fob_id_fk option:not(:first)").remove();

            if ($.trim(contract_id_fk) != "") {
                var myParams = { contract_id_fk: contract_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/get-fob-list",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                               
                                var fob_id_fk = "${val.fob_id_fk }";
                                if ($.trim(fob_id_fk) != '' && val.fob_id_fk == $.trim(fob_id_fk)) {
                                    $("#fob_id_fk").append('<option value="' + val.fob_id_fk + '" selected>' + $.trim(val.fob_id_fk) + '</option>');
                                } else {
                                    $("#fob_id_fk").append('<option value="' + val.fob_id_fk + '">' + $.trim(val.fob_id_fk)  + '</option>');
                                }
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
      
        
        
    </script>
</body>
</html>