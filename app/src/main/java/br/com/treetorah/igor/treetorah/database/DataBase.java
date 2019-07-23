package br.com.treetorah.igor.treetorah.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.treetorah.igor.treetorah.model.Reforestation;
import br.com.treetorah.igor.treetorah.model.TableEstructure;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE="reflorestamento";
    private static final int VERSION=1;

//    string to create the database, if thats not exists in device
    private static final String queryCreationDatabase=
            "CREATE TABLE "+TableEstructure.Table.TABLE_NAME+
                            "("+TableEstructure.Table.COLUMN_ID+" integer primary key autoincrement," +
                             ""+TableEstructure.Table.COLUMN_DATE+" text, " +
                             ""+TableEstructure.Table.COLUMN_STATE +" text, " +
                             ""+TableEstructure.Table.COLUMN_CUTED_TREES+" integer not null," +
                             ""+TableEstructure.Table.COLUMN_VOLUME_CUTED_TREES+" double not null," +
                             ""+TableEstructure.Table.COLUMN_TREES_FOR_PLANT+" integer not null," +
                             ""+TableEstructure.Table.COLUMN_VALUE_TO_PAY+" double not null)";

    //
    private static final String queryVerification="DROP TABLE IF EXISTS ";

    public DataBase(Context context){
            super(context,DATABASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( queryCreationDatabase );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(queryVerification);
        onCreate(db);
    }

    public void saveData(Reforestation reforestation){

        SQLiteDatabase db =  this.getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put( TableEstructure.Table.COLUMN_DATE, reforestation.getDate() );
        values.put(TableEstructure.Table.COLUMN_STATE, reforestation.getState() );
        values.put(TableEstructure.Table.COLUMN_CUTED_TREES, reforestation.getCutedTrees() );
        values.put(TableEstructure.Table.COLUMN_VOLUME_CUTED_TREES, reforestation.getVolumeCutedTrees() );
        values.put( TableEstructure.Table.COLUMN_TREES_FOR_PLANT , reforestation.getTreesForPlant() );
        values.put( TableEstructure.Table.COLUMN_VALUE_TO_PAY , reforestation.getValueToPay() );

        db.insert(TableEstructure.Table.TABLE_NAME,null,values);
    }
    public void updateData(Reforestation reforestation){
        SQLiteDatabase db =  this.getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put( TableEstructure.Table.COLUMN_DATE, reforestation.getDate() );
        values.put(TableEstructure.Table.COLUMN_STATE, reforestation.getState() );
        values.put(TableEstructure.Table.COLUMN_CUTED_TREES, reforestation.getCutedTrees() );
        values.put(TableEstructure.Table.COLUMN_VOLUME_CUTED_TREES, reforestation.getVolumeCutedTrees() );
        values.put( TableEstructure.Table.COLUMN_TREES_FOR_PLANT , reforestation.getTreesForPlant() );
        values.put( TableEstructure.Table.COLUMN_VALUE_TO_PAY , reforestation.getValueToPay() );

        String selection = TableEstructure.Table.COLUMN_ID+"=?";
        String []args = {  String.valueOf( reforestation.getId() )};

        db.update(TableEstructure.Table.TABLE_NAME,values,selection,args);
    }
    public Cursor loadData(){
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor =  db.query(TableEstructure.Table.TABLE_NAME,null,null,null,null,null,null,null);
        return cursor;
    }

    public Cursor query( String sql_query){
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(sql_query,null);
        return cursor;
    }
}
