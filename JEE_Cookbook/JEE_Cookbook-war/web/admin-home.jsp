<%@page import="edu.lab.modelo.Usuario"%>
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
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
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
        <link rel="stylesheet" href="styles/flexslider.css"/>
        <link rel="stylesheet" href="styles/color.css" />
        <link rel="stylesheet" href="styles/prettyPhoto.css" media="screen" />


        <!-- INCLUDE
  ================================================== -->
        <script src="js/libs/jquery/jquery.js"></script>

        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
       <![endif]-->

        <!-- Favicons
        ================================================== -->
        <link rel="shortcut icon" href="images/favicon.ico" />

    </head>
    <body>
        <%
            Usuario usuario = (Usuario) session.getAttribute("user-data");
        %>
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


                <!--
                    CONTENT
                -->
                <div data-index="0" class="carousel-item-container row" >
                    <div class="one_sixth columns item"></div>
                    <div class="one_sixth columns item">
                        <div class="ts-text">
                            <br/>
                            <h1>Welcome</h1><h1> <% out.print(usuario.getNombre());%></h1>
                        </div>
                        <div class="ts-img">
                            <img class="image" alt="" src="images/content/img-default-user.png" />
                        </div>
                    </div>
                    <div class="six columns item">
                        <div class="twelve columns" >
                            <div id="beforecontent">
                                <br/>
                                <h1 class="pagetitle">About Me</h1>
                                <p>Suspendisse arcu nulla, mollis sed eros id, pellentesque vulputate nulla. Nullam lectus dolor, pulvinar eu tristique nec, tempus ut ligula. Quisque pulvinar ut justo id dapibus. Duis convallis tellus faucibus nisl fermentum, nec semper massa adipiscing.</p>
                                <table border="0" style="width:80%">
                                    <tbody>
                                        <tr>
                                            <td>My fisrt name:</td>
                                            <td> <% out.print(usuario.getNombre());%> </td>
                                        </tr>
                                        <tr>
                                            <td>My second name:</td>
                                            <td> <% out.print(usuario.getApellido());%> </td>
                                        </tr>
                                        <tr>
                                            <td>My email:</td>
                                            <td> <% out.print(usuario.getCorreo());%> </td>
                                        </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                    <div class="separator full"></div>
                </div>

                <!-- FOOTER -->
                <div id="footer-include">
                    <!-- <script>
                        $("#footer-include").load("footer.jsp");
                    </script> -->
                    <jsp:include page="footer.jsp"/> 
                </div>
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

        <!-- jQuery Flexslider -->
        <script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
        <script type="text/javascript">
            jQuery(window).load(function () {
                jQuery('.flexslider').flexslider({
                    animation: "fade", //String: Select your animation type, "fade" or "slide"
                    directionNav: false, //Boolean: Create navigation for previous/next navigation? (true/false)
                    controlNav: true  //Boolean: Create navigation for paging control of each clide? Note: Leave true for manualControls usage
                });

            });
        </script>
    </body>
</html>
