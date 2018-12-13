package co.gov.yumbo.reporter.app.dao;

import co.gov.yumbo.reporter.app.dto.UsuarioRolDTO;
import co.gov.yumbo.reporter.app.model.User;

import java.util.List;



public interface UserDao {
	
	List<User> getAllUsuarios();
	User createUser(User user);
    void deleteUsuario(Long id);
    void updateUsuario(User user);
    User getUsuarioByUsername(String username);
    User getUsuarioById(Long id);
	List<UsuarioRolDTO> getAllUsuariosRolDTO();
    
    

}
