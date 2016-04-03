package ir.chocolategroup.majiddelbandam;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by mohammad hosein on 21/03/2016.
 */

public class ScreenLevelPageFragment extends Fragment {

    public static final String ARG_START_LEVEL = "start";
    public static final String ARG_END_LEVEL = "end";
    public static final String ARG_NUMBER_OF_ROW = "row";
    public static final String ARG_NUMBER_OF_COL = "col";

    private static final int lockLevelColor = Color.RED;
    private static final int notDoneLevelColor = Color.BLUE;
    private static final int doneLevelColor = Color.GREEN;

    private static Bitmap ActiveBitmap;
    private static Bitmap DeactiveBitmap;
    private static Bitmap TransparentBitmap;
    private static Paint circlePaint;
    private static Paint textPaint;



    public static void init(Context context,MetaData metaData ) {
        ScreenLevelPageFragment.metaData = metaData;
        ActiveBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.level_image);
        DeactiveBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.level_image_bw);
        TransparentBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.transparent);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStrokeWidth(20);
        circlePaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(150);
        Typeface font = Typeface.createFromAsset(context.getAssets(),
                "swissko.ttf");
        textPaint.setTypeface(font);
    }

    public static ScreenLevelPageFragment create(int startLevel,int endLevel, int numberOfRow, int numberOfCol ) {
        ScreenLevelPageFragment fragment = new ScreenLevelPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_START_LEVEL, startLevel);
        args.putInt(ARG_END_LEVEL, endLevel);
        args.putInt(ARG_NUMBER_OF_COL, numberOfCol);
        args.putInt(ARG_NUMBER_OF_ROW, numberOfRow);
        fragment.setArguments(args);
        return fragment;
    }

    private static MetaData metaData;

    public ScreenLevelPageFragment()
    {

    }
    private int mStartLevel;
    private int mEndLevel;
    private int mNumberOfCol;
    private int mNumberOfRow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStartLevel = getArguments().getInt(ARG_START_LEVEL);
        mEndLevel = getArguments().getInt(ARG_END_LEVEL);
        mNumberOfCol = getArguments().getInt(ARG_NUMBER_OF_COL);
        mNumberOfRow = getArguments().getInt(ARG_NUMBER_OF_ROW);
    }
    private int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.main_activity_slide_page, container, false);

        TableLayout levelLayout = ((TableLayout) rootView.findViewById(R.id.mainSpace));


        final ImageView[] levels = new ImageView[mNumberOfCol*mNumberOfRow];

        TableRow row = new TableRow(getActivity());
        for (int i = 0; i < mNumberOfCol*mNumberOfRow; i++) {
            levels[i] = new ImageView(getActivity());
            if(i<mEndLevel-mStartLevel) {
                id = i + mStartLevel;
                // if for lock or unlock levels
                if (id + 1 <= metaData.getLastUnlockLevel()) {
                    levels[i].setImageDrawable(new BitmapDrawable(getResources(), createPiture(true, doneLevelColor, id + 1, ((double) metaData.minMove[id]) / ((double) metaData.userMove[id]))));
                } else {
                    levels[i].setImageDrawable(new BitmapDrawable(getResources(), createPiture(false, lockLevelColor, id + 1, 0)));
                }

                levels[i].setOnClickListener(new View.OnClickListener() {
                    int id1 = id;
                    MetaData _metaData = metaData;

                    @Override
                    public void onClick(View v) {
                        if (id1 + 1 <= _metaData.userMove.length) {
                            Intent level = new Intent(getActivity(),
                                    LevelActivity.class);
                            level.putExtra("levelnumber", id1 + 1); // id is
                            // the
                            // level
                            // number
                            startActivity(level);
                        }
                    }
                });
            }else
            {
                levels[i].setImageDrawable(new BitmapDrawable(getResources(),TransparentBitmap));
            }

            levels[i].setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT
            ));
            if (i % mNumberOfCol == 0) {
                row = new TableRow(getActivity());
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
                row.setGravity(Gravity.CENTER_HORIZONTAL);
                levelLayout.addView(row);

            }
            row.addView(levels[i]);


        }



        return rootView;
    }
    private Bitmap createPiture(boolean isActive, int circleColor, int levelNumber, double arc) {
        Bitmap basePic;
        if (isActive)
            basePic = ActiveBitmap;
        else
            basePic = DeactiveBitmap;

        Bitmap resPic = Bitmap.createBitmap(basePic.getWidth() + 40, basePic.getHeight() + 40, Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(resPic);

        String text = levelNumber + "";
        Rect bounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        circlePaint.setColor(circleColor);

        if (isActive) {
            RectF rec = new RectF(20, 20, resPic.getWidth() - 20, resPic.getHeight() - 20);
            int deg = (int) (arc * 360);
            tempCanvas.drawArc(rec, 270, deg, false, circlePaint);
            circlePaint.setColor(Color.RED);
            tempCanvas.drawArc(rec, (270 + deg) % 360, 360 - deg, false, circlePaint);
        } else {
            tempCanvas.drawCircle(resPic.getWidth() / 2, resPic.getHeight() / 2, resPic.getWidth() / 2 - 20, circlePaint);
        }


        tempCanvas.drawBitmap(basePic, 20, 20, null);
        tempCanvas.drawText(text, resPic.getWidth() / 2 - bounds.width() / 2, resPic.getHeight() / 2 + bounds.height() / 2, textPaint);
        return resPic;
    }
}
