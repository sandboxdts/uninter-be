package com.renatodematos.uninterbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renatodematos.uninterbe.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
