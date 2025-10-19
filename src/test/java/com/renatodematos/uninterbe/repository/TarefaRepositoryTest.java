package com.renatodematos.uninterbe.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.renatodematos.uninterbe.model.Tarefa;
import com.renatodematos.uninterbe.repository.TarefaRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository tarefaRepository;

    @BeforeEach
    void setUp() {
        tarefaRepository.deleteAll();
    }

    @Test
    void salvarTarefa_DevePersistirCorretamente() {
        Tarefa tarefa = new Tarefa("Implementar API REST", LocalDate.of(2024, 12, 31), "RU5233639");
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        assertThat(tarefaSalva.getId()).isNotNull();
        assertThat(tarefaSalva.getNome()).isEqualTo("Implementar API REST");
        assertThat(tarefaSalva.getResponsavel()).isEqualTo("RU5233639");
        assertThat(tarefaSalva.getDataEntrega()).isEqualTo(LocalDate.of(2024, 12, 31));
    }

    @Test
    void buscarTarefaPorId_QuandoExiste_DeveRetornarTarefa() {
        Tarefa tarefa = new Tarefa("Implementar API REST", LocalDate.of(2024, 12, 31), "RU5233639");
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        Optional<Tarefa> tarefaEncontrada = tarefaRepository.findById(tarefaSalva.getId());
        assertThat(tarefaEncontrada).isPresent();
        assertThat(tarefaEncontrada.get().getNome()).isEqualTo("Implementar API REST");
    }

    @Test
    void buscarTarefaPorId_QuandoNaoExiste_DeveRetornarVazio() {
        Optional<Tarefa> tarefaEncontrada = tarefaRepository.findById(999L);
        assertThat(tarefaEncontrada).isEmpty();
    }

    @Test
    void verificarExistenciaPorId_QuandoExiste_DeveRetornarTrue() {
        Tarefa tarefa = new Tarefa("Implementar API REST", LocalDate.of(2024, 12, 31), "RU5233639");
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        boolean existe = tarefaRepository.existsById(tarefaSalva.getId());
        assertThat(existe).isTrue();
    }

    @Test
    void verificarExistenciaPorId_QuandoNaoExiste_DeveRetornarFalse() {
        boolean existe = tarefaRepository.existsById(999L);
        assertThat(existe).isFalse();
    }
}