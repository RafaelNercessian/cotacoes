package br.com.cabolider.cotacoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error",
					(Object) "Usuário ou senha inválidos!");
		}
		if (logout != null) {
			model.addObject("msg",
					(Object) "Usuário deslogado com sucesso!");
		}
		model.setViewName("login");
		return model;
	}
}