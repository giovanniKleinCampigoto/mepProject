package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Funcao;
import repository.FuncaoRepository;

@ManagedBean (name = "funcaoService")
@ApplicationScoped
public class FuncaoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Funcao f = new Funcao();

	public List<Funcao> buscar() {
		FuncaoRepository fd = new FuncaoRepository();
		return fd.getAll();
	}

	public void add() {
		FuncaoRepository fd = new FuncaoRepository();

		if (this.f.getId() == 0) {
			fd.add(f);
			this.f = new Funcao();
		} else {
			fd.update(f);
			this.f = new Funcao();
		}
	}

	public Funcao deletar(Funcao funcao) {
		FuncaoRepository fd = new FuncaoRepository();
		return fd.remove(funcao.getId());
	}

	public Funcao getF() {
		return f;
	}

	public void setF(Funcao f) {
		this.f = f;
	}	
	
}
