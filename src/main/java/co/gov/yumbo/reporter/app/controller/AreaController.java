package co.gov.yumbo.reporter.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.yumbo.reporter.app.dto.DatabaseReDTO;
import co.gov.yumbo.reporter.app.model.Area;
import co.gov.yumbo.reporter.app.model.DatabaseRe;
import co.gov.yumbo.reporter.app.service.IAreaService;
import co.gov.yumbo.reporter.app.service.IDatabaseReService;

@RestController
@RequestMapping("/area")
@CrossOrigin(origins = {"http://192.168.8.40:4200","http://localhost:4200", "http://localhost:8080"})
public class AreaController {

	@Autowired
	private IAreaService areaService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/create")
	public  ResponseEntity<String> createArea(@RequestBody Area area) {
		try {
			areaService.createArea(area);
			return null;
		} catch (Exception e) {
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/areas")
	public List<Area> getAreas() {
		return areaService.getAllAreas();
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/update")
	public  ResponseEntity<String> updateArea(@RequestBody Area area)  {
		try {
			areaService.updateArea(area);
			return null;
		} catch (Exception e) {
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/delete/{areaId}")
	public  void deleteArea(@PathVariable Long areaId) {
		areaService.deleteArea(areaId);
	} 
	
	@GetMapping("/areas-by-usuario/{usuarioId}")
	public  List<Area> getAreasByUsuarioId(@PathVariable Long usuarioId) {
		return areaService.getAreasByUsuarioId(usuarioId);
	} 



}
