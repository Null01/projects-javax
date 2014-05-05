<%-- 
    Document   : admin_system
    Created on : 28/04/2014, 12:50:49 AM
    Author     : duran
--%>
<%@page import="Facade.ControllerJPAMascota"%>
<%@page import="Entities.Mascota"%>
<%@page import="java.util.List"%>
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
        <link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/ui-darkness/jquery-ui.css" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/config.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-panels.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
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


                        <style>
                            table,th,td {                                
                                width:900px;
                                border:1px solid black;
                                text-align:center;
                                border-collapse: collapse;
                                padding-top: .5em;
                                padding-bottom: .5em;
                            }
                        </style>

                        <script type="text/javascript">
                            $(document).ready(function() {
                                var now = new Date();

                                var day = ("0" + now.getDate()).slice(-2);
                                var month = ("0" + (now.getMonth() + 1)).slice(-2);
                                var today = now.getFullYear() + "-" + (month) + "-" + (day);

                                $('#date_min').val(today);
                                $('#date_max').val(today);

                            });
                        </script>

                        <%
                         /*   ControllerJPAMascota controllerJPAMascota = new ControllerJPAMascota();
                           List<Mascota> l = controllerJPAMascota.getListMascotas();

                            if (l == null) {
                                out.print("null");
                            } else {
                                out.print("not null");
                            }*/

                        %>



                        <!-- Intro -->
                        <section id="intro">
                            <div align="center">
                                <form id="formView" action="" >

                                    <b> Filtro de busqueda: </b> 
                                    <input id="date_min" type="date" name="date_min" style="width:220px ;height:25px"> <b> a </b>
                                    <input id="date_max" type="date" name="date_max" style="width:220px ;height:25px">
                                    <button type="submit" style="width:100px; height:30px">
                                        <i> <b>Buscar. </b></i>
                                    </button>
                                    <table>
                                        <tr>
                                            <td><h1> NOMBRE </h1></td>
                                            <td><h1> EDAD </h1></td>
                                            <td><h1> </h1></td>
                                            <td><h1> </h1></td>
                                        </tr>
                                        <tr>
                                            <td> NOMBRE A </td>
                                            <td> NOMBRE B </td>
                                            <td>
                                                <button type="submit" style="width:65px; height:30px">
                                                    <i class="fa fa-refresh fa-spin"> </i>
                                                </button>
                                            </td>

                                            <td>
                                                <button type="submit" style="width:65px; height:30px">
                                                    <i class="fa fa-trash-o fa-lg"> </i>
                                                </button>
                                            </td>
                                        </tr>  
                                    </table>
                                    <br/><br/>
                                    <button id="btn-create" type="button" style="width:100px; height:30px">
                                        <i class="fa fa-user fa-fw"> <b>Crear. </b></i>
                                    </button>

                                </form>

                            </div>

                            <div id="dialog" title="CREAR UNA MASCOTA">
                                <form id="form-create" action="">
                                    <table style="width:450px">
                                        <tr>
                                            <td> NOMBRE A </td>
                                            <td> <input type="text" name="nombre" ></td>
                                        </tr>  

                                    </table>
                                    <button id="btn-create-submit" type="submit"  style="width:100px; height:30px; text-align:center; ">
                                        <i>Crear </i>
                                    </button>


                                    asdasd
                                </form>

                            </div>

                            <script>
                                $(function() {
                                    $("#dialog").dialog({
                                        autoOpen: false,
                                        modal: true,
                                        resizable: false,
                                        width: 'auto',
                                        dialogClass: "dlg-no-close"
                                    });
                                    $("#btn-create").on("click", function() {
                                        $("#dialog").dialog("open");
                                    });
                                });
                            </script>

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