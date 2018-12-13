package co.gov.yumbo.reporter.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="reporte")
public class Reporte {

	@Id
	@SequenceGenerator(name = "reporteSeq", sequenceName = "reporte_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "reporteSeq")
	@Column(name="reporte_id")
	private Long reporteId;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="consulta")
	@Lob	
	private String consulta;
	
	@Column(name="descripcion")
	@Lob	
	private String descripcion;
	

	@ManyToOne
	@JoinColumn(name = "database_re_id")
	private DatabaseRe database;
	
	@ManyToOne()
	@JoinColumn(name = "area_id")
	private Area area;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fecha_creacion;
	
	public DatabaseRe getDatabase() {
		return database;
	}
	public void setDatabase(DatabaseRe database) {
		this.database = database;
	}
	public Long getReporteId() {
		return reporteId;
	}
	public void setReporteId(Long reporteId) {
		this.reporteId = reporteId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
