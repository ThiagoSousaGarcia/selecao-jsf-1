package com.planner.treina.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.planner.session.SessionManager;
import com.planner.treina.entity.Usuario;

public class UsuarioDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
	private EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();
	
	public Usuario getUsuario(String nomeUsuario, String senha) {
		try {
            Usuario usuario = (Usuario) em
                    .createQuery(
                                "SELECT u from Usuario u where u.login = :name and u.senha = :senha")
                    .setParameter("name", nomeUsuario)
                    .setParameter("senha", senha).getSingleResult();

         return usuario;
         
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Usuario getUsuarioByLogin(String login) {
		try {
            Usuario usuario = (Usuario) em
                    .createQuery(
                                "SELECT u from Usuario u where u.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();

         return usuario;
         
		}catch(NoResultException e){
			return null;
		}
	}
	
	public String getSenhaByLogin(String login) {
		
		try {
			String senha = (String)em.createQuery(
												"Select u.senha from Usuario u where u.login = :login")
					.setParameter("login",login)
					.getSingleResult();
			return senha;
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
	
	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    	return null;
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
			SessionManager.setValueInAplicationMap("user", usuario);
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
			Usuario user = (Usuario) SessionManager.getValueFromAplicationMap("user");
			String userLogado = user.getLogin();
			
			return userLogado;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean atualizarSenha(Usuario usuario, String senha) {
		try {
			tx.begin();
			senha = MD5(senha);
			usuario.setSenha(senha);
			em.merge(usuario);
			tx.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Usuario> getUsuariosCadastrados(){
		List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
		
		return usuarios;
	}
}
