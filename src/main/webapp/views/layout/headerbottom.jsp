	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <nav class="ha-menu ha-bottom">
    
	  <ul class="h-inner">
	    <li class="hi-trigger ma-trigger" data-ma-action="sidebar-open" data-ma-target="#sidebar">
	      
	    </li>
	    <li class="hi-logo"> <a href="<%=request.getContextPath()%>/"><img src="/pmis/resources/img/logo.png" ></a> </li>
	    <%-- <a href="<%=request.getContextPath()%>"><span class="tagline">SynTrack</span></a>
	      <div class="tagline">Driving Infrastructure Forward</div> --%>  
	 
	  </ul>
    
    <div class="navbar-header">
    	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-3">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         </div>
         
        
    <div class="collapse navbar-collapse dropmenu" id="navbar-collapse-3">
    <div > 
    <ul id='cssmenu' class="nav navbar-nav padding">     
    	<%-- <li class="toptabs tab2-home"><a href="<%=request.getContextPath() %>/home">Home</a></li>   --%> 
    	<li class="toptabs" >
    		
			<select id="bottomSearchProjectId" name="bottomSearchProjectId" class="js-example-basic-single" onchange="getWorksListForSearchBottom(this.value);">
			<option>Select Project</option>
			<!-- <option>MUTP-2 (MRVC Works)</option>
			<option>MUTP-2 (CR Works)</option>
			<option>MUTP-2 (WR Works)</option>
			<option>MUTP-2C</option>
			<option>MUTP-3</option>
			<option>MUTP-3A (MRVC Works)</option> -->
			</select>
    		
    	</li>
    		<li class="toptabs" >
    		
			<select id="bottomSearchWorkId" name="bottomSearchWorkId" class="js-example-basic-single">
			<option>Select Work</option>
			<!-- <option>5th and 6th Line Thane-Diva (excluding surplus)</option>
			<option>Extension of H/Line from Andheri to Goregaon</option>
			<option>Stabling Lines -Ph-II (WR)</option>
			<option>DC-AC Conversion Ph-II (MRVC)</option>
			<option>Procurement of M&P for OHE maintenance (100% WB Funded)</option>
			<option>EMU procurement/Manufacture (ICF/Others)</option>
			<option>Technical Assistance & Institutional Strengthening</option>
			<option>R&R-Ph-II</option> -->
			</select>
    		
    	</li>
        <li class="toptabs tab2-dashboard"><a href="#" data-toggle="dropdown">InfoVizes</a>
        
        	<ul class="dropdown-menu top-dropdown">
									
				<c:set var="menuTotalCount" value="${fn:length(dashboardsList) }"></c:set>
				
				<c:forEach var="category" items="${dashboardsList }" varStatus="index">
           			<c:set var="tempactivity" value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
	              	<c:set var="activity" value="${ fn:toLowerCase(tempactivity.replaceAll(' ', '-'))}"></c:set>
              			
           			<c:choose>
           			
           			<c:when test="${menuTotalCount le 10}">
           										      
				      	<li class="dropdown-submenu">
				      		
				      		<c:if test="${empty category.tableauSubList}">
        				 		<a href="<%=request.getContextPath()%>/InfoViz/${activity }" class="dropdown-toggle">
       				 				<span class="${category.imagePath}"></span>
       				 				<span class="nav-label">${category.tableauDashboardName }</span>
       				 			</a>
							</c:if>
							<c:if test="${not empty category.tableauSubList}">
        				 		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
       				 				<span class="${category.imagePath}"></span>
       				 				<span class="nav-label">${category.tableauDashboardName }</span>
       				 				<span class="nav-label right-icon"><i class="fa fa-angle-right"></i></span>
       				 			</a>
							</c:if>
       						
       						<ul class="dropdown-menu">
       						
       							<c:forEach var="subList" items="${category.tableauSubList }">
				           			<c:set var="tempsubActivity" value="${ fn:toLowerCase(subList.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
            						<c:set var="subActivity" value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}"></c:set>
				           			<c:set var="subActivityName" value="${ subList.tableauDashboardName}"></c:set>
			           				<li>
      									<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
      										<i class="${subList.imagePath }"></i>${subList.tableauDashboardName }
          								</a>
              						</li>
				           		</c:forEach>                       
							</ul>
						</li>	
						</c:when>
             			<c:otherwise>
	              			<c:if test="${index.count < 10 }">
	              				<li class="dropdown-submenu">
            				 		<c:if test="${empty category.tableauSubList}">
		        				 		<a href="<%=request.getContextPath()%>/InfoViz/${activity }" class="dropdown-toggle">
		       				 				<span class="${category.imagePath}"></span>
		       				 				<span class="nav-label">${category.tableauDashboardName }</span>
		       				 			</a>
									</c:if>
									<c:if test="${not empty category.tableauSubList}">
		        				 		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
		       				 				<span class="${category.imagePath}"></span>
		       				 				<span class="nav-label">${category.tableauDashboardName }</span>
		       				 				<span class="nav-label right-icon"><i class="fa fa-angle-right"></i></span>
		       				 			</a>
									</c:if>
		       						
		       						<ul class="dropdown-menu">
		       						
		       							<c:forEach var="subList" items="${category.tableauSubList }">
						           			<c:set var="tempsubActivity" value="${ fn:toLowerCase(subList.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
		            						<c:set var="subActivity" value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}"></c:set>
						           			<c:set var="subActivityName" value="${ subList.tableauDashboardName}"></c:set>
					           				<li>
		      									<a href="<%=request.getContextPath()%>/InfoViz/${activity }/${subActivity }">
		      										<i class="${subList.imagePath }"></i>${subList.tableauDashboardName }
		          								</a>
		              						</li>
						           		</c:forEach>                       
									</ul>
         				 		</li>
         				 		</c:if>
          				 		<c:if test="${index.count eq 10 }">
           				 		<li class="dropdown-submenu dropup" >
            				 		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
            				 			<span class="fa fa-list side-icon" aria-hidden="true"></span>
            				 			<span class="nav-label">Other</span>
            				 			<span class="nav-label right-icon"><i class="fa fa-angle-right"></i></span>
           				 			</a>
           				 			
           				 			<ul class="dropdown-menu other-submenu">
							           <c:forEach var="category" items="${dashboardsList }" varStatus="index2">
							           		<c:if test="${index2.count gt 9 }">
							           			<c:set var="tempsubActivity" value="${ fn:toLowerCase(category.tableauDashboardName.replaceAll(' - ', '_'))}"></c:set>
            									<c:set var="subActivity" value="${ fn:toLowerCase(tempsubActivity.replaceAll(' ', '-'))}"></c:set>
							           			<c:set var="subActivityName" value="${ category.tableauDashboardName}"></c:set>
							           			
		              							<li>
            										<a href="<%=request.getContextPath()%>/InfoViz/${subActivity }">
            											<i class="${category.imagePath }"></i>${subActivityName }
		              								</a>
		              							</li>
			              					</c:if>
							           </c:forEach>
							        </ul>
           				 		</li>
          				 		</c:if>				           
					      
             				
             				</c:otherwise>
             			
             			</c:choose>
			        
			        
             		</c:forEach>
				
				
				</ul>
        
        </li>
        
        <li class="toptabs tab2-input-forms dropdown">
           <a href="javascript:void(0);" data-toggle="dropdown">Input forms <b class="caret"></b></a>
           <ul class="dropdown-menu user-logout">
               <li><a style="color: #000;" href="<%=request.getContextPath()%>/activity-progress">Activity</a></li>
               <li><a style="color: #000;" href="<%=request.getContextPath()%>/activity-data">P6 data</a></li>
            </ul>
        </li>
	    
	    <li id="tab2-user-profile" class="toptabs tab2-profile dropdown">
           <a href="javascript:void(0);" data-toggle="dropdown" class=""><i class="fa fa-user"></i>&nbsp;&nbsp;${sessionScope.user.userName} <b class="caret"></b></a>
           <ul class="dropdown-menu user-logout">
               <li><a style="color: #000;" href="<%=request.getContextPath()%>/reset-password">Reset Password</a></li>
               <li><a style="color: #000;" href="<%=request.getContextPath()%>/logout">Logout</a></li>
            </ul>
        </li>
    </ul>
    </div>
    </div>
</nav>

<script src="/pmis/resources/vendors/bower_components/jquery/dist/jquery.min.js"></script>
<script src='/pmis/resources/js/holder.min.js'></script>
<!-- <script src="/pmis/resources/js/script.js"></script> -->

<script>
	/* if('${tabActive}' == '' || '${tabActive}' == null){
		$( ".tab2-home" ).addClass( "active" );
	}else{
		$( ".tab2-${tabActive}" ).addClass( "active" );
	}	 */
	
	$(document).ready(function(){
		if ( screen.width < 768 ){
			$('#user-profile').removeClass('dropdown');
			$('#user-profile').addClass('dropup');
		}else{
			$('#user-profile').removeClass('dropup');
			$('#user-profile').addClass('dropdown')
		}
		
		/* $(window).resize(function(){
			if ( screen.width < 768 ){
				
				$('#user-profile').removeClass('dropdown');
				$('#user-profile').addClass('dropup');
			}else{
				$('#user-profile').removeClass('dropup');
				$('#user-profile').addClass('dropdown')
			}
		});  */
		
		getProjectsListForSearchBottom();
	});
	
	
	function getProjectsListForSearchBottom(){
		$("#bottomSearchProjectId option:not(:first)").remove();		
		$.ajax({
			url:"<%=request.getContextPath()%>/ajax/getProjectsListForSearch",
			cache: false,
			success:function(data){
				if(data.length > 0){
					 $.each(data, function(i,val) {
						 projectName = '';
						if($.trim(val.projectName) != ''){projectName = ' - '+ val.projectName}
			            $("#bottomSearchProjectId").append('<option value="'+val.projectId+'">'+val.projectId + projectName+'</option>');
			         });		
	 			}	
				$("#bottomSearchProjectId").select2();
			}	 
		});
	}
	
	function getWorksListForSearchBottom(projectId){
		$("#bottomSearchWorkId option:not(:first)").remove();
		
		if($.trim(projectId) != ""){
			var myParams = {projectId : projectId}; 
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/getWorksListForSearch",
				data:myParams,cache: false,
				success:function(data){
					if(data.length > 0){
						 $.each(data, function(i,val) {
				            $("#bottomSearchWorkId").append('<option value="'+val.workId+'">'+val.workId+' - '+val.workName+'</option>');
				         });		
		 			}	
					$("#bottomSearchWorkId").select2();
				}	 
			});
		}else{
			$("#bottomSearchWorkId").select2();
		}
	}
	
	
	
</script>