package optjava.ch11;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author ben
 */
public class SimpleTestSimpleDict {

    @Test
    public void empty_dicts_are_empty() {
        SimpleDict sd = new SimpleDict(16);
        assertTrue("Newly ctor'd object should be empty", sd.isEmpty());
        assertEquals("Newly ctor'd object should be empty", 0, sd.size());
        String rem = sd.remove("Foo");
        assertNull("Removed value should be null", rem);
    }

    @Test
    public void simple_put_and_get() {
        SimpleDict sd = new SimpleDict(16);
        sd.put("Foo", "Bar");
        assertEquals(1, sd.size());
        assertFalse("Dict with items should be non-empty", sd.isEmpty());
        assertEquals("get should retrieve Bar", "Bar", sd.get("Foo"));
        sd.put("Foo", "Bar");
        assertEquals(1, sd.size());
        assertTrue("Should contain Foo as a key", sd.containsKey("Foo"));
        assertFalse("Should not contain Fox as a key", sd.containsKey("Fox"));
        assertEquals("get should retrieve Bar", "Bar", sd.get("Foo"));
        sd.put("Foo", "Baz");
        assertEquals(1, sd.size());
        assertEquals("get should retrieve Baz", "Baz", sd.get("Foo"));
        sd.put("Fox", "Box");
        assertEquals(2, sd.size());
        assertEquals("get(Fox) should retrieve Box", "Box", sd.get("Fox"));
    }

    @Test
    public void simple_put_and_remove() {
        SimpleDict sd = new SimpleDict(16);
        sd.put("Foo", "Baz");
        sd.put("Fox", "Box");
        assertEquals(2, sd.size());
        String rem = sd.remove("Foo");
        assertEquals("Removed value should be Baz", "Baz", rem);
        assertEquals(1, sd.size());

    }

    @Test
    public void remove_on_empty_gives_null() {
        SimpleDict sd = new SimpleDict(16);
        String rem = sd.remove("Foo");
        assertNull("Removed value should be null", rem);
        assertEquals(0, sd.size());
    }

    @Test
    public void add_1_and_remove_on_empty_gives_initial_add() {
        SimpleDict sd = new SimpleDict(16);
        sd.put("Foo", "Bar");
        String rem = sd.remove("Foo");
        assertEquals("Removed value should be Bar", "Bar", rem);
        assertEquals(0, sd.size());
    }

    @Test
    public void add_a_and_b_then_remove_b_gives_initial_vb() {
//        SimpleDict sd = new SimpleDict(16);
//        sd.put("Foo", "Bar");
//        String rem = sd.remove("Foo");
//        assertEquals("Removed value should be Bar", "Bar", rem);
//        assertEquals(0, sd.size());
    }

    @Test
    public void capacity_ctor() {
        SimpleDict sd = new SimpleDict(16);
        assertEquals("Default capacity is 16", 16, sd.capacity());
        sd = new SimpleDict(8);
        assertEquals("Expected capacity is 8", 8, sd.capacity());
        sd = new SimpleDict(17);
        assertEquals("Expected capacity is 16", 16, sd.capacity());
        sd = new SimpleDict(-128);
        assertEquals("Expected capacity is 16", 16, sd.capacity());
        sd = new SimpleDict(1);
        assertEquals("Expected capacity is 1", 1, sd.capacity());
        sd = new SimpleDict(256);
        assertEquals("Expected capacity is 256", 256, sd.capacity());
        sd = new SimpleDict(1023);
        assertEquals("Expected capacity is 512", 512, sd.capacity());
    }

}
