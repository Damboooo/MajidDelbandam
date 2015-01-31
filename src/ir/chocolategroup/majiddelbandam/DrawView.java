package ir.chocolategroup.majiddelbandam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
	Paint paint = new Paint();

	public DrawView(Context context) {
		super(context);
		paint.setColor(Color.BLACK);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawLine(0, 0, 200, 200, paint);
		canvas.drawLine(200, 0, 0, 200, paint);
	}

}
