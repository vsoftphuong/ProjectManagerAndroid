package org.gestor.proyectos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class WorkerDatabase {
	

	public static final String KEY_EMAIL = "email";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_NAME = "name";
	public static final String KEY_ROWID = "_id";
	public static final String KEY_PROJECT_ID = "project_id";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private static final String DATABASE_TABLE = "worker";

	/**
	 * Database creation sql statement
	 */
	public static final String WORKER_TABLE_CREATE =
		"CREATE  table "+DATABASE_TABLE+" (_id integer primary key autoincrement, "
		+ KEY_NAME+ " text not null, "
		+ KEY_PROJECT_ID+ " integer not null, " 
		+ KEY_DESCRIPTION+ " text not null, " 
		+ KEY_EMAIL+ " text not null, " 
		+ KEY_PHONE+ " text not null" +");";


	private final Context mCtx;




	public WorkerDatabase(Context ctx) {
		this.mCtx = ctx;
		this.open();
	}


	private WorkerDatabase open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public long createWorker(Worker worker) {
		return mDb.insert(DATABASE_TABLE, null, fromWorkerToContentValues(worker));
	}


	/*
	 * Obtenemos un cursor con todos los trabajadores de un projecto y lo guardamos en un listado.
	 */
	public List<Worker> fetchAllWorkerInProject(long projectId) {
		List<Worker> projectList = new ArrayList<Worker>();
		Cursor mCursor = mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID,
				KEY_NAME,KEY_PROJECT_ID,KEY_DESCRIPTION,KEY_EMAIL,KEY_PHONE}, KEY_PROJECT_ID + "=" + projectId, null, null, null, null);
		if (mCursor != null && mCursor.moveToFirst()) {
			do {
				projectList.add((Worker)fromCursorToWorker(mCursor));
			} while (mCursor.moveToNext());
			mCursor.close();
		}
		return projectList;
	}

	/*
	 * Obtenemos un proyecto usando su identificador de bbdd
	 */
	public Worker fetchWorker(long rowId) throws SQLException {

		Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
				KEY_NAME,KEY_PROJECT_ID,KEY_DESCRIPTION,KEY_EMAIL,KEY_PHONE}, KEY_ROWID + "=" + rowId, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		Worker worker = fromCursorToWorker(mCursor);
		mCursor.close();
		return worker;

	}

	/*
	 * Clases intermedias usadas para convertir de un cursor a un objeto Project
	 */
	private Worker fromCursorToWorker(Cursor cursor) {
		Worker Worker = new Worker();
		Worker.setId(cursor.getInt(cursor.getColumnIndex(KEY_ROWID)));
		Worker.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
		Worker.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
		Worker.setPhone(cursor.getInt(cursor.getColumnIndex(KEY_PHONE)));
		Worker.setProjectId(cursor.getInt(cursor.getColumnIndex(KEY_PROJECT_ID)));
		Worker.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
		return Worker;
	}


	/*
	 * Clase intermedia para convertir un objeto Project a un ContentValues (usado para insertar en base de datos)
	 */
	private ContentValues fromWorkerToContentValues(Worker worker){
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, worker.getName());	
		initialValues.put(KEY_EMAIL, worker.getEmail());
		initialValues.put(KEY_PHONE, worker.getPhone());
		initialValues.put(KEY_PROJECT_ID, worker.getProjectId());
		initialValues.put(KEY_DESCRIPTION, worker.getDescription());
		return initialValues;

	}

	public void deleteAll() {
		mDb.delete(DATABASE_TABLE, null, null);
	}
}

