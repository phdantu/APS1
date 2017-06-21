package com.senac.SimpleJava.Graphics.examples;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import com.senac.SimpleJava.Console;
import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Image;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.events.MouseEvent;
import com.senac.SimpleJava.Graphics.events.MouseObserver;
public class Draw extends GraphicApplication implements MouseObserver {
	
	
	private Image cenario, character; 
	private boolean indice = true;
	private Sala salaTemporaria = new Sala();
	private Sala[] rooms = new Sala[32];
	private int nroRandom = geraNroRandom(), nroDaPortaProximaSala = -1, verificaQualSaidaEstaBloqueada = -1,verificaQualSaidaEstaBloqueadaPorChave =-1;
	private Image portalImage = null, escadaUpImage = null, escadaDownImage = null,keyImage = null;
	private Image trollImage = null, orcImage = null, goblinImage = null, shieldImage = null, lockImage = null;
	private Image knifeImage = null, daggerImage = null, swordImage = null, longSwordImage = null;
	private Point localizacaoDoMonstro = new Point(0.00, 0.00);
	private Point localizacaoDoCadeado = new Point(0.00, 0.00);
	
	
	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		desenhaSalaEPersonagem(canvas);
		if(indice){
			defineSalas(canvas, salaTemporaria);
			desenhaInimigo(canvas,salaTemporaria);
			desenhaArma(canvas,salaTemporaria);
			desenhaCadeado(canvas,salaTemporaria);
			desenhaChave(canvas,salaTemporaria);
			canvas.setForeground(Color.WHITE);
			canvas.putText(10, 10, 15, "Sala: " + nroRandom);
			indice = false;
				}else{
					salaTemporaria = chamaSalaSeguinte(nroDaPortaProximaSala);
					defineSalas(canvas, salaTemporaria);
					desenhaInimigo(canvas,salaTemporaria);
					desenhaArma(canvas,salaTemporaria);
					desenhaCadeado(canvas,salaTemporaria);
					desenhaChave(canvas,salaTemporaria);
					canvas.setForeground(Color.WHITE);
					canvas.putText(10, 10, 15, "Sala: " + nroDaPortaProximaSala);
				}
	}
	@Override
	protected void loop() {
		
	}
	@Override
	public void notify(MouseEvent event, int button, Point point) {
		if (event == MouseEvent.CLICK) {
			if(testaSeClicouNoInimigo(point)){
				Console.println("-----ATACOU O INIMIGO-----");
				turnoJogador();
				Console.println("-----TURNO DO INIMIGO------");
				turnoInimigo();
			}else{
				if(testaSeClicouNaArma(point)){
					Console.println("CLICOU NA ARMA: ",salaTemporaria.getArma().getNome());
					salaTemporaria.getJogador().pegaArma(salaTemporaria.getArma());
				}else{
					if(testaSeClicouNaChave(point)){
						Console.println("PEGOU A CHAVE.");
						salaTemporaria.getJogador().PegaChave(salaTemporaria.getChave());
					}
					if(testaSeClicouNoCadeado(point)){
						if(salaTemporaria.getJogador().getChave() != null){
							Console.println("Destrancou a porta.");
						}else{
							Console.println("Porta trancada. Ache a chave.");
						}
					}
				}
			}
			if(testaPortaNorth(point)){
					nroDaPortaProximaSala = descobreProximaSala(nroRandom,0);
					redraw();
			}else{
				if(testaPortaSouth(point)){
					nroDaPortaProximaSala = descobreProximaSala(nroRandom,1);
					redraw();
				}else{
					if(testaPortaWest(point)){
						nroDaPortaProximaSala = descobreProximaSala(nroRandom,2);
						redraw();
					}else{
						if(testaPortaEast(point)){
							nroDaPortaProximaSala = descobreProximaSala(nroRandom,3);
							redraw();
						}else{
							if(testaPortaUp(point)){
								nroDaPortaProximaSala = descobreProximaSala(nroRandom,4);
								redraw();
							}else{
								if(testaPortaDown(point)){
									nroDaPortaProximaSala = descobreProximaSala(nroRandom,5);
									redraw();
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	protected void setup() {
		Rooms();	
		addMouseObserver(MouseEvent.CLICK, this);
		this.setResolution(Resolution.HIGHRES);
		
		salaTemporaria = rooms[nroRandom];
		//rooms[nroRandom].getExits();
		 try {
	        	cenario = new Image("data/newBG.png");
	        	character = new Image("data/player.png");
	        	//rooms objects images
	        	portalImage = new Image("data/door.png");
	        	escadaUpImage = new Image ("data/upStairs.png");
	        	escadaDownImage = new Image ("data/downStairs.png");
				//items image
	        	shieldImage = new Image("data/shield.png");
	        	keyImage = new Image("data/key.png");
	        	lockImage = new Image("data/lock.png");
	        	//weapons images
	        	knifeImage = new Image("data/knife.png");
	        	daggerImage = new Image("data/dagger.png");
	        	longSwordImage = new Image("data/longSword.png");
	        	swordImage = new Image("data/sword.png");
	        	//enemies images
	        	trollImage = new Image ("data/troll.png");
	        	orcImage = new Image ("data/orc.png");
	        	goblinImage = new Image ("data/goblin.png");
			} catch (IOException e) {
				e.printStackTrace();
				endLoop();
			}
		}
	//-------------------------------------METODOS PARA TESTE DE LOCALIZACAO DE IMAGEM---------------------
	public boolean testaPortaNorth(Point point){
				if(point.x >= 360.00 && point.x <= (portalImage.getWidth()+360) && verificaSeTemImagemDePorta(0)){ //360,0
					if(point.y >= 0 && point.y <= portalImage.getHeight()){
						Console.println("TESTE PRA VER SE ESTA CHEGANDO NESTE TESTE");
						if(verificaQualSaidaEstaBloqueada == 0 && salaTemporaria.getMonstro().getLife() > 0){
							Console.println("Portal bloqueado.");
							return false;
						}else{
							return true;
						}
					}
				}
		return false;
	}
	
	public boolean testaPortaSouth(Point point){
			if(point.x >= 210.00 && point.x <= (portalImage.getWidth()+210) && verificaSeTemImagemDePorta(1)){ //210, 465
				if(point.y >= 465.00 && point.y <= portalImage.getHeight()+465){
					if(verificaQualSaidaEstaBloqueada == 1 && salaTemporaria.getMonstro().getLife() > 0){
						Console.println("Portal bloqueado.");
						return false;
					}else{
						return true;
					}
				}
			}
		return false;
	}
	public boolean testaPortaEast(Point point){
		if(point.x >= 25.00 && point.x <= (portalImage.getWidth()+25) && verificaSeTemImagemDePorta(2)){ //25, 170
			if(point.y >= 170.00 && point.y <= portalImage.getHeight()+170){
				if(verificaQualSaidaEstaBloqueada == 3 && salaTemporaria.getMonstro().getLife() > 0){
					Console.println("Portal bloqueado.");
					return false;
				}else{
					return true;
					}
				}
			}
		return false;
	}
	public boolean testaPortaWest(Point point){
			if(point.x >= 680.00 && point.x <= (portalImage.getWidth()+680) && verificaSeTemImagemDePorta(3)){ //680, 170
				if(point.y >= 170.00 && point.y <= portalImage.getHeight()+170){
					if(verificaQualSaidaEstaBloqueada == 2 && salaTemporaria.getMonstro().getLife() > 0){
						Console.println("Portal bloqueado.");
						return false;
					}else{
						return true;
					}
				}
			}
		return false;
	}
	public boolean testaPortaUp(Point point){
			if(point.x >= 112.00 && point.x <= (escadaUpImage.getWidth()+112) && verificaSeTemImagemDePorta(4)){ // 112, 0
				if(point.y >= 0.00 && point.y <= escadaUpImage.getHeight()){
					if(verificaQualSaidaEstaBloqueada == 4 && salaTemporaria.getMonstro().getLife() > 0){
						Console.println("Portal bloqueado.");
						return false;
					}else{
						return true;
					}
				}
			}
		return false;
	}
	public boolean testaPortaDown(Point point){
			if(point.x >= 590.00 && point.x <= (escadaDownImage.getWidth()+590) && verificaSeTemImagemDePorta(5)){ //590, 420
				if(point.y >= 420.00 && point.y <= escadaDownImage.getHeight()+420){
					if(verificaQualSaidaEstaBloqueada == 5 && salaTemporaria.getMonstro().getLife() > 0){
						Console.println("Portal bloqueado.");
						return false;
					}else{
						return true;
					}
				}
			}
		return false;
	}
	//-------------------------------------METODOS PARA TESTE DE LOCALIZACAO DE IMAGEM---------------------
	public boolean testaSeClicouNaArma(Point point){
		if(point.x >= 600.00 && point.x <= (longSwordImage.getWidth()+600)){ //600, 150
			if(point.y >= 150.00 && point.y <= longSwordImage.getHeight()+150){
				if(salaTemporaria.getArma().getHasWeapon()){
					return true;
				}
			}
		}
		return false;
	}
	public boolean testaSeClicouNoCadeado(Point point){
		if(point.x >= localizacaoDoCadeado.x && point.x <= (portalImage.getWidth()+localizacaoDoCadeado.y)){ //600, 150
			if(point.y >= localizacaoDoCadeado.y && point.y <= portalImage.getHeight()+localizacaoDoCadeado.y){
				if(salaTemporaria.getChave().getHasChave()){
					return true;
				}
			}
		}
		return false;
	}
	public boolean testaSeClicouNaChave(Point point){
		if(point.x >= 150 && point.x <= (keyImage.getWidth()+150)){ //600, 150
			if(point.y >= 400 && point.y <= keyImage.getHeight()+400){
				if(salaTemporaria.getChave().getHasChave()){
					return true;
				}
			}
		}
		return false;
	}
	public boolean testaSeClicouNoInimigo(Point point){
		//Console.println("FORA DO IF -- X ",localizacaoDoMonstro.x," Y -> ",localizacaoDoMonstro.y);
		//Console.println("POINT - FORA DO IF -- X ",point.x," Y -> ",point.y);
		if(point.x >= localizacaoDoMonstro.x && point.x <= (descobreInimigo(salaTemporaria.getMonstro()).getWidth()+localizacaoDoMonstro.y)){ //600, 150
			if(point.y >= localizacaoDoMonstro.y && point.y <= descobreInimigo(salaTemporaria.getMonstro()).getHeight()+localizacaoDoMonstro.y){
				return true;
			}
		}
		return false;
	}
	//---------------------------METODOS GET-------------------------------------------------
	public Image getDaggerImage(){
		return daggerImage;
	}
	public Image getKnifeImage(){
		return knifeImage;
	}
	public Image getLongSwordImage(){
		return longSwordImage;
	}
	public Image getSwordImage(){
		return swordImage;
	}
	//---------------------------------------------------------------------------------------
	public void turnoJogador(){
		if(salaTemporaria.getJogador().chanceDeAcerto()){
			salaTemporaria.getMonstro().levaDano(salaTemporaria.getJogador().getDano());
			Console.println("Causou ", salaTemporaria.getJogador().getDano(), " de dano ao ", salaTemporaria.getMonstro().getNome());
		}else{
			Console.println("Você errou o ataque.");
		}
	}
	public void turnoInimigo(){
		if(salaTemporaria.getMonstro().getLife() > 0){
			Console.println("VIDA DO INIMIGO ",salaTemporaria.getMonstro().getLife());
			if(salaTemporaria.getMonstro().chanceDeAcerto()){
				salaTemporaria.getJogador().levaDano(salaTemporaria.getMonstro().getDano());
				Console.println(salaTemporaria.getMonstro().getNome(), " causou ",salaTemporaria.getMonstro().getDano()," de dano ao seu persongem.");
			}else{
				Console.println(salaTemporaria.getMonstro().getNome()," errou o ataque.");
			}
		}else{
			Console.println("Pare de chutar o cadaver. E atravesse a porta!");
		}
	}
	public boolean verificaSeTemImagemDePorta(int index){
		boolean[] seTemPorta = salaTemporaria.getDirections();
			if(seTemPorta[index]){
				return true;
			}
		return false;
	}
	public Sala chamaSalaSeguinte(int index){
		if(index ==0){
			Console.println("VOCE SAIU DO LABIRINTO");
			System.exit(1);
		}
		if(index < 0){
			Console.println("ERRO DE INDICE");
			System.exit(1);
		}
		return rooms[index];
	}
	public int descobreProximaSala(int indiceSalaAtual, int nroIndiceDaSaida){
		int[] temporario = rooms[indiceSalaAtual].getExits();
		int praRetorno = -2;
		/*north - 0 || south - 1 || east - 2 || west - 3 || up - 4 ||down - 5 */
			switch (nroIndiceDaSaida) {
			case 0:
				praRetorno = temporario[0];
				break;
			case 1:
				praRetorno = temporario[1];
				break;
			case 2:
				praRetorno = temporario[2];
				break;
			case 3:
				praRetorno = temporario[3];
				break;
			case 4:
				praRetorno = temporario[4];
				break;
			case 5:
				praRetorno = temporario[5];
				break;
			}
		return praRetorno;
	}
	public void desenhaSalaEPersonagem(Canvas canvas){
		cenario.resize(800, 600);
		canvas.drawImage(cenario, 0, 0);
		canvas.drawImage(character, 370,250);
	}
	public int geraNroRandom(){
		Random rnd = new Random();
		return (rnd.nextInt(30)+1);
	}
    public void desenhaInimigo(Canvas canvas, Sala salaTemporaria){
    	Random rnd = new Random();
    	double x=0,y=0;
    	int index = -1;
    	boolean[] direcoesTemporario = salaTemporaria.getDirections();
    	while(true){
    		 index = rnd.nextInt(6);
    		 /*north - 0 || south - 1 || east - 2 || west - 3 || up - 4 ||down - 5 */
    		if(direcoesTemporario[index] == true){
    			if(index==0){
    				x = 360;
    				y = 100;
    				canvas.drawImage(descobreInimigo(this.salaTemporaria.getMonstro()),  360,100);
    				break;
    			}else{
    				if(index==1){	
        				x = 360;
        				y = 450;
        				canvas.drawImage(descobreInimigo(this.salaTemporaria.getMonstro()), 360,450);
        				break;
        			}else{
        				if(index==2){
            				x = 600;
            				y = 250;
            				canvas.drawImage(descobreInimigo(this.salaTemporaria.getMonstro()),600,250);
            				break;
            			}else{
            				if(index==3){
                				x = 100;
                				y = 280;
                				canvas.drawImage(descobreInimigo(this.salaTemporaria.getMonstro()), 100,280);
                				break;
                			}else{
                				if(index==4){
                    				x = 200;
                    				y = 100;
                    				canvas.drawImage(descobreInimigo(this.salaTemporaria.getMonstro()),200,100);
                    				break;
                    			}else{
                    				if(index==5){
                        				x = 580;
                        				y = 300;
                        				canvas.drawImage(descobreInimigo(this.salaTemporaria.getMonstro()), 580,300);
                        				break;
                        				}
                    				}
                				}
            				}
        				}
    				}
    		}	
    	}
    	verificaQualSaidaEstaBloqueada = index;
    	localizacaoDoMonstro = new Point(x,y);
    }
    	
	public Image descobreInimigo(Inimigo descobreInimigo){
    	Image imagemRetorno = null;
    	int testeInimigo = descobreInimigo.getIndiceInimigo();
    	if(testeInimigo == 0){
    		imagemRetorno = goblinImage;
    	}else{
    		if(testeInimigo == 1){
    			imagemRetorno = orcImage;
    		}else{
    			if(testeInimigo == 2){
    				imagemRetorno = trollImage;
    			}
    		}
    	}
    	return imagemRetorno;
    }
	public void desenhaCadeado(Canvas canvas, Sala salaTemporaria){
    	if(salaTemporaria.getChave().getHasChave()){
    		Random rnd = new Random();
			int index = -1;
    		double x = 0.0,y= 0.0;
    		boolean[] direcoesTemporario = salaTemporaria.getDirections();
    		while(true){
    		 index = rnd.nextInt(6);
    		/*north - 0 || south - 1 || east - 2 || west - 3 || up - 4 ||down - 5 */
    		 	if(direcoesTemporario[index]){
    				if(index==0){
    					x=380.0;
    					y=30.0;
    					canvas.drawImage(lockImage,  380,30);
    					break;
    				}else{
    					if(index==1){	
        					x=240.0;
        					y=500.0;
        					canvas.drawImage(lockImage, 240,500);
        					break;
        				}else{
        					if(index==2){
            					x=700.0;
            					y=200.0;
            					canvas.drawImage(lockImage, 700,200);
            					break;
            				}else{
            					if(index==3){
                					x=50.0;
                					y=200.0;
                					canvas.drawImage(lockImage, 50,200);
                					break;
                				}else{
                					if(index==4){
                    					x=150.0;
                    					y=100.0;
                    					canvas.drawImage(lockImage,150,100);
                    					break;
                    				}else{
                    					if(index==5){
                    						x=600.0;
                        					y=450.0;
                    						canvas.drawImage(lockImage,600,450);
                        					break;
                        					}
                    					}
                					}
            					}
        					}
    					}
    			}
    			verificaQualSaidaEstaBloqueadaPorChave = index;
    			localizacaoDoCadeado = new Point(x,y);
    		}
    	}
    }
	public void desenhaChave(Canvas canvas, Sala salaTemp){
    	if(salaTemp.getChave().getHasChave()){
    		canvas.drawImage(keyImage, 150, 400);
    	}
	}
    public void desenhaArma(Canvas canvas, Sala salaTemp){
    	if(salaTemp.getArma().getHasWeapon()){
    		canvas.drawImage(descobreArma(salaTemp.getArma()), 600, 150);
    	}
    }
    public Image descobreArma(Arma descobreArma){
    	Image imagemRetorno = null;
    	//Console.println("DANO DA ARMA --> ", descobreArma.getDano());
    	if(descobreArma.getDano() == 1){
    		imagemRetorno = getDaggerImage();
    	}else{
    		if(descobreArma.getDano() == 2){
    			imagemRetorno = getKnifeImage();
    		}else{
    			if(descobreArma.getDano() == 3){
    				imagemRetorno = getSwordImage();
    			}else{
    				if(descobreArma.getDano() == 5){
    					imagemRetorno = getLongSwordImage();
    				}
    			}
    		}
    	}
		return imagemRetorno;
    }
    
	public void Rooms(){
		rooms[0] = new Sala();
		rooms[0] = null;
		String direction;
		int exit;
		int roomCount = 0;
		String linha = null;
		try{
			@SuppressWarnings("resource")
			Scanner arq = new Scanner(new File("data/Labirinto.txt"));
			linha = arq.next();
			while(linha.equalsIgnoreCase("room")){
				roomCount = arq.nextInt();
				rooms[roomCount] = new Sala();
				//While para repetir a quantidade de salas(saidas) que houver
				while(arq.hasNext()){
					direction = arq.next();
					if(direction.equalsIgnoreCase("room")){
						break;
					}else{
						exit = arq.nextInt();
						rooms[roomCount].addExit(direction, exit);
					}
				}
				if(!arq.hasNext()){
					return;
				}
				
			}
			//fecha o arquivo
			arq.close();
		}catch (IOException ioe) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					ioe.getMessage());
		}
	}

    public void defineSalas(Canvas canvas, Sala temp){
    	boolean[] saidasTemporarias;
    	/*north - 0 || south - 1 || east - 2 || west - 3 || up - 4 ||down - 5 */
    		saidasTemporarias = temp.getDirections();
    	int[] teste = temp.getExits();
    	for(int i=0;i<6;i++){
    		Console.println(saidasTemporarias[i]);
    		Console.println(teste[i]);
    	}
    	if(saidasTemporarias[0]){
    		canvas.drawImage(portalImage, 360, 0);
    	}
    	if(saidasTemporarias[1]){
			canvas.drawImage(portalImage, 210, 465);
    	}
    	if(saidasTemporarias[2]){
    		canvas.drawImage(portalImage, 680, 170 );
    	}
    	if(saidasTemporarias[3]){
    		canvas.drawImage(portalImage, 25, 170);
    	}
    	if(saidasTemporarias[4]){
    		canvas.drawImage(escadaUpImage, 112, 0);
    	}
    	if(saidasTemporarias[5]){
    		canvas.drawImage(escadaDownImage, 590, 420);
    	}
    }
}
		

