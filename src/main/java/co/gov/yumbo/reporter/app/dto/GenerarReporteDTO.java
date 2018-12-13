package co.gov.yumbo.reporter.app.dto;

import java.util.List;


public class GenerarReporteDTO {

	private List<String> headers;
	private List<List<String>> resultadoConsulta;
	public List<String> getHeaders() {
		return headers;
	}
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	public List<List<String>> getResultadoConsulta() {
		return resultadoConsulta;
	}
	public void setResultadoConsulta(List<List<String>> resultadoConsulta) {
		this.resultadoConsulta = resultadoConsulta;
	}



}
