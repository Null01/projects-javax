package Names;

import Entities.IdEstadoSolicitud;
import Entities.Solicitud;
import Entities.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-05T21:44:42")
@StaticMetamodel(IdEstadoSolicitud.class)
public class IdEstadoSolicitud_ { 

    public static volatile SingularAttribute<IdEstadoSolicitud, String> nombre;
    public static volatile SingularAttribute<IdEstadoSolicitud, Integer> idEstadoSolicitud;
    public static volatile ListAttribute<IdEstadoSolicitud, Solicitud> solicitudList;

}