package com.prova.automacao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Produtos {

	private WebDriver driver;

	public Produtos(WebDriver driver) {
		this.driver = driver;
	}

	public Produtos abrirSite() {
		driver.get("https://shopcart-challenge.4all.com/");
		return this;
	}

	public void selecionarCategorias(String nomeCategoria) {
		driver.findElement(By.id("open-categories-btn")).click();
		driver.findElement(By.xpath("//*[text()='" + nomeCategoria + "']")).click();
	}

	public void adicionarProdutoUnitario(String nomeProduto) {
		driver.findElement(By.xpath("//*[@class='sc-bdVaJa hRjvgd']//*[text()='" + nomeProduto
				+ "']//../../..//button[contains(text(),'Adicionar ao carrinho')]")).click();
	}

	public void abrirCarrinho() {
		driver.findElement(By.id("cart-btn")).click();
	}

}
