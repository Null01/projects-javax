package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 *
 * @author duran
 * @version 1.0
 */
public class AnalyzeSemantic extends Analyze implements IAnalyze {

    private List<String> listPhrase;
    private Map<String, String> data;
    private List<String> listInterpreterExecute;

    private AnalyzeSemantic() {
    }

    public AnalyzeSemantic(List<String> listPhrase, Map<String, String> map) {
        this.listPhrase = listPhrase;
        this.data = map;
        this.listInterpreterExecute = new ArrayList<>();
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
                if (listPhrase.get(0).compareTo(EExpress.ESCRITURA.getExpress()) == 0) {
                    listPhrase.remove(0);
                    ruleWrite();
                    listPhrase.remove(0);
                    continue;
                }
                if (listPhrase.get(0).compareTo(EExpress.LECTURA.getExpress()) == 0) {
                    listPhrase.remove(0);
                    ruleRead();
                    listPhrase.remove(0);
                    continue;
                }

                if (listPhrase.get(0).compareTo(EExpress.SI.getExpress()) == 0) {
                    listPhrase.remove(0);
                    ruleLogicIf();
                    listPhrase.remove(0);
                    continue;
                }
                if (listPhrase.get(0).compareTo(EExpress.MIENTRAS.getExpress()) == 0) {
                    listPhrase.remove(0);
                    ruleLogicWhile();
                    listPhrase.remove(0);
                    continue;
                }

                if (isVariable(listPhrase.get(0), false) && !isExpress(listPhrase.get(0))) {
                    ruleOperations();
                    continue;
                }

                if (listPhrase.get(0).compareTo(EExpress.FIN.getExpress()) == 0) {
                    listPhrase.remove(0);
                    if (!listPhrase.isEmpty()) {
                        throw new Exception("ERROR - ESTRUCTURA INCORRECTA, REVISE DETALLADAMENTE");
                    }
                    break;
                }

                throw new Exception("ERROR - PARAMETROS NOS VALIDOS");

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

    public Map<String, String> getData() {
        return data;
    }

    public List<String> getListPhrase() {
        return listPhrase;
    }

    public Stack<String> getStackError() {
        return stackError;
    }

    private void ruleWrite() throws Exception {
        String execute = EExpress.ESCRITURA.getExpress();
        if (!listPhrase.isEmpty() && listPhrase.get(0).compareTo(EOperators.PAR_A.getOperator()) == 0) {
            listPhrase.remove(0);
            while (!listPhrase.isEmpty()) {
                if (!isVariable(listPhrase.get(0), true)) {
                    if (!isVariable(listPhrase.get(0), false)) {
                        throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION write(?) SON INCORRECTOS");
                    }
                }
                execute += "#" + ((isVariable(listPhrase.get(0), true)) ? listPhrase.get(0) : listPhrase.get(0) + "@" + data.get(listPhrase.get(0)));
                listPhrase.remove(0);

                if (listPhrase.get(0).compareTo(EOperators.PAR_B.getOperator()) == 0) {
                    if (listPhrase.get(1).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0) {
                        listPhrase.remove(0);
                        listInterpreterExecute.add(execute); // Comandos interpretados para realizar la ejecucion.
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
        String execute = EExpress.LECTURA.getExpress();
        if (!listPhrase.isEmpty() && listPhrase.get(0).compareTo(EOperators.PAR_A.getOperator()) == 0) {
            listPhrase.remove(0);
            while (!listPhrase.isEmpty()) {
                if (!isVariable(listPhrase.get(0), false)) {
                    throw new Exception("ERROR - LOS PARAMETROS DE LA FUNCION read(?) SON INCORRECTOS");
                }
                execute += "#" + listPhrase.get(0) + "@" + data.get(listPhrase.get(0));
                listPhrase.remove(0);

                if (listPhrase.get(0).compareTo(EOperators.PAR_B.getOperator()) == 0) {
                    if (listPhrase.get(1).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0) {
                        listPhrase.remove(0);
                        listInterpreterExecute.add(execute); // Comandos interpretados para realizar la ejecucion.
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
            String firstVar = "";
            while (!listPhrase.isEmpty()) {
                if (!isVariable(listPhrase.get(0), false)) {
                    if (!isDecimal(listPhrase.get(0)) && !isEntero(listPhrase.get(0)) && !isVariable(listPhrase.get(0), true)) {
                        throw new Exception("ERROR - EL PARAMETRO LOGICO ES INCORRECTO [" + listPhrase.get(0) + " ]");
                    }
                }

                String var = listPhrase.get(0);
                if (data.get(listPhrase.get(0)) != null) {
                    var = (data.get(listPhrase.get(0)).contains("@")) ? data.get(listPhrase.get(0)).split("@")[0] : data.get(listPhrase.get(0));
                }

                if (!firstVar.isEmpty()) {
                    if (isVariable(var, true)) {
                        var = EExpress.CADENA.getExpress();
                    } else {
                        if (isEntero(var)) {
                            var = EExpress.ENTERO.getExpress();
                        }
                        if (isDecimal(var)) {
                            var = EExpress.DECIMAL.getExpress();
                        }
                    }
                    if (var.compareTo(firstVar) != 0) {
                        throw new Exception("ERROR - INCOMPATIBILIDAD DE DATOS [" + listPhrase.get(0) + " ]");
                    }
                } else {
                    firstVar = var;
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
            String firstVar = "";
            while (!listPhrase.isEmpty()) {
                if (!isVariable(listPhrase.get(0), false)) {
                    if (!isDecimal(listPhrase.get(0)) && !isEntero(listPhrase.get(0)) && !isVariable(listPhrase.get(0), true)) {
                        throw new Exception("ERROR - EL PARAMETRO LOGICO ES INCORRECTO [" + listPhrase.get(0) + " ]");
                    }
                }
                String var = listPhrase.get(0);
                if (data.get(listPhrase.get(0)) != null) {
                    var = (data.get(listPhrase.get(0)).contains("@")) ? data.get(listPhrase.get(0)).split("@")[0] : data.get(listPhrase.get(0));
                }

                if (!firstVar.isEmpty()) {
                    if (isVariable(var, true)) {
                        var = EExpress.CADENA.getExpress();
                    } else {
                        if (isEntero(var)) {
                            var = EExpress.ENTERO.getExpress();
                        }
                        if (isDecimal(var)) {
                            var = EExpress.DECIMAL.getExpress();
                        }
                    }
                    if (var.compareTo(firstVar) != 0) {
                        throw new Exception("ERROR - INCOMPATIBILIDAD DE DATOS [" + listPhrase.get(0) + " ]");
                    }

                } else {
                    firstVar = var;
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
        String value = data.get(listPhrase.get(0));
        if (value != null && !value.isEmpty()) {
            String type = (value.contains("@")) ? value.split("@")[0] : value;
            listPhrase.remove(0);
            if (listPhrase.get(0).compareTo(EOperators.IGUAL.getOperator()) == 0) {
                listPhrase.remove(0);
                while (!listPhrase.isEmpty()) {
                    String var;
                    if (isEntero(listPhrase.get(0))) {
                        var = EExpress.ENTERO.getExpress();
                    } else {
                        if (isVariable(listPhrase.get(0), true)) {
                            var = EExpress.CADENA.getExpress();
                        } else {
                            if (isDecimal(listPhrase.get(0))) {
                                var = EExpress.DECIMAL.getExpress();
                            } else {
                                var = data.get(listPhrase.get(0));
                                var = (var.contains("@")) ? var.split("@")[0] : var;
                            }
                        }
                    }

                    if (var != null && !var.isEmpty() && var.compareTo(type) == 0) {
                        listPhrase.remove(0);
                        if ((isEntero(listPhrase.get(0)) || isDecimal(listPhrase.get(0))) && listPhrase.get(0).charAt(0) == '-') {
                            continue;
                        } else {
                            if (isSymbolOperator(listPhrase.get(0))) {
                                listPhrase.remove(0);
                                continue;
                            }
                        }
                    }

                    if (listPhrase.get(0).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0) {
                        listPhrase.remove(0);
                        break;
                    } else {
                        throw new Exception("ERROR - EL TERMINO USADO NO ES UNA VARIABLE, OPERADOR O EN SU DEFECTO INCOMPATIBILIDAD DE DATOS. [" + listPhrase.get(0) + " ]");
                    }
                }
            } else {
                throw new Exception("ERROR - SIGNO ASIGNACION INCONGRUENTE. [" + listPhrase.get(0) + " ]");
            }
        } else {
            throw new Exception("ERROR - EN LA CONTRUCCION DE LA EXPRESION. [" + listPhrase.get(0) + " ]");
        }
    }

    public void clearAnalyze() {
        stackError.clear();
    }

    public List<String> getListInterpreterExecute() {
        return listInterpreterExecute;
    }

}
