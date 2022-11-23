package org.unibl.etf.game.cards;

public class OrdinaryCard extends Card{
    private int numOfFields;

    public OrdinaryCard(int numOfFields)
    {
        super();
        this.numOfFields=numOfFields;
        switch (numOfFields){
            case 1:
                this.image=this.getSlika()+"\\one.JPG";
                break;
            case 2:
                this.image=this.getSlika()+"\\two.JPG";
                break;
            case 3:
                this.image=this.getSlika()+"\\three.JPG";
                break;
            case 4:
                this.image=this.getSlika()+"\\four.JPG";
                break;
        }

    }

    public int getNumOfFields() {
        return numOfFields;
    }

    public void setNumOfFields(int numOfFields) {
        this.numOfFields = numOfFields;
    }
}