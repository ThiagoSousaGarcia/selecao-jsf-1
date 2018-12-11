package com.planner.treina.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.planner.treina.entity.Tarefas;
import com.planner.treina.entity.Usuario;

public class TarefaDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
	private EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();
	
	public List<Tarefas> getTarefasByUser(Usuario usuario) {
		try {
			List<Tarefas> tarefas = (List) em.createQuery("Select u from Tarefas u where id_usuario = :usuario order by u.dtcriacao")
					.setParameter("usuario",usuario.getId())
					.getResultList();
			return tarefas;	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean inserirTarefa(String titulo, String descricao, int prioridade, Usuario usuario, Date data) {
		try {
			tx.begin();
			Tarefas tarefa = new Tarefas();
			tarefa.setTitulo(titulo);
			tarefa.setDescricao(descricao);
			tarefa.setPrioridade(prioridade);
			tarefa.setUsuario(usuario);
			tarefa.setDtcriacao(data);
			em.persist(tarefa);
			tx.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			em.close();
			emf.close();
		}
	}
	
}
