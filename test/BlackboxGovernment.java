import org.junit.Test;
import static org.junit.Assert.*;

public class BlackboxGovernment {
    @Test
    public void config() {
        Government gov = new Government("gov.config");
        // Assert that we did not have an exception
        assertTrue(true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void configEmpty() {
        Government gov = new Government("gov_empty.config");
    }

    @Test (expected = IllegalArgumentException.class)
    public void configInvalid() {
        Government gov = new Government("gov_invalid.config");
    }

    @Test (expected = IllegalArgumentException.class)
    public void configEmptyFilename() {
        Government gov = new Government("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void configNullFilename() {
        Government gov = new Government(null);
    }

    /*
     * mobileContact
     */
    @Test
    public void testMobileContactNull() {
        Government gov = new Government("gov.config");
        boolean result = gov.mobileContact(null, "");
        assertFalse(result);
    }

    @Test
    public void testMobileInfoNull() {
        Government gov = new Government("gov.config");
        boolean result = gov.mobileContact("123", null);
        assertFalse(result);
    }

    @Test
    public void testMobileContactNoLab() {
        Government gov = new Government("gov.config");
        boolean result = gov.mobileContact("123", null);
        assertFalse(result);
    }

    static String testInfoPositive = "<test>" +
            "<device>1002</device>" +
            "<hash>9999</hash>" +
            "<result>positive</result>" +
            "</test>";
    static String contactInfo = "<contact>" +
            "<device>1002</device>" +
            "<date>123</date>" +
            "<duration>1</duration>" +
            "</contact>";
    static String contactInfoOtherPerson = "<contact>" +
            "<device>3001</device>" +
            "<date>123</date>" +
            "<duration>1</duration>" +
            "</contact>";
    static String contactInfoEmpty = "";

    @Test
    // 1002 is positive, 1001 had contact with 1002
    public void testMobileContactWithLab() {
        Government gov = new Government("gov.config");
        gov.recordTestResult("9999", 123, true);
        gov.mobileTest("123", testInfoPositive);
        boolean result = gov.mobileContact("123", contactInfo);
        assertTrue(result);
    }

    @Test
    // 1002 is positive, 1001 had contact with 1002
    public void testMobileContactWithLabLongAgo() {
        Government gov = new Government("gov.config");
        gov.recordTestResult("9999", 108, true);
        gov.mobileTest("f0f0f0", testInfoPositive);
        boolean result = gov.mobileContact("f0f0f0", contactInfo);
        assertFalse(result);
    }

    @Test
    // 1002 is positive, 1001 had contact with 1002
    public void testMobileContactWithLabNoContact() {
        Government gov = new Government("gov.config");
        gov.mobileTest("123", testInfoPositive);
        boolean result = gov.mobileContact("123", contactInfoEmpty);
        assertFalse(result);
    }

    @Test
    // 1002 is positive, 1001 had contact with 1002
    public void testMobileContactWithLabOtherPerson() {
        Government gov = new Government("gov.config");
        gov.recordTestResult("9999", 123, true);
        gov.mobileTest("123", testInfoPositive);
        boolean result = gov.mobileContact("123", contactInfoOtherPerson);
        assertFalse(result);
    }

    @Test
    public void testMobileContactEmptyData() {
        Government gov = new Government("gov.config");
        gov.recordTestResult("9999", 123, true);
        gov.mobileTest("123", testInfoPositive);
        boolean result = gov.mobileContact("123", "");
        assertFalse(result);
    }

    // Test that mobileContact actually updates the database
    @Test
    public void testMobileContactDatabase() {
        Government gov = new Government("gov.config");
        gov.mobileContact("123", contactInfo);

        // TODO assertTrue(contact result exists in database)
    }

    // Test that mobileContact works with old data
    @Test
    public void testMobileContactDatabase() {
        // TODO put contact data and positive test result into database

        Government gov = new Government("gov.config");
        boolean result = gov.mobileContact("123", contactInfo);

        assertTrue(result);
    }

    /*
     * recordTestResult
     */

    // Test that recordTestResult actually updates the database
    @Test
    public void testLabTestDatabase() {
        Government gov = new Government("gov.config");
        gov.recordTestResult("9999", 123, true);

        // TODO assertTrue(test result exists in database)
    }

    @Test
    public void testMobileTestDatabase() {
        Government gov = new Government("gov.config");
        gov.mobileTest("9999", testInfoPositive);

        // TODO assertTrue(test result exists in database and is linked with a specific device id)
    }

    @Test
    public void testMobileTestDatabaseNegative() {
        Government gov = new Government("gov.config");
        gov.mobileTest("9999", testInfoNegative);

        // TODO assert(negative test result exists)
    }

    @Test
    public void testMobileTestInvalid() {
        Government gov = new Government("gov.config");
        gov.mobileTest("9999", "");

        // TODO assert(no row exists for this device)
    }

    /*
     * findGatherings
     */

    // These two strings denote a gathering of 4 people. One pair (1001 and 1002)
    // met each other and both met two other people (1003 and 1004).
    // The gathering size should be 4.
    static String contactInfoThreeA = "<contact>" +
            "<device>1002</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>" +
            "<contact>" +
            "<device>1003</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>" +
            "<contact>" +
            "<device>1004</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>";
    static String contactInfoThreeB = "<contact>" +
            "<device>1001</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>" +
            "<contact>" +
            "<device>1003</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>" +
            "<contact>" +
            "<device>1004</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>";

    static String contactInfoThreeC = "<contact>" +
            "<device>2001</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>" +
            "<contact>" +
            "<device>2002</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>" +
            "<contact>" +
            "<device>2003</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>";

    static String contactInfoThreeD = "<contact>" +
            "<device>1001</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>" +
            "<contact>" +
            "<device>2002</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>" +
            "<contact>" +
            "<device>2003</device>" +
            "<date>100</date>" +
            "<duration>10</date>" +
            "</contact>";

    @Test
    public void testFindGatheringsOne() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(100, 4, 10, 0.8f);
        assertEquals(num, 1);
    }

    @Test
    public void testFindGatheringsMany() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeC);
        gov.mobileContact("1002",  contactInfoThreeD);
        gov.mobileContact("2001",  contactInfoThreeD);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(100, 4, 10, 0.8f);
        assertEquals(num, 2);
    }

    @Test
    public void testFindGatheringTooSmall() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(100, 10, 10, -1);
        assertEquals(num, 0);
    }

    @Test
    public void testFindGatheringEdgeSize() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(100, 0, 10, 0.8f);
        assertEquals(num, 0);
    }

    @Test
    public void testFindGatheringWrongDay() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(101, 4, 10, 0.8f);
        assertEquals(num, 0);
    }

    @Test
    public void testFindGatheringIncorrectDay() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(-1, 10, 10, 0.8f);
        assertEquals(num, 0);
    }

    @Test
    public void testFindGatheringTooBrief() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(100, 4, 11, 0.8f);
        assertEquals(num, 0);
    }

    @Test
    public void testFindGatheringIncorrectTime() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(100, 10, -1, 0.8f);
        assertEquals(num, 0);
    }

    @Test
    public void testFindGatheringLowDensity() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(100, 4, 10, 0.99f);
        assertEquals(num, 0);
    }

    @Test
    public void testFindGatheringIncorrectDensity() {
        Government gov = new Government("gov.config");
        gov.mobileContact("1001", contactInfoThreeA);
        gov.mobileContact("1002",  contactInfoThreeB);
        // The calculated density should be exactly 0.833...
        int num = gov.findGatherings(100, 10, -1, 0.8f);
        assertEquals(num, 0);
    }
    
    @Test
    public void testFindGatheringDatabase() {
        Government gov = new Government("gov.config");
        // TODO create correct SQL database entries for a gathering
        int num = gov.findGatherings(100, 10, 10, 0.5f);
        assertEquals(num, 2);
    }
}