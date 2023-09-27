package com.algaworks.algafoodapi.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Represents a group or role that can be assigned to users in the system.
 * Each group has a unique identifier, a name, and may have one or more
 * associated permissions.
 *
 * <p>
 * The {@code id} field is automatically generated by the database and serves
 * as the primary key for identifying groups.
 * </p>
 *
 * <p>
 * The {@code name} field contains the name of the group and should not be null.
 * It provides a descriptive name for the group.
 * </p>
 *
 * <p>
 * The {@code permissions} field represents a collection of permissions
 * associated with the group.
 * It is associated with the {@link Permission} class and may be an empty list.
 * </p>
 *
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * Group group = new Group();
 * group.setName("Admins");
 * Permission permission1 = new Permission();
 * permission1.setName("READ_USERS");
 * Permission permission2 = new Permission();
 * permission2.setName("WRITE_USERS");
 * group.getPermissions().add(permission1);
 * group.getPermissions().add(permission2);
 * groupRepository.save(group);
 * }</pre>
 * </p>
 *
 * @Entity(name = "groups")
 */
@Entity(name = "groups")
public class Group {

	/**
	 * The unique identifier for the group.
	 * This field is automatically generated by the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The name of the group.
	 * It cannot be null and should provide a descriptive name for the group.
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * The collection of permissions associated with the group.
	 * It may be an empty list and is associated with the {@link Permission} class.
	 */
	@ManyToMany
	@JoinTable(name = "group_permission", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> permissions = new ArrayList<>();

	/**
	 * Retrieves the unique identifier of the group.
	 *
	 * @return The group's unique identifier.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the group.
	 *
	 * @param id The group's unique identifier.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the group.
	 *
	 * @return The name of the group.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the group.
	 *
	 * @param name The name of the group.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the collection of permissions associated with the group.
	 *
	 * @return The collection of permissions associated with the group.
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * Sets the collection of permissions associated with the group.
	 *
	 * @param permissions The collection of permissions associated with the group.
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
