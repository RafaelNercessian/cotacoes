package br.com.cabolider.cotacoes.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cabolider.cotacoes.dao.ProdutoDao;
import br.com.cabolider.cotacoes.modelo.Produto;

@Controller
@RequestMapping(value={"/admin"})
public class EdicaoController {
    @Autowired
    private ProdutoDao dao;
    private String precoAte1000Formatado;
    private String precoAte3000Formatado;
    private String precoAcima3000Formatado;

    @RequestMapping(value={"/buscaEdicao"}, method={RequestMethod.POST})
    public String buscaEdicao(@ModelAttribute(value="produto") Produto produto, Model model) {
        Produto produtoBuscado = this.dao.busca(produto.getCodigo());
        if (produtoBuscado == null) {
            model.addAttribute("msg", (Object)"O produto a ser editado n\u00e3o existe!");
            return "editarProduto";
        }
        model.addAttribute("produto", (Object)produtoBuscado);
        return "editarProduto";
    }

    @RequestMapping(value={"/editarProduto"})
    public String editarProduto(Model model) {
        Produto produto = new Produto();
        model.addAttribute("produto", (Object)produto);
        return "editarProduto";
    }

    @RequestMapping(value={"/alteraProduto"}, method={RequestMethod.POST})
    public String alteraProduto(@ModelAttribute(value="produto") Produto produto, Model model) {
        Produto produtoBuscado = this.dao.busca(produto.getCodigo());
        if (!(produtoBuscado.getPrecoAte1000().equals(produto.getPrecoAte1000()) || produtoBuscado.getPrecoAte3000().equals(produto.getPrecoAte3000()) || produtoBuscado.getPrecoAcima3000().equals(produto.getPrecoAcima3000()))) {
            AdminController controller = new AdminController();
            controller.multiplicaFatorIPI(produto);
        }
        this.dao.altera(produto);
        model.addAttribute("msg", (Object)"O produto foi editado com sucesso!");
        return "editarProduto";
    }

    @RequestMapping(value={"/editarTudo"}, method={RequestMethod.GET})
    public String editarTudo() {
        return "editarTudo";
    }

    @RequestMapping(value={"/editarTudo"}, method={RequestMethod.POST})
    public String editarTudoPost(@ModelAttribute(value="valor") String valor, Model model) {
        double valorDouble = Double.parseDouble(valor);
        if (valorDouble <= 0.0) {
            model.addAttribute("msg", (Object)"N\u00e3o \u00e9 poss\u00edvel inserir um n\u00famero menor que 0 (zero)!");
            return "editarTudo";
        }
        List<Produto> listaTudo = this.dao.listaTudo();
        double fator = valorDouble / 100.0 + 1.0;
        DecimalFormat df = new DecimalFormat("#.###");
        df.setMinimumFractionDigits(3);
        for (Produto produto : listaTudo) {
            String precoAte1000 = String.valueOf(produto.getPrecoAte1000().substring(3, 4)) + "." + produto.getPrecoAte1000().substring(5, produto.getPrecoAte1000().length());
            String precoAte3000 = String.valueOf(produto.getPrecoAte3000().substring(3, 4)) + "." + produto.getPrecoAte3000().substring(5, produto.getPrecoAte1000().length());
            String precoAcima3000 = String.valueOf(produto.getPrecoAcima3000().substring(3, 4)) + "." + produto.getPrecoAcima3000().substring(5, produto.getPrecoAte1000().length());
            this.verificaSeTemIPIeMultiplicaFator(fator, df, produto, precoAte1000, precoAte3000, precoAcima3000);
            produto.setPrecoAte1000("R$ " + this.precoAte1000Formatado);
            produto.setPrecoAte3000("R$ " + this.precoAte3000Formatado);
            produto.setPrecoAcima3000("R$ " + this.precoAcima3000Formatado);
            this.dao.altera(produto);
        }
        model.addAttribute("msg", (Object)"Valores alterados com sucesso!");
        return "editarTudo";
    }

    private void verificaSeTemIPIeMultiplicaFator(double fator, DecimalFormat df, Produto produto, String precoAte1000, String precoAte3000, String precoAcima3000) {
        double precoAte1000Double = Double.parseDouble(precoAte1000);
        double precoAte3000Double = Double.parseDouble(precoAte3000);
        double precoAcima3000Double = Double.parseDouble(precoAcima3000);
        if (!produto.getIpi().equals("0")) {
            String ipi = produto.getIpi().substring(0, 1);
            double ipiDouble = Double.parseDouble(ipi);
            double fatorIpi = ipiDouble / 100.0 + 1.0;
            double precoAte1000SemIpi = precoAte1000Double / fatorIpi;
            double precoAte3000SemIpi = precoAte3000Double / fatorIpi;
            double precoAcima3000SemIpi = precoAcima3000Double / fatorIpi;
            double precoAte1000ComFator = precoAte1000SemIpi * fator * fatorIpi;
            this.precoAte1000Formatado = df.format(precoAte1000ComFator);
            double precoAte3000ComFator = precoAte3000SemIpi * fator * fatorIpi;
            this.precoAte3000Formatado = df.format(precoAte3000ComFator);
            double precoAcima3000ComFator = precoAcima3000SemIpi * fator * fatorIpi;
            this.precoAcima3000Formatado = df.format(precoAcima3000ComFator);
        } else {
            double precoAte1000ComFator = precoAte1000Double * fator;
            this.precoAte1000Formatado = df.format(precoAte1000ComFator);
            double precoAte3000ComFator = precoAte3000Double * fator;
            this.precoAte3000Formatado = df.format(precoAte3000ComFator);
            double precoAcima3000ComFator = precoAcima3000Double * fator;
            this.precoAcima3000Formatado = df.format(precoAcima3000ComFator);
        }
    }
}