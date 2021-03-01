<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<!-- footer goes here -->
  <div class="footer-copyright blue lighten-5 bottom">
    <div class="container">
      <span>Copyright 2020 @ mrvc.indianrailways.gov.in | Designed & Developed by</span> <img src="/pmis/resources/images/synergiz.png"
        class="footer-img" alt="footer image">
        <a class="help-icon dropdown-trigger"  href='#' data-target='help-dropdown'>
        	<img src="/pmis/resources/images/help_icon.svg">
        </a>

  <!-- Dropdown Structure -->
  <ul id='help-dropdown' class='dropdown-content blue lighten-5'>
    <li><a href="#!">PMIS Manual <i class="fa fa-download"></i></a></li>
    <li><a href="#!">Primavera Manual <i class="fa fa-download"></i></a></li>
    <li class="divider" tabindex="-1"></li>
    <li class="support-link"> Contact us :  <br> <a href="mailto:support_pmis@mrvc.gov.in">support_pmis@mrvc.gov.in</a></li>
  </ul>
    </div>
  </div>
     <!-- <script src="/pmis/resources/js/theme.js"></script> -->
     <script>
     $('.dropdown-trigger').dropdown({
    	 coverTrigger:false,    	 
    	 closeOnClick: false,
    	 aboveOrigin:true,
     });
     </script>
</body>
</html>