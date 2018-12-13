package co.gov.yumbo.reporter.app.dao;


import java.util.List;

import co.gov.yumbo.reporter.app.model.Area;

public interface IAreaDAO {

	List<Area> getAllAreas();
	List<Area> getAreasByUsuarioId(Long usuarioId);
	Area getAreaById(Long areaId);
	Area getAreaByName(String nombre) ;
	void createArea(Area area);
	void updateArea(Area area);
	void deleteArea(Long areaId);
}
