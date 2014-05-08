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
            <h2><%=request.getParameter("titulo")%></h2>
    </header>
</section>
<div align="center"> 
    <form id="formLogin" name="login" action="registerController">
        <table border="1" style="width:500px" >
            <tbody>
                <tr>
                    <%
                        int estadoMascotas = Integer.parseInt(request.getParameter("estado"));
                        ControllerJPAMascota controllerJPAMascota = new ControllerJPAMascota();
                        List<Mascota> mascotas = controllerJPAMascota.getListaMascotaPorEstado(estadoMascotas);
                        for (Mascota mascota : mascotas) {
                    %>
            <section class="box">
                <a href="#" class="image image-full"><img src="images/pic08.jpg" alt="" /></a>
                <header>
                    <h3><%= mascota.getNombre()%></h3>
                    <span class="byline"><%=mascota.getEdad() + " años"%></span>
                </header>
                <p>
                    <%
                        out.println(mascota.getIdTipoMascota().getIdTipoMascota() == 3 ? mascota.getOtroTipoMascota() : mascota.getIdTipoMascota().getNombre());
                        out.println(" raza ");
                        out.println(mascota.getIdRaza().getIdRaza() == 4 ? mascota.getOtraRaza() : mascota.getIdRaza().getNombre());
                    %>
                </p>
                <footer class="actions">
                    <%if (session.getAttribute("Usuario") != null && estadoMascotas == 2) {
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

</div>
</div>
<!-- Footer Wrapper -->
<div id="footer-wrapper">

    <!-- Footer -->
    <section id="footer" class="container">
    </section>    
</div>

</body>
</html>