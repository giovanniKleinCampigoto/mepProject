package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import model.Consultor;
import model.Empresa;
import repository.EmpresaRepository;

@ManagedBean(name="empresaService")
@ApplicationScoped
public class EmpresaService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Empresa empresa = new Empresa();
	
	private Consultor consultor = new Consultor();

	public List<Empresa> buscar() {
		EmpresaRepository ed = new EmpresaRepository();
		return ed.getAll();
	}

	public void add() {
		EmpresaRepository ed = new EmpresaRepository();
		
		if (empresa.getId() == 0) {
			empresa.setConsultor(consultor);
			ed.add(empresa);
			empresa = new Empresa();
			consultor = new Consultor();
		} else {
			empresa.setConsultor(consultor);
			ed.update(empresa);
			consultor = new Consultor();
			empresa = new Empresa();
		}
	}

	public Empresa deletar(Empresa empresa) {
		EmpresaRepository ed = new EmpresaRepository();
		return ed.remove(empresa.getId());
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}
}
