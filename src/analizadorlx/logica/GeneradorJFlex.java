/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlx.logica;

import analizadorlx.ui.Ventana;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gusta
 */
public class GeneradorJFlex {

    /**
     * Encargado de Construir el Interprete Lexico usando JFlex como apoyo
     *
     * @param path
     * @return true si se logro generar el Lexico | false si existe algun error
     */
    public static boolean generarLexer(String path) {
        try {
            File file = new File(path);
            jflex.Main.generate(file);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontro el Archio Lexer", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public static ArrayList<Token> analizarJFlex(String rutaArchivo) {

        Reader reader = null;
        ArrayList<Token> tokens = null;
        try {
            System.out.println("Con JFlex");

            reader = new BufferedReader(new FileReader(rutaArchivo));
            tokens = new ArrayList<>();

            LexicoJFlexGenerado lexemas = new LexicoJFlexGenerado(reader);

            while (true) {
                try {
                    Token token = lexemas.yylex();

                    if (token == null) {
                        return tokens;
                    }
                    
                    token.setLinea(token.getLinea()+1);
                    
                    tokens.add(token);
                    
                } catch (IOException ex) {
                    Logger.getLogger(GeneradorJFlex.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

}
