package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.shape.DiamondShape;

public class LevitatingFigure extends Figure implements levitationInterface{
    private StarShape shape;
    public LevitatingFigure(DiamondShape ds, Deck deck)
    {
        super(ds,deck);
    }

    @Override
    public void levitate() {

    }
}