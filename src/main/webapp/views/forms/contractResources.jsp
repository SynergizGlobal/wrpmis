<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 
    	<c:if test="${action eq 'edit'}">Edit Contract Resources - Update Forms - PMIS</c:if>
		<c:if test="${action eq 'add'}">Add Contract Resources - Update Forms - PMIS</c:if>
	</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
     <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css">
    <style>
	   .no-mar .row {
	       margin-bottom: 0;
	   }
	
	   .fixed-width {
	       width: 100%;
	       margin-left: auto !important;
	       margin-right: auto !important;
	       margin-bottom: 20px;
	   }
	
	   .fixed-width .table-inside {
	       width: 100%;
	       overflow: auto;
	   }
	
	   td {
	       position: relative;
	   }
	
	   #resourceFormTableBody .select2-container {
	       max-width: 125px;
	   }
	
	   .select2-container--default .select2-selection--single {
	       background-color: transparent;
	   }
	
	   @media screen and (max-width:769px) {
	       #resourceFormTableBody .select2-container {
	           max-width: inherit;
	       }
	   }
	</style>
</head>
<body>

	<!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header  ends-->
	
	<div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6> Add / Edit Contract Resources</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container no-mar">
                        <form action="#">
                            <div class="row ">
                                <div class="col s6 m4 input-field offset-m2">
                                    <p class="searchable_label">Project</p>
                                    <select class="searchable" id="project_fk" name="project_fk">
                                        <option value="">Select</option>
                                        <option value="1">issue 1</option>
                                    </select>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select class="searchable" id="work_fk" name="work_fk">
                                        <option value="">Select</option>
                                        <option value="1">issue 1</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row ">
                                <div class="col s6 m4 input-field offset-m2">
                                    <p class="searchable_label">Contract <span class="required">*</span></p>
                                    <select class="searchable" id="contract_fk" name="contract_fk">
                                        <option value="">Select</option>
                                        <option value="1">issue 1</option>
                                    </select>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Structure <span class="required"></span></p>
                                    <select class="searchable" id="structure_fk" name="structure_fk">
                                        <option value="">Select</option>
                                        <option value="1">issue 1</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m4 input-field offset-m4">
                                    <input id="date" type="text" class="validate datepicker">
                                    <label for="date">Deployment Date</label>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                            </div>

                            <div class="row" style="margin-bottom:20px;">
                                <div class="col s12 m8 offset-m2">
                                    <div class="row fixed-width">
                                        <h5 class="center-align">Resource Details</h5>
                                        <div class="table-inside">
                                            <table id="resourceFormTable"
                                                class="mdl-data-table mobile_responsible_table">
                                                <thead>
                                                    <tr>
                                                        <th>Resource Type</th>
                                                        <th>Resource Name</th>
                                                        <th>Quantity </th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="resourceFormTableBody">
                                                    <tr>
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_type0"
                                                                name="resource_types">
                                                                <option value="1" selected>Equipment</option>
                                                                <option value="2">Manpower</option>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_name0" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names"
                                                                value="Excavator">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field"><input id="qty0"
                                                                type="number" min="1" step="1" class="validate"
                                                                placeholder="Qty" value="Qty"></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_type1"
                                                                name="resource_types">
                                                                <option value="1" selected>Equipment</option>
                                                                <option value="2">Manpower</option>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_name1" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names"
                                                                value="JCB">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field"><input id="qty1"
                                                                type="number" min="1" step="1" class="validate"
                                                                placeholder="Qty" value="Qty"></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_type2"
                                                                name="resource_types">
                                                                <option value="1" selected>Equipment</option>
                                                                <option value="2">Manpower</option>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_name2" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names"
                                                                value="Dumper">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field"><input id="qty2"
                                                                type="number" min="1" step="1" class="validate"
                                                                placeholder="Qty" value="Qty"></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_type3"
                                                                name="resource_types">
                                                                <option value="1" selected>Equipment</option>
                                                                <option value="2">Manpower</option>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_name3" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names"
                                                                value="Grader">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field"><input id="qty3"
                                                                type="number" min="1" step="1" class="validate"
                                                                placeholder="Qty" value="Qty"></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_type4"
                                                                name="resource_types">
                                                                <option value="1" selected>Equipment</option>
                                                                <option value="2">Manpower</option>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_name4" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names"
                                                                value="Water Tanker Bowser">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field"><input id="qty4"
                                                                type="number" min="1" step="1" class="validate"
                                                                placeholder="Qty" value="Qty"></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_type5"
                                                                name="resource_types">
                                                                <option value="1">Equipment</option>
                                                                <option value="2" selected>Manpower</option>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_name5" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names"
                                                                value="Semi Skilled">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field"><input id="qty5"
                                                                type="number" min="1" step="1" class="validate"
                                                                placeholder="Qty" value="Qty"></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_type6"
                                                                name="resource_types">
                                                                <option value="1">Equipment</option>
                                                                <option value="2" selected>Manpower</option>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_name6" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names"
                                                                value="Highly Skilled">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field"><input id="qty6"
                                                                type="number" min="1" step="1" class="validate"
                                                                placeholder="Qty" value="Qty"></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td data-head="Resource Type" class="input-field">
                                                            <select class="searchable" id="resource_type7"
                                                                name="resource_types">
                                                                <option value="1">Equipment</option>
                                                                <option value="2" selected>Manpower</option>
                                                            </select>
                                                        </td>
                                                        <td data-head="Resource Name" class="input-field">
                                                            <input id="resource_name7" type="text" class="validate"
                                                                placeholder="Resource Name" name="resource_names"
                                                                value="UnSkilled">
                                                        </td>
                                                        <td data-head="Quantity" class="input-field"><input id="qty7"
                                                                type="number" min="1" step="1" class="validate"
                                                                placeholder="Qty" value="Qty"></td>
                                                        <td class="mobile_btn_close">
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <table class="mdl-data-table">
                                                <tbody>
                                                    <tr>
                                                        <td colspan="4"><a href="javascript:void(0);"
                                                                onclick="addResource();"
                                                                class="btn tab waves-effect waves-light bg-m t-c "> <i
                                                                    class="fa fa-plus"></i></a></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <input type="hidden" id="rowNo" name="rowNo" value="0" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div></div>
                            <div class="row no-mar">
                                <div class="col s6 m4 offset-m2 center-align mt-brdr">
                                    <div class="m-1">
                                        <button type="submit" class="btn waves-effect waves-light bg-m">Add / Edit
                                        </button>
                                    </div>
                                </div>
                                <div class="col s6 m4 center-align mt-brdr">
                                    <div class="m-1">
                                        <button type="button" class="btn waves-effect waves-light bg-s">Cancel
                                        </button>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>
    
    <!-- footer  starts-->
     <jsp:include page="../layout/footer.jsp"></jsp:include>
    <!-- footer  ends-->
    
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script>
        let date_pickers = document.querySelectorAll('.datepicker');
        $.each(date_pickers, function () {
            var dt = this.value.split(/[^0-9]/);
            this.value = "";
            var options = { format: 'dd-mm-yyyy', autoClose: true };
            if (dt.length > 1) {
                options.setDefaultDate = true,
                    options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
            }
            M.Datepicker.init(this, options);
        });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();           
        });

        function addResource() {
            // var rowNo = $("#rowNo").val();
            var rowNo = $("#resourceFormTableBody tr").length;
            var rNo = Number(rowNo) + 1;
            var html = '<tr id="resourceFormTableRow' + rNo + '"><td data-head="Resource Type" class="input-field"><select class="searchable" id="resource_type' + rNo + '" name="resource_types">' +
			            '<option value="1" selected>Equipment</option> <option value="2">Manpower</option>' +
			            '</select></td><td data-head="Resource Name" class="input-field"> <input id="resource_name' + rNo + '" type="text" class="validate" placeholder="Resource Name" name="resource_names"value="Excavator">' +
			            '</td> <td data-head="Quantity" class="input-field"><input id="qty' + rNo + '" type="number" min="1" step="1" class="validate" placeholder="Qty" value="Qty"></td> <td> <a href="#" class="btn waves-effect waves-light red t-c ">' +
			            '<i class="fa fa-close"></i></a></td></tr>';
            $("#resourceFormTableBody").append(html);
            $("#rowNo").val(rNo);
            $('.searchable').select2();
        }
    </script>

</body>
</html>