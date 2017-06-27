package bean;

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

import controller.PessoaService;
import model.Empresa;
import model.Funcao;
import model.Pessoa;
import model.Setores;


@ManagedBean(name = "pessoaView")
@ApplicationScoped
public class PessoaView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Pessoa> lst;

	@ManagedProperty("#{pessoaService}")
	private PessoaService service;

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
		RequestContext.getCurrentInstance().openDialog("pessoasearch", opcoes, null);
	}
	
	public void dialogoEmpresa() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("empresaselection", opcoes, null);
	}
	
	public void dialogoSetor() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("setorselection", opcoes, null);
	}
	
	public void dialogoFuncao() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		opcoes.put("contentLength", 500);
		RequestContext.getCurrentInstance().openDialog("funcaoselection", opcoes, null);
	}

	public void deletar(Pessoa pessoa) {
		lst.remove(service.deletar(pessoa));
		lst = service.buscar();
	}

	public void selecionar(Pessoa pessoa) {
		RequestContext.getCurrentInstance().closeDialog(pessoa);
		service.setPessoa(pessoa);
	}
	
	public void pessoaSelecionada(SelectEvent event) {
		Pessoa pessoa = (Pessoa) event.getObject();
	}

	public void selecionarEmpresa(Empresa empresa) {
		RequestContext.getCurrentInstance().closeDialog(empresa);
		service.setEmpresa(empresa);
	}
	
	public void empresaSelecionada(SelectEvent event) {
		Empresa empresa = (Empresa) event.getObject();
	}
	
	public void selecionarSetor(Setores setor) {
		RequestContext.getCurrentInstance().closeDialog(setor);
		service.setSetores(setor);
	}
	
	public void setorSelecionado(SelectEvent event) {
		Setores setor = (Setores) event.getObject();
	}
	
	public void selecionarFuncao(Funcao funcao) {
		RequestContext.getCurrentInstance().closeDialog(funcao);
		service.setFuncao(funcao);
	}
	
	public void funcaoSelecionada(SelectEvent event) {
		Funcao funcao = (Funcao) event.getObject();
	}

	public List<Pessoa> getLst() {
		return lst;
	}

	public void setLst(List<Pessoa> lst) {
		this.lst = lst;
	}

	public PessoaService getService() {
		return service;
	}

	public void setService(PessoaService service) {
		this.service = service;
	}
}
