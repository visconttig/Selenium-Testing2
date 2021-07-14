package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.PagoEnLineaPage;
import pages.SucursalesPage;
import utilidades.ClaseDatos;
import utilidades.ClaseProperties;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CasosDePrueba {
    private final String url = "url";
    //definir atributos
    private WebDriver driver;
    private ArrayList<String> datos;

    private HomePage homePage;
    private PagoEnLineaPage pagoEnLineaPage;
    private SucursalesPage sucursalesPage;


    @BeforeSuite
    public void prepracionSuite() {
        System.out.println("Iniciando suite de pruebas");
    }

    @BeforeClass
    public void preparacionClase() {
        homePage = new HomePage(driver);
        homePage.conectarDriver(ClaseProperties.obtenerProperty("browser"),
                ClaseProperties.obtenerProperty("propertyDriver"),
                ClaseProperties.obtenerProperty("rutaDriver"));
        pagoEnLineaPage = new PagoEnLineaPage(homePage.getDriver());
        sucursalesPage = new SucursalesPage(homePage.getDriver());

    }

    @BeforeMethod
    public void preparacionTest() {
        homePage.abrirSitio(ClaseProperties.obtenerProperty(url));
        homePage.maximizarVentana();

    }


    @Test
    public void testInputDatosCorrectos() {
        datos = ClaseDatos.getData("testInputDatosCorrectos");
        String nombre = datos.get(1);
        String rut = datos.get(2);
        String email = datos.get(3);
        String fono = datos.get(4);
        String region = datos.get(5);
        String comuna = datos.get(6);
        String glosa = datos.get(7);
        String resultadoEsperado = datos.get(8);

        homePage.clickBotonCambiate();

        homePage.rellenarFormulario(nombre, rut, email, fono, region, comuna, glosa);

        homePage.clickEnviarFormulario();

        String resultado = homePage.obtenerMensajeEnvioExitoso();
        Assert.assertEquals(resultado, resultadoEsperado);

    }

    @Test
    public void testPreservacionDatosIngresados() {
        datos = ClaseDatos.getData("testPreservacionDatosIngresados");
        String nombre = datos.get(1);
        String rut = datos.get(2);
        String email = datos.get(3);
        String fono = datos.get(4);
        String region = datos.get(5);
        String comuna = datos.get(6);
        String glosa = datos.get(7);
        String resultadoEsperado = datos.get(8);

        homePage.clickBotonCambiate();

        homePage.rellenarFormulario(nombre, rut, email, fono, region, comuna, glosa);

        // cerramos el dialogo
        homePage.clickCerrarDialogo();

        // esperamons un momento
        homePage.esperarXsegundos(500);

        // reabrimos el dialogo
        homePage.clickBotonCambiate();

        String resultado = homePage.getTextoPreservado();

        Assert.assertEquals(resultado, resultadoEsperado);
    }


    @Test
    public void testEnvioFormularioVacio() {
        datos = ClaseDatos.getData("testEnvioFormularioVacio");
        String resultadoEsperado = datos.get(8);

        homePage.clickBotonCambiate();

        homePage.clickEnviarFormulario();

        String resultado = homePage.obtenerResultadoFormularioVacio();
        Assert.assertEquals(resultado, resultadoEsperado);
    }

    @Test
    public void testInputDatosEmailErroneo() {
        datos = ClaseDatos.getData("testInputDatosEmailErroneo");
        String nombre = datos.get(1);
        String rut = datos.get(2);
        String email = datos.get(3);
        String fono = datos.get(4);
        String region = datos.get(5);
        String comuna = datos.get(6);
        String glosa = datos.get(7);
        String resultadoEsperado = datos.get(8);

        homePage.clickBotonCambiate();

        homePage.rellenarFormulario(nombre, rut, email, fono, region, comuna, glosa);

        homePage.clickEnviarFormulario();

        String resultado = homePage.obtenerResultadoEmailError();
        Assert.assertEquals(resultado, resultadoEsperado);
    }

    @Test
    public void testInputDatosNumeroErroneo() {
        datos = ClaseDatos.getData("testInputDatosNumeroErroneo");
        String nombre = datos.get(1);
        String rut = datos.get(2);
        String email = datos.get(3);
        String fono = datos.get(4);
        String region = datos.get(5);
        String comuna = datos.get(6);
        String glosa = datos.get(7);
        String resultadoEsperado = datos.get(8);

        homePage.clickBotonCambiate();

        homePage.rellenarFormulario(nombre, rut, email, fono, region, comuna, glosa);

        homePage.clickEnviarFormulario();

        String resultado = homePage.obtenerResultadoFonoError();
        Assert.assertEquals(resultado, resultadoEsperado);
    }

    @Test
    public void testIngresoFolioPagoErroneo() {
        datos = ClaseDatos.getData("testIngresoFolioPagoErroneo");
        String numeroDeFolio = datos.get(1);
        String resultadoEsperado = datos.get(8);

        homePage.clickPagoEnLinea();
        pagoEnLineaPage.rellenarNumeroDeFolio(numeroDeFolio);
        pagoEnLineaPage.hacerClickEnviarFolio();

        Pattern patterEsperdado = Pattern.compile(resultadoEsperado);
        String resultado = pagoEnLineaPage.getResultadoFolioErroneo(patterEsperdado);
        Assert.assertEquals(resultado, resultadoEsperado);

    }

    @Test
    public void testRedireccionUrl() {
        datos = ClaseDatos.getData("testRedireccionUrl");
        String resultadoEsperado = datos.get(8);

        homePage.clickSucursales();
        String urlResultado = homePage.getCurrentUrl();

        Assert.assertEquals(urlResultado, resultadoEsperado);

    }

    @Test
    public void testBusquedaMapa() {
        datos = ClaseDatos.getData("testBusquedaMapa");
        String textoAbuscar = datos.get(1);
        String resultadoEsperado = datos.get(8);

        homePage.clickSucursales();
        sucursalesPage.rellenaTextoBusquedaMapa(textoAbuscar);
        sucursalesPage.clickBuscarEnMapa();

        Pattern patternEsperaddo = Pattern.compile(resultadoEsperado);
        String resultado = sucursalesPage.getResultadoBusquedaMapa(patternEsperaddo);
        Assert.assertEquals(resultado, resultadoEsperado);
    }


    @AfterTest
    public void closeBrowser() {
        homePage.cerrarBrowser();
    }

}
