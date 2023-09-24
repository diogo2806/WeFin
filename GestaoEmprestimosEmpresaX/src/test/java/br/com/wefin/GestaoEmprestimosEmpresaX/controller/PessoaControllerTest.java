package br.com.wefin.GestaoEmprestimosEmpresaX.controller;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
public class PessoaControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @Autowired
    private PessoaController pessoaController;

    @Test
    public void testListarPessoas() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();

        List<PessoaDTO> pessoas = Arrays.asList(new PessoaDTO(), new PessoaDTO());
        when(pessoaService.findAll()).thenReturn(pessoas);

        mockMvc.perform(get("/api/pessoas/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}]"));
    }
}
