package Names;

import Entities.IdEstadoSolicitud;
import Entities.IdEstadoSolicitud;
import Entities.Mascota;
import Entities.Mascota;
import Entities.Solicitud;
import Entities.Usuario;
import Entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-05T21:44:42")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Integer> idSolicitud;
    public static volatile SingularAttribute<Solicitud, Usuario> idUsuario;
    public static volatile SingularAttribute<Solicitud, IdEstadoSolicitud> idEstadoSolicitud;
    public static volatile SingularAttribute<Solicitud, Date> fechaSolicitud;
    public static volatile SingularAttribute<Solicitud, Mascota> idMascota;

}