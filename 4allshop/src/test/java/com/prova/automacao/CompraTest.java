package com.prova.automacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Unit test for simple App.
 */
public class CompraTest {

    public CompraTest() {

    }

    public WebDriver driver;
    public Produtos produtos;
    public Carrinho carrinho;

    @Before
    public void abrirNavegador() {
        System.setProperty("webdriver.chrome.driver", "/home/carol/workspace/4all/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void fecharNavegador() {
        driver.quit();
    }

    /**
     * Rigorous Test.
     * 
     * 
     */
    @Test
    public void Desafio1() {
        produtos = new Produtos(driver);
        carrinho = new Carrinho(driver);

        produtos.abrirSite();
        produtos.selecionarCategorias("Doces");
        produtos.adicionarProdutoUnitario("Brigadeiro");
        produtos.adicionarProdutoUnitario("Alfajor de chocolate");
        produtos.selecionarCategorias("Todos");
        produtos.abrirCarrinho();
        carrinho.adicionarProdutoQuantidade("Brigadeiro", 4);
        carrinho.concluirCompra();
        assertEquals(carrinho.validaPedidoComSucesso(), "Pedido realizado com sucesso!");
        carrinho.fecharMensagemCompra();


    }

    @Test
    public void Desafio2() {
        produtos = new Produtos(driver);
        carrinho = new Carrinho(driver);

        produtos.abrirSite();
        produtos.selecionarCategorias("Bebidas");
        produtos.adicionarProdutoUnitario("Coca-cola lata");
        produtos.adicionarProdutoUnitario("Fanta uva lata");
        produtos.adicionarProdutoUnitario("Água mineral sem gás");
        produtos.selecionarCategorias("Todos");
        produtos.adicionarProdutoUnitario("Rissole médio");
        produtos.abrirCarrinho();
        carrinho.adicionarProdutoQuantidade("Rissole médio", 9);
        carrinho.removerProdutoQuantidade("Rissole médio", 5);
        carrinho.validarTotal();
        carrinho.concluirCompra();
        assertEquals(carrinho.validaPedidoComSucesso(),"Pedido realizado com sucesso!");
        carrinho.fecharMensagemCompra();

    }
}
