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
@Table(name = "melhorias")
public class MelhoriasAtingidas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable=false)
	@GeneratedValue
	private int id;
	@Column(name = "melhorias", nullable=false)
	private String melhorias;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criado", nullable=false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date criado;

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "consultor_id", nullable = false)
	private Consultor consultor;

	@ManyToOne
	@JoinColumn(name = "empresa_id", nullable = false)
	private Empresa empresa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMelhorias() {
		return melhorias;
	}

	public void setMelhorias(String melhorias) {
		this.melhorias = melhorias;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Consultor getConsultor() {
		return this.consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
		result = prime * result + ((consultor == null) ? 0 : consultor.hashCode());
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + id;
		result = prime * result + ((melhorias == null) ? 0 : melhorias.hashCode());
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
		MelhoriasAtingidas other = (MelhoriasAtingidas) obj;
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
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (id != other.id)
			return false;
		if (melhorias == null) {
			if (other.melhorias != null)
				return false;
		} else if (!melhorias.equals(other.melhorias))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
