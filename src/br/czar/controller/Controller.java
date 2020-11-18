package br.czar.controller;

import java.util.ArrayList;
import java.util.List;

import br.czar.util.Utils;
import br.czar.dao.DAO;

public abstract class Controller<T> {
	protected T entity;
	protected DAO<T> dao = null;
	private List<T> entityList = null;
	
	public Controller(DAO<T> dao) {
		this.dao = dao;
	}

	public void insert() {
		System.out.println(getEntity());
		try {
			dao.insert(getEntity());
			Utils.addInfoMessage("Cadastro realizado com sucesso.");
			clear();
		} catch (Exception e) {
			Utils.addErrorMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			dao.update(getEntity());
			Utils.addInfoMessage("Alteração realizada com sucesso.");
			setEntityList(null);
			clear();
		} catch (Exception e) {
			Utils.addErrorMessage("Não é possivel fazer uma alteração.");
			e.printStackTrace();
		}
	}

	public void delete() {
		delete(getEntity());
	}

	public void delete(T entity) {
		try {
			dao.delete(entity);
			Utils.addInfoMessage("Exclusão realizada com sucesso.");
			clear();
		} catch (Exception e) {
			Utils.addErrorMessage("Não é possivel fazer uma exclusão.");
			e.printStackTrace();
		}
	}
	
	public void edit(T entity) {
		try {
			setEntity(dao.getOne(entity));
		} catch (Exception e) {
			Utils.addErrorMessage("Problema ao editar.");
			e.printStackTrace();
		}
	}
	
	public List<T> getEntityList() {
		if (entityList == null) {
			try {
				entityList = dao.getAll();
			} catch (Exception e) {
				e.printStackTrace();
				entityList = new ArrayList<T>();
			}
		}	
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}
	
	public void clear() {
		entity = null;
		entityList = null;
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
}