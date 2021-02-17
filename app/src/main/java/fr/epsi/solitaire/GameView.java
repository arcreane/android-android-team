package fr.epsi.solitaire;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View {

    int headerBackgroundColor;
    int headerForegroundColor;
    int backgroundColor;
    int redColor;

    public Game game = new Game();

    Bitmap imgPique;
    Bitmap imgPiqueLittle;
    Bitmap imgTreffle;
    Bitmap imgTreffleLittle;
    Bitmap imgCarreau;
    Bitmap imgCarreauLittle;
    Bitmap imgCoeur;
    Bitmap imgCoeurLittle;

    Bitmap imgBack;

    float deckWidth;
    float deckHeight;
    float deckMargin;


    public GameView(Context context) {
        super(context);
        postConstruct();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        postConstruct();
    }

    // Chargement des codes couleurs.
    private void postConstruct() {
        Resources res = getResources();
        headerBackgroundColor = res.getColor( R.color.colorPrimaryDark );
        headerForegroundColor = res.getColor( R.color.headerForegroundColor );
        backgroundColor = res.getColor( R.color.backgroundColor );
        redColor = res.getColor( R.color.redColor );
    }


    // Retaillage des bitmaps en fonction de la taille de la fenêtre.
    @Override
    protected void onSizeChanged( int width, int height, int oldw, int oldh ) {
        super.onSizeChanged( width, height, oldw, oldh );

        deckMargin = width * 0.025f;
        deckWidth = (width - (Game.DECK_COUNT + 1) * deckMargin) / Game.DECK_COUNT;
        deckHeight = deckWidth * 1.4f;

        try {
            int imageSize = (int) (deckWidth * 0.66);
            int imageLittleSize = (int) (deckWidth / 3);


            imgPique = BitmapFactory.decodeResource(getResources(), R.drawable.pique);
            imgPiqueLittle = Bitmap.createScaledBitmap(imgPique, imageLittleSize, imageLittleSize, true);
            imgPique = Bitmap.createScaledBitmap(imgPique, imageSize, imageSize, true);

            imgTreffle = BitmapFactory.decodeResource(getResources(), R.drawable.treffle);
            imgTreffleLittle = Bitmap.createScaledBitmap(imgTreffle, imageLittleSize, imageLittleSize, true);
            imgTreffle = Bitmap.createScaledBitmap(imgTreffle, imageSize, imageSize, true);

            imgCoeur = BitmapFactory.decodeResource(getResources(), R.drawable.coeur);
            imgCoeurLittle = Bitmap.createScaledBitmap(imgCoeur, imageLittleSize, imageLittleSize, true);
            imgCoeur = Bitmap.createScaledBitmap(imgCoeur, imageSize, imageSize, true);

            imgCarreau = BitmapFactory.decodeResource(getResources(), R.drawable.carreau);
            imgCarreauLittle = Bitmap.createScaledBitmap(imgCarreau, imageLittleSize, imageLittleSize, true);
            imgCarreau = Bitmap.createScaledBitmap(imgCarreau, imageSize, imageSize, true);

            imgBack = BitmapFactory.decodeResource(getResources(), R.drawable.back);
            imgBack = Bitmap.createScaledBitmap(imgBack, (int)deckWidth, (int)deckHeight, true);

        } catch (Exception exception) {
            Log.e("ERROR", "Error loading card images");
        }
    }

    /**
     * Calcul de la "bounding box" de la stack spécifiée en paramètre.
     */
    private RectF computeStackRect( int index ) {
        float x = deckMargin + (deckWidth + deckMargin) * index;
        float y = getHeight() * 0.17f;
        return new RectF( x, y, x+deckWidth, y+deckHeight );
    }


    /**
     * Calcul de la "bounding box" de la pile retournée associée à la pioche.
     */
    private RectF computeReturnedPiocheRect() {
        float x = deckMargin + (deckWidth + deckMargin) * 5;
        float y = getHeight() * 0.17f;
        return new RectF( x, y, x+deckWidth, y+deckHeight );
    }


    /**
     * Calcul de la "bounding box" de la pile découverte associée à la pioche.
     */
    private RectF computePiocheRect() {
        float x = deckMargin + (deckWidth + deckMargin) * 6;
        float y = getHeight() * 0.17f;
        return new RectF( x, y, x+deckWidth, y+deckHeight );
    }


    /**
     * Calcul de la "bounding box" du deck spécifié en paramètre.
     */
    private RectF computeDeckRect(int index, int cardIndex ) {
        float x = deckMargin + (deckWidth + deckMargin) * index;
        float y = getHeight() * 0.30f + cardIndex * computeStepY();
        return new RectF( x, y, x+deckWidth, y+deckHeight );
    }


    /**
     * Calcul du décalage en y pour toutes les cartes d'un deck.
     */
    public float computeStepY() {
        return ( getHeight()*0.9f - getHeight()*0.3f ) / 17f;
    }

}
