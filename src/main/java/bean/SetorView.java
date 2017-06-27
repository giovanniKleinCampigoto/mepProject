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

import controller.SetorService;
import model.Setores;

@ManagedBean (name = "setorView")
@ApplicationScoped
public class SetorView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Setores> lst;

	@ManagedProperty("#{setorService}")
	private SetorService service;

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
		RequestContext.getCurrentInstance().openDialog("setorsearch", opcoes, null);
	}
	
	public void buscar(){
		this.lst = service.buscar();
	}
	
	public void deletar(Setores setores){
		lst.remove(service.deletar(setores));
		lst = service.buscar();
	}
	
	public void selecionar(Setores setores){
		RequestContext.getCurrentInstance().closeDialog(setores);
		service.setSt(setores);
	}
	
	public void setorSelecionado(SelectEvent event){
		Setores setores = (Setores) event.getObject();
	}
	
	public List<Setores> getLst() {
		return lst;
	}

	public void setLst(List<Setores> lst) {
		this.lst = lst;
	}

	public SetorService getService() {
		return service;
	}

	public void setService(SetorService service) {
		this.service = service;
	}

}
