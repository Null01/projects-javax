<%@page import="edu.lab.session.ITipoUsuario"%>
<%@page import="edu.lab.modelo.Usuario"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            Usuario usuario = (Usuario) session.getAttribute("user_data");
        %>
        <div id="outerheader">
            <div class="container">
                <header id="top">
                    <div class="row">
                        <div class="nine columns">
                            <div class="logo-icon-l"></div>
                            <div id="logo">
                                <a href="index.jsp">
                                    <img src="images/logo.png" alt=""/>
                                    <h1>Cookbook</h1>                                
                                    <span class="desc">Like a Cook</span>
                                </a>
                            </div>
                            <div class="logo-icon-r"></div>
                        </div>
                        <div class="three columns">
                            <form action="#" id="searchform" method="get">
                                <span class="bg-left"></span>
                                <div class="bg-right">
                                    <input type="text" name="s" id="s" value="Search Form" onFocus="if (this.value == 'Search Form')
                                                this.value = '';" onBlur="if (this.value == '')
                                                            this.value = 'Search Form';" /> 
                                    <input type="submit" class="searchbutton" value="" />
                                </div>
                            </form>
                        </div>
                        <div class="twelve columns">
                            <section id="navigation">
                                <div class="nav-left"></div>
                                <nav id="nav-wrap">
                                    <ul id="topnav" class="sf-menu">
                                        <li><a href="index.jsp">Home</a></li>
                                        <li><a href="about.jsp">About Us</a></li>
                                        <li><a href="menu.jsp">Our Menus</a></li>
                                        <!-- <li><a href="blog-sidebar-right.html">Our Blog</a></li> -->
                                        <li><a href="gallery.jsp">Gallery</a></li>
                                            <%
                                                if (usuario != null) {
                                                    boolean isAdmin = usuario.getTipo().compareTo(ITipoUsuario.ADMIN) == 0;
                                                    String pageUserHome = ((isAdmin) ? "admin" : "user") + "-home.jsp";
                                            %>
                                        <li><a href="<%=pageUserHome%>">User</a></li>
                                        <li><a href="Logout">Logout</a></li>

                                        <%
                                            }
                                        %>
                                    </ul>
                                    <div class="clear"></div>
                                </nav>
                            </section>
                        </div>
                        <div class="clear"></div>
                    </div>
                </header>
            </div>
        </div>
    </body>
</html>