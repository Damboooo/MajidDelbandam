package ir.chocolategroup.majiddelbandam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {
    private Context mContext;
    
    private Path mySegment = new Path();
    
    private Paint mWaterPaint;
    private Paint mWaterStroke;
    private Paint mTextPaint;
    private Paint mBackgroundPaint;
    private int mPercent;
    private float mPhase = 0;
    private float mAmplitude = 10;
    private int mHeight;
    private int mWidth;
    private long mStartTime;
    private Path mSegment = new Path();
    private Path mTopPath = new Path();
    private Path mBottomPath = new Path();
    private PointF mCenter = new PointF();
    private RectF circleRect = new RectF();
    private RectF textsRect = new RectF();
    private float mRadius = 1.0f;
    private float startAngle = 1;
    private float sweepAngle = 1;
    
    public PathView(Context context) {
        super(context);
        mContext = context;
        mStartTime = System.currentTimeMillis();
        mPercent = 11;
        mWaterPaint = new Paint();
        mWaterPaint.setColor(0xff6666ff);
        mWaterPaint.setAntiAlias(true);
        mWaterPaint.setDither(true);
        mWaterPaint.setStyle(Paint.Style.FILL);
        mWaterStroke = new Paint();
        mWaterStroke.setColor(0xff6666ff);
        mWaterStroke.setAntiAlias(true);
        mWaterStroke.setDither(true);
        mWaterStroke.setStyle(Paint.Style.STROKE);
        mWaterStroke.setStrokeJoin(Paint.Join.ROUND);
        mWaterStroke.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint = new Paint();
        mTextPaint.setColor(0xff222233);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextAlign(Align.CENTER);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfffefefe);
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setDither(true);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setStrokeJoin(Paint.Join.ROUND);
        mBackgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        setPaths();
    }
    
    public PathView(Context context, AttributeSet attrs) {
        this(context);
    }
    
    public PathView(Context context, AttributeSet attrs, int d) {
    	this(context);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	super.onSizeChanged(w, h, oldw, oldh);
    	mCenter.x = getWidth() / 2;
    	mCenter.y = getHeight() / 2;
        mRadius = Math.min(getWidth(), getHeight()) / 2 - (int) mWaterPaint.getStrokeWidth() - 30;
        circleRect.set(mCenter.x - mRadius, mCenter.y - mRadius, mCenter.x + mRadius, mCenter.y + mRadius);
        float r = mRadius + 15;
        textsRect.set(mCenter.x - r, mCenter.y - r, mCenter.x + r, mCenter.y + r);

        setPaths();
    }
    
    private void setPaths()
    {
        float y = mCenter.y + mRadius - (2 * mRadius * mPercent / 100 - 1);
        float x = mCenter.x - (float) Math.sqrt(Math.pow(mRadius, 2) - Math.pow(y - mCenter.y, 2));

        float angle = (float) Math.toDegrees(Math.atan((mCenter.y - y) / (x - mCenter.x)));
        startAngle = 180 - angle;
        sweepAngle = 2 * angle - 180;

        mSegment.rewind();
        mSegment.addArc(circleRect, startAngle, sweepAngle);
        
        mTopPath.rewind();
        mTopPath.addArc(textsRect, 180, 180);
        
        mBottomPath.rewind();
        mBottomPath.addArc(textsRect, 180, -180);
    }
    
    public void setPercentage(int p) {
        p = Math.max(0, Math.min(100, p));
        mPercent = p;
        setPaths();
    }
    
    public int getPercentage() {
        return mPercent;
    }
    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawPaint(mBackgroundPaint);
        mPhase = 0.005f * (System.currentTimeMillis() - mStartTime);
        
        mWidth = getWidth();
        mHeight = getHeight();
        
        float[] waterTop = new float[((int)Math.ceil(mWidth))];
        float pos = mRadius * (2*(1-mPercent/100f)-1) + mCenter.y;
        
        int startX = (int)(mCenter.x + mRadius * Math.cos((Math.PI/180) * startAngle));
        int endX = (int)(mCenter.x + mRadius * Math.cos((Math.PI/180) * (startAngle+sweepAngle)));
        int startY = (int)(mCenter.y + mRadius * Math.sin((Math.PI/180) * startAngle));
        
        setPaths();
        float x0, y0;
        waterTop[0] = 0;
        y0 = 0;
        for (int i=1; i<waterTop.length; ++i) {
            waterTop[i] = (float) (mAmplitude * Math.sin(mPhase + i*Math.PI/72) * Math.sin(i*Math.PI/180));
            x0 = ((float)i/waterTop.length) * mWidth;
            y0 = waterTop[i-1] + pos;
            
            if (startX == i) {
            	mSegment.moveTo(x0, startY);
            	mSegment.lineTo(x0, y0);
            } else if (endX == i) {
            	mSegment.lineTo(x0, startY);
            } else if (startX < i && i < endX) {
            	mSegment.lineTo(x0, y0);
            }
        }
        
        mySegment.rewind();
        mySegment.addArc(circleRect, startAngle, sweepAngle);
        mySegment.moveTo(10, 10);
        mySegment.lineTo(100, 100);
        canvas.drawPath(mSegment, mWaterPaint);
        //canvas.drawCircle(mCenter.x, mCenter.y, mRadius, mWaterStroke);
        //canvas.drawCircle(mCenter.x, mCenter.y, .5f*(Math.min(getWidth(), getHeight())-mWaterStroke.getStrokeWidth()), mWaterStroke);
        
        //canvas.drawTextOnPath("Income", mTopPath, 0, 0, mTextPaint);
        //canvas.drawTextOnPath("Outcome", mBottomPath, 0, 0, mTextPaint);
        
        invalidate();
    }
    
    public boolean isClickedOnTopBtn(PointF p) {
    	double dist = Math.sqrt(Math.pow(p.x-mCenter.x, 2) + Math.pow(p.y-mCenter.y, 2));
    	if (dist < mRadius)
    		return false;
    	return p.y < mRadius;
    }
    public boolean isClickedOnBottomBtn(PointF p) {
    	double dist = Math.sqrt(Math.pow(p.x-mCenter.x, 2) + Math.pow(p.y-mCenter.y, 2));
    	if (dist < mRadius)
    		return false;
    	return p.y > mRadius;
    }
}