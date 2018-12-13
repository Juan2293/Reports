package co.gov.yumbo.reporter.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.yumbo.reporter.app.dao.IReporteDAO;
import co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO;
import co.gov.yumbo.reporter.app.model.Area;
import co.gov.yumbo.reporter.app.model.Reporte;

@Service
public class ReporteService implements IReporteService {

	@Autowired
	private IReporteDAO reporteDAO;
	@Override
	public List<Reporte> getAllReportes() {
		return reporteDAO.getAllReportes();
	}

	@Override
	public Reporte getReporteById(long ReporteId) {
		
		return reporteDAO.getReporteById(ReporteId);
	}

	@Override
	public Long createReporte(Reporte reporte) throws Exception{
		
		if (reporte.getNombre().trim().equals("") || reporte.getNombre()==null) {
			throw new Exception("Ingrese el nombre del reporte");
		}
		if (reporteDAO.getReporteByName(reporte.getNombre().trim())!=null) {
			throw new Exception("Ya existe un reporte con el  nombre: "+ reporte.getNombre().trim());
		}
		 return reporteDAO.createReporte(reporte);
		
	}

	@Override
	public void updateReporte(Reporte reporte) throws Exception {
		
		Reporte reporte_aux= reporteDAO.getReporteById(reporte.getReporteId());
		if (reporte.getNombre().trim().equals("") || reporte.getNombre()==null) {
			throw new Exception("Ingrese el nombre del reporte");
		}
		if (reporteDAO.getReporteByName(reporte.getNombre().trim())!=null &&
				!reporte_aux.getNombre().trim().equalsIgnoreCase(reporte.getNombre().trim()) ) {
			throw new Exception("Ya existe un reporte con el  nombre:  "+ reporte.getNombre().trim());
		}
		reporteDAO.updateReporte(reporte);
		
	}

	@Override
	public void deleteReporte(long ReporteId) {
		reporteDAO.deleteReporte(ReporteId);
		
	}

	@Override
	public List<Reporte> getAllReportesByDatabaseId(Long dbId) {
		return reporteDAO.getAllReportesByDatabaseId(dbId);
	}

	@Override
	public List<ReporteDatabaseDTO> getAllReporteDatabaseDTO() {
		return reporteDAO.getAllReporteDatabaseDTO();
	}

	@Override
	public List<ReporteDatabaseDTO> getAllReportesAreasByUsuarioId(Long usuarioId) {
		
		return reporteDAO.getAllReportesAreasByUsuarioId(usuarioId);
	}

}
