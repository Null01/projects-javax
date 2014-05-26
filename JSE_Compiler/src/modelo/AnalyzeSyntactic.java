package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author AGarcia
 */
public class AnalyzeSyntactic extends Analyze implements IAnalyze {

    private Set<String> setWordsReserve;
    private Set<String> setWordsOperators;
    private ArrayList<String> listPhrase;

    public Set<String> getSetWordsReserve() {
        return setWordsReserve;
    }

    public Set<String> getSetWordsOperators() {
        return setWordsOperators;
    }

    public int getNumberlines() {
        return numberlines;
    }

    private AnalyzeSyntactic() {
    }

    public AnalyzeSyntactic(String file) {
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            this.initialize();
            bufferedWriter = new BufferedWriter(new FileWriter(file + "-out", false));
            setWordsReserve = new HashSet<>();
            setWordsOperators = new HashSet<>();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public Stack<String> getStackError() {
        return stackError;
    }

    private void printStackError(String cause) {
        String message = getStackError().pop();
        if (!cause.isEmpty()) {
            message = ELabel.MOTIVO.getMessage() + ELabel.SEPAR_A.getMessage()
                    + message + ELabel.SEPAR_B.getMessage();

            getStackError().push(ELabel.ERROR_LINEA.getMessage()
                    + ELabel.CAUSA.getMessage()
                    + ELabel.SEPAR_A.getMessage() + cause + ELabel.SEPAR_B.getMessage() + message);
        }
    }

    @Override
    public boolean analyzeText() {
        String line;
        Stack<String> stackymbols = new Stack<>();
        boolean textValid = true;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                ++this.numberlines;
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Stack<String> tokenizar = tokenizar(line);
                if (tokenizar != null) {
                    stackymbols.addAll(tokenizar);
                } else {
                    textValid = false;
                }
            }

        } catch (IOException ex) {
            System.out.println(ex);
        } finally {

            String motiveError = "";
            String causeError = "";

            ArrayList<String> l = new ArrayList<>(stackymbols);
            try {
                boolean step = (l.get(0).compareTo(EExpress.PRINCIPAL.getExpress()) == 0)
                        && l.get(1).compareTo(EOperators.PUNTO_PUNTO.getOperator()) == 0
                        && (!isExpress(l.get(2)));
                if (step) {
                    bufferedWriter.write(l.get(0) + " " + l.get(1) + " " + l.get(2) + "\n");
                    step = (l.get(3).compareTo(EExpress.DEFINICION.getExpress()) == 0);
                    if (step) {
                        bufferedWriter.write(l.get(3) + "\n");

                        int index = 3;
                        boolean type_data = false, state;
                        String key = "";
                        while (++index < l.size() && l.get(index).compareTo(EExpress.INICIO.getExpress()) != 0) {
                            if (type_data) {
                                state = isVariable(l.get(index), false) && !isExpress(l.get(index));
                                if (state) {
                                    if (map.get(l.get(index)) == null) {
                                        map.put(l.get(index), key);
                                    } else {
                                        motiveError = "ERROR VARIABLE YA EXISTENTE. ";
                                        causeError = l.get(index);
                                        throw new IllegalAccessException();
                                    }
                                    bufferedWriter.write(interpreter(l.get(index)));
                                    state = l.get(index + 1).compareTo(EOperators.IGUAL.getOperator()) == 0
                                            || l.get(index + 1).compareTo(EOperators.COMA.getOperator()) == 0
                                            || l.get(index + 1).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0;
                                    if (state) {
                                        ++index;
                                        bufferedWriter.write(interpreter(l.get(index)));
                                        if (l.get(index).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0) {
                                            type_data = false;
                                            continue;
                                        }
                                        if (l.get(index).compareTo(EOperators.IGUAL.getOperator()) == 0) {
                                            state = isCadena(l.get(index + 1))
                                                    || isDecimal(l.get(index + 1))
                                                    || isEntero(l.get(index + 1));
                                            if (state) {
                                                ++index;
                                                bufferedWriter.write(interpreter(l.get(index)));

                                                state = l.get(index + 1).compareTo(EOperators.COMA.getOperator()) == 0
                                                        || l.get(index + 1).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0;

                                                if (state) {
                                                    ++index;
                                                    bufferedWriter.write(interpreter(l.get(index)));
                                                    if (l.get(index).compareTo(EOperators.PUNTO_COMA.getOperator()) == 0) {
                                                        type_data = false;
                                                        continue;
                                                    }
                                                } else {
                                                    motiveError = "ERROR OPERADOR SEPARARACION DE DATO.";
                                                    causeError = l.get(index - 1) + " " + l.get(index);
                                                    throw new IllegalAccessException();
                                                }
                                            } else {
                                                motiveError = "ERROR ASIGNACION DE DATO.";
                                                causeError = l.get(index - 1) + " " + l.get(index);
                                                throw new IllegalAccessException();
                                            }
                                        }
                                    } else {
                                        motiveError = "ERROR OPERADOR SEPARARACION DE DATO.";
                                        causeError = l.get(index - 1) + " " + l.get(index) + " " + l.get(index + 1);
                                        throw new IllegalAccessException();
                                    }
                                } else {
                                    motiveError = "ERROR TIPO DE DATO - MAL USO DE UNA PALABRA RESERVADA.";
                                    causeError = l.get(index) + " " + l.get(index + 1);
                                    throw new IllegalAccessException();
                                }

                            } else {
                                type_data = isTypeData(l.get(index))
                                        && l.get(index + 1).compareTo(EOperators.PUNTO_PUNTO.getOperator()) == 0;
                                if (!type_data) {
                                    motiveError = "ERROR EN LA DEFINICION.";
                                    index = (index == l.size() - 1) ? l.size() - 2 : index;
                                    causeError = l.get(index - 1) + " " + l.get(index) + " " + l.get(index + 1);
                                    throw new IllegalAccessException();
                                }

                                key = l.get(index);
                                bufferedWriter.write(interpreter(l.get(index) + l.get(index + 1)));
                                ++index;
                            }
                            motiveError = causeError = "";
                        }

                        state = l.get(index).compareTo(EExpress.INICIO.getExpress()) == 0;
                        if (state) {
                            bufferedWriter.write(interpreter(l.get(index)));
                            while (++index < l.size() && l.get(index).compareTo(EExpress.FIN.getExpress()) != 0) {
                                state = isExpress(l.get(index)) || isSymbol(l.get(index))
                                        || map.containsKey(l.get(index)) || isCadena(l.get(index))
                                        || isEntero(l.get(index)) || isDecimal(l.get(index));
                                if (state) {
                                    bufferedWriter.write(interpreter(l.get(index)));
                                } else {
                                    motiveError = "PALARA NO RECONOCIBLE.";
                                    causeError = l.get(index - 1) + " " + l.get(index);
                                    throw new IllegalAccessException();
                                }
                            }
                            bufferedWriter.write(interpreter(l.get(index)));
                            if (l.size() > (index + 1)) {
                                motiveError = "ERROR CODIGO FUERA DE AREA DE TRABAJO";
                                causeError = l.get(index - 1) + " " + l.get(index);
                                throw new IllegalAccessException();
                            }
                        } else {
                            motiveError = "ERROR AL DEFINIR UNA PALABRA RESERVADA FUERA DEL CONTEXTO.";
                            causeError = l.get(index - 1) + l.get(index) + l.get(index + 1);
                            throw new IllegalAccessException();
                        }
                    } else {
                        motiveError = EError.DEF_PARAM_DEFINICION.getMessage();
                        throw new IllegalAccessException();
                    }
                } else {
                    motiveError = EError.DEF_PARAM_PRINCIPAL.getMessage();
                    causeError = l.get(0) + " " + l.get(1) + " " + l.get(2);
                    throw new IllegalAccessException();
                }
            } catch (IOException | IllegalAccessException | IndexOutOfBoundsException ex) {
                if (motiveError.isEmpty()) {
                    motiveError = EError.DEF_N_PARAM_IN.getMessage();
                }
                getStackError().push(motiveError);
                if (causeError.isEmpty()) {
                    causeError = l.toString();
                }
                printStackError(causeError);
                textValid = false;
                System.out.println(ex);
            }

            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }

            if (textValid) {
                this.listPhrase = new ArrayList<>(l);
            }

            return textValid;
        }
    }

    @Override
    public Stack<String> tokenizar(String line) {
        line = line + ";".trim(); // PARAMETRO BANDERA, DETERIMINA FIN DE LECTURA
        String key, value;
        int index = 0, flag, i = -1;
        Stack<String> stack = new Stack<>();
        while (!line.isEmpty()) {
            char array[] = line.toCharArray();
            key = ("" + array[++i]) + "" + array[(i + 1) % line.length()];
            key = (isSymbol(key)) ? key : ("" + array[i]);
            if (isSymbol(key)) {
                setWordsOperators.add(key);
                flag = isCaseSpecial(i, array);
                if (flag != -1) {
                    index = flag;
                    i += (flag - 1);
                    key = "";
                }

                value = line.substring(0, index).trim();
                if (!value.isEmpty()) {
                    boolean validate = validate(stack, key, value);
                    if (!validate) {
                        printStackError(value);
                        return null;
                    }
                }
                if (flag == -1) {
                    stack.push(key);
                }

                line = line.substring(index + key.length(), line.length()).trim();
                index = 0;
                i = -1;
            } else {
                ++index;
            }
        }
        stack.pop();
        return stack;
    }

    @Override
    public boolean validate(Stack<String> stack, String key, String value) {
        // VERIFICAR POSIBLES EXPRESIONES RELACIONADAS.
        // EJEMPLO (-    3) = -3  // (-   0.22) = -0.22
        if (!stack.isEmpty()) {
            String temp = stack.pop();
            // POSIBLES JOINS ENTRE EXPRESIONES Y SIMBOLOS
            if (isEntero(temp + value)) {
                stack.push(temp + value); //ENTERO CON SIGNO
            } else {
                if (isEntero(value)) {
                    stack.push(temp);
                    stack.push(value); //ENTERO SIN SIGNO
                } else {
                    if (isDecimal(temp + value)) {
                        stack.push(temp + value);   //DECIMAL CON SIGNO
                    } else {
                        if (isDecimal(value)) {
                            stack.push(temp);
                            stack.push(value); //ENTERO SIN SIGNO
                        } else {
                            if (isCadena(value)) {
                                // System.out.println(temp + " isCadena " + value);
                                if (temp.compareTo(value) != 0) {
                                    stack.push(temp);
                                }
                                stack.push(value);
                            } else {
                                if (isExpress(value)) {
                                    setWordsReserve.add(value);
                                    // System.out.println(temp + " isExpress " + value);
                                    if (temp.compareTo(value) != 0) {
                                        stack.push(temp);
                                    }
                                    stack.push(value);
                                } else {
                                    if (isVariable(value, false)) {
                                        //System.out.println(value + " isVariable ");
                                        if (temp.compareTo(value) != 0) {
                                            stack.push(temp);
                                        }
                                        stack.push(value);
                                    } else {
                                        getStackError().push(EError.DEF_VAR_ASIG_STRING.getMessage());
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            stack.push(value);
            return validate(stack, "", value);
        }
        return true;
    }

    private int isCaseSpecial(int index, char[] array) {
        if ((array[index] + "").compareTo(EOperators.COMILLA_DOBLE.getOperator()) == 0) {
            char[] copyOfRange = Arrays.copyOfRange(array, index + 1, array.length);
            int indexOf = new String(copyOfRange).indexOf(array[index] + "");
            return (indexOf != -1) ? indexOf + 2 : -1;
        }
        return -1;
    }

    private String interpreter(String string) {
        String target = string;
        boolean firstEnter = string.compareTo(EExpress.DEFINICION.getExpress()) == 0
                || string.compareTo(EExpress.INICIO.getExpress()) == 0
                || string.compareTo(EExpress.FIN.getExpress()) == 0;
        if (firstEnter) {
            return "\n" + target + "\n";
        }
        boolean endLine = string.compareTo(EExpress.ENTONCES.getExpress()) == 0
                || string.compareTo(EExpress.FIN_SI.getExpress()) == 0;
        if (endLine) {
            return " " + target + "\n";
        }
        boolean onlyEnter = string.compareTo(EOperators.PUNTO_COMA.getOperator()) == 0;
        if (onlyEnter) {
            return target + "\n";
        }
        return " " + target;
    }

    public void clearAnalyze() {
        stackError.clear();
    }

    public Reader ReformatFile(File file) throws FileNotFoundException {
        File f = new File(file.getName() + "-out");
        return new FileReader(f);
    }

    public ArrayList<String> getListPhrase() {
        return listPhrase;
    }

}
