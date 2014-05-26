package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author duran
 * @version 1.0
 */
public class AnalyzeSemantic extends Analyze implements IAnalyze {

    private ArrayList<String> listPhrase;

    private AnalyzeSemantic() {
    }

    public AnalyzeSemantic(ArrayList<String> listPhrase) {
        this.listPhrase = listPhrase;
        this.initialize();
    }

    @Override
    public boolean analyzeText() {
        while (!listPhrase.isEmpty()) {
            String string = listPhrase.get(0);
            listPhrase.remove(0);
            if (string.compareTo(EExpress.INICIO.getExpress()) == 0) {
                break;
            }
        }

        try {

            while (!listPhrase.isEmpty()) {
                boolean isReserveWord = false;
                if (listPhrase.get(0).compareTo(EExpress.ESCRITURA.getExpress()) == 0) {
                    listPhrase.remove(0);
                    ruleWrite();
                    listPhrase.remove(0);
                    isReserveWord = true;
                }
                if (listPhrase.get(0).compareTo(EExpress.LECTURA.getExpress()) == 0) {
                    listPhrase.remove(0);
                    ruleRead();
                    listPhrase.remove(0);
                    isReserveWord = true;
                }

                if (listPhrase.get(0).compareTo(EExpress.SI.getExpress()) == 0) {
                    listPhrase.remove(0);
                    ruleLogicIf();
                    listPhrase.remove(0);
                    isReserveWord = true;
                }
                if (listPhrase.get(0).compareTo(EExpress.MIENTRAS.getExpress()) == 0) {
                    listPhrase.remove(0);
                    ruleLogicWhile();
                    listPhrase.remove(0);
                    isReserveWord = true;
                }

                if (isVariable(listPhrase.get(0), false)) {
                    ruleOperations();
                    isReserveWord = true;
                }

                if (listPhrase.get(0).compareTo(EExpress.FIN.getExpress()) == 0) {
                    listPhrase.remove(0);
                    if (!listPhrase.isEmpty()) {
                        throw new Exception("ERROR - ESTRUCTURA INCORRECTA, REVISE DETALLADAMENTE");
                    }
                    break;
                }

                if (!isReserveWord) {
                    throw new Exception("ERROR - PARAMETROS NOS VALIDOS");
                }
            }
        } catch (Exception e) {
            getStackError().push(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Stack<String> tokenizar(String line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validate(Stack<String> stack, String key, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<String> getListPhrase() {
        return listPhrase;
    }

    public Stack<String> getStackError() {
        return stackError;
    }

    private void ruleWrite() throws Exception {
        if (!listPhrase.isEmpty() && listPhrase.get(0).compareTo(EOperators.PAR_A.getOperator()) == 0) {
            listPhrase.remove(0);
            while (!listPhrase.isEmpty()) {
                if (!isVariable(listPhrase.get(0), true)) {
                    if (!isVariable(listPhrase.get(0), false)) {
                        throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION write(?) SON INCORRECTOS");
                    }
                }
                listPhrase.remove(0);

                if (listPhrase.get(0).compareTo(EOperators.PAR_B.getOperator()) == 0) {
                    if (listPhrase.get(1).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0) {
                        listPhrase.remove(0);
                        break;
                    } else {
                        throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION write(?) SON INCORRECTOS - NO HAY ()");
                    }
                }
                if (listPhrase.get(0).compareTo(EOperators.MAS.getOperator()) != 0) {
                    throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION write(?...) SON INCORRECTOS - NO HAY SEPARADOR");
                }
                listPhrase.remove(0);
            }
        } else {
            throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION write(?) SON INCORRECTOS - NO HAY ()");
        }
    }

    private void ruleRead() throws Exception {
        if (!listPhrase.isEmpty() && listPhrase.get(0).compareTo(EOperators.PAR_A.getOperator()) == 0) {

            listPhrase.remove(0);
            while (!listPhrase.isEmpty()) {
                if (!isVariable(listPhrase.get(0), false)) {
                    throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION read(?) SON INCORRECTOS");
                }
                listPhrase.remove(0);

                if (listPhrase.get(0).compareTo(EOperators.PAR_B.getOperator()) == 0) {
                    if (listPhrase.get(1).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0) {
                        listPhrase.remove(0);
                        break;
                    } else {
                        throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION read(?) SON INCORRECTOS");
                    }
                }
                if (listPhrase.get(0).compareTo(EOperators.COMA.getOperator()) != 0) {
                    throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION read(?...) SON INCORRECTOS");
                }
                listPhrase.remove(0);
            }
        } else {
            throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION read(?) SON INCORRECTOS");
        }
    }

    private void ruleLogicIf() throws Exception {

        if (!listPhrase.isEmpty() && listPhrase.get(0).compareTo(EOperators.PAR_A.getOperator()) == 0) {

            listPhrase.remove(0);
            while (!listPhrase.isEmpty()) {
                if (!isVariable(listPhrase.get(0), false)) {
                    if (!isDecimal(listPhrase.get(0)) && !isEntero(listPhrase.get(0))) {
                        throw new Exception("ERROR - EL PARAMETRO LOGICO ES INCORRECTO [" + listPhrase.get(0) + " ]");
                    }
                }
                listPhrase.remove(0);

                if (listPhrase.get(0).compareTo(EOperators.PAR_B.getOperator()) == 0) {
                    if (listPhrase.get(1).compareTo(EExpress.ENTONCES.getExpress()) == 0) {
                        listPhrase.remove(0);
                        boolean isValid = false;
                        for (int i = 0; i < listPhrase.size(); i++) {
                            if (listPhrase.get(i).compareTo(EExpress.FIN_SI.getExpress()) == 0) {
                                listPhrase.remove(i);
                                isValid = true;
                                break;
                            }
                        }
                        if (!isValid) {
                            throw new Exception("ERROR - LA ESTRUCTURA BLOQUE ES INCORRECTA.");
                        }
                        break;
                    } else {
                        throw new Exception("ERROR - LOS PARAMETROS DEL BLOQUE IF(?) SON INCORRECTOS.");
                    }
                }

                if (!isSymbolLogic(listPhrase.get(0))) {
                    throw new Exception("ERROR - EL OPERADOR LOGICO ES INCORRECTO [" + listPhrase.get(0) + " ]");
                }
                listPhrase.remove(0);
            }
        } else {
            throw new Exception("ERROR - CONDICION LOGICA NO CUMPLE PARAMETROS.");
        }
    }

    private void ruleLogicWhile() throws Exception {
        if (!listPhrase.isEmpty() && listPhrase.get(0).compareTo(EOperators.PAR_A.getOperator()) == 0) {
            listPhrase.remove(0);
            while (!listPhrase.isEmpty()) {
                if (!isVariable(listPhrase.get(0), false)) {
                    if (!isDecimal(listPhrase.get(0)) && !isEntero(listPhrase.get(0))) {
                        throw new Exception("ERROR - EL PARAMETRO LOGICO ES INCORRECTO [" + listPhrase.get(0) + " ]");
                    }
                }
                listPhrase.remove(0);

                if (listPhrase.get(0).compareTo(EOperators.PAR_B.getOperator()) == 0) {
                    if (listPhrase.get(1).compareTo(EExpress.ENTONCES.getExpress()) == 0) {
                        listPhrase.remove(0);
                        boolean isValid = false;
                        for (int i = 0; i < listPhrase.size(); i++) {
                            if (listPhrase.get(i).compareTo(EExpress.FIN_MIENTRAS.getExpress()) == 0) {
                                listPhrase.remove(i);
                                isValid = true;
                                break;
                            }
                        }
                        if (!isValid) {
                            throw new Exception("ERROR - LA ESTRUCTURA BLOQUE ES INCORRECTA.");
                        }
                        break;
                    } else {
                        throw new Exception("ERROR - LOS PARAMETROS DEL BLOQUE WHILE(?) SON INCORRECTOS");
                    }
                }

                if (!isSymbolLogic(listPhrase.get(0))) {
                    throw new Exception("ERROR - EL OPERADOR LOGICO ES INCORRECTO [" + listPhrase.get(0) + " ]");
                }
                listPhrase.remove(0);

            }

        } else {
            throw new Exception("ERROR - CONDICION LOGICA NO CUMPLE PARAMETROS.");
        }
    }

    private void ruleOperations() throws Exception {

    }

    public void clearAnalyze() {
        stackError.clear();
    }
}
