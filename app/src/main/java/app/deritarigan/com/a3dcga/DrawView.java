package app.deritarigan.com.a3dcga;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.Window;

public class DrawView extends View {
    Paint BlackPen = new Paint();
    Paint RedPen = new Paint();
    private MatrixModel v1, v2, v3, v4, v5, v6, v7, v8, vp, row1, row2, row3, row4;
    private Point frontBL, frontTL, frontBR, frontTR, backBL, backTL, backBR, backTR;
    private double x2, y2, z2, p2, centerX, centerY, rx, ry, rz;
    private static final double scale = 100;
    private int theta = 0;
    private Context context;



    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    public  void init(){

    }

    public DrawView(Context context, int theta) {
        super(context);
        BlackPen.setColor(Color.BLACK);
        BlackPen.setStrokeWidth(5);
        RedPen.setColor(Color.RED);
        RedPen.setStrokeWidth(5);
        centerX = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
        centerY = Resources.getSystem().getDisplayMetrics().heightPixels / 2;
        v1 = new MatrixModel(-1, -1, 1, 1);
        v2 = new MatrixModel(-1, 1, 1, 1);
        v3 = new MatrixModel(1, -1, 1, 1);
        v4 = new MatrixModel(1, 1, 1, 1);
        v5 = new MatrixModel(-1, -1, -1, 1);
        v6 = new MatrixModel(-1, 1, -1, 1);
        v7 = new MatrixModel(1, -1, -1, 1);
        v8 = new MatrixModel(1, 1, -1, 1);
        vp = new MatrixModel(0, 0, -5, 1);
        DrawCube();

    }


    private void VanishingPointMatrix(double x, double y, double z, double p) {
        double degree, sin, cos;
        rz = 1 / vp.getZ();
        rx = vp.getX();
        ry = vp.getZ();
        degree = theta * Math.PI / 180;
        sin = Math.sin(degree);
        cos = Math.cos(degree);
        row1 = new MatrixModel(cos, 0, 0, sin * rz);
        row2 = new MatrixModel(0, 1, 0, 0);
        row3 = new MatrixModel(-sin, 0, 0, cos * rz);
        row4 = new MatrixModel(0, 0, 0, 1);
        x2 = (x * row1.getX()) + (y * row2.getX()) + (z * row3.getX()) + (p * row4.getX());
        y2 = (x * row1.getY()) + (y * row2.getY()) + (z * row3.getY()) + (p * row4.getY());
        z2 = (x * row1.getZ()) + (y * row2.getZ()) + (z * row3.getZ()) + (p * row4.getZ());
        p2 = (x * row1.getP()) + (y * row2.getP()) + (z * row3.getP()) + (p * row4.getP());
        x2 = x2 / p2;
        y2 = y2 / p2;
        z2 = z2 / p2;
        p2 = p2 / p2;
    }

    private void DrawCube() {
        VanishingPointMatrix(v1.getX(), v1.getY(), v1.getZ(), v1.getP());
        frontBL = new Point((int) (centerX + (scale * x2)), (int) (centerY + (scale * y2)));
        VanishingPointMatrix(v2.getX(), v2.getY(), v2.getZ(), v2.getP());
        frontTL = new Point((int) (centerX + (scale * x2)), (int) (centerY + (scale * y2)));
        VanishingPointMatrix(v3.getX(), v3.getY(), v3.getZ(), v3.getP());
        frontBR = new Point((int) (centerX + (scale * x2)), (int) (centerY + (scale * y2)));
        VanishingPointMatrix(v4.getX(), v4.getY(), v4.getZ(), v4.getP());
        frontTR = new Point((int) (centerX + (scale * x2)), (int) (centerY + (scale * y2)));
        VanishingPointMatrix(v5.getX(), v5.getY(), v5.getZ(), v5.getP());
        backBL = new Point((int) (centerX + (scale * x2)), (int) (centerY + (scale * y2)));
        VanishingPointMatrix(v6.getX(), v6.getY(), v6.getZ(), v6.getP());
        backTL = new Point((int) (centerX + (scale * x2)), (int) (centerY + (scale * y2)));
        VanishingPointMatrix(v7.getX(), v7.getY(), v7.getZ(), v7.getP());
        backBR = new Point((int) (centerX + (scale * x2)), (int) (centerY + (scale * y2)));
        VanishingPointMatrix(v8.getX(), v8.getY(), v8.getZ(), v8.getP());
        backTR = new Point((int) (centerX + (scale * x2)), (int) (centerY + (scale * y2)));
    }

    protected void onDraw(Canvas canvas) {


        DrawLine(RedPen, frontBL, frontTL, canvas);
        DrawLine(RedPen, frontBL, frontBR, canvas);
        DrawLine(RedPen, frontTL, frontTR, canvas);
        DrawLine(RedPen, frontBR, frontTR, canvas);
        DrawLine(BlackPen, backBL, backTL, canvas);
        DrawLine(BlackPen, backBL, backBR, canvas);
        DrawLine(BlackPen, backTL, backTR, canvas);
        DrawLine(BlackPen, backBR, backTR, canvas);
        DrawLine(BlackPen, frontBL, backBL, canvas);
        DrawLine(BlackPen, frontTL, backTL, canvas);
        DrawLine(BlackPen, frontBR, backBR, canvas);
        DrawLine(BlackPen, frontTR, backTR, canvas);

    }

    private void DrawLine(Paint pen, Point a, Point b, Canvas canvas) {
        canvas.drawLine(a.x, a.y, b.x, b.y, pen);
    }

    public void setOnTouchListener() {
        theta =+2;
    }
}
