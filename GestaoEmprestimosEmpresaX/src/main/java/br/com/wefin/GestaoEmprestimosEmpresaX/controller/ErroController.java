package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErroController implements ErrorController {

    public String handleError(HttpServletRequest request) {
        // Lógica para lidar com erros e redirecionar para a página de erro personalizada
        return "error"; // Certifique-se de que você tenha uma página "error.html" em seus recursos (resources/templates).
    }

    //@Override
    public String getErrorPath() {
        return "/error"; // O caminho para a página de erro personalizada
    }

}
