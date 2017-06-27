package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Empresa;
import model.Funcao;
import model.Pessoa;
import model.Setores;
import repository.PessoaRepository;

@ManagedBean(name = "pessoaService")
@ApplicationScoped
public class PessoaService  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa = new Pessoa();
	private Empresa empresa = new Empresa();
	private Setores setores = new Setores();
	private Funcao funcao = new Funcao();

	public List<Pessoa> buscar() {
		PessoaRepository pd = new PessoaRepository();
		return pd.getAll();
	}

	public void add() {
		PessoaRepository pd = new PessoaRepository();

		if (pessoa.getId() == 0) {
			pessoa.setEmpresa(empresa);
			pessoa.setFuncao(funcao);
			pessoa.setSetores(setores);
			pd.add(pessoa);
			empresa = new Empresa();
			funcao = new Funcao();
			setores = new Setores();
			pessoa = new Pessoa();
		} else {
			pessoa.setEmpresa(empresa);
			pessoa.setFuncao(funcao);
			pessoa.setSetores(setores);
			pd.update(pessoa);
			empresa = new Empresa();
			funcao = new Funcao();
			setores = new Setores();
			pessoa = new Pessoa();
		}
	}

	public Pessoa deletar(Pessoa pessoa) {
		PessoaRepository pd = new PessoaRepository();
		return pd.remove(pessoa.getId());
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Setores getSetores() {
		return setores;
	}

	public void setSetores(Setores setores) {
		this.setores = setores;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

}
