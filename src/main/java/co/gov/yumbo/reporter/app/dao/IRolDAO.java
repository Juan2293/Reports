package co.gov.yumbo.reporter.app.dao;

import java.util.List;

import co.gov.yumbo.reporter.app.model.Role;
import co.gov.yumbo.reporter.app.model.User;

public interface IRolDAO {
	Role getRolById(Long id);
	Role getRolByName(String name);
	List<Role> getAllRols();
}
