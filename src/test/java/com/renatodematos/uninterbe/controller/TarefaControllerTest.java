package com.renatodematos.uninterbe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renatodematos.uninterbe.controller.TarefaController;
import com.renatodematos.uninterbe.model.Tarefa;
import com.renatodematos.uninterbe.repository.TarefaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TarefaController.class)
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TarefaRepository tarefaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Tarefa tarefa;

    @BeforeEach
    void setUp() {
        tarefa = new Tarefa("Teste API", LocalDate.of(2024, 12, 31), "RU5233639");
        tarefa.setId(1L);
    }

    @Test
    void criarTarefa_DeveRetornar201E_TarefaCriada() throws Exception {
        Tarefa tarefaNova = new Tarefa("Teste API", LocalDate.of(2024, 12, 31), "RU5233639");
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        mockMvc.perform(post("/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaNova)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Teste API"))
                .andExpect(jsonPath("$.responsavel").value("RU5233639"))
                .andExpect(jsonPath("$.dataEntrega").value("2024-12-31"))
                .andExpect(jsonPath("$.id").value(1));

        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void listarTarefas_DeveRetornar200E_ListaDeTarefas() throws Exception {
        List<Tarefa> tarefas = Arrays.asList(tarefa);
        when(tarefaRepository.findAll()).thenReturn(tarefas);

        mockMvc.perform(get("/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nome").value("Teste API"))
                .andExpect(jsonPath("$[0].responsavel").value("RU5233639"))
                .andExpect(jsonPath("$[0].dataEntrega").value("2024-12-31"));

        verify(tarefaRepository, times(1)).findAll();
    }

    @Test
    void buscarTarefaPorId_QuandoExiste_DeveRetornarTarefa() throws Exception {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        mockMvc.perform(get("/tarefas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Teste API"))
                .andExpect(jsonPath("$.responsavel").value("RU5233639"))
                .andExpect(jsonPath("$.dataEntrega").value("2024-12-31"))
                .andExpect(jsonPath("$.id").value(1));

        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    void buscarTarefaPorId_QuandoNaoExiste_DeveRetornar404() throws Exception {
        when(tarefaRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/tarefas/{id}", 999L))
                .andExpect(status().isNotFound());

        verify(tarefaRepository, times(1)).findById(999L);
    }

    @Test
    void atualizarTarefa_QuandoExiste_DeveRetornarTarefaAtualizada() throws Exception {
        Tarefa tarefaAtualizada = new Tarefa("Tarefa Atualizada", LocalDate.of(2024, 12, 25), "RU9999999");
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        mockMvc.perform(put("/tarefas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaAtualizada)))
                .andExpect(status().isOk());

        verify(tarefaRepository, times(1)).findById(1L);
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void atualizarTarefa_QuandoNaoExiste_DeveRetornar404() throws Exception {
        Tarefa tarefaAtualizada = new Tarefa("Tarefa Atualizada", LocalDate.of(2024, 12, 25), "RU9999999");
        when(tarefaRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/tarefas/{id}", 999L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaAtualizada)))
                .andExpect(status().isNotFound());

        verify(tarefaRepository, times(1)).findById(999L);
        verify(tarefaRepository, never()).save(any(Tarefa.class));
    }

    @Test
    void removerTarefa_QuandoExiste_DeveRetornar204() throws Exception {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        mockMvc.perform(delete("/tarefas/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(tarefaRepository, times(1)).findById(1L);
        verify(tarefaRepository, times(1)).deleteById(1L);
    }

    @Test
    void removerTarefa_QuandoNaoExiste_DeveRetornar404() throws Exception {
        when(tarefaRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/tarefas/{id}", 999L))
                .andExpect(status().isNotFound());

        verify(tarefaRepository, times(1)).findById(999L);
        verify(tarefaRepository, never()).deleteById(anyLong());
    }
}