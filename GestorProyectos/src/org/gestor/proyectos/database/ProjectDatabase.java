package org.gestor.proyectos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 * 
 * Esta clase gestionará la tabla projects de la base de datos. Creando, obteniendo, borrando registros.
 * 
 * @author jose
 *
 */
public class ProjectDatabase {

	public static final String KEY_DEPARTMENT = "department";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_NAME = "name";
	public static final String KEY_ROWID = "_id";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private static final String DATABASE_TABLE = "projects";

	/**
	 * Database creation sql statement
	 */
	public static final String PROJECT_TABLE_CREATE =
		"CREATE  table "+DATABASE_TABLE+" (_id integer primary key autoincrement, "
		+ KEY_NAME+ " text not null, " 
		+ KEY_DESCRIPTION+ " text not null, " 
		+ KEY_DEPARTMENT+ " text not null" +");";


	private final Context mCtx;




	public ProjectDatabase(Context ctx) {
		this.mCtx = ctx;
		this.open();
	}


	private ProjectDatabase open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	
	public long createProject(Project project) {
		return mDb.insert(DATABASE_TABLE, null, fromProjectToContentValues(project));
	}


	/*
	 * Obtenemos un cursor con todos los proyectos y lo guardamos en un listado.
	 */
	public List<Project> fetchAllProjects() {
		List<Project> projectList = new ArrayList<Project>();
		Cursor mCursor = mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID,
				KEY_NAME,KEY_DEPARTMENT,KEY_DESCRIPTION}, null, null, null, null, null);
		if (mCursor != null && mCursor.moveToFirst()) {
			do {
				projectList.add((Project)fromCursorToProject(mCursor));
			} while (mCursor.moveToNext());
			mCursor.close();
		}
		return projectList;
	}

	/*
	 * Obtenemos un proyecto usando su identificador de bbdd
	 */
	public Project fetchProject(long rowId) throws SQLException {

		Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
				KEY_NAME,KEY_DEPARTMENT,KEY_DESCRIPTION}, KEY_ROWID + "=" + rowId, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		Project project = fromCursorToProject(mCursor);
		mCursor.close();
		return project;

	}

	/*
	 * Clases intermedias usadas para convertir de un cursor a un objeto Project
	 */
	private Project fromCursorToProject(Cursor cursor) {
		Project project = new Project();
		project.setId(cursor.getInt(cursor.getColumnIndex(KEY_ROWID)));
		project.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
		project.setDepartment(cursor.getString(cursor.getColumnIndex(KEY_DEPARTMENT)));
		project.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
		return project;
	}


	/*
	 * Clase intermedia para convertir un objeto Project a un ContentValues (usado para insertar en base de datos)
	 */
	private ContentValues fromProjectToContentValues(Project localProject){
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, localProject.getName());	
		initialValues.put(KEY_DEPARTMENT, localProject.getDepartment());
		initialValues.put(KEY_DESCRIPTION, localProject.getDescription());
		return initialValues;

	}

	public void deleteAll() {
		mDb.delete(DATABASE_TABLE, null, null);
	}
}

