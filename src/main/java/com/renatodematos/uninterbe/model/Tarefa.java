package com.renatodematos.uninterbe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @Column(nullable = false)
    @NotNull(message = "Data de entrega é obrigatória")
    private LocalDate dataEntrega;
    
    @Column(nullable = false)
    @NotBlank(message = "Responsável é obrigatório")
    private String responsavel;
    
    // Construtor vazio
    public Tarefa() {
    }
    
    // Construtor completo (sem ID)
    public Tarefa(String nome, LocalDate dataEntrega, String responsavel) {
        this.nome = nome;
        this.dataEntrega = dataEntrega;
        this.responsavel = responsavel;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public LocalDate getDataEntrega() {
        return dataEntrega;
    }
    
    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    
    public String getResponsavel() {
        return responsavel;
    }
    
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
