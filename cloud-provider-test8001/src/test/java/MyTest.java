import com.mysql.cj.xdevapi.Collection;
import com.upc.springcloud.TestMain8001;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest(classes = TestMain8001.class)
public class MyTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void testMysql() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
