package com.prova.automacao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Carrinho {

	private WebDriver driver;

	public Carrinho(WebDriver driver) {
		this.driver = driver;
	}

	public void adicionarProdutoQuantidade(String nomeProduto, int qtde) {
		for (int i = 0; i < qtde; i++) {
				driver.findElement(By.xpath("//*[contains(text(), '" + nomeProduto + "')]//../div[1]/div[2]")).click();
		}
	}
	
	public void removerProdutoQuantidade(String nomeProduto, int qtde) {

		for (int i = 0; i < qtde; i++) {
			driver.findElement(By.xpath("//*[contains(text(), '" + nomeProduto + "')]//../div[1]/div[1]")).click();
		}
	}

	public void concluirCompra() {
		driver.findElement(By.id("finish-checkout-button")).click();
	}

	public String validaPedidoComSucesso() {
		return driver.findElement(By.xpath("//*[contains(text(),'Pedido realizado com sucesso!')]")).getText();
	}

	public void fecharMensagemCompra() {
		 driver.findElement(By.xpath("//*[contains(text(),'Fechar')]")).click();
	}

	public void validarTotal() {

		int quantidadeItem = 0;
		double valorItem = 0.0f;
		double valorTotal = 0.0f;
		double valorSubtotal;

		
		ArrayList<WebElement> listaItens = (ArrayList<WebElement>) driver
		.findElements(By.xpath("//*[@class='sc-csuQGl wfUhd']"));
		
		String quantidadeItemStr;
		String valorItemStr;
		for (int i = 0; i < listaItens.size(); i++) {
			quantidadeItemStr = driver.findElement(By.id(String.format("product-%d-qtd", i))).getText();
			valorItemStr = driver.findElement(By.id(String.format("product-%d-price", i))).getText();
			
			quantidadeItem = Integer.parseInt(quantidadeItemStr);

			valorItem = limpaValor(valorItemStr);
			
			valorTotal += quantidadeItem * valorItem;
		}

		String subtotal = driver.findElement(By.id("subtotal-price")).getText();
		valorSubtotal = limpaValor(subtotal);

		assertEquals(valorSubtotal + "", valorTotal + "");
	}

	private double limpaValor(String valorItemStr) {
		return Double.parseDouble(valorItemStr.replace("R$", "").replace(",", ".").trim());
	}

}
