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

                <!--  CONTENT  -->
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

                                <div id="target">
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
                                    <input type="button"class="button" id="ocultar" value=" Actualizar     "/>
                                </div>   

                                <div id="target1" style="display:none;">
                                    <form action="Register" method="post">
                                        <fieldset>
                                            <table border="0" style="width:80%">
                                                <tbody>
                                                    <tr>
                                                        <td><label for="fname" >Your first name <span class="required">(required)</span></label></td>
                                                        <td><input type="text" name="fname" value='<% out.print(usuario.getNombre());%>' size="30" required/></td>
                                                    </tr>
                                                    <tr>
                                                        <td><label for="lname" >Your second name <span class="required">(required)</span></label></td>
                                                        <td><input type="text" name="lname" value='<% out.print(usuario.getApellido());%>' size="30" required/></td>
                                                    </tr>
                                                    <tr>
                                                        <td><label for="email" >Your email <span class="required">(required)</span></label></td>
                                                        <td><input type="email" name="email" value='<% out.print(usuario.getCorreo());%>' size="30" required/></td>
                                                    </tr>
                                                    <tr>
                                                        <td><label for="password" >Your password <span class="required">(required)</span></label></td>
                                                        <td><input type="password" name="password" size="30" required/></td>
                                                    </tr>
                                                    <tr>
                                                        <td><label for="confirmPassword" >New password <span class="required">(no-required)</span></label></td>
                                                        <td><input type="password" name="newPassword" size="30" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td><label for="confirmPassword" >Confirm password <span class="required">(no-required)</span></label></td>
                                                        <td><input type="password" name="confirmPassword" size="30" /></td>
                                                    </tr>
                                                <br class="clear" />
                                                <%
                                                    Object message = request.getAttribute("message-error-register");
                                                    if (message != null) {
                                                        out.println("<br/><span class=\"required\">" + message.toString() + "</span><br/>");
                                                    }
                                                %>
                                                </tbody>
                                            </table>
                                            <input type="submit" class="button" id="submit_btn" value="  Guardar     "/>    
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="separator full"></div>
                </div>

                <!-- MAIN CONTENT -->
                <div id="outermain">
                    <div class="container">
                        <div class="row">
                            <section id="maincontent" class="nine columns positionleft">
                                <section class="content">

                                    <!-- PUBLISH GOOGLE-->
                                    <div id="google_pluss">
                                        <div class="Sb">
                                            <div tabindex="0" style="-moz-user-select: none;" class="kqa es" guidedhelpid="sharebox_textarea" role="button">Share what's new...</div>
                                            <div class="iH">
                                                <div class="j7 Be" guidedhelpid="sharebox_text">
                                                    <div class="m7">
                                                        <div class="mB k7"></div>
                                                        <div class="mB l7"></div>                            
                                                    </div>
                                                    <span style="-moz-user-select: none;" role="button" class="d-s aj oqa" tabindex="0">
                                                        <div class="yl lH"></div>
                                                        <div class="dv">Text</div>
                                                    </span></div><div class="j7 " guidedhelpid="sharebox_photos">
                                                    <div class="m7">
                                                        <div class="mB k7"></div>
                                                        <div class="mB l7"></div>                                
                                                    </div>
                                                    <span style="-moz-user-select: none;" role="button" class="d-s aj nqa" tabindex="0">
                                                        <div class="yl FD"></div>
                                                        <div class="dv">Photos</div>
                                                    </span></div><div class="j7 " guidedhelpid="sharebox_link">
                                                    <div class="m7">
                                                        <div class="mB k7"></div>
                                                        <div class="mB l7"></div>                                    
                                                    </div>
                                                    <span style="-moz-user-select: none;" role="button" class="d-s aj mqa" tabindex="0">
                                                        <div class="yl kH"></div>
                                                        <div class="dv">Link</div>
                                                    </span>                                
                                                </div>
                                                <div class="j7 " guidedhelpid="sharebox_video">
                                                    <div class="m7">
                                                        <div class="mB k7"></div>
                                                        <div class="mB l7"></div>                                        
                                                    </div>
                                                    <span style="-moz-user-select: none;" role="button" class="d-s aj pqa" tabindex="0">
                                                        <div class="yl mH"></div>
                                                        <div class="dv">Video</div>
                                                    </span>                        
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                    <div id="entry-author-info">
                                        <div id="author-description">
                                            <img class="avatar alignleft" src="images/content/avatar.gif" alt="">   
                                            <h4 class="author"><%= usuario.getNombre()%></h4>
                                            <p>Curabitur tincidunt iaculis ipsum, eu malesuada tellus congue a. Quisque aliquet, enim eget consequat scelerisque, lectus nibh pulvinar lectus, ac vestibulum nisl urna quis magna. Quisque laoreet pulvinar orci, eget tempor ante consectetur in. Nullam et lorem ut magna aliquet eleifend scelerisque eu justo.  </p>                        
                                        </div><!-- author-description	-->
                                        <div class="clear"></div>
                                    </div>

                                    <section id="comment">
                                        <h3>4 Comments</h3>
                                        <ol class="commentlist">
                                            <li>
                                                <div class="comment-body">
                                                    <div class="comment-arrow"></div>
                                                    <div class="avatar-img"><img src="images/content/avatar.gif" alt="" class="avatar"/></div>
                                                    <cite class="fn">Richard Delano</cite>
                                                    <span class="tdate">August 17, 2010 7:15 am &nbsp;/&nbsp; </span> <span class="reply"><a href="#">Reply</a></span>
                                                    <div class="commenttext">
                                                        <p>Nulla lobortis facilisis eros vitae mollis. Morbi consectetur, tortor ut feugiat rhoncus, nunc augue placerat massa, sit amet laoreet est libero quis nisl. Integer cursus sodales sem eu dapibus. Morbi lobortis eleifend lectus sit amet porttitor. Nam tincidunt congue laoreet.</p>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="comment-body">
                                                    <div class="comment-arrow"></div>
                                                    <div class="avatar-img"><img src="images/content/avatar.gif" alt="" class="avatar"/></div>
                                                    <cite class="fn"><a href="#">Michael John</a></cite>
                                                    <span class="tdate">August 17, 2010 7:15 am &nbsp;/&nbsp;</span> <span class="reply"><a href="#">Reply</a></span>
                                                    <div class="commenttext">
                                                        <p>Nulla lobortis facilisis eros vitae mollis. Morbi consectetur, tortor ut feugiat rhoncus, nunc augue placerat massa, sit amet laoreet est libero quis nisl. Integer cursus sodales sem eu dapibus. Morbi lobortis eleifend lectus sit amet porttitor. Nam tincidunt congue laoreet.</p>
                                                    </div>
                                                </div>
                                                <ol>
                                                    <li>
                                                        <div class="comment-body">
                                                            <div class="comment-arrow"></div>
                                                            <div class="avatar-img"><img src="images/content/avatar.gif" alt="" class="avatar"/></div>
                                                            <cite class="fn"><a href="#">Wayne Rooney</a></cite>
                                                            <span class="tdate">August 17, 2010 7:15 am &nbsp;/&nbsp;</span> <span class="reply"><a href="#">Reply</a></span>
                                                            <div class="commenttext">
                                                                <p>Nulla lobortis facilisis eros vitae mollis. Morbi consectetur, tortor ut feugiat rhoncus, nunc augue placerat massa, sit amet laoreet est libero quis nisl. Integer cursus sodales sem eu dapibus. Morbi lobortis eleifend lectus sit amet porttitor. Nam tincidunt congue laoreet.</p>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ol>
                                            </li>
                                            <li>
                                                <div class="comment-body">
                                                    <div class="comment-arrow"></div>
                                                    <div class="avatar-img"><img src="images/content/avatar.gif" alt="" class="avatar"/></div>
                                                    <cite class="fn"><a href="#">John Doe</a></cite>
                                                    <span class="tdate">August 17, 2010 7:15 am &nbsp;/&nbsp;</span> <span class="reply"><a href="#">Reply</a></span>
                                                    <div class="commenttext">
                                                        <p>Nulla lobortis facilisis eros vitae mollis. Morbi consectetur, tortor ut feugiat rhoncus, nunc augue placerat massa, sit amet laoreet est libero quis nisl. Integer cursus sodales sem eu dapibus. Morbi lobortis eleifend lectus sit amet porttitor. Nam tincidunt congue laoreet.</p>
                                                    </div>
                                                </div>
                                            </li>
                                        </ol>
                                    </section>

                                </section>
                            </section>

                            <aside class="three columns">
                                <div class="sidebar">
                                    <ul>
                                        <li class="widget-container">
                                            <div class="box-notice">
                                                <div class="title">
                                                    <div class="line"></div>
                                                    <span class="icon-l"></span>
                                                    <h2>Our Hours</h2>
                                                    <span class="icon-r"></span>
                                                    <div class="line"></div>
                                                </div>
                                                <h3>Open 5 days a week<br>08am - 09pm</h3>
                                                <p>Farmville Gathaway 58th street, City Name<br><span>+1 800 123 456</span></p>

                                            </div>
                                        </li>
                                        <li class="widget-container">
                                            <h2 class="widget-title"><span>Latest News</span></h2>
                                            <ul class="rp-widget">
                                                <li>
                                                    <img class="frame" alt="" src="images/content/rp1.jpg">
                                                    <h3><a href="#">Default post without image</a></h3>
                                                    <span class="smalldate">April 27, 2012</span>
                                                    <span class="clear"></span>
                                                </li>
                                                <li>
                                                    <img class="frame" alt="" src="images/content/rp2.jpg">
                                                    <h3><a href="#">This is standard post format</a></h3>
                                                    <span class="smalldate">April 27, 2012</span>
                                                    <span class="clear"></span>
                                                </li>
                                                <li class="last">
                                                    <img class="frame" alt="" src="images/content/rp3.jpg">
                                                    <h3><a href="#">Post format multiple gallery</a></h3>
                                                    <span class="smalldate">April 27, 2012</span>
                                                    <span class="clear"></span>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="widget-container ">
                                            <h2 class="widget-title"><span>Categories</span></h2>
                                            <ul>
                                                <li><a href="#">Budget Recipes</a></li>
                                                <li><a href="#">Gluten Free Recipes</a></li>
                                                <li><a href="#">Low Crab Recipes</a></li>
                                                <li><a href="#">Most Popular Menu</a></li>
                                                <li><a href="#">Vegetarian Recipes</a></li>
                                                <li><a href="#">Tips for Cook</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>

                            </aside>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT -->

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
            
            $(document).ready(function () {
                $("#ocultar").click(function () {
                    $('#target').hide();
                    $('.target').hide();
                    $('#target1').show();
                    $('.target1').show();
                });
            });
        </script>
    </body>
</html>
