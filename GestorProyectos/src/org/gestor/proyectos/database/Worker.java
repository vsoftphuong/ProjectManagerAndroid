package org.gestor.proyectos.database;


/**
 * 
 * Clase que encapsulará información del trabajador
 * 
 * @author jose
 *
 */
public class Worker {
	
	int id;
	String name;
	int phone;
	String email;
	String description;
	long projectId;
	
	public Worker(){
		
	}
	
	public Worker(String name, int phone, String email,
			String description, long projectId) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.description = description;
		this.projectId = projectId;
	}
	public int getId() {
		return id;
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
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	
	
	

}
