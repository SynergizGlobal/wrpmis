<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>   

   <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
   <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
   <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
   <style>
   	.preloader-wrapper{
        top: 45%!important;
        left:47%!important;
    }
  /* 	textarea.materialize-textarea {
	    line-height: normal;
	    overflow-y: hidden;
	    padding: .8rem 0 .8rem 0;
	    resize: none;
	    min-height: 3rem;
	    -webkit-box-sizing: border-box;
	    box-sizing: border-box;
	    max-height:100px;
   } */
   nav .menu-active,nav .menu-active.blue{
   		background-color: #f56661 !important;
   		box-shadow: 0 0 5px #bababa inset;
   		transition: all 1s ease-in;
   }
   input:not([type]),
   input[type=text]:not(.browser-default), 
   input[type=password]:not(.browser-default), 
   input[type=email]:not(.browser-default), 
   input[type=url]:not(.browser-default), 
   input[type=time]:not(.browser-default), 
   input[type=date]:not(.browser-default), 
   input[type=datetime]:not(.browser-default),
   input[type=datetime-local]:not(.browser-default),
   input[type=tel]:not(.browser-default), 
   input[type=number]:not(.browser-default), 
   input[type=search]:not(.browser-default), 
   textarea.materialize-textarea{
   		margin-bottom:0 ;
   }
   
   .issue-alert_bg{
   		background-color: #90b6c0;
   }
   
   .notifications_group .item.issue-alert_bg:hover {
	    background-color: #9ebb9f ;
	}

   </style>
   
   <link id="theme" rel="stylesheet" type="text/css" href="" />

</head>
<body>
<!-- light and dark theme shifting related code -->
 <button id="theme-change" class="theme-change"><i class="fa fa-sun-o"></i></button>

<!-- header @navigation starts here -->
  <nav>
    <div class="nav-wrapper blue darken-3">
      <div class="">
        <a href="<%=request.getContextPath() %>/home" class="brand-logo fs"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo"> MRVC PMIS</a>
        <a href="<%=request.getContextPath() %>/home" data-target="mobile-demo" class="sidenav-trigger"><i class="fa fa-bars"></i></a>
        <ul class="right hide-on-med-and-down top-level-menu">
          <%-- <c:if test="${homeHeader ne 'yes'}">
          <li class="searchproject">
            <select id="searchProjectId" name="searchProjectId" onchange="getWorksListForSearch(this.value);" class="customDropdown">
              <option>Select Project</option>
            </select>
          </li>
          <li class="searchwork">
            <select id="searchWorkId" name="searchWorkId" onchange="setGlobalVariable(this.value);">
              <option>Select Work</option>
            </select>
          </li>
          </c:if> --%>
          <li class="blue darken-3"><a href="<%=request.getContextPath() %>/home"><span class="material-icons-outlined">home</span>Home</a></li>
          <c:if test="${sessionScope.USER_ROLE_NAME ne 'Input User' }">
	          <li class="blue darken-2 dropdown"><a href="#" class='head-img'>
		          <!-- 1st level Dropdown starts -->
		          <!-- img src="/pmis/resources/images/dashboard-white.png"--> 
		          <span class="material-icons-outlined">dashboard</span> Modules</a>
	          	  <ul class="second-level-menu">
	                  <c:forEach var="category" items="${dashboardModulesList }" varStatus="index">
	           			<c:set var="tempactivity" value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
		              	<c:set var="activity" value="${ fn:toLowerCase(tempactivity.replaceAll(' ', '-'))}"></c:set>
		              	<c:if test="${empty category.tableauSubList}">
			              	<li>
			              		<a href="<%=request.getContextPath()%>/InfoViz/${activity }">
					 				<span style="padding-right: 5px;" class="fa fa-${category.imagePath}"></span>
					 				<span class="nav-label">${category.tableauDashboardName }</span>
					 			</a>
					 		</li>
				 		</c:if>
				 		<c:if test="${not empty  category.tableauSubList}">
					 		<li class="sub-menu">
					 		 <!-- 2nd level Dropdown starts -->
		                         <a href="#!">
									<span style="padding-right: 5px;" class="fa fa-${category.imagePath}"></span>
					 				<span class="nav-label">${category.tableauDashboardName }</span>
								 </a>
		                         <ul class="third-level-menu">
		                             <c:forEach var="subList" items="${category.tableauSubList }">
					           			<c:set var="tempsubActivity" value="${ fn:toLowerCase(subList.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
	            						<c:set var="subActivity" value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}"></c:set>
					           			<c:set var="subActivityName" value="${ subList.tableauDashboardName}"></c:set>
				           				<li>
	      									<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
	      										<span style="padding-right: 5px;" class="fa fa-${subList.imagePath }"></span>
	      										<span class="nav-label">${subList.tableauDashboardName }</span>
	          								</a>
	              						</li>
					           		</c:forEach> 					           		
		                         </ul>
		                     </li>
		                      <!-- 2nd level Dropdown ends -->
	                     </c:if>
	                            
		              </c:forEach>
	              </ul>
	           	  <!-- 1st level Dropdown ends -->
	          </li>
	          
	          <li class="blue darken-1 dropdown"><a href="#" class='head-img'>
		          <span class="material-icons-outlined">dashboard</span> Works</a>
	          	  <ul class="second-level-menu">
	                  <c:forEach var="category" items="${dashboardProjectsList }" varStatus="index">
	           			<c:set var="tempactivity" value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
		              	<c:set var="activity" value="${ fn:toLowerCase(tempactivity.replaceAll(' ', '-'))}"></c:set>
		              	<c:if test="${empty category.tableauSubList}">
			              	<li>
			              		<a href="<%=request.getContextPath()%>/InfoViz/${activity }">
					 				<span style="padding-right: 5px;" class="fa fa-${category.imagePath}"></span>
					 				<span class="nav-label">${category.tableauDashboardName }</span>
					 			</a>
					 		</li>
				 		</c:if>
				 		<c:if test="${not empty  category.tableauSubList}">
					 		<li class="sub-menu">
		                         <a href="#!">
									<span style="padding-right: 5px;" class="fa fa-${category.imagePath}"></span>
					 				<span class="nav-label">${category.tableauDashboardName }</span>
								 </a>
		                         <ul class="third-level-menu">
		                             <c:forEach var="subList" items="${category.tableauSubList }">
					           			<c:set var="tempsubActivity" value="${ fn:toLowerCase(subList.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
	            						<c:set var="subActivity" value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}"></c:set>
					           			<c:set var="subActivityName" value="${ subList.tableauDashboardName}"></c:set>
				           				<li>
	      									<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
	      										<span style="padding-right: 5px;" class="fa fa-${subList.imagePath }"></span>
	      										<span class="nav-label">${subList.tableauDashboardName }</span>
	          								</a>
	              						</li>
					           		</c:forEach> 
		                         </ul>
		                     </li>
	                     </c:if>
		              </c:forEach>
	              </ul>              
	          </li>          
          </c:if>
          
          <c:if test="${sessionScope.USER_ROLE_NAME ne 'Super User' }">
	          <li class="blue dropdown">
	          	<a href="#" class='head-img'>
	               <span class="material-icons-outlined">post_add</span>
	          		Update Forms</a>
	          	  <%-- <ul class="second-level-menu">
	                  <c:forEach var="form" items="${forms }">
	                  	<li><a href="${form.webFormUrl }">${form.formName }</a></li>
	               	  </c:forEach>
	              </ul> --%>
	              
	              
	              <ul class="second-level-menu">
	              <!-- 1st level Dropdown starts -->
	                  <c:forEach var="form" items="${forms }" varStatus="index">
	           			<c:if test="${empty form.formsSubMenu}">
			              	<li>
			              	<c:if test="${not empty form.webFormUrl}">
			              		<a href="<%=request.getContextPath()%>/${form.webFormUrl }">
					 				<span class="nav-label">${form.formName }</span>
					 			</a>
					 		</c:if>
					 		</li>
				 		</c:if>
				 		<c:if test="${not empty form.formsSubMenu}">
					 		<li class="sub-menu">
		                         <a href="#!">
									<span class="nav-label">${form.formName }</span>
								 </a>
		                         <ul class="third-level-menu">
		                         <!-- 2nd level Dropdown starts -->
		                             <c:forEach var="subList" items="${form.formsSubMenu }">
					           			<!-- <li>
	      									<a href="${subList.webFormUrl }">
	      										<span class="nav-label">${subList.formName }</span>
	          								</a>
	              						</li> -->
		                             <c:if test="${ empty subList.formsSubMenuLevel2}">
		                             	<li>
	      									<a href="<%=request.getContextPath()%>/${subList.webFormUrl }">
	      										<span class="nav-label">${subList.formName }</span>
	          								</a>
	              						</li>
		                             </c:if>
		                               <c:if test="${not empty subList.formsSubMenuLevel2}">
		                             	<li class="sub-menu">
					           			<a href="#"> ${subList.formName }</a>
					           			<ul class="fourth-level-menu">
					           				<c:forEach var="subListLevel2" items="${subList.formsSubMenuLevel2}">
					           					<li> 
					           						<a href="<%=request.getContextPath()%>/${subListLevel2.webFormUrl }"> ${subListLevel2.formName }	  	</a>
					           					</li>
					           				</c:forEach>
					           			</ul>
					           		</li>
		                             </c:if>
					           		</c:forEach> 
					           		<!-- start delete from here after the fourth level menu implemented -->
					           		
					           		<!-- start delete upto here after the fourth level menu implemented -->
					           		<!-- 2nd level Dropdown ends -->
		                         </ul>
		                     </li>
	                     </c:if>
	                            
		              </c:forEach>
		              <!-- 1st level Dropdown ends -->
	              </ul>
	              
	          </li>          
          </c:if>
          
      <c:if test="${not empty reportForms}">   
          <li class="blue lighten-1 dropdown">
          	<a href="#" class='head-img'>
               <span class="material-icons-outlined">assignment</span>
          		Reports</a>
              <ul class="second-level-menu">
              <!-- 1st level Dropdown starts -->
                  <c:forEach var="form" items="${reportForms }" varStatus="index">
           			<c:if test="${empty form.formsSubMenu}">
		              	<li>
		              		<a href="<%=request.getContextPath()%>/${form.webFormUrl }">
				 				<span class="nav-label">${form.formName }</span>
				 			</a>
				 		</li>
			 		</c:if>
			 		<c:if test="${not empty form.formsSubMenu}">
				 		<li class="sub-menu">
	                         <a href="#!">
								<span class="nav-label">${form.formName }</span>
							 </a>
	                         <ul class="third-level-menu">
	                         <!-- 2nd level Dropdown starts -->
	                             <c:forEach var="subList" items="${form.formsSubMenu }">
				           			<li>
      									<a href="<%=request.getContextPath()%>/${subList.webFormUrl }">
      										<span class="nav-label">${subList.formName }</span>
          								</a>
              						</li>
				           		</c:forEach> 
				           		<!-- 2nd level Dropdown ends -->
	                         </ul>
	                     </li>
                     </c:if>
                            
	              </c:forEach>
	              <!-- 1st level Dropdown ends -->
              </ul>
              
          </li>
          </c:if>
          <%-- <li class="blue"><a href="<%=request.getContextPath()%>/dpr" class='head-img'>
	          <span class="material-icons-outlined">assignment</span> Reports</a>
          </li> --%>
          
          <%-- <li class="blue"><a href="<%=request.getContextPath()%>/manuals" class='head-img'>
	          <span class="material-icons-outlined">assignment</span> Manuals</a>
          </li> --%>
           <li class="blue darken-2"><a href="javascript:void(0);" class='head-img'>
	          <span class="material-icons-outlined">description</span> Documents</a>
	          <ul class="second-level-menu">
		          <c:forEach var="obj" items="${webDocumentTypes}">
		          		<c:set var="tempWebDocType" value="${ fn:toLowerCase(obj.type.replaceAll(' - ', '_'))}"></c:set>
         				<c:set var="webDocType" value="${ fn:toLowerCase(tempWebDocType.replaceAll(' ', '-'))}"></c:set>
		              	<li>
		              		<a href="<%=request.getContextPath()%>/web-documents/${webDocType}">
				 				<span class="nav-label">${obj.type}</span>
				 			</a>
				 		</li>
			 	  </c:forEach>
		 	  </ul>
          </li>
         <%--<li class="blue"><a href="<%=request.getContextPath()%>/web-links" class='head-img'>
	          <span class="material-icons-outlined">link</span> Quick Links</a>
          </li>--%>
           <li class="blue darken-1"><a href="javascript:void(0);" class='head-img'>
	          <span class="material-icons-outlined">link</span>Quick Links</a>
	          <ul class="second-level-menu">	
	          <c:forEach var="lObj" items="${webLinksList }">
	          	<li>
				    <a href="${lObj.link }" target="_blank">
				       <span class="nav-label">${lObj.name}</span>
				   </a>
				</li>
	          </c:forEach>
		 	 </ul>
          </li> 
          <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' }"> 
            <li class="blue"><a href="javascript:void(0);" class='head-img'>
          		<span class="material-icons-outlined">list_alt</span>Admin</a>
		         <ul class="second-level-menu">
		        <c:forEach var="form" items="${adminForms }" varStatus="index">
         			<%-- <c:if test="${empty form.formsSubMenu}"> --%>
		              	<li>
		              		<a href="<%=request.getContextPath()%>/${form.url }">
				 				<span class="nav-label">${form.form_name }</span>
				 			</a>
				 		</li>
			 		<%-- </c:if> --%>
             </c:forEach>
			 	 </ul>
         	</li>
         </c:if> 
          
          <%-- <li class="blue lighten-1"><a href="javascript:void(0);" class='head-img notification' id="notification">
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
			          		<li class="item" style="cursor: pointer;" onclick="openActivityUpdateForm('${aObj.taskId}','${aObj.workId}');">${aObj.location } / ${aObj.activityType } / ${aObj.taskName }</li>
			          	  </c:forEach>
                       </ul>
                   </div>
              
          </li> --%>
          
          <li class="blue lighten-1">
          <!-- Notification code starts -->
          		<a class='notification dropdown-trigger' data-target='dropdown1'>
                  <span class="material-icons-outlined">notifications</span>
                  <span class="badge red" id="notificationCount">
                  </span>
                </a>
                <div class="notification_body dropdown-content" id='dropdown1'>
                  <!-- Notification dropdown body starts -->
                      <div>
                          <input type="text"  name="srch-term" id="srch-term" class="browser-default searching empty"placeholder="&#xF002; Search Alerts...">
                      </div>
                      <%-- <ul class="notifications_group" style="margin-top: 5px;" id="notificationList">
                       <!-- list of Notifications starts -->
                      	<c:forEach var="aObj" items="${dueActivities }">
                          <li class="head-item">${aObj.workId} - ${aObj.workName}</li>
                          <c:forEach var="bObj" items="${aObj.dueActivities }">
	                          <li class="item <c:if test="${bObj.readUnreadStatus eq 'Read' }">read</c:if>">
	                              <a href="${bObj.notificationLink}">
	                                  <span><i class="fa fa-${bObj.image }"></i> ${bObj.location } / ${bObj.activityType } / ${bObj.activity } / ${bObj.boqCompleteTotal }</span>
	                                  <div><i class="fa fa-clock-o"></i> <span class="time">${bObj.timeAgo}</span></div>
	                              </a>
	                              <a href="${bObj.notificationLink}">
	                                  <span><i class="fa fa-edit"></i> ${bObj.description}</span>
	                                  <div><i class="fa fa-clock-o"></i> <span class="time">${bObj.timeAgo}</span></div>
	                              </a>
	                          </li>
                          </c:forEach>
                                 <!-- list of Notifications ends -->
                        </c:forEach>
                      </ul> --%>
                      
                       <ul class="notifications_group" style="margin-top: 5px;" id="notificationList">
                       <!-- list of Notifications starts -->
                      <c:if test="${not empty issueAlerts and fn:length(issueAlerts) gt 0}">
	                       <li class="head-item">Issue Alerts</li>
	                       <c:forEach var="obj" items="${issueAlerts }">
	                          <li class="item issue-alert_bg">
	                              <a href="<%=request.getContextPath()%>/get-issue/${obj.issue_id }">
	                              	 <span class="icon">
	                              	 	<i class='fa fa-exclamation-triangle'></i>
	                              	 	<span class="icon-text">Issue</span>
	                              	 </span>                                   
	                                 <div>Work : ${obj.work_short_name }</div>
	                                 <div>Contract : ${obj.contract_short_name }</div>
	                                 <div>Contractor : ${obj.contractor_name }</div>
	                                 <div>Issue <b>${obj.status_fk }</b>: ${obj.title }</div>
	                              </a>
	                          </li>
	                      </c:forEach>
                       </c:if>
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
	                          	<c:set var="bgIcon" value="<i class='material-icons'>account_balance</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Insurance'}">
	                          	<c:set var="bgIcon" value="<i class='material-icons'>security</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Contract Period'}">
	                          	<c:set var="bgIcon" value="<i class='material-icons'>access_time</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Contract Value'}">
	                          	<c:set var="bgIcon" value="<i class='fa fa-money'></i>"></c:set>
	                          </c:if>
                          	  
                          	  <li class="item ${bgClass }">
	                              <a href="<%=request.getContextPath()%>/get-contract/${aObj.contract_id }">
	                              	<span class="icon">
	                              	 	<!-- <i class="material-icons">access_time</i> -->
	                              	 	${bgIcon }
	                              	 	<span class="icon-text">${aObj.alert_type_fk }</span>
	                              	 </span>                                   
	                                 <div>Work : ${aObj.work_short_name }</div>
	                                 <div>Contract : ${aObj.contract_short_name }</div>
	                                 <div>Contractor : ${aObj.contractor_name }</div>
	                                 <div>Reason : ${aObj.alert_value }</div>
	                              </a>
	                          </li>
	                         
                          </c:forEach>
                      </c:forEach>
                      </ul>
                      
                      <!-- Notification dropdown body ends -->
                </div>
          </li>
                    
                    
          <li class="blueblue lighten-1 dropdown"><a href="#" class='head-img'>
          <img src="<%=CommonConstants2.USER_IMAGES %>${sessionScope.user.user_image}" class="profile-img" onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';"/> 
<!--           <span class="material-icons">person</span> -->
          ${USER_NAME }<c:if test="${empty USER_NAME }">${USER_ID}</c:if></a>
          <!-- change password and logout here -->
              <ul class="second-level-menu rs">
              	  <li><a href="<%=request.getContextPath()%>/profile">Profile</a></li>	
                  <li><a href="<%=request.getContextPath()%>/reset-password">Reset password</a></li>
                  <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
              </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
<!-- header @navigation ends here -->

<!-- Mobile navigation stars here -->
  <ul class="sidenav collapsible" id="mobile-demo">
  <li style="background-color:#dfdfdf"> 
  <!-- Mobile navigation close button -->
  	<div class="right-align head">
  		<!-- a class="sidenav-close" href="#!"><i class="fa fa-close sidenav-close"></i></a-->
  		<i class="fa fa-close sidenav-close"></i>
  	</div>
  </li>
  
    <!-- <li>
      <select id="mobileSearchProjectId" name="searchProjectId" onchange="getWorksListForSearch(this.value);">
        <option>Select Project</option>
      </select>
    </li>
    <li>

      <select id="mobileSearchWorkId" name="searchWorkId">
        <option>Select Work</option>
      </select>
    </li> -->	
    <li><a href="<%=request.getContextPath() %>/home"><span class="material-icons-outlined">home</span>Home</a></li>
    <c:if test="${sessionScope.USER_ROLE_NAME ne 'Input User' }">			
    	<li class="sub-menu"><a href="#" class='head-img collapsible-header'><span class="material-icons-outlined">dashboard</span> Modules</a>
			<!-- Mobile dropdown stars here -->
          <ul class="dropdown-data collapsible collapsible-body second-lvl">         
                  <c:forEach var="category" items="${dashboardModulesList }" varStatus="index">
           			<c:set var="tempactivity" value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
	              	<c:set var="activity" value="${ fn:toLowerCase(tempactivity.replaceAll(' ', '-'))}"></c:set>
	              	<c:if test="${empty category.tableauSubList}">
		              	<li>
		              		<a href="<%=request.getContextPath()%>/InfoViz/${activity }">
				 				<span style="padding-right: 5px;" class="fa fa-${category.imagePath}"></span>
				 				<span class="nav-label">${category.tableauDashboardName }</span>
				 			</a>
				 		</li>
			 		</c:if>
			 		<c:if test="${not empty  category.tableauSubList}">
	                         <!-- Mobile 2nd level dropdown stars here -->	                         
				 		<li class="sub-menu">
	                         <a href="#!" class="collapsible-header">
								<span style="padding-right: 5px;" class="fa fa-${category.imagePath}"></span>
				 				<span class="nav-label">${category.tableauDashboardName }</span>
							 </a>
							
	                         <ul class=" dropdown-data collapsible-body third-lvl">
	                             <c:forEach var="subList" items="${category.tableauSubList }">
				           			<c:set var="tempsubActivity" value="${ fn:toLowerCase(subList.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
            						<c:set var="subActivity" value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}"></c:set>
				           			<c:set var="subActivityName" value="${ subList.tableauDashboardName}"></c:set>
			           				<li>
      									<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
      										<span style="padding-right: 5px;" class="fa fa-${subList.imagePath }"></span>
      										<span class="nav-label">${subList.tableauDashboardName }</span>
          								</a>
              						</li>
				           		</c:forEach> 
	                         </ul>
	                         
	                     </li>
	                     	                         <!-- Mobile 2nd level dropdown ends here -->	                         
                     </c:if>
                            
	              </c:forEach>
	              <!-- Mobile dropdown ends here -->
	              
              </ul>
    	</li>
    
    	<li class="sub-menu"><a href="#" class='head-img collapsible-header'><span class="material-icons-outlined">dashboard</span> Works</a>
			<!-- Mobile dropdown stars here -->
          	<ul class="dropdown-data collapsible collapsible-body second-lvl">         
                  <c:forEach var="category" items="${dashboardProjectsList }" varStatus="index">
           			<c:set var="tempactivity" value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
	              	<c:set var="activity" value="${ fn:toLowerCase(tempactivity.replaceAll(' ', '-'))}"></c:set>
	              	<c:if test="${empty category.tableauSubList}">
		              	<li>
		              		<a href="<%=request.getContextPath()%>/InfoViz/${activity }">
				 				<span style="padding-right: 5px;" class="fa fa-${category.imagePath}"></span>
				 				<span class="nav-label">${category.tableauDashboardName }</span>
				 			</a>
				 		</li>
			 		</c:if>
			 		<c:if test="${not empty  category.tableauSubList}">
	                         <!-- Mobile 2nd level dropdown stars here -->	                         
				 		<li class="sub-menu">
	                         <a href="#!" class="collapsible-header">
								<span style="padding-right: 5px;" class="fa fa-${category.imagePath}"></span>
				 				<span class="nav-label">${category.tableauDashboardName }</span>
							 </a>
							
	                         <ul class=" dropdown-data collapsible-body third-lvl">
	                             <c:forEach var="subList" items="${category.tableauSubList }">
				           			<c:set var="tempsubActivity" value="${ fn:toLowerCase(subList.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
            						<c:set var="subActivity" value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}"></c:set>
				           			<c:set var="subActivityName" value="${ subList.tableauDashboardName}"></c:set>
			           				<li>
      									<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
      										<span style="padding-right: 5px;" class="fa fa-${subList.imagePath }"></span>
      										<span class="nav-label">${subList.tableauDashboardName }</span>
          								</a>
              						</li>
				           		</c:forEach> 
	                         </ul>
	                         
	                     </li>
	                     	                         <!-- Mobile 2nd level dropdown ends here -->	                         
                     </c:if>
                            
	              </c:forEach>
	              <!-- Mobile dropdown ends here -->
	              
              </ul>
    	</li>
    </c:if>
    <c:if test="${sessionScope.USER_ROLE_NAME ne 'Super User' }">
	<li class="sub-menu"><a href="#" class='head-img collapsible-header'><span class="material-icons-outlined">post_add</span> Update Forms</a>
    	<ul class="dropdown-data collapsible-body second-lvl collapsible">          	  
          	  <c:forEach var="form" items="${forms }" varStatus="index">
         			<c:if test="${empty form.formsSubMenu}">
		              	<li>
		              	  <c:if test="${not empty form.webFormUrl}">
		              		<a href="<%=request.getContextPath()%>/${form.webFormUrl }">
				 				<span class="nav-label">${form.formName }</span>
				 			</a>
				 		  </c:if>
				 		</li>
			 		</c:if>
			 		<c:if test="${not empty form.formsSubMenu}">
				 		<li class="sub-menu">
	                        <a href="#!" class="collapsible-header">
							<span class="nav-label">${form.formName }</span>
						 </a>
	                        <ul class="dropdown-data collapsible-body third-lvl">
	                            <c:forEach var="subList" items="${form.formsSubMenu }">
			           			<li>
	    									<a href="<%=request.getContextPath()%>/${subList.webFormUrl }">
	    										<span class="nav-label">${subList.formName }</span>
	        								</a>
	            						</li>
			           		</c:forEach> 
	                        </ul>
	                    </li>
                   </c:if>
                          
             </c:forEach>
	              
         </ul>
    </li>
    </c:if>
    
    <%-- <li><a href="<%=request.getContextPath()%>/dpr" class='head-img'><span class="material-icons-outlined">assignment</span> Reports</a></li> --%>
    
   <c:if test="${not empty reportForms}"> 
    <li class="sub-menu"><a href="#" class='head-img collapsible-header'><span class="material-icons-outlined">assignment</span> Reports</a>
    	<ul class="dropdown-data collapsible-body second-lvl collapsible">          	  
          	  <c:forEach var="form" items="${reportForms }" varStatus="index">
         			<c:if test="${empty form.formsSubMenu}">
		              	<li>
		              		<a href="<%=request.getContextPath()%>/${form.webFormUrl }">
				 				<span class="nav-label">${form.formName }</span>
				 			</a>
				 		</li>
			 		</c:if>
			 		<c:if test="${not empty form.formsSubMenu}">
				 		<li class="sub-menu">
	                        <a href="#!" class="collapsible-header">
							<span class="nav-label">${form.formName }</span>
						 </a>
	                        <ul class="dropdown-data collapsible-body third-lvl">
	                            <c:forEach var="subList" items="${form.formsSubMenu }">
			           			<li>
	    									<a href="<%=request.getContextPath()%>/${subList.webFormUrl }">
	    										<span class="nav-label">${subList.formName }</span>
	        								</a>
	            						</li>
			           		</c:forEach> 
	                        </ul>
	                    </li>
                   </c:if>
                          
             </c:forEach>
	              
         </ul>
    </li>
   </c:if>
    
    <%-- <li><a href="<%=request.getContextPath()%>/manuals" class='head-img'><span class="material-icons-outlined">assignment</span> Manuals</a></li> --%>
     <li class="sub-menu"><a href="#" class='head-img collapsible-header'>
	          <span class="material-icons-outlined">description</span> Documents</a>
	          <ul class="dropdown-data collapsible-body second-lvl collapsible">
		 		<c:forEach var="obj" items="${webDocumentTypes}">
	          		<c:set var="tempWebDocType" value="${ fn:toLowerCase(obj.type.replaceAll(' - ', '_'))}"></c:set>
        				<c:set var="webDocType" value="${ fn:toLowerCase(tempWebDocType.replaceAll(' ', '-'))}"></c:set>
	              	<li>
	              		<a href="<%=request.getContextPath()%>/web-documents/${webDocType}">
			 				<span class="nav-label">${obj.type}</span>
			 			</a>
			 		</li>
			 	</c:forEach>
		 	  </ul>
          </li>
          
        <%--  <li class="blue"><a href="<%=request.getContextPath()%>/web-links" class='head-img'>
	          <span class="material-icons-outlined">link</span> Quick Links</a>
          </li> --%>
          
            <li class="sub-menu"><a href="javascript:void(0);" class='head-img collapsible-header'>
	          <span class="material-icons-outlined">link</span>Quick Links</a>
	          <ul class="dropdown-data collapsible-body second-lvl collapsible">
	              <c:forEach var="lObj" items="${webLinksList }">
		          	<li>
					    <a href="${lObj.link }" target="_blank">
					       <span class="nav-label">${lObj.name}</span>
					   </a>
					</li>
		          </c:forEach>
		 	</ul>
          </li> 
           <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' }"> 
            <li class="sub-menu"><a href="#" class='head-img collapsible-header'>
	          <span class="material-icons-outlined">list_alt</span>Admin</a>
	          <ul class="dropdown-data collapsible-body second-lvl collapsible">
		 		  <c:forEach var="form" items="${adminForms }" varStatus="index">
         			<%-- <c:if test="${empty form.formsSubMenu}"> --%>
		              	<li>
		              		<a href="<%=request.getContextPath()%>/${form.url }">
				 				<span class="nav-label">${form.form_name }</span>
				 			</a>
				 		</li>
			 		<%-- </c:if> --%>
             </c:forEach>
		 	  </ul>
          </li>
         </c:if>
    <li>
   		<!-- a class='dropdown-trigger' data-target='dropdown1'-->
   		<a href="<%=request.getContextPath() %>/home" data-target="notification-demo" class="sidenav-trigger">
           <span class="material-icons-outlined">notifications</span> Notifications
           <span class="badge" id="notificationCountMobile"></span> 
           <!--span class="badge red" id="notificationCount"></span-->
         </a>
              
    </li>   
    <li class="sub-menu"><a href="#" class='head-img collapsible-header'>
    	<img src="<%=CommonConstants2.USER_IMAGES %>${sessionScope.user.user_image }" class="profile-img" onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';"> 
		<!--     <span class="material-icons">person</span>  -->
   		${USER_NAME }<c:if test="${empty USER_NAME }">${USER_ID}</c:if></a>
    	<ul class="dropdown-data collapsible-body second-lvl">
    		<li><a href="<%=request.getContextPath()%>/profile">Profile</a></li>	
            <li><a href="<%=request.getContextPath()%>/reset-password">Reset password</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
        </ul>
    </li>
     
  </ul>
  <!-- Mobile navigation ends here -->
 <!-- Mobile notification starts here -->  
    <div class="sidenav" id='notification-demo'>
                      <div class="top-fix">
                       <!-- mobile notification sidenav will close after clicking back-->
                       <a class="sidenav-close white-text" href="#!"><i class="fa fa-arrow-left"></i> Back</a>
                          <input type="text"  name="srch-term" id="srch-term-mobile" class="browser-default searching empty"placeholder="&#xF002; Search Alerts...">
                      </div>
                      <ul class="notifications_group" style="margin-top: 5px;" id="notificationListMobile">
                       <!-- Mobile notification body starts here -->
                       <c:if test="${not empty issueAlerts and fn:length(issueAlerts) gt 0}">
	                       <li class="head-item">Issue Alerts</li>
	                       <c:forEach var="obj" items="${issueAlerts }">
	                          <li class="item" style="background-color: #90b6c0;">
	                              <a href="<%=request.getContextPath()%>/get-issue/${obj.issue_id }">
	                              	 <span class="icon">
	                              	 	<i class='fa fa-exclamation-triangle'></i>
	                              	 	<span class="icon-text">Issue</span>
	                              	 </span>                                   
	                                 <div>Work : ${obj.work_short_name }</div>
	                                 <div>Contract : ${obj.contract_short_name }</div>
	                                 <div>Contractor : ${obj.contractor_name }</div>
	                                 <div>Issue <b>${obj.status_fk }</b>: ${obj.title }</div>
	                              </a>
	                          </li>
	                       </c:forEach>
                       </c:if>
                      
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
	                          	<c:set var="bgIcon" value="<i class='material-icons'>account_balance</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Insurance'}">
	                          	<c:set var="bgIcon" value="<i class='material-icons'>security</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Contract Period'}">
	                          	<c:set var="bgIcon" value="<i class='material-icons'>access_time</i>"></c:set>
	                          </c:if>
	                          <c:if test="${aObj.alert_type_fk eq 'Contract Value'}">
	                          	<c:set var="bgIcon" value="<i class='fa fa-money'></i>"></c:set>
	                          </c:if>
                          	  
                          	  <li class="item ${bgClass }">
	                              <a href="<%=request.getContextPath()%>/get-contract/${aObj.contract_id }">
	                              	<span class="icon">
	                              	 	<!-- <i class="material-icons">access_time</i> -->
	                              	 	${bgIcon }
	                              	 	<span class="icon-text">${aObj.alert_type_fk }</span>
	                              	 </span>                                   
	                                 <div>Work : ${aObj.work_short_name }</div>
	                                 <div>Contract : ${aObj.contract_short_name }</div>
	                                 <div>Contractor : ${aObj.contractor_name }</div>
	                                 <div>Reason : ${aObj.alert_value }</div>
	                              </a>
	                          </li>
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
  <form action="<%=request.getContextPath()%>/set-global-variables" id="setGlobalVariablesForm" name="setGlobalVariablesForm" method="post">
  	<input type="hidden" id="globalProjectId" name="globalProjectId"/>
  	<input type="hidden" id="globalWorkId" name="globalWorkId"/>
  	<input type="hidden" id="globalWorkName" name="globalWorkName"/>
  	<input type="hidden" id="currentUrl" name="currentUrl"/>
  </form>
  
  <!-- <script src="https://code.jquery.com/jquery-3.5.0.min.js" ></script> -->
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script>
	$(document).ready(function(){
		//notification dropdown
		$('.notification.dropdown-trigger').dropdown({
            coverTrigger: false,
            closeOnClick: false,
        });
		
		$("#srch-term").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#notificationList li.item").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		});
		
		
		$("#srch-term-mobile").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#notificationListMobile li.item").filter(function() {
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
        var count = 0;
        <c:forEach var="aObj" items="${alerts }">
        	if("${aObj.value}" != null){
        		count = Number(count) + Number("${fn:length(aObj.value)}")
        	}
        </c:forEach>
        
       
        if("${not empty issueAlerts && fn:length(issueAlerts) gt 0}"){
        	count = Number(count) + Number("${fn:length(issueAlerts)}")
        }
        
        
        
		if(count > 99){
			count = "99+"
		}
    	$("#notificationCount").html(count);
    	$("#notificationCountMobile").html(count);
    	
    	//var link=window.location.href;
    	//var divisions=link.split('/');
    	//var formName=divisions[divisions.length-1];
    	/*  $('nav ul li a').click(function(){
   		    $('li a').removeClass("menu-active");
   		    $(this).closest('ul').parent().find('a').addClass("menu-active");
    	 }); */
	});
	//getting project list from database
	function getProjectsListForSearch(){
		$("#searchProjectId option:not(:first)").remove();
		$("#mobileSearchProjectId option:not(:first)").remove();		
		$.ajax({
			url:"<%=request.getContextPath()%>/ajax/getProjectsListForSearch",
			cache: false,
			success:function(data){
				if(data.length > 0){
					 $.each(data, function(i,val) {
						var projectName = '';
						if($.trim(val.project_name) != ''){projectName = ' - ' + $.trim(val.project_name)}
						var globalProjectId = "${sessionScope.globalProjectId}";
						if($.trim(globalProjectId) != '' && val.project_id == $.trim(globalProjectId)){
							$("#searchProjectId").append('<option value="'+val.project_id+'" selected>'+$.trim(val.project_id) + $.trim(projectName)+'</option>');
				            $("#mobileSearchProjectId").append('<option value="'+val.project_id+'" selected>'+$.trim(val.project_id) + $.trim(projectName)+'</option>');
						}else{
				            $("#searchProjectId").append('<option value="'+val.project_id+'">'+$.trim(val.project_id) + $.trim(projectName)+'</option>');
				            $("#mobileSearchProjectId").append('<option value="'+val.project_id+'">'+$.trim(val.project_id) + $.trim(projectName)+'</option>');
						}
			         });		
	 			}	
				$("#searchProjectId").formSelect();
				$("#mobileSearchProjectId").formSelect();
			}	 
		});
	}
	//getting work list from database
	function getWorksListForSearch(projectId){
		$("#searchWorkId option:not(:first)").remove();
		$("#mobileSearchWorkId option:not(:first)").remove();
		if($.trim(projectId) != ""){
			var myParams = {project_id_fk : projectId}; 
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/getWorksListForSearch",
				data:myParams,cache: false,
				success:function(data){
					if(data.length > 0){
						 $.each(data, function(i,val) {
							var workName = '';
							if($.trim(val.work_name) != ''){workName = ' - ' + $.trim(val.work_name)}
							var globalWorkId = "${sessionScope.globalWorkId}";
							if($.trim(globalWorkId) != '' && val.work_id == $.trim(globalWorkId)){
								$("#searchWorkId").append('<option value="'+val.work_id+'" selected>'+$.trim(val.work_id) + $.trim(workName)+'</option>');
					            $("#mobileSearchWorkId").append('<option value="'+val.work_id+'" selected>'+$.trim(val.work_id) + $.trim(workName)+'</option>');
							}else{
								$("#searchWorkId").append('<option value="'+val.work_id+'">'+$.trim(val.work_id) + $.trim(workName)+'</option>');
					            $("#mobileSearchWorkId").append('<option value="'+val.work_id+'">'+$.trim(val.work_id) + $.trim(workName)+'</option>');
							}
				            
				         });		
		 			}	
					$("#searchWorkId").formSelect();
					$("#mobileSearchWorkId").formSelect();
				}	 
			});
		}else{
			$("#searchWorkId").formSelect();
			$("#mobileSearchWorkId").formSelect();
		}
	}
	
	//for setting global variables from navigation bar
	function setGlobalVariable(searchWorkId){
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
	    if(x.includes("dark")){
	   		theme.href = '/pmis/resources/css/dark-theme.css';
	        themeToggle.style.backgroundColor = "black";
	        themeToggle.children[0].classList.add('fa-moon-o');
	        themeToggle.children[0].classList.remove('fa-sun-o');
	        document.cookie = "theme=dark";
	    }else{
	   		theme.href = '/pmis/resources/css/light-theme.css';
	        themeToggle.style.backgroundColor = "white";
	        themeToggle.children[0].classList.add('fa-sun-o');
	        themeToggle.children[0].classList.remove('fa-moon-o');
	        document.cookie = "theme=light";
	    }
	});
	
	$(function(){
		  var current_page_URL = location.href; 
		  $( ".top-level-menu a" ).each(function() {
		     if ($(this).attr("href") !== "#") {
		       var target_URL = $(this).prop("href");
		       if (target_URL == current_page_URL) {
		          $('a').parents('li, ul').removeClass('menu-active');
					var classItem=$(this).closest('ul').parentsUntil($( "ul.top-level-menu" ));
					$(classItem[classItem.length-1]).addClass('menu-active');					
					var formName=current_page_URL.split('/');	
					if(formName[formName.length-1]=='home'){
						$('.top-level-menu a[href="/pmis/home"]').parent().addClass('menu-active');
					}
		          return false;
		       }
		     }
		  });
		  
		  var formName=current_page_URL.split('/');	
		  var gridOrForm=formName[formName.length-1].split('-')[1];
		  if (gridOrForm != '' || gridOrForm!= undefined){
			  var classItem=$('.top-level-menu a[href="/pmis/'+gridOrForm+'"]').closest('ul').parentsUntil($( "ul.top-level-menu" ));
			  $(classItem[classItem.length-1]).addClass('menu-active')	
		  }
		});
	
  </script>
  
</body>
</html>