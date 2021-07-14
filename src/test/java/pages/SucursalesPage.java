package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

import java.util.regex.Pattern;

public class SucursalesPage extends ClaseBase {
    // locators
    By inputSearhBoxMapa = By.id("branch-search-input");
    By botonBusquedaMapa = By.xpath("//*[@id=\"branch-search-text\"]");
    // resultado busqueda inexistente
    By textoResultadoBusquedaInexistente = By.xpath("//*[@id=\"search-branch-results\"]/p");
    private WebDriver myDriver;

    public SucursalesPage(WebDriver driver) {
        super(driver);
        myDriver = driver;
    }

    // actions
    public void rellenaTextoBusquedaMapa(String textoAbuscar) {
        setTexto(esperarPorPresenciaDelElemento(inputSearhBoxMapa), textoAbuscar);
    }

    public void clickBuscarEnMapa() {
        esperarXsegundos(200);
        hacerClick(esperarPorclickearElemento(botonBusquedaMapa));
    }

    public String getResultadoBusquedaMapa(Pattern pattern) {
      //  esperarXsegundos(3000);
        esperaTextPattern(textoResultadoBusquedaInexistente, pattern);
        return obtenerTexto(esperarPorPresenciaDelElemento(textoResultadoBusquedaInexistente));
    }

}
