<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants2"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <!-- Styles -->
    <link rel="stylesheet" href="/wrpmis/resources/css/style.css"> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <style>
        body { background-color: #FFEFE2; }
        .login-body { display: flex; gap: 20px; padding: 20px; }
        .login-panel {
            background-color: #D58D54; border-radius: 30px; color: #fff;
            display: flex; flex-direction: column; align-items: center;
            width: 30%; height: 80vh; padding: 10px 15px 30px; justify-content: center;
        }
        .logo-name h3 { font-size: 30px; font-weight: 700; text-align: center; }
        .login-form h4 { text-align: center; font-size: 22px; font-weight: 600; padding-top: 20px; }
        .login-logo-img { margin-top: -20em; margin-bottom: 3em; }
        .login-form form { display: flex; flex-direction: column; align-items: center; gap: 10px; }
        input::-ms-reveal, input::-ms-clear { display: none; }
        .form-control, .input-wrapper input { color: #fff; }
        .input-wrapper input { line-height: 100%; }
        .toggle-password { position: absolute; color: #f0f0f0; right: 10px; top: 14px; font-size: 1.35rem; cursor:pointer; }

        @media(max-width: 990px){
            .login-body { flex-direction: column; }
            .carousel-inner img { height: 30vh !important; }
            .login-panel { width: 100%; }
            .login-logo-img { margin-top: 1em; }
        }
        @media(max-width: 767px){ .login-logo-img img{ width:200px; } }
    </style>
</head>
<body>

<div class="login-page">
    <div class="login-heading"><h1>Welcome to Western Railways PMIS</h1></div>

    <div class="login-body">
        <!-- Carousel -->
        <div class="login-carousel">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active"><img data-src="/wrpmis/resources/images/carousel1.webp" class="lazy img-responsive" alt="WR Image"></div>
                    <div class="item"><img data-src="/wrpmis/resources/images/carousel2.webp" class="lazy img-responsive" alt="WR Image"></div>
                    <div class="item"><img data-src="/wrpmis/resources/images/carousel3.webp" class="lazy img-responsive" alt="WR Image"></div>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <!-- Login Panel -->
        <div class="login-panel">
            <div class="login-panel-inner">
                <div class="login-logo-img"><img src="/wrpmis/resources/images/wr-logo.png" alt="WR Logo"></div>
                <div class="logo-name"><h3>Western Railways</h3></div>

                <div class="login-form">
                    <h4>SIGN IN</h4>
                    <form class="col s12 m3" action="<%=request.getContextPath()%>/login" id="loginForm" name="loginForm" method="post">
                        <div class="input-fields">
                            <div class="input-wrapper">
                                <input type="text" name="user_id" id="user_id" autocomplete="off">
                                <label>User Name</label>
                            </div>
                            <div class="input-wrapper">
                                <input type="password" name="password" id="password" autocomplete="off">
                                <label>Password</label>
                                <span class="toggle-password"><i class="fa-solid fa-eye"></i></span>
                            </div>
                            <div class="msg">
                                <p id="message" class="error">${message}</p>
                                <c:if test="${not empty success}"><p id="logoutMsg" class="success">${success}</p></c:if>
                            </div>
                        </div>
                        <button type="button" class="btn btn-transparent-white-border" onclick="login();">Submit</button>
                        <a href="<%=request.getContextPath() %>/forgot-password" class="white">Forgot Password</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>

<script defer>
$(document).ready(function() {
    // Carousel
    $('.carousel').carousel({ interval: 2000 });

    // Toggle password visibility
    $(".toggle-password i").click(function() {
        $(this).toggleClass("fa-eye-slash");
        var input = $("#password");
        input.attr("type", input.attr("type") === "password" ? "text" : "password");
    });

    // Login function
    window.login = function() {
        window.localStorage.clear();
        if ($('#loginForm').valid()) $('#loginForm').submit();
    };

    // Form validation
    $('#loginForm').validate({
        rules: { "user_id": { required:true }, "password": { required:true } },
        messages: { "user_id": "Please provide Login Id & Password", "password": "Please provide Login Id & Password" },
        errorPlacement: function(error, element){
            $("#logoutMsg").html("");
            if(element.attr("id")=="user_id" || element.attr("id")=="password") error.appendTo('#message');
            else error.insertAfter(element);
        }
    });

    // Hide success message after 3s
    $('#logoutMsg').delay(3000).fadeOut('slow');
});
</script>

<script>
document.addEventListener("DOMContentLoaded", function() {
    // Lazy-load carousel images
    const lazyImages = document.querySelectorAll('img.lazy');
    lazyImages.forEach(img => { img.src = img.dataset.src; });
});
</script>

</body>
</html>
