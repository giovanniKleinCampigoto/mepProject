package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Consultor;
import model.Empresa;
import repository.ConsultorRepository;

@ManagedBean(name = "consultorService")
@ApplicationScoped
public class ConsultorService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Consultor consultor = new Consultor();
	
	public List<Consultor> buscar() {
		ConsultorRepository cd = new ConsultorRepository();
		return cd.getAll();
	}

	public void addConsultor() {
		ConsultorRepository cd = new ConsultorRepository();

		if (consultor.getId() == 0) {
			cd.add(consultor);
			consultor = new Consultor();
		} else {
			cd.update(consultor);
			consultor = new Consultor();
		}
	}
	
	public List<Empresa> buscarEmpresas(){
		ConsultorRepository cr = new ConsultorRepository();
		return cr.getEmpresas(this.consultor.getId());
	}
	
	public void buscarByNome(String nome) {
		ConsultorRepository cd = new ConsultorRepository();
		this.setConsultor(cd.getConsultorByName(nome));
	}
	
	public Consultor deletar(Consultor consultor) {
		ConsultorRepository cd = new ConsultorRepository();
		return cd.remove(consultor.getId());
	}

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

}
