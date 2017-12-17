package com.example.sabila.handymind;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.sabila.handymind.buttons.CircleButton;
import com.example.sabila.handymind.buttons.DashedLineButton;
import com.example.sabila.handymind.buttons.DeleteButton;
import com.example.sabila.handymind.buttons.LineButton;
import com.example.sabila.handymind.buttons.OvalButton;
import com.example.sabila.handymind.buttons.RectButton;
import com.example.sabila.handymind.buttons.RoundRectButton;
import com.example.sabila.handymind.buttons.SelectButton;
import com.example.sabila.handymind.buttons.TextButton;
import com.example.sabila.handymind.tools.CircleTool;
import com.example.sabila.handymind.tools.DashedLineTool;
import com.example.sabila.handymind.tools.DeleteTool;
import com.example.sabila.handymind.tools.LineTool;
import com.example.sabila.handymind.tools.OvalTool;
import com.example.sabila.handymind.tools.RectangleTool;
import com.example.sabila.handymind.tools.RoundRectTool;
import com.example.sabila.handymind.tools.SelectionTool;
import com.example.sabila.handymind.tools.TextTool;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawingView drawingView;
    private RectButton rectBtn;
    private CircleButton circleBtn;
    private LineButton lineBtn;
    private DashedLineButton dashedLineBtn;
    private RoundRectButton roundRectBtn;
    private OvalButton ovalBtn;
    private TextButton textBtn;
    private SelectButton selectBtn;
    private EditText inputText;
    private AlertDialog dialog;
    private String message;
    private Button saveBtn;
    private DeleteButton deleteBtn;
    private Button undoBtn;
    private Button redoBtn;
    private Button newBtn;
    private PopupWindow popupWindow;

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
        dashedLineBtn = (DashedLineButton) findViewById(R.id.dashed_line_btn);
        roundRectBtn = (RoundRectButton) findViewById(R.id.roundrect_btn);
        ovalBtn= (OvalButton) findViewById(R.id.oval_btn);
        textBtn = (TextButton) findViewById(R.id.text_btn);
        undoBtn = (android.widget.Button) findViewById(R.id.undo_btn);
        redoBtn = (android.widget.Button) findViewById(R.id.redo_btn);
        deleteBtn = (DeleteButton) findViewById(R.id.delete_button);
        saveBtn = (android.widget.Button) findViewById(R.id.save_button);
        selectBtn = (SelectButton) findViewById(R.id.select_btn);
        newBtn = (Button) findViewById(R.id.new_btn);

        rectBtn.setOnClickListener(this);
        circleBtn.setOnClickListener(this);
        lineBtn.setOnClickListener(this);
        dashedLineBtn.setOnClickListener(this);
        roundRectBtn.setOnClickListener(this);
        ovalBtn.setOnClickListener(this);
        textBtn.setOnClickListener(this);
        undoBtn.setOnClickListener(this);
        redoBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        selectBtn.setOnClickListener(this);
        newBtn.setOnClickListener(this);

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
                drawingView.setMessage(message);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rect_btn:
                drawingView.setActiveTool(new RectangleTool());
                break;
            case R.id.circle_btn:
                drawingView.setActiveTool(new CircleTool());
                break;
            case R.id.line_btn:
                drawingView.setActiveTool(new LineTool());
                break;
            case R.id.dashed_line_btn:
                drawingView.setActiveTool(new DashedLineTool());
                break;
            case R.id.roundrect_btn:
                drawingView.setActiveTool(new RoundRectTool());
                break;
            case R.id.oval_btn:
                drawingView.setActiveTool(new OvalTool());
                break;
            case R.id.text_btn:
                drawingView.setActiveTool(new TextTool());
                showDialog();
                dialog.show();
                break;
            case R.id.select_btn:
                drawingView.setActiveTool(new SelectionTool());
                break;
            case R.id.undo_btn:
                drawingView.undo();
                break;
            case R.id.redo_btn:
                drawingView.redo();
                break;
            case R.id.delete_button:
                drawingView.setActiveTool(new DeleteTool());
                break;
            case R.id.new_btn:
                drawingView.clearAll();
                break;
            case R.id.save_button:
                if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    //Log.v(TAG,"Permission is granted");
                    //File write logic here
                    //return true;
                }
                else ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                Date now = new Date();
                android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
                try {
                    // image naming and path  to include sd card  appending name you choose for file
                    String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
                    Log.d("path",mPath);

                    // create bitmap screen capture
                    View v1 = findViewById(R.id.drawing_view);
                    v1.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
                    v1.setDrawingCacheEnabled(false);

                    File imageFile = new File(mPath);

                    FileOutputStream outputStream = new FileOutputStream(imageFile);
                    int quality = 100;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                    outputStream.flush();
                    outputStream.close();

                    // Show popup
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Save Status");
                    builder.setMessage("Save success, file located at " + mPath);

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    AlertDialog savePopUp = builder.create();
                    savePopUp.show();


                } catch (Throwable e) {
                    // Several error may come out with file handling or DOM
                    e.printStackTrace();
                }
                break;
        }
    }
}
