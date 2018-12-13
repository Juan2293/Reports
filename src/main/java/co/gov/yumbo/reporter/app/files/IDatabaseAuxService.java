package co.gov.yumbo.reporter.app.files;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.gov.yumbo.reporter.app.dto.ReporteParametroDTO;

public interface IDatabaseAuxService {
	
	List<ArrayList<String>> getResultadoConsulta(ReporteParametroDTO reporteParametroDTO);
	List<String> getConsultaHeaders(ResultSet resulset);

}
