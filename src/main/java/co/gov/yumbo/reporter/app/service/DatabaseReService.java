package co.gov.yumbo.reporter.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.yumbo.reporter.app.dao.IDatabaseReDAO;
import co.gov.yumbo.reporter.app.model.Area;
import co.gov.yumbo.reporter.app.model.DatabaseRe;
@Service
public class DatabaseReService implements IDatabaseReService {

	@Autowired
	private IDatabaseReDAO databaseReDAO;
	
	@Override
	public DatabaseRe getDatabaseReById(Long databaseReId) {
		return databaseReDAO.getDatabaseReById(databaseReId);
	}

	@Override
	public void createDatabaseRe(DatabaseRe databaseRe) throws Exception {
		
		if (databaseRe.getNombre().trim().equals("") || databaseRe.getNombre()==null) {
			throw new Exception("Ingrese el nombre de la base de datos");
		}
		if (databaseReDAO.getDatabaseByName(databaseRe.getNombre().trim())!=null) {
			throw new Exception("Ya existe una base de datos con el  nombre: "+ databaseRe.getNombre().trim());
		}
		databaseReDAO.createDatabaseRe(databaseRe);
	}

	@Override
	public void updateDatabaseRe(DatabaseRe databaseRe) throws Exception {
		
		DatabaseRe databaseRe_aux = databaseReDAO.getDatabaseReById(databaseRe.getDbId());
		if (databaseRe.getNombre().trim().equals("") || databaseRe.getNombre()==null) {
			throw new Exception("Ingrese el nombre de la base de datos");
		}
		if (databaseReDAO.getDatabaseByName(databaseRe.getNombre().trim())!=null &&
				!databaseRe_aux.getNombre().trim().equalsIgnoreCase(databaseRe.getNombre().trim()) ) {
			throw new Exception("Ya existe una base de datos con el  nombre:  "+ databaseRe.getNombre().trim());
		}
		
		databaseReDAO.updateDatabaseRe(databaseRe);
	}

	@Override
	public void deleteDatabaseRe(Long databaseReId) {
		databaseReDAO.deleteDatabaseRe(databaseReId);
	}

	@Override
	public List<DatabaseRe> getAllDatabasesRe() {
		return databaseReDAO.getAllDatabasesRe();
	}

}
