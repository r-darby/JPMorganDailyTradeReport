package com.jpmorgan.instructions.processor;

import com.jpmorgan.instructions.enums.BuySell;
import com.jpmorgan.instructions.enums.Currency;
import com.jpmorgan.instructions.pojo.Instruction;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MessageProcessorTest {

    private MessageProcessor messageProcessor = new MessageProcessor();

    /*
    * Remember @Before, @After = run before/after every test
    * @BeforeClass, @AfterClass must be static, run once
    *
    * @Test(expected = IllegalArgumentException.class)
    * */

    @Test
    public void processDataLineTest() {
        // GIVEN
        String dataLine = "foo ,B , 0.50, SGP, 01 Jan 2016, 04 Jan 2016, 200, 100.25";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertEquals("foo", instruction.getEntity());
        assertEquals(BuySell.BUY, instruction.getBuySell());
        assertEquals(0.50, instruction.getAgreedFx(), 5.96e-08);
        assertEquals(Currency.SGP, instruction.getCurrency());
        assertEquals("2016-01-01", instruction.getInstructionDate().toString());
        assertEquals("2016-01-04", instruction.getSettlementDate().toString());
        assertEquals( 200, instruction.getUnits());
        assertEquals(100.25, instruction.getPricePerUnit(), 5.96e-08);
        assertTrue(instruction.isOutgoing());
        assertEquals(BigDecimal.valueOf(10025.0), instruction.getAmount());
    }

    @Test
    public void settlementDateAEDTest() {
        // GIVEN
        String thursdayLine = "foo ,B , 0.50, AED, 03 May 2017, 04 May 2017, 200, 100.25";
        String fridayLine = "foo ,B , 0.50, AED, 03 May 2017, 05 May 2017, 200, 100.25";
        String saturdayLine = "foo ,B , 0.50, AED, 03 May 2017, 06 May 2017, 200, 100.25";
        String sundayLine = "foo ,B , 0.50, AED, 03 May 2017, 07 May 2017, 200, 100.25";

        // WHEN
        Instruction thursdayInstruction = messageProcessor.processDataLine(thursdayLine);
        Instruction fridayInstruction = messageProcessor.processDataLine(fridayLine);
        Instruction saturdayInstruction = messageProcessor.processDataLine(saturdayLine);
        Instruction sundayInstruction = messageProcessor.processDataLine(sundayLine);

        // THEN
        assertEquals("2017-05-04", thursdayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-07", fridayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-07", saturdayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-07", sundayInstruction.getSettlementDate().toString());
    }

    @Test
    public void settlementDateSARTest() {
        // GIVEN
        String thursdayLine = "foo ,B , 0.50, SAR, 03 May 2017, 04 May 2017, 200, 100.25";
        String fridayLine = "foo ,B , 0.50, SAR, 03 May 2017, 05 May 2017, 200, 100.25";
        String saturdayLine = "foo ,B , 0.50, SAR, 03 May 2017, 06 May 2017, 200, 100.25";
        String sundayLine = "foo ,B , 0.50, SAR, 03 May 2017, 07 May 2017, 200, 100.25";

        // WHEN
        Instruction thursdayInstruction = messageProcessor.processDataLine(thursdayLine);
        Instruction fridayInstruction = messageProcessor.processDataLine(fridayLine);
        Instruction saturdayInstruction = messageProcessor.processDataLine(saturdayLine);
        Instruction sundayInstruction = messageProcessor.processDataLine(sundayLine);

        // THEN
        assertEquals("2017-05-04", thursdayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-07", fridayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-07", saturdayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-07", sundayInstruction.getSettlementDate().toString());
    }

    @Test
    public void settlementDateUSDTest() {
        // GIVEN
        String thursdayLine = "foo ,B , 0.50, USD, 03 May 2017, 04 May 2017, 200, 100.25";
        String fridayLine = "foo ,B , 0.50, USD, 03 May 2017, 05 May 2017, 200, 100.25";
        String saturdayLine = "foo ,B , 0.50, USD, 03 May 2017, 06 May 2017, 200, 100.25";
        String sundayLine = "foo ,B , 0.50, USD, 03 May 2017, 07 May 2017, 200, 100.25";
        String mondayLine = "foo ,B , 0.50, USD, 03 May 2017, 08 May 2017, 200, 100.25";

        // WHEN
        Instruction thursdayInstruction = messageProcessor.processDataLine(thursdayLine);
        Instruction fridayInstruction = messageProcessor.processDataLine(fridayLine);
        Instruction saturdayInstruction = messageProcessor.processDataLine(saturdayLine);
        Instruction sundayInstruction = messageProcessor.processDataLine(sundayLine);
        Instruction mondayInstruction = messageProcessor.processDataLine(mondayLine);

        // THEN
        assertEquals("2017-05-04", thursdayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-05", fridayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-08", saturdayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-08", sundayInstruction.getSettlementDate().toString());
        assertEquals("2017-05-08", mondayInstruction.getSettlementDate().toString());
    }

    @Test
    public void missingEntityTest() {
        // GIVEN
        String dataLine = " ,B , 0.50, SGP, 01 Jan 2016, 04 Jan 2016, 200, 100.25";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertNull(instruction);
    }

    @Test
    public void badBuySellTest() {
        // GIVEN
        String dataLine = "foo ,C , 0.50, SGP, 01 Jan 2016, 04 Jan 2016, 200, 100.25";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertNull(instruction);
    }

    @Test
    public void badAgreedFXTest() {
        // GIVEN
        String dataLine = "foo ,B ,xxx, SGP, 01 Jan 2016, 04 Jan 2016, 200, 100.25";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertNull(instruction);
    }

    @Test
    public void badCurrencyTest() {
        // GIVEN
        String dataLine = "foo ,B , 0.50, XXX, 01 Jan 2016, 04 Jan 2016, 200, 100.25";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertNull(instruction);
    }

    @Test
    public void badInstructionDateTest() {
        // GIVEN
        String dataLine = "foo ,B , 0.50, SGP, 32 Jan 2016, 04 Jan 2016, 200, 100.25";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertNull(instruction);
    }

    @Test
    public void badSettlementDateTest() {
        // GIVEN
        String dataLine = "foo ,B , 0.50, SGP, 01 Jan 2016, 04 xxx 2016, 200, 100.25";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertNull(instruction);
    }

    @Test
    public void badUnitsTest() {
        // GIVEN
        String dataLine = "foo ,B , 0.50, SGP, 01 Jan 2016, 04 Jan 2016, xxx, 100.25";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertNull(instruction);
    }

    @Test
    public void badPricePerUnitTest() {
        // GIVEN
        String dataLine = "foo ,B , 0.50, SGP, 01 Jan 2016, 04 Jan 2016, 200, xxx";

        // WHEN
        Instruction instruction = messageProcessor.processDataLine(dataLine);

        // THEN
        assertNull(instruction);
    }
}
