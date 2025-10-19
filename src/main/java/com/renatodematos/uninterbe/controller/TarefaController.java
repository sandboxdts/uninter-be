package com.renatodematos.uninterbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renatodematos.uninterbe.model.Tarefa;
import com.renatodematos.uninterbe.repository.TarefaRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    // POST /tarefas - Criar nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }
    
    // GET /tarefas - Listar todas as tarefas
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return ResponseEntity.ok(tarefas);
    }
    
    // GET /tarefas/{id} - Buscar tarefa específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable("id") Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        
        if (tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // PUT /tarefas/{id} - Atualizar tarefa existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable("id") Long id, @Valid @RequestBody Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        
        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            tarefa.setNome(tarefaAtualizada.getNome());
            tarefa.setDataEntrega(tarefaAtualizada.getDataEntrega());
            tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
            
            Tarefa tarefaSalva = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE /tarefas/{id} - Remover tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable("id") Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        
        if (tarefa.isPresent()) {
            tarefaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
