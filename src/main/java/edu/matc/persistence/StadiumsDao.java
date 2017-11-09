package edu.matc.persistence;

import edu.matc.entity.Stadiums;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class StadiumsDao {

    private final Logger log = Logger.getLogger(this.getClass());

    public List<Stadiums> getAllStadiums() {
        List<Stadiums> stadiums = new ArrayList<Stadiums>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        stadiums = session.createCriteria(Stadiums.class).list();
        session.close();
        return stadiums;
    }

}