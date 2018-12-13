package co.gov.yumbo.reporter.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="parametro")
public class Parametro {

	@Id
	@SequenceGenerator(name = "parametroSeq", sequenceName = "parametro_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "parametroSeq")
	@Column(name="parametro_id")
	private Long parametroId;

	@ManyToOne()
	@JoinColumn(name = "reporte_id")
	@JsonIgnore
	private Reporte reporte;

	@Column(name="nombre")
	private String nombre;

	@Column(name="tipo")
	private String tipo;

	@Column(name="orden")
	private Integer orden;
	
	@Lob
	@Column(name="descripcion")
	private String descripcion;

	// no se mapea, se usa en el excel
	private String valorTemp;

	public Long getParametroId() {
		return parametroId;
	}


	public void setParametroId(Long parametroId) {
		this.parametroId = parametroId;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Reporte getReporte() {
		return reporte;
	}


	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}


	public Integer getOrden() {
		return orden;
	}


	public void setOrden(Integer orden) {
		this.orden = orden;
	}


	public String getValorTemp() {
		return valorTemp;
	}


	public void setValorTemp(String valorTemp) {
		this.valorTemp = valorTemp;
	}

	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
