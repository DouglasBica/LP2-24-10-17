package space.indietech.produto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDao {

	private EntityManager em;

	@Autowired
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void adicionaProduto(Produto produto) {
		em.persist(produto);
	}

	public List<Produto> getListaProdutos() {
		Long codigo = 20L;
		em.find(Produto.class, codigo);
		Query Query = em.createQuery("from Produto");
		List<Produto> produto = Query.getResultList();
		return produto;
	}

	public void delete(Long codigo) {
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto = em.find(Produto.class, codigo);
		em.remove(produto);
		// tenho que criar o produto para depois eu excluir
	}
}
