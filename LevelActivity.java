package com.jerry94.w3villaquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by GARVIT on 13-07-2017.
 */
public class LevelActivity extends Activity {

    Button btn_simple, btn_intermediate, btn_hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        btn_simple = (Button) findViewById(R.id.btnsimple);
        btn_simple.setBackground(this.getResources().getDrawable(R.drawable.button_bg));
        btn_intermediate = (Button) findViewById(R.id.btnintermediate);
        btn_intermediate.setBackground(this.getResources().getDrawable(R.drawable.button_bg));
        btn_hard = (Button) findViewById(R.id.btnhard);
        btn_hard.setBackground(this.getResources().getDrawable(R.drawable.button_bg));

        btn_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LevelActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        btn_intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LevelActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LevelActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LevelActivity.this);
        alertDialog.setTitle("Confirm Exit..");
        alertDialog.setMessage("Are you sure want to exit..??");
        alertDialog.setIcon(R.drawable.alert);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                System.exit(1);
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
