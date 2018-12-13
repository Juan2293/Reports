package co.gov.yumbo.reporter.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.yumbo.reporter.app.model.Role;
import co.gov.yumbo.reporter.app.service.IRolService;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = {"http://192.168.8.40:4200","http://localhost:4200", "http://localhost:8080"})
public class RolController {

	@Autowired
	private IRolService rolService;
	
	
	
	@GetMapping("/roles")
	public List<Role>  getParametrosByReporteId() {
		return rolService.getAllRols();
	}
}
