package app.deritarigan.com.a3dcga;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private int theta = 0, alpa = 0, beta = 0;
    private Button btnX, btnY, btnZ , btnSet;
    private ImageView ivUp,ivDown,ivRight,ivLeft;
    private ImageView mImageView;
    private EditText etP,etQ,etR;
    Paint BlackPen = new Paint();
    Paint RedPen = new Paint();
    MatrixModel tempValue;
    final MatrixModel vertex[] = new MatrixModel[]{
            new MatrixModel(-1, -1, 1, 1),
            new MatrixModel(-1, 1, 1, 1),
            new MatrixModel(1, -1, 1, 1),
            new MatrixModel(1, 1, 1, 1),
            new MatrixModel(-1, -1, -1, 1),
            new MatrixModel(-1, 1, -1, 1),
            new MatrixModel(1, -1, -1, 1),
            new MatrixModel(1, 1, -1, 1),
    };
    private Point vertexPoint[] = new Point[]{
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0)
    };

    private static final double scale = 100;
    private Point center;
    boolean rotOnX = false;
    boolean rotOnY = false;
    boolean rotOnZ = false;
    double dx = 0.0;
    double dy = 0.0;
    double p = 0.0;
    double q = 0.0;
    double r = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindUI();

        BlackPen.setColor(Color.BLACK);
        BlackPen.setStrokeWidth(5);
        RedPen.setColor(Color.RED);
        RedPen.setStrokeWidth(5);
        Bitmap bitmap = Bitmap.createBitmap(Resources.getSystem().getDisplayMetrics().widthPixels,
                Resources.getSystem().getDisplayMetrics().heightPixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.WHITE);
        BlackPen.setAntiAlias(true);
        RedPen.setAntiAlias(true);

        center = CountUtil.getCenter();
        DrawCube();

        onDraw2(canvas);

        mImageView.setImageBitmap(bitmap);

        btnY.setOnClickListener(view->{

            final Handler handler = new Handler();
            Timer timer = new Timer(false);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (rotOnY) {

                                beta = beta + 2;
                                canvas.drawColor(Color.WHITE);
                                DrawCube();
                                onDraw2(canvas);
                                mImageView.setImageBitmap(bitmap);
                            }else {
                                timer.cancel();
                                timer.purge();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask, 0, 100); // every 5 seconds.
            if (rotOnY){
                rotOnY = false;
                btnY.setBackgroundResource(R.drawable.gradient_button1);
                btnY.setTextColor(getResources().getColor(R.color.color_black));
            }else {
                rotOnY = true;
                btnY.setBackgroundResource(R.drawable.gradient_button);
                btnY.setTextColor(getResources().getColor(R.color.color_white));
            }
        });
        btnX.setOnClickListener(view -> {
            final Handler handler = new Handler();
            Timer timer = new Timer(false);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (rotOnX) {

                                alpa = alpa + 2;
                                canvas.drawColor(Color.WHITE);
                                DrawCube();
                                onDraw2(canvas);
                                mImageView.setImageBitmap(bitmap);
                            }else {
                                timer.cancel();
                                timer.purge();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask, 0, 100); // every 5 seconds.
            if (rotOnX){
                rotOnX = false;
                btnX.setBackgroundResource(R.drawable.gradient_button1);
                btnX.setTextColor(getResources().getColor(R.color.color_black));
            }else {
                rotOnX = true;
                btnX.setBackgroundResource(R.drawable.gradient_button);
                btnX.setTextColor(getResources().getColor(R.color.color_white));
            }
        });
        btnZ.setOnClickListener(view -> {
            final Handler handler = new Handler();
            Timer timer = new Timer(false);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (rotOnZ) {

                                theta = theta + 2;
                                canvas.drawColor(Color.WHITE);
                                DrawCube();
                                onDraw2(canvas);
                                mImageView.setImageBitmap(bitmap);
                            }else {
                                timer.cancel();
                                timer.purge();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask, 0, 100);
            if (rotOnZ){
                rotOnZ = false;
                btnZ.setBackgroundResource(R.drawable.gradient_button1);
                btnZ.setTextColor(getResources().getColor(R.color.color_black));
            }else {
                rotOnZ = true;
                btnZ.setBackgroundResource(R.drawable.gradient_button);
                btnZ.setTextColor(getResources().getColor(R.color.color_white));
            }
        });

        ivUp.setOnClickListener(view->{
            dy = dy-0.2;
            canvas.drawColor(Color.WHITE);
            DrawCube();
            onDraw2(canvas);
            mImageView.setImageBitmap(bitmap);
        });
        ivDown.setOnClickListener(view->{
            dy = dy+0.2;
            canvas.drawColor(Color.WHITE);
            DrawCube();
            onDraw2(canvas);
            mImageView.setImageBitmap(bitmap);
        });

        ivLeft.setOnClickListener(view->{
            dx = dx-0.2;
            canvas.drawColor(Color.WHITE);
          DrawCube();
            onDraw2(canvas);
            mImageView.setImageBitmap(bitmap);
        });
        ivRight.setOnClickListener(view->{
            dx = dx+0.2;
            canvas.drawColor(Color.WHITE);
            DrawCube();
            onDraw2(canvas);
            mImageView.setImageBitmap(bitmap);
        });

        mImageView.setOnClickListener(view->{
            if (etQ.getText().toString().isEmpty()){
                etQ.setText("0");
            }
            if (etP.getText().toString().isEmpty()){
                etP.setText("0");
            }
            if (etR.getText().toString().isEmpty()){
                etR.setText("0");
            }
            q = Double.valueOf(etQ.getText().toString());
            p = Double.valueOf(etP.getText().toString());
            r = Double.valueOf(etR.getText().toString());
            canvas.drawColor(Color.WHITE);
            DrawCube();
            onDraw2(canvas);
            mImageView.setImageBitmap(bitmap);
        });

    }

    private void DrawCube() {
        for (int i = 0; i < vertex.length; i++) {
            tempValue = CountUtil.CalculationOnMatrixVt(vertex[i], p, q, r, theta, alpa, beta,dx,dy);
            vertexPoint[i] = new Point((int) (center.x + (scale * tempValue.getX())), (int) (center.y + (scale * tempValue.getY())));
        }
    }

    protected void onDraw2(Canvas canvas) {

        DrawLine(BlackPen, vertexPoint[4], vertexPoint[5], canvas);
        DrawLine(BlackPen, vertexPoint[4], vertexPoint[6], canvas);
        DrawLine(BlackPen, vertexPoint[5], vertexPoint[7], canvas);
        DrawLine(BlackPen, vertexPoint[6], vertexPoint[7], canvas);
        DrawLine(BlackPen, vertexPoint[0], vertexPoint[4], canvas);
        DrawLine(BlackPen, vertexPoint[1], vertexPoint[5], canvas);
        DrawLine(BlackPen, vertexPoint[2], vertexPoint[6], canvas);
        DrawLine(BlackPen, vertexPoint[3], vertexPoint[7], canvas);
        DrawLine(RedPen, vertexPoint[0], vertexPoint[1], canvas);
        DrawLine(RedPen, vertexPoint[0], vertexPoint[2], canvas);
        DrawLine(RedPen, vertexPoint[1], vertexPoint[3], canvas);
        DrawLine(RedPen, vertexPoint[2], vertexPoint[3], canvas);

    }

    private void DrawLine(Paint pen, Point a, Point b, Canvas canvas) {
        canvas.drawLine(a.x, a.y, b.x, b.y, pen);
    }

    private void BindUI(){
        mImageView = (ImageView) findViewById(R.id.iv);
        btnX = (Button) findViewById(R.id.btnRotX);
        btnY = (Button) findViewById(R.id.btnRotY);
        btnZ = (Button) findViewById(R.id.btnRotZ);
        btnSet = (Button) findViewById(R.id.btnSet);

        ivDown=(ImageView) findViewById(R.id.ivDown);
        ivLeft=(ImageView) findViewById(R.id.ivLeft);
        ivRight=(ImageView) findViewById(R.id.ivRight);
        ivUp=(ImageView) findViewById(R.id.ivUp);

        etP = (EditText) findViewById(R.id.etP);
        etQ = (EditText) findViewById(R.id.etQ);
        etR = (EditText) findViewById(R.id.etR);
    }


}
