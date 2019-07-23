package br.com.treetorah.igor.treetorah;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.com.treetorah.igor.treetorah.database.DataBase;
import br.com.treetorah.igor.treetorah.model.Action;
import br.com.treetorah.igor.treetorah.model.TableEstructure;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private DataBase db;
    private ListView list;

    public void loadRegisters(){

        db = new DataBase(MainActivity.this);
        String[] from = {
                TableEstructure.Table.COLUMN_ID,
                TableEstructure.Table.COLUMN_DATE,
                TableEstructure.Table.COLUMN_STATE,
                TableEstructure.Table.COLUMN_CUTED_TREES,
                TableEstructure.Table.COLUMN_VOLUME_CUTED_TREES};

        int[]to = {R.id.txtId,
                R.id.textState,
                R.id.textDate,
                R.id.textCutedTrees,
                R.id.txtVolCutTree};
        SimpleCursorAdapter simpleCursorAdapter =  new SimpleCursorAdapter(getApplicationContext(),R.layout.item_list,db.loadData(),from,to);

        list.setAdapter(simpleCursorAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list =  findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i =  new Intent(MainActivity.this,CompleteRegisterActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.dialog_text);
                builder.setTitle(R.string.Atenção);
                builder.setPositiveButton(R.string.Sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    Intent intent =  new Intent(MainActivity.this,FormPolimorf.class);
                    intent.putExtra("action",Action.UPDATE);
                    intent.putExtra("id",id);
                    startActivity(intent);
                    }
                });
                builder.setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true ;
            }
        });

        btn = findViewById(R.id.btnNewReg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,FormPolimorf.class);
                intent.putExtra("action",Action.SAVE);
                startActivity(intent);
            }
        });

        try {
            loadRegisters();
        }catch (Exception e){
            Toast.makeText( getApplicationContext(),""+e,Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRegisters();
    }

}
