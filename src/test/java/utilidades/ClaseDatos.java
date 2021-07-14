package utilidades;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ClaseDatos {

    //public static void main(String[] args) throws IOException {
    public static ArrayList<String> getData(String nombreTests) {
        ArrayList<String> arrayDatos = new ArrayList<String>();

        //Crear un objeto de tipo File en base a la ruta del archivo
        FileInputStream file = null;
        try {
            file = new FileInputStream(ClaseProperties.obtenerProperty("rutaExcel"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //Crear un objeto de tipo Libro Excel en base al file
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Contamos la cantidad de hojas del libro
        int nroHojasExcel = excel.getNumberOfSheets();

        //iteramos en las hojas del libro
        for (int i = 0; i < nroHojasExcel; i++) {
            //evaluamos si el titulo de la hoja coincide con la hoja a utilizar"
            if (excel.getSheetName(i).equalsIgnoreCase(ClaseProperties.obtenerProperty("hojaExcel"))) {
                //encontre la hoja

                //Instanciamos un objeto de tipo hoja excel
                XSSFSheet hojaExcel = excel.getSheetAt(i);

                //Instancias un iterador con las filas de la hoja
                Iterator<Row> filas = hojaExcel.rowIterator();

                //Creamos un objeto de tipo fila en base al iterador de filas utilizando la 1ra fila
                Row primeraFila = filas.next();

                //creamos un iterador de celdas de la primera fila
                Iterator<Cell> celda = primeraFila.cellIterator();

                int k = 0;
                int columna = 0;

                //evaluamos si existe un valor por iterar
                while (celda.hasNext()) {

                    //Creo un objeto de tipo celda con la celda a iterar
                    Cell celdaSeleccionada = celda.next();

                    //verificamos si la celda contiene el titulo del campo "Nombre CP"
                    if (celdaSeleccionada.getStringCellValue().equalsIgnoreCase(ClaseProperties.obtenerProperty("tituloCPs"))) {

                        //Encontre la celda que contiene el titulo de los casos
                        columna = k;
                    }
                    k++;
                }
                //System.out.println("La celda con el titulo se encuentra en la posición: "+columna);

                while (filas.hasNext()) {
                    Row r = filas.next();

                    if (r.getCell(columna).getStringCellValue().equalsIgnoreCase(nombreTests)) {
                        //encontre la fila con el nombre del caso de prueba

                        Iterator<Cell> cv = r.cellIterator();

                        while (cv.hasNext()) {
                            Cell c = cv.next();

                            if (c.getCellType() == CellType.STRING) {
                                //System.out.println(c.getStringCellValue());
                                arrayDatos.add(c.getStringCellValue());
                            } else if (c.getCellType() == CellType.NUMERIC) {
                                //System.out.println(NumberToTextConverter.toText(c.getNumericCellValue()));
                                arrayDatos.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return arrayDatos;
    }

}
