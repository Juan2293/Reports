package co.gov.yumbo.reporter.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mysql.jdbc.ResultSetMetaData;

import co.gov.yumbo.reporter.app.dao.IReporteDAO;
import co.gov.yumbo.reporter.app.datasource.DataSourceFactory;
import co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO;
import co.gov.yumbo.reporter.app.model.DatabaseRe;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReporterYumboApplicationTests {
	
	@Autowired
	private IReporteDAO reporteDAO;
	@Test
	public void contextLoads() {
		
		List<ReporteDatabaseDTO> reporteDatabaseDTO =reporteDAO.getAllReporteDatabaseDTO();
		
		for (ReporteDatabaseDTO reporteDatabase : reporteDatabaseDTO) {
			System.out.println(reporteDatabase.getDatabaseNombre());
			System.out.println(reporteDatabase.getReporteNombre());
			
			System.out.println("************************");
		}
	}
//	@Test
//	public void testDBCPDataSource() {
//		
//		
//		DatabaseRe database = new DatabaseRe();
////		database.setUrl("jdbc:postgresql://localhost:5432/test");
////		database.setTipo("postgres");
////		database.setPassword("postgres");
////		database.setUsuario("postgres");
//		
//		database.setUrl("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false");
//		database.setTipo("mysql");
//		database.setPassword("root");
//		database.setUsuario("root");
//		
//		DataSource ds = DataSourceFactory.getDataSource(database);
//		
//		
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		
//		try {
//			String sql = "select name from city where id = ? ";
//			con = ds.getConnection();
//
//			
//			PreparedStatement preparedStatement =  con.prepareStatement(sql);
//			preparedStatement.setString(1, "717");
//			rs = preparedStatement.executeQuery();
//			ResultSetMetaData resultSetMetaData = (ResultSetMetaData) rs.getMetaData();
//			while(rs.next()){
//				System.out.println("username = "+rs.getString(1));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//				try {
//					if(rs != null) rs.close();
//					if(stmt != null) stmt.close();
//					if(con != null) con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
//	}


}
