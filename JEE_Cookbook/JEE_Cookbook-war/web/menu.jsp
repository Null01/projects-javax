<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->

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
                                    <h1 class="pagetitle">Our Menu</h1>
                                    <p>Suspendisse arcu nulla, mollis sed eros id, pellentesque vulputate nulla. Nullam lectus dolor, pulvinar eu tristique nec, tempus ut ligula. Quisque pulvinar ut justo id dapibus. Duis convallis tellus faucibus nisl fermentum, nec semper massa adipiscing.</p>
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

                                    <div class="carousel">

                                        <div data-index="0" class="carousel-item-container row" >
                                            <div class="one_fourth columns item">
                                                <div class="ts-img">
                                                    <a class="image" href="images/content/img-menu1.jpg" data-rel="prettyPhoto[mixed]" >
                                                        <span class="rollover"></span>
                                                        <span class="zoom"></span>
                                                        <img alt="" src="images/content/img-menu1.jpg"/>
                                                    </a>
                                                </div>
                                                <div class="ts-text">
                                                    <h2>Baked-Fish</h2>
                                                    <img alt="" src="images/star.png"/>
                                                    <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam. Vivamus vel tincidunt libero, vitae elementum ligula.</p>
                                                    <span class="price">$10.99</span>
                                                    <a class="button" href="#">Read More</a>
                                                </div>
                                            </div>
                                            <div class="one_fourth columns item">
                                                <div class="ts-img">
                                                    <a class="image" href="images/content/img-menu2.jpg" data-rel="prettyPhoto[mixed]" >
                                                        <span class="rollover"></span>
                                                        <span class="zoom"></span>
                                                        <img alt="" src="images/content/img-menu2.jpg"/>
                                                    </a>
                                                </div>
                                                <div class="ts-text">
                                                    <h2>Spaghetti Italiano</h2>
                                                    <img alt="" src="images/star.png"/>
                                                    <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam. Vivamus vel tincidunt libero, vitae elementum ligula.</p>
                                                    <span class="price">$10.99</span>
                                                    <a class="button" href="#">Read More</a>
                                                </div>
                                            </div>
                                            <div class="one_fourth columns item">
                                                <div class="ts-img">
                                                    <a class="image" href="images/content/img-menu3.jpg" data-rel="prettyPhoto[mixed]" >
                                                        <span class="rollover"></span>
                                                        <span class="zoom"></span>
                                                        <img alt="" src="images/content/img-menu3.jpg"/>
                                                    </a>
                                                </div>
                                                <div class="ts-text">
                                                    <h2>Breakfast Salad</h2>
                                                    <img alt="" src="images/star.png"/>
                                                    <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam. Vivamus vel tincidunt libero, vitae elementum ligula.</p>
                                                    <span class="price">$10.99</span>
                                                    <a class="button" href="#">Read More</a>
                                                </div>
                                            </div>
                                            <div class="one_fourth columns item">
                                                <div class="ts-img">
                                                    <a class="image" href="images/content/img-menu4.jpg" data-rel="prettyPhoto[mixed]" >
                                                        <span class="rollover"></span>
                                                        <span class="zoom"></span>
                                                        <img alt="" src="images/content/img-menu4.jpg"/>
                                                    </a>
                                                </div>
                                                <div class="ts-text">
                                                    <h2>Chicken Barbeque</h2>
                                                    <img alt="" src="images/star.png"/>
                                                    <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam. Vivamus vel tincidunt libero, vitae elementum ligula.</p>
                                                    <span class="price">$10.99</span>
                                                    <a class="button" href="#">Read More</a>
                                                </div>
                                            </div>
                                            <div class="one_fourth columns item">
                                                <div class="ts-img">
                                                    <a class="image" href="images/content/img-menu5.jpg" data-rel="prettyPhoto[mixed]" >
                                                        <span class="rollover"></span>
                                                        <span class="zoom"></span>
                                                        <img alt="" src="images/content/img-menu5.jpg"/>
                                                    </a>
                                                </div>
                                                <div class="ts-text">
                                                    <h2>Steak</h2>
                                                    <img alt="" src="images/star.png"/>
                                                    <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam. Vivamus vel tincidunt libero, vitae elementum ligula.</p>
                                                    <span class="price">$10.99</span>
                                                    <a class="button" href="#">Read More</a>
                                                </div>
                                            </div>
                                            <div class="one_fourth columns item">
                                                <div class="ts-img">
                                                    <a class="image" href="images/content/img-menu6.jpg" data-rel="prettyPhoto[mixed]" >
                                                        <span class="rollover"></span>
                                                        <span class="zoom"></span>
                                                        <img alt="" src="images/content/img-menu6.jpg"/>
                                                    </a>
                                                </div>
                                                <div class="ts-text">
                                                    <h2>Omlate</h2>
                                                    <img alt="" src="images/star.png"/>
                                                    <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam. Vivamus vel tincidunt libero, vitae elementum ligula.</p>
                                                    <span class="price">$10.99</span>
                                                    <a class="button" href="#">Read More</a>
                                                </div>
                                            </div>
                                            <div class="one_fourth columns item">
                                                <div class="ts-img">
                                                    <a class="image" href="images/content/img-menu7.jpg" data-rel="prettyPhoto[mixed]" >
                                                        <span class="rollover"></span>
                                                        <span class="zoom"></span>
                                                        <img alt="" src="images/content/img-menu7.jpg"/>
                                                    </a>
                                                </div>
                                                <div class="ts-text">
                                                    <h2>Bacon</h2>
                                                    <img alt="" src="images/star.png"/>
                                                    <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam. Vivamus vel tincidunt libero, vitae elementum ligula.</p>
                                                    <span class="price">$10.99</span>
                                                    <a class="button" href="#">Read More</a>
                                                </div>
                                            </div>
                                        </div>
                                        <nav class="carousel-nav">
                                            <a class="port-nav left"></a><a class="port-nav right"></a>
                                        </nav>
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

    <!-- Mirrored from demohtml.templatesquare.com/terramia/menu.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Mar 2015 03:35:33 GMT -->
</html>
