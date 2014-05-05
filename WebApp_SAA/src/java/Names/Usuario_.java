package Names;

import Entities.Solicitud;
import Entities.TipoUsuario;
import Entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-05T21:44:42")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> direccion;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, String> nombres;
    public static volatile SingularAttribute<Usuario, String> telefono2;
    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile ListAttribute<Usuario, Solicitud> solicitudList;
    public static volatile SingularAttribute<Usuario, String> telefono1;
    public static volatile SingularAttribute<Usuario, TipoUsuario> idTipoUsuario;
    public static volatile SingularAttribute<Usuario, String> password;

}