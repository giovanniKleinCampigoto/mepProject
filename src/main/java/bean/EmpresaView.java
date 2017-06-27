package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import controller.EmpresaService;
import model.Consultor;
import model.Empresa;

@ManagedBean(name = "empresaView")
@ApplicationScoped
public class EmpresaView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Empresa> lst;

	@ManagedProperty("#{empresaService}")
	private EmpresaService service;

	@PostConstruct
	public void init() {
		lst = service.buscar();
	}
	
	public void buscar(){
		this.lst = service.buscar();
	}

	public void abrirDialogoBusca() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("empresasearch", opcoes, null);
	}

	public void abrirDialogoSelecao() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("consultorselection", opcoes, null);
	}

	public void deletar(Empresa empresa) {
		lst.remove(service.deletar(empresa));
		lst = service.buscar();
	}

	public void selecionar(Empresa empresa) {
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

	public List<Empresa> getLst() {
		return lst;
	}

	public void setLst(List<Empresa> lst) {
		this.lst = lst;
	}

	public EmpresaService getService() {
		return service;
	}

	public void setService(EmpresaService service) {
		this.service = service;
	}

}
