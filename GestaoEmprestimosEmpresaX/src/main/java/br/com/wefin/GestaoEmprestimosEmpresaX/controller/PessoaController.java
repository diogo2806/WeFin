package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.wefin.GestaoEmprestimosEmpresaX.service.PessoaService;
import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;  // Importe esta classe
import java.util.List;  // Importe esta classe

@Controller
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/listarPessoas")
    public String listarPessoas(Model model) {
        List<PessoaDTO> pessoas = pessoaService.findAll();
        model.addAttribute("pessoas", pessoas);
        return "listarPessoas";
    }

    @PostMapping("/criarPessoa")
    public String criarPessoa(@ModelAttribute PessoaDTO pessoaDTO) {
        // Aqui você deve implementar a lógica para criar uma nova pessoa
        // Você pode chamar o serviço para salvar a pessoa no banco de dados
        pessoaService.save(pessoaDTO);

        // Redirecione para a página de listagem de pessoas ou outra página apropriada
        return "redirect:/listarPessoas";
    }

}