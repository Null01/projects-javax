package edu.lab.services.publish;

import edu.lab.entities.Commentspublish;
import edu.lab.entities.Publish;
import edu.lab.modelo.Comentario;
import edu.lab.modelo.Publicacion;
import edu.lab.modelo.Usuario;
import java.util.ArrayList;
import java.util.Date;
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
                Usuario usuario = new Usuario(p.getEmail().getFname(), p.getEmail().getLname(), p.getEmail().getEmail(), "");
                Publicacion publicacion = new Publicacion(p.getIdpublish(), p.getTittle(), p.getDescription(), p.getDatecreated(), usuario, null);
                List<Commentspublish> commentspublishList = p.getCommentspublishList();
                List<Comentario> outcomeComments = new ArrayList<>();
                if (commentspublishList != null) {
                    for (Object objComment : commentspublishList) {
                        Commentspublish c = (Commentspublish) objComment;
                        Usuario u = new Usuario(c.getEmail().getFname(), c.getEmail().getLname(), c.getEmail().getEmail(), "");
                        System.out.println(u.getNombre() + " " + u.getApellido());
                        Comentario comentario = new Comentario(c.getIdcomment(), c.getIdcommenttoothercomment(), c.getCommentspublish(), c.getDatecreated(), u, null);
                        outcomeComments.add(comentario);
                    }
                }
                publicacion.setComment(outcomeComments);
                outcomePublish.add(publicacion);
            }
        }
        return outcomePublish;
    }

    public void createPublication(String idpublish, Usuario usuario, String comment) {
        EntityManager em = emf.createEntityManager();
        
        Commentspublish commentPublish = new Commentspublish();
        commentPublish.setCommentspublish(comment);
        commentPublish.setDatecreated(new Date());
        commentPublish.setIdcommenttoothercomment(0); // arreglar
        Publish publish = em.find(Publish.class, Integer.parseInt(idpublish));
        commentPublish.setIdpublish(publish);
        edu.lab.entities.Usuario usuario1 = em.find(edu.lab.entities.Usuario.class, usuario.getCorreo());
        commentPublish.setEmail(usuario1);

        em.getTransaction().begin();
        em.persist(commentPublish);
        em.getTransaction().commit();
    }

}
