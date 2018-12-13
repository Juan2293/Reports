package co.gov.yumbo.reporter.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO;
import co.gov.yumbo.reporter.app.model.Area;
import co.gov.yumbo.reporter.app.model.Reporte;

@Transactional
@Repository
public class ReporteDAO implements IReporteDAO {

	@PersistenceContext
	private EntityManager entityManager;	

	@SuppressWarnings("unchecked")
	@Override
	public List<Reporte> getAllReportes() {
		String hql = "FROM Reporte order by nombre asc";
		return (List<Reporte>)entityManager.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDatabaseDTO> getAllReporteDatabaseDTO() {
		// el orden depende del constructor que esta en el DTO
		String hql = "SELECT  distinct  new co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO(r.reporteId , r.nombre, d.dbId , d.tipo , d.nombre , a.nombre, r.descripcion ) "
				+ " FROM Reporte r, DatabaseRe d, Area a "
				+ " Where "
				+ "  r.database.dbId = d.dbId and "
				+ "  a.areaId = r.area.areaId ";
		return (List<ReporteDatabaseDTO>)entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Reporte getReporteById(long reporteId) {
		return entityManager.find(Reporte.class, reporteId);
	
	}

	@Override
	public Long createReporte(Reporte reporte) {
		entityManager.persist(reporte);
		return reporte.getReporteId();
	}

	@Override
	public void updateReporte(Reporte reporte) {
		entityManager.merge(reporte);
		
	}

	@Override
	public void deleteReporte(long reporteId) {
		entityManager.remove(getReporteById(reporteId));
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Reporte> getAllReportesByDatabaseId(Long dbId) {
		return (List<Reporte>) entityManager.createQuery("FROM Reporte r where r.database.dbId = ?1")
				.setParameter(1, dbId)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteDatabaseDTO> getAllReportesAreasByUsuarioId(Long usuarioId) {
		String hql = "SELECT  distinct  new co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO(r.reporteId , r.nombre, d.dbId , d.tipo , d.nombre , a.nombre) "
				+ " FROM Reporte r, DatabaseRe d, Area a, User u "
				+ "  inner  join u.area ua "
				+ "  Where"
				+ "  u.id = ?1 and "
				+ "  r.database.dbId = d.dbId and "
				+ "  a.areaId = r.area.areaId and "
				+ " ua.areaId = r.area.areaId "
				;
		return (List<ReporteDatabaseDTO>)entityManager.createQuery(hql).setParameter(1, usuarioId).getResultList();
	}

	@Override
	public Reporte getReporteByName(String nombre) {
		String hql = "FROM Reporte r where lower(r.nombre)   =  lower( :nombre ) ";
		Reporte reporte = null;
		try {
			reporte = (Reporte) entityManager.createQuery(hql).setParameter("nombre", nombre).getSingleResult();
			return reporte;
		} catch (Exception e) {
			return reporte;
		}
	}

}
