package day13;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class allTests {
    static File testFile = null;
    static File realFile = null;
    Solver solver = null;
    private List<List<String>> input = new ArrayList<List<String>>();

    @BeforeAll
    static void classSetUp() {
        URL path = allTests.class.getResource("test.txt");
        testFile = new File(path.getFile());
        path = allTests.class.getResource("input.txt");
        realFile = new File(path.getFile());
    }

    @Test
    void acceptance() {
        input = Utils.splitAt(Utils.transform(testFile));
        assertEquals(13, new Solver(input).solve1());
        assertEquals(140, new Solver(input).solve2());
        input = Utils.splitAt(Utils.transform(realFile));
        assertEquals(5196, new Solver(input).solve1());
        assertEquals(22134, new Solver(input).solve2());
    }

    @Test
    void simpleLists() {
        assertResult(new String[]{"[1]", "[2]"}, 1);
        assertResult(new String[]{"[2]", "[1]"}, 0);
        assertResult(new String[]{"[1,1]", "[1,2]"}, 1);
        assertResult(new String[]{"[2,1]", "[1,2]"}, 0);
        assertResult(new String[]{"[1]", "[1,2]"}, 1);
        assertResult(new String[]{"[1,0]", "[1]"}, 0);
        assertResult(new String[]{"[]", "[1]"}, 1);
        assertResult(new String[]{"[1]", "[]"}, 0);
    }

    @Test
    void nestedLists() {
        assertResult(new String[]{"[[1]]", "[[2]]"}, 1);
        assertResult(new String[]{"[[2]]", "[[1]]"}, 0);
        assertResult(new String[]{"[[1],[1]]", "[[1],[2]]"}, 1);
        assertResult(new String[]{"[[1],[]]", "[[1],[1]]"}, 1);
        assertResult(new String[]{"[[1],[1]]", "[[1],[]]"}, 0);
    }

    @Test
    void listAndNoneList() {
        assertResult(new String[]{"[[1]]", "[2]"}, 1);
        assertResult(new String[]{"[1]", "[[2]]"}, 1);
    }

    @Test
    void nestedListToString() {
        assertEquals("[[6]]", NestedList.create("[[6]]").toString());
    }

    private void assertResult(String[] strings, int expected) {
        input.clear();
        input.add(Arrays.asList(strings));
        assertEquals(expected, new Solver(input).solve1());
    }


}