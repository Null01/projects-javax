<%-- 
    Document   : login
    Created on : 27/04/2014, 11:45:57 PM
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
    if (httpSession != null) {
        Object object = httpSession.getAttribute("session");
        if (object != null) {
            response.sendRedirect("admin_system.jsp");
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
        </noscript>
        <!--[if lte IE 8]><script src="js/html5shiv.js"></script><link rel="stylesheet" href="css/ie8.css" /><![endif]-->
    </head>
    <body class="homepage">

        <!-- Header Wrapper -->

        <!-- Main Wrapper -->
        <div id="main-wrapper">
            <div class="container">
                <div class="row">
                    <div class="12u">


                    </div>
                </div>
                <div class="row">
                    <div class="12u">

                        <!-- Blog -->
                        <section>
                            <header class="major">
                                <h2>BIENVENIDO POR FAVOR IDENTIFIQUESE.</h2>
                            </header>
                        </section>
                        <div align="center"> 
                            <form id="formLogin" name="login" action="loginController">
                                <table border="1" style="width:500px" >
                                    <tbody>
                                        <tr>
                                            <td><h2>USUARIO: </h2></td>
                                            <td><input type="text" name="user" value="admin" class="rounded_corners" required /></td>
                                        </tr>
                                        <tr>
                                            <td><h2>CONTRASEÑA: </h2></td>
                                            <td><input type="password" name="password" value="admin" class="rounded_corners" required /></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <input type="submit" class="button alt" />

                            </form>
                        </div> 
                    </div>
                </div>



            </div>
        </div>

        <!-- Footer Wrapper -->

    </body>
</html>