package org.gestor.proyectos.database;


/**
 * 
 * Clase que encapsulará información del proyecto
 * 
 * @author jose
 *
 */
public class Project {

	long id;
	String name;
	String description;
	String department;
	
	
	
	public long getId() {
		return id;
	}


	public Project(String name, String department, String description) {
		super();
		this.name = name;
		this.description = description;
		this.department = department;
	}





	public Project() {
		// TODO Auto-generated constructor stub
	}


	public void setId(int id) {
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}



}
