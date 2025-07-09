package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WikiConnectionTest {

    @Test
    public void createUrlRequestTest(){
        WikiConnection wikiConnection = new WikiConnection();
        assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Frank%20Zappa&rvprop=timestamp|user&rvlimit=21&redirects",wikiConnection.createRequestUrl("Frank Zappa"));
    }
}
