package Controller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProcedureController implements Serializable {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApp_PLSQLPU");

    public List<String> getAllTables() {
        List<String> outcome = new ArrayList<String>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String str_query = "SELECT TABLE_NAME FROM ALL_TAB_COLUMNS WHERE OWNER = ? GROUP BY TABLE_NAME ORDER BY TABLE_NAME";
        Query query = em.createNativeQuery(str_query);
        query.setParameter(1, "PLSQL");
        List<?> resultSet = query.getResultList();
        em.getTransaction().commit();
        for (Object object : resultSet) {
            String tableName = String.valueOf(object);
            outcome.add(tableName);
        }
        return outcome;
    }

    public List<String> getAllFieldsForTable(String tableSelected) {
        List<String> outcome = new ArrayList<String>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String str_query = "SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_NAME = ? ORDER BY COLUMN_NAME";
        Query query = em.createNativeQuery(str_query);
        query.setParameter(1, tableSelected);
        List<?> resultSet = query.getResultList();
        em.getTransaction().commit();
        for (Object object : resultSet) {
            String tableName = String.valueOf(object);
            outcome.add(tableName);
        }
        return outcome;
    }

    public boolean isPossibleCreateTable(String new_table) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String str_query = "SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_NAME = ?";
        Query query = em.createNativeQuery(str_query);
        query.setParameter(1, new_table);
        List<?> resultSet = query.getResultList();
        em.getTransaction().commit();
        return (resultSet == null || resultSet.isEmpty() || resultSet.size() == 0);
    }

    public void callProcedureEncrypted(String nameNewTable, String tableSelected, String fieldSelected, Integer nFields) throws SQLException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(Connection.class);
        CallableStatement prepareCall = connection.prepareCall("{CALL SECURITY_PACK.GAMAL_MAIN(?,?,?,?)}");
        prepareCall.setString(1, nameNewTable);
        prepareCall.setString(2, tableSelected);
        prepareCall.setString(3, fieldSelected);
        prepareCall.setInt(4, nFields);
        prepareCall.executeQuery();
        em.getTransaction().commit();
    }

}
