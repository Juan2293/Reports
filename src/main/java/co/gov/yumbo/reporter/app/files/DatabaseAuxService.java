package co.gov.yumbo.reporter.app.files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import co.gov.yumbo.reporter.app.datasource.DataSourceFactory;
import co.gov.yumbo.reporter.app.dto.ReporteParametroDTO;
import co.gov.yumbo.reporter.app.model.Parametro;

public class DatabaseAuxService implements IDatabaseAuxService {


	Connection con = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;
	List<ArrayList<String>> reusltado;

	@Override
	public List<ArrayList<String>> getResultadoConsulta(ReporteParametroDTO reporteParametroDTO) {

		reusltado = new ArrayList<ArrayList<String>>();
		try {


			DataSource datasource = DataSourceFactory.getDataSource(reporteParametroDTO.getReporte().getDatabase());

			con = datasource.getConnection();
			preparedStatement =  con.prepareStatement(reporteParametroDTO.getReporte().getConsulta());

			Parametro parametros[] = reporteParametroDTO.getParametros();

			//agrego los parametros a la consulta
			for (int i = 1; i <= parametros.length; i++) {
				preparedStatement.setString(i, parametros[i-1].getValorTemp());
			}

			//ejecuto el query
			rs = preparedStatement.executeQuery();


			ResultSetMetaData resultSetMetaData = (ResultSetMetaData) rs.getMetaData();
			int columnasCount = resultSetMetaData.getColumnCount();
			ArrayList<String> arrayFilaAux;


			while(rs.next()){
				arrayFilaAux = new ArrayList<>();
				for (int i = 1; i <= columnasCount; i++) {
					arrayFilaAux.add(rs.getString(i));
					//					sheet.addCell(new Label(i-1,row, rs.getString(i)));	
				}
				reusltado.add(arrayFilaAux);
			}

			//obtengo el nombre de las columnas

			//			ArrayList<String> headers = new ArrayList<>();
			//			ResultSetMetaData resultSetMetaData = (ResultSetMetaData) rs.getMetaData();
			//			int columnasCount = resultSetMetaData.getColumnCount();
			//
			//			
			//			for (int i = 1; i <= columnasCount; i++) {
			//				headers.add(resultSetMetaData.getColumnName(i));
			//			}

			rs.close();
			preparedStatement.close();
			con.close();
		} catch (Exception e) {

			// TODO: handle exception
		}

		return reusltado;
	}

	@Override
	public List<String> getConsultaHeaders(ResultSet rs) {
		ArrayList<String> headers = new ArrayList<>();
		try {			
			ResultSetMetaData resultSetMetaData = (ResultSetMetaData) rs.getMetaData();
			int columnasCount = resultSetMetaData.getColumnCount();
			for (int i = 1; i <= columnasCount; i++) {
				headers.add(resultSetMetaData.getColumnName(i));
			}
		} catch (Exception e) {
		}
		return headers;
	}



}
