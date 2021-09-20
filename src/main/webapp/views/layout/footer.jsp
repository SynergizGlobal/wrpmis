<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
	<style type="text/css">
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
      <span> &copy; <span id="currentYear"></span> @ <a href="https://mrvc.indianrailways.gov.in/" target="_blank" style="color:blue">mrvc.indianrailways.gov.in</a> | Designed & Developed by</span> 
      		<a href="https://www.synergizglobal.com" target="_blank"> <img src="/pmis/resources/images/synergiz.png" class="footer-img" alt="footer image"></a>
        <a class="help-icon dropdown-trigger"  href='#' data-target='help-dropdown'>
        	<img src="/pmis/resources/images/help_icon.svg">
        </a>

  <!-- Dropdown Structure -->
  <ul id='help-dropdown' class='dropdown-content blue lighten-5'>
    <li><a href="/pmis/5. User Manual for Risk Module-V2.pdf" target="_blank">User Manual for Risk Module<i class="fa fa-download"></i></a></li>
    	<!-- <ul >
	        <li style="min-height: 20px;">
	        	<a style="font-size: 14px;min-height: 20px;padding: 11px 16px;line-height: 0px;font-weight: 100;" href="/pmis/5. User Manual for PMIS-V 3.pdf" target="_blank">User Manual for Issue Module & FOB<i class="fa fa-download"></i></a>
	        </li>
	    </ul> -->
	    <li><a href="/pmis/5. User Manual for PMIS-V 3.pdf" target="_blank">User Manual for Issue Module & FOB <i class="fa fa-download"></i></a></li>
    <li><a href="/pmis/Primmavera P6_ppm_usermanual  Ver-19.12.pdf" target="_blank">Primavera Manual <i class="fa fa-download"></i></a></li>
    <li class="divider" tabindex="-1"></li>
    <li class="support-link"> Contact us :  <a href="mailto:support_pmis@mrvc.gov.in" style="display: inline;">support_pmis@mrvc.gov.in</a></li>
  </ul>
    </div>
  </div>
     <!-- <script src="/pmis/resources/js/theme.js"></script> -->
     <script>
     $( document ).ready(function() {
			if(window.matchMedia("(max-width: 769px)").matches)
			{ 
			  var elem = document.documentElement;
			  $('html').click(function () {
			        if (elem.isFullscreen !== true) {
			        	elem.requestFullscreen(); 
			        }
			    });
			}
			$('.dropdown-trigger').dropdown({
		    	 coverTrigger:false,    	 
		    	 closeOnClick: false,
		    	 aboveOrigin:true,
		     });
		   document.querySelectorAll('.pmis-textarea').forEach(function (item) {
		   		item.style.height = (item.scrollHeight < 59) ? '59px' : item.scrollHeight + 'px';
		   });
		}); 
     
     document.getElementById("currentYear").innerHTML = new Date().getFullYear();
     
     document.querySelectorAll('.pmis-textarea').forEach(function (item) {
         item.addEventListener('keyup', function (event) {
             item.style.height = (item.scrollHeight < 59) ? '59px' : item.scrollHeight + 'px';
         });
         item.addEventListener('change', function (event) {			
			   item.style.height = 0;			   
			   item.style.height = (item.scrollHeight<59) ?'59px' : (item.scrollHeight+ 'px');

     	});
      }); 
     
    
     </script>
</body>
</html>