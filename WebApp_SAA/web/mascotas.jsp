<%-- 
    Document   : mascotas
    Created on : May 5, 2014, 10:09:22 PM
    Author     : Yury
--%>

<%@page import="java.util.List"%>
<%@page import="Entities.Mascota"%>
<%@page import="Facade.ControllerJPAMascota"%>
<%@page import="Facade.ControllerJPAUsuario"%>
<jsp:include page="header.jsp"></jsp:include>

    <!-- Blog -->
    <section>
        <header class="major">
            <h2>MASCOTAS EN ADOPCIÓN</h2>
        </header>
    </section>
    <div align="center"> 
        <form id="formLogin" name="login" action="registerController">
            <table border="1" style="width:500px" >
                <tbody>
                    <tr>
                    <%
                    ControllerJPAMascota controllerJPAMascota = new ControllerJPAMascota();
                    List<Mascota> mascotas = controllerJPAMascota.getListaMascotaPorEstado(2);
                    for(Mascota mascota : mascotas) {
                    %>
                    <section class="box">
                        <a href="#" class="image image-full"><img src="images/pic08.jpg" alt="" /></a>
                        <header>
                            <h3><%= mascota.getNombre() %></h3>
                            <span class="byline"><%=mascota.getEdad() + " años" %></span>
                        </header>
                        <p>
                            <%
                            out.println(mascota.getIdTipoMascota().getIdTipoMascota() == 3 ? mascota.getOtroTipoMascota() : mascota.getIdTipoMascota().getNombre());
                            out.println(" raza ");
                            out.println(mascota.getIdRaza().getIdRaza() == 4 ? mascota.getOtraRaza(): mascota.getIdRaza().getNombre());
                            %>
                        </p>
                        <footer class="actions">
                            <%if (session.getAttribute("Usuario") != null) {
                                out.println("<a href='confirmacionAdopcion.jsp?idmascota=" + mascota.getIdMascota() + "' class='button fa fa-file-text'>Adoptar</a>");
                            }%>
                        </footer>
                    </section>
                    <%}%>
                    </tr>
                </tbody>
            </table>
        </form>
    </div> 

<jsp:include page="footer.jsp"></jsp:include>