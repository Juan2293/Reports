package co.gov.yumbo.reporter.app.service;
import java.util.List;

import co.gov.yumbo.reporter.app.model.Area;

public interface IAreaService {

	List<Area> getAllAreas();
	Area getAreaById(Long areaId);
	List<Area> getAreasByUsuarioId(Long usuarioId);
	Area getAreaByName(String nombre) throws Exception ;
	void createArea(Area area) throws Exception;
	void updateArea(Area area) throws Exception;
	void deleteArea(Long areaId);
}
