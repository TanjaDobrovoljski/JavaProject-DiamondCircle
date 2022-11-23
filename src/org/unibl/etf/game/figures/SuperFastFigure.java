package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.shape.DiamondShape;

public class SuperFastFigure extends Figure {
    private SpiralShape shape;
    public SuperFastFigure(DiamondShape ds, Deck deck)
    {
        super(ds,deck);
        numberOfMoves*=2;
    }


}