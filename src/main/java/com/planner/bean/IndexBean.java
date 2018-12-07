package com.planner.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = -8699654255891466192L;

	private String title;

	@PostConstruct
	private void init() {
		this.title = "Teste Prático PLANNER SISTEMAS";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
