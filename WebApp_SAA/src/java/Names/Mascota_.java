package Names;

import Entities.EstadoMascota;
import Entities.EstadoMascota;
import Entities.Mascota;
import Entities.Raza;
import Entities.Raza;
import Entities.Solicitud;
import Entities.Solicitud;
import Entities.TipoMascota;
import Entities.TipoMascota;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-05T21:44:42")
@StaticMetamodel(Mascota.class)
public class Mascota_ { 

    public static volatile SingularAttribute<Mascota, String> nombre;
    public static volatile SingularAttribute<Mascota, String> otraRaza;
    public static volatile SingularAttribute<Mascota, Integer> edad;
    public static volatile SingularAttribute<Mascota, TipoMascota> idTipoMascota;
    public static volatile SingularAttribute<Mascota, Raza> idRaza;
    public static volatile ListAttribute<Mascota, Solicitud> solicitudList;
    public static volatile SingularAttribute<Mascota, String> otroTipoMascota;
    public static volatile SingularAttribute<Mascota, EstadoMascota> idEstadoMascota;
    public static volatile SingularAttribute<Mascota, Integer> idMascota;

}