package co.gov.yumbo.reporter.app.files;

import javax.servlet.http.HttpServletResponse;

import co.gov.yumbo.reporter.app.dto.ReporteParametroDTO;
import jxl.write.WritableWorkbook;

public interface IExcelOutputService {

	WritableWorkbook createExcelOutputExcel(HttpServletResponse response,ReporteParametroDTO reporteParametroDTO) throws Exception;
}
