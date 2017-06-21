package com.senac.SimpleJava.Graphics.examples;

import java.util.Random;

import com.senac.SimpleJava.Graphics.Image;

public class Chave extends Item{
	private boolean chave;
	private int cor;
	private Image chaveImagem = null;
	
	public Chave(){
		this.cor = randomizaChave(); //Gera numero aleatorio para criação da chave. Cada numero corresponde uma cor. 0 1 e 2
		this.chaveImagem = chaveImagem;
		chave = false;
	}
	
	public int randomizaChave(){
		Random rnd = new Random();
		int nroRandom = (rnd.nextInt(2));
		return nroRandom;
	}
	public int getCor(){
		return cor;
	}
	public void setHasChave(boolean chave) {
		this.chave = chave;
	}
	public boolean getHasChave() {
		return chave;
	}
	
}
