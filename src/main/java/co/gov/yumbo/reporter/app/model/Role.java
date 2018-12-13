package co.gov.yumbo.reporter.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="rol")
@Entity
public class Role {

	@Id
	@SequenceGenerator(name = "rol_seq", sequenceName = "rol_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rol_seq")
	@Column(name="rol_id")
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
