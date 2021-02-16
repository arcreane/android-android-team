package fr.epsi.solitaire;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {

    private int headerBackgroundColor;
    private int headerForegroundColor;
    private int backgroundColor;
    private int redColor;


    public GameView(Context context) {
        super(context);
        postConstruct();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        postConstruct();
    }

    private void postConstruct() {
        Resources res = getResources();
        headerBackgroundColor = ((Resources) res).getColor( R.color.colorPrimaryDark );
        headerForegroundColor = res.getColor( R.color.headerForegroundColor );
        backgroundColor = res.getColor( R.color.backgroundColor );
        redColor = res.getColor( R.color.redColor );
    }


}
