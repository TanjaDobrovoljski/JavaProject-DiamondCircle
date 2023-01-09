package org.unibl.etf.tools;

public class NotEnoughArguments extends Exception{
    public NotEnoughArguments(){
        super("Najmanje morate unijeti dva igraca!");
    }
}
