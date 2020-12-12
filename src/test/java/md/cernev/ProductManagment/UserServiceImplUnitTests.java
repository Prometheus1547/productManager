package md.cernev.ProductManagment;

import md.cernev.ProductManagment.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplUnitTests {

    @Autowired
    private UserService userService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        assertThat(userService.list()).isNotEmpty();
    }
}
