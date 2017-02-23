package com.makeramen.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LugaresDB";

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_DISCOTECAS_TABLE = "CREATE TABLE discotecas ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"nombre TEXT, "+
				"direccion TEXT, "+
                "telefono TEXT, " +
                "informacion TEXT, " +
                "imagen1 INTEGER," +
                "imagen2 INTEGER )";
		
		// create books table
		db.execSQL(CREATE_DISCOTECAS_TABLE);

        String CREATE_BARES_TABLE = "CREATE TABLE bares ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "direccion TEXT, "+
                "telefono TEXT, " +
                "informacion TEXT, " +
                "imagen1 INTEGER," +
                "imagen2 INTEGER )";

        db.execSQL(CREATE_BARES_TABLE);

        String CREATE_CITA_TABLE = "CREATE TABLE cita ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "direccion TEXT, "+
                "telefono TEXT, " +
                "informacion TEXT, " +
                "imagen1 INTEGER," +
                "imagen2 INTEGER )";

        db.execSQL(CREATE_CITA_TABLE);

        String CREATE_COMIDA_TABLE = "CREATE TABLE comida ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "direccion TEXT, "+
                "telefono TEXT, " +
                "informacion TEXT, " +
                "imagen1 INTEGER," +
                "imagen2 INTEGER )";

        db.execSQL(CREATE_COMIDA_TABLE);

        /*String CREATE_DEPORTE_TABLE = "CREATE TABLE deporte ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "direccion TEXT, "+
                "telefono TEXT, " +
                "informacion TEXT, " +
                "imagen1 INTEGER," +
                "imagen2 INTEGER )";

        db.execSQL(CREATE_DEPORTE_TABLE);*/

        String CREATE_KARAOKE_TABLE = "CREATE TABLE karaoke ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "direccion TEXT, "+
                "telefono TEXT, " +
                "informacion TEXT, " +
                "imagen1 INTEGER," +
                "imagen2 INTEGER )";

        db.execSQL(CREATE_KARAOKE_TABLE);

        String CREATE_SANO_TABLE = "CREATE TABLE sano ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "direccion TEXT, "+
                "telefono TEXT, " +
                "informacion TEXT, " +
                "imagen1 INTEGER," +
                "imagen2 INTEGER )";

        db.execSQL(CREATE_SANO_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS discotecas");
        db.execSQL("DROP TABLE IF EXISTS bares");
        db.execSQL("DROP TABLE IF EXISTS cita");
        db.execSQL("DROP TABLE IF EXISTS comida");
        db.execSQL("DROP TABLE IF EXISTS deporte");
        db.execSQL("DROP TABLE IF EXISTS karaoke");
        db.execSQL("DROP TABLE IF EXISTS sano");
        
        // create fresh books table
        this.onCreate(db);
	}
	//---------------------------------------------------------------------
   
	/**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */
	
	// Books table name
    //private static final String TABLE_DISCOTECAS = "discotecas";
    
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_DIRECCION = "direccion";
    private static final String KEY_TELEFONO = "telefono";
    private static final String KEY_INFORMACION = "informacion";
    private static final String KEY_IMAGEN1 = "imagen1";
    private static final String KEY_IMAGEN2 = "imagen2";

    private static final String[] COLUMNS = {KEY_ID,KEY_NOMBRE,KEY_DIRECCION,KEY_TELEFONO,KEY_INFORMACION,KEY_IMAGEN1, KEY_IMAGEN2};
    
	public void addLugar(String tabla, Object[] lugar){
		//Log.d("addBook", book.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		 
		// 2. create ContentValues to add key "column"/value

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, lugar[0].toString()); // get title
        values.put(KEY_DIRECCION, lugar[1].toString());
        values.put(KEY_TELEFONO, lugar[2].toString());
        values.put(KEY_INFORMACION, lugar[3].toString());
        values.put(KEY_IMAGEN1, ((Integer) lugar[4]));
        values.put(KEY_IMAGEN2, (Integer)lugar[5]);

        // 3. insert
        db.insert(tabla, // table
        		null, //nullColumnHack
        		values); // key/value -> keys = column names/ values = column values
        
        // 4. close
        db.close(); 
	}
	
	public ArrayList<Object[]> getLugares(String Tabla){

		// 1. get reference to readable DB
        ArrayList<Object[]> lugares = new ArrayList<>();
        Object[] lugar = new Object[7];
		SQLiteDatabase db = this.getReadableDatabase();
		 
		// 2. build query
        Cursor cursor = db.rawQuery("SELECT * FROM "+Tabla+"",null);
        		/*db.query(Tabla, // a. table
        		COLUMNS, // b. column names
        		" id = ?", // c. selections 
                COLUMNS, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit*/
        
        // 3. if we got results get the first one
        int i=0;
        if (cursor != null) {
            //cursor.moveToFirst();
                //do {
                    Log.i("contador",""+i++);
            while (cursor.moveToNext()){
                lugar[0] = cursor.getInt(cursor.getColumnIndex("id"));//id
                lugar[1] = cursor.getString(cursor.getColumnIndex("nombre"));//nombre
                lugar[2] = cursor.getString(cursor.getColumnIndex("direccion"));//direccion
                lugar[3] = cursor.getString(cursor.getColumnIndex("telefono"));//telefono
                lugar[4] = cursor.getString(cursor.getColumnIndex("informacion"));//informacion
                lugar[5] = cursor.getInt(cursor.getColumnIndex("imagen1"));//imagen1
                lugar[6] = cursor.getInt(cursor.getColumnIndex("imagen2"));//imagen2
                lugares.add(lugar);
                lugar = new Object[7];

                }

        }
        db.close();
        return lugares;
	}

    public int getSize(String Tabla){
        int size=0;
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor = db.rawQuery("SELECT * FROM "+Tabla+"",null);
        while(cursor.moveToNext()){
            size++;
        }

        return size;
    }




	
	// Get All Books
    /*public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_DISCOTECAS;
 
    	// 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
            	book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }
        
		Log.d("getAllBooks()", books.toString());

        // return books
        return books;
    }*/
	

}
