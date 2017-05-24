package analizadorlx.logica;

/**
 * Clase que modela un token
 *
 * @author Gustavo Salgado, Carlos Toro, Laura Rua Universidad del Quindio
 * Programa de Ingenieria de Sistemas y Computacion Asignatura: Teoría de
 * Lenguajes Formales
 * @version 1.0
 * @since 2017
 */
public class Token {

    /**
     * Constantes para modelar los posibles tipos de token que se van a analizar
     */
    final public static String ENTERO = "Entero";
    final public static String IDENTIFICADOR = "Identificador";
    final public static String NO_RECONOCIDO = "No reconocido";
    final public static String COMENTARIO = "Comentario";
    final public static String PALABRA_RESERVADA = "Palabra Reservada";
    final public static String SIMBOLO = "Simbolo";
    final public static String SEPARADOR = "Separador";
    final public static String CADENA = "Cadena";
    final public static String TERMINACION_LINEA = "Terminación de línea";
    final public static String ESP_BLANCO = "Espacio";

    /**
     * Cadena que representa un Lexema
     */
    private String lexema;

    /**
     * Cadena que representa el tipo de token
     */
    private String tipo;

    /**
     * Entero que representa en que linea esta el token
     */
    private int linea;

    /**
     * Entero que representa el indice del siguiente lexema
     */
    private int indiceSiguiente;

    /**
     * Entero que representa el indice del anterior lexema
     */
    private int indiceAnterior;

    /**
     * Metodo constructor de la clase Token
     *
     * @param lexema el lexema
     * @param tipo el tipo de token
     * @param indiceSiguiente el indice siguiente del lexema
     * @param indiceAnterior el indice anterior del lexema
     */
    public Token(String lexema, String tipo, int indiceSiguiente, int indiceAnterior) {
        super();
        this.lexema = lexema;
        this.tipo = tipo;
        this.indiceSiguiente = indiceSiguiente;
        this.indiceAnterior = indiceAnterior;
    }

    /**
     * Entrega la informacion del token
     *
     * @return cadena con la informacion del token
     */
    public String darDescripcion() {
        return "Token: " + lexema + "     Tipo: " + tipo + "  Inicio: " + indiceAnterior + "  Fin: " + indiceSiguiente;
    }

    /**
     * Método que retorna el lexema del token
     *
     * @return el lexema del token
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * Método get del indice siguiente del lexema
     *
     * @return indice siguiente del lexema
     */
    public int getIndiceSiguiente() {
        return indiceSiguiente;
    }

    /**
     * Método get del indice anterior del lexema
     *
     * @return indice anterior del lexema
     */
    public int getIndiceAnterior() {
        return indiceAnterior;
    }

    /**
     * Método get del token
     *
     * @return el tipo del token
     */
    public String getTipo() {
        return tipo;
    }

}
