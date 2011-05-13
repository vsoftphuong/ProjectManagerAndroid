package org.gestor.proyectos.project;

import org.gestor.proyectos.R;
import org.gestor.proyectos.database.Project;
import org.gestor.proyectos.database.ProjectDatabase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class ProjectNew extends Activity implements OnItemSelectedListener{

	Spinner departmentSpinner;
	String selectedDepartment;
	EditText nameValue;
	EditText descriptionValue;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_new_screen);
		(findViewById(R.id.save)).setOnClickListener(saveListener);
		(findViewById(R.id.cancel)).setOnClickListener(cancelListener);
		nameValue = (EditText) (findViewById(R.id.nameEdit));
		descriptionValue = (EditText) (findViewById(R.id.descriptionEdit));
		generateSpinner();

	}

	private void generateSpinner() {
		departmentSpinner = (Spinner) findViewById(R.id.departamento);
		ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(
				this, R.array.departamentos, android.R.layout.simple_spinner_item);
		ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		departmentSpinner.setAdapter(ad);
		departmentSpinner.setOnItemSelectedListener(this);
	}
	
	
	OnClickListener saveListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			ProjectDatabase projectManager = new ProjectDatabase(getBaseContext());
			Project newProject = new Project();
			newProject.setName(nameValue.getText().toString());
			newProject.setDepartment(selectedDepartment);
			newProject.setDescription(descriptionValue.getText().toString());
			if(projectManager.createProject(newProject)>0){
				Toast.makeText(getBaseContext(), getString(R.string.new_project_ok), Toast.LENGTH_LONG).show();
				finish();
			}
		}
	};
	

	OnClickListener cancelListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			finish();
		}
	};
	
	
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		selectedDepartment =  parent.getItemAtPosition(position).toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
