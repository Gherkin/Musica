import com.github.gherkin.resources.DAO;
import org.junit.Test;


import java.sql.SQLException;

public class DAOTest {
    @Test(expected = SQLException.class)
    public void retrieveTest() {
        try {
            DAO.retrieve(34214);
        } catch (SQLException e) {
            e.printStackTrace();
            org.junit.Assert.assertEquals(e.getMessage(), "yo");
        }
    }
}
