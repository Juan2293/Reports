package co.gov.yumbo.reporter.app.service;

import java.util.List;

import co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO;
import co.gov.yumbo.reporter.app.model.Reporte;

public interface IReporteService {
	List<Reporte> getAllReportes();
	List<Reporte> getAllReportesByDatabaseId(Long dbId);
	List<ReporteDatabaseDTO> getAllReporteDatabaseDTO();
	List<ReporteDatabaseDTO> getAllReportesAreasByUsuarioId(Long usuarioId);
	Reporte getReporteById(long reporteId);
	Long createReporte(Reporte reporte) throws Exception;
	void updateReporte(Reporte reporte) throws Exception;
	void deleteReporte(long reporteId);
}
