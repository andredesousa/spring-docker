package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

@DisplayName("Application")
class ApplicationTests {

    static MockedStatic<SpringApplication> springApplicationMock;

    @BeforeAll
    static void beforeAll() {
        springApplicationMock = mockStatic(SpringApplication.class);
    }

    @Test
    @DisplayName("#main method was called")
    void contextLoads() {
        springApplicationMock
            .when(() -> SpringApplication.run(Application.class, new String[] {}))
            .thenCallRealMethod();

        Application.main(new String[] {});

        springApplicationMock.verify(() -> SpringApplication.run(Application.class, new String[] {}), times(1));
    }

    @Test
    @DisplayName("#getHello returns 'Hello World!' message")
    void findAll() {
        Application app = new Application();

        assertEquals("Hello World!", app.getHello());
    }
}