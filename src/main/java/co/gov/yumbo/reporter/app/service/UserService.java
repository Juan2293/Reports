package co.gov.yumbo.reporter.app.service;


import java.util.List;

import co.gov.yumbo.reporter.app.dto.UserDto;
import co.gov.yumbo.reporter.app.dto.UsuarioRolDTO;
import co.gov.yumbo.reporter.app.model.User;

public interface UserService {

    User createUser(UserDto user) throws Exception;
    List<User> getAllUsuarios();
    void deleteUsuario(long id);
    void updateUsuario(UserDto usuarioDTO) throws Exception;
    void updateUsuariAreas(UserDto usuarioDTO);
    User getUsuarioByUsername(String username);
    User getUsuarioById(Long id);
    List<UsuarioRolDTO> getAllUsuariosRolDTO();

}
