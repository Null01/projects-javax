<%-- 
    Document   : index
    Created on : 08-mar-2015, 17:44:49
    Author     : andresfelipegarciaduran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <meta name="format-detection" content="telephone=no"/>
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/contact-form.css">
        <link rel="stylesheet" href="css/camera.css">
        <link rel="stylesheet" href="css/jquery.fancybox.css">
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

    <body>
        <div class="page">
            <!--========================================================
                                      HEADER
            =========================================================-->
            <header>

                <div class="camera_container">
                    <div id="camera" class="camera_wrap">
                        <div data-thumb="images/slide01_thumb.jpg" data-src="images/slide01.jpg">
                            <div class="camera_caption fadeIn">
                            </div>
                        </div>
                        <div data-thumb="images/slide02_thumb.jpg" data-src="images/slide02.jpg">
                            <div class="camera_caption fadeIn">
                            </div>
                        </div>
                        <div data-thumb="images/slide03_thumb.jpg" data-src="images/slide03.jpg">
                            <div class="camera_caption fadeIn">
                            </div>
                        </div>
                    </div>

                    <div class="brand wow fadeIn">
                        <h1 class="brand_name">
                            <a href="admin.jsp">RecetarioSocial</a>
                        </h1>
                    </div>
                </div>

                <div class="toggle-menu-container">
                    <nav class="nav">
                        <div class="nav_title"></div>
                        <a class="sf-menu-toggle fa fa-bars" href="#"></a>
                        <ul class="sf-menu">
                            <li class="active">
                                <a href="index.jsp">Home</a>
                            </li>
                            <li>
                                <a id="modal_trigger_login" href="#modal_login" >Ingresar</a>
                            </li>
                            <li>
                                <a href="contact.jsp">Contactenos</a>
                            </li>
                        </ul>
                    </nav>
                </div>

            </header>
            <!--========================================================
                                      CONTENT
            =========================================================-->
            <main>
                <section class="well">
                    <div class="container">
                        <h2><em>Bienvenido</em></h2>
                        <br/>

                        <form id="contact-form">
                            <fieldset>
                                <label class="Subject">
                                    <input type="text" name="search_data" placeholder="¿Que desea para hoy?" value=""
                                           data-constraints="@Required" />
                                    <span class="empty-message">*This field is required.</span>
                                    <span class="error-message">*This is not a valid phone.</span>
                                </label>
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
                    <div class="container">
                        <div class="row box-2">
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img01.jpg" alt=""></div></div>
                                <h3>Anteger convallis orci vel mi nelaoreet, at ornare lorem consequat. </h3>
                                <p>Vestibulum volutpatturpis ut massa commodo, quis aliquam massa facilisis.Integer convavel miberto merlonelaoreet, at ornare lorem consequat.</p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img02.jpg" alt=""></div></div>
                                <h3>Genteger convallis orci vel mi nelaoreet, at ornare lorem consequat.</h3>
                                <p>Meestibulum volutpatturpis ut massa commodo, quis aliquam massa facilisis.Integer convavel miberto merlonelaoreet, at ornare lorem consequatre. </p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img03.jpg" alt=""></div></div>
                                <h3>Ternteger convallis orci vel mi nelaoreet, at ornare lorem consequat. </h3>
                                <p>Testibulum volutpatturpis ut massa commodo, quis aliquam massa facilisis.Integer convavel miberto merlonelaoreet, at ornare lorem consequ.</p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                        </div>
                        <div class="row box-2">
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img04.jpg" alt=""></div></div>
                                <h3>Onteger convallis orci vel mi nelaoreet, at ornare lorem consequate. </h3>
                                <p>Testibulum volutpatturpis ut massa commodo, quis aliquam massa facilisis.Integer convavel miberto merlonelaoreet, at ornare lorem consequa.</p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img05.jpg" alt=""></div></div>
                                <h3>Fenteger convallis orci vel mi nelaoreet, at ornare lorem consequat. </h3>
                                <p>Vestibulum volutp turpis ut massa commodo, quis aliquam massa facilisis.Vestibulum volutpat turpis ut massa commodo, quis aliquam massa facilisis.</p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img06.jpg" alt=""></div></div>
                                <h3>Dernteger convallis orci vel mi nelaoreet, at ornare lorem consequat. </h3>
                                <p>Testibulum volutpatturpis ut massa commodo, quis aliquam massa facilisis.Integer convavel miberto merlonelaoreet, at ornare lorem conseasellus era nisl. </p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                        </div>
                        <div class="row box-2">
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img07.jpg" alt=""></div></div>
                                <h3>Anteger convallis orci vel mi nelaoreet, at ornare lorem consequat. </h3>
                                <p>Vestibulum volutpatturpis ut massa commodo, quis aliquam massa facilisis.Integer convavel miberto merlonelaoreet, at ornare lorem consequat.</p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img08.jpg" alt=""></div></div>
                                <h3>Genteger convallis orci vel mi nelaoreet, at ornare lorem consequat.</h3>
                                <p>Meestibulum volutpatturpis ut massa commodo, quis aliquam massa facilisis.Integer convavel miberto merlonelaoreet, at ornare lorem consequatre. </p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                            <div class="grid_4">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 76.21621621621622%;"><img src="images/page-4_img09.jpg" alt=""></div></div>
                                <h3>Ternteger convallis orci vel mi nelaoreet, at ornare lorem consequat. </h3>
                                <p>Testibulum volutpatturpis ut massa commodo, quis aliquam massa facilisis.Integer convavel miberto merlonelaoreet, at ornare lorem consequ.</p>
                                <a href="#" class="btn">Read more</a>
                            </div>
                        </div>
                    </div>
                </section>

                <section class="well well__offset-3">
                    <div class="container">
                        <h2><em>¿Quienes Somos?</em></h2>
                        <div class="row box-1">
                            <div class="grid_6">
                                <div class="img"><div class="lazy-img" style="padding-bottom: 64.91228070175439%;"><img src="images/page-3_img01.jpg" alt=""></div></div>
                            </div>
                            <div class="grid_6">
                                <h3 class="indents-3">Onteger convallis orci vel mi nelaoreet, at ornare lorem consequat. Phasellus era nisl auctor vel veliterol sed,pharetra venenatis nulla.</h3>
                                <p class="indents-3">Vestibulum volutpat turpis ut massa commodo, quis aliquam massa facilisis.Integer convallis orci vel mi nelaoreet, at ornare lorem consequat. Phasellus era nisl auctor vel veliterol. sed,pharetra venenatis nulla.</p>
                                <h3 class="indents-3">Hestibulum volutpat turpis ut massa commodo, quis aliquam massa fertoli estibulum volutpat turpis ut massa commodo.</h3>
                                <p class="indents-3"> Guis aliquam massa gertoli facilisis.Integer convallis orci vel mi nelaoreet, at ornare lorem consequat. Phasellus era nisl auctor vel veliterol sed,pharetra venenatis nulla.</p>
                                <h3 class="indents-3">Enteger convallis orci vel mi nelaoreet, at ornare lorem consequat. Phasellus era nisl auctor vel veliterol sed,pharetra venenatis nulla.</h3>
                                <p class="indents-3">Vestibulum volutpat turpis ut massa commodo, quis aliquam massa facilisis.Integer convallis orci vel mi nelaoreet, at ornare lorem consequat. Phasellus era nisl auctor vel veliterol. sed,pharetra venenatis nulla.Vestibulum volutpat turpis ut massa commodo, quis aliquam massa fertoli estibulum</p>
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

            <!--=========================================================
                                   POP-UP
                  ==========================================================-->

            <div id="popups-include">
                <script>
                    $("#popups-include").load("pop-ups.jsp");
                </script>
            </div>


        </div>
        <!--  <script src="js/foundation/vendor/jquery.js"></script> -->
        <script src="js/foundation/vendor/fastclick.js"></script>
        <script src="js/foundation/foundation.min.js"></script>
        <script src="js/foundation/foundation/foundation.js"></script>
        <script src="js/foundation/foundation/foundation.orbit.js"></script>
        <script src="js/foundation/foundation/foundation.topbar.js"></script>
        <script>
                    $(document).foundation();
        </script>
        <script src="js/script.js"></script>
    </body>
</html>
