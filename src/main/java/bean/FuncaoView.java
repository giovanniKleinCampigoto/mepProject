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

import controller.FuncaoService;

import model.Funcao;

@ManagedBean(name = "funcaoView")
@ApplicationScoped
public class FuncaoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Funcao> lst;

	@ManagedProperty("#{funcaoService}")
	private FuncaoService service;

	@PostConstruct
	public void init() {
		this.lst = service.buscar();
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
		RequestContext.getCurrentInstance().openDialog("funcaosearch", opcoes, null);
	}

	public void deletar(Funcao funcao) {
		this.lst.remove(service.deletar(funcao));
		this.lst = service.buscar();
	}

	public void selecionar(Funcao funcao) {
		RequestContext.getCurrentInstance().closeDialog(funcao);
		service.setF(funcao);
	}

	public void funcaoSelecionada(SelectEvent event) {
		Funcao funcao = (Funcao) event.getObject();
	}

	public List<Funcao> getLst() {
		return lst;
	}

	public void setLst(List<Funcao> lst) {
		this.lst = lst;
	}

	public FuncaoService getService() {
		return service;
	}

	public void setService(FuncaoService service) {
		this.service = service;
	}
}
