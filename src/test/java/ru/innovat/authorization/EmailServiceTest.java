package ru.innovat.authorization;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.innovat.service.authorization.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"delete-user.sql", "create-user-for-service.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
}
