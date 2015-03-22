package edu.lab.services.publish;

import edu.lab.entities.Commentspublish;
import edu.lab.entities.Publish;
import edu.lab.modelo.Comentario;
import edu.lab.modelo.Publicacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author andresfelipegarciaduran
 */
@Stateful
@LocalBean
public class PublishControllerBean {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JEE_Cookbook-ejbPU");

    public List<Publicacion> obtenerPublicacionPorUsuario(String email) {
        List<Publicacion> outcomePublish = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM " + Publish.class.getSimpleName() + " p WHERE p.email.email = :idEmail").setParameter("idEmail", email);
        List list = query.getResultList();
        if (list != null) {
            for (Object objPublish : list) {
                Publish p = (Publish) objPublish;
                Publicacion publicacion = new Publicacion(p.getIdpublish(), p.getTittle(), p.getDescription(), p.getDatecreated(), p.getEmail().getEmail(), null);
                List<Commentspublish> commentspublishList = p.getCommentspublishList();
                List<Comentario> outcomeComments = new ArrayList<>();
                if (commentspublishList != null) {
                    for (Object objComment : commentspublishList) {
                        Commentspublish c = (Commentspublish) objComment;
                        Comentario comentario = new Comentario(c.getIdcomment(), c.getIdcommenttoothercomment(), c.getCommentspublish(), c.getDatecreated(), c.getEmail().getEmail(), null);
                        outcomeComments.add(comentario);
                    }
                }
                publicacion.setComment(outcomeComments);
                outcomePublish.add(publicacion);
            }
        }
        return outcomePublish;
    }
}
