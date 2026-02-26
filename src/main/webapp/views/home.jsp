<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome to PMIS</title>
	<link rel="stylesheet" type="text/css" href="/wrpmis/resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="/wrpmis/resources/css/navigation.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	
	<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght@400&icon_names=dashboard" rel="stylesheet" />
  <style>
  	body{
  		background-color: #FFEFE2;
  	}
  	.menu-item a{
  		color: #fff;
  	}
  	.homepage-heading{
  		padding: 40px 0;
  	}
  	.homepage-menu{
  		display: flex;
  		flex-wrap: wrap;
  		align-items: center;
  		justify-content: center;
  		gap: 40px;
  		align-items: stretch;
  	}
  	.homepage-menu a{
  		background-color: #fff;
  		color: #000;
  		font-weight: 600;
  		box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  		padding: 15px 40px;
  		border-radius: 10px;
  		flex: 0 0 20%;
  		display: flex;
    	align-items: center;
	    text-align: center;
	    justify-content: center;
	    transition: 0.3s ease-in-out;
  	}
  	.homepage-menu a:hover{
  		background-color: #D58D54;
  		color: #fff;
  	}
  	@media(max-width: 630px){
  		.heading-name h1{
  			margin: 0;
  		}
  		.homepage-menu a{
  			padding: 15px 10px;
  			flex: 0 0 35%;
  		}
  		.homepage-menu{
  			justify-content: space-between;
  			gap: 30px;
  			padding: 0 5px;
  		}
  		.heading-logo img{
  			width: 40px;
  			height: 40px;
  		}
  	}
  </style>

</head>

<body>
<div id="bg">
	<img src="/wrpmis/resources/images/login-background.jpg" alt="">
</div>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>
  
    <div class="container no-mar" >
            <div class="row">
                <div class="input-field col m11 s9">
                    <i class="material-icons prefix right-side">search</i>
                    <input id="projects_search" type="text" class="validate autocomplete" placeholder="Search ...">
                </div>
                <div class="col m1 s3 input-field">
                    <div class="map-btn-holder">
                        <a class="waves-effect waves-light btn modal-trigger" href="#mapmodal" title="click to see map">
                            <!-- <span class="material-icons">map</span> -->
                            <i class="fa fa-globe"></i>
                        </a>
                        <!-- Modal Structure -->
                        <div id="mapmodal" class="modal">
                            <div class="modal-content">
                                <h4 class="modal-header">Map with MUTP 3A Corridors <span
                                        class="right modal-action modal-close"><span
                                            class="material-icons">close</span></span></h4>
                                <img src="/wrpmis/resources/images/final_map.png" alt="Map Image" width="100%">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row no-mar">        
        	<c:forEach var="pObj" items="${projectsInfo}" varStatus="index">
        	<c:if test="${ (index.count-1) % 4 eq 0}"></div><div class="row no-mar"></c:if>
	            <div class="col s12 m3 projects-filter">
	                <div id="project_data_${index.count }" class="card main-clr" >
	                    <div class="card-content">
	                        <span class="card-title center-align">${pObj.project_name }</span>
	                        <div class="line">
	                            <p class="alignleft">Sanctioned Estimated Cost (in Cr) </p>
	                            <p class="aligncenter">:</p>
	                            <p class="alignright"><c:if test="${not empty pObj.sanctioned_estimated_cost }">₹  ${pObj.sanctioned_estimated_cost } Cr </c:if></p>
	                        </div>
	                        <div class="line">
	                            <p class="alignleft">Sanctioned Year</p>
	                            <p class="aligncenter">:</p>
	                            <p class="alignright">${pObj.sanctioned_year_fk }</p>
	                        </div>
	                        <div class="line">
	                            <p class="alignleft">
	                            <c:if test="${pObj.project_status eq 'Closed' }">Completion Cost </c:if>
	                            <c:if test="${pObj.project_status eq 'Open' }">Latest Revised Cost </c:if>
	                            </p>
	                            <p class="aligncenter">:</p>
	                            <p class="alignright">
	                            <c:if test="${pObj.project_status eq 'Closed' }"><c:if test="${not empty pObj.completion_cost }">₹  ${pObj.completion_cost } Cr </c:if> </c:if>
	                            <c:if test="${pObj.project_status eq 'Open' }"><c:if test="${not empty pObj.latest_revised_cost }">₹  ${pObj.latest_revised_cost } Cr </c:if></c:if>
	                            </p>
	                        </div>
	                        <div class="line">
	                            <p class="alignleft">
	                            <c:if test="${pObj.project_status eq 'Closed' }">Completion Year</c:if>
	                            <c:if test="${pObj.project_status eq 'Open' }">Projected Completion Year</c:if>
	                            </p>
	                            <p class="aligncenter">:</p>
	                            <p class="alignright">
	                            <c:if test="${pObj.project_status eq 'Closed' }">${pObj.year_of_completion }</c:if>
	                            <c:if test="${pObj.project_status eq 'Open' }">${pObj.projected_completion_year }</c:if>
	                            </p>
	                        </div>
	                        <div class="line">
                               <%--  <p class="alignleft">Project ID</p>
                                <p class="aligncenter">:</p>
                                <p class="alignright">${pObj.project_id }</p> --%>
                                
                                <p class="alignleft">Physical Progress (%)</p>
                                <p class="aligncenter">:</p>
                                <p class="alignright"> 
	                                <c:if test="${pObj.project_status eq 'Closed' }">100% </c:if>
		                            <c:if test="${pObj.project_status eq 'Open' }">  </c:if>
	                            </p>
                                
		                    </div> 
		                     <div class="line">
                                <%-- <p class="alignleft">Plan Head No</p>
                                <p class="aligncenter">:</p>
                                <p class="alignright">${pObj.plan_head_number }</p> --%>
                                <p class="alignleft">Financial Progress (%)</p>
                                <p class="aligncenter">:</p>
                                <p class="alignright">
                                	<c:if test="${pObj.project_status eq 'Closed' }">100% </c:if>
	                            	<c:if test="${pObj.project_status eq 'Open' }">  </c:if>
                                </p>
                                
		                    </div> 
	                        <div class="button">
		                        <c:if test="${not empty pObj.projectDocs}">
		                              <a class="btn btn-left modal-trigger" href="#documentsModal${index.count }"><i class="fa fa-download" ></i></a> 
		                              <div id="documentsModal${index.count }" class="modal">
								            <div class="modal-content">
								                <h5 class="modal-header">${pObj.project_name } Files<span class="right modal-action modal-close"><span
								                            class="material-icons">close</span></span></h5>
								                <div class="row">
								                    <div class="col m2 hide-on-small"></div>
								                    <table border = "1">
												         <tr>
												            <th>Project file Type</th>
												            <th>File Name</th>
												            <th>Uploaded On</th>
												            <th>Download</th>
												         </tr>
												          <c:forEach var="pDocs" items="${pObj.projectDocs}" >
													         <tr>
													            <td>${pDocs.project_file_type_fk }</td>
													            <td>${pDocs.attachment }</td>
													            <td>${pDocs.created_date }</td>
													            <td style="text-align:center;">
													             <a href="<%=CommonConstants.PROJECT_FILES%>${pDocs.project_id_fk }/${pDocs.attachment }" class="filevalue" download><i class="fa fa-arrow-down"></i></a>
									                            </td>
													         </tr>
												          </c:forEach>	  
												      </table>
								                    <div class="col m2 hide-on-small"></div>
								                </div>
								
								            </div>
								    </div>
								                              
		                        </c:if>         
		                        <c:if test="${not empty pObj.projectGallery and fn:length(pObj.projectGallery) gt 0}">
			                        <a class="btn btn-center modal-trigger" href="#mediamodal${index.count }">Gallery</a>   	                 
			                        <div id="mediamodal${index.count }" class="modal media-modal">
			                            <div class="modal-content">
			                                <h5 class="modal-header">${pObj.project_name } Media <span class="right modal-action modal-close">
			                                	<span  class="material-icons" onclick="pauseVideo${index.count }();">close</span></span>
			                                </h5>
		                                	<div class="row">
		                                		<div class="col s12 m12">
		                                			<div class="carousel carousel-slider">
		                                				<c:forEach var="fObj" items="${pObj.projectGallery }">
			                                				<c:choose>
			                                					<c:when test="${fn:endsWith(fObj.file_name, '.jpeg')==true or fn:endsWith(fObj.file_name, '.jpg')==true or fn:endsWith(fObj.file_name, '.png')==true or fn:endsWith(fObj.file_name, '.gif')==true}">
			                                						<a class="carousel-item" href="javascript:void(0);">
			                                							<img src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}">
			                                						</a>
			                                					</c:when>
			                                					<c:otherwise>
			                                						<a class="carousel-item" href="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}">
			                                							<c:if test="${fn:endsWith(fObj.file_name, '.mp4')==true}">
								                                            <video class="" preload="true" controls loop="loop" >
								                                                <source src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}" type="video/mp4" title="video">
								                                            </video>
							                                            </c:if>
							                                            <c:if test="${fn:endsWith(fObj.file_name, '.avi')==true}">
								                                            <video class="" preload="true" controls loop="loop" >
								                                                <source src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}" type="video/avi" title="video">
								                                            </video>
							                                            </c:if>
							                                            <c:if test="${fn:endsWith(fObj.file_name, '.mkv')==true}">
								                                            <video class="" preload="true" controls loop="loop" >
								                                                <source src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}" type="video/mkv" title="video">
								                                            </video>
							                                            </c:if>
							                                            <c:if test="${fn:endsWith(fObj.file_name, '.webm')==true}">
								                                            <video class="" preload="true" controls loop="loop" >
								                                                <source src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}" type="video/webm" title="video">
								                                            </video>
							                                            </c:if>
							                                        </a>
			                                					</c:otherwise>
			                                				</c:choose>
		                                				</c:forEach>
				                                        <div class="row slider-center"><i id="next${index.count }" class="material-icons">chevron_right</i> <i id="prev${index.count }" class="material-icons">chevron_left</i></div>
													</div>
		                                		</div>
		                                	</div>
			                            </div>
			                        </div> 
			                        <script type="text/javascript">
				                        $('i#prev${index.count }').click(function() {
				                        	$(this).parent().parent().carousel('prev');
				                        });
			
				                        $('i#next${index.count }').click(function() {
				                           // $('.carousel').carousel('next');
				                        	$(this).parent().parent().carousel('next');
				                        });
				                        
				                        function pauseVideo${index.count }(){
				                        	$("video").each(function() {
				                        	    $(this).get(0).pause();
				                        	});
				                        }
			                        </script>
		                        </c:if>
		                        <c:if test="${not empty pObj.benefits }">
		                        	<a class="btn btn-center tooltipped " data-position="top" data-tooltip="${pObj.benefits }">Benefits</a> 
		                        </c:if>
		                             
			                    <a class="btn btn-right" onclick="closeOther('${index.count }')">More</a>
	                        </div>
	                    </div>
	                </div>
	                <div class="row result hidden" id="result${index.count }">
	                    <div class="col s12 m12">
	                        <div class="card sec-clr">
	                            <div class="card-content">
	                                <div class="card-title title-btn"> &nbsp;
	                                    <span class="right">
	                                        <span class="material-icons" onclick="closeDiv('${index.count }')">keyboard_backspace</span>
	                                    </span>
	                                </div>
	                                <div class="row">
	                                	<c:forEach var="wObj" items="${pObj.worksInfo}" varStatus="index">
	                                	<c:if test="${ (index.count-1) % 3 eq 0}"></div><div class="row"></c:if>
		                                    <div class="col s12 m4 projects-filter-work">
		                                        <div class="card main-clr">
		                                            <div class="card-content" style="cursor: pointer;" >
		                                                <span class="card-title center-align">${wObj.work_short_name }</span>
		                                                <div class="line">
		                                                    <p class="alignleft">Sanctioned Estimated Cost (in Cr) </p>
		                                                    <p class="aligncenter">:</p>
		                                                    <p class="alignright"><c:if test="${not empty wObj.sanctioned_estimated_cost }">₹  ${wObj.sanctioned_estimated_cost } Cr </c:if></p>
		                                                </div>
		                                                <div class="line">
		                                                    <p class="alignleft">Sanctioned Year</p>
		                                                    <p class="aligncenter">:</p>
		                                                    <p class="alignright">${wObj.sanctioned_year_fk }</p>
		                                                </div>
		                                                
		                                                <div class="line">
								                            <p class="alignleft">
								                            <c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">Completion Cost </c:if>
								                            <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">Latest Revised Cost </c:if>
								                            </p>
								                            <p class="aligncenter">:</p>
								                            <p class="alignright">
								                            <c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}"><c:if test="${not empty wObj.completion_cost }">₹  ${wObj.completion_cost } Cr </c:if></c:if>
								                            <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}"><c:if test="${not empty wObj.latest_revised_cost }">₹  ${wObj.latest_revised_cost } Cr </c:if></c:if>
								                            </p>
								                        </div>
								                        <div class="line">
								                            <p class="alignleft">
								                            <c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">Completion Year</c:if>
								                            <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">Projected Completion Year</c:if>
								                            </p>
								                            <p class="aligncenter">:</p>
								                            <p class="alignright">
								                            <c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">${wObj.year_of_completion }</c:if>
								                            <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">${wObj.projected_completion_year }</c:if>
								                            </p>
								                        </div>

									                    <%-- <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">
		                        							<div class="line">
								                                <p class="alignleft">PB Item No</p>
								                                <p class="aligncenter">:</p>
								                                <p class="alignright">${wObj.pink_book_item_number}</p>
										                    </div> 
									                    </c:if> --%>
									                    <div class="line">
							                                <p class="alignleft">Railway</p>
							                                <p class="aligncenter">:</p>
							                                <p class="alignright">${wObj.railwayAgency}</p>
									                    </div> 
									                    <div class="line">
							                                <p class="alignleft">Execution Agency</p>
							                                <p class="aligncenter">:</p>
							                                <p class="alignright">${wObj.executedBy}</p>
									                    </div>
														<div class="line">
			
															<p class="alignleft">Physical Progress (%)</p>
															<p class="aligncenter">:</p>
															<p class="alignright">
																<%-- <c:if test="${wObj.project_status eq 'Closed' }"> </c:if> --%>
																<c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">100% </c:if>
															</p>
			
														</div>
														<div class="line">
															<p class="alignleft">Financial Progress (%)</p>
															<p class="aligncenter">:</p>
															<p class="alignright">
																<%-- <c:if test="${wObj.project_status eq 'Closed' }">100% </c:if> --%>
																<c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">100% </c:if>
															</p>
			
														</div>
												<div class="button">
														<!-- testing -->
														 <c:if test="${not empty wObj.workDocs}">
								                              <a class="btn btn-center modal-trigger" href="#workDocumentsModal${index.count }"><i class="fa fa-download" ></i></a> 
								                              <div id="workDocumentsModal${index.count }" class="modal">
														            <div class="modal-content">
														                <h5 class="modal-header">${wObj.work_short_name } Files<span class="right modal-action modal-close"><span
														                            class="material-icons">close</span></span></h5>
														                <div class="row">
														                    <div class="col m2 hide-on-small"></div>
														                    <table border = "1">
																		         <tr>
																		            <th>Work file Type</th>
																		            <th>File Name</th>
																		            <th>Uploaded On</th>
																		            <th>Download</th>
																		         </tr>
																		          <c:forEach var="wDocs" items="${wObj.workDocs}" >
																			         <tr>
																			            <td>${wDocs.work_file_type_fk }</td>
																			            <td>${wDocs.attachment }</td>
																			            <td>${wDocs.created_date }</td>
																			            <td style="text-align:center;">
																			             <a href="<%=CommonConstants2.WORK_FILES %>${wObj.work_id }/${wDocs.attachment }" class="filevalue" download><i class="fa fa-arrow-down"></i></a>
															                            </td>
																			         </tr>
																		          </c:forEach>	  
																		      </table>
														                    <div class="col m2 hide-on-small"></div>
														                </div>
														
														            </div>
														    </div>
														                              
								                        </c:if>         
									                   
								                        <c:forEach var="obj" items="${workDetails }">
	 															<c:if test="${ obj.work_id eq wObj.work_id}">
										                          <a class="btn btn-right" onclick="closeOther('${index.count }'); getTableauDashboard('${obj.work_id}'); ">More</a> 
									                            </c:if>		                                				
								                         </c:forEach>
								                         	                        
								                        </div>
		                                            </div>
		                                        </div>
		                                    </div>
	                                    </c:forEach>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div> 
	            	           
            </c:forEach>
        </div>
    </div>    
 
  <!-- footer included -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
    		
  <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/wrpmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/wrpmis/resources/js/select2.min.js"></script>
  <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>

   <script>
        $(document).ready(function () {
        	$('.modal').modal({ dismissible: false });
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
             /*    onOpenStart: function() {
                    $('<div class="modal-overlay" style="z-index:9998; display: block; opacity: 0.7;"></div>').insertAfter('#dropdown1');
                }, */
                onCloseStart: function() {
                    $('#dropdown1').next('.modal-overlay').remove();
                }
            });
            $('.sidenav').sidenav();
            $('.collapsible').collapsible();
            $('.modal').modal({
                dismissible: false,
                'onOpenEnd': initCarousel
            });
            $('.tooltipped').tooltip();
            $('.carousel').carousel({
                fullWidth: true
              });
            function initCarousel() {
            	$('.carousel').carousel({
                    fullWidth: true
                  });
            }
           
            var searchData = {};
            
            <c:forEach var="pObj" items="${projectsInfo}" varStatus="index">
            	searchData['${pObj.project_name}'] = null;
            </c:forEach>
            
            $('input.autocomplete').autocomplete({
                data: searchData,
            });
        });
        
        

        $("#projects_search").on("keyup change", function() {
            var txt = $('#projects_search').val();
            $('.projects-filter').each(function(){
            	if($(this).text().toUpperCase().indexOf(txt.toUpperCase()) != -1){
                   $(this).show();
               }else{
            	   $(this).hide();
               }
            });
            
            $('.projects-filter-work').each(function(){
            	if($(this).text().toUpperCase().indexOf(txt.toUpperCase()) != -1){
                   $(this).show();
               }else{
            	   $(this).hide();
               }
            });
        });
                

    	/*function showWorkData(indexNo) {
            $('.result').each(function () {
                if (!$(this).hasClass('hidden')) {
                    $(this).addClass('hidden');
                }
            });
            $('.card.main-clr.active').each(function () {
                $(this).removeClass('active');
            });
            $("#project_data_"+indexNo).addClass('active');
            $("#project_data_"+indexNo).parent().find('.result').removeClass('hidden');
            
            var colLeft = Number($("#project_data_"+indexNo).parent().position().left);
            var rowLeft = Number($("#project_data_"+indexNo).parent().parent().position().left);
            $("#project_data_"+indexNo).parent().find('.result').css('margin-left', (rowLeft - colLeft - 10) + 'px');
        } */

        function closeDiv(no) {
            $('.card.main-clr.active').each(function () {
                $(this).removeClass('active hidden');
            });
            $('#result' + no).addClass('hidden');
            $('[id^="project_data"].card.main-clr:not(.active)').each(function () {               
      			$(this).parent().removeClass('hidden');
             });
        }
        
        function closeOther(no){
        	  $('.result').each(function () {
                  if (!$(this).hasClass('hidden')) {
                      $(this).addClass('hidden');
                  }
              });
        	   $('.card.main-clr.active').each(function () {
                   $(this).removeClass('active');
               });
        	          	   
        	   $("#project_data_"+no).addClass('active hidden');
        	                
               $('[id^="project_data"].card.main-clr:not(.active)').each(function () {
                  $(this).parent().addClass('hidden');
               });
              // $("#project_data_"+no).parent().removeClass('hidden');
               $('#result'+no).removeClass('hidden');
        }
        
        
        function getTableauDashboard(work_id){
        	if($.trim(work_id) != ''){
        		var myParams = { work_id: work_id };
        		$.ajax({
                      url: "<%=request.getContextPath()%>/ajax/getDashBoradName",
                      data: myParams, cache: false,
                      success: function (data) {
                          if (data.length > 0) {
                              $.each(data, function (i, val) {
                                  if ($.trim(val.dashboard_name) != '') {
                                	  var parent_dashboard_id_sr_fk = $.trim(val.parent_dashboard_id_sr_fk) ;
                                	  var name = $.trim(val.dashboard_name).toLowerCase();
                                	  var link = name.replaceAll(" ", "-");
                                	  if($.trim(val.dashboard_id) == $.trim(val.parent_dashboard_id_sr_fk)){
                                		  
                                		  window.location.href = "<%=request.getContextPath()%>/InfoViz/"+link
                                	  }else{
                                		  var parent_dashboard_id_sr_fk = $.trim(val.parent_dashboard_id_sr_fk) ;
                                		  var myParams2 = { parent_dashboard_id_sr_fk: parent_dashboard_id_sr_fk };
                                		  $.ajax({
                                              url: "<%=request.getContextPath()%>/ajax/getSubLink",
                                              data: myParams2, cache: false,
                                              success: function (data) {
                                                  if (data.length > 0) {
                                                      $.each(data, function (i, val) {
                                                          if (parent_dashboard_id_sr_fk != '') {
                                                        	  var sub_link = $.trim(val.subLink).toLowerCase()+"/";
                                                        	  window.location.href = "<%=request.getContextPath()%>/InfoViz/"+sub_link+link
                                                          } 
                                                      });
                                                  }
                                              }
                                          });
                                	  }
                                  } 
                              });
                          }
                          
                      }
                  });
        		
        	}
        }

        
    </script>
    
</body>

</html>