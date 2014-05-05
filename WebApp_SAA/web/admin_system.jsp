<%-- 
    Document   : admin_system
    Created on : 28/04/2014, 12:50:49 AM
    Author     : duran
--%>
<%@page import="Enums.LabelsStates"%>
<%@page import="Entities.EstadoMascota"%>
<%@page import="Entities.TipoMascota"%>
<%@page import="Entities.Raza"%>
<%@page import="Facade.ControllerJPAMascota"%>
<%@page import="Entities.Mascota"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
        Dopetrope 2.5 by HTML5 UP
        html5up.net | @n33co
        Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->

<jsp:include page="adminHeader.jsp"></jsp:include>

    

    <script type="text/javascript">
        $(document).ready(function() {
            var now = new Date();

            var day = ("0" + now.getDate()).slice(-2);
            var month = ("0" + (now.getMonth() + 1)).slice(-2);
            var today = now.getFullYear() + "-" + (month) + "-" + (day);

            $('#date_min').val(today);
            $('#date_max').val(today);

        });
    </script>

    <!-- Intro -->
    <section id="intro">
        <div align="center">
        <%
            ControllerJPAMascota controllerJPAMascota = new ControllerJPAMascota();
            List<Mascota> listaMascotas = controllerJPAMascota.getListaMascota();

            if (listaMascotas == null || listaMascotas.isEmpty()) {
                out.println(" - NO HAY MASCOTAS RELACIONADAS - ");
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
                    <td><h1> ID_MASCOTA </h1></td>
                    <td><h1> NOMBRE </h1></td>
                    <td><h1> EDAD </h1></td>
                    <td><h1> ESTADO MASCOTA </h1></td>
                    <td><h1> TIPO MASCOTA MASCOTA </h1></td>
                    <td><h1> </h1></td>
                </tr>

                <%
                    for (Mascota row : listaMascotas) {
                        out.print("<tr>");
                        out.print("<td> " + row.getIdMascota() + "</td>");
                        out.print("<td> " + row.getNombre() + "</td>");
                        out.print("<td> " + row.getEdad() + "</td>");
                        out.print("<td> " + row.getIdEstadoMascota().getNombre() + "</td>");
                        out.print("<td> " + row.getIdTipoMascota().getNombre() + "</td>");
                %>
                <td>
                    <button type="submit" style="width:65px; height:30px">
                        <i class="fa fa-refresh fa-spin"> </i>
                    </button>
                </td>

                <td>
                    <button type="submit" style="width:65px; height:30px">
                        <i class="fa fa-trash-o fa-lg"> </i>
                    </button>
                </td>
                <%
                        out.print("</tr>");
                    }
                %>

            </table>
            <br/><br/>
            <button id="btn-create" type="button" style="width:100px; height:30px">
                <i class="fa fa-user fa-fw"> <b>Crear. </b></i>
            </button>

        </form>

        <%}
        %>

    </div>

    <div id="dialog" title="CREAR UNA MASCOTA">
        <form id="form-create" action="CreateMascotaController" method="post">
            <table style="width:450px">
                <tr>
                    <td> NOMBRE</td>
                    <td> <input type="text" name="nombre" required></td>
                </tr>

                <tr>
                    <td> EDAD</td>
                    <td> <input type="number" name="edad" min="1" max="20" required></td>
                </tr>

                <tr>
                    <td> TIPO DE RAZA</td>
                    <td> 
                        <select style="width:300px" name="raza" required>
                            <%
                                List<Raza> listaRazas = controllerJPAMascota.getListaRazas();
                                if (listaRazas == null || listaRazas.isEmpty()) {
                                    out.print("- NO HAY RAZAS - ");
                                } else {
                                    for (Raza raza : listaRazas) {
                            %>

                            <option value ="<%= raza.getIdRaza()%>"> <% out.print(raza.getNombre());  %> </option>

                            <%      }
                                }
                            %>                                                
                        </select>
                    </td>
                </tr>
                <td> TIPO DE MASCOTA</td>
                <td> <select style="width:300px" name="mascota" required>
                        <%
                            List<TipoMascota> listaTipoMascota = controllerJPAMascota.getListaTipoMascota();
                            if (listaTipoMascota == null || listaTipoMascota.isEmpty()) {
                                out.print("- NO HAY RAZAS - ");
                            } else {
                                for (TipoMascota tipoMascota : listaTipoMascota) {
                        %>

                        <option value ="<%= tipoMascota.getIdTipoMascota()%>"> <% out.print(tipoMascota.getNombre());  %> </option>

                        <%      }
                            }
                        %>                                                
                    </select>
                </td>
            </table>
            <button id="btn-create-submit" type="submit"  style="width:100px; height:30px; text-align:center; ">
                <i>Crear Mascota</i>
            </button>

        </form>

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