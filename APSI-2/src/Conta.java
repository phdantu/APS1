
public class Conta {
	/*2) Crie uma classe que representa uma Conta Banc�ria, 
	 * esta conta banc�ria deve permitir saques e dep�sitos. 
	 * Os saques s� podem ser efetuados se houver saldo suficiente. 
		Crie tamb�m uma classe que representa um Cliente do banco, ao qual a conta banc�ria deve ser associada.*/
	double saldo = 0.0;
	
	public Conta(double saldo, Cliente clienteBancario){
		this.saldo = saldo;
	}
	public String realizaSaque(double valor){
		if(this.saldo<valor){
			this.saldo -= valor;
			return "Saque realizado";
		}
		else{
			return "Saldo insuficiente";
		}
		
	}
	
	public void realizaDeposito(double valor){
		this.saldo += valor;
	}
}
