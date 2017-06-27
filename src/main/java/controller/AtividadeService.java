package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Atividades;
import model.Consultor;
import model.Empresa;
import model.Status;
import repository.AtividadesRepository;
import repository.ConsultorRepository;

@ManagedBean
@ApplicationScoped
public class AtividadeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Atividades atividade = new Atividades();
	private Status status = new Status();
	private Consultor consultor = new Consultor();
	private Empresa empresa = new Empresa();

	public List<Atividades> buscar() {
		AtividadesRepository ar = new AtividadesRepository();
		return ar.getAll();
	}

	public void add() {
		AtividadesRepository ar = new AtividadesRepository();

		if (atividade.getId() == 0) {
			atividade.setStatus(status);
			atividade.setConsultor(consultor);
			atividade.setEmpresa(empresa);
			ar.add(atividade);
			status = new Status();
			consultor = new Consultor();
			empresa = new Empresa();
			atividade = new Atividades();
		} else {
			atividade.setStatus(status);
			atividade.setConsultor(consultor);
			atividade.setEmpresa(empresa);
			ar.update(atividade);
			status = new Status();
			consultor = new Consultor();
			empresa = new Empresa();
			atividade = new Atividades();
		}
	}

	public Atividades deletar(Atividades atv) {
		AtividadesRepository ar = new AtividadesRepository();
		return ar.remove(atv.getId());
	}

	public Atividades getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividades atividade) {
		this.atividade = atividade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
