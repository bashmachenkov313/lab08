package bashmachenkov.gr313.lab08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText txtct1;

    int nid;
    String ntxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtct1 = findViewById(R.id.txt_content);

        Intent i = getIntent();
        nid = i.getIntExtra("note-id",0);
        ntxt = i.getStringExtra("note-txt");

        txtct1.setText(ntxt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itm_save)
        {
            String text = txtct1.getText().toString();
            g.notes.alterNote(nid,text);
            Toast.makeText(MainActivity2.this,"Успешно изменено",Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (id == R.id.itm_delete)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Вы уверены?");
            dialog.setPositiveButton("Да", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    g.notes.deleteNote(nid);
                    Toast.makeText(MainActivity2.this,"Успешно удалено",Toast.LENGTH_SHORT).show();
                    finish();
                }

            });
            dialog.setNegativeButton("Нет",null);
            dialog.show();
        }


        return super.onOptionsItemSelected(item);
    }
}