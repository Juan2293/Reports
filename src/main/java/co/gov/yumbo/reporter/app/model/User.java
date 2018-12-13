package co.gov.yumbo.reporter.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name="usuario")
@Entity
public class User {

    @Id
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", initialValue = 3, allocationSize = 3)
    @GeneratedValue(generator = "usuario_seq")
    private long id;
    @Column
    private String username;
    @Column
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @Column(name="descripcion")
	private String descripcion;
   
    @OneToOne
	@JoinColumn(name = "rol_id")
    private Role role; 
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "area_usuario",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "area_id") })
    
//       @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//       @JoinTable(name = "area_usuario", joinColumns = {
//          @JoinColumn(name = "user_id") }, inverseJoinColumns = {
//          @JoinColumn(name = "area_id") })
  private Set<Area> area = new HashSet<>();


//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_ROLES", joinColumns = {
//            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
//            @JoinColumn(name = "ROLE_ID") })
//    private Set<Role> roles;

    public Set<Area> getArea() {
		return area;
	}

	public void setArea(Set<Area> area) {
		this.area = area;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
