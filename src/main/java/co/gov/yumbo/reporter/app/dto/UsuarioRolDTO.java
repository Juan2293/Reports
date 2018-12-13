package co.gov.yumbo.reporter.app.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UsuarioRolDTO {

	private long usuarioId;
	private String username;
	private String usuarioDescripcion;
	private long rolId;
	private String rolNombre;
	private String rolDescripcion;
	public long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsuarioDescripcion() {
		return usuarioDescripcion;
	}
	public void setUsuarioDescripcion(String usuarioDescripcion) {
		this.usuarioDescripcion = usuarioDescripcion;
	}
	public long getRolId() {
		return rolId;
	}
	public void setRolId(long rolId) {
		this.rolId = rolId;
	}
	public String getRolNombre() {
		return rolNombre;
	}
	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}
	public String getRolDescripcion() {
		return rolDescripcion;
	}
	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}
	public UsuarioRolDTO(long usuarioId, String username, String usuarioDescripcion, long rolId, String rolNombre,
			String rolDescripcion) {
		super();
		this.usuarioId = usuarioId;
		this.username = username;
		this.usuarioDescripcion = usuarioDescripcion;
		this.rolId = rolId;
		this.rolNombre = rolNombre;
		this.rolDescripcion = rolDescripcion;
	}

	
}
