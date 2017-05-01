import com.senac.SimpleJava.Console;
import java.text.DecimalFormat;   
public class Temperatura {

	
	public double converterParaCelcius(double grau){
		return (grau-32)/1.8;
	}
	
	public double converterParaFarenheit(double grau){
		return (grau*1.8)+ 32;
	}
	public Double ajustaCorretamente(String grau){
		
	if(grau.endsWith("F")){
		return converterParaCelcius(Double.parseDouble(grau.substring(0, 2)));
		} else{
			return converterParaCelcius(Double.parseDouble(grau.substring(0, 2)));
		}
	}
	
	
	public void run(){
		double grauC = 73;
		double grauF = 137;
		String graus = "43F";
		
		//transforma double em uma string para limitar numero e casas decimais.
		DecimalFormat df = new DecimalFormat("0.##");

		//.format recebe um double e converte em string com numero de casas decimais informados
		// teste dos metodos
		String grauCtoF = df.format(converterParaFarenheit(grauC));
		String grauFtoC = df.format(converterParaCelcius(grauF));
		String stringEmGraus = df.format(ajustaCorretamente(graus));
		
		Console.println(grauC,"° Celcius convertidos para ",grauCtoF,"° Farenheit");
		Console.println(grauF,"° Farenheit convertidos para ",grauFtoC,"° Celcius");
		Console.println("Recebida uma String e retornado o valor convertido para a outra medida de Grau: ",stringEmGraus);
	}
}
/*
4) Crie uma classe que representa a temperatura, onde o usuario da classe pode 
converter a temperatura em graus Celsius ou Farenheit. 
Crie tambem um metodo nesta classe que receba uma string, como "23C" ou "45F" e 
ajuste corretamente a temperatura.*/
