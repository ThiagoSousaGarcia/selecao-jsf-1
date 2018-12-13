package com.planner.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.planner.treina.controller.TarefaDAO;
import com.planner.treina.controller.UsuarioDAO;
import com.planner.treina.entity.Tarefas;
import com.planner.treina.entity.Usuario;



@Named
@ViewScoped
public class TarefaBean implements Serializable {

	private static final long serialVersionUID = -8699654255891466192L;
	
	private List<Tarefas> tarefas = new ArrayList<Tarefas>();
	private TarefaDAO tarefaDao = new TarefaDAO();
	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private Tarefas tarefa = new Tarefas();
	
	@PostConstruct
	public void init() {
		this.usuario = usuarioDao.getUsuarioByLogin(usuarioDao.usuarioLogado());
		this.setTarefas(tarefaDao.getTarefasByUser(this.usuario));
	}

	public List<Tarefas> getTarefas() {
		return tarefas;
	}
	
	public String redirecionarNovatarefa() {
		return "/cadastrotarefas";
	}

	public void setTarefas(List<Tarefas> tarefas) {
		this.tarefas = tarefas;
	}
	
	public String cadastrarTarefa() {
		Date data = new Date();
			tarefaDao.inserirTarefa(tarefa.getTitulo(), tarefa.getDescricao(), 
					tarefa.getPrioridade(), usuario, data);
			
			return "/tarefas";
	}

	public Tarefas getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefas tarefa) {
		this.tarefa = tarefa;
	}
	
	
	
	
	
}
	
	
		

