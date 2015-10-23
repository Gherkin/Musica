import com.github.gherkin.data.Song;
import com.github.gherkin.resources.DAO;
import org.junit.BeforeClass;
import org.junit.Test;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DAOTest {

    @BeforeClass
    public static void init() throws SQLException {

        DAO.initConnection();
    }

    @Test
    public void regularTest() {

        try {
            Song song = new Song(4, "Test Song");
            DAO.insert(song);
            Song song2 = DAO.retrieve(song.getId());

            assertEquals(song, song2);

            DAO.delete(song.getId());

        } catch (SQLException e) {
           fail(e.getMessage());
        }
    }

    @Test
    public void nullIdTest() {

        try {
            Song song = new Song(null, "Test Song");
            DAO.insert(song);

            //delete it

        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = NullPointerException.class)
    public void nullNameTest() {

        try {
            Song song = new Song(34, null);
            DAO.insert(song);
            Song song2 = DAO.retrieve(song.getId());

            assertEquals(song, song2);

            DAO.delete(song.getId());

        } catch(SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = SQLException.class)
    public void deleteTest() throws SQLException {

        Song song = new Song(40, "Test song");
        DAO.insert(song);
        Song song2 = DAO.retrieve(song.getId());

        if(!song.equals(song2)) {
           fail("songs arent equal");
        }

        DAO.delete(song.getId());

        song2 = DAO.retrieve(song.getId());
    }
}
