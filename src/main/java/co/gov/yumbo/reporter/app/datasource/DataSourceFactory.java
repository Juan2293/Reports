package co.gov.yumbo.reporter.app.datasource;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;

import co.gov.yumbo.reporter.app.model.DatabaseRe;

@Service
public class DataSourceFactory {
	
	
	public static DataSource getDataSource(DatabaseRe database){
		
		BasicDataSource datasource = new BasicDataSource();
//		datasource.setValidationQuery("SELECT 1");
		
		
		if("postgres".equalsIgnoreCase(database.getTipo())){

			
			datasource.setDriverClassName("org.postgresql.Driver");
			datasource.setUrl(database.getUrl());
			datasource.setUsername(database.getUsuario());
			datasource.setPassword(database.getPassword());

		}else if("mysql".equalsIgnoreCase(database.getTipo())){

			System.out.println("entro al mysql");

			System.out.println(database.getUrl());
			System.out.println(database.getUsuario());
			System.out.println(database.getPassword());

			datasource.setDriverClassName("com.mysql.jdbc.Driver");
			datasource.setUrl(database.getUrl());
			datasource.setUsername(database.getUsuario());
			datasource.setPassword(database.getPassword());

		}else if("oracle".equalsIgnoreCase(database.getTipo())){
			
			System.out.println(database.getUrl());
			System.out.println(database.getUsuario());
			System.out.println(database.getPassword());
//			datasource.setDriverClassName();
			datasource.setUrl(database.getUrl());
			datasource.setUsername(database.getUsuario());
			datasource.setPassword(database.getPassword());
			//			
		}else{
			return null;
		}

		return datasource;

	}
	
	
	
}
