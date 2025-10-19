package com.renatodematos.uninterbe.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private LocalDate dataEntrega;
    
    @Column(nullable = false)
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
