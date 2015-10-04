package org.demo.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (uniqueConstraints = @UniqueConstraint (columnNames = { "name", "category" }))
@SuppressWarnings("unused")
public class Subcategory extends Domain {

    private static Log logger = LogFactory.getLog(Subcategory.class);

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size (min=1, max=100)
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    @NotNull
    @Valid
    @Embedded
    private Record record = new Record();
    public Record getRecord() {
        return record;
    }
    public void setRecord(Record record) {
        this.record = record;
    }

	@NotNull
	@ManyToOne
    @JoinColumn(name = "category")
	private Category category;
    public Category getCategory() {
        return this.category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

	public Subcategory() {}

    public Subcategory(String name, Category category) {
        logger.info("New object " + Subcategory.class);
        this.name = (name == null)? null : name.toUpperCase();
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subcategory that = (Subcategory) o;

        return !(category != null ? !category.equals(that.category) : that.category != null) && !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", record=" + record +
                '}';
    }
}
