package com.planner.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.planner.treina.controller.UsuarioDAO;
import com.planner.treina.entity.Usuario;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = -8699654255891466192L;

	private String title;
	private String usuarioLogado;
	private UsuarioDAO userDao = new UsuarioDAO();
	private List<Usuario> users = new ArrayList<Usuario>();
	
	
	@PostConstruct
	private void init() {
		this.title = "Teste Prático PLANNER SISTEMAS";
		this.usuarioLogado = userDao.usuarioLogado();
		this.users = userDao.getUsuariosCadastrados();
		
	}
	
	public String redirecionarUsuario() {
		return "/index";
	}
	
	public String redirecionarTarefa() {
		return "/login";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}



	public List<Usuario> getUsers() {
		return users;
	}



	public void setUsers(List<Usuario> users) {
		this.users = users;
	}


	
}
