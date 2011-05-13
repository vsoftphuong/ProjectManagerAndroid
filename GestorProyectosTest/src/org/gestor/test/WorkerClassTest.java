package org.gestor.test;

import org.gestor.proyectos.database.Project;
import org.gestor.proyectos.database.ProjectDatabase;
import org.gestor.proyectos.database.Worker;
import org.gestor.proyectos.database.WorkerDatabase;
import org.junit.After;
import org.junit.Before;

import junit.framework.Assert;
import android.test.AndroidTestCase;

public class WorkerClassTest extends AndroidTestCase {
	
	WorkerDatabase workerManager;
	ProjectDatabase projectManager;
	
	@Before
	public void setUp() throws Exception {
		workerManager = new WorkerDatabase(this.getContext());
		projectManager = new ProjectDatabase(this.getContext());
		workerManager.deleteAll();
		projectManager.deleteAll();
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	public void testAddWorker() {
		String projectName="Nombre";
		String projectDescription="Descripción breve";
		int workerPhone = 444333;
		String workerEmail ="Informática";

		Project project = new Project("Projecto","Departamento","Descripción");
		long projectId = projectManager.createProject(project);
		
		
		Worker worker = new Worker(projectName, workerPhone, workerEmail, projectDescription, projectId);
		long workerId = workerManager.createWorker(worker);
		Assert.assertTrue(workerId > 0);
		Worker databaseWorker = workerManager.fetchWorker(workerId);
		Assert.assertEquals(projectName,databaseWorker.getName());
		Assert.assertEquals(projectDescription,databaseWorker.getDescription());
		Assert.assertEquals(workerPhone,databaseWorker.getPhone());
		Assert.assertEquals(workerEmail,databaseWorker.getEmail());
		Assert.assertEquals(projectId,databaseWorker.getProjectId());

		
	}
	
	
	public void testAddSeveralProjects(){
		
		for(int i=0;i<3;i++){
			Project project = new Project("Projecto"+i,"Departamento"+i,"Descripción"+1);
			projectManager.createProject(project);
		}
		Assert.assertEquals(3,projectManager.fetchAllProjects().size());
		
	}
	
	
	

}
