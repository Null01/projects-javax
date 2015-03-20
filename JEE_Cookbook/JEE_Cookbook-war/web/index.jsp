<%@page import="edu.lab.modelo.Usuario"%>
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

                <!-- SLIDER -->
                <div id="outerslider">
                    <div class="container">
                        <div class="row">
                            <%
                                String classSliderContainer = "nine columns";
                                if (usuario != null) {
                                    classSliderContainer = "twelve columns";
                                }%>
                            <div id="slidercontainer" class="<%=classSliderContainer%>">
                                <!-- <div id="headline">We serve authentic Italian Dishes</div> -->
                                <div id="slider">
                                    <div id="slideritems" class="flexslider">
                                        <ul class="slides">
                                            <li>
                                                <img src="images/slider/slide1.jpg" alt="" />
                                                <div class="flex-caption">
                                                    <div class="title-wrap">
                                                        <!--   <h1>Family style &amp; catering menu</h1> -->
                                                    </div>
                                                    <p>Pellentesque at pulvinar justo. Nulla feugiat convallis nisl, vel malesuada justo posuere in. Nunc in libero sit amet ante pharetra euismod. Nullam porttitor, risus at vestibulum convallis, orci diam ornare massa, et porttitor neque orci ut tortor.</p>
                                                </div>
                                            </li>
                                            <li>
                                                <img src="images/slider/slide2.jpg" alt="" />
                                                <div class="flex-caption">
                                                    <div class="title-wrap">
                                                        <!--   <h1>Baked-Fish</h1> -->
                                                    </div>
                                                    <p>Pellentesque at pulvinar justo. Nulla feugiat convallis nisl, vel malesuada justo posuere in. Nunc in libero sit amet ante pharetra euismod. Nullam porttitor, risus at vestibulum convallis, orci diam ornare massa, et porttitor neque orci ut tortor.</p>
                                                </div>
                                            </li>
                                            <li>
                                                <img src="images/slider/slide3.jpg" alt="" />
                                                <div class="flex-caption">
                                                    <div class="title-wrap">
                                                        <!-- <h1>Grilled Chicken Marsala</h1> -->
                                                    </div>
                                                    <p>Pellentesque at pulvinar justo. Nulla feugiat convallis nisl, vel malesuada justo posuere in. Nunc in libero sit amet ante pharetra euismod. Nullam porttitor, risus at vestibulum convallis, orci diam ornare massa, et porttitor neque orci ut tortor.</p>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <% if (usuario == null) { %>
                            <div class="three columns">
                                <br/> <br/> 
                                <div class="box-notice-login">
                                    <div class="title">
                                        <h2>Bienvenido</h2>
                                    </div>
                                    <form id="loginform" action="Login" method="post">
                                        <div class="bg-right">
                                            <input type="email" name="email" id="usuario"  placeholder="Usuario" required/>
                                        </div>
                                        <div class="bg-right">
                                            <input type="password" name="password" id="password" placeholder="Contraseña"  required/>
                                        </div>
                                        <input type="submit" class="button" id="submit_btn" value="  Ingresar  "/>
                                    </form>
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
                <!-- END SLIDER -->

                <!-- MAIN CONTENT -->
                <div id="outermain">
                    <div class="container">
                        <div class="row">
                            <div id="maincontent" class="twelve columns">

                                <div class="content">

                                    <div class="row textCenter">
                                        <div class="one_third columns">
                                            <div class="title-wrap">
                                                <div class="line-one-third"></div>
                                                <h2>Best cuisine for the most<br> exquisite taste</h2>
                                                <div class="line-one-third"></div>
                                            </div>
                                            <p>Mauris laoreet ultricies leo volutpat laoreet. Pellentesque at velit eu nibh varius ullamcorper ac aliquam diam. Vestibulum pellentesque at justo eu pharetra.</p>
                                            <a class="button" href="#">Read More</a>
                                        </div>
                                        <div class="one_third columns">
                                            <div class="title-wrap">
                                                <div class="line-one-third"></div>
                                                <h2>An ideal place to spend a<br> romantic dinner</h2>
                                                <div class="line-one-third"></div>
                                            </div>
                                            <p>Mauris laoreet ultricies leo volutpat laoreet. Pellentesque at velit eu nibh varius ullamcorper ac aliquam diam. Vestibulum pellentesque at justo eu pharetra.</p>
                                            <a class="button" href="#">Read More</a>
                                        </div>
                                        <div class="one_third columns">
                                            <div class="title-wrap">
                                                <div class="line-one-third"></div>
                                                <h2>The right place to have <br>good rest</h2>
                                                <div class="line-one-third"></div>
                                            </div>

                                            <p>Mauris laoreet ultricies leo volutpat laoreet. Pellentesque at velit eu nibh varius ullamcorper ac aliquam diam. Vestibulum pellentesque at justo eu pharetra.</p>
                                            <a class="button" href="#">Read More</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT -->

                <!-- AFTER CONTENT -->
                <div id="outeraftercontent">
                    <div class="line-top"></div>
                    <div class="container">
                        <div class="row">
                            <div class="twelve column">
                                <div class="title-wrap">
                                    <div class="line"></div>
                                    <span class="icon-l"></span>
                                    <h2>This Week's Best Choices</h2>
                                    <span class="icon-r"></span>
                                    <div class="line"></div>
                                </div>
                                <div class="row">
                                    <div class="one_fifth columns">
                                        <div class="ts-img">
                                            <div class="image">
                                                <span class="rollover"></span>
                                                <div class="text">
                                                    <h3>Baked-Fish</h3>
                                                    <a class="button2" href="#">Read More</a>
                                                </div>
                                                <img alt="" src="images/content/img-menu1.jpg"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="one_fifth columns">
                                        <div class="ts-img">
                                            <div class="image">
                                                <span class="rollover"></span>
                                                <div class="text">
                                                    <h3>Bacon</h3>
                                                    <a class="button2" href="#">Read More</a>
                                                </div>
                                                <img alt="" src="images/content/img-menu7.jpg"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="one_fifth columns">
                                        <div class="ts-img">
                                            <div class="image">
                                                <span class="rollover"></span>
                                                <div class="text">
                                                    <h3>Barbeque</h3>
                                                    <a class="button2" href="#">Read More</a>
                                                </div>
                                                <img alt="" src="images/content/img-menu4.jpg"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="one_fifth columns">
                                        <div class="ts-img">
                                            <div class="image">
                                                <span class="rollover"></span>
                                                <div class="text">
                                                    <h3>Steak</h3>
                                                    <a class="button2" href="#">Read More</a>
                                                </div>
                                                <img alt="" src="images/content/img-menu5.jpg"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="one_fifth columns">
                                        <div class="ts-img">
                                            <div class="image">
                                                <span class="rollover"></span>
                                                <div class="text">
                                                    <h3>Appetizer</h3>
                                                    <a class="button2" href="#">Read More</a>
                                                </div>
                                                <img alt="" src="images/content/img-menu8.jpg"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="line-bottom"></div>
                </div>
                <!-- END AFTER CONTENT -->

                <!-- BEFORE FOOTER -->
                <div id="outerbeforefooter">
                    <div class="container">
                        <div class="row">
                            <div class="one_half columns">
                                <div class="title-wrap">
                                    <div class="line-one-half"></div>
                                    <h2>Welcome to Our Restaurant</h2>
                                    <div class="line-one-half"></div>
                                </div>
                                <p class="colortext textBold">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed sagittis arcu id erat dictum lobortis.</p>
                                <p><img alt="" src="images/content/chef2.png" class="alignleft"/>Phasellus adipiscing, nulla sed facilisis malesuada, lorem mauris semper nunc, a ultrices tortor orci eget mi. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>

                                <p>Suspendisse vitae sagittis justo, sit amet aliquam dolor. Aenean aliquam, ipsum quis auctor tincidunt, lectus mauris iaculis lacus.</p>
                                <a class="button" href="#">Read More</a>
                            </div>
                            <div class="one_half columns">
                                <div class="title-wrap">
                                    <div class="line-one-half"></div>
                                    <h2>Our Latest News</h2>
                                    <div class="line-one-half"></div>
                                </div>
                                <div id="post-shortcode" class="row">
                                    <div class="one_half columns">
                                        <div class="frame">
                                            <div class="image">
                                                <span class="rollover"></span>
                                                <div class="text">
                                                    <a class="button2" href="#">Read More</a>
                                                </div>
                                                <img src="images/content/post1.jpg" alt=""/>
                                            </div>
                                        </div>
                                        <h3>Family style &amp; catering menu</h3>
                                        <div class="entry-utility">
                                            june 20,2013  /  by <a href="#">Admin</a>
                                        </div>
                                        <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam..</p>
                                    </div>

                                    <div class="one_half columns">
                                        <div class="frame">
                                            <div class="image">
                                                <span class="rollover"></span>
                                                <div class="text">
                                                    <a class="button2" href="#">Read More</a>
                                                </div>
                                                <img src="images/content/post2.jpg" alt=""/>
                                            </div>
                                        </div>
                                        <h3>Baked-Fish</h3>
                                        <div class="entry-utility">
                                            june 20,2013  /  by <a href="#">Admin</a>
                                        </div>
                                        <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam..</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END BEFORE FOOTER -->

                <!-- FOOTER -->
                <div id="footer-include">
                    <!--<script>
                        $("#footer-include").load("footer.jsp");
                    </script> -->
                    <jsp:include page="footer.jsp" />
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
