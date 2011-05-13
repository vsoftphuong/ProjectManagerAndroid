package org.gestor.proyectos;

import org.gestor.proyectos.project.ProjectList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        (findViewById(R.id.project_list)).setOnClickListener(viewAllProjectListener);
        
    }
    
    
    OnClickListener viewAllProjectListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent allProjectIntent = new Intent(Main.this,ProjectList.class);	
			startActivity(allProjectIntent);
		}
	};
}