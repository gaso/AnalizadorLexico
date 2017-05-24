package analizadorlx.logica;

import java.util.ArrayList;

/**
 * Clase que modela un analizador lexico
 *
 * @author Gustavo Salgado, Carlos Toro, Laura Rua Universidad del Quindio
 * Programa de Ingenieria de Sistemas y Computacion Asignatura: Teoría de
 * Lenguajes Formales
 * @version 1.0
 * @since 2017
 */
public class AnalizadorLexico {

    /**
     * ArrayList de cadenas
     */
    ArrayList<String> reservada = new ArrayList<>();

    /**
     * Metodo constructor de la clase analizadorLexico
     */
    public AnalizadorLexico() {
        super();
        cargarPalabrasReservadas();
    }

    /**
     * Extrae los tokens de un codigo fuente dado
     *
     * @param cod codigo al cual se le van a extraer los tokens
     * @return ArrayList con los tokens
     */
    public ArrayList extraerTokens(String cod) {
        Token token;
        Token token1;
        ArrayList vectorTokens = new ArrayList();

        // El primer token se extrae a partir de la posicion cero
        int i = 0;

        // Ciclo para extraer todos los tokens
        String[] arregloString = cod.split("\n");
        for (int x = 0; x < arregloString.length; x++) {
            // Extrae el token de la posición i
            while (i < arregloString[x].length()) {
                token = extraerSiguienteToken(arregloString[x], i);
                token1 = new Token(token.getLexema(), token.getTipo(), 0, x + 1);
                vectorTokens.add(token1);
                i = token.getIndiceSiguiente();
            }
            i = 0;
        }
        return vectorTokens;
    }

    /**
     * Extrae el token de la cadena cod a partir de la posicion i
     *
     * @param cod codigo al cual se le va a extraer un token
     * @param i posicion a partir de la cual se va a extraer el token
     * @return token que se extrajo de la cadena
     */
    private Token extraerSiguienteToken(String cod, int i) {

        Token token;

        // Intentar extraer palabra reservada
        token = extraerPalabraReservada(cod, i);
        if (token != null) {
            return token;
        }

        // Intenta extraer un identificador
        token = extraerIdentificador(cod, i);
        if (token != null) {
            return token;
        }

        // Intenta extraer un entero
        token = extraerEntero(cod, i);
        if (token != null) {
            return token;
        }

        // Intenta extraer una cadena
        token = extraerCadena(cod, i);
        if (token != null) {
            return token;
        }

        // Intenta extraer los simbolos del lenguaje {} 
        token = extraerSimboloLlaves(cod, i);
        if (token != null) {
            return token;
        }

        // Intenta extraer los simbolos del lenguaje ->
        token = extraerSimboloFlecha(cod, i);
        if (token != null) {
            return token;
        }

        // Intenta extraer un comentario
        token = extraerComentario(cod, i);
        if (token != null) {
            return token;
        }

        // Intenta extraer un separador
        token = extraerSeparador(cod, i);
        if (token != null) {
            return token;
        }

        // Intenta extraer una terminacion de línea
        token = extraerTerminacionLinea(cod, i);
        if (token != null) {
            return token;
        }

        // Extrae un espacio en blanco
        token = extraerEspacioBlanco(cod, i);
        if (token != null) {
            return token;
        }

        // Extrae un token no reconocido
        token = extraerNoReconocido(cod, i);
        return token;
    }

    /**
     * Intenta extraer un entero de la cadena cod a partir de la posicion i
     *
     * @param cod codigo al cual se le va a intentar extraer un entero
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerEntero(String cod, int i) {

        if (Character.isDigit(cod.charAt(i))) {
            int j = i + 1;
            while (j < cod.length() && cod.charAt(j) != ',' && cod.charAt(j) != ' ' && cod.charAt(j) != '.' && cod.charAt(j) != '}') {
                j++;
            }
            String lexema = cod.substring(i, j);
            char c[] = cod.substring(i, j).toCharArray();

            for (int k = 0; k < c.length; k++) {
                if (!Character.isDigit(c[k])) {
                    Token t = new Token(lexema, Token.NO_RECONOCIDO, j, i);
                    return t;
                }
            }

            Token t = new Token(lexema, Token.ENTERO, j, i);
            return t;

        }
        return null;
    }

    /**
     * Intenta extraer un identificador de la cadena cod a partir de la posicion
     * i
     *
     * @param cod codigo al cual se le va a intentar extraer un identificador
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerIdentificador(String cod, int i) {
        if (Character.isLetter(cod.charAt(i))) {
            int j = i + 1;
            while (j < cod.length() && Character.isLetterOrDigit(cod.charAt(j))) {
                j++;
            }
            String lex = cod.substring(i, j);
            Token token = new Token(lex, Token.IDENTIFICADOR, j, i);
            return token;
        }
        return null;
    }

    /**
     * Intenta extraer un espacio en blanco de la cadena cod a partir de la
     * posicion i
     *
     * @param cod codigo al cual se le va a intentar extraer un espacio en
     * blanco
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerEspacioBlanco(String cod, int i) {
        if (Character.isWhitespace(cod.charAt(i))) {
            int j = i + 1;
            String lex = cod.substring(i, j);
            Token token = new Token(lex, Token.ESP_BLANCO, j, i);
            return token;
        }
        return null;
    }

    /**
     * Intenta extraer un lexema no reconocido de la cadena cod a partir de la
     * posicion i.
     *
     * @param cod codigo al cual se le va a extraer el token no reconocido
     * @param i posicion a partir de la cual se va a extraer el token no
     * reconocido
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerNoReconocido(String cod, int i) {
        int j = i + 1;
        while (j < cod.length()) {
            if (cod.charAt(j) == ' ' || cod.charAt(j) == ',') {
                break;
            }
            j++;
           // System.out.println("j= " + j);
        }
        String lexema = cod.substring(i, j);

        Token token = new Token(lexema, Token.NO_RECONOCIDO, j, i);
        return token;
    }

    /**
     * Intenta extraer una palabra reservada de la cadena cod a partir de la
     * posicion i
     *
     * @param cod codigo al cual se le va a intentar extraer una palabra
     * reservada
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerPalabraReservada(String cod, int i) {
        if (esLetra(cod.charAt(i))) {

            int j = i + 1;

            while (j < cod.length() && cod.charAt(j) != ',' && cod.charAt(j) != ' ') {
                j++;
            }

            String lexema = cod.substring(i, j);

            if (esPReservada(lexema)) {
                Token token = new Token(lexema, Token.PALABRA_RESERVADA, j, i);
                return token;
            } else {
                return null;

            }

        }
        return null;
    }

    /**
     * Intenta extraer el simbolo llaves de la cadena cod a partir de la
     * posicion i, que equivale a { y }
     *
     * @param cod codigo al cual se le va a intentar extraer el simbolo de
     * llaves
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerSimboloLlaves(String cod, int i) {
        if (cod.charAt(i) == '{' || cod.charAt(i) == '}') {
            int j = i + 1;
            String lex = cod.substring(i, j);
            Token token = new Token(lex, Token.SIMBOLO, j, i);
            return token;
        }
        return null;
    }

    /**
     * Intenta extraer el simbolo flecha de la cadena cod a partir de la
     * posicion i, que equivale a ->
     *
     * @param cod codigo al cual se le va a intentar extraer el simbolo de
     * flecha
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerSimboloFlecha(String cod, int i) {
        if (cod.charAt(i) == '-') {
            if (i + 1 < cod.length() && cod.charAt(i + 1) == '>') {
                int j = i + 2;

                String lex = cod.substring(i, j);

                Token token = new Token(lex, Token.SIMBOLO, j, i);
                return token;
            }
        }
        return null;
    }

    /**
     * Intenta extraer el comentario de la cadena cod a partir de la posicion i
     *
     * @param cod codigo al cual se le va a intentar extraer un comentario
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerComentario(String cod, int i) {
        if (cod.charAt(i) == '-') {

            if (i + 1 < cod.length() && cod.charAt(i + 1) == '-') {
                int j = i + 2;

                while (j < cod.length()) {

                    if (cod.charAt(j) == '-' && cod.charAt(j + 1) == '-') {
                        String lex = cod.substring(i, j + 2);
                        Token token = new Token(lex, Token.COMENTARIO, j + 2, i);
                        return token;
                    }
                    j++;
                }
                String lex = cod.substring(i, j);

                Token token = new Token(lex, Token.NO_RECONOCIDO, j, i);
                return token;
            }
        }
        return null;
    }

    /**
     * Intenta extraer la cadena de la cadena cod a partir de la posicion i
     *
     * @param cod codigo al cual se le va a intentar extraer una cadena
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerCadena(String cod, int i) {
        if (cod.charAt(i) == '"') {
            int j = i + 1;

            while (j < cod.length()) {

                if (cod.charAt(j) == '"') {
                    String lex = cod.substring(i, j + 1);
                    Token token = new Token(lex, Token.CADENA, j + 1, i);
                    return token;
                }
                j++;
            }
            String lex = cod.substring(i, j);

            Token token = new Token(lex, Token.NO_RECONOCIDO, j, i);
            return token;
        }
        return null;
    }

    /**
     * Intenta extraer el separador de la cadena cod a partir de la posicion i
     *
     * @param cod codigo al cual se le va a intentar extraer un separador
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerSeparador(String cod, int i) {
        if (cod.charAt(i) == ',') {
            int j = i + 1;
            String lex = cod.substring(i, j);
            Token token = new Token(lex, Token.SEPARADOR, j, i);
            return token;
        }
        return null;
    }

    /**
     * Intenta extraer la terminacion de linea (punto) de la cadena cod a partir
     * de la posicion i
     *
     * @param cod codigo al cual se le va a intentar extraer unaa terminacion de
     * linea
     * @param i posicion a partir de la cual se va a intentar extraer un entero
     * @return el token indicado o null si no lo encuentra
     */
    private Token extraerTerminacionLinea(String cod, int i) {
        if (cod.charAt(i) == '.') {
            int j = i + 1;
            String lex = cod.substring(i, j);
            Token token = new Token(lex, Token.TERMINACION_LINEA, j, i);
            return token;
        }
        return null;
    }

    /**
     * Determina si un carácter es una letra
     *
     * @param caracter caracter que se va a analizar
     * @return true si el caracter es una letra de lo contrario false si no es
     * una letra
     */
    private boolean esLetra(char caracter) {
        return (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
    }

    /**
     * Determina si un lexema es una palabra reservada
     *
     * @param lexema cadena que se va a analizar
     * @return true si es una palabra reservada de lo contrario false si no es
     * una palabra reservada
     */
    private boolean esPReservada(String lexema) {
        for (String r : reservada) {
            if (r.equalsIgnoreCase(lexema)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Carga las palabras reservadas al ArrayList reservada
     */
    private void cargarPalabrasReservadas() {
        reservada.add("traer");
        reservada.add("de");
        reservada.add("el");
        reservada.add("o");
        reservada.add("sea");
        reservada.add("igual");
        reservada.add("a");
        reservada.add("donde");
        reservada.add("como");
        reservada.add("agrupado");
        reservada.add("por");
        reservada.add("este");
        reservada.add("entre");
        reservada.add("no");
        reservada.add("ordenado");
        reservada.add("ascendente");
        reservada.add("descendente");
        reservada.add("en");
        reservada.add("ingresar");
        reservada.add("eliminar");
        reservada.add("todos");
        reservada.add("los");
        reservada.add("datos");
        reservada.add("actualizar");
        reservada.add("y");
    }
}
