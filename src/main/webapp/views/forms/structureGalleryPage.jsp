<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Structure Gallery - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png"> 
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contractor.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
   	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
   	<link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
   	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
   <style>
   		/*  #imageFiles{
   		 	display: flex;
		    flex-wrap: nowrap;
		    justify-content: center;
		    vertical-align: middle;
   		 } */
   		 .br-bl{border: 2px solid #4498d3dd;
   		 	padding:10px !important;
   		 	border-radius: 10px;
   		 }
   		 .bg-m, .bg-m:hover{background-color: #4498d3dd;}
   		 .img-li:hover {
    		box-shadow: 0 14px 28px rgb(0 0 0 / 15%), 0 10px 10px rgb(0 0 0 / 0%);
		}
   		 .img-li{
   		 border: 3px solid #ededed;
	    border-radius: 50px;
	    padding: 20px !important;
	    margin: 10px !important;
	    }
	    .w31{width: 31% !important;}
	    .mt10px{margin-top: 10px;padding: 10px;}
   		 .cw{color:#fff;}
   		 .column-reverse{
            display: flex;
            flex-direction: row-reverse;
            align-items: normal;
        }
        button.accordion {
          width: 100%;
          background-color: transparent;
          border: none;
          outline: none;
          text-align: left;
          padding: 15px 20px;
          font-size: 14px;
          color: #333;
          cursor: pointer;
          transition: background-color 0.2s linear;
      }

      

      button.accordion:hover,
      button.accordion.is-open {
          background-color: #ddd;
      }

      .accordion-content {
          background-color: white;
          border-left: 1px solid whitesmoke;
          border-right: 1px solid whitesmoke;
          padding: 0 20px;
          max-height: 0;
          overflow: hidden;
          transition: max-height 0.2s ease-in-out;
      }
      .gal-image{
        height: 180px;
        max-width: 20em;
      }
      #myImg {
  border-radius: 5px;
  cursor: pointer;
  transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal{top: 10%;
	max-height: 80%;
    width: 50%;

}

/* Modal Content (image) */
.modal-content {
  margin: auto;
  display: block;
  width: 100%;
  max-width: 700px;
  z-index: 1003 !important;
}

/* Caption of Modal Image */
/*#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}*/

/* Add Animation */


/* The Close Button */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}
/*breadcrumbs*/
ul.breadcrumb li {
  display: inline;
  font-size: 18px;
}
ul.breadcrumb li+li:before {
  padding: 8px;
  color: black;
  content: "/\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
@media(max-width: 575px){
.column-reverse{flex-direction: column;}
}
   </style>
</head>

<body>
    <!-- header included -->
    <%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>

	<div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6><span id="work_short_name"></span>Gallery</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar" style="margin-bottom: 0;">
                        </div>
                        <div class="row  column-reverse">
                            <div class="col m2 s12">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m12 input-field br-bl">
                                        <p class="searchable_label"> Month </p>
                                        <select id="created_date" name="created_date" class="searchable" onchange="getGalleryList();">
                                          <c:forEach var="obj" items="${dates}">
												<option value="${obj.valueDate }">${obj.created_date }</option>
											</c:forEach> 
                                        </select>
                                    </div>
                                    <div class="col s12 m12 input-field br-bl">
                                        <p class="searchable_label">Structure</p>
                                        <select id="structure_type_fk" name="structure_type_fk" class="searchable" onchange="getGalleryList();">
                                            <option value="" disabled selected>Select</option>
                                           
                                        </select>
                                    </div>
                                    <div class="col s12 m12 input-field br-bl">
                                        <p class="searchable_label">Structure Id</p>
                                        <select id="structure" name="structure" class="searchable" onchange="getGalleryList();">
                                            <option value="" disabled selected>Select</option>
                                           
                                        </select>
                                    </div>
                                   <!--  <div class="col s12 m12">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                        style="margin-top: 20px;width: 100%;">Clear Filters</button>
                                    </div> -->
                                </div>
                            </div>
                            <div class="col m10 s12">
                                <div class="photos">
                                    <ul id="imageFiles">
                                        <%-- <div class="row">
                                            <li class="col l4 m4 s12">
                                                <center>
                                                    <a href="#modal1" class="modal-trigger">
                                                        <img src="/pmis/resources/images/mrvclogo.png" alt="image" class="gal-image myImages" id="myImg">
                                                    </a>
                                                </center>
                                                <button class="accordion">description: <span class="right">Date: 08/03/2021</span></button>
                                                <div class="accordion-content">
                                                    <p>
                                                      Whether you need a wordpress website, a shopify site, or a custom fullstack application
                                                  </p>
                                              </div>
                                          </li>
                                       </div> --%>

						          </ul>
						      </div>
						<!-- update popup starts -->
						<div>
						
						</div>
						<div id="modal" class="modal">
						    <div class="modal-content">
						        <h4><div class="cw p-2 bg-m modal-title" id="modalhead"></div></h4>
						          <img class="modal-content" id="img01" src="">
						          
						      </div>
						  </div>
				</div>
</div>
</div>
</div>
</div>
</div>
</div>

	<!-- Page Loader -->
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
	
    <!-- footer included -->
   <%--  <jsp:include page="../layout/footer.jsp"></jsp:include> --%>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
   	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>  

	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="contractor_id" id="contractor_id" />
    </form>
     
    <form action="<%=request.getContextPath() %>/export-contractor" name="exportContractorForm" id="exportContractorForm" target="_blank" method="post">	
        <input type="hidden" name="contractor_id" id="exportContractor_id" />
	</form>
	
    <script type="text/javascript">
  
        $(".selectrow1").click(function(){
            $(".row1").prop("checked",$(this).prop("checked"));
        });
        $(".selectrow2").click(function(){
            $(".row2").prop("checked",$(this).prop("checked"));
        });
        $(".selectrow3").click(function(){
            $(".row3").prop("checked",$(this).prop("checked"));
        });
        $(".selectcolumn1").click(function(){
            $(".column1").prop("checked",$(this).prop("checked"));
        });
        $(".selectcolumn2").click(function(){
            $(".column2").prop("checked",$(this).prop("checked"));
        });
    </script>
    <script>
        $(document).ready(function () {
            $('.modal').modal();
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
            getGalleryList();
            
           
        });
        function getGalleryList() {
        	$(".page-loader").show();
        	var created_date = $("#created_date").val();
        	var structure_type_fk = $("#structure_type_fk").val();
        	var structure = $("#structure").val();
        	var work_id = '${work_id}';
        	getMonthList();
        	getStructuresList();
        	getStructureTypeList(); 
     	   $("#imageFiles").text("");
        	$('#imageFiles li').remove();
       	 	var myParams = {created_date : created_date,structure_type_fk : structure_type_fk, structure : structure, work_id : work_id};
               $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getGalleryList",
                   data: myParams, cache: false,async: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {  
                        	   //var path = '${CommonConstants2.STRUCTURE_FILE_SAVING_PATH}';
                                var htmlText = '<div class=""><li class="col l4 m4 s12 img-li w31"><center>'
                                	+'<a href="#modal" class="modal-trigger" >'
                                    +'  <img src="/pmis/STRUCTURE_FILES/'+val.attachment+'" alt="image" onclick="openImage('+i+')" class="gal-image myImages" id="myImg'+i+'">'
                                        +'</a> </center>'
                                    +' <div class="accordion mt10px"  >'+val.name+' <input type="hidden" id="name'+i+'" value="'+val.name+'"/><input type="hidden" id="date'+i+'" value="'+val.created_date+'"/>'
                                +'<span class="right">'+val.created_date+'</span></div></li></div>';
                                var work_short_name = val.work_short_name+" - ";
                                $('#work_short_name').text(work_short_name);
   	                            $("#imageFiles").append(htmlText);
   	                            $("#imageFiles").css({"text-align": "left"});
                           });
                       }else{
                    	    var htmlText = 'No Records Found!'
                    		var work_short_name = '${work.work_short_name}'+" - ";
                            $('#work_short_name').text(work_short_name);
                    		$("#imageFiles").append(htmlText);
                    	    $("#imageFiles").css({"text-align": "center"});
                       }
                       $('.searchable').select2();
                       $(".page-loader").hide();
                   },error: function (jqXHR, exception) {
    	   			      $(".page-loader").hide();
   	   	          	  getErrorMessage(jqXHR, exception);
   	   	     	  }
               });
        }
        
        
        function getMonthList() {
        	$(".page-loader").show();
        	var created_date = $("#created_date").val();
        	var structure_type_fk = $("#structure_type_fk").val();
        	var structure = $("#structure").val();
        	var work_id = '${work_id}';
            if ($.trim(created_date) == "") {
            	$("#created_date option:not(:first)").remove();
            	var myParams = {created_date : created_date,structure_type_fk : structure_type_fk, structure : structure, work_id : work_id};               
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getMonthList",
                    data: myParams, cache: false, 
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                            $("#created_date").append('<option value="' + val.valueDate + '">' + $.trim(val.created_date) +'</option>');
                            });
                        }else{
                        	$("#created_date").append('<option value="">' + 'select' +'</option>');
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
        function getStructuresList() {
        	$(".page-loader").show();
        	var created_date = $("#created_date").val();
        	var structure_type_fk = $("#structure_type_fk").val();
        	var structure = $("#structure").val();
        	var work_id = '${work_id}';
            if ($.trim(structure) == "") {
            	$("#structure option:not(:first)").remove();
            	var myParams = {created_date : created_date,structure_type_fk : structure_type_fk, structure : structure, work_id : work_id};               
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStructureIdList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                            $("#structure").append('<option value="' + val.structure + '">' + $.trim(val.structure) +'</option>');
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
        function getStructureTypeList() {
        	$(".page-loader").show();
        	var created_date = $("#created_date").val();
        	var structure_type_fk = $("#structure_type_fk").val();
        	var structure = $("#structure").val();
        	var work_id = '${work_id}';
            if ($.trim(structure_type_fk) == "") {
            	$("#structure_type_fk option:not(:first)").remove();
            	var myParams = {created_date : created_date,structure_type_fk : structure_type_fk, structure : structure, work_id : work_id};               
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStructuresLists",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                            $("#structure_type_fk").append('<option value="' + val.structure_type_fk + '">' + $.trim(val.structure_type_fk) +'</option>');
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
        
        //accordion script

      
        const accordionBtns = document.querySelectorAll(".accordion");

        accordionBtns.forEach((accordion) => {
          accordion.onclick = function () {
            this.classList.toggle("is-open");

            let content = this.nextElementSibling;
            console.log(content);

            if (content.style.maxHeight) {
      //this is if the accordion is open
      content.style.maxHeight = null;
  } else {
      //if the accordion is currently closed
      content.style.maxHeight = content.scrollHeight + "px";
      console.log(content.style.maxHeight);
  }
};
}); 

        //modal images
        // create references to the modal...
        
function openImage(i){
	$("#modalhead").text('');
	var dd = $('#myImg'+i);
	var imgSrc = $(dd).attr("src");
	$('#modal img').attr('src', imgSrc);  
	var name = $('#name'+i).val();
	var date = $('#date'+i).val();
	var spanText = name+'    '+date;
	$("#modalhead").append(name+' <span class="right">'+date+'</span> ');
	//$('#modal img').width(300); // Units are assumed to be pixels
	//$('#modal img').height(300);
}


</script>
</body>

</html>