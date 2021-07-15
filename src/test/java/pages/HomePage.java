package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

import java.util.regex.Pattern;

public class HomePage extends ClaseBase {
    //aca centralizaremos los locators
    By botonCambiate = By.cssSelector("a.invitacion");
    By inputNombre = By.id("name");
    By inputRut = By.id("rut");
    By inputEmail = By.id("email");
    By inputFono = By.id("fono");
    By inputOtroTexto = By.id("glosa");
    //Select dropRegiones
    By dropRegiones = By.id("regiones");
    //Select dropComunas
    By dropComunas = By.id("comuna");
    //boton enviar
    By botonEnviar = By.id("enviar");
    // resultado
    By textoResultado = By.xpath("//*[@id=\"datas_modal\"]/div/div/div/div/div[2]/h5/p");
    // boton cerrar dialogo
    By botonCierraDialogo = By.xpath("//*[@id=\"datas_modal\"]/div/div/div/div/div[1]/img");
    // resultado reapertura formulario
    By textoPreservado = By.xpath("//*[@id=\"name\"]");
    // resultado formulario vacio
    By textResultadoFormVacio = By.xpath("//*[@id=\"rut-error\"]");
    // resultado email error
    By textResultadoEmailError = By.id("email-error");
    // resultado numero erroneo
    By textResultadoFonoError = By.id("fono-error");
    // boton pago en linea
    By botonPagoEnLinea = By.xpath("//*[@id=\"header\"]/div[1]/div/div/div/ul[1]/li[2]/a/span");
    // boton sucursales
    By botonSucursales = By.xpath("//*[@id=\"menu-item-284\"]/a");
    private WebDriver myDriver;

    public HomePage(WebDriver driver) {
        super(driver);
        myDriver = driver;
    }

    // acciones del site
    public void clickBotonCambiate() {
        hacerClick(esperarPorclickearElemento(botonCambiate));
        esperarXsegundos(1000);
    }

    public void rellenarFormulario(String nombre, String rut, String email, String fono, String region, String comuna, String glosa) {
        setTexto(esperarPorPresenciaDelElemento(inputNombre), nombre);
        setTexto(esperarPorPresenciaDelElemento(inputRut), rut);
        setTexto(esperarPorPresenciaDelElemento(inputEmail), email);
        setTexto(esperarPorPresenciaDelElemento(inputFono), fono);
        setTexto(esperarPorPresenciaDelElemento(inputOtroTexto), glosa);
        selectByVisibleText(getSelector(dropRegiones), region);
        selectByVisibleText(getSelector(dropComunas), comuna);
    }

    public void clickEnviarFormulario() {
        hacerClick(esperarPorclickearElemento(botonEnviar));
    }

    public String obtenerMensajeEnvioExitoso(Pattern pattern) {
        //esperarXsegundos(1000);
        esperaTextPattern(textoResultado, pattern);
        return obtenerTexto(esperarPorPresenciaDelElemento(textoResultado));
    }

    public void clickCerrarDialogo() {
        hacerClick(esperarPorclickearElemento(botonCierraDialogo));
    }

    public String getTextoPreservado() {
        esperarXsegundos(200);
        return buscarElementoWeb(textoPreservado).getAttribute("value");

    }

    public String obtenerResultadoFormularioVacio() {
        esperarXsegundos(200);
        return obtenerTexto(textResultadoFormVacio);
    }

    public String obtenerResultadoEmailError() {
        esperarXsegundos(200);
        return obtenerTexto(textResultadoEmailError);
    }

    public String obtenerResultadoFonoError() {
        esperarXsegundos(200);
        return obtenerTexto(textResultadoFonoError);
    }

    public void clickPagoEnLinea() {
        hacerClick(esperarPorclickearElemento(botonPagoEnLinea));
    }

    public void clickSucursales() {
        hacerClick(esperarPorclickearElemento(botonSucursales));
        esperarXsegundos(200);
    }

}
