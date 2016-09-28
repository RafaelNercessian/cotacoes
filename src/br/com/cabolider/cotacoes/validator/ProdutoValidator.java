package br.com.cabolider.cotacoes.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.cabolider.cotacoes.dao.ProdutoDao;
import br.com.cabolider.cotacoes.modelo.Produto;

@Component
public class ProdutoValidator implements Validator {
	@Autowired
	private ProdutoDao dao;

	public boolean supports(Class<?> clazz) {
		return Produto.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Produto produto = (Produto) target;
		ValidationUtils.rejectIfEmptyOrWhitespace((Errors) errors,
				(String) "codigo", (String) "codigo",
				(String) "Por favor, insira o código!");
		ValidationUtils.rejectIfEmptyOrWhitespace((Errors) errors,
				(String) "descricao", (String) "descricao",
				(String) "Por favor insira a descrição!");
		ValidationUtils.rejectIfEmptyOrWhitespace((Errors) errors,
				(String) "precoAte1000", (String) "precoAte1000",
				(String) "Por favor, insira o preço até 1.000m!");
		ValidationUtils.rejectIfEmptyOrWhitespace((Errors) errors,
				(String) "precoAte3000", (String) "precoAte3000",
				(String) "Por favor, insira o preço até 3.000m!");
		ValidationUtils.rejectIfEmptyOrWhitespace((Errors) errors,
				(String) "precoAcima3000", (String) "precoAcima3000",
				(String) "Por favor, insira o preço acima de 3.000m!");
		ValidationUtils.rejectIfEmptyOrWhitespace((Errors) errors,
				(String) "ipi", (String) "ipi",
				(String) "Por favor, insira o IPI!");
		ValidationUtils.rejectIfEmptyOrWhitespace((Errors) errors,
				(String) "cor", (String) "cor",
				(String) "Por favor, insira a cor!");
		Produto busca = this.dao.busca(produto.getCodigo());
		if (busca != null) {
			errors.rejectValue("codigo", "codigo",
					"O c\u00f3digo inserido j\u00e1 est\u00e1 cadastrado!");
		}
	}
}