package model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "observacao")
public class ObsCronograma implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable=false)
	@GeneratedValue
	private int id;	
	@Column(name = "previsto", nullable=false)
	private String previsto;
	@Column(name = "realizado", nullable=false)
	private String realizado;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criado", nullable=false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date criado;
	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)	
	private Status status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPrevisto() {
		return previsto;
	}
	public void setPrevisto(String previsto) {
		this.previsto = previsto;
	}
	public String getRealizado() {
		return realizado;
	}
	public void setRealizado(String realizado) {
		this.realizado = realizado;
	}
	
	public Status getStatus() {
		return this.status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getCriado() {
		return criado;
	}
	public void setCriado(Date criado) {
		this.criado = criado;
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
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + id;
		result = prime * result + ((previsto == null) ? 0 : previsto.hashCode());
		result = prime * result + ((realizado == null) ? 0 : realizado.hashCode());
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
		ObsCronograma other = (ObsCronograma) obj;
		if (criado == null) {
			if (other.criado != null)
				return false;
		} else if (!criado.equals(other.criado))
			return false;
		if (id != other.id)
			return false;
		if (previsto == null) {
			if (other.previsto != null)
				return false;
		} else if (!previsto.equals(other.previsto))
			return false;
		if (realizado == null) {
			if (other.realizado != null)
				return false;
		} else if (!realizado.equals(other.realizado))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
	
}
