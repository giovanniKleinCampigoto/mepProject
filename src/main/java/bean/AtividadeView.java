package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import controller.AtividadeService;
import model.Atividades;
import model.Consultor;
import model.Empresa;
import model.Status;

@ManagedBean(name = "atividadeView")
@ApplicationScoped
public class AtividadeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Atividades> lst;

	@ManagedProperty("#{atividadeService}")
	private AtividadeService service;

	@PostConstruct
	public void init() {
		this.lst = service.buscar();
	}
	
	public void buscar(){
		this.lst = service.buscar();
	}
	
	public void dialogoBuscar() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 800);
		opcoes.put("contentLength", 800);
		RequestContext.getCurrentInstance().openDialog("atividadesearch", opcoes, null);
	}

	public void dialogoEmpresa() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("empselecatv", opcoes, null);
	}
	
	public void dialogoConsultor() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("consultorselecatv", opcoes, null);
	}
	
	public void dialogoStatus() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("statusselection", opcoes, null);
	}
	
	public void selecionar(Atividades atividade) {
		RequestContext.getCurrentInstance().closeDialog(atividade);
		service.setAtividade(atividade);
	}
	
	public void atividadeSelecionada(SelectEvent event) {
		Atividades atividade = (Atividades) event.getObject();
	}
	
	public void selecionarEmpresa(Empresa empresa) {
		RequestContext.getCurrentInstance().closeDialog(empresa);
		service.setEmpresa(empresa);
	}
	
	public void empresaSelecionada(SelectEvent event) {
		Empresa empresa = (Empresa) event.getObject();
	}
	
	public void selecionarConsultor(Consultor consultor) {
		RequestContext.getCurrentInstance().closeDialog(consultor);
		service.setConsultor(consultor);
	}
	
	public void consultorSelecionado(SelectEvent event) {
		Consultor consultor = (Consultor) event.getObject();
	}
	
	public void selecionarStatus(Status status) {
		RequestContext.getCurrentInstance().closeDialog(status);
		service.setStatus(status);
	}
	
	public void statusSelecionado(SelectEvent event) {
		Status status = (Status) event.getObject();
	}

	public List<Atividades> getLst() {
		return lst;
	}

	public void setLst(List<Atividades> lst) {
		this.lst = lst;
	}

	public AtividadeService getService() {
		return service;
	}

	public void setService(AtividadeService service) {
		this.service = service;
	}
	
}
