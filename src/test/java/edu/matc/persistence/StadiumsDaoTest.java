package edu.matc.persistence;

import edu.matc.entity.Stadiums;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StadiumsDaoTest {
    @Test
    public void getAllStadiums() throws Exception {
        StadiumsDao stadiumsDao = new StadiumsDao();
        List<Stadiums> stadiumsList = stadiumsDao.getAllStadiums();

        assertEquals(stadiumsList.size(), 108);
    }

}