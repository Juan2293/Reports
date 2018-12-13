package co.gov.yumbo.reporter.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO;
import co.gov.yumbo.reporter.app.model.Area;


@Transactional
@Repository
public class AreaDAO implements IAreaDAO{

	@PersistenceContext
	private EntityManager entityManager;


	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAllAreas() {
		String hql = "FROM Area order by nombre asc";
		return (List<Area>)entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Area getAreaById(Long areaId) {

		return entityManager.find(Area.class, areaId);
	}

	@Override
	public void createArea(Area area) {
		entityManager.persist(area);
	}

	@Override
	public void updateArea(Area area) {
		entityManager.merge(area);

	}

	@Override
	public void deleteArea(Long areaId) {
		entityManager.remove(getAreaById(areaId));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAreasByUsuarioId(Long usuarioId){
		String hql = "SELECT u.area from User u where u.id = ?1 ";
		return (List<Area>)entityManager.createQuery(hql).setParameter(1, usuarioId)
				.getResultList();
	}

	@Override
	public Area getAreaByName(String nombre) {
		String hql = "FROM Area a where lower(a.nombre)   =  lower( :nombre ) ";
		Area area = null;
		try {
			 area = (Area) entityManager.createQuery(hql).setParameter("nombre", nombre).getSingleResult();
			return area;
		} catch (Exception e) {
			return area;
		}

	}

	//	@SuppressWarnings("unchecked")
	//	@Override
	//	public List<ReporteDatabaseDTO> getAllReporteDatabaseDTO() {
	//		// el orden depende del constructor que esta en el DTO
	//		String hql = "SELECT  distinct  new co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO(r.reporteId , r.nombre, d.dbId , d.tipo , d.nombre , a.nombre) "
	//				+ " FROM Reporte r, DatabaseRe d, Area a "
	//				+ " Where "
	//				+ "  r.database.dbId = d.dbId and "
	//				+ "  a.areaId = r.area.areaId ";
	//		return (List<ReporteDatabaseDTO>)entityManager.createQuery(hql).getResultList();
	//	}





}
