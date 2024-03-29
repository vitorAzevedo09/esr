package com.algaworks.algafoodapi.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Represents a city within a geographical region.
 * Each city has a unique identifier, a name, and is associated with a state.
 *
 * <p>
 * The {@code id} field is automatically generated by the database and serves
 * as the primary key for identifying cities.
 * </p>
 *
 * <p>
 * The {@code name} field contains the name of the city and should not be null.
 * </p>
 *
 * <p>
 * The {@code state} field represents the state to which the city belongs.
 * It is associated with the {@link State} class and should not be null.
 * </p>
 *
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * State state = stateRepository.findById(1L).orElseThrow(StateNotFoundException::new);
 * City city = new City();
 * city.setName("New York");
 * city.setState(state);
 * cityRepository.save(city);
 * }</pre>
 * </p>
 *
 * @Entity(name = cities)
 */
@Entity(name = "cities")
public class City {

    /**
     * The unique identifier for the city.
     * This field is automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Valid
    @NotNull
    private Long id;

    /**
     * The name of the city.
     * It cannot be null and should provide the name of the city.
     */
    @NotNull
    private String name;

    /**
     * The state to which the city belongs.
     * It cannot be null and is associated with the {@link State} class.
     */
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    /**
     * Constructs a new city with the specified identifier and name.
     *
     * @param id   The unique identifier for the city.
     * @param name The name of the city.
     */
    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Default constructor for the city.
     */
    public City() {
    }

    /**
     * Retrieves the unique identifier of the city.
     *
     * @return The city's unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the city.
     *
     * @param id The city's unique identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the city.
     *
     * @return The name of the city.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the city.
     *
     * @param name The name of the city.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the state to which the city belongs.
     *
     * @return The state to which the city belongs.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state to which the city belongs.
     *
     * @param state The state to which the city belongs.
     */
    public void setState(State state) {
        this.state = state;
    }
}
