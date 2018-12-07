package com.planner.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.planner.treina.entity.Tarefas;
import com.planner.treina.entity.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -8699654255891466192L;

	@Inject
	private EntityManager em;
	
	private String title;

	@PostConstruct
	private void init() {
		this.title = "Login - do bean";
		select();
	}
	
	private void select() {
		
		List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getUsuario());
		}
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
