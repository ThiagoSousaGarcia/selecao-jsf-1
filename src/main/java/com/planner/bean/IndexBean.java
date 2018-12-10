package com.planner.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.planner.treina.controller.UsuarioDAO;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = -8699654255891466192L;

	private String title;
	private String usuarioLogado;
	private UsuarioDAO userDao = new UsuarioDAO();

	@PostConstruct
	private void init() {
		this.title = "Teste Prático PLANNER SISTEMAS";
		this.usuarioLogado = userDao.usuarioLogado();
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
	
}
