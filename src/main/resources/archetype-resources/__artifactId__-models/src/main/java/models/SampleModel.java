package ${package}.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;

/**
 * A sample model
 */
@Entity
@Table(name = "Sample_Models")
public class SampleModel {


    /**
     * THe model's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private long id;

    /**
     * The model's name.
     */
    @Column(length = 64, nullable = false)
    private String name;

    // Define more properties here...

    /**
     * Default constructor used by Hibernate
     */
    /* package */ SampleModel() {
        // For Hibernate
    }

    /**
     * Constructor.
     *
     * @param name The name for the model.
     */
    public SampleModel(String name) {
        update(name);
    }

    /**
     * Updates this object.
     *
     * @param name The new name for the object.
     */
    public void update(String name) {
        validate(name);
        this.name = name;
    }

    /**
     * @return The model's id.
     */
    public long getId() {
        return id;
    }

    /**
     * @return The model's name.
     */
    public String getName() {
        return name;
    }

    // Add more getters here...

    /**
     * Validates the given arguments
     *
     * @param name The name to be validated
     * @throws IllegalArgumentException If any argument is wrong.
     */
    private void validate(String name) throws IllegalArgumentException {
        // Add validations here...
    }

    // Add more private methods here...

    /**
     * Equals based on the id.
     *
     * @param obj The object to be compared with.
     * @return {@code true} if they are the same object, or {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SampleModel other = (SampleModel) obj;

        return this.id == other.id;
    }

    /**
     * @return The hashcode based on the id.
     */
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}