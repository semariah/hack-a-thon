package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {

    @Before
    public void setUp() throws Exception {
    }

    private Team setUpNewTeam() {
        return new Team("Team: JS", "JS students of epicodus");
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
    public void TeamReturnsCorrectlyMembers_2() throws Exception {
        Team team = setUpNewTeam();
        List<String> teamMembers = new ArrayList<String>();
        teamMembers.add("The Women");
        team.setTeamMembers(teamMembers);
        assertEquals(1, team.getTeamMembers().size());

    }

    @Test
    public void AllTeamsAreReturnedCorrectly_true() throws Exception {
        Team team = setUpNewTeam();
        assertEquals(1, Team.getTeamInstances().size());

    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Team team = setUpNewTeam();
        assertEquals(false, team.getPublished());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Team team = setUpNewTeam();
        assertEquals(LocalDateTime.now().getDayOfWeek(), team.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void getId_teamsInstantiateWithAnID_1() throws Exception{
//        Team.clearAllTeams();
        Team team = setUpNewTeam();
        assertEquals(1, team.getId());
    }







//    @Test
//    public void TeamContainsCorrectlyMe_true() throws Exception {
//        Team team = setUpNewTeam();
//        assertEquals(true, team.getAll().contains(""));
//
//    }





}