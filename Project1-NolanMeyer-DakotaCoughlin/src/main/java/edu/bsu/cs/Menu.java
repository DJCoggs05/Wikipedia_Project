package edu.bsu.cs;

import edu.bsu.cs.Exceptions.networkErrorException;
import edu.bsu.cs.Exceptions.noArticleException;
import edu.bsu.cs.Exceptions.openInputStreamException;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final WikiConnection wikipediaConnection = new WikiConnection();
    private RevisionParser parser;
    private final RevisionFormatter revisionFormatter = new RevisionFormatter();
    private final Scanner scnr = new Scanner(System.in);

    public void runMenu(){
        System.out.print("Enter a title you would like revisions about:");
        String userInput = scnr.nextLine();

        if(validateUserInput(userInput)) {

            try {
                List<Revision> revisionList = inputSearch(userInput);

                if (!revisionList.isEmpty()) {
                    inputSearchPrint(revisionList);
                }
            }catch(noArticleException | networkErrorException | openInputStreamException e){
                System.err.println(e.getMessage());
            }

        }else{
            System.err.println("Please enter an article!");
        }
    }

    private List<Revision> inputSearch(String userInput) throws noArticleException, networkErrorException, openInputStreamException {
        InputStream wikiResponse = wikipediaConnection.search(userInput);
        parser = new RevisionParser(new RevisionInputStream(wikiResponse));
        return parser.parse();
    }

    private void inputSearchPrint(List<Revision> revisionList){
        System.out.println(parser.extractRedirect(parser.inputStreamInstance.openInputStream()));
        System.out.println(revisionFormatter.printRevisionList(revisionList));
    }

    protected boolean validateUserInput(String userInput){
        return !userInput.isEmpty();
    }
}
