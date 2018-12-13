package co.gov.yumbo.reporter.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.yumbo.reporter.app.dao.IParametroDAO;
import co.gov.yumbo.reporter.app.model.Parametro;
@Service
public class ParametroService  implements IParametroService {

	@Autowired
	private IParametroDAO parametroDAO;
	
	@Override
	public Parametro getParametroById(long parametroId) {
		return parametroDAO.getParametroById(parametroId);
	}
	@Override
	public void createParametro(Parametro parametro) {
		parametroDAO.createParametro(parametro);	
	}
	@Override
	public void updateParametro(Parametro parametro) {
		parametroDAO.updateParametro(parametro);		
	}
	@Override
	public void deleteParametro(long parametroId) {
		parametroDAO.deleteParametro(parametroId);
		
	}
	@Override
	public List<Parametro> getAllParametrosByReportId(long reporteId) {
		return parametroDAO.getAllParametrosByReportId(reporteId);
		 
	}
	@Override
	public void deleteParametroByReporteId(long reporteId) {
		parametroDAO.deleteParametroByReporteId(reporteId);
	}



}
