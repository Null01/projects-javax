package Names;

import Entities.TipoUsuario;
import Entities.Usuario;
import Entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-05T21:44:42")
@StaticMetamodel(TipoUsuario.class)
public class TipoUsuario_ { 

    public static volatile SingularAttribute<TipoUsuario, String> nombre;
    public static volatile ListAttribute<TipoUsuario, Usuario> usuarioList;
    public static volatile SingularAttribute<TipoUsuario, Integer> idTipoUsuario;

}