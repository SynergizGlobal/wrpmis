<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context_path" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
	<style type="text/css">
		.help-txt{
    		position: relative;
		    top: -1.1em;
		    right: .5em;
		    font-size: 1rem;
    	}
		.f-logo{width: 27px;vertical-align: bottom;}
		.dropdown-content li>a>i{
		    float:right;
		    /* margin:auto; */
		    margin: auto 10px;
		}
		#help-dropdown.dropdown-content {
		    /* width: 250px !important; */
		    width: auto !important;
		    bottom: 31px;
		    top: inherit !important;
		}
	</style>
</head>
<body>
<!-- footer goes here -->
  <div class="footer-copyright blue lighten-5 bottom">
    <div class="container">
    	<c:if test="${sessionScope.IS_TEST_ENV_ENABLED eq 'true' }">
	    	<span style="width: 15%;">
			  <select class="browser-default" style="width: 15%;display: inline-block;" onchange="switchEnvironment(this.value);">
			    <option value="/pmis/" <c:if test="${context_path eq '/pmis' }">selected</c:if>>Production</option>
			    <option value="/pmis_qa/" <c:if test="${context_path eq '/pmis_qa' }">selected</c:if>>Testing</option>
			  </select>
			</span>
		</c:if>
      	<span> &copy; <span id="currentYear"></span> @ <a href="https://mrvc.indianrailways.gov.in/" target="_blank" style="color:blue"><b>MRVC </b> 
      	<img src="/pmis/resources/images/mrvclogo.png" alt="Logo" class="f-logo"> </a> | Designed & Developed by</span> 
      		<a href="https://www.synergizglobal.com" target="_blank"> <img src="/pmis/resources/images/synergiz.png" class="footer-img" alt="footer image"></a>
        <a class="help-icon dropdown-trigger"  href='#' data-target='help-dropdown'>
        	<span class="help-txt">Help desk</span><img src="/pmis/resources/images/help_icon.svg">
        </a>

	  <!-- Dropdown Structure -->
	  <ul id='help-dropdown' class='dropdown-content blue lighten-5'>
	   <c:forEach var="manualObj" items="${userManuals }">
	    	<li><a href="<%=CommonConstants2.PMIS_MANUALS%>${manualObj.manual_id }/${manualObj.file_name }" target="_blank">${manualObj.title } <i class="fa fa-download"></i></a></li>
	    </c:forEach>
	    <li class="divider" tabindex="-1"></li>
	    <li class="support-link"> Contact us :  <a href="mailto:helpdesk_pmis@mrvc.gov.in" style="display: inline;">helpdesk_pmis@mrvc.gov.in</a></li>
	  </ul>
    </div>
  </div>
  <form id="switchEnv" name="switchEnv" method="post">
  	<input type="hidden" name="user_id" value="${sessionScope.user.user_id }" />
  	<input type="hidden" name="password" value="${sessionScope.user.decrypted_password }" />
  	<input type="hidden" name="current_url" id="current_url" />
  </form>
     <!-- <script src="/pmis/resources/js/theme.js"></script> -->
     <script>
     $( document ).ready(function() {
			/*if(window.matchMedia("(max-width: 769px)").matches)
			{ 
			  var elem = document.documentElement;
			  $('html').click(function () {
			        if (elem.isFullscreen !== true) {
			        	elem.requestFullscreen(); 
			        }
			    });
			}*/
			$('.dropdown-trigger').dropdown({
		    	 coverTrigger:false,    	 
		    	 closeOnClick: false,
		    	 aboveOrigin:true,
		     });
		   document.querySelectorAll('.pmis-textarea:not(.textarea-no-height)').forEach(function (item) {
		   		item.style.height = (item.scrollHeight < 59) ? '59px' : item.scrollHeight + 'px';
		   });
		   
		}); 
     
     document.getElementById("currentYear").innerHTML = new Date().getFullYear();
     
     document.querySelectorAll('.pmis-textarea:not(.textarea-no-height)').forEach(function (item) {
         item.addEventListener('keyup', function (event) {
             item.style.height = (item.scrollHeight < 59) ? '59px' : item.scrollHeight + 'px';
         });
         item.addEventListener('change', function (event) {			
			   item.style.height = 0;			   
			   item.style.height = (item.scrollHeight<59) ?'59px' : (item.scrollHeight+ 'px');

     	});
      }); 
      
     function switchEnvironment(val){
    	 var current_url = $(location).attr('href');
    	 current_url = current_url.replace("/pmis/",val);
    	 current_url = current_url.replace("/pmis_qa/",val);
    	 $("#current_url").val(current_url);
    	 //alert($(location).attr('protocol')+"//"+$(location).attr('hostname')+val+"login");
    	 $("#switchEnv").attr("action",$(location).attr('protocol')+"//"+$(location).attr('hostname')+val+"login");
    	 $("#switchEnv").submit();
    	 /* alert("host = " 
    			    + $(location).attr('host') 
    			    + "\nhostname = " 
    			    + $(location).attr('hostname') 
    			    + "\npathname = " 
    			    + $(location).attr('pathname') 
    			    + "\nhref = " 
    			    + $(location).attr('href') 
    			    + "\nport = " + $(location).attr('port') 
    			    + "\nprotocol = " 
    			    + $(location).attr('protocol')); */
     }
    
     </script>
</body>
</html>