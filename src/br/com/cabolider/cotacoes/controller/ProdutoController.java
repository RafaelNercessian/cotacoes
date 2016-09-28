package br.com.cabolider.cotacoes.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cabolider.cotacoes.dao.ProdutoDao;
import br.com.cabolider.cotacoes.modelo.Produto;
import br.com.cabolider.cotacoes.modelo.ProdutoImpressao;

@Controller
public class ProdutoController {
	@Autowired
	private ProdutoDao dao;
	private List<Produto> lista = new ArrayList<Produto>();
	private Map<String, Object> parametros = new HashMap<String, Object>();
	private static int i = 1;

	@RequestMapping(value = { "/busca" })
	public String busca(String codigo, HttpSession session, Model model)
			throws Exception {
		Produto produto = this.dao.busca(codigo.substring(1, codigo.length()));
		if (produto == null) {
			model.addAttribute("msg",
					(Object) "O produto buscado n\u00e3o existe!");
			session.setAttribute("lista", this.lista);
			return "index";
		}
		if (!this.lista.contains((Object) produto)) {
			this.lista.add(produto);
		}
		session.setAttribute("lista", this.lista);
		return "index";
	}

	@RequestMapping(value = { "/deleta" })
	public String deleta(String codigo, Model model) {
		Produto produto = this.dao.busca(codigo);
		ProdutoImpressao produtoImpressao = new ProdutoImpressao();
		produtoImpressao.setCodigo(produto.getCodigo());
		this.lista.remove(produto);
		model.addAttribute("lista", this.lista);
		return "index";
	}

	@RequestMapping(value = { "/geraCotacao" })
	public void geraCotacao(HttpServletResponse response,
			@RequestParam(value = "pintura") String pintura,
			@RequestParam(value = "corte") String corte, Model model)
			throws Exception {
		String pinturaSub = pintura.substring(1, 4);
		String corteSub = corte.substring(1, 4);
		List<ProdutoImpressao> listaImpressao = this
				.verificaSeHaCorteEOuPintura(pinturaSub, corteSub);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
				listaImpressao);
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("br/com/cabolider/cotacoes/cotacao.jrxml");
		JasperReport report = JasperCompileManager
				.compileReport((InputStream) in);
		JasperPrint impressao = JasperFillManager.fillReport(
				(JasperReport) report, this.parametros,
				(JRDataSource) dataSource);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + i
				+ "_cabolider_cotacao" + ".pdf");
		++i;
		ServletOutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream((JasperPrint) impressao,
				(OutputStream) outStream);
	}

	private List<ProdutoImpressao> verificaSeHaCorteEOuPintura(
			String pinturaSub, String corteSub) {
		List<ProdutoImpressao> listaImpressao = this
				.insereProdutoNoProdutoImpressao();
		if (pinturaSub.equals("Sim") && corteSub.equals("Sim")) {
			this.verificaSeHaPinturaECorte(listaImpressao);
		} else if (pinturaSub.equals("Sim") || corteSub.equals("Sim")) {
			this.verificaSeHaPinturaOuCorte(listaImpressao);
		}
		return listaImpressao;
	}

	private void verificaSeHaPinturaECorte(List<ProdutoImpressao> listaImpressao) {
		for (ProdutoImpressao produto : listaImpressao) {
			String precoAte1000 = String.valueOf(produto.getPrecoAte1000()
					.substring(3, 4))
					+ "."
					+ produto.getPrecoAte1000().substring(5,
							produto.getPrecoAte1000().length());
			String precoAte3000 = String.valueOf(produto.getPrecoAte3000()
					.substring(3, 4))
					+ "."
					+ produto.getPrecoAte3000().substring(5,
							produto.getPrecoAte3000().length());
			String precoAcima3000 = String.valueOf(produto.getPrecoAcima3000()
					.substring(3, 4))
					+ "."
					+ produto.getPrecoAcima3000().substring(5,
							produto.getPrecoAcima3000().length());
			DecimalFormat df = new DecimalFormat("#.###");
			double precoAte1000Double = Double.parseDouble(precoAte1000) * 1.21;
			double precoAte3000Double = Double.parseDouble(precoAte3000) * 1.21;
			double precoAcima3000Double = Double.parseDouble(precoAcima3000) * 1.21;
			String precoAte1000String = df.format(precoAte1000Double);
			String precoAte3000String = df.format(precoAte3000Double);
			String precoAcima3000String = df.format(precoAcima3000Double);
			produto.setPrecoAte1000("R$ " + precoAte1000String);
			produto.setPrecoAte3000("R$ " + precoAte3000String);
			produto.setPrecoAcima3000("R$ " + precoAcima3000String);
		}
	}

	private void verificaSeHaPinturaOuCorte(
			List<ProdutoImpressao> listaImpressao) {
		for (ProdutoImpressao produto : listaImpressao) {
			String precoAte1000 = String.valueOf(produto.getPrecoAte1000()
					.substring(3, 4))
					+ "."
					+ produto.getPrecoAte1000().substring(5,
							produto.getPrecoAte1000().length());
			String precoAte3000 = String.valueOf(produto.getPrecoAte3000()
					.substring(3, 4))
					+ "."
					+ produto.getPrecoAte3000().substring(5,
							produto.getPrecoAte3000().length());
			String precoAcima3000 = String.valueOf(produto.getPrecoAcima3000()
					.substring(3, 4))
					+ "."
					+ produto.getPrecoAcima3000().substring(5,
							produto.getPrecoAcima3000().length());
			DecimalFormat df = new DecimalFormat("#.###");
			double precoAte1000Double = Double.parseDouble(precoAte1000) * 1.1;
			double precoAte3000Double = Double.parseDouble(precoAte3000) * 1.1;
			double precoAcima3000Double = Double.parseDouble(precoAcima3000) * 1.1;
			String precoAte1000String = df.format(precoAte1000Double);
			String precoAte3000String = df.format(precoAte3000Double);
			String precoAcima3000String = df.format(precoAcima3000Double);
			produto.setPrecoAte1000("R$ " + precoAte1000String);
			produto.setPrecoAte3000("R$ " + precoAte3000String);
			produto.setPrecoAcima3000("R$ " + precoAcima3000String);
		}
	}

	private List<ProdutoImpressao> insereProdutoNoProdutoImpressao() {
		ArrayList<ProdutoImpressao> listaImpressao = new ArrayList<ProdutoImpressao>();
		for (Produto produto : this.lista) {
			ProdutoImpressao produtoImpressao = new ProdutoImpressao();
			produtoImpressao.setPrecoAte1000(produto.getPrecoAte1000());
			produtoImpressao.setPrecoAcima3000(produto.getPrecoAcima3000());
			produtoImpressao.setPrecoAte3000(produto.getPrecoAte3000());
			produtoImpressao.setCor(produto.getCor());
			produtoImpressao.setCodigo(produto.getCodigo());
			produtoImpressao.setDescricao(produto.getDescricao());
			produtoImpressao.setIpi(produto.getIpi());
			listaImpressao.add(produtoImpressao);
		}
		return listaImpressao;
	}
}
