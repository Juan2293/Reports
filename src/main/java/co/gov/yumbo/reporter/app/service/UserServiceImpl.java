package co.gov.yumbo.reporter.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.gov.yumbo.reporter.app.dao.IRolDAO;
import co.gov.yumbo.reporter.app.dao.UserDao;
import co.gov.yumbo.reporter.app.dto.UserDto;
import co.gov.yumbo.reporter.app.dto.UsuarioRolDTO;
import co.gov.yumbo.reporter.app.model.Role;
import co.gov.yumbo.reporter.app.model.User;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private IRolDAO rolDAO;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//busca el usuario;
		User user = userDao.getUsuarioByUsername(username);

		// si el usuario no existe manda esto
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		//si el usuario existe envia las credenciales 
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private List<SimpleGrantedAuthority> getAuthority(User user) {

		//se agreg la información de los roles en el TOKEN

		//		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		SimpleGrantedAuthority authorities = new SimpleGrantedAuthority("ROLE_"+user.getRole().getName().trim());
		//		user.getRoles().forEach(role -> {
		//			//authorities.add(new SimpleGrantedAuthority(role.getName()));
		//			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		//		});
		//		return authorities;
		//		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return Arrays.asList(authorities);
	}

	@Override
	public List<User> getAllUsuarios() {
		List<User> list = new ArrayList<>();
		userDao.getAllUsuarios().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void deleteUsuario(long id) {
		userDao.deleteUsuario(id);
	}

	@Override
	public User getUsuarioByUsername(String username) {
		return userDao.getUsuarioByUsername(username);
	}

	@Override
	public User getUsuarioById(Long id) {
		return userDao.getUsuarioById(id);
	}

	@Override
	public User createUser(UserDto user) throws Exception {
		
//		User usuario =null;
//		userDao.getUsuarioByUsername(user.getUsername().trim());
//		
		if(userDao.getUsuarioByUsername(user.getUsername().trim())!=null) {
			throw new Exception("El usuario ingresado ya existe");
		}
		if (user.getPassword()==null || user.getPassword().trim().equals("")  ) {
			throw new Exception("Ingrese un valor para el password");
		}
		
		
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setDescripcion(user.getDescripcion());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		

		//se le asigna el rol
		//		Set<Role> set = new HashSet<Role>();
		//		Role role = rolDAO.getRolByName(user.getRol());
		//		set.add(role);
		newUser.setRole(rolDAO.getRolById(new Long(user.getRol())));


		return userDao.createUser(newUser);
	}



	@Override
	public List<UsuarioRolDTO> getAllUsuariosRolDTO() {
		return userDao.getAllUsuariosRolDTO();
	}

	@Override
	public void updateUsuario(UserDto usuarioDTO) throws Exception {
	
		User usuario = userDao.getUsuarioByUsername(usuarioDTO.getUsername().trim());
		if(usuario!=null && !usuarioDTO.getUsername().equalsIgnoreCase(userDao.getUsuarioById(usuarioDTO.getId()).getUsername())) {
			throw new Exception("El usuario ingresado ya existe");
		}
		if (usuarioDTO.getPassword().trim().equals("") || usuarioDTO.getPassword()==null) {
			throw new Exception("Ingrese un valor para el password");
		}
		
		User user = userDao.getUsuarioById(usuarioDTO.getId());
		user.setUsername(usuarioDTO.getUsername());
		user.setDescripcion(usuarioDTO.getDescripcion());
		user.setRole(rolDAO.getRolById(new Long(usuarioDTO.getRol())));
		
		
		userDao.updateUsuario(user);
	}

	@Override
	public void updateUsuariAreas(UserDto usuarioDTO) {

		User usuario = userDao.getUsuarioById(usuarioDTO.getId());
		usuario.setArea(usuarioDTO.getAreas());
		
		userDao.updateUsuario(usuario);
		
	}



}
