package utilidades;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class ClaseBase {
    //atributos
    protected WebDriver driver;
    protected WebDriverWait wait;

    //Métodos

    //constructor
    public ClaseBase(WebDriver driver) {
        this.driver = driver;
    }

    //getters and setters
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //métodos que contengan las acciones del webdriver y element

    //método para buscar un elemento web
    public WebElement buscarElementoWeb(By localizador) {
        return this.driver.findElement(localizador);
    }

    //método para buscar elementos web
    public List<WebElement> buscarElementosWeb(By localizador) {
        return this.driver.findElements(localizador);
    }

    //click en base a un locator
    public void hacerClick(By localizador) {
        this.driver.findElement(localizador).click();
    }

    //sobrecarga del método click para trabajar desde un elemento web
    public void hacerClick(WebElement elemento) {
        elemento.click();
    }

    // select
    public void selectByVisibleText(Select selector, String visibleText) {
        esperarXsegundos(500);
        selector.selectByVisibleText(visibleText);
    }

    // dropDownSelector
    public Select getSelector(By selector) {
        Select dropDownSelector = new Select(this.driver.findElement(selector));
        return dropDownSelector;
    }

    //obtenerTexto
    public String obtenerTexto(By localizador) {
        return this.driver.findElement(localizador).getText();
    }

    //sobrecarga del método obtenerTexto
    //para trabajar desde un elemento web
    public String obtenerTexto(WebElement elemento) {
        return elemento.getText();
    }

    //ingresarDatos via locator
    public void setTexto(By localizador, String texto) {
        this.driver.findElement(localizador).sendKeys(texto);
    }

    //ingresarDatos via web element
    public void setTexto(WebElement elemento, String texto) {
        elemento.sendKeys(texto);
    }

    //maximizar la ventana
    public void maximizarVentana() {
        this.driver.manage().window().maximize();
    }

    //crear la conexion con el browser
    public WebDriver conectarDriver(String browser, String property, String rutaDriver) {

        switch (browser) {
            case "chrome":
                System.setProperty(property, rutaDriver);
                this.driver = new ChromeDriver();
                return this.driver;
            case "firefox":
                System.setProperty(property, rutaDriver);
                this.driver = new FirefoxDriver();
                return this.driver;
            case "ie":
                System.setProperty(property, rutaDriver);
                this.driver = new InternetExplorerDriver();
                return this.driver;
            default:
                return driver;
        }

    }

    //esperas (implicitas y explicitas)
    public void esperarXsegundos(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println("error al esperar...");
            e.printStackTrace();
        }
    }

    //espera explicita por presencia del elemento web
    public WebElement esperarPorPresenciaDelElemento(By localizador) {
        wait = new WebDriverWait(this.driver, 20);

        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    //espera explicita por elemento a clickear
    public WebElement esperarPorclickearElemento(By localizador) {
        wait = new WebDriverWait(this.driver, 20);

        return wait.until(ExpectedConditions.elementToBeClickable(localizador));
    }

    public boolean esperaTextPattern(By locator, Pattern pattern){
        wait = new WebDriverWait(this.driver, 20);
        return wait.until(ExpectedConditions.textMatches(locator, pattern));
    }

    //movernos entre frames
    public void cambiarFrame(String idFrame) {
        this.driver.switchTo().frame(idFrame);
    }

    //buscar frames atraves del tag "iframe"
    public int contarFrames(By localizador) {
        List<WebElement> frames = this.driver.findElements(localizador);
        return frames.size();
    }

    //cargar la url
    public void abrirSitio(String url) {
        this.driver.get(url);
    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    //cerrar el browser
    public void cerrarBrowser() {
        this.driver.close();
    }

}
