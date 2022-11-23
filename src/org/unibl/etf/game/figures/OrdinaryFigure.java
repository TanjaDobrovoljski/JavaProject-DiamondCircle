package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.shape.DiamondShape;

public class OrdinaryFigure extends Figure{
    private CircleShape shape;
    public OrdinaryFigure(DiamondShape ds, Deck deck)
    {
        super(ds,deck);
    }
}