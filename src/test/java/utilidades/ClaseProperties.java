package utilidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ClaseProperties {

    public static String obtenerProperty(String nombre) {

        //Instanciar un objeto de tipo properties
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("C:\\Users\\justb\\Desktop\\MegaSync\\Z_JAVA_PROJECTS\\TrabajoPOM_clavo\\src\\setup.properties");
            prop.load(input);

        } catch (Exception e) {
            System.out.println("Error al cargar archivo properties...");
            e.printStackTrace();
        }

        return prop.getProperty(nombre);

    }
}
