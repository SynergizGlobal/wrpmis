<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>   

   <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
   <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
   <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
   
   <style type="text/css">
   		.noti:after {
            position: absolute;
            right: 30%;
            bottom: -10px;
            width: 0;
            height: 0;
            content: '';
            border-left: 10px solid transparent;
            border-right: 10px solid transparent;
            border-bottom: 10px solid #f56661;
        }  
        .notification .material-icons-outlined{
        line-height:inherit;}
       
        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            position: relative;
        }
        /* notification count styling */
        #notificationCountMobile{
        	background-color: red;
    		color: #fff;
    		font-size: 0.85rem;
    		border-radius: 3px;
    		-webkit-box-shadow: 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 5px 0 rgba(0,0,0,0.2);
    		box-shadow: 0 2px 2px 0 rgba(0,0,0,0.14), 0 3px 1px -2px rgba(0,0,0,0.12), 0 1px 5px 0 rgba(0,0,0,0.2);
        }
        .earching.empty::placeholder{
        	color:#fff;
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
          <li class="blue darken-2 dropdown"><a href="#" class='head-img'>
          <!-- 1st level Dropdown starts -->
          <!-- img src="/pmis/resources/images/dashboard-white.png"--> 
          <span class="material-icons-outlined">dashboard</span> Dashboard</a>
          	  <ul class="second-level-menu">
                  <c:forEach var="category" items="${dashboardsList }" varStatus="index">
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
          <c:if test="${sessionScope.USER_ROLE_NAME ne 'Super User' }">
	          <li class="blue darken-1 dropdown">
	          	<a href="#" class='head-img'>
	               <span class="material-icons-outlined">post_add</span>
	          		Update Forms</a>
	          	  <ul class="second-level-menu">
	                  <c:forEach var="form" items="${forms }">
	                  	<li><a href="${form.webFormUrl }">${form.formName }</a></li>
	               	  </c:forEach>
	              </ul>
	              
	              
	              <ul class="second-level-menu">
	              <!-- 1st level Dropdown starts -->
	                  <c:forEach var="form" items="${forms }" varStatus="index">
	           			<c:if test="${empty form.formsSubMenu}">
			              	<li>
			              		<a href="${form.webFormUrl }">
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
	      									<a href="${subList.webFormUrl }">
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
          
          <li class="blue"><a href="#" class='head-img'>
	          <span class="material-icons-outlined">assignment</span> Reports</a>
          </li>
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
                  <span class="badge red" id="notificationCount"></span>
                </a>
                <div class="notification_body dropdown-content" id='dropdown1'>
                  <!-- Notification dropdown body starts -->
                      <div>
                          <input type="text"  name="srch-term" id="srch-term" class="browser-default searching empty"placeholder="&#xF002; Search Notifications...">
                      </div>
                      <ul class="notifications_group" style="margin-top: 5px;" id="notificationList">
                       <!-- list of Notifications starts -->
                      	<c:forEach var="aObj" items="${dueActivities }">
                          <li class="head-item">${aObj.workId} - ${aObj.workName}</li>
                          <c:forEach var="bObj" items="${aObj.dueActivities }">
	                          <li class="item <c:if test="${bObj.readUnreadStatus eq 'Read' }">read</c:if>">
	                              <a href="${bObj.notificationLink}">
	                                  <span><i class="fa fa-${bObj.image }"></i> ${bObj.location } / ${bObj.activityType } / ${bObj.activity } / ${bObj.boqCompleteTotal }</span>
	                                  <div><i class="fa fa-clock-o"></i> <span class="time">${bObj.timeAgo}</span></div>
	                              </a>
	                              <%-- <a href="${bObj.notificationLink}">
	                                  <span><i class="fa fa-edit"></i> ${bObj.description}</span>
	                                  <div><i class="fa fa-clock-o"></i> <span class="time">${bObj.timeAgo}</span></div>
	                              </a> --%>
	                          </li>
                          </c:forEach>
                                 <!-- list of Notifications ends -->
                        </c:forEach>
                      </ul>
                      <!-- Notification dropdown body ends -->
                </div>
          </li>
                    
                    
          <li class="blueblue lighten-1 dropdown"><a href="#" class='head-img'>
          <!--img src="/pmis/resources/images/user-white.png"--> 
          <span class="material-icons">person</span>${USER_NAME }<c:if test="${empty USER_NAME }">${USER_ID}</c:if></a>
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
    <li class="sub-menu"><a href="#" class='head-img collapsible-header'><span class="material-icons-outlined">dashboard</span> Dashboard</a>
<!-- Mobile dropdown stars here -->
          <ul class="dropdown-data collapsible collapsible-body second-lvl">         
                  <c:forEach var="category" items="${dashboardsList }" varStatus="index">
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
    
	<li class="sub-menu"><a href="#" class='head-img collapsible-header'><span class="material-icons-outlined">post_add</span> Update Forms</a>
    	<ul class="dropdown-data collapsible-body second-lvl collapsible">          	  
          	  <c:forEach var="form" items="${forms }" varStatus="index">
         			<c:if test="${empty form.formsSubMenu}">
		              	<li>
		              		<a href="${form.webFormUrl }">
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
	    									<a href="${subList.webFormUrl }">
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
    <li><a href="#" class='head-img'><span class="material-icons-outlined">assignment</span> Reports</a></li>
    <li>
   		<!-- a class='dropdown-trigger' data-target='dropdown1'-->
   		<a href="<%=request.getContextPath() %>/home" data-target="notification-demo" class="sidenav-trigger">
           <span class="material-icons-outlined">notifications</span> Notifications
           <span class="badge" id="notificationCountMobile"></span> 
           <!--span class="badge red" id="notificationCount"></span-->
         </a>
              
    </li>   
    <li class="sub-menu"><a href="#" class='head-img collapsible-header'><span class="material-icons">person</span> MRVC</a>
    	<ul class="dropdown-data collapsible-body second-lvl">
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
                          <input type="text"  name="srch-term" id="srch-term-mobile" class="browser-default searching empty"placeholder="&#xF002; Search Notifications...">
                      </div>
                      <ul class="notifications_group" style="margin-top: 5px;" id="notificationListMobile">
                       <!-- Mobile notification body starts here -->
                      	<c:forEach var="aObj" items="${dueActivities }">
                          <li class="head-item">${aObj.workId} - ${aObj.workName}</li>
                          <c:forEach var="bObj" items="${aObj.dueActivities }">
	                          <li class="item <c:if test="${bObj.readUnreadStatus eq 'Read' }">read</c:if>">
	                              <a href="${bObj.notificationLink}">
	                                  <span><i class="fa fa-${bObj.image }"></i> ${bObj.location } / ${bObj.activityType } / ${bObj.activity } / ${bObj.boqCompleteTotal }</span>
	                                  <div><i class="fa fa-clock-o"></i> <span class="time">${bObj.timeAgo}</span></div>
	                              </a>
	                          </li>
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
        <c:forEach var="aObj" items="${dueActivities }">
        	if("${aObj.dueActivities.size()}" != null && "${aObj.dueActivities.size()}" != ''){
        		count = Number(count) + Number("${aObj.dueActivities.size()}")
        	}
        </c:forEach>
        
		if(count > 99){
			count = "99+"
		}
    	$("#notificationCount").html(count);
    	$("#notificationCountMobile").html(count);
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
	
	
	
	// this one is just to wait for the page to load
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
	
	
	
  </script>
  
</body>
</html>