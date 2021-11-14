package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {

    private WebDriver navegador;

    public FormularioDeAdicaoDeProdutoPage(WebDriver navegador) {
        this.navegador = navegador;

    }

    public FormularioDeAdicaoDeProdutoPage informarNomeDoProduto(String produtoNome) {
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;

    }

    public FormularioDeAdicaoDeProdutoPage informarValorProduto(String valor) {
        navegador.findElement(By.id("produtovalor")).sendKeys(valor);

        return this;
    }

    public FormularioDeAdicaoDeProdutoPage informarCores(String cores) {
        navegador.findElement(By.id("produtocores")).sendKeys(cores);
        return this;

    }

    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro(){

        navegador.findElement(By.cssSelector("button[type = 'submit']")).click();
        return new ListaDeProdutosPage(navegador);

    }

    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso(){

        navegador.findElement(By.cssSelector("button[type = 'submit']")).click();
        return new FormularioDeEdicaoDeProdutoPage(navegador);

    }



}

