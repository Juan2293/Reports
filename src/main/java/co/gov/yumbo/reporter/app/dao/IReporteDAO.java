package co.gov.yumbo.reporter.app.dao;

import java.util.List;

import co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO;
import co.gov.yumbo.reporter.app.model.Reporte;


public interface IReporteDAO{

	List<Reporte> getAllReportes();
	List<Reporte> getAllReportesByDatabaseId(Long dbId);
	List<ReporteDatabaseDTO> getAllReportesAreasByUsuarioId(Long usuarioId);
	Reporte getReporteById(long reporteId);
	Reporte getReporteByName(String nombre);
	Long createReporte(Reporte reporte);
	void updateReporte(Reporte reporte);
	void deleteReporte(long reporteId);
	List<ReporteDatabaseDTO> getAllReporteDatabaseDTO();

}
