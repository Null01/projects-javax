package model;

import java.util.StringTokenizer;

/**
 *
 * @author duran
 * @version 1.0
 */
public class QueryDB {

    private static final String SELECT = "SELECT";
    private static final String FROM = "FROM";
    private static final String WHERE = "WHERE";

    /**
     * Este metodo manipula una sentecia SQL para que sea posible ser
     * interpretada por la persisencia manejando transaction-type=JTA
     *
     * @param query Sentencia usada en diversos manejadores de DB - SQL
     * @return retorna una cadena legible para la persistencia - JPA
     */
    public static String processQuery(String query) {
        StringTokenizer st = new StringTokenizer(query, " ,=");
        String temp, queryResult = "";
        try {
            while (st.hasMoreTokens()) {
                temp = st.nextToken().toUpperCase();
                switch (temp) {
                    case SELECT:
                        queryResult += SELECT + " p ";
                        break;
                    case FROM:
                        queryResult += FROM + " " + st.nextToken() + " p ";
                        break;
                    case WHERE:
                        queryResult += WHERE + " ";
                        while (st.hasMoreTokens()) {
                            queryResult += " p." + st.nextToken() + " = " + st.nextToken() + " ";
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return queryResult;
    }

}
