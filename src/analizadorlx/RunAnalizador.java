package analizadorlx;

import analizadorlx.ui.Ventana;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new Ventana().setVisible(true);
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
