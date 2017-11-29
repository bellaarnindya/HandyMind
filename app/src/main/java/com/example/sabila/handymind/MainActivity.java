package com.example.sabila.handymind;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.example.sabila.handymind.buttons.CircleButton;
import com.example.sabila.handymind.buttons.LineButton;
import com.example.sabila.handymind.buttons.RectButton;
import com.example.sabila.handymind.buttons.TextButton;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private RectButton rectBtn;
    private CircleButton circleBtn;
    private LineButton lineBtn;
    private TextButton textBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        drawingView = (DrawingView)findViewById(R.id.drawing_view);

        rectBtn = (RectButton) findViewById(R.id.rect_btn);
        circleBtn = (CircleButton) findViewById(R.id.circle_btn);
        lineBtn = (LineButton) findViewById(R.id.line_btn);
        textBtn = (TextButton) findViewById(R.id.text_btn);

        rectBtn.setOnClickListener(setShape);
        circleBtn.setOnClickListener(setShape);
        lineBtn.setOnClickListener(setShape);
        textBtn.setOnClickListener(setShape);

    }

    View.OnClickListener setShape = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rect_btn:
                    drawingView.setSelectedShape("rectangle");
                    break;
                case R.id.circle_btn:
                    drawingView.setSelectedShape("circle");
                    break;
                case R.id.line_btn:
                    drawingView.setSelectedShape("line");
                    break;
                case R.id.text_btn:
                    drawingView.setSelectedShape("text");
                    break;
            }
        }
    };



}
