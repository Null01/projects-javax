<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->

    <!-- Mirrored from demohtml.templatesquare.com/terramia/contact.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Mar 2015 03:35:51 GMT -->
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
                                    <h1 class="pagetitle">Contact Us</h1>
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
                            <section id="maincontent" class="nine columns positionleft">

                                <section class="content">

                                    <p>Morbi nec nunc condimentum, egestas dui nec, fermentum diam. Vivamus vel tincidunt libero, vitae elementum ligula. Nunc placerat purus quam, ac adipiscing arcu rutrum eu. Vestibulum adipiscing ut augue ut auctor. </p>

                                    <h3>For Reservation Please Call Us or Use the Form Below</h3>
                                    <div class="phone"><img src="images/phone.png" alt=""  /><span>+1 800 123 456  /  +1 800 777 888</span></div>

                                    <div id="contactform">
                                        <form id="contact" action="#">
                                            <fieldset>
                                                <span class="error" id="name_error">Please enter name !</span>
                                                <span class="error" id="email_error">Please enter email address !</span>
                                                <span class="error" id="email_error2">Please enter valid email address !</span>
                                                <span class="error" id="msg_error">Please enter message !</span>
                                                <label for="name" id="name_label">Your name <span class="required">(required)</span></label>
                                                <input type="text" name="name" id="name" size="50" value="" class="text-input" />
                                                <label for="email" id="email_label">Your email <span class="required">(required)</span></label>
                                                <input type="text" name="email" id="email" size="50" value="" class="text-input" />
                                                <label for="subject" id="subject_label">Subject</label>
                                                <input type="text" name="subject" id="subject" size="50"  value="" class="text-input" />
                                                <label for="msg" id="msg_label">Your message <span class="required">(required)</span></label>
                                                <textarea rows="7" name="msg" id="msg" class="text-input"></textarea>
                                                <br class="clear" />
                                                <input type="submit" name="submit" class="button" id="submit_btn" value="Send Message"/>
                                            </fieldset>
                                        </form>
                                    </div><!-- end contactform -->

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

                                    </ul>
                                </div>

                            </aside>
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


        <!-- Form Contact Script -->
        <script type="text/javascript" src="js/contact.js"></script>


        <!-- jQuery Dropdown Mobile -->
        <script type="text/javascript" src="js/tinynav.min.js"></script>

        <!-- Custom Script -->
        <script type="text/javascript" src="js/custom.js"></script>

    </body>

    <!-- Mirrored from demohtml.templatesquare.com/terramia/contact.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Mar 2015 03:35:52 GMT -->
</html>
