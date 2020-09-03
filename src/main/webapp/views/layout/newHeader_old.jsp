<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
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
        
        
   </style>

</head>
<body>
<!-- header  -->
  <nav>
    <div class="nav-wrapper blue darken-3">
      <div class="">
        <a href="<%=request.getContextPath() %>/home" class="brand-logo fs"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo"> MRVC PMIS</a>
        <a href="<%=request.getContextPath() %>/home" data-target="mobile-demo" class="sidenav-trigger"><i class="fa fa-bars"></i></a>
        <ul class="right hide-on-med-and-down top-level-menu">
          <c:if test="${homeHeader ne 'yes' }">
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
          </c:if>
          <li class="blue darken-2 dropdown"><a href="#" class='head-img'>
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
          		<a class='notification dropdown-trigger' data-target='dropdown1'>
                  <span class="material-icons-outlined">notifications</span>
                  <span class="badge red" id="notificationCount"></span>
                </a>
                <div class="notification_body dropdown-content" id='dropdown1'>
                      <div>
                          <input type="text"  name="srch-term" id="srch-term" class="browser-default searching empty"placeholder="&#xF002; Search Notifications...">
                      </div>
                      <ul class="notifications_group" style="margin-top: 5px;" id="notificationList">
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
                        </c:forEach>
                      </ul>
                </div>
          </li>
                    
                    
          <li class="blueblue lighten-1 dropdown"><a href="#" class='head-img'>
          <!--img src="/pmis/resources/images/user-white.png"--> 
          <span class="material-icons">person</span> MRVC</a>
              <ul class="second-level-menu rs">
                  <li><a href="<%=request.getContextPath()%>/reset-password">Reset password</a></li>
                  <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
              </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <ul class="sidenav" id="mobile-demo">
    <li>
      <select id="mobileSearchProjectId" name="searchProjectId" onchange="getWorksListForSearch(this.value);">
        <option>Select Project</option>
      </select>
    </li>
    <li>

      <select id="mobileSearchWorkId" name="searchWorkId">
        <option>Select Work</option>
      </select>
    </li>
    <li><a href="#" class='head-img'><span class="material-icons-outlined">dashboard</span> Dashboard</a>
		<ul class="dropdown-data">
              <c:forEach var="category" items="${dashboardsList }" varStatus="index">
           			<c:set var="tempactivity" value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
	              	<c:set var="activity" value="${ fn:toLowerCase(tempactivity.replaceAll(' ', '-'))}"></c:set>
	              	<li>
	              		<a href="<%=request.getContextPath()%>/InfoViz/${activity }">
			 				<span class="${category.imagePath}"></span>
			 				<span class="nav-label">${category.tableauDashboardName }</span>
			 			</a>
			 		</li>
	         </c:forEach>
         </ul>
    </li>
    <li><a href="#" class='head-img'><span class="material-icons-outlined">post_add</span>Update Forms</a>
    	<ul class="dropdown-data">
             <c:forEach var="form" items="${forms }">
          		<li><a href="${form.webFormUrl }">${form.formName }</a></li>
          	  </c:forEach>
         </ul>
    </li>
    <li><a href="#" class='head-img'><span class="material-icons-outlined">assignment</span> Reports</a></li>
    <li><a href="#" class='head-img'><span class="material-icons">person</span> MRVC</a>
    	<ul class="dropdown-data">
            <li><a href="<%=request.getContextPath()%>/reset-password">Reset password</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
        </ul>
    </li>
  </ul>
  
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
		
		$('.notification.dropdown-trigger').dropdown({
            coverTrigger: false,
            closeOnClick: false,
        });
		
		$("#srch-term").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#notificationList li.item").filter(function() {
		    	//alert(value);
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		});
		 
		$('.sidenav').sidenav();
		$("#searchProjectId").formSelect();
		$("#mobileSearchProjectId").formSelect();
		
		$("#searchWorkId").formSelect();
		$("#mobileSearchWorkId").formSelect();
			
		getProjectsListForSearch();
		var globalProjectId = "${sessionScope.globalProjectId}";
		if($.trim(globalProjectId) != ''){
			getWorksListForSearch(globalProjectId);
		}


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
	});
	
	function getProjectsListForSearch(){
		$("#searchProjectId option:not(:first)").remove();
		$("#mobileSearchProjectId option:not(:first)").remove();		
		$.ajax({
			url:"<%=request.getContextPath()%>/ajax/getProjectsListForSearch",
			cache: false,
			success:function(data){
				if(data.length > 0){
					 $.each(data, function(i,val) {
						 projectName = '';
						if($.trim(val.projectName) != ''){projectName = ' - '+ val.projectName}
						var globalProjectId = "${sessionScope.globalProjectId}";
						if($.trim(globalProjectId) != '' && val.projectId == $.trim(globalProjectId)){
							$("#searchProjectId").append('<option value="'+val.projectId+'" selected>'+val.projectId + projectName+'</option>');
				            $("#mobileSearchProjectId").append('<option value="'+val.projectId+'" selected>'+val.projectId + projectName+'</option>');
						}else{
				            $("#searchProjectId").append('<option value="'+val.projectId+'">'+val.projectId + projectName+'</option>');
				            $("#mobileSearchProjectId").append('<option value="'+val.projectId+'">'+val.projectId + projectName+'</option>');
						}
			         });		
	 			}	
				$("#searchProjectId").formSelect();
				$("#mobileSearchProjectId").formSelect();
			}	 
		});
	}
	
	function getWorksListForSearch(projectId){
		$("#searchWorkId option:not(:first)").remove();
		$("#mobileSearchWorkId option:not(:first)").remove();
		if($.trim(projectId) != ""){
			var myParams = {projectId : projectId}; 
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/getWorksListForSearch",
				data:myParams,cache: false,
				success:function(data){
					if(data.length > 0){
						 $.each(data, function(i,val) {
							var globalWorkId = "${sessionScope.globalWorkId}";
							if($.trim(globalWorkId) != '' && val.workId == $.trim(globalWorkId)){
								$("#searchWorkId").append('<option value="'+val.workId+'" selected>'+val.workId+' - '+val.workName+'</option>');
					            $("#mobileSearchWorkId").append('<option value="'+val.workId+'" selected>'+val.workId+' - '+val.workName+'</option>');
							}else{
								$("#searchWorkId").append('<option value="'+val.workId+'">'+val.workId+' - '+val.workName+'</option>');
					            $("#mobileSearchWorkId").append('<option value="'+val.workId+'">'+val.workId+' - '+val.workName+'</option>');
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
	
  </script>
  
</body>
</html>