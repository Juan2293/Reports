package co.gov.yumbo.reporter.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.yumbo.reporter.app.dao.IAreaDAO;
import co.gov.yumbo.reporter.app.dao.IDatabaseReDAO;
import co.gov.yumbo.reporter.app.model.Area;
import co.gov.yumbo.reporter.app.model.DatabaseRe;
import oracle.net.aso.a;
@Service
public class AreaService implements IAreaService {

	@Autowired
	private IAreaDAO areaDAO;
	
	@Override
	public List<Area> getAllAreas() {
		return areaDAO.getAllAreas();
	}

	@Override
	public Area getAreaById(Long areaId) {
		return areaDAO.getAreaById(areaId);
	}

	@Override
	public void createArea(Area area) throws Exception {

		if (area.getNombre().trim().equals("") || area.getNombre()==null) {
			throw new Exception("Ingrese el nombre del área");
		}
		if (areaDAO.getAreaByName(area.getNombre().trim())!=null) {
			throw new Exception("Ya existe un área con el  nombre: "+ area.getNombre().trim());
		}
		areaDAO.createArea(area);
		
	}

	@Override
	public void updateArea(Area area)  throws Exception  {
		
		Area area_aux = areaDAO.getAreaById(area.getAreaId());
		if (area.getNombre().trim().equals("") || area.getNombre()==null) {
			throw new Exception("Ingrese el nombre del área");
		}
		if (areaDAO.getAreaByName(area.getNombre().trim())!=null &&
				!area_aux.getNombre().trim().equalsIgnoreCase(area.getNombre().trim()) ) {
			throw new Exception("Ya existe un área con el  nombre:  "+ area.getNombre().trim());
		}
//		if (!area_aux.getNombre().trim().equalsIgnoreCase(area.getNombre().trim()) && areaDAO.getAreaByName(area.getNombre().trim())!=null ) {
//			throw new Exception("Ya existe un área con el  nombre:  "+ area.getNombre().trim());
//		}
		areaDAO.updateArea(area);
	}

	@Override
	public void deleteArea(Long areaId) {
		areaDAO.deleteArea(areaId);		
	}

	@Override
	public List<Area> getAreasByUsuarioId(Long usuarioId) {
		return areaDAO.getAreasByUsuarioId(usuarioId);
	}

	@Override
	public Area getAreaByName(String nombre) throws Exception {
		
		return areaDAO.getAreaByName(nombre);
	}



}
