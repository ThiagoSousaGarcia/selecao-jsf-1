package com.planner.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.planner.treina.controller.UsuarioDAO;
import com.planner.treina.entity.Tarefas;
import com.planner.treina.entity.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -8699654255891466192L;

	@Inject
	private EntityManager em;
	

	private UsuarioDAO userDao = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	
	private String senha = userDao.getSenhaByLogin(userDao.usuarioLogado());
	Usuario usuarioLogado = userDao.getUsuarioByLogin(userDao.usuarioLogado());
	
	
	public String envia() {
		
		String senha = userDao.MD5(usuario.getSenha());
		usuario = userDao.getUsuario(usuario.getLogin(),senha);
		
		if(usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                    "Erro no Login!"));
			return null;
		}else {
			userDao.logarUsuario(usuario);
			return "/index";
		}
	}
	
	public String redirecionarSenha() {
		
		String senhaEntrada = userDao.MD5(usuario.getSenha());
		
		if(senha.equals(senhaEntrada)) {
			return "/alterarsenha";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha inválida",
                    "Erro no Login!"));
			return null;
			
		}
	}
	
	public String alterarSenha() {
		try {
			
			userDao.atualizarSenha(usuarioLogado, usuario.getSenha());
			return "/index";
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String deslogar() {
		userDao.deslogarUsuario(usuarioLogado);
		return "/login2";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	private String title;

	@PostConstruct
	private void init() {
		System.out.println();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
