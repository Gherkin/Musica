import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by niyohn on 2015-10-23.
 */
public class SongResourceTest extends Mockito {

    @Test
    public void testGet() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse respone = mock(HttpServletResponse.class);
    }
}
