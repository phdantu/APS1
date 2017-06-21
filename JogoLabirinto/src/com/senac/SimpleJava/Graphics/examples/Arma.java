package com.senac.SimpleJava.Graphics.examples;

import java.util.Random;

public class Arma extends Item{
	private boolean arma;
	private int dano;
	private String nome;
	
	public Arma(){
		this.dano = randomizaArma();
		arma = false;
		if(dano == 1){
			this.nome = "Adaga";
		}else{
			if(dano == 2){
				this.nome = "Faca";
			}else{
				if(dano == 3){
					this.nome = "Espada";
				}else{
					if(dano == 5){
						this.nome = "Espada Longa";
					}
				}
			}
		}
	}
	
	public int chanceDeAcerto(Arma arma){
		int retorno = 0;
		if(arma.dano == 1){
			this.nome = "Adaga";
			retorno = 74;
		}else{
			if(arma.dano == 2){
				this.nome = "Faca";
				retorno =  79;
			}else{
				if(arma.dano == 3){
					this.nome = "Espada";
					retorno =  84;
				}else{
					if(arma.dano == 5){
						this.nome = "Espada Longa";
						retorno =  64;
					}
				}
			}
		}
		return retorno;
	}
	
	//Metodo para randomizar uma arma. gera um numero entre 1.2.3 e 5. Referente ao Dano.
	public int randomizaArma(){
		Random rnd = new Random();
		int nroRandom = 0;
				
		while(true){
			if(nroRandom == 4 || nroRandom == 0){
			nroRandom = (rnd.nextInt(6));
			}
			else{
				break;
			}
		}
		return nroRandom;
	}
	
	public int getDano(){
		return dano;
	}
	public void setDano(int dano){
		this.dano = dano;
	}
	
	public void setHasWeapon(boolean arma) {
		this.arma = arma;
	}
	public boolean getHasWeapon() {
		return arma;
	}

	public String getNome() {
		return nome;
	}
	
}
