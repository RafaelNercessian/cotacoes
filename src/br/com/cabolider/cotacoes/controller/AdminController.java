package br.com.cabolider.cotacoes.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cabolider.cotacoes.dao.ProdutoDao;
import br.com.cabolider.cotacoes.modelo.Produto;
import br.com.cabolider.cotacoes.validator.ProdutoValidator;

@Controller
@RequestMapping(value={"/admin"})
public class AdminController {
    @Autowired
    private ProdutoDao dao;
    @Autowired
    private ProdutoValidator produtoValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator((Validator)this.produtoValidator);
    }

    @RequestMapping(method={RequestMethod.GET})
    public String admin() {
        return "admin";
    }

    @RequestMapping(value={"/produto"})
    public String produto(Model model) {
        Produto produto = new Produto();
        model.addAttribute((Object)produto);
        return "adicionarProduto";
    }

    @RequestMapping(value={"/adicionaProduto"}, method={RequestMethod.POST})
    public String adicionaProduto(HttpServletRequest request, @ModelAttribute(value="produto") @Validated Produto produto, BindingResult result, RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            return "adicionarProduto";
        }
        redirect.addFlashAttribute("mensagem", (Object)"Produto adicionado com sucesso!");
        this.multiplicaFatorIPI(produto);
        this.dao.adiciona(produto);
        return "redirect:/admin/produto";
    }

    public void multiplicaFatorIPI(Produto produto) {
        String ipi = produto.getIpi().substring(0, 1);
        if (!ipi.equals("0")) {
            String precoAte1000 = String.valueOf(produto.getPrecoAte1000().substring(3, 4)) + "." + produto.getPrecoAte1000().substring(5, produto.getPrecoAte1000().length());
            String precoAte3000 = String.valueOf(produto.getPrecoAte3000().substring(3, 4)) + "." + produto.getPrecoAte3000().substring(5, produto.getPrecoAte3000().length());
            String precoAcima3000 = String.valueOf(produto.getPrecoAcima3000().substring(3, 4)) + "." + produto.getPrecoAcima3000().substring(5, produto.getPrecoAcima3000().length());
            double ipiDouble = Double.parseDouble(ipi);
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.UP);
            df.setMinimumFractionDigits(3);
            double fatorIpi = ipiDouble / 100.0 + 1.0;
            double precoAte1000Double = Double.parseDouble(precoAte1000) * fatorIpi;
            double precoAte3000Double = Double.parseDouble(precoAte3000) * fatorIpi;
            double precoAcima3000Double = Double.parseDouble(precoAcima3000) * fatorIpi;
            String precoAte1000String = df.format(precoAte1000Double);
            String precoAte3000String = df.format(precoAte3000Double);
            String precoAcima3000String = df.format(precoAcima3000Double);
            produto.setPrecoAte1000("R$ " + precoAte1000String);
            produto.setPrecoAte3000("R$ " + precoAte3000String);
            produto.setPrecoAcima3000("R$ " + precoAcima3000String);
        }
    }
}