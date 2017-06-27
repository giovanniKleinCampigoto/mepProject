package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable=false)
	@GeneratedValue
	private int id;
	@Column(name = "nome", nullable=false)
	private String nome;
	@Column(name = "cnpj", nullable=false)
	private String cnpj;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criado", nullable=false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date criado;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private Set<MelhoriasAtingidas> ma = new LinkedHashSet<>();

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private Set<Atividades> at = new LinkedHashSet<>();

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private Set<Pessoa> set = new LinkedHashSet<>();

	@ManyToOne
	@JoinColumn(name = "consultor_id", nullable = false)
	private Consultor consultor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<MelhoriasAtingidas> getMa() {
		return this.ma;
	}

	public void setMa(Set<MelhoriasAtingidas> ma) {
		this.ma = ma;
	}

	public Set<Atividades> getAt() {
		return this.at;
	}

	public void setAt(Set<Atividades> at) {
		this.at = at;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getCriado() {
		return criado;
	}

	@PrePersist
	public void onCreate() {
		criado = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		criado = new Date();
	}

	public void setCriado(Date criado) {
		this.criado = criado;
	}

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((at == null) ? 0 : at.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((consultor == null) ? 0 : consultor.hashCode());
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + id;
		result = prime * result + ((ma == null) ? 0 : ma.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((set == null) ? 0 : set.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (at == null) {
			if (other.at != null)
				return false;
		} else if (!at.equals(other.at))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (consultor == null) {
			if (other.consultor != null)
				return false;
		} else if (!consultor.equals(other.consultor))
			return false;
		if (criado == null) {
			if (other.criado != null)
				return false;
		} else if (!criado.equals(other.criado))
			return false;
		if (id != other.id)
			return false;
		if (ma == null) {
			if (other.ma != null)
				return false;
		} else if (!ma.equals(other.ma))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		return true;
	}

}
