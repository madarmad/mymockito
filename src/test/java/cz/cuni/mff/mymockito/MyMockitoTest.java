package cz.cuni.mff.mymockito;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyMockitoTest {

    @Test
    public void simpleTest() {
        Object o = MyMockito.mock(Object.class);

        MyMockito.when(o.hashCode()).thenReturn(10);

        assertEquals(10, o.hashCode());
    }

    @Test
    public void multipleTest() {
        MyTestClass m1 = MyMockito.mock(MyTestClass.class);
        MyTestClass m2 = MyMockito.mock(MyTestClass.class);

        assertEquals("value", m1.getValue());

        MyMockito.when(m1.getValue()).thenReturn("hello1");
        MyMockito.when(m2.getValue()).thenReturn("hello2");

        assertEquals("hello1", m1.getValue());
        assertEquals("hello2", m2.getValue());
    }

    @Test
    public void testArg() {
        Object o = MyMockito.mock(Object.class);

        assertFalse(o.equals("true"));

        MyMockito.when(o.equals("true")).thenReturn(true);

        assertTrue(o.equals("true"));
        assertFalse(o.equals("anythingElse"));
    }

    public static class MyTestClass {

        public String getValue() {
            return "value";
        }

    }

}