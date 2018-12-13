package co.gov.yumbo.reporter.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.gov.yumbo.reporter.app.model.Area;
import co.gov.yumbo.reporter.app.model.DatabaseRe;

@Transactional
@Repository
public class DatabaseReDAO implements IDatabaseReDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public DatabaseRe getDatabaseReById(Long databaseReId) {
		return entityManager.find(DatabaseRe.class, databaseReId);
	}

	@Override
	public void createDatabaseRe(DatabaseRe databaseRe) {
		entityManager.persist(databaseRe);
	}

	@Override
	public void updateDatabaseRe(DatabaseRe databaseRe) {
		entityManager.merge(databaseRe);
	}

	@Override
	public void deleteDatabaseRe(Long databaseReId) {
		entityManager.remove(getDatabaseReById(databaseReId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DatabaseRe> getAllDatabasesRe() {

		String hql = "FROM DatabaseRe order by nombre asc";
		return (List<DatabaseRe>)entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public DatabaseRe getDatabaseByName(String nombre) {
		System.out.println("entro a validar -->"+ nombre);
		String hql = "FROM DatabaseRe d where lower(d.nombre) =  lower( :nombre ) ";
		DatabaseRe database = null;
		try {
			database = (DatabaseRe) entityManager.createQuery(hql).setParameter("nombre", nombre).getSingleResult();
			System.out.println("entro  -->"+ nombre);
			return database;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		
			return database;
		}

	}


	

}
