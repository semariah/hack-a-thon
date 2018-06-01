package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TeamTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewTeamObjectGetsCorrectlyCreated_true() throws Exception {
        Team team = new Team("Team: JS", "JS students of epicodus", "sem");
        assertEquals(true, team instanceof Team);
    }

    @Test
    public void TeamInstantiatesWithName_true() throws Exception {
        Team team = new Team("Team: JS", "JS students of epicodus", "sem");
        assertEquals("Team: JS", team.getName());

    }

    @Test
    public void TeamInstantiatesWithDescription_true() throws Exception {
        Team team = new Team("Team: JS", "JS students of epicodus", "sem");
        assertEquals("JS students of epicodus", team.getDescription());

    }

    @Test
    public void TeamReturnsCorrectlyMemberToAdd_true() throws Exception {
        Team team = new Team("Team: JS", "JS students of epicodus", "sem");
        assertEquals(1, team.getAll().size());

    }

    @Test
    public void TeamContainsCorrectlyMemberToAdd_true() throws Exception {
        Team team = new Team("Team: JS", "JS students of epicodus", "sem");
        assertEquals(true, team.getAll().contains("ariam"));

    }





}