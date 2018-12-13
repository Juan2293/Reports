package co.gov.yumbo.reporter.app.controller;

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
import co.gov.yumbo.reporter.app.model.DatabaseRe;
import co.gov.yumbo.reporter.app.service.IDatabaseReService;

@RestController
@RequestMapping("/database")
@CrossOrigin(origins = {"http://192.168.8.40:4200","http://localhost:4200", "http://localhost:8080"})
public class DatabaseReController {

	@Autowired
	private IDatabaseReService databaseReService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/create")
	public  ResponseEntity<String> createDatabase(@RequestBody DatabaseReDTO databaseReDTO) {
		try {
			databaseReService.createDatabaseRe(databaseReDTO.getDatabasRe());
			return null;
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/databases")
	public List<DatabaseRe> getReportes() {
		return databaseReService.getAllDatabasesRe();
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/update")
	public  ResponseEntity<String> updateDatabase(@RequestBody DatabaseReDTO databaseReDTO) {
		try {
			databaseReService.updateDatabaseRe(databaseReDTO.getDatabasRe());
			return null;
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/delete/{dbId}")
	public  void deleteDatabase(@PathVariable Long dbId) {
		databaseReService.deleteDatabaseRe(dbId);

	} 


}
