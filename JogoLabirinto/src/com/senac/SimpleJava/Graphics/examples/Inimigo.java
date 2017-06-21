package com.senac.SimpleJava.Graphics.examples;

import java.util.Random;

import com.senac.SimpleJava.Console;

public class Inimigo {
	private boolean hasEnemie, hasWeapon;
	private int life;
	private int dano;
	private int random;
	private String nome;
	
	public Inimigo(){
		setHasWeapon(false);
		hasEnemie = false;
		random = randomizaInimigo();
		if(random == 0){
			setDano(2);
			life = 12;
			setNome("Goblin");
			if(chance33PorCento()){
				setHasWeapon(true);
				setDano(dano*2);
			}
		}else{
			if(random == 1){
				setDano(4);
				life = 10;
				setNome("Orc");
				if(chance33PorCento()){
					setHasWeapon(true);
					setDano(dano*2);
				}
			}else{
				if(random == 2){
					setDano(6);
					life = 8;
					setNome("Troll");
					if(chance33PorCento()){
						setHasWeapon(true);
						setDano(dano*2);
					}
				}
			}
		}
	}
	public void levaDano(int dano){
		life=life-dano;
		if(confereLife(dano)){
			Console.println(nome," tem agora ",life," pontos de vida.");
		}
		else{
			Console.println(nome," derrotado!");
		}
		
	}
	private int randomizaInimigo() {
		Random rnd = new Random();
		return rnd.nextInt(3);
	}
	public int getIndiceInimigo(){
		return random;
	}
	public boolean isHasEnemie() {
		return hasEnemie;
	}

	public void setHasEnemie(boolean hasEnemie) {
		this.hasEnemie = hasEnemie;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private boolean chance33PorCento(){
        int numero = (int) (Math.random()*3);
        return (numero == 0 ? true : false);
    }
	public boolean getHasWeapon() {
		return hasWeapon;
	}
	public void setHasWeapon(boolean hasWeapon) {
		this.hasWeapon = hasWeapon;
	}
	public int getDano(){
		return dano;
	}
	public int getLife(){
		return life;
	}
	public void setDano(int dano){
		this.dano = dano;
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
		int random = rnd.nextInt(99);
		if(this.nome.equalsIgnoreCase("goblin")){
			if(random <= 79){
				retorno = true;
			}
		}else{
			if(this.nome.equalsIgnoreCase("orc")){
				if(random <= 74){
					retorno = true;
				}
			}else{
				if(this.nome.equalsIgnoreCase("troll")){
					if(random <= 49){
						retorno = true;
					}
				}
			}
		}
		return retorno;
	}
}
