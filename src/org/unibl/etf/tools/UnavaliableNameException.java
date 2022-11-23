package org.unibl.etf.tools;

public class UnavaliableNameException extends Exception{
    public UnavaliableNameException()
    {
        super("The name you entered is not unique!");
    }

}