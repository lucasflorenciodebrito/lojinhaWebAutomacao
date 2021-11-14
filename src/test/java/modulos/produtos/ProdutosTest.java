package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.ListaDeProdutosPage;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Módulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver94\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //vou maximizar a tela
        this.navegador.manage().window().maximize();

        //vou definir um tempo de espera padrão de até 5s
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar para a página da lojinha WEB
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero(){

       
        String mensagemToast = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormuláeioDeAdiçãoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorProduto("000")
                .informarCores("preto,branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemToast );

    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor maior que 7000,00")
    public void testeNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormuláeioDeAdiçãoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorProduto("700001")
                .informarCores("preto,branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada );
    }

    @Test
    @DisplayName("Posso adiciomar produtos que estejam no limite de 0,01")
    public void testPossoAdicionarProdutosComValorDeumCentavo(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormuláeioDeAdiçãoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorProduto("001")
                .informarCores("preto,branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada );

    }

    @Test
    @DisplayName("Posso adiciomar produtos que estejam no limite de 7.000,00")
    public void testPossoAdicionarProdutosComValorDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormuláeioDeAdiçãoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorProduto("700000")
                .informarCores("preto,branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada );

    }


   

    @AfterEach
    public void afterEach(){

        //Vou fechar o navegador
       navegador.quit();

    }

}
