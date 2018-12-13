	package co.gov.yumbo.reporter.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="database_re")
public class DatabaseRe {

	@Id
	@SequenceGenerator(name = "databaseRepSeq", sequenceName = "databaseRep_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "databaseRepSeq")
	@Column(name="database_re_id")
	private Long dbId;

	@Column(name="nombre")
	private String nombre;

	//	@JsonIgnore
	@Column(name="usuario")
	private String usuario;

	
	@Column(name="password")
	private String password;

	//	@JsonIgnore
	@Column(name="url")
	private String url;

	@Column(name="tipo")
	private String tipo;

	public Long getDbId() {
		return dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	} 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



}
