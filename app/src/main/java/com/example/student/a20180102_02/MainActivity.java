package com.example.student.a20180102_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1;
    Switch sw;
    ProgressBar pb,pb2;
    SeekBar sb;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //這行之前，View不存在，所以findViewById在這行之前會找不到而當機

        cb1=findViewById(R.id.checkBox);
        sw = findViewById(R.id.switch1);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        pb2=findViewById(R.id.progressBar2);
        sb=findViewById(R.id.seekBar);
        tv=findViewById(R.id.textView2);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b) {
                    Toast.makeText(MainActivity.this, "打勾了", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_SHORT).show();
                }
            }
        });

    sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean b) {
            if(b)
            {
                pb.setVisibility(View.VISIBLE);
            }
            else
            {
                    pb.setVisibility(View.INVISIBLE);
            }
            }
        });

    sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress1, boolean fromUser) {
            tv.setText(String.valueOf(progress1));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });
    }

    public void click1(View v)
    {
        RadioGroup rg=findViewById(R.id.radioGroup);
        {
            switch (rg.getCheckedRadioButtonId())
            {
                case R.id.radioButton3:
                    Toast.makeText(this, "A", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radioButton2:
                    Toast.makeText(this,"B",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radioButton:
                    Toast.makeText(this,"C",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this,"選一個啦!",Toast.LENGTH_SHORT).show();
            }
        }
        Toast.makeText(this,String.valueOf(sb.getProgress()),Toast.LENGTH_SHORT).show();
    }
    public void click2(View v){
        pb.setVisibility(View.VISIBLE);
        new Thread(){   //若無用run，則pb還沒來的及跑出來就會被停掉，所以要把Thread直抓出來分別執行
            public void run(){
                super.run();
                try {
                    Thread.sleep(3000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {  //若無此段，表面上在模擬器無問題，但實際在實機上會跑不出來，因為Android不允許主程式的屬性被非主程式改動(INVISIBLE)
                    @Override
                    public void run() {
                        pb.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }.start();  //偷懶法，匿名類別+直接start
    }

    public void click3(View v)
    {
        pb2.setProgress(pb2.getProgress()-10);
    }
    public void  click4(View v)
    {
        pb2.setProgress(pb2.getProgress()+10);
    }
}
