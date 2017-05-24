package analizadorlx.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se encarga de gestionar todo lo relacionado con archivos, es decir,
 * guardar, cargar, etc
 * 
 * @author Gustavo Salgado, Carlos Toro, Laura Rua
 * Universidad del Quindio
 * Programa de Ingenieria de Sistemas y Computacion
 * Asignatura: Teoría de Lenguajes Formales
 * @version 1.0
 * @since 2017
 */
public class Archivos {

    /**
     * Lee un archivo, transforma la información del archivo a una cadena de
     * texto
     *
     * @param abre archivo que se desea leer
     * @return cadena con los datos del archivo
     */
    public String leerArchivo(File abre) {
        FileReader archivos = null;
        String texto = "";
        try {
            String aux = "";
            archivos = new FileReader(abre);
            BufferedReader lee = new BufferedReader(archivos);
            while ((aux = lee.readLine()) != null) {
                texto += aux + "\n";
            }
            lee.close();
            return texto;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, "Archivo no encontrado", ex);
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, "Error Leyendo Archivo", ex);
        } finally {
            try {
                archivos.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return texto;
    }

    /**
     * Guarda el archivo, toma una cadena y la guarda en un archivo
     *
     * @param contenido cadena con la información que se va a guardar
     * @param file el archivo donde se va a guardar la información
     * @return true si se guardo el archivo de lo contrario false si no se
     * guardo el archivo
     */
    public boolean guardar(String contenido, File file) {

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = contenido.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
