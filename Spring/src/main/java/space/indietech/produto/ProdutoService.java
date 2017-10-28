package space.indietech.produto;
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoService {

	private ProdutoDao pDao;

	@Autowired
	public ProdutoService(ProdutoDao pDao) {
		this.pDao = pDao;
	}

	public List<Produto> getLista() {
		return pDao.getListaProdutos();
	}

	public Produto getProdutoPeloCodigo(long codigo) {
		List<Produto> lista = pDao.getListaProdutos();
		for (Produto produto : lista) {
			if (produto.getCodigo() == codigo) {
				return produto;
			}
		}
		throw new RuntimeException();
	}

	public void setNewProduto(Produto produto) {
		List<Produto> lista = pDao.getListaProdutos();
		if (lista.size() == 0) {
			produto.setCodigo(1);
		} else {
			Produto ultimoProdutoDaLista = lista.get(lista.size() - 1);
			produto.setCodigo(ultimoProdutoDaLista.getCodigo() + 1);
		}
		this.pDao.adicionaProduto(produto);
	}

	public void deleteProduto(long codigo) {
		this.pDao.delete(codigo);
	}

	public ProdutoComDesconto calculadoraDesconto(long codigo, double desconto) {
		Produto produto = this.getProdutoPeloCodigo(codigo);
		ProdutoComDesconto produtoDesconto = new ProdutoComDesconto(codigo, desconto, produto.getNome(),
				produto.getValor());
		return produtoDesconto;
	}
}
