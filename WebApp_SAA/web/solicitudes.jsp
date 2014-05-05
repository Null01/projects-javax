<%-- 
    Document   : solicitudes
    Created on : May 6, 2014, 4:29:37 PM
    Author     : Yury
--%>

<%@page import="Entities.Solicitud"%>
<%@page import="java.util.List"%>
<%@page import="Facade.ControllerJPASolicitud"%>
<jsp:include page="adminHeader.jsp"></jsp:include>

    <!-- Intro -->
    <section id="intro">
        <div align="center">
        <%
            ControllerJPASolicitud controllerJPAMascota = new ControllerJPASolicitud();
            List<Solicitud> listaSolicitudes = controllerJPAMascota.getListSolicitudes();

            if (listaSolicitudes == null || listaSolicitudes.isEmpty()) {
                out.println(" - NO HAY SOLICITUDES - ");
            } else {
        %>

        <form id="form-view" action="" >

            <b> Filtro de busqueda: </b> 
            <input id="date_min" type="date" name="date_min" style="width:220px ;height:25px"> <b> a </b>
            <input id="date_max" type="date" name="date_max" style="width:220px ;height:25px">
            <button type="submit" style="width:100px; height:30px">
                <i> <b>Buscar. </b></i>
            </button>
            <table>
                <tr>
                    <td><h1> ID_SOLICITUD </h1></td>
                    <td><h1> MASCOTA </h1></td>
                    <td><h1> USUARIO </h1></td>
                    <td><h1> ESTADO  </h1></td>
                    <td><h1> FECHA </h1></td>
                    <td><h1> </h1></td>
                </tr>

                <%
                    for (Solicitud row : listaSolicitudes) {
                        out.print("<tr>");
                        out.print("<td> " + row.getIdSolicitud() + "</td>");
                        out.print("<td> " + row.getIdMascota().getNombre() + "</td>");
                        out.print("<td> " + row.getIdUsuario().getNombres() + " " + row.getIdUsuario().getApellidos() + "</td>");
                        out.print("<td> " + row.getIdEstadoSolicitud().getNombre() + "</td>");
                        out.print("<td> " + row.getFechaSolicitud() + "</td>");
                        if (row.getIdEstadoSolicitud().getIdEstadoSolicitud() == 1) {
                            out.print("<td><a href='aceptarSolicitudController?idsolicitud=" + row.getIdSolicitud() + "'>Aceptar</a></td>");
                            out.print("<td><a href='denegarSolicitudController?idsolicitud=" + row.getIdSolicitud() + "'>Denegar</a></td>");
                        }
                        out.print("</tr>");
                    }
                %>

            </table>
            <% if (request.getAttribute("mensaje") != null) {%>
            <td> <%=request.getAttribute("mensaje")%> </td>
            <%}%>
        </form>

        <%}
        %>

    </div>

    <script>
        $(function() {
            $("#dialog").dialog({
                autoOpen: false,
                modal: true,
                resizable: false,
                width: 'auto',
                dialogClass: "dlg-no-close"
            });
            $("#btn-create").on("click", function() {
                $("#dialog").dialog("open");
            });
        });
    </script>

</section>

<!-- Footer -->
</div>
</div>
</div>
</div>
<!-- Main Wrapper -->

<!-- Footer Wrapper -->
<div id="footer-wrapper">

    <!-- Footer -->
    <section id="footer" class="container">
    </section>    
</div>
</body>
</html>