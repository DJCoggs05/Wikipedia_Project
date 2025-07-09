package edu.bsu.cs;

import edu.bsu.cs.Exceptions.noArticleException;
import edu.bsu.cs.Exceptions.openInputStreamException;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisionParserTest {

    @Test
    public void returnsFirstRevisionNameTest() throws noArticleException, openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert sampleFile != null;
        RevisionParser parser = new RevisionParser(new RevisionInputStream(sampleFile));
        List<Revision> firstRevisionName = parser.parse();
        assertEquals("Miklogfeather", firstRevisionName.getFirst().name);
    }

    @Test
    public void returnsFirstRevisionTimeStampTest() throws noArticleException, openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert sampleFile != null;
        RevisionParser parser = new RevisionParser(new RevisionInputStream(sampleFile));
        List<Revision> firstRevisionTimeStamp = parser.parse();
        assertEquals("2023-09-07T18:34:43Z",firstRevisionTimeStamp.getFirst().timeStampOfRevision);
    }

    @Test
    public void returnsListOfSize4() throws noArticleException, openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert sampleFile != null;
        RevisionParser parser = new RevisionParser(new RevisionInputStream(sampleFile));
        List<Revision> revisionList = parser.parse();
        assertEquals(4,revisionList.size());
    }

    @Test
    public void extraRevisionsTest() throws noArticleException, openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert sampleFile != null;
        RevisionParser parser = new RevisionParser(new RevisionInputStream(sampleFile));
        JSONArray revisions = parser.extractRevisions(new ByteArrayInputStream(parser.inputStreamInstance.inputStream));
        assertEquals(4,revisions.size());
    }

    @Test
    public void convertRevisionsToListTest() throws noArticleException, openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert sampleFile != null;
        RevisionParser parser = new RevisionParser(new RevisionInputStream(sampleFile));
        JSONArray parsedRevisions = parser.extractRevisions(new ByteArrayInputStream(parser.inputStreamInstance.inputStream));
        List<Revision> revisions = parser.convertRevisionsToList(parsedRevisions);
        assertEquals(4,revisions.size());
    }

    @Test
    public void extractRedirectTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json");
        assert sampleFile != null;
        RevisionParser parser = new RevisionParser(new RevisionInputStream(sampleFile));
        String parsedRedirect = parser.extractRedirect(new ByteArrayInputStream(parser.inputStreamInstance.inputStream));
        assertEquals("Redirected to Frank Zappa",parsedRedirect);
    }

}
