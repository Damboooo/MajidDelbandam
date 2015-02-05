package ir.chocolategroup.majiddelbandam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
	int x1 = 0;
	int x2 = 0;
	int y1 = 0;
	int y2 = 0;
	Paint paint = new Paint();

	public DrawView(Context context,int a1 , int a2 , int b1 , int b2) {
		super(context);
		x1 = a1;
		x2 = a2;
		y1 = b1;
		y2 = b2;
		paint.setColor(Color.BLACK);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawLine(x1, y1, x2, y2, paint);
//		canvas.drawLine(200, 0, 0, 200, paint);
	}

}
