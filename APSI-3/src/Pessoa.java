import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import com.senac.SimpleJava.Console;

public class Pessoa {
	LocalDate hoje = LocalDate.now();
	LocalDate dataNasc;
	String signo = "b";
	int idade;
	
	//calcula idade da pessoa
	
	public int calculaIdade(){
		if(dataNasc.getDayOfYear() <= hoje.getDayOfYear()){
			return idade = hoje.getYear() - dataNasc.getYear();
		}else{
			return idade = (hoje.getYear() - dataNasc.getYear())-1;
		}
	}
	
	//TESTE DE SIGNO PRECISA SER MELHORADO.
	public String verificaSigno(){
		int diaDoAno = dataNasc.getDayOfYear();
		if(!dataNasc.isLeapYear()){
			diaDoAno -= 1;
		}
		if(diaDoAno > 20 && diaDoAno < 52){
			return "Aquario";	
		} else if(diaDoAno > 51 && diaDoAno < 81){
			return "Peixes";
		} else if(diaDoAno > 80 && diaDoAno < 112){
			return "Aries";
		} else if(diaDoAno > 111 && diaDoAno < 142){
			return "Touro";
		} else if(diaDoAno > 141 && diaDoAno < 173){
			return "Gemeos";
		} else if(diaDoAno > 172 && diaDoAno < 204){
			return "Cancer";
		} else if(diaDoAno > 203 && diaDoAno < 236){
			return "Leao";
		} else if(diaDoAno > 235 && diaDoAno < 267){
			return "Virgem";
		} else if(diaDoAno > 266 && diaDoAno < 297){
			return "Libra";
		} else if(diaDoAno > 296 && diaDoAno < 326){
			return "Escorpiao";
		} else if(diaDoAno > 325 && diaDoAno < 356){
			return "Sagitario";
		}else{
			return "Capricornio";
		}
	}
	
	public void run(){
		
		dataNasc = LocalDate.of(1990, 12, 14);
		signo = verificaSigno();
		idade = calculaIdade();
												//format corrige a data para o formato especificado.
		Console.println("Data de Nascimento: ",dataNasc.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		Console.println("Idade calculada da Pessoa: ",idade);
		Console.println("Signo da pessoa: ",signo);
		
	}
}



/*3) Crie uma classe que representa uma Pessoa, 
 * esta classe deve implementar os atributos Data de Nascimento, Idade e Signo. 
 * Voce deve utilizar a 
 * classe LocalDate (disponivel a partir do Java  8, no pacote java.time).*/
 