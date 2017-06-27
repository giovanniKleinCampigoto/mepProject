package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import model.Consultor;
import model.Usuario;
import repository.UsuarioRepository;
import util.SessionUtil;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private String nomeUsuario;
	private String senha;
	private String redirect;

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();

		List<Usuario> lu = new ArrayList<>();
		UsuarioRepository ud = new UsuarioRepository();
		Usuario aux = null;

		lu = ud.getAll();

		if (!lu.isEmpty()) {
			for (int i = 0; i < lu.size(); i++) {

				if (lu.get(i).getUsuario().equals(this.nomeUsuario) && lu.get(i).getSenha().equals(this.senha)) {
					aux = lu.get(i);
				}

			}
		}

		if (aux != null) {
			this.usuario = aux;
			
			if (usuario.getTipo().equals("Administrador")) {
				SessionUtil.setParam("Administrador", this.usuario);
				this.redirect = "cadastro?faces-redirect=true";
			}
			if (usuario.getTipo().equals("Consultor")) {
				SessionUtil.setParam("Consultor", this.usuario);
				ConsultorService cs = new ConsultorService();
				cs.buscarByNome(this.usuario.getUsuario());
				this.redirect = "consultor?faces-redirect=true";
			}

		} else {
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			this.usuario = null;
		}

		return redirect;
	}

	public String logout() {
		this.redirect = "login?faces-redirect=true";
		this.usuario = null;
		SessionUtil.remove("Administrador");
		SessionUtil.remove("Consultor");
		return redirect;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
