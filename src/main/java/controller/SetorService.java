package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Setores;
import repository.SetoresRepository;

@ManagedBean(name = "setorService")
@ApplicationScoped
public class SetorService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Setores st = new Setores();

	public List<Setores> buscar() {
		SetoresRepository sd = new SetoresRepository();
		return sd.getAll();
	}

	public void add() {
		SetoresRepository sd = new SetoresRepository();

		if (st.getId() == 0) {
			sd.add(st);
			st = new Setores();
		} else {
			sd.update(st);
			st = new Setores();
		}
	}

	public Setores deletar(Setores setores) {
		SetoresRepository sd = new SetoresRepository();
		return sd.remove(setores.getId());
	}

	public Setores getSt() {
		return st;
	}

	public void setSt(Setores st) {
		this.st = st;
	}
}
