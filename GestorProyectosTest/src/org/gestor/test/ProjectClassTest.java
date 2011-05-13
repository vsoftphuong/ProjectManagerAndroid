package org.gestor.test;

import org.gestor.proyectos.database.Project;
import org.gestor.proyectos.database.ProjectDatabase;
import org.junit.After;
import org.junit.Before;

import junit.framework.Assert;
import android.test.AndroidTestCase;

public class ProjectClassTest extends AndroidTestCase {
	
	ProjectDatabase projectManager;
	
	@Before
	public void setUp() throws Exception {
		projectManager = new ProjectDatabase(this.getContext());
		projectManager.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	public void testAddProject() {
		String projectName="Nombre";
		String projectDescription="Descripción breve";
		String projectDepartment ="Informática";
		Project project = new Project(projectName,projectDepartment,projectDescription);
		long projectId = projectManager.createProject(project);
		Assert.assertTrue(projectId > 0);
		Project databaseProject = projectManager.fetchProject(projectId);
		Assert.assertEquals(projectName,databaseProject.getName());
		Assert.assertEquals(projectDescription,databaseProject.getDescription());
		Assert.assertEquals(projectDepartment,databaseProject.getDepartment());
		
	}
	
	
	public void testAddSeveralProjects(){
		
		for(int i=0;i<3;i++){
			Project project = new Project("Projecto"+i,"Departamento"+i,"Descripción"+1);
			projectManager.createProject(project);
		}
		Assert.assertEquals(3,projectManager.fetchAllProjects().size());
		
	}
	
	
	

}
