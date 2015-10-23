import com.github.gherkin.data.Song;
import com.github.gherkin.resources.DAO;
import org.junit.BeforeClass;
import org.junit.Test;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DAOTest {

    @BeforeClass
    public static void init() throws SQLException {

        DAO.initConnection();
    }

    @Test
    public void regularTest() throws SQLException {

        try {
            Song song = new Song(4, "Test Song");
            DAO.insert(song);
            Song song2 = DAO.retrieve(song.getId());

            assertEquals(song, song2);

            DAO.delete(song.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void nullIdTest() throws SQLException {

        Song song = new Song(null, "Test Song");
        DAO.insert(song);
    }

    @Test(expected = NullPointerException.class)
    public void nullNameTest() throws SQLException, NullPointerException {

        Song song = new Song(34, null);
        DAO.insert(song);
        Song song2 = DAO.retrieve(song.getId());

        assertEquals(song, song2);
    }
}
