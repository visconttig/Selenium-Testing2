package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

import java.util.regex.Pattern;

public class PagoEnLineaPage extends ClaseBase {

    // locators
    By inputNumeroDeFolio = By.id("numero_folio");
    By botonEnviarFolio = By.xpath("//*[@id=\"submitfolio\"]");
    // resultado folio erroneo
    By resultadoFolioErroneo = By.cssSelector("[role='alert']");
    private WebDriver myDriver;

    public PagoEnLineaPage(WebDriver driver) {
        super(driver);
        myDriver = driver;
    }

    // actions
    public void rellenarNumeroDeFolio(String numeroDeFolio) {
        //  esperarXsegundos(1000);
        setTexto(esperarPorPresenciaDelElemento(inputNumeroDeFolio), numeroDeFolio);
    }

    public String getResultadoFolioErroneo(Pattern pattern) {
       // esperarXsegundos(1000);
        esperaTextPattern(resultadoFolioErroneo, pattern);
        return obtenerTexto(esperarPorPresenciaDelElemento(resultadoFolioErroneo));
    }

    public void hacerClickEnviarFolio() {
        hacerClick(esperarPorclickearElemento(botonEnviarFolio));
    }
}
