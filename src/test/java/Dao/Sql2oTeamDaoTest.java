package Dao;

import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
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
        assertNotEquals(originalTeamId, team.getId());
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


    private Team setupNewTeam() {
        return new Team ("Team: JS", "JS students of epicodus");
    }
}
