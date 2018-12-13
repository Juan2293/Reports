package co.gov.yumbo.reporter.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.yumbo.reporter.app.dto.ReporteDatabaseDTO;
import co.gov.yumbo.reporter.app.dto.ReporteParametroDTO;
import co.gov.yumbo.reporter.app.files.DatabaseAuxService;
import co.gov.yumbo.reporter.app.files.ExcelOutputServiceImpl;
import co.gov.yumbo.reporter.app.model.Parametro;
import co.gov.yumbo.reporter.app.model.Reporte;
import co.gov.yumbo.reporter.app.service.IParametroService;
import co.gov.yumbo.reporter.app.service.IReporteService;


@RestController
@RequestMapping("/reporte")
@CrossOrigin(origins = {"http://192.168.8.40:4200","http://localhost:4200", "http://localhost:8080"})
public class ReporteController {

	@Autowired
	private IReporteService reporteService;
	@Autowired
	private IParametroService parametroService;




	@PostMapping("/create")
	public  ResponseEntity<String> createReporte(@RequestBody ReporteParametroDTO reporteParametroDTO) throws Exception {

		try {
			Long reporteId = reporteService.createReporte(reporteParametroDTO.getReporte());
			Reporte reporte = reporteService.getReporteById(reporteId);

			Parametro parametros[] = reporteParametroDTO.getParametros();
			//orden de los parametros
			for (int i = 0; i < parametros.length; i++) {
				parametros[i].setReporte(reporte);
				parametros[i].setOrden(i+1); // orden de los parametros
				parametroService.createParametro(parametros[i]);
			}
			return null;
		} catch (Exception e) {
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/update")
	public  ResponseEntity<String> updateReporte(@RequestBody ReporteParametroDTO reporteParametroDTO) throws Exception {

		try {
			Reporte reporte = reporteService.getReporteById(reporteParametroDTO.getReporte().getReporteId());
			if(reporte!=null) {
				reporteService.updateReporte(reporteParametroDTO.getReporte());

				// borro los parametros que hay (Solucion rapida)
				parametroService.deleteParametroByReporteId(reporte.getReporteId());

				//los creo de nuevo()
				Parametro parametros[] = reporteParametroDTO.getParametros();
				//orden de los parametros
				try {
					for (int i = 0; i < parametros.length; i++) {
						parametros[i].setReporte(reporte);
						parametros[i].setOrden(i+1); // orden de los parametros
						parametros[i].setParametroId(null); // para que no arroje error por mandarle  un id lleno
						parametroService.createParametro(parametros[i]);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
			return null;
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@DeleteMapping("/delete/{reporteId}")
	public  void deleteReporte(@PathVariable Long reporteId) {
		Reporte reporte = reporteService.getReporteById(reporteId);

		if(reporte!=null) {
			// borro los parametros que hay (Solucion rapida)
			parametroService.deleteParametroByReporteId(reporte.getReporteId());
			reporteService.deleteReporte(reporte.getReporteId());
			
		}
	} 

	@GetMapping("/reportes")
	public List<Reporte> getReportes() {
		return reporteService.getAllReportes();
	}
	
	@GetMapping("/reports-databases")
	public List<ReporteDatabaseDTO> getReportesDatabases() {
		return reporteService.getAllReporteDatabaseDTO();
	}

	@GetMapping("/reporte/{reporteId}")
	public Reporte getReporteById(@PathVariable Long reporteId) {
		return reporteService.getReporteById(reporteId);
	}

	@GetMapping("/reportes-by-database/{dbId}")
	public List<Reporte> getReportesByDatabaseId(@PathVariable Long dbId) {
		return reporteService.getAllReportesByDatabaseId(dbId);
	}
	
	@GetMapping("/reportes-areas-by-usuario/{usuarioId}")
	public List<ReporteDatabaseDTO> getAllReportesAreasByUsuarioId(@PathVariable Long usuarioId) {
		return reporteService.getAllReportesAreasByUsuarioId(usuarioId);
	}



	@PostMapping("/generate")
	public ResponseEntity<?> downloadExcelOutputExl(@RequestBody ReporteParametroDTO reporteParametroDTO, HttpServletResponse response) throws IOException{
		try {
			ExcelOutputServiceImpl excelOutputService = new ExcelOutputServiceImpl();
			excelOutputService.createExcelOutputExcel(response,reporteParametroDTO);
			return null;
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// cuando se quiere consultar el select desde la aplicacion
	@PostMapping("/resultadoConsulta")
	public List<ArrayList<String>> getConsultaResultado(@RequestBody ReporteParametroDTO reporteParametroDTO, HttpServletResponse response) throws IOException{

		DatabaseAuxService databaseAuxService = new DatabaseAuxService();			
		return databaseAuxService.getResultadoConsulta(reporteParametroDTO);

	}



}
