<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome to PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
   <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
   
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
       
   <!--  <style>
        .input-field .select2-container--default {
            width: 100% !important;
        }

        /* searchable dropdown styling */
        .select2-container--default .select2-selection--single {
            background-color: lightgrey;
            border: 1px solid #aaa;
            height: 32px;
            /* border-radius: 2px; */
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }

        /* datatable styling starts here*/
        .dataTables_filter label::after {
            position: relative;
            content: '\f002';
            color: #6C587B;
            right: 15px;
            font: normal normal normal 14px/1 FontAwesome;
        }

        .dataTables_filter label {
            color: #fff;
        }

        /* datatable stylings ends here*/
        
        
          h4 {
		    font-size: 2.28rem;
		    line-height: 110%;
		    margin: 0 0 .912rem 0;
		}
		
		.card .card-content {
		    padding: 5px 24px;
		    border-radius: 0 0 2px 2px;
		}
	.no-mar{
		margin-bottom:0;
	}
    </style> -->
    
     <style>
        .card-title {
            text-transform: capitalize;
        }

        .card.main-clr {
            background-color: #fdfdfd ;
            border-radius: 10px !important;
        }

        .no-mar .row {
            margin-bottom: 0;
        }

        .hidden {
            display: none;
        }

        .card.main-clr.active,
        .card.sec-clr {
            background-color: #DAE8F9;
        }

        .card.main-clr.active {
            transition: background-color 0.3s ease-in-out;
        }

        .card.sec-clr .card.main-clr.active,
        .card.sec-clr .card.main-clr,
        .card.sec-clr .card.sec-clr {
            background-color: #f0f7ff ;
        }

        .card-title .material-icons {
            font-size: 1.5rem;
            cursor: pointer;
        }

        .active .card-content:after {
            content: "";
            position: absolute;
            top: 100%;
            left: 48%;
            margin-left: -5px;
            border-width: 15px;
            border-style: solid;
            border-color: #DAE8F9 transparent transparent transparent;
            -webkit-filter: drop-shadow(0px 2px 1px rgba(0, 0, 0, 0.35));
            filter: drop-shadow(0px 2px 1px rgba(0, 0, 0, 0.35));
        }

        .card.sec-clr .card.main-clr.active .card-content:after {
            border-color: #f0f7ff transparent transparent transparent;
        }
      
           .card.main-clr {
            min-height: 180px;
        }

        .card.main-clr .line {
            display: flex;
            padding: 1px;
            justify-content: space-between;
            border-bottom: 1px solid #eee;
        }

        .card.main-clr .line::after {
            clear: both;
        }

        .card.main-clr .line>.alignleft {
            float: left;
            width: 48.33333%;
            text-align: left;
        }

        .card.main-clr .line>.aligncenter {
            float: left;
            width: 3.33333%;
            text-align: center;
        }

        .card.main-clr .line>.alignright {
            float: left;
            width: 48.33333%;
            text-align: right;
        }
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

<!--   <div class="row no-mar">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content" style="height: 660px;">
                    <span class="card-title ">
                        <div class="center-align p-2">
                            <h4> Welcome to MRVC PMIS </h4>
                            <img src="/pmis/resources/images/mrvclogo.png" alt="lgo" style="width: 10%;">
                        </div>
                    </span>
                </div>
            </div>
        </div>
    </div>
     -->
     
        	
    <div class="container-fluid no-mar" style="margin-bottom:40px">
        <div class="row">
            <div class="col m1 hide-on-small"></div>
            <div class="col m10 s12">
                <div class="row">
                    <div class="col s12 m4">
                        <div class="card main-clr" data-row="1" onclick="showdiv(this,1)">
                            <div class="card-content">
                                <span class="card-title center-align">MUTP - I</span>
                                
                                <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m4">
                        <div class="card main-clr" data-row="1" onclick="showdiv(this,1)">
                            <div class="card-content">
                                <span class="card-title center-align">MUTP - II</span>
                                <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sanctioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2019</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Latest Revised Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">1,90,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Projected Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m4">
                        <div class="card main-clr" data-row="1" onclick="showdiv(this,1)">
                            <div class="card-content">
                                <span class="card-title center-align">MUTP - II B</span>
                                  <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col m1 hide-on-small"></div>
        </div>

        <div class="row result hidden" id="result1">
            <div class="col m1 hide-on-small"></div>
            <div class="col m10 s12">
                <div class="row">
                    <div class="col s12 m12">
                        <div class="card sec-clr">
                            <div class="card-content">
                                <div class="card-title"> &nbsp;
                                    <span class="right">
                                        <span class="material-icons" onclick="closeDiv(1)">close</span>
                                    </span>
                                </div>
                                <div class="row">
                                    <div class="col s12 m4">
                                        <div class="card main-clr" >
                                            <div class="card-content">
                                                <span class="card-title center-align">thane diva </span>
                                                  <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s12 m4">
                                        <div class="card main-clr" >
                                            <div class="card-content">
                                                <span class="card-title center-align">harbour line</span>
                                                  <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s12 m4">
                                        <div class="card main-clr" >
                                            <div class="card-content">
                                                <span class="card-title center-align">stabling lines</span>
                                                   <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col s12 m4">
                                        <div class="card main-clr" >
                                            <div class="card-content">
                                                <span class="card-title center-align">dc to ac conversion</span>
                                                   <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>                              
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col m1 hide-on-small"></div>
        </div>

        <div class="row">
            <div class="col m1 hide-on-small"></div>
            <div class="col m10 s12">
                <div class="row">
                    <div class="col s12 m4">
                        <div class="card main-clr" data-row="2" onclick="showdiv(this,2)">
                            <div class="card-content">
                                <span class="card-title center-align">MUTP - III</span>
                                   <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m4">
                        <div class="card main-clr" data-row="2" onclick="showdiv(this,2)">
                            <div class="card-content">
                                <span class="card-title center-align">MUTP - III A</span>
                                  <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m4">
                        <div class="card main-clr" data-row="2" onclick="showdiv(this,2)">
                            <div class="card-content">
                                <span class="card-title center-align">PH-53 FOB</span>
                                  <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col m1 hide-on-small"></div>
        </div>

        <div class="row hidden result" id="result2">
            <div class="col m1 hide-on-small"></div>
            <div class="col m10 s12">
                <div class="row">
                    <div class="col s12 m12">
                        <div class="card sec-clr">
                            <div class="card-content">
                                <div class="card-title">
                                    <span class="right">
                                        <span class="material-icons" onclick="closeDiv(2)">close</span>
                                    </span>
                                </div>
                                <p>MUTP - II B Data</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col m1 hide-on-small"></div>
        </div>

        <div class="row">
            <div class="col m1 hide-on-small"></div>
            <div class="col m10 s12">
                <div class="row">
                    <div class="col s12 m4">
                        <div class="card main-clr" data-row="3" onclick="showdiv(this,3)">
                            <div class="card-content">
                                <span class="card-title center-align">PH-53 FOB WR</span>
                                  <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m4">
                        <div class="card main-clr" data-row="3" onclick="showdiv(this,3)">
                            <div class="card-content">
                                <span class="card-title center-align">Other Works</span>
                                 <div class="line">
                                    <p class="alignleft">Origional Sanctioned Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,00,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Sactioned Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2018</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Cost</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2,10,00,000</p>
                                </div>
                                <div class="line">
                                    <p class="alignleft">Completion Year</p>
                                    <p class="aligncenter">:</p>
                                    <p class="alignright">2020</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col m1 hide-on-small"></div>
        </div>

        <div class="row hidden result" id="result3">
            <div class="col m1 hide-on-small"></div>
            <div class="col m10 s12">
                <div class="row">
                    <div class="col s12 m12">
                        <div class="card sec-clr">
                            <div class="card-content">
                                <div class="card-title">
                                    <span class="right">
                                        <span class="material-icons" onclick="closeDiv(3)">close</span>
                                    </span>
                                </div>
                                <p>MUTP - II B Data</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col m1 hide-on-small"></div>
        </div>

    </div>
    


  <!-- footer included -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
    		
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>

  <script>
        function showdiv(present, no) {
            $('.card.main-clr.active').each(function () {
                if ($(this).attr('data-row') == no) {
                    $(this).removeClass('active');
                }
            });
            $(present).addClass('active');
            $('#result' + no).removeClass('hidden');           
        }
        function closeDiv(no) {
            $('.card.main-clr.active').each(function () {
                if ($(this).attr('data-row') == no) {
                    $(this).removeClass('active');
                }
            });
            $('#result' + no).addClass('hidden');
        }      

    </script>
    
</body>

</html>