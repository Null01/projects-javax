<%-- 
    Document   : contact
    Created on : 08-mar-2015, 17:57:33
    Author     : andresfelipegarciaduran
--%>

<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>

<%
    ServletContext contexto = getServletContext();
    Integer usuarioConectados = null;
    synchronized (contexto) {
        usuarioConectados = (Integer) contexto.getAttribute("usuariosConectados");
    }
%>

<html lang="en">
    <head>
        <title>Menu</title>
        <meta charset="utf-8">
        <meta name="format-detection" content="telephone=no"/>
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/font-awesome.css">

        <link rel="stylesheet" href="css/demo.css"/>

        <script src="js/jquery.js"></script>
        <script src="js/jquery-migrate-1.2.1.js"></script>
        <script type="text/javascript" src="js/jquery.leanModal.min.js"></script>

        <!--[if lt IE 9]>
        <html class="lt-ie9">
        <div style=' clear: both; text-align:center; position: relative;'>
            <a href="http://windows.microsoft.com/en-US/internet-explorer/..">
                <img src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820"
                     alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."/>
            </a>
        </div>
        <script src="js/html5shiv.js"></script>
        <![endif]-->

        <script src='js/device.min.js'></script>
    </head>

    <%
        Usuario usuario = (Usuario) session.getAttribute("user_data");
    %>
    <body>
        <div class="page">
            <!--========================================================
                                      HEADER
            =========================================================-->
            <header>

                <div id="stuck_container" class="stuck_container">
                    <div class="container">

                        <div class="brand">
                            <h1 class="brand_name">
                                <a href="index.jsp">RecetarioSocial</a>
                            </h1>
                        </div>
                        <nav class="nav">
                            <div class="nav_title"></div>
                            <a class="sf-menu-toggle fa fa-bars" href="#"></a>
                            <ul class="sf-menu">
                                <li class="active">
                                    <a href="index.jsp">Home</a>
                                </li>
                                <li>
                                    <a href="Logout">Cerrar sesión</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>

            </header>
            <!--========================================================
                                      CONTENT
            =========================================================-->
            <main>
                <section class="well well__offset-3">
                    <div class="container">
                        <h2><em>Administración <% out.print(usuario.getNombre());%></em></h2>
                        <br/>
                        <div class="row box-3">
                            <div class="grid_5">
                                <h2>Usuarios registrados</h2>
                                <div>
                                    <ul>
                                        <h3>
                                            Usuario Conectados: <%=(usuarioConectados == null) ? "0" : usuarioConectados.toString()%>
                                        </h3>
                                    </ul>
                                </div>
                            </div>
                            <div class="preffix_1 grid_6">
                                <h2>Trazabilidad del sistema</h2>

                                <input type="button" value="Consultar LOG" class="button" data-type="zoomin" />

                                <div class="overlay-container">
                                    <div class="window-container zoomin">
                                        <h3>Visor de LOG</h3> 
                                        <object type="text/plain" data="log_app.txt" width="1100" height="300"></object>
                                        <br/>
                                        <span class="close">Cerrar</span>
                                    </div>
                                </div>   

                            </div>
                        </div>
                    </div>
                </section>
            </main>

            <!--========================================================
                                      FOOTER
            =========================================================-->
            <footer>
                <div class="container">
                    <ul class="socials">
                        <li><a href="#" class="fa fa-facebook"></a></li>
                        <li><a href="#" class="fa fa-tumblr"></a></li>
                        <li><a href="#" class="fa fa-google-plus"></a></li>
                    </ul>
                    <div class="copyright">© <span id="copyright-year"></span> |
                        <a href="#">Recetario Social </a>
                    </div>
                </div>
            </footer>


        </div>
        <!--  <script src="js/foundation/vendor/jquery.js"></script> -->
        <script>
            $(document).foundation();
        </script>
        <script src="js/script.js"></script>
        <script>!window.jQuery && document.write(unescape('%3Cscript src="js/jquery-1.7.1.min.js"%3E%3C/script%3E'))</script>
        <script type="text/javascript" src="js/demo.js"></script>
    </body>
</html>