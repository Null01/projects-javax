package Names;

import Entities.EstadoMascota;
import Entities.Mascota;
import Entities.Mascota;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-05T21:44:42")
@StaticMetamodel(EstadoMascota.class)
public class EstadoMascota_ { 

    public static volatile SingularAttribute<EstadoMascota, String> nombre;
    public static volatile SingularAttribute<EstadoMascota, Integer> idEstadoMascota;
    public static volatile ListAttribute<EstadoMascota, Mascota> mascotaList;

}