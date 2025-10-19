package com.renatodematos.uninterbe.model;

import org.junit.jupiter.api.Test;

import com.renatodematos.uninterbe.model.Tarefa;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TarefaTest {

    private Tarefa tarefa;

    @BeforeEach
    void setUp() {
        tarefa = new Tarefa();
    }

    @Test
    void construtorVazio_DeveCriarTarefaVazia() {
        assertThat(tarefa).isNotNull();
        assertThat(tarefa.getId()).isNull();
        assertThat(tarefa.getNome()).isNull();
        assertThat(tarefa.getDataEntrega()).isNull();
        assertThat(tarefa.getResponsavel()).isNull();
    }

    @Test
    void construtorCompleto_DeveCriarTarefaComDados() {
        String nome = "Implementar API REST";
        LocalDate dataEntrega = LocalDate.of(2024, 12, 31);
        String responsavel = "RU5233639";

        Tarefa tarefaCompleta = new Tarefa(nome, dataEntrega, responsavel);

        assertThat(tarefaCompleta).isNotNull();
        assertThat(tarefaCompleta.getNome()).isEqualTo(nome);
        assertThat(tarefaCompleta.getDataEntrega()).isEqualTo(dataEntrega);
        assertThat(tarefaCompleta.getResponsavel()).isEqualTo(responsavel);
        assertThat(tarefaCompleta.getId()).isNull();
    }

    @Test
    void settersEGetters_DeveFuncionarCorretamente() {
        Long id = 1L;
        String nome = "Teste de API";
        LocalDate dataEntrega = LocalDate.of(2024, 12, 25);
        String responsavel = "RU1234567";

        tarefa.setId(id);
        tarefa.setNome(nome);
        tarefa.setDataEntrega(dataEntrega);
        tarefa.setResponsavel(responsavel);

        assertThat(tarefa.getId()).isEqualTo(id);
        assertThat(tarefa.getNome()).isEqualTo(nome);
        assertThat(tarefa.getDataEntrega()).isEqualTo(dataEntrega);
        assertThat(tarefa.getResponsavel()).isEqualTo(responsavel);
    }

    @Test
    void setNome_ComValorValido_DeveAtualizarNome() {
        String nomeOriginal = "Tarefa Original";
        String nomeNovo = "Tarefa Atualizada";

        tarefa.setNome(nomeOriginal);
        assertThat(tarefa.getNome()).isEqualTo(nomeOriginal);

        tarefa.setNome(nomeNovo);
        assertThat(tarefa.getNome()).isEqualTo(nomeNovo);
    }

    @Test
    void setDataEntrega_ComDataValida_DeveAtualizarData() {
        LocalDate dataOriginal = LocalDate.of(2024, 12, 1);
        LocalDate dataNova = LocalDate.of(2024, 12, 15);

        tarefa.setDataEntrega(dataOriginal);
        assertThat(tarefa.getDataEntrega()).isEqualTo(dataOriginal);

        tarefa.setDataEntrega(dataNova);
        assertThat(tarefa.getDataEntrega()).isEqualTo(dataNova);
    }

    @Test
    void setResponsavel_ComValorValido_DeveAtualizarResponsavel() {
        String responsavelOriginal = "RU1111111";
        String responsavelNovo = "RU2222222";

        tarefa.setResponsavel(responsavelOriginal);
        assertThat(tarefa.getResponsavel()).isEqualTo(responsavelOriginal);

        tarefa.setResponsavel(responsavelNovo);
        assertThat(tarefa.getResponsavel()).isEqualTo(responsavelNovo);
    }

    @Test
    void setId_ComValorValido_DeveAtualizarId() {
        Long idOriginal = 1L;
        Long idNovo = 2L;

        tarefa.setId(idOriginal);
        assertThat(tarefa.getId()).isEqualTo(idOriginal);

        tarefa.setId(idNovo);
        assertThat(tarefa.getId()).isEqualTo(idNovo);
    }
}