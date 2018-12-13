package co.gov.yumbo.reporter.app.service;

import java.util.List;

import co.gov.yumbo.reporter.app.model.Role;

public interface IRolService {
	Role getRolById(Long id);
	List<Role> getAllRols();
}
