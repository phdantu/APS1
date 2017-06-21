package com.senac.SimpleJava.Graphics.examples;

public class Sala {
	
	private int[] roomToGo = new int[6];
	private boolean[] directions = new boolean[6];
	private Inimigo monstro = new Inimigo();
	private Chave chave = new Chave();
	private Arma arma = new Arma();
	private Player jogador = new Player();
	
	
	public Sala(){
		for(int i=0; i<6;i++){
			roomToGo[i] = -1;
			directions[i] = false;
		}
		chanceDeTerItem(); //Gera a chance de ter um item.
	}
	
	public void addExit(String directionExit, int room){
		//informar a direcao de saida se haverá saida ou não para cada posicao, cima, baixo, esq, dir, sobe ou desce
			verificaSaidasDaSala(directionExit, room);
		}
    public void verificaSaidasDaSala(String nomeSaida, int roomToGo){
    	/*north - 0 || south - 1 || east - 2 || west - 3 || up - 4 ||down - 5 */
    	//Metodo atribui a sala em especifico, quais saidas e pra qual sala essas saidas levam.
    	if(nomeSaida.equalsIgnoreCase("north")){
    		directions[0] = true;
    		this.roomToGo[0] = roomToGo;
    	}else{
    		if(nomeSaida.equalsIgnoreCase("south")){
    			directions[1] = true;
    			this.roomToGo[1] = roomToGo;
    		}else{
    			if(nomeSaida.equalsIgnoreCase("east")){
    				directions[2] = true;
    				this.roomToGo[2] = roomToGo;
    			}else{
    				if(nomeSaida.equalsIgnoreCase("west")){
    					directions[3] = true;
    					this.roomToGo[3] = roomToGo;
    				}else{
    					if(nomeSaida.equalsIgnoreCase("up")){
    						directions[4] = true;
    						this.roomToGo[4] = roomToGo;
    					}else{
    						if(nomeSaida.equalsIgnoreCase("down")){
    							directions[5] = true;
    							this.roomToGo[5] = roomToGo;
    						}
    					}
    				}
    			}
    		}    	
    	}
    }
    public Arma getArma(){
    	return arma;
    }

    public void chanceDeTerItem(){
    	if(chance50PorCento()){
    		chave.setHasChave(true);
    	}
    	if(chance50PorCento()){
    		arma.setHasWeapon(true);
    	}
    }
    
    private boolean chance50PorCento(){
        int numero = (int) (Math.random()*2);
        return (numero == 0 ? true : false);
    }
    
    public int[] getExits(){
    	return roomToGo;
    }
    
    public boolean[] getDirections(){
    	return directions;
    }

	public Inimigo getMonstro() {
		return monstro;
	}
	
	public Chave getChave(){
		return chave;
	}
	
	public Player getJogador() {
		return jogador;
	}

	public void setJogador(Player jogador) {
		this.jogador = jogador;
	}
}
