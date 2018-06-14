package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void NewMemberObjectCreatesCorrectly_true() throws Exception {
        Member member = setupNewMember();
        assertEquals(true, member instanceof Member);
    }

    @Test
    public void setName_setsCorrectly() {
        Member member = setupNewMember();
        member.setName("semhar");
        assertEquals("semhar", member.getName());
    }

    @Test
    public void setId_setsMemberIdTo_1() {
        Member member = setupNewMember();
        member.setId(1);
        assertEquals(1, member.getId());
    }

    @Test
    public void setTeamId_CorrectlyTo_1() {
        Member member = setupNewMember();
        member.setTeamId(1);
        assertEquals(1, member.getTeamId());
    }

    private Member setupNewMember() {
        return new Member("semhar",1);
    }

}
