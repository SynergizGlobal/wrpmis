<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    <!--[if IE 9 ]><html class="ie9"><![endif]-->
   <html>
    <head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error page</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/r83/three.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/latest/TweenMax.min.js"></script>
    <style>
        body {
       			background: url("/wrpmis/resources/images/bg-500.jpg");
           		/* background: #EBE9E9; */
                background-position: center;
			    background-size: cover;
			    background-repeat: no-repeat;
			    background-attachment: fixed;
        }
/* card positon and styling */
        .row {
            display: flex;
            height: 85vh;
        }

        .row>.col {
            margin: auto;
        }

        .card-action a {
            color: blue !important;
            margin-right: 0 !important;
        }

        .card-content * {
            text-shadow: 0px 10px 5px #bbb;
        }

/* color change animation code */
        @keyframes color-change {

            50% {
                color: red;
                transform: scale(1.2);
            }

            100% {
                color: black;
            }
        }
		
		.error-head {
		  position: relative;
		}
		
		.error-head:after {
		  position: absolute;
		  bottom: 0;  
		  height: 100%;
		  width: 100%;
		  content: "";
		  background: linear-gradient(to top,
		     rgb(235, 233, 233, 0.5) 35%, 
		     rgba(255,255,255, 0) 80%
		  );
		  pointer-events: none; /* so the text is still selectable */
		}
        .error-text h1 {
            color: #995152;
            font-size: 60px;
            margin: 12px 0 4px;
            /* animation-name: color-change;
            animation-duration: 1s;
            /* animation-direction: alternate; */
            animation-iteration-count: infinite; */
        }
        .error-text h4{
        	margin: 8px 0 4px;
        	font-size: 25px;
    		letter-spacing: -1px;
    		color: #000;
        }
        .error-text{
        	display: flex;
        	flex-direction: column;
        	align-items: center;
        }
        .error-caption{
        	padding: 20px 40px;
        	color: #995152;
        }
        .error-caption h3{
        	font-size: 36px;
        	margin: 12px 0 8px;
        }
        .error-caption h4{
        	font-size: 24px;
        	margin: 8px 0 12px;
        	line-height: 1.3;
        }
        
        /* game css */
        
        


			#container {
			  width: 100%;
			  height: 100%;
			  position: relative;
			}
			#container #score {
			  position: absolute;
			  top: 200px;
			  width: 100%;
			  text-align: center;
			  font-size: 60px;
			  transition: transform 0.5s ease;
			  color: #333344;
			  transform: translatey(-200px) scale(1);
			}
			#container #game {
			  position: relative;
			  top: 0;
			  right: 0;
			  bottom: 0;
			  left: 0;
			}
			#container .game-over {
			  position: relative;
			  top: 0;
			  left: 0;
			  width: 100%;
			  height: 85%;
			  display: flex;
			  flex-direction: column;
			  align-items: center;
			  justify-content: center;
			}
			#container .game-over * {
			  transition: opacity 0.5s ease, transform 0.5s ease;
			  opacity: 0;
			  transform: translatey(-50px);
			  color: #333344;
			}
			#container .game-over h2 {
			  margin: 0;
			  padding: 0;
			  font-size: 40px;
			}
			#container .game-ready {
			  position: absolute;
			  top: 0;
			  left: 0;
			  width: 100%;
			  height: 100%;
			  display: flex;
			  flex-direction: column;
			  align-items: center;
			  justify-content: space-around;
			}
			#container .game-ready #start-button {
			  transition: opacity 0.5s ease, transform 0.5s ease;
			  opacity: 0;
			  transform: translatey(-50px);
			  border: 3px solid #333344;
			  padding: 10px 20px;
			  background-color: transparent;
			  color: #333344;
			  font-size: 30px;
			  top: 100px;
    			position: absolute;
			}
			#container #instructions {
			  position: absolute;
			  width: 100%;
			  top: 25%;
			  left: 0;
			  text-align: center;
			  transition: opacity 0.5s ease, transform 0.5s ease;
			  opacity: 0;
			}
			#container #instructions.hide {
			  opacity: 0 !important;
			}
			#container.playing #score, #container.resetting #score {
			  transform: translatey(-110px) scale(1);
			}
			#container.playing #instructions {
			  opacity: 1;
			}
			#container.ready .game-ready #start-button {
			  opacity: 1;
			  transform: translatey(0);
			}
			#container.ended #score {
			  transform: translatey(-50px) scale(1);
			}
			#container.ended .game-over * {
			  opacity: 1;
			  transform: translatey(0);
			}
			#container.ended .game-over p {
			  transition-delay: 0.3s;
			}
			.bottom{
				font-size: 16px;
			}
			canvas{
				height: 500px !important;
				position: absolute;
			}
			
			/* hour glass css */
			.hourglassBackground {
				  position: relative;
				  height: 100px;
				  width: 130px;
				  border-radius: 50%;
				  margin: 0px auto;
				}
				
				.hourglassContainer {
				  position: absolute;
				  top: 20px;
				  left: 10px;
				  width: 50px;
				  height: 70px;
				  -webkit-animation: hourglassRotate 2s ease-in 0s infinite;
				          animation: hourglassRotate 2s ease-in 0s infinite;
				  transform-style: preserve-3d;
				  perspective: 1000px;
				}
				.hourglassContainer div,
				.hourglassContainer div:before,
				.hourglassContainer div:after {
				  transform-style: preserve-3d;
				}
				
				@-webkit-keyframes hourglassRotate {
				  0% {
				    transform: rotateX(0deg);
				  }
				  50% {
				    transform: rotateX(180deg);
				  }
				  100% {
				    transform: rotateX(180deg);
				  }
				}
				
				@keyframes hourglassRotate {
				  0% {
				    transform: rotateX(0deg);
				  }
				  50% {
				    transform: rotateX(180deg);
				  }
				  100% {
				    transform: rotateX(180deg);
				  }
				}
				[class^=hourglassCap] {
				  position: absolute;
				  left: 0;
				  height: 5px;
				  width: 50px;
				  background-color: #384A8A;
				}
				[class^=hourglassCap]:before, [class^=hourglassCap]:after {
				  content: "";
				  display: block;
				  position: absolute;
				  border-radius: 50%;
				  width: 50px;
				  height: 50px;
				  transform: rotateX(90deg);
				  background-color: #384A8A;
				}
				
				.hourglassCapTop {
				  top: 0;
				}
				.hourglassCapTop:before {
				  top: -25px;
				}
				.hourglassCapTop:after {
				  top: -20px;
				}
				
				.hourglassCapBottom {
				  bottom: 0;
				}
				.hourglassCapBottom:before {
				  bottom: -25px;
				}
				.hourglassCapBottom:after {
				  bottom: -20px;
				}
				
				.hourglassGlassTop {
				  transform: rotateX(90deg);
				  position: absolute;
				  top: -16px;
				  left: 3px;
				  border-radius: 50%;
				  width: 44px;
				  height: 44px;
				  background-color: #384A8A;
				}
				
				.hourglassGlass {
				  perspective: 100px;
				  position: absolute;
				  top: 32px;
				  left: 20px;
				  width: 10px;
				  height: 6px;
				  background-color: #999999;
				  opacity: 0.5;
				}
				.hourglassGlass:before, .hourglassGlass:after {
				  content: "";
				  display: block;
				  position: absolute;
				  background-color: #999999;
				  left: -17px;
				  width: 44px;
				  height: 28px;
				}
				.hourglassGlass:before {
				  top: -27px;
				  border-radius: 0 0 25px 25px;
				}
				.hourglassGlass:after {
				  bottom: -27px;
				  border-radius: 25px 25px 0 0;
				}
				
				.hourglassCurves:before, .hourglassCurves:after {
				  content: "";
				  display: block;
				  position: absolute;
				  top: 32px;
				  width: 6px;
				  height: 6px;
				  border-radius: 50%;
				  background-color: transparent;
				  -webkit-animation: hideCurves 2s ease-in 0s infinite;
				          animation: hideCurves 2s ease-in 0s infinite;
				}
				.hourglassCurves:before {
				  left: 15px;
				}
				.hourglassCurves:after {
				  left: 29px;
				}
				
				@-webkit-keyframes hideCurves {
				  0% {
				    opacity: 1;
				  }
				  25% {
				    opacity: 0;
				  }
				  30% {
				    opacity: 0;
				  }
				  40% {
				    opacity: 1;
				  }
				  100% {
				    opacity: 1;
				  }
				}
				
				@keyframes hideCurves {
				  0% {
				    opacity: 1;
				  }
				  25% {
				    opacity: 0;
				  }
				  30% {
				    opacity: 0;
				  }
				  40% {
				    opacity: 1;
				  }
				  100% {
				    opacity: 1;
				  }
				}
				.hourglassSandStream:before {
				  content: "";
				  display: block;
				  position: absolute;
				  left: 24px;
				  width: 3px;
				  background-color: #897869;
				  -webkit-animation: sandStream1 2s ease-in 0s infinite;
				          animation: sandStream1 2s ease-in 0s infinite;
				}
				.hourglassSandStream:after {
				  content: "";
				  display: block;
				  position: absolute;
				  top: 36px;
				  left: 19px;
				  border-left: 6px solid transparent;
				  border-right: 6px solid transparent;
				  border-bottom: 6px solid #897869;
				  -webkit-animation: sandStream2 2s ease-in 0s infinite;
				          animation: sandStream2 2s ease-in 0s infinite;
				}
				
				@-webkit-keyframes sandStream1 {
				  0% {
				    height: 0;
				    top: 35px;
				  }
				  50% {
				    height: 0;
				    top: 45px;
				  }
				  60% {
				    height: 35px;
				    top: 8px;
				  }
				  85% {
				    height: 35px;
				    top: 8px;
				  }
				  100% {
				    height: 0;
				    top: 8px;
				  }
				}
				
				@keyframes sandStream1 {
				  0% {
				    height: 0;
				    top: 35px;
				  }
				  50% {
				    height: 0;
				    top: 45px;
				  }
				  60% {
				    height: 35px;
				    top: 8px;
				  }
				  85% {
				    height: 35px;
				    top: 8px;
				  }
				  100% {
				    height: 0;
				    top: 8px;
				  }
				}
				@-webkit-keyframes sandStream2 {
				  0% {
				    opacity: 0;
				  }
				  50% {
				    opacity: 0;
				  }
				  51% {
				    opacity: 1;
				  }
				  90% {
				    opacity: 1;
				  }
				  91% {
				    opacity: 0;
				  }
				  100% {
				    opacity: 0;
				  }
				}
				@keyframes sandStream2 {
				  0% {
				    opacity: 0;
				  }
				  50% {
				    opacity: 0;
				  }
				  51% {
				    opacity: 1;
				  }
				  90% {
				    opacity: 1;
				  }
				  91% {
				    opacity: 0;
				  }
				  100% {
				    opacity: 0;
				  }
				}
				.hourglassSand:before, .hourglassSand:after {
				  content: "";
				  display: block;
				  position: absolute;
				  left: 6px;
				  background-color: #897869;
				  perspective: 500px;
				}
				.hourglassSand:before {
				  top: 8px;
				  width: 39px;
				  border-radius: 3px 3px 30px 30px;
				  -webkit-animation: sandFillup 2s ease-in 0s infinite;
				          animation: sandFillup 2s ease-in 0s infinite;
				}
				.hourglassSand:after {
				  border-radius: 30px 30px 3px 3px;
				  -webkit-animation: sandDeplete 2s ease-in 0s infinite;
				          animation: sandDeplete 2s ease-in 0s infinite;
				}
				
				@-webkit-keyframes sandFillup {
				  0% {
				    opacity: 0;
				    height: 0;
				  }
				  60% {
				    opacity: 1;
				    height: 0;
				  }
				  100% {
				    opacity: 1;
				    height: 17px;
				  }
				}
				
				@keyframes sandFillup {
				  0% {
				    opacity: 0;
				    height: 0;
				  }
				  60% {
				    opacity: 1;
				    height: 0;
				  }
				  100% {
				    opacity: 1;
				    height: 17px;
				  }
				}
				@-webkit-keyframes sandDeplete {
				  0% {
				    opacity: 0;
				    top: 45px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  1% {
				    opacity: 1;
				    top: 45px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  24% {
				    opacity: 1;
				    top: 45px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  25% {
				    opacity: 1;
				    top: 41px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  50% {
				    opacity: 1;
				    top: 41px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  90% {
				    opacity: 1;
				    top: 41px;
				    height: 0;
				    width: 10px;
				    left: 20px;
				  }
				}
				@keyframes sandDeplete {
				  0% {
				    opacity: 0;
				    top: 45px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  1% {
				    opacity: 1;
				    top: 45px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  24% {
				    opacity: 1;
				    top: 45px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  25% {
				    opacity: 1;
				    top: 41px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  50% {
				    opacity: 1;
				    top: 41px;
				    height: 17px;
				    width: 38px;
				    left: 6px;
				  }
				  90% {
				    opacity: 1;
				    top: 41px;
				    height: 0;
				    width: 10px;
				    left: 20px;
				  }
				}
				.d-flex{
					display: flex;
				}
				.align-center{
					align-items: center;
				}
				.align-end{
					align-items: end;
				}
				.w-max-content{
					width: max-content;
				}
    </style>
</head>

<body>

    <!-- header  starts-->
    <nav>
        <div class="nav-wrapper blue darken-3">
            <div class="">
                <a href="<%=request.getContextPath() %>/home" class="brand-logo fs"><img src="/wrpmis/resources/images/mrvclogo.png" alt="Logo"> WR PMIS</a>
                <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="fa fa-bars"></i></a>

            </div>
        </div>
    </nav>

    <!-- header ends  -->
    
<!-- error card in single row  -->
    <%-- <div class="row">
        <div class="col s12 m3">
            <div class="card center-align">
                <div class="card-content">
                     <h1>500</h1>
                    <h6>Server Error. Try Later</h6>
                </div>
                <div class="card-action">
                    <a href="<%=request.getContextPath()%>/">Go to Home</a>
                </div>
            </div>
        </div>
    </div> --%>
    <div class="server-error">
    	<div class="error-text">
    		<div class="error-head">
    		<div class="d-flex align-center">
    			<h1>500</h1>
   				
    		</div>
    			<h4>Internal Server ERROR</h4>
    		</div>
    	</div>
    	<div class="error-caption">
    		<div class="d-flex align-end w-max-content">
    			<h3>Something went wrong...</h3>
    			<div class="hourglassBackground">
				  <div class="hourglassContainer">
				    <div class="hourglassCurves"></div>
				    <div class="hourglassCapTop"></div>
				    <div class="hourglassGlassTop"></div>
				    <div class="hourglassSand"></div>
				    <div class="hourglassSandStream"></div>
				    <div class="hourglassCapBottom"></div>
				    <div class="hourglassGlass"></div>
				  </div>
				</div>
    		</div>
    		
    		<h4>Its not you, it's Us. Promise we will get back to You.</h4>
    	</div>
    </div>


<!-- <div id="container">
	<div id="game"></div>
	<div id="score">0</div>
	<div id="instructions">Click (or press the spacebar) to place the block</div>
	<div class="game-over">
		<h2>Game Over</h2>
		<p>You did great, you're the best.</p>
		<p>Click or spacebar to start again</p>
	</div>
	<div class="game-ready">
		<div id="start-button">Start</div>
		<div></div>
	</div>
</div> -->


    <!-- footer  -->
    <div class="footer-container">
        <div class="footer-copyright blue lighten-5 bottom">
            <div class="container">
                <span>Copyright 2020 @ mrvc.indianrailways.gov.in | Designed & Developed by</span> <img
                    src="/wrpmis/resources/images/synergiz.png" class="footer-img" alt="footer image">
            </div>
        </div>
    </div>
    
    
    <script>
    "use strict";
    console.clear();
    class Stage {
        constructor() {
            // container
            this.render = function () {
                this.renderer.render(this.scene, this.camera);
            };
            this.add = function (elem) {
                this.scene.add(elem);
            };
            this.remove = function (elem) {
                this.scene.remove(elem);
            };
            this.container = document.getElementById('game');
            // renderer
            this.renderer = new THREE.WebGLRenderer({
                antialias: true,
                alpha: false
            });
            this.renderer.setSize(window.innerWidth, window.innerHeight);
            this.renderer = new THREE.WebGLRenderer({ alpha: true });
            this.renderer.setClearColor(0xffffff, 0);
            this.container.appendChild(this.renderer.domElement);
            // scene
            this.scene = new THREE.Scene();
            // camera
            let aspect = window.innerWidth / window.innerHeight;
            let d = 20;
            this.camera = new THREE.OrthographicCamera(-d * aspect, d * aspect, d, -d, -100, 1000);
            this.camera.position.x = 2;
            this.camera.position.y = 2;
            this.camera.position.z = 2;
            this.camera.lookAt(new THREE.Vector3(0, 0, 0));
            //light
            this.light = new THREE.DirectionalLight(0xffffff, 0.5);
            this.light.position.set(0, 499, 0);
            this.scene.add(this.light);
            this.softLight = new THREE.AmbientLight(0xffffff, 0.4);
            this.scene.add(this.softLight);
            window.addEventListener('resize', () => this.onResize());
            this.onResize();
        }
        setCamera(y, speed = 0.3) {
            TweenLite.to(this.camera.position, speed, { y: y + 4, ease: Power1.easeInOut });
            TweenLite.to(this.camera.lookAt, speed, { y: y, ease: Power1.easeInOut });
        }
        onResize() {
            let viewSize = 30;
            this.renderer.setSize(window.innerWidth, window.innerHeight);
            this.camera.left = window.innerWidth / -viewSize;
            this.camera.right = window.innerWidth / viewSize;
            this.camera.top = window.innerHeight / viewSize;
            this.camera.bottom = window.innerHeight / -viewSize;
            this.camera.updateProjectionMatrix();
        }
    }
    class Block {
        constructor(block) {
            // set size and position
            this.STATES = { ACTIVE: 'active', STOPPED: 'stopped', MISSED: 'missed' };
            this.MOVE_AMOUNT = 12;
            this.dimension = { width: 0, height: 0, depth: 0 };
            this.position = { x: 0, y: 0, z: 0 };
            this.targetBlock = block;
            this.index = (this.targetBlock ? this.targetBlock.index : 0) + 1;
            this.workingPlane = this.index % 2 ? 'x' : 'z';
            this.workingDimension = this.index % 2 ? 'width' : 'depth';
            // set the dimensions from the target block, or defaults.
            this.dimension.width = this.targetBlock ? this.targetBlock.dimension.width : 10;
            this.dimension.height = this.targetBlock ? this.targetBlock.dimension.height : 2;
            this.dimension.depth = this.targetBlock ? this.targetBlock.dimension.depth : 10;
            this.position.x = this.targetBlock ? this.targetBlock.position.x : 0;
            this.position.y = this.dimension.height * this.index;
            this.position.z = this.targetBlock ? this.targetBlock.position.z : 0;
            this.colorOffset = this.targetBlock ? this.targetBlock.colorOffset : Math.round(Math.random() * 100);
            // set color
            if (!this.targetBlock) {
                this.color = 0x333344;
            }
            else {
                let offset = this.index + this.colorOffset;
                var r = Math.sin(0.3 * offset) * 55 + 200;
                var g = Math.sin(0.3 * offset + 2) * 55 + 200;
                var b = Math.sin(0.3 * offset + 4) * 55 + 200;
                this.color = new THREE.Color(r / 255, g / 255, b / 255);
            }
            // state
            this.state = this.index > 1 ? this.STATES.ACTIVE : this.STATES.STOPPED;
            // set direction
            this.speed = -0.1 - (this.index * 0.005);
            if (this.speed < -4)
                this.speed = -4;
            this.direction = this.speed;
            // create block
            let geometry = new THREE.BoxGeometry(this.dimension.width, this.dimension.height, this.dimension.depth);
            geometry.applyMatrix(new THREE.Matrix4().makeTranslation(this.dimension.width / 2, this.dimension.height / 2, this.dimension.depth / 2));
            this.material = new THREE.MeshToonMaterial({ color: this.color, shading: THREE.FlatShading });
            this.mesh = new THREE.Mesh(geometry, this.material);
            this.mesh.position.set(this.position.x, this.position.y + (this.state == this.STATES.ACTIVE ? 0 : 0), this.position.z);
            if (this.state == this.STATES.ACTIVE) {
                this.position[this.workingPlane] = Math.random() > 0.5 ? -this.MOVE_AMOUNT : this.MOVE_AMOUNT;
            }
        }
        reverseDirection() {
            this.direction = this.direction > 0 ? this.speed : Math.abs(this.speed);
        }
        place() {
            this.state = this.STATES.STOPPED;
            let overlap = this.targetBlock.dimension[this.workingDimension] - Math.abs(this.position[this.workingPlane] - this.targetBlock.position[this.workingPlane]);
            let blocksToReturn = {
                plane: this.workingPlane,
                direction: this.direction
            };
            if (this.dimension[this.workingDimension] - overlap < 0.3) {
                overlap = this.dimension[this.workingDimension];
                blocksToReturn.bonus = true;
                this.position.x = this.targetBlock.position.x;
                this.position.z = this.targetBlock.position.z;
                this.dimension.width = this.targetBlock.dimension.width;
                this.dimension.depth = this.targetBlock.dimension.depth;
            }
            if (overlap > 0) {
                let choppedDimensions = { width: this.dimension.width, height: this.dimension.height, depth: this.dimension.depth };
                choppedDimensions[this.workingDimension] -= overlap;
                this.dimension[this.workingDimension] = overlap;
                let placedGeometry = new THREE.BoxGeometry(this.dimension.width, this.dimension.height, this.dimension.depth);
                placedGeometry.applyMatrix(new THREE.Matrix4().makeTranslation(this.dimension.width / 2, this.dimension.height / 2, this.dimension.depth / 2));
                let placedMesh = new THREE.Mesh(placedGeometry, this.material);
                let choppedGeometry = new THREE.BoxGeometry(choppedDimensions.width, choppedDimensions.height, choppedDimensions.depth);
                choppedGeometry.applyMatrix(new THREE.Matrix4().makeTranslation(choppedDimensions.width / 2, choppedDimensions.height / 2, choppedDimensions.depth / 2));
                let choppedMesh = new THREE.Mesh(choppedGeometry, this.material);
                let choppedPosition = {
                    x: this.position.x,
                    y: this.position.y,
                    z: this.position.z
                };
                if (this.position[this.workingPlane] < this.targetBlock.position[this.workingPlane]) {
                    this.position[this.workingPlane] = this.targetBlock.position[this.workingPlane];
                }
                else {
                    choppedPosition[this.workingPlane] += overlap;
                }
                placedMesh.position.set(this.position.x, this.position.y, this.position.z);
                choppedMesh.position.set(choppedPosition.x, choppedPosition.y, choppedPosition.z);
                blocksToReturn.placed = placedMesh;
                if (!blocksToReturn.bonus)
                    blocksToReturn.chopped = choppedMesh;
            }
            else {
                this.state = this.STATES.MISSED;
            }
            this.dimension[this.workingDimension] = overlap;
            return blocksToReturn;
        }
        tick() {
            if (this.state == this.STATES.ACTIVE) {
                let value = this.position[this.workingPlane];
                if (value > this.MOVE_AMOUNT || value < -this.MOVE_AMOUNT)
                    this.reverseDirection();
                this.position[this.workingPlane] += this.direction;
                this.mesh.position[this.workingPlane] = this.position[this.workingPlane];
            }
        }
    }
    class Game {
        constructor() {
            this.STATES = {
                'LOADING': 'loading',
                'PLAYING': 'playing',
                'READY': 'ready',
                'ENDED': 'ended',
                'RESETTING': 'resetting'
            };
            this.blocks = [];
            this.state = this.STATES.LOADING;
            this.stage = new Stage();
            this.mainContainer = document.getElementById('container');
            this.scoreContainer = document.getElementById('score');
            this.startButton = document.getElementById('start-button');
            this.instructions = document.getElementById('instructions');
            this.scoreContainer.innerHTML = '0';
            this.newBlocks = new THREE.Group();
            this.placedBlocks = new THREE.Group();
            this.choppedBlocks = new THREE.Group();
            this.stage.add(this.newBlocks);
            this.stage.add(this.placedBlocks);
            this.stage.add(this.choppedBlocks);
            this.addBlock();
            this.tick();
            this.updateState(this.STATES.READY);
            document.addEventListener('keydown', e => {
                if (e.keyCode == 32)
                    this.onAction();
            });
            document.addEventListener('click', e => {
                this.onAction();
            });
            document.addEventListener('touchstart', e => {
                e.preventDefault();
                // this.onAction();
                // ☝️ this triggers after click on android so you
                // insta-lose, will figure it out later.
            });
        }
        updateState(newState) {
            for (let key in this.STATES)
                this.mainContainer.classList.remove(this.STATES[key]);
            this.mainContainer.classList.add(newState);
            this.state = newState;
        }
        onAction() {
            switch (this.state) {
                case this.STATES.READY:
                    this.startGame();
                    break;
                case this.STATES.PLAYING:
                    this.placeBlock();
                    break;
                case this.STATES.ENDED:
                    this.restartGame();
                    break;
            }
        }
        startGame() {
            if (this.state != this.STATES.PLAYING) {
                this.scoreContainer.innerHTML = '0';
                this.updateState(this.STATES.PLAYING);
                this.addBlock();
            }
        }
        restartGame() {
            this.updateState(this.STATES.RESETTING);
            let oldBlocks = this.placedBlocks.children;
            let removeSpeed = 0.2;
            let delayAmount = 0.02;
            for (let i = 0; i < oldBlocks.length; i++) {
                TweenLite.to(oldBlocks[i].scale, removeSpeed, { x: 0, y: 0, z: 0, delay: (oldBlocks.length - i) * delayAmount, ease: Power1.easeIn, onComplete: () => this.placedBlocks.remove(oldBlocks[i]) });
                TweenLite.to(oldBlocks[i].rotation, removeSpeed, { y: 0.5, delay: (oldBlocks.length - i) * delayAmount, ease: Power1.easeIn });
            }
            let cameraMoveSpeed = removeSpeed * 2 + (oldBlocks.length * delayAmount);
            this.stage.setCamera(2, cameraMoveSpeed);
            let countdown = { value: this.blocks.length - 1 };
            TweenLite.to(countdown, cameraMoveSpeed, { value: 0, onUpdate: () => { this.scoreContainer.innerHTML = String(Math.round(countdown.value)); } });
            this.blocks = this.blocks.slice(0, 1);
            setTimeout(() => {
                this.startGame();
            }, cameraMoveSpeed * 1000);
        }
        placeBlock() {
            let currentBlock = this.blocks[this.blocks.length - 1];
            let newBlocks = currentBlock.place();
            this.newBlocks.remove(currentBlock.mesh);
            if (newBlocks.placed)
                this.placedBlocks.add(newBlocks.placed);
            if (newBlocks.chopped) {
                this.choppedBlocks.add(newBlocks.chopped);
                let positionParams = { y: '-=30', ease: Power1.easeIn, onComplete: () => this.choppedBlocks.remove(newBlocks.chopped) };
                let rotateRandomness = 10;
                let rotationParams = {
                    delay: 0.05,
                    x: newBlocks.plane == 'z' ? ((Math.random() * rotateRandomness) - (rotateRandomness / 2)) : 0.1,
                    z: newBlocks.plane == 'x' ? ((Math.random() * rotateRandomness) - (rotateRandomness / 2)) : 0.1,
                    y: Math.random() * 0.1,
                };
                if (newBlocks.chopped.position[newBlocks.plane] > newBlocks.placed.position[newBlocks.plane]) {
                    positionParams[newBlocks.plane] = '+=' + (40 * Math.abs(newBlocks.direction));
                }
                else {
                    positionParams[newBlocks.plane] = '-=' + (40 * Math.abs(newBlocks.direction));
                }
                TweenLite.to(newBlocks.chopped.position, 1, positionParams);
                TweenLite.to(newBlocks.chopped.rotation, 1, rotationParams);
            }
            this.addBlock();
        }
        addBlock() {
            let lastBlock = this.blocks[this.blocks.length - 1];
            if (lastBlock && lastBlock.state == lastBlock.STATES.MISSED) {
                return this.endGame();
            }
            
            this.scoreContainer.innerHTML = String(this.blocks.length - 1);
            let newKidOnTheBlock = new Block(lastBlock);
            this.newBlocks.add(newKidOnTheBlock.mesh);
            this.blocks.push(newKidOnTheBlock);
            this.stage.setCamera(this.blocks.length * 2);
            if (this.blocks.length >= 5){
            	 this.instructions.classList.add('hide');
            }
            else if(this.state != this.STATES.PLAYING){
            	this.scoreContainer.innerHTML = '0';
            }
               
        }
        endGame() {
            this.updateState(this.STATES.ENDED);
        }
        tick() {
            this.blocks[this.blocks.length - 1].tick();
            this.stage.render();
            requestAnimationFrame(() => { this.tick(); });
        }
    }
    let game = new Game();






    </script>

</body>

</html>
