package edu.bsu.cs.Exceptions;

public class noArticleException extends Exception{

    public noArticleException(){
        super("No Wikipedia article could be found!");
    }

}
