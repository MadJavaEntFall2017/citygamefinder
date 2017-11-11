package edu.matc.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


/**
 * This class declares root resource and provider classes
 *
 * @author Great Lakes Team
 */
@ApplicationPath("/")
public class CityGameFinderApplication extends Application {

    /**
     * The method returns a non-emtpy collection with classes, that must be included in the published JAX-RS application
     * @return the Set of classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(SportsService.class );
        return h;
    }
}