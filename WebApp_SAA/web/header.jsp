<%@page import="Entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
        Dopetrope 2.5 by HTML5 UP
        html5up.net | @n33co
        Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
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

                            <!-- Nav -->
                            <nav id="nav">
                                <ul>
                                    <li class="current_page_item"><a href="index.jsp">BIENVENIDO</a></li>
                                    <li class="current_page_item"><a href="login_user.jsp">MI CUENTA</a></li>
                                    <li>
                                        <a href="">MASCOTAS</a>
                                        <ul>
                                            <li><a href="adoptar.jsp">Adopta</a></li>
                                            <li><a href="#">Adoptados</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">CONTACTO</a></li>
                                </ul>
                            </nav>

                        </section>
                    </div>
                </div>
                <div class="row">
                    <h3>
                    <% if (session.getAttribute("Usuario") != null) {
                        Usuario us = (Usuario)session.getAttribute("Usuario");
                        out.println(us.getNombres() + " " + us.getApellidos());
                    }%>
                    </h3>
                </div>
                <div class="row">
                    <div class="12u">