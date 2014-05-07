<%-- 
    Document   : adminHeader
    Created on : May 6, 2014, 4:30:20 PM
    Author     : Yury
--%>

<%
    HttpSession httpSession = request.getSession();
    if (httpSession == null) {
        response.sendRedirect("login.jsp");
    } else {
        Object object = httpSession.getAttribute("Administrador");
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
                                    <li class="button alt"><a href="admin_system.jsp"> MASCOTAS </a>
                                        <ul>
                                            <li><a href="#">Crear mascota ...</a></li>
                                            <li><a href="#">Modificar mascota</a></li>
                                            <li><a href="#">Eliminar mascota</a></li>
                                        </ul>
                                    </li>                                  


                                    <li class="button alt"><a href="solicitudes.jsp"> - SOLICITUDES - </a>
                                    </li>                                  
                                </ul>
                            </nav>


                        </section>
                        <a href="logoutController" class="button alt pos_text_left"> Salir</a>
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