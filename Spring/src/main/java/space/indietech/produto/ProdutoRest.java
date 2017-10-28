package space.indietech.produto;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoRest {

	private ProdutoService produtoService;

	@Autowired
	public ProdutoRest(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(this.produtoService.getLista());
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> get(@PathVariable("codigo") long codigo) {
		try {
			return ResponseEntity.ok(this.produtoService.getProdutoPeloCodigo(codigo));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping
	public ResponseEntity<Produto> setNewProduto(@RequestBody Produto produto) {
		this.produtoService.setNewProduto(produto);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{codigo}/{desconto}")
	public ResponseEntity<ProdutoComDesconto> getProdutoDesconto(@PathVariable("codigo") long codigo,
			@PathVariable("desconto") double desconto) {
		try {
			return ResponseEntity.ok(produtoService.calculadoraDesconto(codigo, desconto));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Produto> delete(@PathVariable("codigo") long codigo) {
		this.produtoService.deleteProduto(codigo);
		return ResponseEntity.noContent().build();
	}
}
