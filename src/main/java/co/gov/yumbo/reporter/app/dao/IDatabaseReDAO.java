package co.gov.yumbo.reporter.app.dao;


import java.util.List;

import co.gov.yumbo.reporter.app.model.Area;
import co.gov.yumbo.reporter.app.model.DatabaseRe;

public interface IDatabaseReDAO {

	List<DatabaseRe> getAllDatabasesRe();
	DatabaseRe getDatabaseReById(Long databaseReId);
	void createDatabaseRe(DatabaseRe databaseRe);
	void updateDatabaseRe(DatabaseRe databaseRe);
	void deleteDatabaseRe(Long databaseReId);
	DatabaseRe getDatabaseByName(String nombre);
}
