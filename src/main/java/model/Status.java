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
@Table(name = "status")
public class Status implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private int id;
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "criado", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)	
	private Date criado;

	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private Set<MelhoriasAtingidas> ma = new LinkedHashSet<MelhoriasAtingidas>();
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private Set<ObsCronograma> ob = new LinkedHashSet<ObsCronograma>();
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private Set<Atividades> at = new LinkedHashSet<Atividades>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<MelhoriasAtingidas> getMa() {
		return ma;
	}

	public void setMa(Set<MelhoriasAtingidas> ma) {
		this.ma = ma;
	}

	public Set<ObsCronograma> getOb() {
		return ob;
	}

	public void setOb(Set<ObsCronograma> ob) {
		this.ob = ob;
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
	
	@PrePersist
	public void onCreate(){
		criado = new Date();
	}
	
	@PreUpdate	
	public void onUpdate(){
		criado = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((at == null) ? 0 : at.hashCode());
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + id;
		result = prime * result + ((ma == null) ? 0 : ma.hashCode());
		result = prime * result + ((ob == null) ? 0 : ob.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Status other = (Status) obj;
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
		if (id != other.id)
			return false;
		if (ma == null) {
			if (other.ma != null)
				return false;
		} else if (!ma.equals(other.ma))
			return false;
		if (ob == null) {
			if (other.ob != null)
				return false;
		} else if (!ob.equals(other.ob))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
