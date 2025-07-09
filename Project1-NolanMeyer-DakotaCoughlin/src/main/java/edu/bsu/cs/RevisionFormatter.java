package edu.bsu.cs;

import java.util.List;

public class RevisionFormatter {

    public String printRevisionList(List<Revision> revisionList){

        int lineNumber = 1;

        StringBuilder output = new StringBuilder();

        for(Revision revision:revisionList){
            output.append(String.format("%d  %s",lineNumber,formatOutput(revision)));
            lineNumber++;
        }

        return output.toString();
    }

    protected String formatOutput(Revision revision){
        return String.format("%s  %s\n",revision.timeStampOfRevision,revision.name);
    }

}
