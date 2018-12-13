package co.gov.yumbo.reporter.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.gov.yumbo.reporter.app.model.Parametro;

@Transactional
@Repository
public class ParametroDAO implements IParametroDAO {

	@PersistenceContext
	private EntityManager entityManager;	
	

	@Override
	public Parametro getParametroById(long parametroId) {
		return entityManager.find(Parametro.class, parametroId);
	}

	@Override
	public void createParametro(Parametro parametro) {
		entityManager.persist(parametro);
	}

	@Override
	public void updateParametro(Parametro parametro) {
		entityManager.merge(parametro);
	}

	@Override
	public void deleteParametro(long parametroId) {
		entityManager.remove(getParametroById(parametroId));
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> getAllParametrosByReportId(long reporteId) {

		return (List<Parametro>) entityManager.createQuery("FROM Parametro p where p.reporte.reporteId = ?1  ")
				.setParameter(1, reporteId)
				.getResultList();
	}

	@Override
	public void deleteParametroByReporteId(long reporteId) {
		Query query = entityManager.createQuery("Delete Parametro p where p.reporte.reporteId = ?1  ");
		query.setParameter(1, reporteId);
		query.executeUpdate();
	}

}
