/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entities.Auditor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import session.AuditorFacade;
import session.AuditorFacadeLocal;
import session.LoginFacadeLocal;

/**
 *
 * @author duran
 * @version 1.0
 */
public class InterceptorWeb {

    @Resource(lookup = "ds_sgr")
    javax.sql.DataSource data;

    private final String query = "INSERT INTO auditor (nameClass, namemethod, named) VALUES (?,?,?)";

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        String nameClass = context.getMethod().getDeclaringClass().getName();
        String methodClass = context.getMethod().getName();
        String name = context.getTarget().getClass().getName();

        Connection connection = data.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nameClass);
            statement.setString(2, name);
            statement.setString(3, methodClass);
            statement.executeUpdate();

            System.out.println("Before: " + nameClass + "  " + name + "  " + methodClass);

            Auditor auditor = new Auditor();
            auditor.setNameclass(nameClass);
            auditor.setNamed(name);
            auditor.setNamemethod(methodClass);
            return context.proceed();

        } catch (Exception e) {
            throw e;
        } finally {
            connection.close();
        }

    }

    private AuditorFacadeLocal lookupAuditorFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (AuditorFacadeLocal) c.lookup("java:global/JEE_SGR/JEE_SGR-ejb/AuditorFacade!session.AuditorFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void persist(Object object) {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>JEE_SGR-ejbPU2</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
