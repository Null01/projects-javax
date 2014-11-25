package Controller;

import Entities.Assignee;
import Entities.Classification;
import Entities.Inventor;
import Entities.Patent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author andresfelipegarciaduran
 */
public class PatentController implements Serializable {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApp_PLSQLPU");

    private static final HashSet<String> set_type_data = new HashSet<String>();

    static {
        set_type_data.add("NUMBER");
        set_type_data.add("DATE");
        set_type_data.add("VARCHAR");
        set_type_data.add("VARCHAR2");
        set_type_data.add("CLOB");
    }

    public List<String> getListFieldTablePatent() {
        List<String> outcome = new ArrayList<String>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String str_query = "SELECT COLUMN_NAME, DATA_TYPE FROM ALL_TAB_COLUMNS WHERE TABLE_NAME = ?";
        Query query = em.createNativeQuery(str_query);
        query.setParameter(1, Patent.class.getSimpleName().toUpperCase());
        List<?> resultSet = query.getResultList();
        em.getTransaction().commit();
        for (Object object : resultSet) {
            Object[] array_objects = (Object[]) object;
            String columnName = String.valueOf(array_objects[0]);
            String dataType = String.valueOf(array_objects[1]);
            if (set_type_data.contains((String) dataType)) {
                outcome.add(columnName);
            }
        }
        return outcome;
    }

    public List<String> getValuesTableInventor() {
        List<String> outcome = new ArrayList<String>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Inventor.findAll");
        em.getTransaction().commit();
        List<?> resultSet = query.getResultList();
        for (Object object : resultSet) {
            Inventor inventor = (Inventor) object;
            outcome.add(inventor.getInventorId() + "-" + inventor.getName());
        }
        return outcome;
    }

    public List<String> getValuesTableClassification() {
        List<String> outcome = new ArrayList<String>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Classification.findAll");
        em.getTransaction().commit();
        List<?> resultSet = query.getResultList();
        for (Object object : resultSet) {
            Classification classification = (Classification) object;
            outcome.add(classification.getClassificationId() + "-" + classification.getCode());
        }
        return outcome;
    }

    public List<String> getValuesTableAssignee() {
        List<String> outcome = new ArrayList<String>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Assignee.findAll");
        em.getTransaction().commit();
        List<?> resultSet = query.getResultList();
        for (Object object : resultSet) {
            Assignee assignee = (Assignee) object;
            outcome.add(assignee.getAssigneeId() + "-" + assignee.getName());
        }
        return outcome;
    }

    public boolean createPatent(String idPatent, String titlePatent, Date publicDate, String description, String inventors, String classification, String assignee, String contentType, File file, Object content) throws FileNotFoundException, SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Patent patent = new Patent(idPatent);
        patent.setPatentTitle(titlePatent);
        patent.setPublicDate(publicDate);
        patent.setDescription(description);
        if (content != null) {
            patent.setDocumentExt(contentType);
        }

        StringTokenizer st;

        st = new StringTokenizer(inventors, "-");
        Inventor reference_Inventor = em.getReference(Inventor.class, new BigDecimal(st.nextToken()));
        List<Inventor> list_Inventors = new ArrayList<Inventor>();
        list_Inventors.add(reference_Inventor);
        patent.setInventorList(list_Inventors);

        st = new StringTokenizer(classification, "-");
        Classification reference_Classification = em.getReference(Classification.class, new BigDecimal(st.nextToken()));
        List<Classification> list_Classifications = new ArrayList<Classification>();
        list_Classifications.add(reference_Classification);
        patent.setClassificationList(list_Classifications);

        st = new StringTokenizer(assignee, "-");
        Assignee reference_Assignee = em.getReference(Assignee.class, new BigDecimal(st.nextToken()));
        List<Assignee> list_Assignees = new ArrayList<Assignee>();
        list_Assignees.add(reference_Assignee);
        patent.setAssigneeList(list_Assignees);

        patent.setDocument(null);
        patent.setRelatedPatentsList(null);
        patent.setRelatedPatentsList1(null);
        transaction.begin();
        em.persist(patent);
        transaction.commit();

        if (content != null) {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            PreparedStatement prepareStatement = connection.prepareStatement("UPDATE " + Patent.class.getSimpleName() + " SET DOCUMENT = ? WHERE PATENT_ID = ?");
            InputStream inputStream = new FileInputStream(file);
            prepareStatement.setBinaryStream(1, inputStream, (int) file.length());
            prepareStatement.setString(2, idPatent);
            prepareStatement.executeUpdate();
            em.getTransaction().commit();
        }
        return true;
    }

    public boolean deletePatent(String idPatent) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Patent patent = em.find(Patent.class, idPatent);
        em.remove(patent);
        em.getTransaction().commit();
        return true;
    }

    public List<Patent> searchPatents(String field, String keyword) throws SQLException {
        String str_search = "%";
        StringTokenizer st = new StringTokenizer(keyword);
        while (st.hasMoreTokens()) {
            str_search += st.nextToken() + "%";
        }
        List<Patent> outcome = new ArrayList<Patent>();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery("SELECT * FROM " + Patent.class.getSimpleName() + " WHERE " + field + "  like ?");
        query.setParameter(1, str_search);
        em.getTransaction().commit();
        List<?> resultSet = query.getResultList();
        for (Object object : resultSet) {
            Object[] array_objects = (Object[]) object;
            Patent patent = new Patent(String.valueOf(array_objects[0]));
            patent.setPatentTitle(String.valueOf(array_objects[1]));
            patent.setPublicDate((Date) array_objects[2]);
            patent.setDocument((array_objects[3] == null) ? null : new SerialBlob((byte[]) array_objects[3]));
            patent.setDescription(String.valueOf(array_objects[4]));
            patent.setDocumentExt(String.valueOf(array_objects[5]));
            outcome.add(patent);
        }
        return outcome;
    }

    private void callProcedureOperationsDocument(String operation, Class typeClass, Object[] object) throws SQLException, FileNotFoundException {
        if (typeClass.equals(Patent.class)) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Connection connection = em.unwrap(Connection.class);
            if (operation.compareTo("CREATE") == 0) {
                CallableStatement prepareCall = connection.prepareCall("{CALL LOAD_FILES_PACK.CREATE_FILE(?,?,?,?)}");
                prepareCall.setString(1, "PATENT");
                prepareCall.setString(2, "PATENT_ID");
                prepareCall.setString(3, String.valueOf(object[0]));
                File file = (File) object[1];
                prepareCall.setBinaryStream(4, new FileInputStream(file), (int) file.length());
                prepareCall.executeQuery();
            }
            em.getTransaction().commit();
        }
    }

    public void updatePatent(Patent patentUpdate) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        /*Patent find = em.find(Patent.class, patentUpdate.getPatentId());
         find.setPatentId(patentUpdate.getPatentId());
         find.setPatentTitle(patentUpdate.getPatentTitle());
         find.setDescription(patentUpdate.getDescription());
         find.setPublicDate(patentUpdate.getPublicDate());
         find.setAssigneeList(patentUpdate.getAssigneeList());
         find.setClassificationList(patentUpdate.getClassificationList());
         find.setInventorList(patentUpdate.getInventorList());
         em.merge(find);*/

        Query query = em.createNativeQuery("UPDATE " + Patent.class.getSimpleName() + " SET DESCRIPTION = ? , PATENT_TITLE = ? WHERE PATENT_ID = ?");
        query.setParameter(1, patentUpdate.getDescription());
        query.setParameter(2, patentUpdate.getPatentTitle());
        query.setParameter(3, patentUpdate.getPatentId());
        query.executeUpdate();
        em.getTransaction().commit();

    }
}
