package Dao;

import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private org.sql2o.Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        conn = (org.sql2o.Connection) sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingTeamsSetsId() throws Exception {
        Team team = setupNewTeam();
        int originalTeamId = team.getId();
        teamDao.add(team);
        assertEquals(originalTeamId, team.getId());
    }

    @Test
    public void existingTeamsCanBeFoundById() throws Exception {
        Team team = setupNewTeam ();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }

    @Test
    public void addedTeamsAreReturnedFromgetAll() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        assertEquals(1, teamDao.getAll().size());
    }

    @Test
    public void noTeamsReturnsEmptyList() throws Exception {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void updateChangesTeamName() throws Exception {
        String initialDescription = "Js students of epicodus";
        Team team = setupNewTeam();
        teamDao.add(team);
        teamDao.update(team.getId(), "javascript", "javascript students");
        Team updatedTeam = teamDao.findById(team.getId());
        assertNotEquals(initialDescription, updatedTeam.getName());
    }

    @Test
    public void updateChangesTeamDescription() throws Exception {
        String initialDescription = "Js students of epicodus";
        Team team = setupNewTeam();
        teamDao.add(team);
        teamDao.update(team.getId(), "javascript", "javascript students");
        Team updatedTeam = teamDao.findById(team.getId());
        assertNotEquals(initialDescription, updatedTeam.getDescription());
    }

    @Test
    public void deleteByIdDeletesCorrectTeam() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0, teamDao.getAll().size());
    }


    @Test
    public void clearAllClearsAll() throws Exception {
        Team team = setupNewTeam();
        Team otherTeam = setupNewTeam();
        teamDao.add(team);
        teamDao.add(otherTeam);
        int daoSize = teamDao.getAll().size();
        teamDao.clearAllTeams();
        assertTrue(daoSize > 0 && daoSize > teamDao.getAll().size());
    }


    private Team setupNewTeam() {
        return new Team ("Team: JS", "JS students of epicodus");
    }
}
