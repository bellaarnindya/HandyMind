package com.example.sabila.handymind;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


import com.example.sabila.handymind.buttons.CircleButton;
import com.example.sabila.handymind.buttons.LineButton;
import com.example.sabila.handymind.buttons.LineSButton;
import com.example.sabila.handymind.buttons.OvalButton;
import com.example.sabila.handymind.buttons.RectButton;
import com.example.sabila.handymind.buttons.RoundRectButton;
import com.example.sabila.handymind.shapes.RoundRect;
import com.example.sabila.handymind.buttons.TextButton;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private RectButton rectBtn;
    private CircleButton circleBtn;
    private LineButton lineBtn;
    private LineSButton lineSBtn;
    private RoundRectButton roundRectBtn;
    private OvalButton ovalBtn;
    private TextButton textBtn;
    private EditText inputText;
    private AlertDialog dialog;
    private String message;

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
        lineSBtn = (LineSButton)  findViewById(R.id.line_s_btn);
        roundRectBtn = (RoundRectButton) findViewById(R.id.roundrect_btn);
        ovalBtn= (OvalButton) findViewById(R.id.oval_btn);
        textBtn = (TextButton) findViewById(R.id.text_btn);

        rectBtn.setOnClickListener(setShape);
        circleBtn.setOnClickListener(setShape);
        lineBtn.setOnClickListener(setShape);
        lineSBtn.setOnClickListener(setShape);
        roundRectBtn.setOnClickListener(setShape);
        ovalBtn.setOnClickListener(setShape);
        textBtn.setOnClickListener(setShape);
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input Text");
        builder.setMessage("Please fill this to input your message.");

        inputText = new EditText(this);
        builder.setView(inputText);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                message = inputText.getText().toString();
                drawingView.getMessage(message);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog = builder.create();
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
                case R.id.line_s_btn:
                    drawingView.setSelectedShape("striped-line");
                    break;
                case R.id.roundrect_btn:
                    drawingView.setSelectedShape("roundrect");
                    break;
                case R.id.oval_btn:
                    drawingView.setSelectedShape("oval");
                    break;
                case R.id.text_btn:
                    showDialog();
                    dialog.show();
                    break;
            }
        }
    };
}
