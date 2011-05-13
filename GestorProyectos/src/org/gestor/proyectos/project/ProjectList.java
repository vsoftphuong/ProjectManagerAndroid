package org.gestor.proyectos.project;

import java.util.List;

import org.gestor.proyectos.Main;
import org.gestor.proyectos.R;
import org.gestor.proyectos.database.Project;
import org.gestor.proyectos.database.ProjectDatabase;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ProjectList extends ListActivity {
	
	ProjectDatabase projectManager;
	List<Project> projectList;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.project_list_screen);
	        
	        projectManager = new ProjectDatabase(this);
	        projectList = projectManager.fetchAllProjects();
	        ProjectBaseAdapter projectAdapter = new ProjectBaseAdapter(this,projectList);
	        this.setListAdapter(projectAdapter);
	        
	        findViewById(R.id.add_proyect).setOnClickListener(addProjectListener);
	        
	        
	    }
	 
	OnClickListener addProjectListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent newProjectIntent = new Intent(ProjectList.this,ProjectNew.class);	
			startActivity(newProjectIntent);
			
		}
	};

}
