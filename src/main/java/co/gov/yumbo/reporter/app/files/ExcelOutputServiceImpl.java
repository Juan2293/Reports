package co.gov.yumbo.reporter.app.files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

//import com.mysql.jdbc.ResultSetMetaData;

import co.gov.yumbo.reporter.app.datasource.DataSourceFactory;
import co.gov.yumbo.reporter.app.dto.ReporteParametroDTO;
import co.gov.yumbo.reporter.app.model.Parametro;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Service()
public class ExcelOutputServiceImpl implements IExcelOutputService {

	@Override
	public WritableWorkbook createExcelOutputExcel(HttpServletResponse response, ReporteParametroDTO reporteParametroDTO) throws Exception {

		ArrayList<String> headers = new ArrayList<>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;


		//Nombre del reporte
		String fileName = "Reporte.xls";
		WritableWorkbook writableWorkbook = null;
		try {
			//me conecto al datasource 
			DataSource datasource = DataSourceFactory.getDataSource(reporteParametroDTO.getReporte().getDatabase());


			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

			//se crea el archivo excel en bytes
			writableWorkbook = Workbook.createWorkbook(response.getOutputStream());

			//datasource conexion
			con = datasource.getConnection();
			preparedStatement =  con.prepareStatement(reporteParametroDTO.getReporte().getConsulta());

			Parametro parametros[] = reporteParametroDTO.getParametros();

			//agrego los parametros a la consulta
			for (int i = 1; i <= parametros.length; i++) {
				preparedStatement.setString(i, parametros[i-1].getValorTemp());
			}

			//ejecuto el query
			rs = preparedStatement.executeQuery();

			//obtengo el nombre de las columnas
			ResultSetMetaData resultSetMetaData = (ResultSetMetaData) rs.getMetaData();
			int columnasCount = resultSetMetaData.getColumnCount();

			
			for (int i = 1; i <= columnasCount; i++) {
				headers.add(resultSetMetaData.getColumnName(i));
			}


			//se crea la hoja1 del excel
			WritableSheet excelOutputsheet = writableWorkbook.createSheet("Excel Output", 0);
			addExcelOutputHeader(excelOutputsheet,headers );
			writeExcelOutputData(excelOutputsheet,rs, columnasCount);


			rs.close();
			preparedStatement.close();
			con.close();


			writableWorkbook.write();
			writableWorkbook.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();


		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}

		
		return writableWorkbook;
	}




	private static void addExcelOutputHeader(WritableSheet sheet,List<String> headers) throws RowsExceededException, WriteException{
		WritableCellFormat cFormat = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.TIMES, 11, WritableFont.BOLD);
		cFormat.setFont(font);
		for (int i = 0; i < headers.size(); i++) {
			sheet.addCell(new Label(i, 0, headers.get(i).toString(),cFormat));
		}

	}

	private  static void writeExcelOutputData(WritableSheet sheet,ResultSet rs, int columnasCount) throws RowsExceededException, WriteException{
		try {
			
			//  indica la fila en la cual escirbir
			int row =1;	
			while(rs.next()){
				//  Columna, Fila, Valor
				for (int i = 1; i <= columnasCount; i++) {
					sheet.addCell(new Label(i-1,row, rs.getString(i)));	
				}
				row++;

			}
		} catch (Exception e) {

		}

	}
}
