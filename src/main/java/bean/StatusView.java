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

import controller.StatusService;
import model.Status;

@ManagedBean(name = "statusView")
@ApplicationScoped
public class StatusView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Status> lst;

	@ManagedProperty("#{statusService}")
	private StatusService service;

	@PostConstruct
	public void init() {
		lst = service.buscar();
	}

	public void abrirDialogoBusca() {
		Map<String, Object> opcoes = new HashMap<>();
		
		opcoes.put("modal", true);
		opcoes.put("resizable",false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("statussearch", opcoes, null);
	}
	
	public void buscar(){
		this.lst = service.buscar();
	}

	public void deletar(Status status){
		lst.remove(service.deletar(status));
		lst = service.buscar();
	}
	
	public void selecionar(Status status){
		RequestContext.getCurrentInstance().closeDialog(status);
		service.setSt(status);
	}
	
	public void statusSelecionado(SelectEvent event){
		Status status = (Status) event.getObject();
	}
	
	public List<Status> getLst() {
		return lst;
	}

	public void setLst(List<Status> lst) {
		this.lst = lst;
	}

	public StatusService getService() {
		return service;
	}

	public void setService(StatusService service) {
		this.service = service;
	}

}
