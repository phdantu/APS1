package com.senac.SimpleJava.Graphics.examples;

import java.util.Random;

import com.senac.SimpleJava.Console;

public class Player {
	private Arma arma = null;
	private Chave chave;
	private boolean maoDireita,maoEsquerda, teste = true;
	private int life, dano;
	public Player(){
		setMaoDireita(false);
		setMaoEsquerda(false);
		life = 20;
		setDano(2);
	}
	public void levaDano(int dano){
		life=life-dano;
		if(confereLife(dano)){
			Console.println("Você tem agora ",life," pontos de vida.");
		}
		else{
			Console.println("Você Morreu");
		}
		
	}
	public void pegaArma(Arma arma){
		if(verificaSeMaosEstaoOcupadas()){
			Console.println("Você está com ambas as maos ocupadas.");
		}else{
			if(teste){
				this.arma = arma;
				setChanceDeAcerto(arma.chanceDeAcerto(arma));
				this.dano = 2+arma.getDano();
				Console.println("Seu dano agora é: ", dano);
				teste = false;
				maoDireita = true;
			}else{
				this.arma = arma;
				setChanceDeAcerto(arma.chanceDeAcerto(arma));
				Console.println("Você substituiu a arma de dano: ", this.arma.getDano());
				this.dano = 2+arma.getDano();
				Console.println("Pela arma com dano de: ", arma.getDano());
				maoDireita = true;
			}
		}
		
	}
	public void PegaChave(Chave chave){
		if(verificaSeMaosEstaoOcupadas()){
			Console.println("Você está com ambas as maos ocupadas.");
		}else{
			this.chave = chave;
			maoEsquerda = true;
		}
	}
	public Chave getChave(){
		return chave;
	}
	public boolean verificaSeMaosEstaoOcupadas(){
		if(maoDireita && maoEsquerda){
			return true;
		}
		return false;
	}
	public boolean confereLife(int dano){
		if(dano >= this.life){
			return false;
		}
		return true;
	}
	public boolean chanceDeAcerto(){
		boolean retorno = false;
		Random rnd = new Random();
		int val = rnd.nextInt(4) + 1;
			if (val == 1) { // <-- 1/4 of the time.
				
			} 
			else {
				retorno = true; // <-- 3/4 of the time.
			}
				return retorno;
	}
	public boolean setChanceDeAcerto(int valor){
		boolean retorno = false;
		Random rnd = new Random();
			int random = rnd.nextInt(100);
			if(random <= valor){
				retorno = true;
			}
				return retorno;
	}
	/*
	public String verificaMaoOcupada(){
		if(getMaoDireita()){
			return "Mao direita ocupada";
		}else{
			setMaoDireita(true);
			if(getMaoEsquerda()){
				return "Mao esquerda Ocupada, Ambas maos ocupadas.";
			}else{
				setMaoEsquerda(true);
			}
		
		}
		return "oi";
	}*/
	public boolean getMaoDireita() {
		return maoDireita;
	}
	public void setMaoDireita(boolean maoDireita) {
		this.maoDireita = maoDireita;
	}
	public boolean getMaoEsquerda() {
		return maoEsquerda;
	}
	public void setMaoEsquerda(boolean maoEsquerda) {
		this.maoEsquerda = maoEsquerda;
	}
	public int getDano() {
		return dano;
	}
	public void setDano(int dano) {
		this.dano = dano;
	}
	public int getLife(){
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
}
