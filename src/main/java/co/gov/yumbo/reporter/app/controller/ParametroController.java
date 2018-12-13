package co.gov.yumbo.reporter.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.yumbo.reporter.app.model.Parametro;
import co.gov.yumbo.reporter.app.service.IParametroService;

@RestController
@RequestMapping("/parametro")
@CrossOrigin(origins = {"http://192.168.8.40:4200","http://localhost:4200", "http://localhost:8080"})
public class ParametroController {

	@Autowired
	private IParametroService parametroService;
	
	
	
	@GetMapping("/parametros/{reporteId}")
	public ResponseEntity<List<Parametro>>  getParametrosByReporteId(@PathVariable Long reporteId) {
		List<Parametro> list = parametroService.getAllParametrosByReportId(reporteId);
		return new ResponseEntity<List<Parametro>>(list, HttpStatus.OK);
	}
}
