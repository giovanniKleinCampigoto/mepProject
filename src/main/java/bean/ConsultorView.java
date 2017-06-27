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

import controller.ConsultorService;

import model.Consultor;
import model.Empresa;

@ManagedBean(name = "consultorView")
@ApplicationScoped
public class ConsultorView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Consultor> lst;
	private List<Empresa> empresalst;

	@ManagedProperty("#{consultorService}")
	private ConsultorService service;
	
	@PostConstruct
	public void init() {
		lst = service.buscar();
		empresalst = service.buscarEmpresas();
	}

	public void buscar() {
		this.lst = service.buscar();
	}
	
	public void buscarEmpresas(){
		this.empresalst = service.buscarEmpresas();
	}

	public void abrirDialogoBusca() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("consultorsearch", opcoes, null);
	}

	public void deletar(Consultor consultor) {
		lst.remove(service.deletar(consultor));
		lst = service.buscar();
	}

	public void selecionar(Consultor consultor) {
		RequestContext.getCurrentInstance().closeDialog(consultor);
		service.setConsultor(consultor);
	}

	public void consultorSelecionado(SelectEvent event) {
		Consultor consultor = (Consultor) event.getObject();
	}

	public List<Consultor> getLst() {
		return lst;
	}

	public void setLst(List<Consultor> lst) {
		this.lst = lst;
	}

	public ConsultorService getService() {
		return service;
	}

	public void setService(ConsultorService service) {
		this.service = service;
	}

	public List<Empresa> getEmpresalst() {
		return empresalst;
	}

	public void setEmpresalst(List<Empresa> empresalst) {
		this.empresalst = empresalst;
	}
	
	


}
