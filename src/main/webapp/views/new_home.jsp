<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Welcome to PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
	
    <link rel="stylesheet" href="/pmis/resources/css/normalize.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/li-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
     <style type="text/css">
		/* 3rd demo start */
        
         .btn {
				  cursor: pointer;
				  -webkit-transition: all 0.15s ease-in-out;
				  transition: all 0.15s ease-in-out;
				}
				.btn:before,
				.btn:after {
				  -webkit-transition: all 0.15s ease-in-out;
				  transition: all 0.15s ease-in-out;
				}
				.diamond {
				  display: flex;
			    justify-content: center;
			    align-items: center;
				  color: #000;
				  line-height: 40px;
				  padding: 0 20px;
				  background: #fff;
				  margin: 30px auto;
				  /* position: relative; */
				  height: 80px;
				  width: 250px;
				  border-radius: 20px;
				}
				.diamond span {
				  display: inline-block;
				  width: 200px;
				  font-size: 24px;
				  font-weight: bold;
				}
				.diamond:before,
				.diamond:after {
				  /* content: ''; */
				  position: absolute;
				  width: 0;
				  height: 0;
				  border: 35px solid transparent;
				}
				.diamond:before {
				  right: 100%;
				  border-right-color: #fff;
				}
				.diamond:after {
				  left: 100%;
				  border-left-color: #fff;
				}
				
				.diamond:hover {
				  background: rgba(21, 101, 192, 0.8);
				  /* filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
		            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
		            transform: scale(1.1); */
		            transition: all 0.2s;
		            transition-duration: 0.2s;
		            color: #fff;
				}
				.diamond:hover:before {
				  border-right-color: rgba(21, 101, 192, 0.8);
				  /* filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
		            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
		            transform: scale(1.1); */
		            transition: all 0.2s;
		            transition-duration: 0.2s;
				}
				.diamond:hover:after {
				  border-left-color: rgba(21, 101, 192, 0.8);
				  /* filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
		            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
		            transform: scale(1.1); */
		            transition: all 0.2s;
		            transition-duration: 0.2s;
				}
				.hexagon {
				  position: relative;
				  width: 150px;
				  height: 81.08108108px;
				  line-height: 75px;
				  background-color: #fff;
				  margin: 50px auto;
				  transform: rotate(90deg)
				}
				.hexagon:before,
				.hexagon:after {
				  content: "";
				  position: absolute;
				  width: 0;
				  left: 0;
				  border-left: 75px solid transparent;
				  border-right: 75px solid transparent;
				}
				.hexagon:before {
				  bottom: 99.40257%;
				  border-bottom: 50px solid #fff;
				}
				.hexagon:after {
				  top: 98.40257%;
				  width: 0;
				  border-top: 50px solid #fff;
				}
				.hexagon span {
				  display: inline-block;
				  vertical-align: middle;
				  line-height: normal;
				  margin: 30px 10px;
				  transform: rotate(-90deg);
				  color: #000;
				  width: 80px;
				}
				.hexagon:hover {
				  background: rgba(255, 255, 255, 0.9);
				}
				.hexagon:hover:before {
				  border-bottom: 50px solid rgba(255, 255, 255, 0.9);
				}
				.hexagon:hover:after {
				  border-top: 50px solid rgba(255, 255, 255, 0.9);
				}
        
        /* 3rd demo end */
        
        /* map styles start */
          .map-btn-holder .btn {
            background-color: #1565C0cc;
           /*  background-image: linear-gradient(to right, #16D58A, #00BDE7); */
            /* background-color:#01BAEF; */
            background-color: transparent;
             border-radius: 50%; 
            box-shadow: none;
            font-size: 2.25rem;
            line-height: 2.5rem;
            height: 5rem;
            padding: 14px 8px;
    		box-shadow: 0 2px 2px 0 rgb(0 0 0 / 14%), 0 3px 1px -2px rgb(0 0 0 / 12%), 0 1px 5px 0 rgb(0 0 0 / 20%);
        }

        .map-btn-holder .fa {
            font-size: 200%;
            vertical-align: middle;
            height: 100%;
            margin-top: 5%;
        }


		.modal {
		            max-height: 100%;
		            top: 0 !important;
		        }
		  .modal-header {
	           text-align: center;
	           background-color: #1565C0cc;
	           / background-image: linear-gradient(to right, #16D58A, #00BDE7); /
	           background-color:#01BAEF;
	           color: #fff;
	           margin: -24px -24px 20px !important;
	           padding: 1rem;
	       }
	       .map-btn-holder{
	       	margin-top: 30px;
	       	text-align: center
	       }
        
        /* map styles end */
        .btn-menu ul {
            text-align: center;
        }

        .btn-menu ul li {
            padding: 20px;
            font-size: 18px;
        }

        .btn-menu ul a li {
            color: #000;
            font-weight: bold;
            z-index: 100;
        }

        .btn-menu ul a li button:hover {
            color: #fff;
            font-weight: bold;
            text-decoration: underline;
        }
		.heading img{width: 135%;margin-top: 30px;}
        .heading h3 {
            color: #fff;
            font-weight: bold;
            padding: 10px;
            /* background: #1565C0; */
            vertical-align: middle;
            width: 100%;
            font-size:40px;
        }
		/* .heading h3:before,
			.heading h3:after {
			  content: '';
			  position: absolute;
			  width: 0;
			  height: 0;
			  /* border: 30px solid transparent; */
			  top: 0;
			}
			.heading h3:before {
			  right: 100%;
			  border-right-color: #1565C0;
			}
			.heading h3:after {
			  left: 100%;
			  border-left-color: #1565C0;
			} */

        li {
            position: relative;
        }

        .row .col {
            padding: 10px 0.75rem !important;
        }
		 
		@media(max-width: 1560px){
			.heading h3 {
                font-size: 40px;
            }
		} 
        @media(max-width: 1366px) {
            .heading h3 {
                font-size: 40px;
            }
            .map-btn-holder .fa {
            font-size: 3.3rem;
            }
        }

        .btn-menu a {
            -webkit-animation: fadeIn 0.5s linear;
            animation: fadeIn 0.5s linear;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
        }

        .btn-menu a li {
            /* position: relative; */
            display: block;
            margin-bottom: 5px;
            padding: 10px;
            text-align: center;
             border-radius: 50%;
            
  background-color: transparent;
    border-radius: 10%;
   padding: 14px 8px;
    
    
 /*    box-shadow: 0 2px 2px 0 rgb(0 0 0 / 14%), 0 3px 1px -2px rgb(0 0 0 / 12%), 0 1px 5px 0 rgb(0 0 0 / 20%); */
               

        }

        /* .btn-menu a:nth-child(1) {
            -webkit-animation-delay: 0.25s;
            animation-delay: 0.25s;
        }

        .btn-menu a:nth-child(2) {
            -webkit-animation-delay: 0.5s;
            animation-delay: 0.5s;
        }

        .btn-menu a:nth-child(3) {
            -webkit-animation-delay: 0.75s;
            animation-delay: 0.75s;
        }

        .btn-menu a:nth-child(4) {
            -webkit-animation-delay: 1s;
            animation-delay: 1s;
        }

        .btn-menu a:nth-child(5) {
            -webkit-animation-delay: 1.25s;
            animation-delay: 1.25s;
        }

        .btn-menu a:nth-child(6) {
            -webkit-animation-delay: 1.50s;
            animation-delay: 1.50s;
        }

        @-webkit-keyframes fadeIn {
            0% {
                opacity: 0;
                top: 100px;
            }

            75% {
                opacity: 0.5;
                top: 0px;
            }

            100% {
                opacity: 1;
            }
        }

        .btn-menu a {
            position: relative;
            text-align: center;
            text-transform: uppercase;
            -webkit-animation: fadeIn 0.5s linear;
            animation: fadeIn 0.5s linear;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
        }
        .btn-menu a:hover{
        	text-decoration:none;
        	color:#fff;
        }

        .btn-menu a:nth-child(1) {
            -webkit-animation-delay: 0.25s;
            animation-delay: 0.25s;
        }

        .btn-menu a:nth-child(2) {
            -webkit-animation-delay: 0.5s;
            animation-delay: 0.5s;
        }

        .btn-menu a:nth-child(3) {
            -webkit-animation-delay: 0.75s;
            animation-delay: 0.75s;
        }

        .btn-menu a:nth-child(4) {
            -webkit-animation-delay: 1s;
            animation-delay: 1s;
        }

        .btn-menu a:nth-child(5) {
            -webkit-animation-delay: 1.25s;
            animation-delay: 1.25s;
        }

        .btn-menu a:nth-child(6) {
            -webkit-animation-delay: 1.50s;
            animation-delay: 1.50s;
        }

        @-webkit-keyframes fadeIn {
            0% {
                opacity: 0;
                top: 100px;
            }

            75% {
                opacity: 0.5;
                top: 0px;
            }

            100% {
                opacity: 1;
            }
        } */

        .btn-menu li button:hover {
            /* filter: drop-shadow(2px 4px 6px black); */
            filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
            transform: scale(1.1);
            transition: all 0.2s;
            transition-duration: 0.2s;
        }

        @media(max-width: 1366px) {
            .heading h3 {
                font-size: 40px;
            }
            .map-btn-holder .btn{height: 4rem;padding: 8px;}
        }

       /*  .tracking-in-expand-fwd {
            -webkit-animation: tracking-in-expand-fwd 2.8s cubic-bezier(0.215, 0.610, 0.355, 1.000) both;
            animation: tracking-in-expand-fwd 2.8s cubic-bezier(0.215, 0.610, 0.355, 1.000) both;
        }

        @-webkit-keyframes tracking-in-expand-fwd {
            0% {
                letter-spacing: -0.5em;
                -webkit-transform: translateZ(-700px);
                transform: translateZ(-700px);
                opacity: 0;
            }

            40% {
                opacity: 0.6;
            }

            100% {
                -webkit-transform: translateZ(0);
                transform: translateZ(0);
                opacity: 1;
            }
        }

        @keyframes tracking-in-expand-fwd {
            0% {
                letter-spacing: -0.5em;
                -webkit-transform: translateZ(-700px);
                transform: translateZ(-700px);
                opacity: 0;
            }

            40% {
                opacity: 0.6;
            }

            100% {
                -webkit-transform: translateZ(0);
                transform: translateZ(0);
                opacity: 1;
            }
        } */

        body {
            margin: 0;
            font-family: 'Open Sans', sans-serif;
            /* background: black url("/pmis/resources/images/login-background.jpg") no-repeat center center; */
            /* background: #6596ff; */
            background-size: cover;
            background-repeat-y: repeat;
            
        }
         body:after{
         	backdrop-filter: blur(5px);
         }

        body:after {
            content: '';
            /* background-color: rgba(0, 0, 0, .1);  */
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: -1;
        }

        .mx-auto {
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }
         @media(max-width: 1024px){
        	.heading h3{font-size: 40px;}
        }
        @media(max-width: 768px){
        	.heading h3{width: 90%;}
        	.diamond span {
			    display: flex;
			    width: 92px;
			    margin-top: 0;
			    line-height: normal;
			    vertical-align: middle;
			    align-content: center;
			    align-items: center;
			    height: 100%;
			}
			.diamond:after{top: 0;}
		}
		@media(max-width: 820px){
			.heading img {
			    width: 173%;
			    margin-top: 36px;
			}
        }
        @media(max-width: 575px){
        	.heading h3{width: 100%;line-height: 1.2;font-size: 15px;}
        	.map-btn-holder{margin: 0; margin-top: 33px;}
        	/* .heading h3:before, .heading h3:after{
        		border: 28px solid transparent;
        	}
        	.heading h3:before {
			    right: 100%;
			    border-right-color: #1565C0;
			}
			.heading h3:after {
			    left: 100%;
			    border-left-color: #1565C0;
			} */
        	.diamond{height: 80px;margin: 7px auto;width: 165px;}
        	.diamond:before, .diamond:after{
        		border: 25px solid transparent;
        	}
        	.diamond:before {
			    right: 100%;
			    border-right-color: #fff;
			}
			.diamond:after {
			    left: 100%;
			    border-left-color: #fff;
			}
			.diamond span{width: initial;}
			.map-btn-holder .fa{font-size: 2rem;}
			.map-btn-holder .btn{padding: 0 20px;}
			.map-btn-holder .btn{height: 3rem;padding: 2px 10px;}
			.heading img{margin-top: 37px;width: 125%;}
        }

        /* 2nd demo start */

        .btn-menu-1 {
            overflow: auto;
        }

        .slide {
            letter-spacing: 1px;
            background: #42a5f5;
            background:#9ED1FA;
            background-image: linear-gradient(90deg, red, blue);
            color: white;
            position: relative;
            outline: none;
            border: none;
            height: 80px;
            width: 14em;
            font-size: 18px;
            z-index: 2;
            transition: 0.01s 0.23s ease-out all;
            overflow: hidden;
            border-radius: 5px;
        }

        .slide:before {
            content: "";
            position: absolute;
            left: 0;
            top: 0;
            height: 100%;
            width: 55%;
            background: #f56661;
            background:darkgray;
            background-image: linear-gradient(90deg, red, #71008E);
            z-index: -1;
            transition: 0.3s ease-in all;
        }

        .slide:after {
            content: "";
            position: absolute;
            left: -5%;
            top: 5%;
            height: 90%;
            width: 5%;
            background: #084192;
            background:gray;
            background-image: linear-gradient(90deg, red, #71008E);
            z-index: -1;
            transition: 0.4s 0.02s ease-in all;
        }

        .slide:hover {
            cursor: pointer;
            color: transparent;
            box-shadow: 2px 2px 5px #666, -2px -2px 6px #333, 3px 3px 5px #111;
        }

        .slide:hover:before {
            left: 100%;
            width: 25%;
        }

        .slide:hover:after {
            left: 100%;
            width: 70%;
        }

        .icon-right {
            position: absolute;
            top: 5px;
            right: 35px;
        }

        .icon-right:after {
            font-family: "FontAwesome";
            content: "→";
            font-size: 24px;
            display: inline-block;
            position: relative;
            top: 35px;
            transform: translate3D(0, -50%, 0);
        }

        .signature {
            position: fixed;
            font-family: sans-serif;
            font-weight: 100;
            bottom: 10px;
            left: 0;
            letter-spacing: 4px;
            font-size: 10px;
            width: 100vw;
            text-align: center;
            color: white;
            text-transform: uppercase;
            text-decoration: none;
        }

        .text-align-left {
            text-align: left;
        }

        .mt3em {
            margin-top: 11em;
        }
		 /* .btn-menu ul a li #diamond:hover::after {
            content: "";
            position: absolute;
            width: 50%;
            height: 70%;
            background-color: inherit;
            border-radius: 50%;
            background-color: #42a5f5;
            z-index: -1;
            left: 4.3em;
            top: 2rem;
            animation: ripple 1.5s ease-out infinite;
        }

        @keyframes ripple {
            from {
                opacity: 1;
                transform: scale(0);
            }

            to {
                opacity: 0;
                transform: scale(3);
            } */
        
        /* 2nd demo end */
        /* 3rd demo start */
        main {
	 /* position: relative;
	 height: 95vh;
	 z-index: -10;
	 background: linear-gradient(to top, rgba(27, 29, 29, 1), rgba(255, 255, 255, .75)); */
	 position: absolute;
    height: 100%;
    z-index: 1;
    /* background: linear-gradient(to top, rgba(255, 255, 255, 1), rgba(101, 150, 255, .75)); */
    width: 100%;
    bottom: 2em;
}

}
.btn-menu{
	position:relative;
	margin-top: -6em;
	
}
@media(max-width: 1920px){
.btn-menu{
	position:relative;
	margin-top: -3em;
	z-index: 11;
}
}
@media(max-width: 1560px){
.btn-menu{
	position:relative;
	margin-top: -6em;
}
}
 body {
	 background: linear-gradient(to top, rgba(255, 255, 255, 1), rgba(101, 150, 255, .75));
	 
}
.bg{
	    background-image: url(/pmis/resources/images/new-background-railway-3.png);
	    background-repeat: no-repeat;
	    position: absolute;
	    width: 100%;
	    height: 45%;
	    top: 45%;
	    background-size: cover;
	    z-index: 0;
}
/* .gr-bg{
	background: linear-gradient(to top, rgba(255, 255, 255, 1), rgba(101, 150, 255, .75));
	position: absolute;
    height: 90%;
    width: 100%;
} */

/*  .crane__list, .skyscrappers__list, .tree__container {
	 position: absolute;
	 width: 100%;
	 bottom: 0;
}
 .advice {
	 display: flex;
	 height: 50vh;
	 width: 100vw;
	 flex-flow: column nowrap;
	 justify-content: center;
	 align-items: center;
}
 .advice__title {
	 font-size: 3rem;
	 text-align: center;
}
 .advice__description {
	 margin-top: 1rem;
	 font-size: 2rem;
	 text-align: center;
}
 .advice__description span:first-child {
	 margin-right: -0.7rem;
}
 .advice__description span:last-child {
	 margin-left: -0.7rem;
}
 .city-stuff {
	 display: flex;
	 position: absolute;
	 justify-content: center;
	 width: 100%;
	 height: 100%;
	 bottom: 0em;
	 overflow: hidden;
	 box-shadow: inset 0 -60px 0 -30px rgba(75, 175, 172, 1);
}
 .skyscrappers__list {
	 width: 100%;
	 height: 86.6666666667px;
	 left: 0;
}
 .skyscrappers__list .skyscrapper__item {
	 position: absolute;
	 height: inherit;
	 bottom: 15%;
	 width: 43.3333333333px;
	 background: linear-gradient(115deg, rgba(75, 175, 172, 1) 73%, rgba(60, 139, 137, 1) 73%, rgba(60, 139, 137, 1) 100%);
}
 .skyscrappers__list .skyscrapper__item:after {
	 content: "";
	 position: absolute;
	 width: 80%;
	 height: 80%;
	 left: 10%;
	 bottom: 10%;
	 background: url("/pmis/resources/images/sky-list.png");
}
 .skyscrappers__list .skyscrapper__item:last-child:not(:only-child) {
	 background: rgba(75, 175, 172, 1);
}
 .skyscrappers__list .skyscrapper-1 {
	 width: 121.3333333333px;
	 height: 138.6666666667px;
	 right: 25%;
	 bottom: 0;
	 z-index: 10;
	 transform: rotate(180deg);
}
 @media screen and (max-width: 450px) {
	 .skyscrappers__list .skyscrapper-1 {
		 display: none;
	}
}
 .skyscrappers__list .skyscrapper-2 {
	 width: 60.6666666667px;
	 height: 69.3333333333px;
	 right: 35%;
	 bottom: 0;
	 z-index: 10;
	 transform: rotate(180deg);
	 bottom: 10%;
}
 @media screen and (max-width: 900px) {
	 .skyscrappers__list .skyscrapper-2 {
		 display: none;
	}
}
 @media screen and (max-width: 450px) {
	 .skyscrappers__list .skyscrapper-1 {
		 display: none;
	}
}
 .skyscrappers__list .skyscrapper-3 {
	 width: 40.4444444444px;
	 height: 46.2222222222px;
	 right: 45%;
	 bottom: 0;
	 z-index: 10;
	 transform: rotate(180deg);
	 height: 115.5555555556px;
}
 @media screen and (max-width: 900px) {
	 .skyscrappers__list .skyscrapper-3 {
		 display: none;
	}
}
 @media screen and (max-width: 450px) {
	 .skyscrappers__list .skyscrapper-1 {
		 display: none;
	}
}
 .skyscrappers__list .skyscrapper-4 {
	 width: 30.3333333333px;
	 height: 34.6666666667px;
	 right: 55%;
	 bottom: 0;
	 z-index: 10;
	 transform: rotate(180deg);
	 height: 86.6666666667px;
}
 .skyscrappers__list .skyscrapper-4:after {
	 width: 20%;
	 height: 60%;
	 left: 25%;
}
 @media screen and (max-width: 450px) {
	 .skyscrappers__list .skyscrapper-1 {
		 display: none;
	}
}
 .skyscrappers__list .skyscrapper-5 {
	 width: 24.2666666667px;
	 height: 27.7333333333px;
	 right: 65%;
	 bottom: 0;
	 z-index: 10;
	 transform: rotate(180deg);
	 width: 7%;
	 right: 67%;
	 height: 50%;
	 z-index: 11;
}
 .skyscrappers__list .skyscrapper-5:after {
	 height: 0;
}
 @media screen and (max-width: 450px) {
	 .skyscrappers__list .skyscrapper-1 {
		 display: none;
	}
}
 .crane-cabin, .crane-arm, .crane-picker {
	 transform-origin: 80% center;
	 animation: crane__movement 12s infinite alternate;
}
 .crane__list {
	 width: 260px;
	 height: 173.3333333333px;
	 z-index: 0;
	 perspective: 600px;
}
 .crane__list .crane__item {
	 position: absolute;
	 border: solid 1px rgba(75, 175, 172, 1);
	 border-radius: 2px;
}
 .crane__list .crane-cable {
	 width: 1px;
	 height: 1px;
	 border: none;
	 outline: 1px solid transparent;
	 background: rgba(75, 175, 172, 1);
	 z-index: 0;
}
 .crane__list .crane-cable-1 {
	 width: 60%;
	 top: 0;
	 left: 11%;
	 transform-origin: right 0;
	 animation: cable-1__movement 12s infinite alternate;
}
 .crane__list .crane-cable-2 {
	 width: 19%;
	 top: 0;
	 right: 8%;
	 transform-origin: top left;
	 animation: cable-2__movement 12s infinite alternate;
}
 .crane__list .crane-cable-3 {
	 height: 30%;
	 top: 22%;
	 left: 9%;
	 transform-origin: right center;
	 animation: cable-3__movement 12s ease-in-out infinite alternate;
}
 .crane__list .crane-cable-3:after {
	 content: "";
	 display: block;
	 position: absolute;
	 height: 0.2em;
	 width: 9000%;
	 bottom: 0;
	 left: -4500%;
	 background: rgba(144, 208, 205, 1);
	 border: 1px solid rgba(75, 175, 172, 1);
}
 .crane__list .crane-stand {
	 width: 5%;
	 height: 100%;
	 right: 25%;
	 z-index: 1;
	 background: linear-gradient(to top, rgba(75, 175, 172, 1), rgba(191, 212, 211, 1));
}
 .crane__list .crane-weight {
	 width: 8%;
	 height: 20%;
	 right: 4%;
	 top: 12%;
	 z-index: 2;
	 background: rgba(169, 209, 207, 1);
	 transform-origin: 0 center;
	 animation: crane-weight__movement 12s infinite alternate;
}
 .crane__list .crane-cabin {
	 width: 12%;
	 height: 9%;
	 right: 24%;
	 top: 20%;
	 z-index: 2;
	 background: rgba(169, 209, 207, 1);
}
 .crane__list .crane-cabin:after {
	 content: "";
	 display: block;
	 position: absolute;
	 width: 100%;
	 height: 10%;
	 top: 60%;
	 left: 0;
	 background: rgba(255, 255, 255, 1);
}
 .crane__list .crane-arm {
	 width: 100%;
	 height: 7%;
	 top: 15%;
	 border-top-left-radius: 10px;
	 z-index: 3;
	 background: rgba(169, 209, 207, 1);
}
 .crane-1 {
	 left: 20%;
	 z-index: 10;
}
 .crane-2 {
	 left: 30%;
	 z-index: 10;
	 bottom: -1rem;
	 z-index: -1;
	 transform: scale(0.75) scaleX(-1);
}
 @media screen and (max-width: 900px) {
	 .crane-2 {
		 display: none;
	}
}
 .crane-2 .crane-cable-3 {
	 animation-delay: 3s;
}
 .crane-3 {
	 left: 40%;
	 z-index: 10;
	 bottom: -0.5rem;
	 transform: scale(0.8);
}
 @media screen and (max-width: 900px) {
	 .crane-3 {
		 z-index: -1;
		 transform: scale(0.75) scaleX(-1);
	}
}
 @media screen and (max-width: 900px) {
	 .crane-3 {
		 display: none;
	}
}
 .crane-3 .crane-cable-3 {
	 animation-delay: 4.5s;
}
 .tree__container {
	 width: 100%;
	 height: 62.6666666667px;
	 left: 0;
	 margin-bottom: 4px;
}
 .tree__item {
	 display: flex;
	 flex-flow: column nowrap;
	 position: absolute;
	 justify-content: flex-end;
	 align-items: center;
	 left: 60%;
}
 .tree__trunk {
	 order: 2;
	 display: block;
	 position: absolute;
	 width: 4px;
	 height: 8px;
	 margin-top: 8px;
	 border-radius: 2px;
	 background: rgba(87, 71, 61, 1);
}
 .tree__leaves {
	 order: 1;
	 position: relative;
	 border-top: 0 solid transparent;
	 border-right: 4px solid transparent;
	 border-bottom: 32px solid rgba(75, 175, 172, 1);
	 border-left: 4px solid transparent;
}
 .tree__leaves:after {
	 content: "";
	 position: absolute;
	 height: 100%;
	 left: -4px;
	 border-top: 0;
	 border-right: 0;
	 border-bottom: 32px solid rgba(60, 139, 137, 1);
	 border-left: 4px solid transparent;
}
 .tree-1 {
	 left: 66%;
}
 @media screen and (max-width: 768px) {
	 .tree-1 {
		 display: none;
	}
}
 .tree-2 {
	 left: 67%;
}
 @media screen and (max-width: 768px) {
	 .tree-2 {
		 display: none;
	}
}
 .tree-4 {
	 left: 57%;
}
 .tree-5 {
	 left: 58%;
}
 .tree-7 {
	 left: 51%;
}
 @media screen and (max-width: 450px) {
	 .tree-7 {
		 display: none;
	}
}
 .tree-8 {
	 left: 52%;
}
 @media screen and (max-width: 450px) {
	 .tree-8 {
		 display: none;
	}
}
 @keyframes cable-1__movement {
	 0%, 20% {
		 transform: rotateY(0) rotateZ(-10deg);
	}
	 70%, 100% {
		 transform: rotateY(45deg) rotateZ(-10deg);
	}
}
 @keyframes cable-2__movement {
	 0%, 20% {
		 transform: rotateY(0) rotateZ(29deg);
	}
	 70%, 100% {
		 transform: rotateY(15deg) rotateZ(29deg);
	}
}
 @keyframes cable-3__movement {
	 0% {
		 transform: translate(0, 0);
	}
	 20% {
		 transform: translate(2500%, -18%);
	}
	 60% {
		 transform: translate(11000%, -25%);
	}
	 70% {
		 height: 30%;
		 transform: translate(9100%, -25%);
	}
	 90%, 100% {
		 height: 80%;
		 transform: translate(9100%, -15%);
	}
}
 @keyframes crane__movement {
	 0%, 20% {
		 transform: rotateY(0);
	}
	 70%, 100% {
		 transform: rotateY(45deg);
	}
}
 @keyframes crane-weight__movement {
	 0%, 20% {
		 transform: rotateY(0) translateX(0);
	}
	 70%, 100% {
		 transform: rotateY(45deg) translateX(-50%);
	}
} */
.tran{
	overflow: hidden !important;
	position: absolute;
    width: 100%;
    bottom: 6%;
}
.trans{
	width: 500px;
}
.slide-right {
	-webkit-animation: slide-right 8s ease-in-out infinite alternate-reverse both;
	        animation: slide-right 8s ease-in-out infinite alternate-reverse both;
	        overflow: hidden;
}
@-webkit-keyframes slide-right {
  0% {
    -webkit-transform: translateX(-150%);
            transform: translateX(-150%);
  }
  100% {
    -webkit-transform: translateX(400%);
            transform: translateX(400%);
  }
}
@keyframes slide-right {
  0% {
    -webkit-transform: translateX(-150%);
            transform: translateX(-150%);
  }
  100% {
    -webkit-transform: translateX(400%);
            transform: translateX(400%);
  }
}
.line{
	height: 1px;
	background:#000;
}

.middleText {
  position: fixed;
  top: 35%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  z-index: 1;
}
.down-background{
	position: relative;
    display: contents;
}


/* 3rd demo end */
       
    </style>
</head>

<body>

    <!-- header  starts-->
  <jsp:include page="./layout/header.jsp"></jsp:include>
    <!-- header ends  -->
    
    <!-- li  starts-->

    <!-- li ends  -->

   
   	<!-- <main>
  
  <section class="city-stuff">
    <ul class="skyscrappers__list">
      <li class="skyscrapper__item skyscrapper-1"></li>
      <li class="skyscrapper__item skyscrapper-2"></li>
      <li class="skyscrapper__item skyscrapper-3"></li>
      <li class="skyscrapper__item skyscrapper-4"></li>
      <li class="skyscrapper__item skyscrapper-5"></li>
    </ul>
    <ul class="tree__container">
      <li class="tree__list">
        <ul class="tree__item tree-1">
          <li class="tree__trunk"></li>
          <li class="tree__leaves"></li>
        </ul>
        <ul class="tree__item tree-2">
          <li class="tree__trunk"></li>
          <li class="tree__leaves"></li>
        </ul>
        <ul class="tree__item tree-3">
          <li class="tree__trunk"></li>
          <li class="tree__leaves"></li>
        </ul>  
        <ul class="tree__item tree-4">
          <li class="tree__trunk"></li>
          <li class="tree__leaves"></li>
        </ul>  
        <ul class="tree__item tree-5">
          <li class="tree__trunk"></li>
          <li class="tree__leaves"></li>
        </ul>  
        <ul class="tree__item tree-6">
          <li class="tree__trunk"></li>
          <li class="tree__leaves"></li>
        </ul>  
        <ul class="tree__item tree-7">
          <li class="tree__trunk"></li>
          <li class="tree__leaves"></li>
        </ul>  
        <ul class="tree__item tree-8">
          <li class="tree__trunk"></li>
          <li class="tree__leaves"></li>
        </ul>  
      </li>
    </ul>
    <ul class="crane__list crane-1">
      <li class="crane__item crane-cable crane-cable-1"></li>
      <li class="crane__item crane-cable crane-cable-2"></li>
      <li class="crane__item crane-cable crane-cable-3"></li>
      <li class="crane__item crane-stand"></li>
      <li class="crane__item crane-weight"></li>
      <li class="crane__item crane-cabin"></li>
      <li class="crane__item crane-arm"></li>
    </ul>
    <ul class="crane__list crane-2">
      <li class="crane__item crane-cable crane-cable-1"></li>
      <li class="crane__item crane-cable crane-cable-2"></li>
      <li class="crane__item crane-cable crane-cable-3"></li>
      <li class="crane__item crane-stand"></li>
      <li class="crane__item crane-weight"></li>
      <li class="crane__item crane-cabin"></li>
      <li class="crane__item crane-arm"></li>
    </ul>
    <ul class="crane__list crane-3">
      <li class="crane__item crane-cable crane-cable-1"></li>
      <li class="crane__item crane-cable crane-cable-2"></li>
      <li class="crane__item crane-cable crane-cable-3"></li>
      <li class="crane__item crane-stand"></li>
      <li class="crane__item crane-weight"></li>
      <li class="crane__item crane-cabin"></li>
      <li class="crane__item crane-arm"></li>
    </ul>
  </section>
</main> -->
   		<div class="container middleText">
	        <div class="row" style="width:1000px;">
	            <div class="col s12 m12 l12">
	            	<div class="row">
	            		<div class="col s2 m1 l1">
		            		<div class="heading">
		            			<br><br><br><img src="/pmis/resources/images/mrvclogo.png" alt="Logo">
		            		</div>
	            		</div>
	                <div class="col l10 m10 s9">
	                	<div class="heading color-change-2x">
	                    <center>
	                    
	                    <br><br><br><h3 class="tracking-in-expand-fwd">Mumbai Railway Vikas Corporation</h3></center>
	                </div>
	                </div>
	                <div class="col l1 m1 s1">
	                <div class="map-btn-holder"><br><br><br>
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
	                               <img src="/pmis/resources/images/final_map.png" alt="Map Image" width="100%">
	                            </div>
	                        </div>
	                    </div>
	                    </div>
	            	</div>
	                <div class="btn-menu">
	                    <div class="row">
	                    	<ul class="">
	                    	<c:forEach var="project" items="${projects }">
		                        <a href="javascript:getProjectOverview('${project.project_id }');">
		                        	<li class="col s6 m4 l4 text-align-left">
		                            <div id="diamond">
									    <div class="btn diamond">
									         <span>${project.project_name }</span>
									    </div>
									</div>
		                        </li></a>
	                        </c:forEach>
	                    </ul>
	                    </div>                    
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="down-background">
	    	<div class="bg" id="train"></div>
			   <div class="tran">
			   	 <div class="row">
			   	 	<img src="/pmis/resources/images/train.png" alt="Logo" class="trans slide-right">
			   	 	<div class="line"></div>
			   	 </div>
			   </div>
	    </div>
    
    <form action="<%=request.getContextPath()%>/project-overview" id="projectOverviewForm" method="post">
    	<input type="hidden" id="project_id_overview" name="project_id">
    </form>

<!-- footer starts here  -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
<!-- footer ends here  -->
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
  <script type="text/javascript">
  	
  	$(document).ready(function () {
  		var procedureResult = '${procedureResult}';
  		if($.trim(procedureResult) != ''){
  			alert(procedureResult);
  		}
  		
  		$('#mapmodal').modal({ dismissible: true });
  		
  		var flushHostsSuccess = '${flushHostsSuccess}';
		if($.trim(flushHostsSuccess) != ''){
			swal("Success!", flushHostsSuccess);
		}
		
        var flushHostsError = '${flushHostsError}';
		if($.trim(flushHostsError) != ''){
			swal("Failed!", flushHostsError, "error");
		}
  	});
  	
	function getProjectOverview(project_id){
  		$("#project_id_overview").val(project_id);
  		$("#projectOverviewForm").submit();
  	}
  </script>
</body>

</html>