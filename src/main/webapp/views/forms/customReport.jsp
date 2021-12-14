<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <title>Custom Reports</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">

    <style>
        label {
            font-size: 1rem;
            color: #000;
            margin: 0 1rem;
        }

        .select2 {
            width: 100% !important;
        }

        .pdt0 {
            padding-top: 0 !important;
        }

        .filled-in.selectAll {
            display: contents;
        }

        .l9px {
            left: -9px !important;
        }

        .list_holder {
            margin-top: 1em;
        }

        .heading {
            text-align: center;
            text-transform: capitalize;
            margin-bottom: 3rem;
            font-weight: 500;
        }

        ul {
            list-style-type: none;
        }

        .optionItem,
        .optionGroup {
            padding: .5rem 1rem;
        }

        .optionItem.selected,
        .optionGroup.selected {
            /* //background-color: rgba(0, 0, 0, .3); */
            color: #fff;
            background-color: #337ab7;
        }

        .optionGroup>span {
            display: block;
        }

        .optionGroup>span+ul:empty {
            display: none;
        }

        .list_holder .multiList {
            padding: .15rem;
            list-style-type: none;
            height: 50vh;
            margin: 0 auto;
            overflow: auto;
            background-color: #fff;
            box-shadow: 0 2px 2px 0 rgb(0 0 0/ 14%), 0 3px 1px -2px rgb(0 0 0/ 12%),
                0 1px 5px 0 rgb(0 0 0/ 20%)
        }

        .list_holder__btns {
            display: flex;
            flex-direction: column;
            justify-content: space-evenly;
            align-items: center;
            /* should change again  */
            gap: 0rem;
            padding: 1rem;
            height: 19em;
        }

        .list_holder__btns>.button {
            padding: .5rem 1rem;
            text-decoration: none;
            border-radius: 5px;
            border: 0;
            margin: 5px;
            background-color: #fff;
            width: 4rem;
            font-weight: 600;
            text-align: center;
            box-shadow: 2px 2px 5px #cecece;
        }

        .list_holder__btns>.button:before {
            content: attr(data-icon);
            vertical-align: super;
        }

        .py-none {
            padding-top: 0 !important;
            padding-bottom: 0 !important;
        }

        .pt-1r {
            padding-top: 1rem;
        }

        .pos-abs {
            position: absolute;
        }

        .r-0 {
            right: 0;
        }

        .bg-m.pos-abs {
            top: .52rem;
        }

        .mb-2px {
            margin-bottom: 2px;
        }
		@media only screen and (max-width:768px) {
			.list_holder__btns{
				height:auto;
				padding:2rem;
			}
		}

        @media only screen and (max-width:576px) {
            .button {
                transform: rotate(90deg);
            }

            .list_holder__btns {
                display: inline-block;
                text-align: center;
                width: 100%;
            }
        }

        /*box styles end*/
        [type="checkbox"]:not (:checked),
        [type="checkbox"]:checked {
            opacity: 1;
            position: relative;
            pointer-events: auto;
        }

        #select,
        #contract,
        #risk,
        #project {
            display: none;
        }

        .mlr15px {
            margin: 0 15px;
        }

        .dropdown-form,
        .dropdown-options {
            padding: 15px;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        .fixed-width {
            width: 100%;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

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

        .fw-8p {
            width: 8%;
        }

        #dashboard_form_table td>.btn {
            padding: 0 12px;
        }

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .required {
            color: red;
            font-size: 1.3rem;
            vertical-align: middle;
        }

        .w20em {
            width: 20em;
        }
    </style>
</head>

 <body>

     <!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
     <!-- header ends  -->

        <div class="">

        <div class="card ">
            <div class="card-content">
                <div class="center-align">
                    <span class="card-title headbg">
                        <div class="center-align p-2 bg-m">
                            <h5>Custom Reports</h5>
                        </div>
                    </span>
                </div>
                <!-- form start-->
                <form action="#" id="module-select">
                    <div class="container">
                        <div class="row no-mar">
                            <div class="col s12 m8 input-field offset-m2">
                                <div class="dropdown-form">
                                    <p class="searchable_label">Select Module</p>
                                    <select id="module_id_fk" class="searchable" name="module_id_fk">
                                        <option value="">Select</option>
                                        <option value="contract">Contract</option>
                                        <option value="risk">Risk</option>
                                        <option value="project">Project</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row no-mar">
                        <div class="col l12 m12 s12">
                            <div class="dropdown-options pdt0">

                                <div class="show-hide" id="contract" style="display: none;">
                                    <div class="md-sl row">
                                        <div class="col s6 m4 l2">
                                            <p class="searchable_label">Project</p>
                                            <select class="searchable" name="project_id_fk">
                                                <option value="option-1">CR FOB</option>
                                                <option value="option-2">Option-2</option>
                                                <option value="option-3">Option-3</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2">
                                            <p class="searchable_label">Work</p>
                                            <select class="searchable" name="work_id_fk" id="work_id_fk">
                                                <option value="option-1">CR FOB</option>
                                                <option value="option-2">Option-2</option>
                                                <option value="option-3">Option-3</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2">
                                            <p class="searchable_label">Contract</p>
                                            <select class="searchable" name="contract_id_fk" id="contract_id_fk">
                                                <option value="option-1">CR FOB lot-I</option>
                                                <option value="option-2">Option-2</option>
                                                <option value="option-3">Option-3</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2">
                                            <p class="searchable_label">HOD</p>
                                            <select class="searchable" name="hod_id_fk" id="hod_id_fk">
                                                <option value="option-1">CPM-I</option>
                                                <option value="option-2">Option-2</option>
                                                <option value="option-3">Option-3</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2">
                                            <p class="searchable_label">Dy HOD</p>
                                            <select class="searchable" name="dyhod_id_fk" id="dyhod_id_fk">
                                                <option value="option-1">Dy CPM-I</option>
                                                <option value="option-2">Option-2</option>
                                                <option value="option-3">Option-3</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2">
                                            <p class="searchable_label">Department</p>
                                            <select class="searchable" name="department_id_fk" id="department_id_fk">
                                                <option value="option-1">Engineering</option>
                                                <option value="option-2">Option-2</option>
                                                <option value="option-3">Option-3</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col s12 center-align">
                                            <button type="button"
                                                class="btn bg-m waves-effect waves-light t-c clear-filters"
                                                onclick="clearFilters()">Clear
                                                Filters</button>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="center-align">
                                            <p>
                                                <label>
                                                    <input type="checkbox" class="filled-in" checked />
                                                    <span>Contract Details</span>
                                                </label>
                                                <label>
                                                    <input type="checkbox" class="filled-in" checked />
                                                    <span>Insurance</span>
                                                </label>
                                                <label>
                                                    <input type="checkbox" class="filled-in" checked />
                                                    <span>Bank Guarantee</span>
                                                </label>
                                                <label>
                                                    <input type="checkbox" class="filled-in" checked />
                                                    <span>Milestones</span>
                                                </label>
                                                <label>
                                                    <input type="checkbox" class="filled-in" checked />
                                                    <span>Revision Details</span>
                                                </label>
                                            </p>
                                        </div>
                                    </div>

                                    <div class="row no-mar">

                                        <div class="list_holder">
                                            <div class="col m5 s12">
                                                <div class="left-div card mb-2px">
                                                    <div class="card-content py-none">
                                                        <div class="row no-mar">
                                                            <div class="input-field col s1 l9px">
                                                                <p class="pt-1r">
                                                                    <label> <input type="checkbox"
                                                                            class="filled-in selectAll"
                                                                            data-id="left" />
                                                                        <span></span>
                                                                    </label>
                                                                </p>
                                                            </div>
                                                            <div class="input-field col s10">
                                                                <input placeholder="Search Available Fields"
                                                                    data-id="left" type="text" class="validate search">
                                                                <button type="button" class="btn bg-m pos-abs r-0">
                                                                    <i class="fa fa-search"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <ul name="left-box" id="left-box" class="multiList">
                                                    <!-- <li class="optionItem " data-parent="noGrp" data-pos="1">+1</li>
 <li class="optionItem " data-parent="noGrp" data-pos="2">+2</li> -->
                                                    <li class="optionGroup " id="grp1"><span data-parent="grp1"
                                                            isSelected="false"><b>Contract
                                                                Details</b></span>
                                                        <ul>
                                                            <li class="optionItem " data-parent="grp1" data-pos="1">
                                                                Contract
                                                                ID</li>
                                                            <li class="optionItem " data-parent="grp1" data-pos="2">
                                                                Contract
                                                                Shortname</li>
                                                            <li class="optionItem " data-parent="grp1" data-pos="3">HOD
                                                            </li>
                                                            <li class="optionItem " data-parent="grp1" data-pos="4">Dy
                                                                HOD</li>
                                                        </ul>
                                                    </li>
                                                    <!-- <li class="optionItem " data-parent="noGrp" data-pos="3">+3</li>
 <li class="optionItem " data-parent="noGrp" data-pos="4">+4</li> -->
                                                    <li class="optionGroup " id="grp2"><span data-parent="grp2"
                                                            isSelected="false"><b>Bank
                                                                Guarentee</b></span>
                                                        <ul>
                                                            <li class="optionItem " data-parent="grp2" data-pos="1">Bank
                                                                Guarentee Value</li>
                                                            <li class="optionItem " data-parent="grp2" data-pos="2">Bank
                                                                Guarentee Expiry date</li>
                                                            <li class="optionItem " data-parent="grp2" data-pos="3">Bank
                                                                Guarentee Number</li>
                                                        </ul>
                                                    </li>
                                                    <li class="optionGroup hidden" id="grp3"><span data-parent="grp3"
                                                            isSelected="false"><b>Insurance</b></span>
                                                        <ul>
                                                            <li class="optionItem hidden" data-parent="grp3"
                                                                data-pos="1">
                                                                Insurance Value</li>
                                                            <li class="optionItem hidden" data-parent="grp3"
                                                                data-pos="2">
                                                                Insurance Number</li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col m2 s12">
                                                <div class="list_holder__btns">
                                                    <button type="button" onclick="addSelected()" class="select button"
                                                        data-icon=">"></button>
                                                    <button type="button" onclick="delSelected()" class="remove button"
                                                        data-icon="<"></button>
                                                </div>
                                            </div>
                                            <div class="col m5 s12">
                                                <div class="right-div card mb-2px">
                                                    <div class="card-content py-none">
                                                        <div class="row no-mar">
                                                            <div class="input-field col s1 l9px">
                                                                <p class="pt-1r">
                                                                    <label> <input type="checkbox"
                                                                            class="filled-in selectAll"
                                                                            data-id="right" />
                                                                        <span></span>
                                                                    </label>
                                                                </p>
                                                            </div>
                                                            <div class="input-field col s10">
                                                                <input placeholder="Search Selected Fields"
                                                                    data-id="right" type="text" class="validate search">
                                                                <button type="button" class="btn bg-m pos-abs r-0">
                                                                    <i class="fa fa-search"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <ul name="right-box" id="right-box" class="multiList">
                                                    <!-- <li class="optionItem hidden" data-parent="noGrp" data-pos="1">+1</li>
 <li class="optionItem hidden" data-parent="noGrp" data-pos="2">+2</li> -->
                                                    <li class="optionGroup hidden" id="grp1"><span data-parent="grp1"
                                                            isSelected="false"><b>Contract
                                                                Details</b></span>
                                                        <ul>
                                                            <li class="optionItem hidden" data-parent="grp1"
                                                                data-pos="1">
                                                                Contract ID</li>
                                                            <li class="optionItem hidden" data-parent="grp1"
                                                                data-pos="2">
                                                                Contract Shortname</li>
                                                            <li class="optionItem hidden" data-parent="grp1"
                                                                data-pos="3">HOD
                                                            </li>
                                                            <li class="optionItem hidden" data-parent="grp1"
                                                                data-pos="4">Dy
                                                                HOD</li>
                                                        </ul>
                                                    </li>
                                                    <li class="optionGroup hidden" id="grp2"><span data-parent="grp2"
                                                            isSelected="false"><b>Bank
                                                                Guarentee</b></span>
                                                        <ul>
                                                            <li class="optionItem hidden" data-parent="grp2"
                                                                data-pos="1">Bank
                                                                Guarentee Value</li>
                                                            <li class="optionItem hidden" data-parent="grp2"
                                                                data-pos="2">Bank
                                                                Guarentee Expiry date</li>
                                                            <li class="optionItem hidden" data-parent="grp2"
                                                                data-pos="3">Bank
                                                                Guarentee Number</li>
                                                        </ul>
                                                    </li>
                                                    <li class="optionGroup" id="grp3"><span data-parent="grp3"
                                                            isSelected="false"><b>Insurance</b></span>
                                                        <ul>
                                                            <li class="optionItem" data-parent="grp3" data-pos="1">
                                                                Insurance
                                                                Value</li>
                                                            <li class="optionItem" data-parent="grp3" data-pos="2">
                                                                Insurance
                                                                Number</li>
                                                        </ul>
                                                    </li>
                                                    <!-- <li class="optionItem hidden" data-parent="noGrp" data-pos="3">+3</li>
 <li class="optionItem hidden" data-parent="noGrp" data-pos="4">+4</li> -->

                                                </ul>
                                            </div>

                                        </div>

                                    </div>

                                </div>
                            </div>
                            <!-- ========================category-2================================ -->

                            <div class="show-hide" id="risk" style="display: none;">
                                <p>risk div shown</p>
                            </div>

                            <div class="show-hide" id="project" style="display: none;">
                                <p>project div shown</p>
                            </div>
                        </div>
                    </div>

                    <div class="container container-no-margin">
                        <div class="row">
                            <div class="col s6 mt-brdr ">
                                <div class="center-align m-1">
                                    <button class="btn waves-effect waves-light bg-m">Generate</button>
                                </div>
                            </div>
                            <div class="col s6 mt-brdr ">
                                <div class="center-align m-1">
                                    <button class="btn waves-effect waves-light bg-s">Reset</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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
        // selecting single item
        $(document).on('click', '.multiList .optionItem', function () {
            $(this).toggleClass('selected');
        });

        // selecting group of items
        $(document).on('click', '.multiList .optionGroup > span', function () {
            var teval = !($(this).attr('isSelected') == 'true');
            $(this).attr('isSelected', teval);
            teval ? ($(this).find('+ul >li:not(.hidden)').addClass('selected')) :
                ($(this).find('+ul >li:not(.hidden)').removeClass('selected'));
        });

        // selct all check box functionality
        $('.selectAll').change(function () {
            var idName = $(this).data('id');
            if (this.checked) {
                $('#' + idName + '-box .optionItem:not(.hidden)').addClass('selected');
            }
            else {
                $('#' + idName + '-box .optionItem:not(.hidden)').removeClass('selected');
            }
        });

        function addSelected() {
            $('#left-box .optionItem.selected:not(.hidden)').each(function () {
                let item = this;
                let pos = $(item).data('pos');
                let parent = $(item).data('parent');
                $('#right-box [data-parent="' + parent + '"][data-pos="' + pos + '"]').removeClass('hidden');
                if (parent != 'noGrp') {
                    $('#right-box #' + parent).removeClass('hidden');
                    if ($(item).parent().find('li:not(.hidden)').length == 1) {
                        $('#left-box #' + $(item).data('parent')).addClass('hidden');
                    }
                }
                $(item).toggleClass('hidden selected');
            });
            if ($('#left-box .optionItem:not(".hidden")').length == 0) {
                if ($('.selectAll[data-id="left"]').prop('checked')) {
                    $('.selectAll[data-id="left"]').prop('checked', false)
                }
            }
        }
        function delSelected() {
            $('#right-box .optionItem.selected:not(.hidden)').each(function () {
                let item = this;
                let pos = $(item).data('pos');
                let parent = $(item).data('parent');
                $('#left-box [data-parent="' + parent + '"][data-pos="' + pos + '"]').removeClass('hidden');
                if (parent != 'noGrp') {
                    $('#left-box #' + parent).removeClass('hidden');
                    if ($(item).parent().find('li:not(.hidden)').length == 1) {
                        $('#right-box #' + $(item).data('parent')).addClass('hidden');
                    }
                }
                $(item).toggleClass('hidden selected');
            });
            if ($('#right-box .optionItem:not(".hidden")').length == 0) {
                if ($('.selectAll[data-id="right"]').prop('checked')) {
                    $('.selectAll[data-id="right"]').prop('checked', false)
                }
            }
        }

        $('.search').keyup(function (e) {
            var idName = $(this).data('id');
            var code = e.keyCode || e.which;
            if (code == "9") return;
            if (code == "27") $(this).val(null);
            var $rows = $("#" + idName + "-box").find(" li");
            var val = $.trim($(this).val()).replace(/ +/g, " ").toLowerCase();
            $rows
                .removeClass('hidden')
                .filter(function () {
                    var text = $(this).text().replace(/\s+/g, " ").toLowerCase();
                    return !~text.indexOf(val);
                })
                .addClass('hidden');
        });
        var items = $('.left-box > li').get();
        items.sort(function (a, b) {
            var keyA = $(a).text();
            var keyB = $(b).text();

            if (keyA < keyB) return -1;
            if (keyA > keyB) return 1;
            return 0;
        });
        var ul = $('.alphaList');
        $.each(items, function (i, li) {
            ul.append(li); /* This removes li from the old spot and moves it */
        });

    </script>

    <script>
         $('#module_id_fk').change(function () {
            $('.show-hide').hide();
            if ($('#module_id_fk').val()) {
                $('#' + $('#module_id_fk').val()).show();
            }
        });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $(".searchable").each(function (index, val) {
                $(this).select2({ placeholder: $(this).attr('placeholder') });
            });
        });

        function clearFilter() {
            $('#project_id_fk,#work_id_fk,#contract_id_fk,#hod_id_fk,#dyhod_id_fk,#department_id_fk').val('');
            $('.searchable').select2();
        }
    </script>
 </body>

</html>