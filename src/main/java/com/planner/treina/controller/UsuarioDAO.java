package com.planner.treina.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.planner.treina.entity.Usuario;

public class UsuarioDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
	private EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();
	
	public Usuario getUsuario(String nomeUsuario, String senha) {
		try {
            Usuario usuario = (Usuario) em
                    .createQuery(
                                "SELECT u from Usuario u where u.usuario = :name and u.senha = :senha")
                    .setParameter("name", nomeUsuario)
                    .setParameter("senha", senha).getSingleResult();

         return usuario;
         
		}catch(NoResultException e){
			return null;
		}
	}
	
	
	
	public boolean inserirUsuario(Usuario usuario) {
		try {
			tx.begin();
			em.persist(usuario);
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
	
	
	public boolean deletarUsuario(int id) {
		try {
			Usuario usuario = (Usuario) em.createQuery(
					"SELECT u "
					+ "from "
					+ "Usuario u "
					+ "where u.id = :id ")
					.setParameter("id",id).getSingleResult();
			
			tx.begin();
			em.remove(usuario);
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
	
	public boolean logarUsuario(Usuario usuario) {
		try {
			tx.begin();
			usuario.setLogged(true);
			em.merge(usuario);
			tx.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			em.close();
			emf.close();
		}
		
	}
	
	public boolean deslogarUsuario(Usuario usuario) {
		try {
			tx.begin();
			usuario.setLogged(false);
			em.merge(usuario);
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
	
	public String usuarioLogado() {
		try {
			String user = (String) em.createQuery("Select u.login "
					+ "from "
					+ "Usuario u "
					+ "where u.isLogged = 1").getSingleResult();
			return user;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
