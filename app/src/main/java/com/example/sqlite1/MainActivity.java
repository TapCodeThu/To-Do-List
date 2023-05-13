package com.example.sqlite1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lv;
    ArrayList<CongViec> arrayList;
    CongViecAdapter adapter;
    EditText editTextThem;
    Button buttonThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.id_listview);
        editTextThem = (EditText) findViewById(R.id.id_edittextthem);
        buttonThem = (Button) findViewById(R.id.id_button);
        arrayList = new ArrayList<>();
        adapter = new CongViecAdapter(this,R.layout.dong_cong_viec,arrayList);
        lv.setAdapter(adapter);

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tasks = editTextThem.getText().toString().trim();
                if(TextUtils.isEmpty(tasks))
                {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập công việc!", Toast.LENGTH_SHORT).show();

                    return;
                }

                database.QueryData("INSERT INTO CongViec VALUES (null,'"+ tasks+"')");
                ShowData();
            }
        });

        //Tao database ghichu
        database = new Database(this,"ghichu.sqlite",null,1);

        //tao bang
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCongViec VARCHAR(200))");
        //database.QueryData("INSERT INTO CongViec VALUES (null,'Cong Viec 1')");
        //database.QueryData("INSERT INTO CongViec VALUES (null,'Cong Viec 2')");

       ShowData();
    }
    public void DialogCapNhat(String ten, int id)
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit);
        EditText editTextsua = (EditText) dialog.findViewById(R.id.id_editnoidung);
        Button button_sua = (Button) dialog.findViewById(R.id.id_buttoncapnhat);
        Button button_huy = (Button) dialog.findViewById(R.id.id_buttonhuy);
        editTextsua.setText(ten);


        button_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        button_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataSua = editTextsua.getText().toString().trim();
                if(TextUtils.isEmpty(dataSua))
                {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập dữ liệu!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                database.QueryData("UPDATE CongViec SET TenCongViec ='"+ dataSua+"' WHERE Id = '"+ id+"'");
                dialog.dismiss();
                ShowData();
            }
        });
        dialog.show();
    }
    public void DialogDelete(String ten, int id)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn thực sự muốn xóa " +ten+ " ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM CongViec WHERE Id = '" +id+"'");
                ShowData();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
   private void ShowData()
   {
       arrayList.clear();
       Cursor dataCongViec = database.getData("SELECT * FROM CongViec");
       while (dataCongViec.moveToNext())
       {
           String ten = dataCongViec.getString(1);
           int id =dataCongViec.getInt(0);
           // Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
           arrayList.add(new CongViec(id,ten));
       }
       adapter.notifyDataSetChanged();
   }
}