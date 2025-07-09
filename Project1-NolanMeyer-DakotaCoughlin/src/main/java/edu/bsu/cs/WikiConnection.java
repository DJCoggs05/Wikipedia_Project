package edu.bsu.cs;

import edu.bsu.cs.Exceptions.networkErrorException;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikiConnection {

    public InputStream search(String pageTitle) throws networkErrorException {
        String url = createRequestUrl(pageTitle);
        return getInputStream(url);
    }

    protected String createRequestUrl(String pageTitle){
        String encodedTitle = URLEncoder.encode(pageTitle, StandardCharsets.UTF_8);
        encodedTitle = encodedTitle.replace("+","%20");
        return "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                + encodedTitle + "&rvprop=timestamp|user&rvlimit=21&redirects";
    }

    private InputStream getInputStream(String url) throws networkErrorException {
        InputStream output;
        try{
            @SuppressWarnings("deprecation")
            URL urlConnection = new URL(url);
            URLConnection connection = urlConnection.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (nolan.meyer@bsu.edu)");
            output = connection.getInputStream();
        }catch (Exception e) {
            throw new networkErrorException();
        }
        return output;
    }
}
