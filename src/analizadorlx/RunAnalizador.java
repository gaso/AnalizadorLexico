package analizadorlx;

import analizadorlx.logica.GeneradorJFlex;
import analizadorlx.ui.Ventana;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Ejecuta el analizador léxico
 *
 * @author Gustavo Salgado, Carlos Toro, Laura Rua Universidad del Quindio
 * Programa de Ingenieria de Sistemas y Computacion Asignatura: Teoría de
 * Lenguajes Formales
 * @version 1.0
 * @since 2017
 */
public class RunAnalizador {

    private static final String PATH_FLEX = "src/analizadorlx/logica/lexico.flex";

    private static boolean generarFlex = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    if (generarFlex) {
                        if (GeneradorJFlex.generarLexer(PATH_FLEX)) {
                            new Ventana().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se logro construir el Lenguaje para el Analizador , revise la configuracion de su archivo Flex", "Error Generando Lexico con JFlex", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                    }else{
                        new Ventana().setVisible(true);
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RunAnalizador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(RunAnalizador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RunAnalizador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(RunAnalizador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
