import org.junit.Test;

import static org.junit.Assert.*;

public class BlackboxMobileDevice {
    /*
     * Constructor
     */
    @Test
    public void testConfig () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        // no exception
        assert(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConfig () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile_invalid.config", gov);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyConfig () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile_empty.config", gov);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullGov () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConfig () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice(null, gov);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyConfigFilename () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("", gov);
    }

    /*
     * recordContact
     */

    @Test
    public void testRecordContact1 () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.recordContact("123", 100, 10);
        assert(true); // no exception
        // TODO how can we figure out the side effects?
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordContactInvalidIndividual () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.recordContact(null, 100, 10);
        // TODO how can we figure out the side effects?
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordContactInvalidIndividual2 () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.recordContact("", 100, 10);
        // TODO how can we figure out the side effects?
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordContactInvalidDate () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.recordContact("123", -1, 10);
        // TODO how can we figure out the side effects?
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordContactInvalidDuration () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.recordContact("123", 100, -1);
        // TODO how can we figure out the side effects?
    }

    /*
     * positiveTest
     */

    @Test
    public void testPositiveTest () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.positiveTest("9999");
        assert(true); // no exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPositiveTestInvalid () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.positiveTest("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPositiveTestInvalid2 () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.positiveTest(null);
    }

    /*
     * Synchronize data
     */
    @Test
    public void testSynchronizeData () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        boolean result = mob.synchronizeData();
        assertTrue(result);
    }

    @Test
    public void testSynchronizeDataBadGov () {
        Government gov = new Government("gov_invalid.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        boolean result = mob.synchronizeData();
        assertFalse(result);
    }

    @Test
    public void testSynchronizeDataNullGov () {
        MobileDevice mob = new MobileDevice("mobile.config", null);
        boolean result = mob.synchronizeData();
        assertFalse(result);
    }

    // TODO test SQL database changes
    @Test
    public void testSynchronizeDataContact () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.recordContact("123", 100, 10);
        boolean result = mob.synchronizeData();

        /* TODO
         * Get contact between this device and the other device at date 100
         * assertTrue(row exists)
         * (of course the mobiledevice does not have access to the db, but we do)
         */
    }

    @Test
    public void testSynchronizeDataTest () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        mob.positiveTest("9999");
        boolean result = mob.synchronizeData();

        /* TODO
         * Get test with this device id and the test hash 9999
         * assertTrue(row exists)
         * (of course the mobiledevice does not have access to the db, but we do)
         */
    }


    @Test
    public void testSynchronizeDataEmptyDb () {
        Government gov = new Government("gov.config");
        MobileDevice mob = new MobileDevice("mobile.config", gov);
        boolean result = mob.synchronizeData();

        /* TODO
         * assertTrue(no rows)
         * (of course the mobiledevice does not have access to the db, but we do)
         */
    }
}