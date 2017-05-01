
public class Conta {
	/*2) Crie uma classe que representa uma Conta Bancária, 
	 * esta conta bancária deve permitir saques e depósitos. 
	 * Os saques só podem ser efetuados se houver saldo suficiente. 
		Crie também uma classe que representa um Cliente do banco, ao qual a conta bancária deve ser associada.*/
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
