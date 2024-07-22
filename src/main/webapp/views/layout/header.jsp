<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined"	rel="stylesheet">
<link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
<style>
	/* body .brand-logo img{
	     		width: 53rem! important;
	     	} */
	.nav-wrapper{z-index: 1000;padding-left: 0;height: 68px !important;}
	nav .menu-active, nav .menu-active.blue {
		/* background-color: #f56661 !important; */
		background: rgb(101, 150, 255, .75) !important;
		/* box-shadow: 0 0 5px #bababa inset */;
		transition: all 1s ease-in;
	}
	.blue.lighten-1, .blue.darken-3, .blue.darken-2, .blue, .blue.darken-1{
		background: #000063 !important;
	}
	li.item.row {
	    margin-bottom: 0;
	}
	input:not ([type] ), input[type=text]:not (.browser-default ), input[type=password]:not
		(.browser-default ), input[type=email]:not (.browser-default ), input[type=url]:not
		(.browser-default ), input[type=time]:not (.browser-default ), input[type=date]:not
		(.browser-default ), input[type=datetime]:not (.browser-default ),
		input[type=datetime-local]:not (.browser-default ), input[type=tel]:not
		(.browser-default ), input[type=number]:not (.browser-default ), input[type=search]:not
		(.browser-default ), textarea.materialize-textarea {
		margin-bottom: 0;
	}
	
	.issue-alert_bg {
		background-color: #90b6c0;
	}
	
	.notifications_group .item.issue-alert_bg:hover {
		background-color: #9ebb9f;
	}
	
	.unread-message {
		background-color: #C6E0B4 ;
	}
	
	.read-message {
		/*background-color: #e7e7e7;*/
		background-color: #ffffff!important;
	}
	
	.notifications_group .item.unread-message:hover {
		background-color: #C6E0B4 ;
	}
	
	.notifications_group .item.read-message:hover {
		background-color: #ffffff;
	}
	.fourth-lvl{
		margin-left: 10px !important;
    	margin-right: 10px !important;
    	background-color: #e0e0e0 !important;
	}
	.pt-10{
		padding-top:.6rem !important;
	}
	/*  textarea auto height code starts here */
    .pmis-textarea {
           height: inherit;
           padding: 1rem 0 .5rem;
           box-sizing: border-box;
           border: 0;
           border-bottom: 1px solid #9e9e9e;
           outline: none;
           resize: none;
           font-family: verdana;
           font-size: 16px;
           overflow:hidden;
           margin-bottom:8px;
       }
       .pmis-textarea.valid{
       	border-bottom: 1px solid #4CAF50;
	    -webkit-box-shadow: 0 1px 0 0 #4caf50;
	    box-shadow: 0 1px 0 0 #4caf50;
       }
       .pmis-textarea.invalid{
       	border-bottom: 1px solid #F44336;
	    -webkit-box-shadow: 0 1px 0 0 #f44336;
	    box-shadow: 0 1px 0 0 #f44336;
       }
       .pmis-textarea.my-valid-class {
		    color: inherit;
		}
       .pmis-textarea.valid.my-valid-class {
		    color: #4caf50;
		}
       .pmis-textarea.invalid.my-valid-class {
		    color: #f44336;
		}
     /*  textarea auto height code ends here */
     	.brand-logo{width: 100%;}
     	.brand-logo img{width: 4.5rem! important;}
     
     .markread{float: right;
			    background-color: rgba(255, 255, 255, 0.6);
			    padding: 4px 1rem;
			    border-radius: 1rem;
			    box-shadow: 2px 2px 4px #333;
			    border: none;
			    color: #333;}
	 .more{position: absolute;
 	   		top: 26em;
    		right: 0.5em;}	
	    @media only screen and (min-width: 1440px){
		#CurrentDate {
		    top: 0.7vw;
			}
		}
     @media only screen and (max-width:1400px) and (min-width:1024px) {
     	nav ul a{
     		padding : 0 5px;
     		font-size:.96rem;
     	}
     	.notification{
     		width:54px;     		
     	}
     	.notification .badge.red{
     		right:15px;
     		top:0;
     	}
     	.brand-logo img{
     		width: 4.5rem! important;
     	}
     }
     @media(max-width: 912px){
     	.brand-logo{width: 100%;}
     	nav .brand-logo {
    		left: 90%;
    	}
     	.brand-logo img{width: 4.5rem! important;}
     	.accordions{display:flex !important;}
     	.notifications_group{height: 52em;}
     	#tech_assist_ul{display:block;}
     }
     @media(max-width: 575px){
     /* .brand-logo.fs {
    		width: 50%;
		} */
		span.badge1{
			margin-top: 0px !important;
		}
     }
     
     .theme-change {
	    position: fixed;
	    z-index: 2;
	    right: -20px;
	    top: 80px;
	    border: 0;
	    cursor: pointer;
	    font-size: 1.5rem;
	    transition: all 1s ease-in-out;
	}
	@media(max-width: 1920px){
			#CurrentDate{
			 	left:4em !important;
			 	font-size:18px !important;
			}
		}
			 #CurrentDate{
			 	font-size:18px !important;
			}
			

			
	.accordions{
	    right: 8em;
	    position: absolute;
	    width: 4em;
	    display:none;
	    
	}
	.accordion {
	  margin: 10px 35px;
	  position: relative;
	  border-radius: .88rem;
	  background: #fff;
	  width: fit-content;
	  box-shadow: 2px 2px 4px rgb(0 0 0 / 30%);
	}
	
	
	.accordion__header {
	  position: absolute;
	  top: 0;
	  left: 0;
	  display: flex;
	  align-items: center;
	  height: 2.4rem;
	  width: 8rem;
	  padding: .31rem 1.25rem .44rem .56rem;
	  cursor: pointer;
	  border-radius: .88rem;
	  z-index: 10;
	}
	span.badge1{
		border-radius: 50px;
		margin-top: -9px;
		min-width: 1rem;
		padding: 0 8px;
	    margin-left: -9px;
	    font-size: 12px;
	    color: #fff;
	    height: 23px;
	}
	.material-icons-outlined{
		font-size: 28px;
		width: 30px;
	}
	.accordion__icon {
	  margin-right: .63rem;
	  width: 2rem;
	  height: 1.5rem;
	  transition: all .5s ease; 
	}
	
	.accordion__title {
	  font-weight: 700;
	  font-size: .75rem;
	}
	
	.accordion__body {
	  position: absolute;
	  float: right;
	  right:-4rem;
	  width: 400px !important;
	  height: 0;
	  min-height: 0;
	  border-radius: .88rem;
	  transition: all .5s ease;
	  overflow: hidden;
	  background-color: #fff;
	  color: #000;
	  top: 3rem;
	}
	
	.accordion__inner {
	  position: relative;
	  padding: 15px	;
	  width: 29rem;
	  font-size: .88rem;
	  line-height: 1.13rem
	}
	.rm9em{right: -9em !important;}
	.d-dis-none{
		display: none;
	}
	@media(max-width: 575px){
		.brand-logo.fs {
		    width: 55%;
    		left: 52%;
		}
		.brand-logo img {
		    width: 3.5rem! important;
		}
		#CurrentDate {
		    font-size: 16px !important;
		    left: 3.5em !important;
		}
		#notificationCountMobile, #messagesCountMobile{
			font-size: 10px;
		}
		.material-icons-outlined{
			font-size: 24px;
    		width: 28px;
		}
		nav .sidenav-trigger{
			margin-top: 5px;
		}
		.brand-logo.fs, .brand-logo.fs a{
			    font-size: 16px;
		}
		.accordions{
	    top:5px;
		}
		.d-dis-none{
			display: block;
		}
	}
	@media(max-width: 400px){
		.accordion__body{
			width: 375px !important;
		}
		.accordion__inner{
			width: 27rem;
		}
	}
	@media(max-width: 376px){
		.accordion__body{
			width: 360px !important;
		}
		.accordion__inner{
			width: 26rem;
		}
		.m-space{
			margin-left: -1.5em;
		}
		span.badge1{
			min-width: 10px;
			padding: 0 5px;
		}
		.accordions{
	    right: 6.5em;
	    width: 4em;
		}
		
		#CurrentDate{
			font-size: 14px !important;
    		left: 4em !important;
		}
	}
	@media(max-width: 360px){
		.accordion__body{
			width: 350px !important;
		}
		.accordion__inner{
			width: 26rem;
		}
		/* .brand-logo.fs {
		    width: 68%;
		} */
	}
		
</style>

<link id="theme" rel="stylesheet" type="text/css" href="" />

</head>

<body>
	<!-- light and dark theme shifting related code -->
	<button id="theme-change" class="theme-change">
		<i class="fa fa-sun-o"></i>
	</button>

	<!-- header @navigation starts here -->
	<nav>
	<div class="nav-wrapper blue lighten-1">
		<div class="">
			<a href="<%=request.getContextPath() %>/home" class="brand-logo fs"><img
				src="/pmis/resources/images/mrvcnewhome.png" alt="Logo"> <span class="brand-text">MRVC PMIS</span> <span id="CurrentDate"></span>
			</a> 
			<a href="<%=request.getContextPath() %>/home"
				data-target="mobile-demo" class="sidenav-trigger"> 
					<i class="fa fa-bars"></i>
			</a>
			<div class="accordions"> 

          <div class="col s6 m6 l6">
          	<div class="accordion">
              <div class="accordion__header">
                
                <div class="accordion__title"><span class="material-icons-outlined">notifications</span> <span
						class="badge badge1 red" id="notificationCountMobile">0 </span></div>
              </div>
              <div class="accordion__body rm9em">
                <div class="accordion__inner">
                  <div class="accordion__text">
                  <div class="search-holder">
							<input type="text" name="srch-term" id="srch-term"
								class="browser-default searching empty"
								placeholder="&#xF002; Search Alerts..." >
								<select class="browser-default" id="alert_type_fk_mob" name="alert_type_fk" onchange="getAlertsForHeaderNotifications(this.value);">														
									<option value="">Alert Type</option>		
									<c:forEach var="obj" items="${alertTypes }">
										<option value="${obj.alert_type_fk}">${obj.alert_type_fk}</option>	
									</c:forEach>							
								</select>
								
						</div>

						<ul class="notifications_group"	id="notificationListMobile" > 
							<!-- list of Notifications starts -->
							
							<c:forEach var="obj" items="${alerts }">
								<li class="head-item">${obj.key}</li>
								<c:if test="${obj.key eq '3rd Alert'}">
									<c:set var="bgClass" value="type-3"></c:set>
								</c:if>
								<c:if test="${obj.key eq '2nd Alert'}">
									<c:set var="bgClass" value="type-2"></c:set>
								</c:if>
								<c:if test="${obj.key eq '1st Alert'}">
									<c:set var="bgClass" value="type-1"></c:set>
								</c:if>
								<c:forEach var="aObj" items="${obj.value}">

									<c:if test="${aObj.alert_type_fk eq 'Bank Guarantee'}">
										<c:set var="bgIcon"
											value="<i class='material-icons'>account_balance</i>">
										</c:set>
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Insurance'}">
										<c:set var="bgIcon"
											value="<i class='material-icons'>security</i>">
										</c:set>
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Contract Period'}">
										<c:set var="bgIcon"
											value="<i class='material-icons'>access_time</i>">
										</c:set>
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Contract Value'}">
										<c:set var="bgIcon" value="<i class='fa fa-money'></i>">
										</c:set>
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Issue'}">
										<c:set var="bgIcon" value="<i class='fa fa-exclamation-triangle'></i>"></c:set> 										
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Risk'}">
										<c:set var="bgIcon" value="<i class='material-icons'>error_outline</i>"></c:set> 										
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Safety'}">
										<c:set var="bgIcon" value="<i class='material-icons-outlined'>verified_user</i>"></c:set>										
									</c:if>								
										 
									<li class="item ${bgClass } row" >										
										<a href="<%=request.getContextPath()%>${aObj.redirect_url }" target="_new">
											<div class="col m2">
												<span class="bl" class="icon"> <!-- <i class="material-icons">access_time</i> -->
													${bgIcon } <span class="bl" class="icon-text">${aObj.alert_type_fk
	                                                                            }</span>
												</span>										
											</div>
											<div class="col m10">
											<c:if test="${aObj.alert_type_fk ne 'Risk' }">
												<div>Work : ${aObj.work_short_name }</div>
												<div>Contract : ${aObj.contract_short_name }</div>
												<div>Contractor : ${aObj.contractor_name }</div>
											</c:if>
											<div>Reason : ${aObj.alert_value }</div>
											</div>
									</a></li>

								</c:forEach>
							</c:forEach>
						</ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          
            <div class="col s6 m6 l6">
            	<div class="accordion">
                <div class="accordion__header">
                  
                  <div class="accordion__title m-space"><span class="material-icons-outlined">chat_bubble_outlined</span>
						<span class="badge badge1 red" id="messagesCountMobile">0 </span></div>
                </div>
                <div class="accordion__body">
                  <div class="accordion__inner">
                    <div class="accordion__text">
                    <!-- Mobile messages starts here -->
						<div class="search-holder">
							<input type="text" name="srch-term" id="messages-srch-term"
								class="browser-default searching empty"
								placeholder="&#xF002; Search Messages...">
							<select class="browser-default" id="message_type_web" name="message_type" onchange="getMesagesForHeader(this.value);">														
								<option value="">Message Type</option>	
								<c:forEach var="obj" items="${messageTypes }">
									<option value="${obj.message_type}">${obj.message_type}</option>	
								</c:forEach>								
							</select>
						</div>
						<ul class="notifications_group message_group"	id="messagesListMobile">
							<!-- list of messages starts -->
							<c:if test="${not empty messages and fn:length(messages) gt 0}">
							
								<li class="head-item">Messages</li>
								<c:forEach var="obj" items="${messages }">
									<c:if test="${not empty obj.read_time}">
										<c:set var="message_color_bg" value="read-message"></c:set>
									</c:if>
									<c:if test="${empty obj.read_time}">
										<c:set var="message_color_bg" value="unread-message"></c:set>
									</c:if>

									<li class="item ${message_color_bg}">
										<a target="_new" href="<%=request.getContextPath()%>${obj.redirect_url}<c:if test="${fn:contains(obj.redirect_url, '?')}">&</c:if><c:if test="${not fn:contains(obj.redirect_url, '?')}">?</c:if>message_id=${obj.message_id }">
											<div class="row col m12">
												<div class="col m2">
													<i class='fa fa-exclamation-triangle'></i> 
													<span class="icon-text">${obj.message_type }</span>
											    </div>
											    <div class="col m10">
											    	<div>${obj.message }</div> 
											    	<span class="date_text"> <i class='fa fa-clock-o'></i> ${obj.created_date }</span>
											    </div>
										    </div>
										</a>
									</li>
								</c:forEach>
							</c:if>														
						</ul>
						
							<!-- <div class="head-item  more"><button class="markread"  id="markasread" onclick="getAllMessages();">More</button></div> -->
						
						<!-- messages dropdown body ends -->
					
                    </div>
                  </div>
                </div>
              </div>
            </div>
          
              
                      
        </div>
			
			<ul class="right hide-on-med-and-down top-level-menu" id="DesktopView">
				<li class="blue darken-3"><a
					href="<%=request.getContextPath() %>/home"><span
						class="material-icons-outlined">home</span>Home</a></li>
				<%-- <c:if test="${sessionScope.USER_ROLE_NAME ne 'Input User' }"> --%>
					<li class="blue darken-2 dropdown"><a href="#"
						class='head-img'> <!-- 1st level Dropdown starts --> <!-- img src="/pmis/resources/images/dashboard-white.png"-->
							<span class="material-icons-outlined">dashboard</span> Modules
					</a>
						<ul class="second-level-menu">
							<c:forEach var="category" items="${dashboardModulesList }"
								varStatus="index">
								<c:set var="tempactivity"
									value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}">
								</c:set>
								<c:set var="activity"
									value="${ fn:toLowerCase(tempactivity.replaceAll(' ', '-'))}">
								</c:set>
								<c:if test="${empty category.tableauSubList}">
									<li><a
										href="<%=request.getContextPath()%>/InfoViz/${activity }">
											<span style="padding-right: 5px;"
											class="fa fa-${category.imagePath}"></span> <span
											class="nav-label">${category.tableauDashboardName
                                                                        }</span>
									</a></li>
								</c:if>
								<c:if test="${not empty  category.tableauSubList}">
									<li class="sub-menu">
										<!-- 2nd level Dropdown starts --> 
										<a href="#!"> <span
											style="padding-right: 5px;"
											class="fa fa-${category.imagePath}"></span> <span
											class="nav-label">${category.tableauDashboardName
                                                                        }</span>
										</a>
										<ul class="third-level-menu">
											<c:forEach var="subList" items="${category.tableauSubList }">
												<c:set var="tempsubActivity"
													value="${ fn:toLowerCase(subList.tableauDashboardName.replaceAll(' - ', '_'))}">
												</c:set>
												<c:set var="subActivity"
													value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}">
												</c:set>
												<%-- <li><a
													href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
														<span style="padding-right: 5px;"
														class="fa fa-${subList.imagePath }"></span> <span
														class="nav-label">${subList.tableauDashboardName
                                                                                    }</span>
												</a></li> --%>
												
												<c:if test="${empty subList.tableauSubListLevel2}">
													<li>
													<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
															<span style="padding-right: 5px;" class="fa fa-${subList.imagePath }"></span> 
															<span class="nav-label">${subList.tableauDashboardName }</span>
													</a>
													</li>
												</c:if>
												<c:if test="${not empty  subList.tableauSubListLevel2}">
													<li class="sub-menu">
														<a href="#!"> 
															<span style="padding-right: 5px;" class="fa fa-${subList.imagePath}"></span> 
															<span class="nav-label">${subList.tableauDashboardName}</span>
														</a>
														<ul class="third-level-menu">
															<c:forEach var="subListLevel2" items="${subList.tableauSubListLevel2 }">
																<c:set var="tempsubActivityLevel2"
																	value="${ fn:toLowerCase(subListLevel2.tableauDashboardName.replaceAll(' - ', '_'))}">
																</c:set>
																<c:set var="subActivityLevel2"
																	value="${ fn:toLowerCase(tempsubActivityLevel2.replaceAll(' ', '-'))}">
																</c:set>
																<li><a
																	href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivityLevel2 }">
																		<span style="padding-right: 5px;"
																		class="fa fa-${subListLevel2.imagePath }"></span> <span
																		class="nav-label">${subListLevel2.tableauDashboardName
				                                                                                    }</span>
																</a></li>
															</c:forEach>
														</ul></li>
												</c:if>
												
											</c:forEach>
										</ul>
									</li>
									<!-- 2nd level Dropdown ends -->
								</c:if>

							</c:forEach>
						</ul> <!-- 1st level Dropdown ends --></li>

					<li class="blue darken-1 dropdown"> 
						<a href="#" class='head-img'> <span class="material-icons-outlined">dashboard</span>Works</a>
						<ul class="second-level-menu">
							<c:forEach var="category" items="${dashboardProjectsList }"
								varStatus="index">
								<c:set var="tempactivity"
									value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}">
								</c:set>
								<c:set var="activity"
									value="${ fn:toLowerCase(tempactivity.replaceAll(' ', '-'))}">
								</c:set>
								<c:if test="${empty category.tableauSubList}">
									<li><a
										href="<%=request.getContextPath()%>/${category.tableauUrl }">
											<span style="padding-right: 5px;"
											class="fa fa-${category.imagePath}"></span> <span
											class="nav-label">${category.tableauDashboardName
                                                                        }</span>
									</a></li>
								</c:if>
								<c:if test="${not empty  category.tableauSubList}">
									<li class="sub-menu"><a href="#!"> <span
											style="padding-right: 5px;"
											class="fa fa-${category.imagePath}"></span> <span
											class="nav-label">${category.tableauDashboardName
                                                                        }</span>
									</a>
										<ul class="third-level-menu">
											<c:forEach var="subList" items="${category.tableauSubList }">
												<c:set var="tempsubActivity"
													value="${ fn:toLowerCase(subList.tableauDashboardName.replaceAll(' - ', '_'))}">
												</c:set>
												<c:set var="subActivity"
													value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}">
												</c:set>
												<%-- <li>
												<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
														<span style="padding-right: 5px;" class="fa fa-${subList.imagePath }"></span> 
														<span class="nav-label">${subList.tableauDashboardName }</span>
												</a>
												</li> --%>
												
												
												
												<c:if test="${empty subList.tableauSubListLevel2}">
													<li>
													<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
															<span style="padding-right: 5px;" class="fa fa-${subList.imagePath }"></span> 
															<span class="nav-label">${subList.tableauDashboardName }</span>
													</a>
													</li>
												</c:if>
												<c:if test="${not empty  subList.tableauSubListLevel2}">
													<li class="sub-menu">
														<a href="#!"> 
															<span style="padding-right: 5px;" class="fa fa-${subList.imagePath}"></span> 
															<span class="nav-label">${subList.tableauDashboardName}</span>
														</a>
														<ul class="third-level-menu">
															<c:forEach var="subListLevel2" items="${subList.tableauSubListLevel2 }">
																<c:set var="tempsubActivityLevel2"
																	value="${ fn:toLowerCase(subListLevel2.tableauDashboardName.replaceAll(' - ', '_'))}">
																</c:set>
																<c:set var="subActivityLevel2"
																	value="${ fn:toLowerCase(tempsubActivityLevel2.replaceAll(' ', '-'))}">
																</c:set>
																<li><a
																	href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivityLevel2 }">
																		<span style="padding-right: 5px;"
																		class="fa fa-${subListLevel2.imagePath }"></span> <span
																		class="nav-label">${subListLevel2.tableauDashboardName
				                                                                                    }</span>
																</a></li>
															</c:forEach>
														</ul></li>
												</c:if>
												
											</c:forEach>
										</ul></li>
								</c:if>
							</c:forEach>
						</ul></li>
				<%-- </c:if> --%>

				<c:if test="${sessionScope.USER_ROLE_NAME ne 'Super User' && sessionScope.USER_ROLE_NAME ne 'Contractor' }">
					<li class="blue dropdown">
						<a href="#" class='head-img'> <span class="material-icons-outlined">post_add</span> Update Forms</a>

						<ul class="second-level-menu">
							<!-- 1st level Dropdown starts -->
							
							<c:forEach var="form" items="${forms }" varStatus="index">
								<c:if test="${empty form.formsSubMenu}">
									<li><c:if test="${not empty form.webFormUrl}">
											<a href="<%=request.getContextPath()%>/${form.webFormUrl }">
												<span class="nav-label">${form.formName }</span>
											</a>
										</c:if></li>
								</c:if>
								<c:if test="${not empty form.formsSubMenu}">
									<li class="sub-menu">
										<a href="#!"> <span class="nav-label">${form.formName }</span>  </a>
									
										<ul class="third-level-menu">
											<!-- 2nd level Dropdown starts -->
											<c:forEach var="subList" items="${form.formsSubMenu }">

												<c:if test="${ empty subList.formsSubMenuLevel2 and not empty subList.webFormUrl}">
													<li><a
														href="<%=request.getContextPath()%>/${subList.webFormUrl }">
															<span class="nav-label">${subList.formName }</span>
													</a></li>
												</c:if>
												<c:if test="${not empty subList.formsSubMenuLevel2}">
													<li class="sub-menu">
														<a href="#">${subList.formName }</a>
														<ul class="fourth-level-menu">
															<c:forEach var="subListLevel2"
																items="${subList.formsSubMenuLevel2}">
																<li><a
																	href="<%=request.getContextPath()%>/${subListLevel2.webFormUrl }">
																		${subListLevel2.formName } </a></li>
															</c:forEach>
														</ul></li>
												</c:if>
											</c:forEach>
										</ul></li>
								</c:if>
								<%-- </c:if> --%>
							</c:forEach>
							<!-- 1st level Dropdown ends -->
						</ul></li>
				</c:if>

				<c:if test="${not empty reportForms}">
					<li class="blue lighten-1 dropdown"><a href="#"
						class='head-img'> <span class="material-icons-outlined">assignment</span>
							Reports
					</a>
						<ul class="second-level-menu">
						
							<!-- 1st level Dropdown starts -->
							<c:forEach var="form" items="${reportForms }" varStatus="index">
								<c:if test="${empty form.formsSubMenu}">
									<li><a
										href="<%=request.getContextPath()%>/${form.webFormUrl }">
											<span class="nav-label">${form.formName }</span>
									</a></li>
								</c:if>
								<c:if test="${not empty form.formsSubMenu}">
									<li class="sub-menu"><a href="#!"> <span
											class="nav-label">${form.formName }</span>
									</a>
										<ul class="third-level-menu">
											<!-- 2nd level Dropdown starts -->
											<c:forEach var="subList" items="${form.formsSubMenu }">
												<c:if test="${ empty subList.formsSubMenuLevel2 and not empty subList.webFormUrl}">
													<li><a
														href="<%=request.getContextPath()%>/${subList.webFormUrl }">
															<span class="nav-label">${subList.formName }</span>
													</a></li>
												</c:if>
												<c:if test="${not empty subList.formsSubMenuLevel2}">
													<li class="sub-menu"><a href="#">
															${subList.formName }</a>
														<ul class="fourth-level-menu">
															<c:forEach var="subListLevel2"
																items="${subList.formsSubMenuLevel2}">
																<li><a
																	href="<%=request.getContextPath()%>/${subListLevel2.webFormUrl }">
																		${subListLevel2.formName } </a></li>
															</c:forEach>
														</ul></li>
												</c:if>											
											</c:forEach>
											<!-- 2nd level Dropdown ends -->
										</ul></li>
								</c:if>

							</c:forEach>
							<!-- 1st level Dropdown ends -->
						</ul></li>
				</c:if>
				<c:if test="${sessionScope.USER_ROLE_NAME ne 'Contractor' }">
				<li class="blue darken-2"><a href="javascript:void(0);"
					class='head-img'> <span class="material-icons-outlined">description</span>
						Documents
				</a>
					<ul class="second-level-menu">
						<c:forEach var="obj" items="${webDocumentTypes}">
							<c:set var="tempWebDocType"
								value="${ fn:toLowerCase(obj.type.replaceAll(' - ', '_'))}">
							</c:set>
							<c:set var="webDocType"
								value="${ fn:toLowerCase(tempWebDocType.replaceAll(' ', '-'))}">
							</c:set>
							<li><a
								href="<%=request.getContextPath()%>/web-documents/${webDocType}">
									<span class="nav-label">${obj.type}</span>
							</a></li>
						</c:forEach>
					</ul></li>
				
				<%--<li class="blue"><a href="<%=request.getContextPath()%>/web-links"
                                                class='head-img'>
                                                <span class="material-icons-outlined">link</span> Quick Links</a>
                                            </li>--%>
				<li class="blue darken-1"><a href="javascript:void(0);"
					class='head-img'> <span class="material-icons-outlined">link</span> Quick
						Links
				</a>
					<ul class="second-level-menu">
						<c:forEach var="lObj" items="${webLinksList }">
							<li><a href="${lObj.link }" target="_blank"> <span
									class="nav-label">${lObj.name}</span>
							</a></li>
						</c:forEach>
					</ul></li>
				</c:if>
				<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' }">
					<li class="blue"><a href="javascript:void(0);"
						class='head-img'> <span class="material-icons-outlined">list_alt</span> Admin
					</a>
						<ul class="second-level-menu">
							<c:forEach var="form" items="${adminForms }" varStatus="index">
								<c:if test="${empty form.formsSubMenu}"> 
								<li><a href="<%=request.getContextPath()%>/${form.url }">
										<span class="nav-label">${form.form_name}</span>
								</a></li>
								</c:if> 
								<c:if test="${not empty form.formsSubMenu}">
									<li class="sub-menu"><a href="#!"> <span
											class="nav-label">${form.form_name }</span>
									</a>
										<ul class="third-level-menu">
											<c:forEach var="subList" items="${form.formsSubMenu }">
													<li><a href="<%=request.getContextPath()%>/${subList.url }">
															<span class="nav-label">${subList.form_name }</span>
													</a></li>
											</c:forEach>
											<!-- 2nd level Dropdown ends -->
										</ul></li>
								</c:if>								
								
							</c:forEach>
						</ul></li>
				</c:if>

				<%-- <li class="blue lighten-1"><a href="javascript:void(0);"
                                                    class='head-img notification' id="notification">
                                                    <span class="material-icons-outlined">notifications</span>
                                                    <span class="notification_number">
                                                        <c:if test="${dueActivities.size() > 99 }">
                                                            99+
                                                        </c:if>
                                                        <c:if test="${dueActivities.size() < 100 }">
                                                            ${dueActivities.size() }
                                                        </c:if>
                                                    </span></a>
                                                <div class="notification_body">
                                                    <ul class="notifications_group">
                                                        <c:forEach var="aObj" items="${dueActivities }">
                                                            <li class="item" style="cursor: pointer;"
                                                                onclick="openActivityUpdateForm('${aObj.taskId}','${aObj.workId}');">
                                                                ${aObj.location } / ${aObj.activityType } /
                                                                ${aObj.taskName }</li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>

                                                </li> --%>

				<li class="blue lighten-1">
					<!-- Notification code starts --> <a
					class='notification dropdown-trigger' data-target='dropdown1'>
						<span class="material-icons-outlined">notifications</span> <span
						class="badge red" id="notificationCount">0 </span>
				</a>
					<div class="notification_body dropdown-content" id='dropdown1'>
						<!-- Notification dropdown body starts -->
						<div class="search-holder">
							<input type="text" name="srch-term" id="srch-term"
								class="browser-default searching empty"
								placeholder="&#xF002; Search Alerts..." >
								<select class="browser-default" id="alert_type_fk_web" name="alert_type_fk" onchange="getAlertsForHeaderNotifications(this.value);">														
									<option value="">Alert Type</option>		
									<c:forEach var="obj" items="${alertTypes }">
										<option value="${obj.alert_type_fk}">${obj.alert_type_fk}</option>	
									</c:forEach>							
								</select>
								
						</div>

						<ul class="notifications_group"	id="notificationList" > 
							<!-- list of Notifications starts -->
							
							<c:forEach var="obj" items="${alerts }">
								<li class="head-item">${obj.key}</li>
								<c:if test="${obj.key eq '3rd Alert'}">
									<c:set var="bgClass" value="type-3"></c:set>
								</c:if>
								<c:if test="${obj.key eq '2nd Alert'}">
									<c:set var="bgClass" value="type-2"></c:set>
								</c:if>
								<c:if test="${obj.key eq '1st Alert'}">
									<c:set var="bgClass" value="type-1"></c:set>
								</c:if>
								<c:forEach var="aObj" items="${obj.value}">

									<c:if test="${aObj.alert_type_fk eq 'Bank Guarantee'}">
										<c:set var="bgIcon"
											value="<i class='material-icons'>account_balance</i>">
										</c:set>
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Insurance'}">
										<c:set var="bgIcon"
											value="<i class='material-icons'>security</i>">
										</c:set>
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Contract Period'}">
										<c:set var="bgIcon"
											value="<i class='material-icons'>access_time</i>">
										</c:set>
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Contract Value'}">
										<c:set var="bgIcon" value="<i class='fa fa-money'></i>">
										</c:set>
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Issue'}">
										<c:set var="bgIcon" value="<i class='fa fa-exclamation-triangle'></i>"></c:set> 										
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Risk'}">
										<c:set var="bgIcon" value="<i class='material-icons'>error_outline</i>"></c:set> 										
									</c:if>
									<c:if test="${aObj.alert_type_fk eq 'Safety'}">
										<c:set var="bgIcon" value="<i class='material-icons-outlined'>verified_user</i>"></c:set>										
									</c:if>								
										 
									<li class="item ${bgClass } row" >										
										<a href="<%=request.getContextPath()%>${aObj.redirect_url }" target="_new">
											<div class="col m2">
												<span class="bl" class="icon"> <!-- <i class="material-icons">access_time</i> -->
													${bgIcon } <span class="bl" class="icon-text">${aObj.alert_type_fk
	                                                                            }</span>
												</span>										
											</div>
											<div class="col m10">
											<c:if test="${aObj.alert_type_fk ne 'Risk' }">
												<div>Work : ${aObj.work_short_name }</div>
												<div>Contract : ${aObj.contract_short_name }</div>
												<div>Contractor : ${aObj.contractor_name }</div>
											</c:if>
											<div>Reason : ${aObj.alert_value }</div>
											</div>
									</a></li>

								</c:forEach>
							</c:forEach>
						</ul>

						<!-- Notification dropdown body ends -->
					</div>
				</li>

				<li class="blue ">
					<!-- messages code starts --> <a
					class='notification dropdown-trigger' data-target='messages1'  >
						<span class="material-icons-outlined">chat_bubble_outlined</span>
						<span class="badge red" id="messagesCount">0 </span>
				</a>
					<div class="notification_body dropdown-content" id='messages1'>
						<div class="search-holder">
							<input type="text" name="srch-term" id="messages-srch-term"
								class="browser-default searching empty"
								placeholder="&#xF002; Search Messages...">
							<select class="browser-default" id="message_type_web" name="message_type" onchange="getMesagesForHeader(this.value);">														
								<option value="">Message Type</option>	
								<c:forEach var="obj" items="${messageTypes }">
									<option value="${obj.message_type}">${obj.message_type}</option>	
								</c:forEach>								
							</select>
						</div>
						<ul class="notifications_group message_group"	id="messagesList">
							<!-- list of messages starts -->
							<c:if test="${not empty messages and fn:length(messages) gt 0}">
							
								<li class="head-item">Messages</li>
								<c:forEach var="obj" items="${messages }">
									<c:if test="${not empty obj.read_time}">
										<c:set var="message_color_bg" value="read-message"></c:set>
									</c:if>
									<c:if test="${empty obj.read_time}">
										<c:set var="message_color_bg" value="unread-message"></c:set>
									</c:if>

									<li class="item ${message_color_bg}">
										<a target="_new" href="<%=request.getContextPath()%>${obj.redirect_url}<c:if test="${fn:contains(obj.redirect_url, '?')}">&</c:if><c:if test="${not fn:contains(obj.redirect_url, '?')}">?</c:if>message_id=${obj.message_id }">
											<div class="row col m12">
												<div class="col m2">
													<i class='fa fa-exclamation-triangle'></i> 
													<span class="icon-text">${obj.message_type }</span>
											    </div>
											    <div class="col m10">
											    	<div>${obj.message }</div> 
											    	<span class="date_text"> <i class='fa fa-clock-o'></i> ${obj.created_date }</span>
											    </div>
										    </div>
										</a>
									</li>
								</c:forEach>
							</c:if>														
						</ul>
						
							<!-- <div class="head-item  more"><button class="markread"  id="markasread" onclick="getAllMessages();">More</button></div> -->
						
						<!-- messages dropdown body ends -->
					</div>
				</li>

				<li class="blueblue lighten-1 dropdown"><a href="#"
					class='head-img'> <img
						src="<%=CommonConstants2.USER_IMAGES %>${sessionScope.user.user_image}"
						class="profile-img"
						onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';" />
						<!--           <span class="material-icons">person</span> -->
						${USER_NAME }<c:if test="${empty USER_NAME }">${USER_ID}</c:if></a> <!-- change password and logout here -->
					<ul class="second-level-menu rs">
						<li><a href="<%=request.getContextPath()%>/profile">Profile</a></li>
						<li><a href="<%=request.getContextPath()%>/reset-password">Reset
								password</a></li>
						<li><a href="#" title="Form Error">Help</a></li>
						<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
					</ul></li>
			</ul>			
	
		
		</div>
	</div>
	</nav>
	
	<img src="/pmis/resources/images/backbtn.png" onclick="javascript:window.history.back();" width="30" height="30" id="imgbackBtn" class="d-dis-none">
	
	
	
	<!-- header @navigation ends here -->

	<!-- Mobile navigation stars here -->
	<ul class="sidenav collapsible" id="mobile-demo">
		<li style="background-color: #dfdfdf">
			<!-- Mobile navigation close button -->			
			<div class="head">
				<span class="left-align">
					<img src="<%=CommonConstants2.USER_IMAGES %>${sessionScope.user.user_image }"
					class="profile-img"	onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';">
					${USER_NAME }<c:if test="${empty USER_NAME }">${USER_ID}</c:if>
				</span>
				<!-- a class="sidenav-close" href="#!"><i class="fa fa-close sidenav-close"></i></a-->
				<span class="right" style="margin-top:.7rem">
					<i class="fa fa-close sidenav-close right-align"></i>
				</span>
			</div>
		</li>


		<c:if test="${sessionScope.USER_ROLE_NAME ne 'Super User' }">
			<li class="sub-menu"><a href="#"
				class='head-img collapsible-header'><span
					class="material-icons-outlined">post_add</span> Update Forms</a>
				<ul class="dropdown-data collapsible-body second-lvl collapsible">
					<c:forEach var="form" items="${forms }" varStatus="index">
					<c:if test="${form.displayInMobile eq 'Yes'}">
						<c:if test="${empty form.formsSubMenu}">
							<li>
								<c:if test="${not empty form.webFormUrl}">									
										<a href="<%=request.getContextPath()%>/${form.webFormUrl }">
											<span class="nav-label">${form.formName }</span> 										</a>									
								</c:if>
							</li>
						</c:if>
						
						<c:if test="${not empty form.formsSubMenu}">
								<li class="sub-menu"><a href="#!" class="collapsible-header">
										<span class="nav-label">${form.formName }</span> 
								</a>
									<ul class="dropdown-data collapsible-body third-lvl">
										<c:forEach var="subList" items="${form.formsSubMenu }">
										<c:if test="${subList.displayInMobile eq 'Yes'}">
											<c:if test="${ empty subList.formsSubMenuLevel2}">
												<li><a
													href="<%=request.getContextPath()%>/${subList.webFormUrl }">
														<span class="nav-label">${subList.formName }</span>
												</a></li>
											</c:if>
											<c:if test="${not empty subList.formsSubMenuLevel2}">
												<li class="sub-menu" id="tech_assist_list"><a href="#!" class="collapsible-header">
														<span class="nav-label">${subList.formName }</span></a>
													<ul class="dropdown-data collapsible-body fourth-lvl" id="tech_assist_ul">
														<c:forEach var="subListLevel2" items="${subList.formsSubMenuLevel2}">
															<li><a
																href="<%=request.getContextPath()%>/${subListLevel2.webFormUrl }">
																	${subListLevel2.formName } </a></li>
														</c:forEach>
													</ul></li>
											</c:if>
										  </c:if>
										</c:forEach>
									</ul></li>
						</c:if> 
						</c:if> 
		
					</c:forEach>

				</ul></li>
		</c:if>

		<%-- <li><a href="<%=request.getContextPath()%>/dpr" class='head-img'><span
                                    class="material-icons-outlined">assignment</span> Reports</a></li> --%>

		<c:if test="${not empty reportForms}">
			<li class="sub-menu"><a href="#"
				class='head-img collapsible-header'><span
					class="material-icons-outlined">assignment</span> Reports</a>
				<ul class="dropdown-data collapsible-body second-lvl collapsible">
					<c:forEach var="form" items="${reportForms }" varStatus="index">
					<c:if test="${form.displayInMobile eq 'Yes'}">
						<c:if test="${empty form.formsSubMenu}">
							<li>
								<a href="<%=request.getContextPath()%>/${form.webFormUrl }"> <span
									class="nav-label">${form.formName }</span>
								</a>
							</li>
						</c:if>
						
						   <c:if test="${not empty form.formsSubMenu}">
								<li class="sub-menu"><a href="#!" class="collapsible-header">
										<span class="nav-label">${form.formName }</span>
								</a>
									<ul class="dropdown-data collapsible-body third-lvl">
										<c:forEach var="subList" items="${form.formsSubMenu }">
												<c:if test="${ empty subList.formsSubMenuLevel2 and not empty subList.webFormUrl}">
													<li><a
														href="<%=request.getContextPath()%>/${subList.webFormUrl }">
															<span class="nav-label">${subList.formName }</span>
													</a></li>
												</c:if>
												<c:if test="${not empty subList.formsSubMenuLevel2}">
													<li class="sub-menu active" ><a href="#!" >
															${subList.formName }</a>
														<ul class="dropdown-data collapsible-body fourth-lvl" id="tech_assist_ul" style="display: block;">
															<c:forEach var="subListLevel2"
																items="${subList.formsSubMenuLevel2}">
																<li><a
																	href="<%=request.getContextPath()%>/${subListLevel2.webFormUrl }">
																		${subListLevel2.formName } </a></li>
															</c:forEach>
														</ul></li>
												</c:if>
										</c:forEach>
									</ul></li>
						</c:if> 
						</c:if>
					</c:forEach>
				
				</ul></li>
		</c:if>
				<li class="sub-menu"><a href="javascript:void(0);"
					class='head-img collapsible-header'> <span class="material-icons-outlined">description</span>
						Documents
				</a>
					<ul class="dropdown-data collapsible-body second-lvl collapsible">
						<c:forEach var="obj" items="${webDocumentTypes}">
							<c:set var="tempWebDocType"
								value="${ fn:toLowerCase(obj.type.replaceAll(' - ', '_'))}">
							</c:set>
							<c:set var="webDocType"
								value="${ fn:toLowerCase(tempWebDocType.replaceAll(' ', '-'))}">
							</c:set>
							<li><a
								href="<%=request.getContextPath()%>/web-documents/${webDocType}">
									<span class="nav-label">${obj.type}</span>
							</a></li>
						</c:forEach>
					</ul></li>
				<%--<li class="blue"><a href="<%=request.getContextPath()%>/web-links"
                                                class='head-img'>
                                                <span class="material-icons-outlined">link</span> Quick Links</a>
                                            </li>--%>
				<li class="sub-menu"><a href="javascript:void(0);"
					class='head-img collapsible-header'> <span class="material-icons-outlined">link</span> Quick
						Links
				</a>
					<ul class="dropdown-data collapsible-body second-lvl collapsible">
						<c:forEach var="lObj" items="${webLinksList }">
							<li><a href="${lObj.link }" target="_blank"> <span
									class="nav-label">${lObj.name}</span>
							</a></li>
						</c:forEach>
					</ul></li>
 
		<li class="sub-menu"><a href="#"
			class='head-img collapsible-header'> <img
				src="<%=CommonConstants2.USER_IMAGES %>${sessionScope.user.user_image }"
				class="profile-img"
				onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';">
				<!--     <span class="material-icons">person</span>  --> ${USER_NAME }<c:if
					test="${empty USER_NAME }">${USER_ID}</c:if></a>
			<ul class="dropdown-data collapsible-body second-lvl">
				<li><a href="<%=request.getContextPath()%>/profile">Profile</a></li>
				<li><a href="<%=request.getContextPath()%>/reset-password">Reset
						password</a></li>
				<li><a href="#">Help</a></li>
				<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
			</ul></li>

	</ul>
	<!-- Mobile navigation ends here -->
	<!-- Mobile notification starts here -->
	<div class="sidenav" id='notification-demo'>
		<div class="top-fix search-holder">
			<!-- mobile notification sidenav will close after clicking back-->
			<a class="sidenav-close white-text" href="#!"><i class="fa fa-arrow-left"></i> Back</a> 
			<input type="text" name="srch-term" id="srch-term-mobile"
				class="browser-default searching empty"
				placeholder="&#xF002; Search Alerts...">
			<select class="browser-default" id="alert_type_fk_mobile" name="alert_type_fk" onchange="getAlertsForHeaderNotifications(this.value);">														
				<option value="">Alert Type</option>		
				<c:forEach var="obj" items="${alertTypes }">
					<option value="${obj.alert_type_fk}">${obj.alert_type_fk}</option>	
				</c:forEach>							
			</select>
		</div>
		<ul class="notifications_group" id="notificationListMobile">
			<!-- Mobile notification body starts here -->

			<c:forEach var="obj" items="${alerts }">
				<li class="head-item">${obj.key}</li>
				<c:if test="${obj.key eq '3rd Alert'}">
					<c:set var="bgClass" value="type-3"></c:set>
				</c:if>
				<c:if test="${obj.key eq '2nd Alert'}">
					<c:set var="bgClass" value="type-2"></c:set>
				</c:if>
				<c:if test="${obj.key eq '1st Alert'}">
					<c:set var="bgClass" value="type-1"></c:set>
				</c:if>
				<c:forEach var="aObj" items="${obj.value}">

					<c:if test="${aObj.alert_type_fk eq 'Bank Guarantee'}">
						<c:set var="bgIcon"
							value="<i class='material-icons'>account_balance</i>">
						</c:set>
					</c:if>
					<c:if test="${aObj.alert_type_fk eq 'Insurance'}">
						<c:set var="bgIcon" value="<i class='material-icons'>security</i>"></c:set>
					</c:if>
					<c:if test="${aObj.alert_type_fk eq 'Contract Period'}">
						<c:set var="bgIcon"
							value="<i class='material-icons'>access_time</i>"></c:set>
					</c:if>
					<c:if test="${aObj.alert_type_fk eq 'Contract Value'}">
						<c:set var="bgIcon" value="<i class='fa fa-money'></i>"></c:set>
					</c:if>
					<c:if test="${aObj.alert_type_fk eq 'Issue'}">
						<c:set var="bgIcon" value="<i class='fa fa-exclamation-triangle'></i>"></c:set>
					</c:if>
					<c:if test="${aObj.alert_type_fk eq 'Risk'}">
						<c:set var="bgIcon" value="<i class='material-icons'>error</i>"></c:set> 										
					</c:if>
					<c:if test="${aObj.alert_type_fk eq 'Safety'}">
						<c:set var="bgIcon" value="<i class='material-icons-outlined'>verified_user</i>"></c:set>										
					</c:if>		
					<li class="item ${bgClass }"><a
						href="<%=request.getContextPath()%>${aObj.redirect_url }">
							<span class="icon"> <!-- <i class="material-icons">access_time</i> -->
								${bgIcon } <span class="icon-text">${aObj.alert_type_fk }</span>
						</span>
							<div>Work : ${aObj.work_short_name }</div>
							<div>Contract : ${aObj.contract_short_name }</div>
							<div>Contractor : ${aObj.contractor_name }</div>
							<div>Reason : ${aObj.alert_value }</div>
					</a></li>
					<!-- <li class="item type-1">
	                              <a href="#">
	                              	 <span class="icon">
	                              	 	<i class="material-icons">security</i>
	                              	 	<span class="icon-text">Insurance</span>
	                              	 </span>
	                                  <div>Work name</div>
	                                  <div>Contract Id</div>
	                                  <div>Contractor</div>
	                                  <div>Reason (Insurance)</div>
	                              </a>
	                          </li>
	                          <li class="item type-2">
	                              <a href="#">
	                              	<span class="icon">
	                              	 	<i class="material-icons">access_time</i>
	                              	 	<span class="icon-text">Contract Period</span>
	                              	 </span>
	                                  <div>Work name</div>
	                                  <div>Contract Id</div>
	                                  <div>Contractor</div>
	                                  <div>Reason </div>
	                              </a>
	                          </li>
	                          <li class="item type-3">
	                              <a href="#">
	                                  <a href="#">
	                                  <span class="icon">
	                              	 	<i class="fa fa-money"></i>
	                              	 	<span class="icon-text">Contract Value</span>
	                              	  </span>
	                                  <div>Work name</div>
	                                  <div>Contract Id</div>
	                                  <div>Contractor</div>
	                                  <div>Reason </div>
	                              </a>
	                              </a>
	                          </li>
	                           <li class="item type-1">
	                              <a href="#">
	                             	<span class="icon">
	                              	 	<i class="material-icons">account_balance</i>
	                              	 	<span class="icon-text">Bank Guarantee</span>
	                              	 </span>
	                                 <div>Work name</div>
	                                 <div>Contract Id</div>
	                                 <div>Contractor</div>
	                                 <div>Reason </div>
	                              </a>
	                          </li> -->
				</c:forEach>
			</c:forEach>
			<!-- Mobile notification body ends here -->
		</ul>
	</div>
	<!-- Mobile notification ends here -->
	<!-- Mobile messages starts here -->
	<div class="sidenav" id='messages-demo'>
		<div class="top-fix search-holder">
			<!-- mobile notification sidenav will close after clicking back-->
			<a class="sidenav-close white-text" href="#!"><i
				class="fa fa-arrow-left"></i> Back</a> 
			<input type="text"
				name="srch-term" id="messages-srch-term-mobile"
				class="browser-default searching empty"
				placeholder="&#xF002; Search Messages...">
			<select class="browser-default" id="message_type_mobile" name="message_type" onchange="getMesagesForHeader(this.value);">														
				<option value="">Message Type</option>	
				<c:forEach var="obj" items="${messageTypes }">
					<option value="${obj.message_type}">${obj.message_type}</option>	
				</c:forEach>								
			</select>
		</div>
		<ul class="notifications_group message_group" id="messagesListMobile">
			<c:if test="${not empty messages and fn:length(messages) gt 0}">
				<li class="head-item">Messages</li>
				<c:forEach var="obj" items="${messages }">
					<c:if test="${not empty obj.read_time}">
						<c:set var="message_color_bg" value="read-message"></c:set>
					</c:if>
					<c:if test="${empty obj.read_time}">
						<c:set var="message_color_bg" value="unread-message"></c:set>
					</c:if>

					<li class="item ${message_color_bg}">
					<a target="_new" href="<%=request.getContextPath()%>${obj.redirect_url}<c:if test="${fn:contains(obj.redirect_url, '?')}">&</c:if><c:if test="${not fn:contains(obj.redirect_url, '?')}">?</c:if>message_id=${obj.message_id }">
							<span class="icon"> <i class='fa fa-exclamation-triangle'></i>
								<span class="icon-text">${obj.message_type }</span>
						</span>
							<div>${obj.message }</div>
							<span class="date_text">
								<i class='fa fa-clock-o'></i> ${obj.created_date }
							</span>
					</a></li>
				</c:forEach>
			</c:if>
			<!-- Mobile notification body ends here -->
		</ul>
	</div>
	<!-- Mobile messages ends here -->
	<form action="<%=request.getContextPath()%>/set-global-variables"
		id="setGlobalVariablesForm" name="setGlobalVariablesForm"
		method="post">
		<input type="hidden" id="globalProjectId" name="globalProjectId" /> <input
			type="hidden" id="globalWorkId" name="globalWorkId" /> <input
			type="hidden" id="globalWorkName" name="globalWorkName" /> <input
			type="hidden" id="currentUrl" name="currentUrl" />
	</form>
	

	<!-- <script src="https://code.jquery.com/jquery-3.5.0.min.js" ></script> -->
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<!-- <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>  -->
	<script>
	
	/*var win = window,
    doc = document,
    docElem = doc.documentElement,
    body = doc.getElementsByTagName('body')[0],
    x = win.innerWidth || docElem.clientWidth || body.clientWidth,
    y = win.innerHeight|| docElem.clientHeight|| body.clientHeight;
	
	if(x<=400)
	{
		$(window).on("navigate", function (event, data) {
			  var direction = data.state.direction;
			  if (direction == 'back') {
			    window.history.back();
			  }
			});
		$("#imgbackBtn").show();
	}
	else
		{
			$("#imgbackBtn").hide();
		}*/
	
	let accordions = document.querySelectorAll(".accordion");
	accordions.forEach((element) => {
	  element.querySelector(".accordion__header").addEventListener("click", () => {
	    toggleAccordion(element);
	  });
	});

	function toggleAccordion(element) {
	  let isActive = element.classList.contains("active");
	  if (document.querySelectorAll(".accordion.active").length > 0) {
	    document.querySelector(".accordion.active .accordion__body").style.width =
	      document.querySelector(".accordion.active .accordion__header")
	        .offsetWidth + "px";
	    document.querySelector(".accordion.active .accordion__body").style.height = "0px";
	    document.querySelector(".accordion.active").classList.remove("active");
	  }
	  if (isActive) {
	    element.querySelector(".accordion__body").style.width = 
	      element.querySelector(".accordion__header").offsetWidth + "px";
	    element.querySelector(".accordion__body").style.height =
	      element.querySelector(".accordion__header").offsetHeight * 0 + "px";
	    element.classList.remove("active");
	  } else {
	    element.querySelector(".accordion__body").style.width =
	      element.querySelector(".accordion__inner").offsetWidth + "px";
	    element.querySelector(".accordion__body").style.height =
	      element.querySelector(".accordion__inner").offsetHeight + "0px";
	    element.classList.add("active");
	  }
	}
   			var dt=new Date();
   			//var month= dt.getMonth() >10 ? "" + dt.getMonth(): "0" + dt.getMonth(); 
   			const months = ["Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"];
   			var years=dt.getYear() >100 ? dt.getYear()-100: dt.getYear();
   			var todayDate=dt.getDate() +"-"+months[dt.getMonth()]+"-"+years;   			
   			document.getElementById("CurrentDate").innerHTML=todayDate;
   			
               $(document).ready(function () {
          	   
            	   
            	   $("a[href='/pmis/contractorslist']").click(function() {

            		   $.ajax({
                           url: "<%=request.getContextPath()%>/ajax/contractorslist",
                           	  cache: false,async:true,
    		                  success: function (data) {
    		                  }
    		                    	  
    		           });
            	   });
            	  // $('.header-select').formSelect();
                   //notification dropdown
                   $('.notification.dropdown-trigger').dropdown({
                       coverTrigger: false,
                       closeOnClick: false,
                   });

                   $("#srch-term").on("keyup", function () {
                       var value = $(this).val().toLowerCase();
                       $("#notificationList li.item").filter(function () {
                           $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                       });
                   });

                   $("#srch-term-mobile").on("keyup", function () {
                       var value = $(this).val().toLowerCase();
                       $("#notificationListMobile li.item").filter(function () {
                           $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                       });
                   });

                   $("#messages-srch-term").on("keyup", function () {
                       var value = $(this).val().toLowerCase();
                       $("#messagesList li.item").filter(function () {
                           $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                       });
                   });

                   $("#messages-srch-term-mobile").on("keyup", function () {
                       var value = $(this).val().toLowerCase();
                       $("#messagesListMobile li.item").filter(function () {
                           $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                       });
                   });
                   //initialization of material components starts
                   $('.sidenav').sidenav();
                   $('.collapsible').collapsible();
                   $("#searchProjectId").formSelect();
                   $("#mobileSearchProjectId").formSelect();

                   $("#searchWorkId").formSelect();
                   $("#mobileSearchWorkId").formSelect();
                   //initialization of material components ends

                   /* getProjectsListForSearch();
                   var globalProjectId = "${sessionScope.globalProjectId}";
                   if($.trim(globalProjectId) != ''){
                       getWorksListForSearch(globalProjectId);
                   } */

                   //notification searching
                   $('.browser-default.searching').on('keyup', function () {
                       var input = $(this);
                       if (input.val().length === 0) {
                           input.addClass('empty');
                       } else {
                           input.removeClass('empty');
                       }
                   });

                   /******************************************************************************/
                  /*  var count = 0;
                   <c:forEach var="aObj" items="${alerts }">
                       if("${aObj.value}" != null){
                           count = Number(count) + Number("${fn:length(aObj.value)}")
                       }
                   </c:forEach> 

                   $("#notificationCount").html(count);
                   $("#notificationCountMobile").html(count);*/


                   /******************************************************************************/
                   /* var msgCount = 0;
                   if ("${not empty messages && fn:length(messages) gt 0}") {
                       <c:forEach var="mObj" items="${messages }">
                           if("${mObj.read_time}" == null || "${mObj.read_time}" == ''){
                               msgCount = Number(msgCount) + 1;
							}
						</c:forEach>
                   }
                   if (msgCount > 99) {
                       msgCount = "99+"
                   }

                   $("#messagesCount").html(msgCount);
                   $("#messagesCountMobile").html(msgCount); */
                   /******************************************************************************/

                   //var link=window.location.href;
                   //var divisions=link.split('/');
                   //var formName=divisions[divisions.length-1];
                   /*  $('nav ul li a').click(function(){
                               $('li a').removeClass("menu-active");
                               $(this).closest('ul').parent().find('a').addClass("menu-active");
                    }); */
                    
                    getAlertsForHeaderNotifications('');
                    getMesagesForHeader('');
                    
               });
               
               /**********************************************************************************************************************/
               function getAlertsForHeaderNotifications(alert_type_fk) {
            	   $("#notificationList").html('');
            	   $("#notificationListMobile").html('');
            	   var myParams = {alert_type_fk : alert_type_fk};
                   $.ajax({
                       url: "<%=request.getContextPath()%>/ajax/getAlertsForHeaderNotifications",
                       	  data: myParams,cache: false,async:true,
		                  success: function (data) {
		                    	  var html = "";
		                    	  var count = 0;
		                    	  $.each(data, function(key, value) {
		                    		  html = html + '<li class="head-item">'+key+'</li>';
		                    		  var bgClass = '';
		                    		  if(key == '3rd Alert'){
		                    			  bgClass = 'type-3';
		                    		  }else if(key == '2nd Alert'){
		                    			  bgClass = 'type-2';
		                    		  }else if(key == '1st Alert'){
		                    			  bgClass = 'type-1';
		                    		  }
		                    		  count = count + Number(data[key].length);
		                    		  $.each(data[key], function (i, val) {
		                    			  var bgIcon = '';
			                    		  if(val.alert_type_fk == 'Bank Guarantee'){
			                    			  bgIcon = '<i class="material-icons">account_balance</i>';
			                    		  }else if(val.alert_type_fk == 'Insurance'){
			                    			  bgIcon = '<i class="material-icons">security</i>';
			                    		  }else if(val.alert_type_fk == 'Contract Period'){
			                    			  bgIcon = '<i class="material-icons">access_time</i>';
			                    		  }else if(val.alert_type_fk == 'Contract Value'){
			                    			  bgIcon = '<i class="fa fa-money"></i>';
			                    		  }else if(val.alert_type_fk == 'Issue'){
			                    			  bgIcon = '<i class="material-icons-outlined">warning_amber</i>';
			                    		  }else if(val.alert_type_fk == 'Risk'){
			                    			  bgIcon = '<i class="material-icons">error_outline</i>';
			                    		  }else if(val.alert_type_fk == 'Safety'){
			                    			  bgIcon = '<i class="material-icons">verified_user</i>';
			                    		  }
			                    		  else if(val.alert_type_fk == 'Execution'){
			                    			  bgIcon = '<i class="material-icons">settings</i>';
			                    		  }			                    		  
			                    		  else if(val.alert_type_fk == 'R&R'){
			                    			  bgIcon = '<i class="material-icons">location_disabled</i>';
			                    		  }	
			                    		  else if(val.alert_type_fk == 'Land Acquisition'){
			                    			  bgIcon = '<i class="material-icons">crop_landscape</i>';
			                    		  }
			                    		  else if(val.alert_type_fk == 'Contract'){
			                    			  bgIcon = '<i class="material-icons">playlist_add</i>';
			                    		  }	
			                    		  else if(val.alert_type_fk == 'Drawing'){
			                    			  bgIcon = '<i class="material-icons">draw</i>';
			                    		  }				                    		  
			                    		  else if(val.alert_type_fk == 'Utility Shifting'){
			                    			  bgIcon = '<i class="material-icons">filter_tilt_shift</i>';
			                    		  }
			                    		  else if(val.alert_type_fk == 'Contract Milestone'){
			                    			  bgIcon = '<i class="material-icons">touch_app</i>';
			                    		  }				                    		  
			                    		  
			                    		  var urlStringContains = ""
			                    		  if($.trim(val.redirect_url) != '' && (val.redirect_url).indexOf("?") > 0 && $.trim(val.alerts_user_id) != ''){
			                    			  urlStringContains = "&"+ 'alerts_user_id='+val.alerts_user_id;
			                    		  }else if($.trim(val.alerts_user_id) != ''){
			                    			  urlStringContains = "?" + 'alerts_user_id='+val.alerts_user_id
			                    		  }
			                    				  
			                    		  var read_message_bg = "";
			                    		  if($.trim(val.read_time) != ''){
			                    			  read_message_bg = " read-message";
			                    		  }
			                    		  
			                    		  html = html + '<li class="item '+bgClass + read_message_bg + ' row">'
			                    		  		+ '<a target="_new" href="<%=request.getContextPath()%>'+(val.redirect_url)+urlStringContains+'">'
			                    		  		/* +'<div class="col m2 s2 icon-holder">'
			                    		  		+ '<span class="icon"> '+bgIcon+' <span class="icon-text">'+val.alert_type_fk+'</span> </span>'
			                    		  		+'</div> <div class="col m10 s10 text-holder">'; */
			                    		  		+'<table><tbody><tr class="no-border"> <td class="alert-table-icon"><span class="icon"> '+bgIcon
			                    		  		+' <span class="icon-text">'+val.alert_type_fk+'</span> </span> </td> <td class="alert-table-content">';
			                    		  		if(val.alert_type_fk != 'Risk' && val.alert_type_fk != 'R&R' && val.alert_type_fk != 'Land Acquisition' && val.alert_type_fk != 'Utility Shifting'){
			                    		  			 html = html + '<div>Work : '+val.work_short_name +'</div>'
								                    		  		+ '<div>Contract : '+val.contract_short_name +'</div>'
								                    		  		+ '<div>Contractor : '+val.contractor_name +'</div>'
			                    		  		}
			                    		  		var label = "Reason : ";
			                    		  		if(val.alert_type_fk == 'Issue'){
			                    		  			label = 'Short Description : ';
				                    		    }else if(val.alert_type_fk == 'Risk' || val.alert_type_fk == 'R&R' || val.alert_type_fk == 'Land Acquisition'){
				                    		    	label = '';
				                    		    }
			                    		  		 
			                    		  		html = html + '<div>'+ label + val.alert_value +'</div>'+'</div>'
			                    		  		+ '</td></tr> </tbody> </table> </a></li>';
			                    		  
		                    		  });
	                    		  });
		                    	  
		                    	  $("#notificationList").html(html);
		                    	  $("#notificationListMobile").html(html);
		                    	  
		                    	  if (count > 99) {
		                    		  count = "99+";
		                          }
		                    	  
		                    	  $("#notificationCount").html(count);
		                          $("#notificationCountMobile").html(count);
		                      }
		           });                   
				}
               var messagesArray=new Array();
               
               function getMesagesForHeader(message_type) {
            	   $("#messagesList").html('');
            	   $("#messagesListMobile").html('');
            	   var myParams = {message_type : message_type};
                   $.ajax({
                       url: "<%=request.getContextPath()%>/ajax/getMesagesForHeader",
                       	  data: myParams,cache: false,async:true,
		                  success: function (data) {
		                	  messagesArray=[];
		                    	  var html = "";
		                    	  var count = 0;
		                    	  html = html + '<li class="head-item">Messages</li>';
		                    	  $.each(data, function(i, val) {
		                    		  messagesArray.push(val.message_id);
		                    		  if(data.length > 0 && i==0){
 				                    	  html = '<li class="head-item">Messages<button type="button" class="markread" id="markallread" onClick="changeReadStatus();">Mark All Read</button></li>';
 		                    		  } 
		                    		  var message_color_bg = '';
		                    		  if($.trim(val.read_time) != ''){
		                    			  message_color_bg = 'read-message';
		                    		  }else{
		                    			  message_color_bg = 'unread-message';
		                    		  }
		                    		  if($.trim(val.read_time) == ''){
		                    		  	count = count + 1;
		                    		  }
		                    		  var urlStringContains = "?"
		                    		  if($.trim(val.redirect_url) != '' && (val.redirect_url).indexOf("?") > 0){
		                    			  urlStringContains = "&";
		                    		  }
		                    		  html = html + '<li class="item '+message_color_bg+' row">';
		                    		  		if($.trim(val.redirect_url) != ''){
		                    		  			html = html + '<a target="_new" href="<%=request.getContextPath()%>'+(val.redirect_url)+urlStringContains+'message_id='+val.message_id +'">'
		                    		  		}else{
		                    		  			html = html + '<a href="javascript:void(0);" target="_new">'
		                    		  		}
		                    		  		
		                    		  		
		                    		  		//+ '<div class="col m12">'
		                    		  		html = html + '<div class="col m2" style="text-align: center;">'
		                    		  		+ '<i class="fa fa-exclamation-triangle" style="font-size:2rem;margin-top: 10px;height: 1.5rem;line-height: 1.5rem;"></i>'
		                    		  		+ '<span class="">'+val.message_type+'</span>'
		                    		  		+ '</div>'
		                    		  		+ '<div class="col m10">'
		                    		  		+ '<div>'+val.message +'</div> '
		                    		  		+ '<div style="font-size: 10px;padding:5px 0px 0px 0px;"> <i class="fa fa-clock-o"></i> &nbsp;'+val.created_date+'<span style="float:right;font-size: 10px;">'+val.timeAgo+'</span></div>'
		                    		  		+ '</div>'
		                    		  		//+ '</div>'
		                    		  				
		                    		  		/* + '<span class="icon"> <i class="fa fa-exclamation-triangle"></i> <span class="icon-text">'+val.message_type+'</span> </span>'
		                    		  		+ '<div>'+val.message +'</div>'
		                    		  		+ '<span class="date_text"><i class="fa fa-clock-o"></i>'+val.created_date+'</span>' */
		                    		  		
		                    		  		+ '</a></li>';
			                    		 
	                    		  });
		                    	  
		                    	  $("#messagesList").html(html);
		                    	  $("#messagesListMobile").html(html);
		                    	  
		                    	  if (count > 99) {
		                    		  count = "99+";
		                          }
		                    	  $("#messagesCount").html(count);
		                          $("#messagesCountMobile").html(count);
		                      }
		           });
				}
               
               /**********************************************************************************************************************/
               
               
               //getting project list from database
               function getProjectsListForSearch() {
                   $("#searchProjectId option:not(:first)").remove();
                   $("#mobileSearchProjectId option:not(:first)").remove();
                   $.ajax({
                       url: "<%=request.getContextPath()%>/ajax/getProjectsListForSearch",
		                  cache: false,
		                  success: function (data) {
		                      if (data.length > 0) {
		                          $.each(data, function (i, val) {
		                              var projectName = '';
		                              if ($.trim(val.project_name) != '') { projectName = ' - ' + $.trim(val.project_name) }
		                              var globalProjectId = "${sessionScope.globalProjectId}";
		                              if ($.trim(globalProjectId) != '' && val.project_id == $.trim(globalProjectId)) {
		                                  $("#searchProjectId").append('<option value="' + val.project_id + '" selected>' + $.trim(val.project_id) + $.trim(projectName) + '</option>');
		                                  $("#mobileSearchProjectId").append('<option value="' + val.project_id + '" selected>' + $.trim(val.project_id) + $.trim(projectName) + '</option>');
		                              } else {
		                                  $("#searchProjectId").append('<option value="' + val.project_id + '">' + $.trim(val.project_id) + $.trim(projectName) + '</option>');
		                                  $("#mobileSearchProjectId").append('<option value="' + val.project_id + '">' + $.trim(val.project_id) + $.trim(projectName) + '</option>');
		                              }
		                          });
		                      }
		                      $("#searchProjectId").formSelect();
		                      $("#mobileSearchProjectId").formSelect();
		                  }
		              });
		       }
               
               
               function changeReadStatus()
               {
            	   if($("#messages-srch-term").val()=="")
            	   {
	            	    var myParams = { message_ids:  messagesArray.toString() };
	            	   	$.ajax({
		                      url: "<%=request.getContextPath()%>/ajax/changeMessagesReadStatus",
		                      data: myParams, cache: false,
		                      success: function (data) 
		                      {
		                    	  console.log(data);
		                          getMesagesForHeader($('[name="message_type"]').val());
	
		                      }
		                  }); 
            		}
            	    else
            		{
                        var newMsgArray=new Array();

                        $("#messagesList li.item").filter(function () {
                        	if($(this).is(":visible"))
                        		{
                        			var msgValue=$("a",this).attr('href');
                        			var spltStr=msgValue.split("=");
                        			newMsgArray.push(spltStr[spltStr.length-1]);
                        
                        		}
                        });   
                        var myParams = { message_ids:  newMsgArray.toString() };
	            	   	$.ajax({
		                      url: "<%=request.getContextPath()%>/ajax/changeMessagesReadStatus",
		                      data: myParams, cache: false,
		                      success: function (data) 
		                      {
		                    	  console.log(data);
		                          getMesagesForHeader($('[name="message_type"]').val());
	
		                      }
		                  });                        
            	    	
            		}
               }
               
               
	           //getting work list from database
	           function getWorksListForSearch(projectId) {
	              $("#searchWorkId option:not(:first)").remove();
	              $("#mobileSearchWorkId option:not(:first)").remove();
	              if ($.trim(projectId) != "") {
	                  var myParams = { project_id_fk: projectId };
	                  $.ajax({
	                      url: "<%=request.getContextPath()%>/ajax/getWorksListForSearch",
	                      data: myParams, cache: false,
	                      success: function (data) {
	                          if (data.length > 0) {
	                              $.each(data, function (i, val) {
	                                  var workName = '';
	                                  if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
	                                  var globalWorkId = "${sessionScope.globalWorkId}";
	                                  if ($.trim(globalWorkId) != '' && val.work_id == $.trim(globalWorkId)) {
	                                      $("#searchWorkId").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
	                                      $("#mobileSearchWorkId").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
	                                  } else {
	                                      $("#searchWorkId").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
	                                      $("#mobileSearchWorkId").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
	                                  }
	
	                              });
	                          }
	                          $("#searchWorkId").formSelect();
	                          $("#mobileSearchWorkId").formSelect();
	                      }
	                  });
	              } else {
	                  $("#searchWorkId").formSelect();
	                  $("#mobileSearchWorkId").formSelect();
	              }
	          }
		
	          //for setting global variables from navigation bar
	          function setGlobalVariable(searchWorkId) {
	              var searchProjectId = $("#searchProjectId").val();
	              var searchWorkName = $("#searchWorkId option:selected").text();;
	              $("#globalProjectId").val(searchProjectId);
	              $("#globalWorkId").val(searchWorkId);
	              $("#globalWorkName").val(searchWorkName);
	              var url = window.location.href;
	              $("#currentUrl").val(url);
	              //alert(url)
	              $("#setGlobalVariablesForm").submit();
	          }



	          //this one is just to wait for the page to load
	          document.addEventListener('DOMContentLoaded', () => {
	              var theme = document.getElementById('theme');
	              var themeToggle = document.getElementById('theme-change');
	              themeToggle.addEventListener('click', () => {
	                  // if it's light -> go dark
	                  if (themeToggle.children[0].classList.contains("fa-moon-o")) {
	                      theme.href = '/pmis/resources/css/light-theme.css';
	                      themeToggle.style.backgroundColor = "white";
	                      themeToggle.children[0].classList.add('fa-sun-o');
	                      themeToggle.children[0].classList.remove('fa-moon-o');
	                      document.cookie = "theme=light";
	                  } else {
	                      // if it's dark -> go light
	                      theme.href = '/pmis/resources/css/dark-theme.css';
	                      themeToggle.style.backgroundColor = "black";
	                      themeToggle.children[0].classList.add('fa-moon-o');
	                      themeToggle.children[0].classList.remove('fa-sun-o');
	                      document.cookie = "theme=dark";
	                  }
	              });
	
	              //checking in the cookies which theme should load
	              var x = document.cookie;
	              if (x.includes("dark")) {
	                  theme.href = '/pmis/resources/css/dark-theme.css';
	                  themeToggle.style.backgroundColor = "black";
	                  themeToggle.children[0].classList.add('fa-moon-o');
	                  themeToggle.children[0].classList.remove('fa-sun-o');
	                  document.cookie = "theme=dark";
	              } else {
	                  theme.href = '/pmis/resources/css/light-theme.css';
	                  themeToggle.style.backgroundColor = "white";
	                  themeToggle.children[0].classList.add('fa-sun-o');
	                  themeToggle.children[0].classList.remove('fa-moon-o');
	                  document.cookie = "theme=light";
	              }
	          });
	          
	          $(document).click(function() {
	         	  if($('#dropdown1').css('display')=='block' &&	!$('a[data-target="dropdown1"]').hasClass('menu-active')	){
	        		  $('a[data-target="dropdown1"]').addClass('menu-active');
	        	  } else {
	        		  $('a[data-target="dropdown1"]').removeClass('menu-active');
	        	  }
	         	  if($('#messages1').css('display')=='block' &&	!$('a[data-target="messages1"]').hasClass('menu-active')	){
	        		  $('a[data-target="messages1"]').addClass('menu-active');
	        	  } else {
	        		  $('a[data-target="messages1"]').removeClass('menu-active');
	        	  }         	    
	          });
	
	          $(function(){
	    		  var current_page_URL = location.href; 
	    		  $( ".top-level-menu :not(.notification_body ) a" ).each(function() {
	    		     if ($(this).attr("href") !== "#") {
	    		       var target_URL = $(this).prop("href");
	    		       if (target_URL == current_page_URL) {
	    		          $('a').parents('li, ul').removeClass('menu-active');
	    					var classItem=$(this).closest('ul').parentsUntil($( "ul.top-level-menu" ));
	    					$(classItem[classItem.length-1]).addClass('menu-active');					
	    					var formName=current_page_URL.split('/');	
	    					if(formName[formName.length-1]=='home' || formName[formName.length-1]==''){
	    						$('.top-level-menu a[href="/pmis/home"]').parent().addClass('menu-active');
	    					}
	    		          return false;
	    		       }
	    		     }
	    		  });	
	    		  var formName=current_page_URL.split('/');	    		  
	    		  var gridOrForm=(formName.length > 5)? (formName[formName.length-2].split('-')[1]) : (formName[formName.length-1].split('-')[1]);
	    		  var sn=formName[formName.length-1].split('-');  		 			  
	    		  var preGridOrForm=(formName.length > 5)? (formName[formName.length-2].split('-')[0]) : (formName[formName.length-1].split('-')[0]) ;
	    		  if ( (gridOrForm != '' || gridOrForm!= undefined) && (preGridOrForm=='get' || preGridOrForm=='add') ){
	    			  var urlName='';
	    			  if(sn.length>= 3){
	    				 var lengthOfSn;
	    				  (sn[sn.length-1]=='form') ? lengthOfSn=sn.length-2 :  lengthOfSn=sn.length-1 ;
	    				  for (i=1;i<=lengthOfSn;i++){    					  
	    					  if (urlName!="") {urlName=urlName+"-";}
	    					  urlName=urlName+sn[i]
	    				  }
	    			  }
	    			  gridOrForm=urlName ?urlName : gridOrForm;
	    			  var classItem=$('.top-level-menu a[href="/pmis/'+gridOrForm+'"]').closest('ul').parentsUntil($( "ul.top-level-menu" ));
	    			  $(classItem[classItem.length-1]).addClass('menu-active')	
	    		  }
	    		 
	    	 });

	         /*  $("#tech_assist_list").click(function(){
	        	  $(this).toggleClass("active"); 
	        	  	if($(this).hasClass('active'))
	        	     	$('#tech_assist_ul').css("display","block"); 
	        	  	else
	        	  		$('#tech_assist_ul').css("display","none"); 
	        	}); */
 </script>

</body>

</html>