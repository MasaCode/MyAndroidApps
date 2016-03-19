package com.example.moritamasashi.databaseprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DBProvider extends Activity implements View.OnClickListener{

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
    private final static String TAG_WRITE = "write";
    private final static String TAG_READ = "read";
    private EditText edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        edit = new EditText(this);
        edit.setLayoutParams(new LinearLayout.LayoutParams(MP,WC));
        layout.addView(edit);

        layout.addView(makeButton("Writing",TAG_WRITE));
        layout.addView(makeButton("Reading",TAG_READ));
    }

    private Button makeButton(String text,String tag){
        Button button = new Button(this);
        button.setText(text);
        button.setTag(tag);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        button.setOnClickListener(this);
        return button;
    }


    public void onClick(View view){
        String tag = (String)view.getTag();
        if(TAG_WRITE.equals(tag)) {
            try {
                writeDB(edit.getText().toString());
            } catch (Exception e) {
                toast("Faild Writing");
            }
        }else if(TAG_READ.equals(tag)){
            try{
                edit.setText(readDB());
            }catch(Exception e){
                toast("Faild Reading");
            }
        }
    }


    private void writeDB(String info) throws Exception{
        Uri uri = Uri.parse("content://net.npaka.dbprovider/");

        ContentValues values = new ContentValues();
        values.put("id","0");
        values.put("info",info);
        int num = getContentResolver().update(uri,values,null,null);
        if(num == 0) getContentResolver().insert(uri,values);
    }

    private String readDB() throws Exception{
        Uri uri = Uri.parse("content://net.npaka.dbprovider/");
        Cursor c = this.getContentResolver().query(uri,new String[]{"id","info"},"id=0",null,null);
        if(c.getCount() == 0) throw new Exception();
        c.moveToFirst();
        String str = c.getString(1);
        c.close();
        return str;
    }

    private void toast(String text){
        if(text == null) text = "";
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }


}
