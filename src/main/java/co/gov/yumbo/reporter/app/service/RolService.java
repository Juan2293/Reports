package co.gov.yumbo.reporter.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.yumbo.reporter.app.dao.IRolDAO;
import co.gov.yumbo.reporter.app.model.Role;

@Service
public class RolService implements IRolService {
	
	@Autowired
	private IRolDAO rolDAO;

	@Override
	public Role getRolById(Long id) {
		return rolDAO.getRolById(id);
	}

	@Override
	public List<Role> getAllRols() {
		return rolDAO.getAllRols();
	}

}
