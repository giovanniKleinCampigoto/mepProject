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
@Table(name = "atividades")
public class Atividades implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private int id;
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	@Column(name = "area", nullable = false)
	private String area;
	@Column(name = "programa", nullable = false)
	private String programa;
	@Column(name = "ferramenta", nullable = false)
	private String ferramenta;
	@Column(name = "atividades", columnDefinition="TEXT" , nullable = false)
	private String atividades;
	@Column(name = "tipo", nullable = false)
	private String tipo;
	@Temporal(TemporalType.TIME)
	@Column(name = "inicio", nullable = false)
	private Date inicio;
	@Temporal(TemporalType.TIME)
	@Column(name = "fim", nullable = false)
	private Date fim;
	@Temporal(TemporalType.TIME)
	@Column(name = "tempo", nullable = false)
	private Date tempo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criado", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getFerramenta() {
		return ferramenta;
	}

	public void setFerramenta(String ferramenta) {
		this.ferramenta = ferramenta;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Date getTempo() {
		return tempo;
	}

	public void setTempo(Date tempo) {
		this.tempo = tempo;
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
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((atividades == null) ? 0 : atividades.hashCode());
		result = prime * result + ((consultor == null) ? 0 : consultor.hashCode());
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((ferramenta == null) ? 0 : ferramenta.hashCode());
		result = prime * result + ((fim == null) ? 0 : fim.hashCode());
		result = prime * result + id;
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((programa == null) ? 0 : programa.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tempo == null) ? 0 : tempo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Atividades other = (Atividades) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (atividades == null) {
			if (other.atividades != null)
				return false;
		} else if (!atividades.equals(other.atividades))
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
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (ferramenta == null) {
			if (other.ferramenta != null)
				return false;
		} else if (!ferramenta.equals(other.ferramenta))
			return false;
		if (fim == null) {
			if (other.fim != null)
				return false;
		} else if (!fim.equals(other.fim))
			return false;
		if (id != other.id)
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (programa == null) {
			if (other.programa != null)
				return false;
		} else if (!programa.equals(other.programa))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tempo == null) {
			if (other.tempo != null)
				return false;
		} else if (!tempo.equals(other.tempo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
