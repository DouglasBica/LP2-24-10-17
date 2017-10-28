package space.indietech.produto;

public class ProdutoComDesconto extends Produto {

	private double desconto;
	private double valorComDesconto;

	public ProdutoComDesconto(long codigo, double desconto, String nome, double valor) {
		this.codigo = codigo;
		this.nome = nome;
		this.desconto = desconto;
		this.valor = valor;
		gerarDesconto();
	}


	private void gerarDesconto() {
		this.valorComDesconto = this.valor - (this.valor * (this.desconto/100)) ;
	}

	public double getDesconto() {
		return desconto;
	}

	public double getValorComDesconto() {
		return valorComDesconto;
	}


}
