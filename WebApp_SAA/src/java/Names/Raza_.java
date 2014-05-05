package Names;

import Entities.Mascota;
import Entities.Mascota;
import Entities.Raza;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-05T21:44:42")
@StaticMetamodel(Raza.class)
public class Raza_ { 

    public static volatile SingularAttribute<Raza, String> nombre;
    public static volatile SingularAttribute<Raza, Integer> idRaza;
    public static volatile ListAttribute<Raza, Mascota> mascotaList;

}