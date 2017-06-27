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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "consultor")
public class Consultor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private int id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criado", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date criado;

	@OneToMany(mappedBy = "consultor", cascade = CascadeType.ALL)
	private Set<MelhoriasAtingidas> ma = new LinkedHashSet<MelhoriasAtingidas>();

	@OneToMany(mappedBy = "consultor", cascade = CascadeType.ALL)
	private Set<Atividades> at = new LinkedHashSet<Atividades>();

	@OneToMany(mappedBy = "consultor", cascade = CascadeType.ALL)
	private Set<Empresa> em = new LinkedHashSet<Empresa>();

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
		return ma;
	}

	public void setMa(Set<MelhoriasAtingidas> ma) {
		this.ma = ma;
	}

	public Set<Atividades> getAt() {
		return at;
	}

	public void setAt(Set<Atividades> at) {
		this.at = at;
	}

	public Date getCriado() {
		return criado;
	}

	public void setCriado(Date criado) {
		this.criado = criado;
	}

	public Set<Empresa> getEm() {
		return em;
	}

	public void setEm(Set<Empresa> em) {
		this.em = em;
	}

	@PrePersist
	public void onCreate() {
		criado = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		criado = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((at == null) ? 0 : at.hashCode());
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + ((em == null) ? 0 : em.hashCode());
		result = prime * result + id;
		result = prime * result + ((ma == null) ? 0 : ma.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Consultor other = (Consultor) obj;
		if (at == null) {
			if (other.at != null)
				return false;
		} else if (!at.equals(other.at))
			return false;
		if (criado == null) {
			if (other.criado != null)
				return false;
		} else if (!criado.equals(other.criado))
			return false;
		if (em == null) {
			if (other.em != null)
				return false;
		} else if (!em.equals(other.em))
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
		return true;
	}

}
