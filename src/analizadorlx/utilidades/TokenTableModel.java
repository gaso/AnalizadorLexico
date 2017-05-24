package analizadorlx.utilidades;

import analizadorlx.logica.Token;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Clase encargada de tabular los tokens, lexemas y línea en la que sale el
 * token
 *
 * @author Gustavo Salgado, Carlos Toro, Laura Rua Universidad del Quindio
 * Programa de Ingenieria de Sistemas y Computacion Asignatura: Teoría de
 * Lenguajes Formales
 * @version 1.0
 * @since 2017
 */
public class TokenTableModel extends DefaultTableModel {

    /**
     * Lista de tokens
     */
    private List<Token> tokens;
    /**
     * Arreglo de cadenas que guarda los tokens, el lexema y la línea
     */
    private String[] columnas = {"Token", "Lexema", "Línea"};

    /**
     * Metodo constructor de la clase TokenTableModel
     *
     * @param tokens lista de tokens
     */
    public TokenTableModel(List<Token> tokens) {
        super();
        this.tokens = tokens;
        setColumnIdentifiers(columnas);
    }

    /**
     * Metodo sobreescrito del numero de filas
     */
    @Override
    public int getRowCount() {
        if (tokens == null) {
            return 0;
        }
        return tokens.size();
    }

    /**
     * Metodo sobreescrito ValueAt, que define en que columna y fila va el
     * token, el lexema y el valor línea
     *
     * @param row valor de la fila
     * @param col valor de la columna
     * @return tipo de token
     */
    @Override
    public Object getValueAt(int row, int col) {
        Token token = tokens.get(row);
        if (col == 0) {
            return token.getLexema();
        } else if (col == 1) {
            return token.getTipo();
        }
        return token.getIndiceAnterior();
    }

    /**
     * Metodo get del listado de tokens
     *
     * @return listado de tokens
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Metodo set del listado de tokens
     *
     * @param tokens listado de tokens
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
        fireTableDataChanged();
    }

}
