package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.Consultor;
import model.Usuario;
import repository.ConsultorRepository;
import repository.UsuarioRepository;

@ManagedBean(name = "usuarioService")
@ApplicationScoped
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	public List<Usuario> buscar() {
		UsuarioRepository ud = new UsuarioRepository();
		return ud.getAll();
	}

	public void add() {

		UsuarioRepository ud = new UsuarioRepository();

		if (usuario.getId() == 0) {
			if (usuario.getTipo().equals("Consultor")) {
				Consultor cons = null;

				ConsultorRepository cr = new ConsultorRepository();

				cons = cr.getConsultorByName(usuario.getUsuario());
				if (cons == null) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage mensagem = new FacesMessage("Erro!", "Não existe nenhum consultor com este nome!");
					mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(null, mensagem);
				} else {
					ud.add(usuario);
					usuario = new Usuario();
				}
			} else {
				ud.add(usuario);
				usuario = new Usuario();
			}

		} else {
			ud.update(usuario);
			usuario = new Usuario();
		}
	}	

	public Usuario deletar(Usuario usuario) {
		UsuarioRepository ud = new UsuarioRepository();
		return ud.remove(usuario.getId());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
