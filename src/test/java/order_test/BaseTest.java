package order_test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class BaseTest {

    @BeforeEach
    public void logTestStart(TestInfo testInfo) {
        System.out.printf("Test is started: [%s]%n", testInfo.getDisplayName());
    }

    @AfterEach
    public void logTestFinish(TestInfo testInfo) {
        System.out.printf("Test is finished: [%s]%n", testInfo.getDisplayName());
    }
}
