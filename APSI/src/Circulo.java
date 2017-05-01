
public class Circulo {
	
	double diametro = 0.0;
	double raio;
	double area = 0.0;
	double circunferencia = 0.0;
	
	public Circulo(double raio){
		this.raio = raio;
		this.diametro = raio*2;
	}
	
	public double calculaArea(){
		return this.area = 3.14 * raio;
	}
	
	public double calculaCircunferencia(){
		return this.circunferencia = diametro * 3.14;
	}
	
	public String verificaContato(){
		//considerando que o tamanho do plano seria 81,
		//se os circulos forem maiores que a metade do plano, eles estao se tocando
		if(calculaArea() > 40){
			return "Circulos estao se tocando";
		}
		else{
			return "Circulos nao estao se tocando";
		}
	}
	

}
