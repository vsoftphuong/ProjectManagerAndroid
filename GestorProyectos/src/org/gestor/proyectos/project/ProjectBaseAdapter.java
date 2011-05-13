package org.gestor.proyectos.project;

import java.util.List;

import org.gestor.proyectos.R;
import org.gestor.proyectos.database.Project;
import org.gestor.proyectos.database.WorkerDatabase;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProjectBaseAdapter extends BaseAdapter {

	Context localContext;
	List<Project> elements;

	public ProjectBaseAdapter(Context context, List<Project> projectList) {
		localContext = context;
		elements = projectList;
	}


	public int getCount() {

		return elements.size();
	}

	public Project getItem(int position) {

		return elements.get(position);
	}

	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView rowText = null;
		if (convertView == null) {
			rowText = (TextView) LayoutInflater.from(localContext).inflate(R.layout.project_row, parent, false);
		} else {
			rowText = (TextView) convertView;
		}
		//Obtenemos el número de integrantes de cada proyecto
		Project project = elements.get(position);
		WorkerDatabase workerManager = new WorkerDatabase(this.localContext);
		int numberOfWorkersInProject = workerManager.fetchAllWorkerInProject(project.getId()).size();
		((TextView) rowText.findViewById(R.id.project_text)).setText(project.getName() + " --- " + numberOfWorkersInProject + " " + localContext.getString(R.string.workers));
		return rowText; 
	}

}
