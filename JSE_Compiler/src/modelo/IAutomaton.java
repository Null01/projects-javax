package modelo;

/**
 *
 * @author AGarcia
 */
public interface IAutomaton {

    public boolean isEntero(String string);

    public boolean isCadena(String string);

    public boolean isVariable(String string, boolean b);

    public boolean isDecimal(String string);

    public boolean isExpress(String string);

    public boolean isSymbol(String string);

    public boolean isTypeData(String string);

    public boolean isSymbolLogic(String string);

}
