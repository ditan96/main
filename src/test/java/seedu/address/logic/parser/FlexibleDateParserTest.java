package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import static seedu.address.testutil.TypicalFlexibleDateInputs.END_MONTH;
import static seedu.address.testutil.TypicalFlexibleDateInputs.END_WEEK;
import static seedu.address.testutil.TypicalFlexibleDateInputs.INVALID_MISSING_DAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.INVALID_MISSING_KEYWORD;
import static seedu.address.testutil.TypicalFlexibleDateInputs.INVALID_MISSING_WEEK_MONTH;
import static seedu.address.testutil.TypicalFlexibleDateInputs.LAST_MONTH_END;
import static seedu.address.testutil.TypicalFlexibleDateInputs.LAST_MONTH_MID;
import static seedu.address.testutil.TypicalFlexibleDateInputs.LAST_MONTH_START;
import static seedu.address.testutil.TypicalFlexibleDateInputs.LAST_WEEK_MONDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.LAST_WEEK_SUNDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.LAST_WEEK_WEDNESDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.MID_MONTH;
import static seedu.address.testutil.TypicalFlexibleDateInputs.MID_WEEK;
import static seedu.address.testutil.TypicalFlexibleDateInputs.NEXT;
import static seedu.address.testutil.TypicalFlexibleDateInputs.NEXT_MONTH_END;
import static seedu.address.testutil.TypicalFlexibleDateInputs.NEXT_MONTH_MID;
import static seedu.address.testutil.TypicalFlexibleDateInputs.NEXT_MONTH_START;
import static seedu.address.testutil.TypicalFlexibleDateInputs.NEXT_WEEK_MONDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.NEXT_WEEK_SUNDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.NEXT_WEEK_WEDNESDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.START_MONTH;
import static seedu.address.testutil.TypicalFlexibleDateInputs.START_WEEK;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_MONTH_END;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_MONTH_MID;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_MONTH_START;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_WEEK_MONDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_WEEK_SUNDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.THIS_WEEK_WEDNESDAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.TODAY;
import static seedu.address.testutil.TypicalFlexibleDateInputs.TOMORROW;
import static seedu.address.testutil.TypicalFlexibleDateInputs.YESTERDAY;

import java.time.format.DateTimeFormatter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.project.Deadline;
import seedu.address.model.util.FlexibleDate;

public class FlexibleDateParserTest {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final FlexibleDateParser parser = new FlexibleDateParser();

    @Test
    public void parseFlexibleDate_today() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(TODAY);

        assertEquals(currentDate.currentDate(), userInput);
    }

    @Test
    public void parseFlexibleDate_tomorrow() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(TOMORROW);

        assertEquals(currentDate.dateNumDaysLater(NEXT), userInput);
    }

    @Test
    public void parseFlexibleDate_yesterday() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(YESTERDAY);

        assertEquals(currentDate.dateNumDaysBefore(NEXT), userInput);
    }

    @Test
    public void parseFlexibleDate_thisWeekStart() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_WEEK_MONDAY);

        assertEquals(currentDate.thisWeekDate(START_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_thisWeekMid() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_WEEK_WEDNESDAY);

        assertEquals(currentDate.thisWeekDate(MID_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_thisWeekEnd() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_WEEK_SUNDAY);

        assertEquals(currentDate.thisWeekDate(END_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_thisMonthStart() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_MONTH_START);

        assertEquals(currentDate.thisMonthDate(START_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_thisMonthMid() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_MONTH_MID);

        assertEquals(currentDate.thisMonthDate(MID_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_thisMonthEnd() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(THIS_MONTH_END);

        assertEquals(currentDate.thisMonthDate(END_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_nextWeekStart() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(NEXT_WEEK_MONDAY);

        assertEquals(currentDate.nextWeekDate(START_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_nextWeekMid() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(NEXT_WEEK_WEDNESDAY);

        assertEquals(currentDate.nextWeekDate(MID_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_nextWeekEnd() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(NEXT_WEEK_SUNDAY);

        assertEquals(currentDate.nextWeekDate(END_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_nextMonthStart() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(NEXT_MONTH_START);

        assertEquals(currentDate.nextMonthDate(START_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_nextMonthMid() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(NEXT_MONTH_MID);

        assertEquals(currentDate.nextMonthDate(MID_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_nextMonthEnd() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(NEXT_MONTH_END);

        assertEquals(currentDate.nextMonthDate(END_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_lastWeekStart() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(LAST_WEEK_MONDAY);

        assertEquals(currentDate.lastWeekDate(START_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_lastWeekMid() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(LAST_WEEK_WEDNESDAY);

        assertEquals(currentDate.lastWeekDate(MID_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_lastWeekEnd() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(LAST_WEEK_SUNDAY);

        assertEquals(currentDate.lastWeekDate(END_WEEK), userInput);
    }

    @Test
    public void parseFlexibleDate_lastMonthStart() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(LAST_MONTH_START);

        assertEquals(currentDate.lastMonthDate(START_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_lastMonthMid() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(LAST_MONTH_MID);

        assertEquals(currentDate.lastMonthDate(MID_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_lastMonthEnd() throws Exception {
        FlexibleDate currentDate = new FlexibleDate();
        String userInput = parser.parseFlexibleDate(LAST_MONTH_END);

        assertEquals(currentDate.lastMonthDate(END_MONTH), userInput);
    }

    @Test
    public void parseFlexibleDate_keywordMissing_failure() throws Exception {

        String expectedMessage = String.format(Deadline.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_MISSING_KEYWORD, expectedMessage);
    }

    @Test
    public void parseFlexibleDate_weekMonthMissing_failure() throws Exception {

        String expectedMessage = String.format(FlexibleDate.FLEXI_DATE_MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_MISSING_WEEK_MONTH, expectedMessage);
    }

    @Test
    public void parseFlexibleDate_targetDayMissing_failure() throws Exception {

        String expectedMessage = String.format(FlexibleDate.DAY_OF_WEEK_MONTH_CONSTRAINTS);
        assertParseFailure(parser, INVALID_MISSING_DAY, expectedMessage);
    }
}