package example.com.termproject;



import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

public class DataBaseActivity extends Activity {

    // Database 관련 객체들
    SQLiteDatabase db;
    String dbName = "idList.db"; // name of Database;
    String tableName = "idListTable"; // name of Table;
    int dbMode = Context.MODE_PRIVATE;


    // layout object
    EditText mEtName;
    EditText mEtIndex;
    Button mBtInsert;
    Button mBtRead;
    Button mBtRemove;
    Button mBtEdit;


    ListView mList;
    ArrayAdapter<String> basicAdapter;
    ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create databases
        db = openOrCreateDatabase(dbName,dbMode,null);
        // create table;
        createTable();

        mEtName = (EditText) findViewById(R.id.et_text);
        mEtIndex = (EditText) findViewById(R.id.et_index);
        mBtInsert = (Button) findViewById(R.id.bt_insert);
        mBtRead = (Button) findViewById(R.id.bt_read);
        mBtRemove = (Button) findViewById(R.id.bt_remove);
        mBtEdit = (Button) findViewById(R.id.bt_edit);
        mList = (ListView) findViewById(R.id.list_view);

        mBtInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEtName.getText().toString();
                insertData(name);
            }
        });

        mBtRead = (Button) findViewById(R.id.bt_read);
        mBtRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameList.clear();
                selectAll();
                basicAdapter.notifyDataSetChanged();
            }
        });

        mBtEdit = (Button) findViewById(R.id.bt_edit);
        mBtEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = mEtName.getText().toString();
                String index = mEtIndex.getText().toString();
                int indexOf = Integer.parseInt(index);
                updateData(indexOf, name);
            }
        });

        mBtRemove = (Button) findViewById(R.id.bt_remove);
        mBtRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String index = mEtIndex.getText().toString();
                int indexOf = Integer.parseInt(index);
                removeData(indexOf);
            }
        });


        // Create listview
        nameList = new ArrayList<String>();
        basicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nameList);
        mList.setAdapter(basicAdapter);

    }



    // Table 생성
    public void createTable() {
        try {
            String sql = "create table " + tableName + "(id integer primary key autoincrement, " + "name text not null)";
            db.execSQL(sql);
        } catch (android.database.sqlite.SQLiteException e) {
            Log.d("Lab sqlite", "error: " + e);
        }
    }

    // Data 추가
    public void insertData(String name) {
        String sql = "insert into " + tableName + " values(NULL, '" + name + "');";
        db.execSQL(sql);
    }

    // Data 업데이트
    public void updateData(int index, String name) {
        String sql = "update " + tableName + " set name = '" + name + "' where id = " + index + ";";
        db.execSQL(sql);
    }

    // Data 삭제
    public void removeData(int index) {
        String sql = "delete from " + tableName + " where id = " + index + ";";
        db.execSQL(sql);
    }

    // Data 읽기(꺼내오기)
    public void selectData(int index) {
        String sql = "select * from " + tableName + " where id = " + index + ";";
        Cursor result = db.rawQuery(sql, null);


        if (result.moveToFirst()) {
            int id = result.getInt(0);
            String name = result.getString(1);

            Log.d("lab_sqlite", "\"index= \" + id + \" name=\" + name ");
        }
        result.close();
    }


    // 모든 Data 읽기
    public void selectAll() {
        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);

        results.moveToFirst();


        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);

            Log.d("lab_sqlite", "index= " + id + " name=" + name);

            nameList.add(name);

            results.moveToNext();
        }
        results.close();
    }


}