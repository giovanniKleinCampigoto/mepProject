package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Usuario;
import util.SessionUtil;

@ManagedBean(name = "usuarioWorkView")
@SessionScoped
public class UsuarioWorkView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = (Usuario) SessionUtil.getParam("usuarioLOGADO");

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
