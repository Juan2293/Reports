package co.gov.yumbo.reporter.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.yumbo.reporter.app.dto.DatabaseReDTO;
import co.gov.yumbo.reporter.app.dto.UserDto;
import co.gov.yumbo.reporter.app.dto.UsuarioRolDTO;
import co.gov.yumbo.reporter.app.model.User;
import co.gov.yumbo.reporter.app.service.UserService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"http://192.168.8.40:4200","http://localhost:4200", "http://localhost:8080"})
public class UsuariosController {

	@Autowired
	private UserService usuarioService;

	//	@PreAuthorize("hasAnyRole('ADMIN')")
	//	@GetMapping("/usuarios")
	//	public List<User> getUsuarios() {
	//		return usuarioService.getAllUsuarios();
	//	}

//	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/usuarios-rol")
	public List<UsuarioRolDTO> getUsuariosRolesDTO() {
		return usuarioService.getAllUsuariosRolDTO();
	}

//	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/create")
	public  ResponseEntity<String> createUsuario(@RequestBody UserDto userDto) {
		try {
			usuarioService.createUser(userDto);
			return null;
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/update")
	public  ResponseEntity<String> updateUsuario(@RequestBody UserDto usuarioDTO) {
		
		try {
			usuarioService.updateUsuario(usuarioDTO);
			return null;
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	} 
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/update-usuario-areas")
	public  ResponseEntity<String> updateUsuarioAreas(@RequestBody UserDto usuarioDTO) {
		usuarioService.updateUsuariAreas(usuarioDTO);
		return null;
	} 
}

