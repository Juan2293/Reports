package co.gov.yumbo.reporter.app.dto;

import co.gov.yumbo.reporter.app.model.Parametro;
import co.gov.yumbo.reporter.app.model.Reporte;

public class ReporteParametroDTO {

	private Reporte reporte;
	private Parametro parametros[];
	
	public Reporte getReporte() {
		return reporte;
	}
	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}
	public Parametro[] getParametros() {
		return parametros;
	}
	public void setParametros(Parametro parametros[]) {
		this.parametros = parametros;
	}
	
	
}
