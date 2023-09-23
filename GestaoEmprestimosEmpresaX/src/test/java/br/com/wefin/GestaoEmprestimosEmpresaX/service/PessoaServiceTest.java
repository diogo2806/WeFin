package br.com.wefin.GestaoEmprestimosEmpresaX.service;

import br.com.wefin.GestaoEmprestimosEmpresaX.dto.PessoaDTO;
import br.com.wefin.GestaoEmprestimosEmpresaX.model.Pessoa;
import br.com.wefin.GestaoEmprestimosEmpresaX.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PessoaServiceTest {

    @InjectMocks
    PessoaService pessoaService;

    @Mock
    PessoaRepository pessoaRepository;

    public PessoaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<PessoaDTO> result = pessoaService.findAll();

        assertEquals(2, result.size());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        PessoaDTO result = pessoaService.findById(1L);

        assertEquals(1L, result.getId());
        verify(pessoaRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(1L);
        dto.setIdentificador("IdentificadorValido");  // Adicionado para evitar NullPointerException

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO result = pessoaService.save(dto);

        assertEquals(1L, result.getId());
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    public void testDelete() {
        pessoaService.delete(1L);
        verify(pessoaRepository, times(1)).deleteById(1L);
    }
}
