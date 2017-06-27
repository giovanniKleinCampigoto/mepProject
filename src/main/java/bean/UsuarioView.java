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

import controller.LoginBean;
import controller.UsuarioService;

import model.Usuario;

@ManagedBean(name = "usuarioView")
@ApplicationScoped
public class UsuarioView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Usuario> lst;

	@ManagedProperty("#{usuarioService}")
	private UsuarioService service;

	@PostConstruct
	public void init() {
		this.lst = service.buscar();
	}

	public void abrirDialogoBusca() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("usuariosearch", opcoes, null);
	}
	
	public void buscar(){
		this.lst = service.buscar();
	}
	
	public void deletar(Usuario usuario) {
		lst.remove(service.deletar(usuario));
		lst = service.buscar();
	}

	public void selecionar(Usuario usuario) {
		RequestContext.getCurrentInstance().closeDialog(usuario);
		service.setUsuario(usuario);
	}
	
	public void usuarioSelecionado(SelectEvent event) {
		Usuario usuario = (Usuario) event.getObject();
	}

	public List<Usuario> getLst() {
		return lst;
	}

	public void setLst(List<Usuario> lst) {
		this.lst = lst;
	}

	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

}
