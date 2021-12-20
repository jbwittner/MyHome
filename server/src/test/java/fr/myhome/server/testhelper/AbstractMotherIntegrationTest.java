package fr.myhome.server.testhelper;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public abstract class AbstractMotherIntegrationTest {

    @Autowired
    protected TestFactory testFactory;

    /**
     * Method launch before each test
     */
    @BeforeEach
    public void beforeEach() {
        SecurityContextHolder.clearContext();
        this.testFactory.resetAllList();
        this.initDataBeforeEach();
    }

    /**
     * Method launch after each test
     */
    @AfterEach
    public void afterEach(){
        SecurityContextHolder.clearContext();
        this.testFactory.resetAllList();
    }

    /**
     * Method used to prepare the data of tests
     */
    abstract protected void initDataBeforeEach();

}
