<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute("user-data") == null) {
            if (session.getAttribute("current-page") != null) {
                response.sendRedirect(request.getContextPath() + "/" + session.getAttribute("current-page"));
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }

        ServletContext contexto = getServletContext();
        Integer usuarioConectados = null;
        synchronized (contexto) {
            usuarioConectados = (Integer) contexto.getAttribute("usuariosConectados");
        }
    %>
    <!-- Mirrored from demohtml.templatesquare.com/terramia/gallery.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Mar 2015 03:35:39 GMT -->
    <head>

        <!-- Basic Page Needs
  ================================================== -->
        <meta charset="utf-8" />
        <title>Cookbook</title>
        <meta name="robots" content="index, follow" />
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />

        <!-- Mobile Specific Metas
  ================================================== -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />


        <!-- CSS
  ================================================== -->
        <link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,400italic,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="styles/style.css" />
        <link rel="stylesheet" href="styles/inner.css" />
        <link rel="stylesheet" href="styles/layout.css" />
        <link rel="stylesheet" href="styles/flexslider.css" />
        <link rel="stylesheet" href="styles/color.css" />
        <link rel="stylesheet" href="styles/prettyPhoto.css"  media="screen" />

        <!-- INCLUDE ============================== -->
        <script src="js/libs/jquery/jquery.js"></script>



        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicons
        ================================================== -->
        <link rel="shortcut icon" href="images/favicon.ico" />
    </head>
    <body>

        <div id="bodychild">
            <div id="outercontainer">

                <!-- HEADER -->
                <div id="navegation-include">
                    <!-- <script>
                        $("#navegation-include").load("navegation.jsp");
                    </script> -->
                    <jsp:include page="navegation.jsp"/>
                </div>
                <!-- END HEADER -->

                <!-- BEFORE CONTENT -->
                <div id="outerbeforecontent">
                    <div class="container">
                        <div class="row">
                            <div class="twelve columns" >
                                <div id="beforecontent">
                                    <h1 class="pagetitle">User - Control</h1>
                                    <div class="row box-3">
                                        <div class="grid_5">
                                            <div>
                                                <h3>
                                                    Usuarios conectados: <%=(usuarioConectados == null) ? "0" : usuarioConectados.toString()%>
                                                </h3>
                                            </div>
                                        </div>
                                        <div class="preffix_1 grid_6">
                                            <div class="overlay-container">
                                                <div class="window-container zoomin">
                                                    <h3>Visor de LOG</h3> 
                                                    <object type="text/plain" data="log_app.txt" width="1100" height="300"></object>
                                                    <br/>
                                                </div>
                                            </div>   

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="separator full"></div>
                </div>
                <!-- END BEFORE CONTENT -->


                <!-- MAIN CONTENT -->
                <div id="outermain">
                    <div class="container">
                        <div class="row">
                            <section id="maincontent" class="twelve columns">

                                <section class="content">

                                    <div id="gallery" class="row">
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img1.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img1.jpg"/>
                                            </a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img2.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img2.jpg"/></a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img3.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img3.jpg"/></a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img4.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img4.jpg"/></a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img5.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img5.jpg"/></a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img6.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img6.jpg"/></a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img7.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img7.jpg"/></a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img8.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img8.jpg"/></a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img9.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img9.jpg"/></a>
                                        </div>
                                        <div class="one_fifth columns">
                                            <a class="image frame" href="images/content/img10.jpg" data-rel="prettyPhoto[mixed]" >
                                                <span class="rollover"></span>
                                                <span class="zoom"></span>
                                                <img alt="" src="images/content/img10.jpg"/></a>
                                        </div>

                                    </div>

                                </section>

                            </section>

                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT -->

                <!-- FOOTER -->
                <footer id="footer">

                    <!-- COPYRIGHT -->
                    <div id="outercopyright">
                        <div class="container">
                            <div class="row">
                                <div id="copyright" class="six columns">
                                    Copyright &copy; 2013 Terramia. Designed by <a href="http://templatesquare.com/">TemplateSquare.com</a>
                                </div>

                                <div class="six columns">
                                    <ul class="sn">
                                        <li><a title="Twitter" href="http://twitter.com/"><span class="icon-img twitter"></span></a></li>
                                        <li><a title="Facebook" href="http://facebook.com/"><span class="icon-img facebook"></span></a></li>
                                        <li><a title="Google+" href="https://plus.google.com/"><span class="icon-img google"></span></a></li>
                                        <li><a title="Pinterest" href="http://pinterest.com/"><span class="icon-img pinterest"></span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END COPYRIGHT -->
                </footer>
                <!-- END FOOTER -->
                <div class="clear"></div><!-- clear float --> 
            </div><!-- end outercontainer -->
        </div><!-- end bodychild -->


        <!-- ////////////////////////////////// -->
        <!-- //      Javascript Files        // -->
        <!-- ////////////////////////////////// -->
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>

        <!-- jQuery Superfish -->
        <script type="text/javascript" src="js/hoverIntent.js"></script> 
        <script type="text/javascript" src="js/superfish.js"></script> 
        <script type="text/javascript" src="js/supersubs.js"></script>


        <!-- jQuery PrettyPhoto -->
        <script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
        <script type="text/javascript" src="js/fade.js"></script>


        <!-- jQuery Dropdown Mobile -->
        <script type="text/javascript" src="js/tinynav.min.js"></script>

        <!-- Custom Script -->
        <script type="text/javascript" src="js/custom.js"></script>

    </body>

    <!-- Mirrored from demohtml.templatesquare.com/terramia/gallery.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Mar 2015 03:35:51 GMT -->
</html>
