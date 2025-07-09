package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    public void validateUserInputEmptyString(){
        Menu menu = new Menu();
        assertFalse(menu.validateUserInput(""));
    }

    @Test
    public void validateUserInput(){
        Menu menu = new Menu();
        assertTrue(menu.validateUserInput("Test User Name"));
    }
}
