package co.gov.yumbo.reporter.app.dao;

import java.util.List;
import co.gov.yumbo.reporter.app.model.Parametro;

public interface IParametroDAO {

	
	Parametro getParametroById(long parametroId);
	void createParametro(Parametro parametro);
	void updateParametro(Parametro parametro);
	void deleteParametro(long parametroId);
	void deleteParametroByReporteId(long reporteId);
	List<Parametro> getAllParametrosByReportId(long reporteId);
}
