package modelo;

import java.util.Stack;

/**
 *
 * @author AGarcia
 */
public interface IAnalyze {

    public Stack<String> stackError = new Stack<>();

    public boolean analyzeText();

    public Stack<String> tokenizar(String line);

    public boolean validate(Stack<String> stack, String key, String value);
}
