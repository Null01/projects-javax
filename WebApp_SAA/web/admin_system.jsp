<%-- 
    Document   : admin_system
    Created on : 28/04/2014, 12:50:49 AM
    Author     : duran
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
        Dopetrope 2.5 by HTML5 UP
        html5up.net | @n33co
        Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->

<%
    HttpSession httpSession = request.getSession(false);
    if (httpSession == null) {
        response.sendRedirect("login.jsp");
    } else {
        Object object = httpSession.getAttribute("session");
        if (object == null) {
            response.sendRedirect("login.jsp");
        }
    }
%>
<html>
    <head>
        <title>SAA</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,900,300italic" rel="stylesheet" />
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/config.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-panels.min.js"></script>
        <noscript>
        <link rel="stylesheet" href="css/skel-noscript.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-desktop.css" />
        <link rel="stylesheet" href="css/font-awesome.css" />
        </noscript>
        <!--[if lte IE 8]><script src="js/html5shiv.js"></script><link rel="stylesheet" href="css/ie8.css" /><![endif]-->
    </head>
    <body class="homepage">
        <!-- Header Wrapper -->
        <div id="header-wrapper">
            <div class="container">
                <div class="row">
                    <div class="12u">

                        <!-- Header -->
                        <section id="header">

                            <!-- Logo -->
                            <h1><a href="#">SISTEMA DE ADOPCION ANIMALES</a></h1>

                            <!-- navegate -->
                            <nav id="nav">
                                <ul>
                                    <li class="button alt"><a href="#"> MASCOTA </a>
                                        <ul>
                                            <li><a href="#">Crear mascota ...</a></li>
                                            <li><a href="#">Modificar mascota</a></li>
                                            <li><a href="#">Eliminar mascota</a></li>
                                        </ul>
                                    </li>                                  


                                    <li class="button alt"><a href="#"> - LOCK - </a>
                                    <li class="button alt"><a href="#"> - LOCK - </a>
                                    <li class="button alt"><a href="#"> - LOCK - </a>
                                    <li class="button alt"><a href="#"> - LOCK - </a>
                                    </li>                                  
                                </ul>
                            </nav>


                        </section>
                        <a href="loginController" class="button alt pos_text_left"> Salir</a>
                    </div>
                </div>
                <div class="row">

                </div>
                <div class="row">
                    <div class="12u">

                        <!-- Intro -->
                        <section id="intro">
                            <div  align="center">
                                <form id="formView" action="" >
                                    <table style="width:400px" >
                                        <tr>
                                            <td><h1> NOMBRE </h1></td>
                                            <td><h1> EDAD </h1></td>
                                        </tr>

                                        <tr>
                                            <td> NOMBRE A </td>
                                            <td> NOMBRE B </td>
                                            <td>
                                                <button type="submit"  style="width:65px; height:30px">
                                                    <i class="fa fa-spinner fa-spin"> </i>
                                                </button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> NOMBRE A </td>
                                            <td> NOMBRE B </td>
                                            <td>

                                            </td>
                                        </tr>  
                                    </table>
                                </form>
                            </div>


                            <!--
                            
                            <div class="row">
                                <div class="4u">
                                    <section class="middle">
                                        <span class="pennant pennant-alt"><span class="fa fa-cog"></span></span>
                                        <header>
                                            <h2>Developing</h2>
                                        </header>                                            
                                    </section>
                                </div>
                                <div class="4u">
                                    <section class="middle">
                                        <span class="pennant pennant-alt"><span class="fa fa-flash"></span></span>
                                        <header>
                                            <h2>Développement</h2>
                                        </header>                                            
                                    </section>
                                </div>
                                <div class="4u">
                                    <section class="last">
                                        <span class="pennant pennant-alt"><span class="fa fa-star"></span></span>
                                        <header>
                                            <h2>Developing</h2>
                                        </header>                                          
                                    </section>
                                </div>
                            </div> -->




                        </section>

                    </div>
                </div>
            </div>
        </div>

        <!-- Main Wrapper -->

        <!-- Footer Wrapper -->
        <div id="footer-wrapper">

            <!-- Footer -->
            <section id="footer" class="container">
            </section>    
        </div>

    </body>
</html>