<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->

    <!-- Mirrored from demohtml.templatesquare.com/terramia/single.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Mar 2015 03:35:55 GMT -->
    <head>

        <!-- Basic Page Needs
  ================================================== -->
        <meta charset="utf-8" />
        <title>Terramia</title>
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
    <body class="single">
        <div id="bodychild">
            <div id="outercontainer">
                <!-- HEADER -->
                <div id="navegation-include">
                    <!--<script>
                        $("#navegation-include").load("navegation.jsp");
                    </script> -->
                    <jsp:include page="navegation.jsp" />
                </div>
                <!-- END HEADER -->



                <!-- MAIN CONTENT -->
                <div id="outermain">
                    <div class="container">
                        <div class="row">
                            <section id="maincontent" class="nine columns positionleft">
                                <section class="content">
                                    <div class="six columns">
                                        <div id="contactform">
                                            <form action="Register" method="post">
                                                <fieldset>
                                                    <label for="fname" >Your first name <span class="required">(required)</span></label>
                                                    <input type="text" name="fname" size="60" required/>
                                                    <label for="lname" >Your second name <span class="required">(required)</span></label>
                                                    <input type="text" name="lname"  size="60" required/>
                                                    <label for="email" >Your email <span class="required">(required)</span></label>
                                                    <input type="email" name="email"  size="60" required/>
                                                    <label for="password" >Your password <span class="required">(required)</span></label>
                                                    <input type="password" name="password"  size="60" required/>
                                                    <label for="confirmPassword" >Confirm password <span class="required">(required)</span></label>
                                                    <input type="password" name="confirmPassword"  size="60" required/>
                                                    <br class="clear" />
                                                    <input type="submit" class="button" id="submit_btn" value="      Register     "/>
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </section>
                            </section>

                            <div class="three columns">
                                <div class="sidebar">
                                    <ul>
                                        <li class="widget-container">
                                            <h2 class="widget-title"><span>Archives</span></h2>
                                            <ul>
                                                <li><a href="#">December 2012</a></li>
                                                <li><a href="#">January 2013</a></li>
                                                <li><a href="#">March 2013</a></li>
                                                <li><a href="#">June 2013</a></li>
                                            </ul>
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

                            </div>
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

    <!-- Mirrored from demohtml.templatesquare.com/terramia/single.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Mar 2015 03:35:56 GMT -->
</html>
