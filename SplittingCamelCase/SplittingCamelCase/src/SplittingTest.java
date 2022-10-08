import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SplittingTest {

   

    @Test
    public void testCase1() throws Exception {
        List<String> expectedValue = Collections.singletonList("name");
        assertEquals(SplittingMain.splittingCamelCaseStrings("name"), expectedValue);
    }

    @Test
    public void testCase2() throws Exception {
        List<String> expectedValue = Collections.singletonList("name");
        assertEquals(SplittingMain.splittingCamelCaseStrings("Name"), expectedValue);
    }

    @Test
    public void testCase3() throws Exception {
        List<String> expectedValue = Arrays.asList("composed","name");
        assertEquals(SplittingMain.splittingCamelCaseStrings("composedName"), expectedValue);

    }

    @Test
    public void testCase4() throws Exception {
        List<String> expectedValue = Arrays.asList("composed","name");
        assertEquals(SplittingMain.splittingCamelCaseStrings("ComposedName"), expectedValue);
    }

    @Test
    public void testCase5() throws Exception {
        List<String> expectedValue = Arrays.asList("ORCHID");
        assertEquals(SplittingMain.splittingCamelCaseStrings("ORCHID"), expectedValue);
    }

    @Test
    public void testCase6() throws Exception {
        List<String> expectedValue = Arrays.asList("number", "ORCHID");
        assertEquals(SplittingMain.splittingCamelCaseStrings("numberORCHID"), expectedValue);
    }


    @Test
    public void testCase7() throws Exception {
        List<String> expectedValue = Arrays.asList("number", "ORCHID", "researcher");
        assertEquals(SplittingMain.splittingCamelCaseStrings("numberORCHIDResearcher"), expectedValue);
    }

    @Test
    public void testCase8() throws Exception {
        List<String> expectedValue = Arrays.asList("recover", "10", "first");
        assertEquals(SplittingMain.splittingCamelCaseStrings("recover10First"), expectedValue);
    }

    @Test
    public void testCase9(){
        try {
            SplittingMain.splittingCamelCaseStrings("10First");

            fail();

        } catch (Exception e){

            assertEquals("Oops,Can't start with the number!!", e.getMessage());
        }
    }

    @Test
    public void testCase10(){
        try {
            SplittingMain.splittingCamelCaseStrings("name#Composed");

        } catch (Exception e){

            assertEquals("Special characters are not allowed,only letters and numbers", e.getMessage());
        }
    }


}
