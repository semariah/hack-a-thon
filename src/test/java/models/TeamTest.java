package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }

    @Test
    public void NewTeamObjectGetsCorrectlyCreated_true() throws Exception {
        Team team = setUpNewTeam();
        assertEquals(true, team instanceof Team);
    }

    @Test
    public void TeamInstantiatesWithName_true() throws Exception {
        Team team = setUpNewTeam();
        team.setName("Team: JS");
        assertEquals("Team: JS", team.getName());

    }

    @Test
    public void TeamInstantiatesWithDescription_true() throws Exception {
        Team team = setUpNewTeam();
        team.setDescription();
        assertEquals("JS students of epicodus", team.getDescription());

    }


    @Test
    public void getId_returnsId_1() throws Exception{
        Team team = setUpNewTeam();
        team.setId(1);
        assertNotEquals(1, team.getId());
    }

    private Team setUpNewTeam() {
        return new Team("Team: JS", "JS students of epicodus");
    }


}