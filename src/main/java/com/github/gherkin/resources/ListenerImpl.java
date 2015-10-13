package com.github.gherkin.resources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.github.gherkin.resources.DAO.*;

public class ListenerImpl implements ServletContextListener{
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Logger logger = Logger.getLogger("Musica");
        logger.log(Level.ALL, "hello");
        try {
            DAO.initConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getStackTrace().toString());
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
