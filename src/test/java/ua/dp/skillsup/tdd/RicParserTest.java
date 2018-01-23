package ua.dp.skillsup.tdd;

import org.junit.*;
import ua.dp.skillsup.tdd.ric.Ric;
import ua.dp.skillsup.tdd.ric.RicParser;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RicParserTest {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After class");
    }

    @Before
    public void before() {
        System.out.println("Before");
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @Test
    public void noOptionalParameterTest() {
        //Given
        RicParser parser = new RicParser();
        java.lang.String code = "USDFB=";

        //When
        Ric ric = parser.parse(code);

        //Than
        assertEquals("Wrong currency", "USD",
                ric.getCurrency());
        assertEquals("Wrong tenor", "FB",
                ric.getTenor());
    }

    @Test
    public void optionalParameterTest() {
        RicParser parser = new RicParser();
        Ric ric = parser.parse("USDFA=GBP");
        assertEquals("Wrong currency", "USD",
                ric.getCurrency());
        assertEquals("Wrong tenor", "FA",
                ric.getTenor());
        assertEquals("Wrong second currency", "GBP",
                ric.getContributor());
    }

    @Test(expected = NullPointerException.class)
    public void testException() {
        String s = null;
        s.toString();
    }

//    @Test
//    public void testWithMatchers() {
//        Object actual = "aa";
//        assertThat(actual, allOf(describedAs("Is string", is(String.class)), notNullValue()));
//    }
}
