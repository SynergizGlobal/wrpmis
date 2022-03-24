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
    	main {
		  transition: transform .7s ease-in-out;
		}
		.move-to-left {
		  transform: translateX(-400px);
		}
		.move-to-left-partly {
		  transform: translateX(-30px);
		  -webkit-animation: slide-left 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
	        animation: slide-left 0.5s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
		}
		@-webkit-keyframes slide-left {
  0% {
    -webkit-transform: translateX(0);
            transform: translateX(0);
  }
  25% {
  	width: 90%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  50% {
  	width: 85%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  75% {
  	width: 80%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  100% {
    -webkit-transform: translateX(-30px);
            transform: translateX(-30px);
            width: 75%;
  }
}
@keyframes slide-left {
  0% {
    -webkit-transform: translateX(0);
            transform: translateX(0);
  }
  15% {
  	width: 98%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  20% {
  	width: 97%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
   25% {
  	width: 95%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  30% {
  	width: 93%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
   35% {
  	width: 91%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  40% {
  	width: 89%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  45% {
  	width: 87%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  50% {
  	width: 85%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  
  70% {
  	width: 80%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  75% {
  	width: 79%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  80% {
  	width: 78%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
    85% {
  	width: 77%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  90% {
  	width: 76%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  95% {
  	width: 75.5%;
  	 transition: width 2s, transform .7s ease-in-out;
  }
  100% {
    -webkit-transform: translateX(-30px);
            transform: translateX(-30px);
            width: 75%;
  }
}
		.sidebar {
		  height: 50%;
		  width: 320px;
		  position: absolute;
		  top: 18.5rem;
		  z-index: 1;
		  right: -400px;
		  background-color: #FFF;
		  box-shadow: 0 1rem 5rem rgba(0, 0, 0, 0.4);
		  transition: transform .7s ease-in-out;
		}
		.container-liner{
		  margin-left:1rem;
		}
		.sidebar-tab {
		  height: 100%;
		  width: 2rem;
		  position: fixed;
		  top: 3rem;
		  z-index: 1;
		  right: 0;
		  background-color: #FFF;
		  box-shadow: 0 1rem 5rem rgba(0, 0, 0, 0.5);
		  transition: transform .7s ease-in-out;
		}


        #divGrpHeaders label {
            font-size: 1rem;
            color: #000;
            margin: 0 1rem;
        }
		#divGrpHeaders label> span{
			font-size:1.15rem;
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

        .list_holder {
            margin-top: 1em;
        }

        .heading
         {
            text-align: center;
            text-transform: capitalize;
            margin-bottom: 3rem;
            font-weight: 500;
        }
        
/*         select {
     display: block; 
} */

        ul {
            list-style-type: none;
        }

        .optionItem,
        .optionGroup {
            padding: .1rem 1rem;
        }

        .optionItem.selected,
        .optionGroup.selected {
            /* //background-color: rgba(0, 0, 0, .3); */
            color: #fff;
            background-color: #337ab7;
        }

        .optionGroup>span {
            display: block;
            font-size:1.1rem;
        }

        .optionGroup>span+ul:empty {
            display: none;
        }
        .optionGroup >ul{
        	border-left:1px solid #bbb;
        }        
		.multiList li.optionGroup:nth-child(even) {
		    background-color: #fafafa;
		}
        .list_holder .multiList {
            padding: .15rem;
            list-style-type: none;
            height: 40vh;
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

        .r-75rem {
            right: 0.75rem;
        }

        .bg-m.pos-abs {
            top: .52rem;
        }

        .mb-2px {
            margin-bottom: 2px;
        }
        .disp-inb{
        	display:inline-block;        	
        }
        span.disp-inb > .select2.select2-container{
        	min-width:13rem;
        }
        span.disp-inb .selection .select2-selection__rendered{
        	color:#333 !important;
        	padding-left:.5rem !important;
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
			span.disp-inb{
	        	display:block;   
	        	margin-bottom:.5rem;     	
	        }
	        span.disp-inb >.select2.select2-container{
	        	width:9rem !important;
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
       div.mt-brdr {
	   		margin-top: 5px;
	   }
        
    </style>
</head>

 <body>

     <!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
     <!-- header ends  -->

        <div class="">
		<form action="<%=request.getContextPath() %>/generate-custom-report" id="customReportForm" name="customReportForm" method="post" target="_blank">

        <div class="card ">
            <div class="card-content">
                <div class="center-align">
                    <span class="card-title headbg">
                        <div class="center-align p-2 bg-m">
                            <h5>
                            	<span class="disp-inb">
                            		<select id="module_name_fk" class="searchable" name="module_name_fk" onChange="getModuleColumns();">
                                       <option value="">Select</option>
                                        <c:forEach var="obj" items="${modulesList }">
                                     	    <option value= "${ obj.module_name_fk}" <c:if test="${formDetails.module_name_fk eq obj.module_name_fk}">selected</c:if>>${obj.module_name_fk}</option>
                                         </c:forEach>
                                    </select>
                            	</span>
                            	Custom Reports
                            </h5>
                        </div>
                    </span>
                </div>
                <!-- form start-->
                  <%--   <div class="container">
                        <div class="row no-mar">
                            <div class="col s12 m8 input-field offset-m2">
                                <div class="dropdown-form">
                                    <p class="searchable_label">Select Module</p>
                                    <select id="module_name_fk" class="searchable" name="module_name_fk" onChange="getModuleColumns();">
                                       <option value="">All</option>
                                        <c:forEach var="obj" items="${modulesList }">
                                     	    <option value= "${ obj.module_name_fk}" <c:if test="${formDetails.module_name_fk eq obj.module_name_fk}">selected</c:if>>${obj.module_name_fk}</option>
                                         </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div> --%>
                    <div class="row no-mar">
                        <div class="col l12 m12 s12">
                            <div class="dropdown-options pdt0">

                                <div class="show-hide" id="Contracts" style="display: none;">
                                    <div class="md-sl row no-mar" id="filtersAppend">
                                       
                                    </div>
                                    <div class="row" style="margin: 10px 0;">
                                        <div class="col s12 center-align">
                                            <button type="button"
                                                class="btn bg-m waves-effect waves-light t-c clear-filters"
                                                onclick="clearFilters()">Clear
                                                Filters</button>
                                        </div>
                                    </div>

                                    <div class="row no-mar">
                                        <div class="center-align" id="divGrpHeaders">
                                        </div>
                                    </div>
									<div class="row">
										<div class="col s6 m4 l4 offset-l8" style="text-align:right;">
										<button class="btn waves-effect waves-light bg-s" onClick="saveLayout();" type="button">Save</button>
										<a class="btn waves-effect waves-light bg-s" onclick="toggleSidebar()">Layout</a>
										</div>
									</div>
                                    <main class="text-center" id="main">
                                    	<div class="row no-mar">

                                        <div class="list_holder">
                                            <div class="col m5 s12">
                                                <div class="left-div card mb-2px">
                                                    <div class="card-content py-none">
                                                        <div class="row no-mar">
                                                            <div class="input-field col s1">
                                                                <p class="pt-1r">
                                                                    <label> <input type="checkbox"
                                                                            class="filled-in selectAll"
                                                                            data-id="left" />
                                                                        <span></span>
                                                                    </label>
                                                                </p>
                                                            </div>
                                                            <div class="input-field col s11">
                                                                <input placeholder="Search Available Fields"
                                                                    data-id="left" type="text" class="validate search">
                                                                <button type="button" class="btn bg-m pos-abs r-75rem">
                                                                    <i class="fa fa-search"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <ul name="left-box" id="left-box" class="multiList">
                                                    

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
                                                            <div class="input-field col s1">
                                                                <p class="pt-1r">
                                                                    <label> <input type="checkbox"
                                                                            class="filled-in selectAll"
                                                                            data-id="right" />
                                                                        <span></span>
                                                                    </label>
                                                                </p>
                                                            </div>
                                                            <div class="input-field col s11">
                                                                <input placeholder="Search Selected Fields"
                                                                    data-id="right" type="text" class="validate search">
                                                                <button type="button" class="btn bg-m pos-abs r-75rem">
                                                                    <i class="fa fa-search"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <ul name="right-box" id="right-box" class="multiList">
                                                   

                                                </ul>
                                            </div>

                                        </div>

                                    </div>
                                    </main>
                                    
                                    <div class="sidebar" id="sidebar">
									  <div class="container-liner">
									    <div class="card">
	                                             	<table>
	                                             		<thead>
		                                             		<tr>
			                                             		<td><b>Layout</b></td>
			                                             		<td><b>Action</b></td>
		                                             		</tr>
	                                             		</thead>
	                                             		<tbody>
		                                             		<tr>
			                                             		<td>Layout 1</td>
			                                             		<td>
			                                             			<button type="button" class="btn r-75rem">
		                                                                    <i class="fa fa-spinner" aria-hidden="true"></i>
		                                                             </button>
			                                             			<button type="button" class="btn r-75rem" onClick="removeLayout();">
		                                                                    <i class="fa fa-remove"></i>
		                                                             </button>
		                                                         </td>
	                                                         </tr>
	                                                         <tr>
		                                                         <td>Layout 2</td>
		                                                         <td>
		                                                         	<button type="button" class="btn r-75rem">
		                                                                    <i class="fa fa-spinner" aria-hidden="true"></i>
		                                                             </button>
		                                                         	<button type="button" class="btn r-75rem">
		                                                                    <i class="fa fa-remove"></i>
		                                                             </button>
		                                                         </td>
	                                                          </tr>
                                                          </tbody>
	                                             	</table>
	                                            </div> 
									  </div>
									</div>

                                </div>
                            </div>

                        </div>
                    </div>
					<input type="hidden" name="grpHead" id="grpHead">
					<input type="hidden" name="grpHeadColumns" id="grpHeadColumns">
					<input type="hidden" name="filterColumns" id="filterColumns">
					
                    <div class="container container-no-margin">
                        <div class="row">
                            <div class="col s6 mt-brdr ">
                                <div class="center-align m-1">
                                    <button type="button" class="btn waves-effect waves-light bg-m" onClick="generateReport();">Generate</button>
                                </div>
                            </div>
                            <div class="col s6 mt-brdr ">
                                <div class="center-align m-1">
                                    <button type="button" class="btn waves-effect waves-light bg-s" onClick="getModuleColumns();">Reset</button>
                                </div>
                            </div>
                        </div>
                    </div>
                
            </div>
        </div>
     </form></div>
   
    
     <!-- footer  -->
     <jsp:include page="../layout/footer.jsp"></jsp:include>

     <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
     <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
     <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
     <script src="/pmis/resources/js/dataTables.material.min.js"></script>
     <script src="/pmis/resources/js/select2.min.js"></script>

     <script>       
     /* Unsure how to center this without JS :/*/
     $(function(){
       $("#sidebar-tab-text").width($("#sidebar").height());
     });
     $( window ).resize(function() {
       $("#sidebar-tab-text").width($("#sidebar").height());
     });
     /* End of unsure centering */

     //The only necessary piece of code lol
     function toggleSidebar(){
       $("#sidebar").toggleClass("move-to-left");
       $("#sidebar-tab").toggleClass("move-to-left");
       $("main").toggleClass("move-to-left-partly");
       $(".arrow").toggleClass("active");
     }

     /* Totally unncessary swyping gestures*/
     var gestureZone = document;
     var touchstartX = 0, touchstartY = 0;
     gestureZone.addEventListener('touchstart', function(event) {
         touchstartX = event.changedTouches[0].screenX;
         touchstartY = event.changedTouches[0].screenY;
     }, false);

     gestureZone.addEventListener('touchend', function(event) {
         var touchendX = event.changedTouches[0].screenX;
         var touchendY = event.changedTouches[0].screenY;
         handleGesure(touchendX, touchendY);
     }, false); 

     function handleGesure(touchendX, touchendY) {
         var acceptableYTravel = (touchendY-touchstartY) < 30 && (touchendY-touchstartY) > -30;
       
         var swiped = 'swiped: ';
         if (touchendX < touchstartX && acceptableYTravel) {
             openSidebar();
             console.log(swiped + 'left!');
         }
         if (touchendX > touchstartX  && acceptableYTravel) {
             closeSidebar();
             console.log(swiped + 'right!');
         }
     }
     function openSidebar(){
       $("#sidebar").addClass("move-to-left");
       $("main").addClass("move-to-left-partly");
       $("#sidebar-tab").addClass("move-to-left");
     }
     function closeSidebar(){
       $("#sidebar").removeClass("move-to-left");
       $("main").removeClass("move-to-left-partly");
       $("#sidebar-tab").removeClass("move-to-left");
     }
     /* End of totally unncessary swyping gestures*/
     
     
     
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
        var grpHeadArray=new Array();
        var grpColumnArray=new Array();
        
        function generateReport()
        {
        	grpHeadArray=[];
        	grpColumnArray=[];
        	

        	
        	$('#right-box li').not('.hidden').map(function() {
    			var id=$(this).attr("id");
    			if(id!=undefined)
   				{
   					var grpName=$("#"+id+" span").html();
   					grpName=replaceAll(grpName,"<b>","");
   					grpName=replaceAll(grpName,"</b>","");
   					grpHeadArray.push(grpName);
   				}

        	})   
        	
        	$('#right-box li ul li').not('.hidden').map(function() {
    			var id=$(this).parent().parent().attr('id');
    			if(id!=undefined)
   				{
   					var grpName=$("#"+id+" span").html();
   					grpName=replaceAll(grpName,"<b>","");
   					grpName=replaceAll(grpName,"</b>","");
  					
	    			var ids=$(this).html();
	    			ids=ids.replace(/<\/?span[^>]*>/g,"");
	    			ids=ids.replace("*","");
    			 	 if(/\s+$/.test(ids)) 
    				 {
    			 		ids=ids.slice(0, ids.length-1) ;
    				 }
	    			grpColumnArray.push(grpName+"-"+ids);
   				}
    			
        	}) 
        	
        	var concatfilter="";
        	
        	$("#grpHead").val(grpHeadArray);
        	$("#grpHeadColumns").val(grpColumnArray);
        	
        	
        	if($("#filtersAppend").html()!="")
    		{        	
	       		if($("#project_id").val()!="")
	       		{
	       			concatfilter=concatfilter+"a.project_id='"+$("#project_id").val()+"' and ";
	       		}
	       		
	       		if($("#work_id").val()!="")
	       		{
	       			concatfilter=concatfilter+"a.work_id='"+$("#work_id").val()+"' and ";
	       		}  
	       		
	       		if($("#contract_id").val()!="")
	       		{
	       			concatfilter=concatfilter+"a.contract_id='"+$("#contract_id").val()+"' and ";
	       		}
	       		
	       		if($("#hod_user_id_fk").val()!="")
	       		{
	       			concatfilter=concatfilter+"a.hod='"+$("#hod_user_id_fk").val()+"' and ";
	       		}
	       		
	       		if($("#dy_hod_user_id_fk").val()!="")
	       		{
	       			concatfilter=concatfilter+"a.dyhod='"+$("#dy_hod_user_id_fk").val()+"' and ";
	       		}     
	       		
	       		if($("#department").val()!="")
	       		{
	       			concatfilter=concatfilter+"a.department='"+$("#department").val()+"' and ";
	       		} 
	       		concatfilter=concatfilter.slice(0, concatfilter.length-4) ;
	       		
	        	$("#filterColumns").val(concatfilter);
    		}

       		
        	$('#customReportForm').submit();
        	

        }
        function replaceAll(str, find, replace) {
        	  return str.replace(new RegExp(find, 'g'), replace);
        }
        
        function removeLayout()
        {
	       	 if (confirm("Are you sure you want to delete layout")) 
	    	 {  
	    	 }        	
        }
        
        function saveLayout()
        {
        	var layoutfieldslength=$('#right-box li ul').find('li:not(.hidden)').length;
        	if(layoutfieldslength==0)
       		{
        		$("#layoutErrorMsg").html("Please select atleast one field into right box!.");
       		}
        	else
       		{
        		$("#layoutErrorMsg").html("");
       		}
        	
        	grpHeadArray=[];
        	grpColumnArray=[];
        	

        	
        	$('#right-box li').not('.hidden').map(function() {
    			var id=$(this).attr("id");
    			if(id!=undefined)
   				{
   					var grpName=$("#"+id+" span").html();
   					grpName=replaceAll(grpName,"<b>","");
   					grpName=replaceAll(grpName,"</b>","");
   					grpHeadArray.push(grpName);
   				}

        	})   
        	
        	$('#right-box li ul li').not('.hidden').map(function() {
    			var id=$(this).parent().parent().attr('id');
    			if(id!=undefined)
   				{
   					var grpName=$("#"+id+" span").html();
   					grpName=replaceAll(grpName,"<b>","");
   					grpName=replaceAll(grpName,"</b>","");
  					
	    			var ids=$(this).html();
	    			ids=ids.replace(/<\/?span[^>]*>/g,"");
	    			ids=ids.replace("*","");
    			 	 if(/\s+$/.test(ids)) 
    				 {
    			 		ids=ids.slice(0, ids.length-1) ;
    				 }
	    			grpColumnArray.push(grpName+"-"+ids);
   				}
    			
        	}) 
        	
        	var concatfilter="";
        	
        	$("#grpHead").val(grpHeadArray);
        	$("#grpHeadColumns").val(grpColumnArray); 
        	
        	
        }
        
        
        
        function resetWorksAndProjectsDropdowns(contract){
    		$(".page-loader-1").show();
   		
    		
    		var projectId = '';
    		var workId = ''
    			var contract_id_fk = $("#contract_id_fk").val();
    			if(contract_id_fk == ""){
    				contract_id_fk = contract;
    			}
    			if($.trim(contract_id_fk) != ''){        			
    				workId = $("#contract_id_fk").find('option:selected').attr("name");
    				if(workId == null){
    					workId =  contract_id_fk.substring(0, 6); 
    				}
    				projectId = workId.substring(0, 3);    
    				//workId = workId.substring(3, work_id.length);
    				$("#project_id").val(projectId);
    				$("#contract_id_fk").val(contract_id_fk);
    				
    			
    				$("#project_id").select2();
    				$("#contract_id_fk").select2();
    			}
    			
    			if ($.trim(projectId) != "") {
    				$("#work_id_fk option:not(:first)").remove();
    	        var myParams = { project_id_fk: projectId };
    	        $.ajax({
    	            url: "<%=request.getContextPath()%>/ajax/getAcivitiesBulkUpdateWorksList",
    	            data: myParams, cache: false,async: false,
    	            success: function (data) {
    	                if (data.length > 0) {
    	                    $.each(data, function (i, val) {
    	                        var workName = '';
    	                        if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) }
    	                        if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
    	                            $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' +  $.trim(workName) + '</option>');
    	                        } else {
    	                            $("#work_id_fk").append('<option value="' + val.work_id + '">' +  $.trim(workName) + '</option>');
    	                        }
    	                    });
    	                }
    	                $('.searchable').select2();
    	                $(".page-loader-1").hide();
    	            }
    	        });
    	        $('.searchable').select2();
    	    }
    			
    	}        
        
        function addSelected() {
        	var lhArray=new Array();
            $('#left-box .optionItem.selected:not(.hidden)').each(function () {
                let item = this;
                let pos = $(item).data('pos');
                let parent = $(item).data('parent');
                $('#right-box [data-parent="' + parent + '"][data-pos="' + pos + '"]').removeClass('hidden');
                lhArray.push(parent);
                if (parent != 'noGrp') {
                    $('#right-box #' + parent).removeClass('hidden');
                    if ($(item).parent().find('li:not(.hidden)').length == 1) {
                        $('#left-box #' + $(item).data('parent')).addClass('hidden');
                    }
                }
                $(item).toggleClass('hidden selected');
            });
            
			$('#right-box li ul li').map(function() {
        		
   				var id=$(this).html();
   				if(id.indexOf('<span class="required">*</span>')!=-1)
   				{
   					var index=$(this).attr('data-pos');
   					var indexParent=$(this).attr('data-parent');
   					
   					$('#right-box [data-parent="' + indexParent + '"][data-pos="' + index + '"]').removeClass('hidden');
   					$('#right-box [data-parent="' + indexParent + '"][data-pos="' + index + '"]').prop('disabled',true);
   					if(lhArray.indexOf(indexParent)!=-1)
   						{
   							$('#left-box [data-parent="' + indexParent + '"][data-pos="' + index + '"]').addClass('hidden');
   						}
   				}
        	});            
            
            if ($('#left-box .optionItem:not(".hidden")').length == 0) {
                if ($('.selectAll[data-id="left"]').prop('checked')) {
                    $('.selectAll[data-id="left"]').prop('checked', false)
                }
            }
        	$('#right-box li').not('.hidden').map(function() {
        		
    			var id=$(this).attr("id");
    			if(id!=undefined)
   				{
   					var grpName=$("#"+id+" span").html();
   					grpName=replaceAll(grpName,"<b>","");
   					grpName=replaceAll(grpName,"</b>","");
   					var rlp=id.replace("grp","");
   					$("#chkGrp"+rlp).prop("disabled",true);
   				}
    			
        	})             
            
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
        	$('#left-box li').not('.hidden').map(function() {
    			var id=$(this).attr("id");
    			if(id!=undefined)
   				{
   					var grpName=$("#"+id+" span").html();
   					grpName=replaceAll(grpName,"<b>","");
   					grpName=replaceAll(grpName,"</b>","");
   					var rlp=id.replace("grp","");
   					$("#chkGrp"+rlp).prop("disabled",false);

   				}

        	})
        	
        	var moveArray=new Array();
        	
        	$('#right-box li').not('.hidden').map(function() {
    			var id=$(this).attr("id");
    			if(id!=undefined)
   				{
    				moveArray.push(id);
   				}

        	})  

   					
   					
 					for(var t=0;t<moveArray.length;t++)
						{
						  	var indexStr=moveArray[t];
				        	var cnt=0;

	   						
						  	$('#right-box li[id="'+indexStr+'"] ul li').not('.hidden').map(function() {
				        		
				   				var id=$(this).html();
				   				if(id.indexOf('<span class="required">*</span>')==-1)
				   				{
				   					var index=$(this).attr('data-pos');
				   					var indexParent=$(this).attr('data-parent');
				   		        	cnt++;


				   				}
				        	}); 
						  	
						  	if(cnt==0)
					  		{
							  	$('#right-box li[id="'+indexStr+'"] ul li').not('.hidden').map(function() {
					        		
					   				var id=$(this).html();
					   				if(id.indexOf('<span class="required">*</span>')!=-1)
					   				{
					   					var index=$(this).attr('data-pos');
					   					var indexParent=$(this).attr('data-parent');					   					
					   					
					   					$('#right-box [data-parent="' + indexParent + '"][data-pos="' + index + '"]').addClass('hidden');
					   					$('#right-box [data-parent="' + indexParent + '"][data-pos="' + index + '"]').prop('disabled',false);
					   					$('#left-box [data-parent="' + indexParent + '"][data-pos="' + index + '"]').removeClass('hidden');

					   				}
					        	});	
							  	$('#right-box li[id="'+indexStr+'"]').addClass('hidden');
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
         $('#module_name_fk').change(function () {
            $('.show-hide').hide();
            if ($('#module_name_fk').val()) {
                //$('#' + $('#module_name_fk').val()).show();
            	$('.show-hide').show();
            }
        });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $(".searchable").each(function (index, val) {
                $(this).select2({ placeholder: $(this).attr('placeholder') });
            });
            
        });
        
        
        function getModuleFilters()
        {
        	$("#filtersAppend").html("");
        	var myParams={module_name_fk:$("#module_name_fk").val()};
        	$.ajax({
                url: "<%=request.getContextPath()%>/ajax/getModuleFilters",
                data:myParams,
                type: 'GET',
                async: false,
                dataType: 'json',
                success: function (data) 
                {
	               	 var html="";
	
	            	 for (var i = 0; i < data.length; i++) 
	            	 {
	            		 html+= '<div class="col s6 m4 l2">'+
	                         '<p class="searchable_label">'+data[i]["filter_name"]+'</p>'+
	                         '<select class="searchable validate-dropdown" id="'+data[i]["option_id"]+'" name="'+data[i]["option_value"]+'">'+
	                             '<option value="">Select</option>';
	                             
	                             var myParams={module_name_fk:$("#module_name_fk").val(),table_name:data[i]["table_name"]};
	                         	$.ajax({
	                                 url: "<%=request.getContextPath()%>/ajax/getModuleFiltersOptionValues",
	                                 data:myParams,
	                                 type: 'GET',
	                                 async: false,
	                                 dataType: 'json',
	                                 success: function (response) 
	                                 {
			                             for(var k=0;k<response.length;k++)
			                           	 {
			                            	 html +='<option value="'+response[k]["option_id"]+'">'+response[k]["option_value"]+'</option>';
			                           	 }
	                                 }
	                         	});
	                    html+='</select></div>';
	            	 }	
	            	 
	            	 $("#filtersAppend").append(html);
	            	 
	            	  $('.searchable').select2();
                }
        	});
        }
        
        
        function getModuleColumns()
        {
        	getModuleFilters();
        	
	       	 $("#divGrpHeaders").html("");
	    	 $(".multiList").html("");
    	 
        	var myParams={module_name_fk:$("#module_name_fk").val()};
        	 $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getModuleGroups",
                 data:myParams,
                 type: 'GET',
                 async: false,
                 dataType: 'json',
                 success: function (data) 
                 {
                	 var html="";
                	 var appendHeader="<p>";
                	 
                	 var hdnHtml="";
                	 var leftArray=new Array();
                	 var rightArray=new Array();

                	 for (i = 0; i < data.length; i++) 
                	 {
                		 leftArray=[];
                		 rightArray=[];
                		 var concat=i+1;
                		 appendHeader +='<label id="'+data[i]["name"]+'"><input type="checkbox" id="chkGrp'+concat+'" class="filled-in" checked onchange="showColumns('+concat+');"/><span>'+data[i]["name"]+'</span></label>';
	                	 html +='<li class="optionGroup " id="grp'+concat+'"><span data-parent="grp'+concat+'" isSelected="false"><b>'+data[i]["name"]+'</b></span>';
	                	 html +="<ul id='columnDiv"+concat+"'>";
	                	 
	                	 hdnHtml +='<li class="optionGroup hidden" id="grp'+concat+'"><span data-parent="grp'+concat+'" isSelected="false"><b>'+data[i]["name"]+'</b></span>';
	                	 hdnHtml +="<ul id='columnDiv"+concat+"'>";
	                	 
	                 	 var Params={module_name_fk:$("#module_name_fk").val(),table_name:data[i]["table_name"]};
		               	 $.ajax({
		                        url: "<%=request.getContextPath()%>/ajax/getModuleGroupColumns",
		                        data:Params,
		                        type: 'GET',
		                        async: false,
		                        dataType: 'json',
		                        success: function (response) 
		                        {	
		                        	
				                	 for (i1 = 0; i1 < response.length; i1++) 
				                	 {
				                		 	if(response[i1]["column_name"].indexOf("</span>")!=-1)
				                			 {
				                		 		leftArray.push(response[i1]["column_name"]);
				                			 }
				                	 }
				                	 for (i1 = 0; i1 < response.length; i1++) 
				                	 {
				                		 	if(response[i1]["column_name"].indexOf("</span>")==-1)
				                			 {
				                		 		leftArray.push(response[i1]["column_name"]);
				                			 }
				                	 }				                	 
				                	 
				                	 
		                        	
				                	 for (i1 = 0; i1 < response.length; i1++) 
				                	 {
				                		 var concatStr=i1+1;
				                		 html +='<li class="optionItem " data-parent="grp'+concat+'" data-pos="'+concatStr+'">'+leftArray[i1]+'</li>';
				                		 hdnHtml +='<li class="optionItem hidden" data-parent="grp'+concat+'" data-pos="'+concatStr+'">'+leftArray[i1]+'</li>';
				                	 }
		                        }
		               	 });
	                	 
	                	 
	                	 
	                	 html +="</ul>";
	                	 html +="</li>";
	                	 hdnHtml +="</ul></li>";
                	 }
                	 appendHeader +="</p>";
                	 $("#divGrpHeaders").append(appendHeader);
                	 $("#left-box").append(html);
                	 $("#right-box").append(hdnHtml);
                 }
             });        	
        }
        
        function showColumns(groupname)
        {
        		if($("#chkGrp"+groupname).is(":checked"))
        		{
        			$("#grp"+groupname).show();
        			$("#columnDiv"+groupname).show();
        		}
        		else
       			{
        			$("#grp"+groupname).hide();
        			$("#columnDiv"+groupname).hide();
       			}
        		
        }

        function clearFilter() {
            $('#project_id_fk,#work_id_fk,#contract_id_fk,#hod_id_fk,#dyhod_id_fk,#department_id_fk').val('');
            $('.searchable').select2();
        }
    </script>
 </body>

</html>