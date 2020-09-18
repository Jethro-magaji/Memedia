package com.memes.memedia.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataRepoTest {

    @Test
    void getFromDatabase() {

        DataRepo repo = new DataRepo("example", "example2");

        assertEquals("hello", repo.getFromDatabase("example"));

        assertEquals("world", repo.getFromDatabase("example2"));

    }
}