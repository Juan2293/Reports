package co.gov.yumbo.reporter.app.service;
import java.util.List;

import co.gov.yumbo.reporter.app.model.DatabaseRe;

public interface IDatabaseReService {

	List<DatabaseRe> getAllDatabasesRe();
	DatabaseRe getDatabaseReById(Long databaseReId);
	void createDatabaseRe(DatabaseRe databaseRe) throws Exception;
	void updateDatabaseRe(DatabaseRe databaseRe) throws Exception;
	void deleteDatabaseRe(Long databaseReId);
}
