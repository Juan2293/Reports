package co.gov.yumbo.reporter.app.dto;



public class ReporteDatabaseDTO {

	private Long reporteId;	
	
	private String reporteNombre;
	
	private String databaseTipo;
	
	private Long dbId;
	private String databaseNombre;
	private String areaNombre;
	private String reporteDescripcion;
	
	
	public Long getReporteId() {
		return reporteId;
	}
	public void setReporteId(Long reporteId) {
		this.reporteId = reporteId;
	}
	public String getReporteNombre() {
		return reporteNombre;
	}
	public void setReporteNombre(String reporteNombre) {
		this.reporteNombre = reporteNombre;
	}
	public Long getDbId() {
		return dbId;
	}
	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}
	public String getDatabaseNombre() {
		return databaseNombre;
	}
	public void setDatabaseNombre(String databaseNombre) {
		this.databaseNombre = databaseNombre;
	}
	public String getDatabaseTipo() {
		return databaseTipo;
	}
	public void setDatabaseTipo(String databaseTipo) {
		this.databaseTipo = databaseTipo;
	}
	public String getAreaNombre() {
		return areaNombre;
	}
	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}
	public String getReporteDescripcion() {
		return reporteDescripcion;
	}
	public void setReporteDescripcion(String reporteDescripcion) {
		this.reporteDescripcion = reporteDescripcion;
	}
	public ReporteDatabaseDTO(Long reporteId, String reporteNombre, Long dbId, String databaseTipo,
			String databaseNombre, String areaNombre, String reporteDescripcion) {
		super();
		this.reporteId = reporteId;
		this.reporteNombre = reporteNombre;
		this.databaseTipo = databaseTipo;
		this.dbId = dbId;
		this.databaseNombre = databaseNombre;
		this.areaNombre = areaNombre;
		this.reporteDescripcion = reporteDescripcion;
	}
	

}
