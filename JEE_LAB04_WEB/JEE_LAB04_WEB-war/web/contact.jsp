<%-- 
    Document   : contact
    Created on : 08-mar-2015, 17:57:33
    Author     : andresfelipegarciaduran
--%>

<%@page import="session.ITipoUsuario"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Menu</title>
        <meta charset="utf-8">
        <meta name="format-detection" content="telephone=no"/>
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/contact-form.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/font-awesome.css">
        <link rel="stylesheet" href="css/style_popup.css" />

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
                                    <a href="contact.jsp">Contactenos</a>
                                </li>
                                <% if (usuario == null) { %>
                                <li>
                                    <a id="modal_trigger_login" href="#modal_login" >Ingresar</a>
                                </li>
                                <%} else {
                                    if (usuario.getTipo().compareTo(ITipoUsuario.ADMIN) == 0) {
                                %>
                                <li>
                                    <a href="admin.jsp" >Servicios</a>
                                </li>
                                <%
                                } else {
                                %>
                                <li>
                                    <a href="user.jsp" >Servicios</a>
                                </li>
                                <%
                                    }
                                %>
                                <li>
                                    <a href="Logout" >Cerrar sesion</a>
                                </li>

                                <%}%>

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
                        <h2><em>Contactenos</em></h2>
                        <br/>
                        <div class="row box-3">
                            <div class="grid_5">
                                <h2>Contacts Form</h2>
                                <form id="contact-form" class='contact-form'>
                                    <div class="contact-form-loader"></div>
                                    <fieldset>
                                        <label class="name">
                                            Your Name:
                                            <input type="text" name="name" placeholder="" value=""
                                                   data-constraints="@Required @JustLetters"/>
                                            <span class="empty-message">*This field is required.</span>
                                            <span class="error-message">*This is not a valid name.</span>
                                        </label>

                                        <label class="email">
                                            Your E-mail:
                                            <input type="text" name="email" placeholder="" value=""
                                                   data-constraints="@Required @Email"/>
                                            <span class="empty-message">*This field is required.</span>
                                            <span class="error-message">*This is not a valid email.</span>
                                        </label>

                                        <label class="Subject">
                                            Subject:
                                            <input type="text" name="phone" placeholder="" value=""
                                                   data-constraints="@Required"/>
                                            <span class="empty-message">*This field is required.</span>
                                            <span class="error-message">*This is not a valid phone.</span>
                                        </label>

                                        <label class="message">
                                            Message:
                                            <textarea name="message" placeholder=""
                                                      data-constraints='@Required @Length(min=20,max=999999)'></textarea>
                                            <span class="empty-message">*This field is required.</span>
                                            <span class="error-message">*The message is too short.</span>
                                        </label>

                                        <div class="btn-wr">
                                            <a class="" href="#" data-type="reset">Clear</a>
                                            <a class="" href="#" data-type="submit">Send</a>
                                        </div>
                                    </fieldset>
                                    <div class="modal fade response-message">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <h4 class="modal-title">Modal title</h4>
                                                </div>
                                                <div class="modal-body">
                                                    You message has been sent! We will be in touch soon.
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="preffix_1 grid_6">
                                <h2>Contacts Information</h2>
                                <h3>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consectetur orci sed Curabitur vel lorem sit amet nulla ullamcorper fermentum. In vitae varius augue, eu consectetur ligula. Etiam dui eros, laoreet sit amet est vel</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consectetur orci sed Curabitur vel lorem sit amet nulla ullamcorper fermentum. In vitae varius augue, eu consectetur ligula. Etiam dui eros, laoreet sit amet est vel, commodo venenatis eros.Lamus at magna non nunc tristique rhoncuseri tym.<br><br>Etiam dui eros, laoreet sit amet est vel, commodo venenatis eros.Lamus at magna non nunc tristique rhoncuseri tym. Etiam dui eros, laoreet sit amet est vel, commodo venenatis eros.Lamus at magna non nunc tristique.</p>
                                <address class="address-2">
                                    <div class="address_container"><p>The Company Name Inc. 9870 St Vincent Place, Glasgow, DC 45 Fr 45.</p></div>
                                    <dl>
                                        <dt>Telphone:</dt> <dd>+1 800 603 6035</dd>
                                        <dt>FAX:</dt> <dd>+1 800 899 9898</dd>
                                        <dt>E-mail:</dt> <dd><a href="mailto:mail@demolink.org">mail@demolink.org</a></dd>
                                    </dl>
                                </address>
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
        <script src="js/script.js"></script>
    </body>
</html>