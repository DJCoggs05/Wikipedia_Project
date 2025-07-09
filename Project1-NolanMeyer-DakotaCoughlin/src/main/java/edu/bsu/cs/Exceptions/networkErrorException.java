package edu.bsu.cs.Exceptions;

public class networkErrorException extends Exception {
    public networkErrorException(){
        super("There seems to be a network error!");
    }
}
