package co.gov.yumbo.reporter.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="area")
public class Area {
	
	@Id
	@SequenceGenerator(name = "areaSeq", sequenceName = "area_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "areaSeq")
	@Column(name="area_id")
	private Long areaId;
	
	@Column(name="nombre")
	private String nombre;
	
//	@ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE
//            })
//	   @JoinTable(name = "area_usuario",
//	            joinColumns = { @JoinColumn(name = "area_id") },
//	            inverseJoinColumns = { @JoinColumn(name = "user_id") })
//	    private Set<User> usuario = new HashSet<>();
	
//	 @ManyToMany(fetch = FetchType.LAZY,
//     cascade = {
//         CascadeType.PERSIST,
//         CascadeType.MERGE
//     },
//     mappedBy = "area")
//private Set<User> usuario = new HashSet<>();
//	
//	public Set<User> getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Set<User> usuario) {
//		this.usuario = usuario;
//	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
