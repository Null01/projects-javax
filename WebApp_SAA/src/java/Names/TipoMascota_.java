package Names;

import Entities.Mascota;
import Entities.Mascota;
import Entities.TipoMascota;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-05T21:44:42")
@StaticMetamodel(TipoMascota.class)
public class TipoMascota_ { 

    public static volatile SingularAttribute<TipoMascota, String> nombre;
    public static volatile SingularAttribute<TipoMascota, Integer> idTipoMascota;
    public static volatile ListAttribute<TipoMascota, Mascota> mascotaList;

}