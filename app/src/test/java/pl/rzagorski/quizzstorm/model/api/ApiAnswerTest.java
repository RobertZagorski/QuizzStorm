package pl.rzagorski.quizzstorm.model.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import pl.rzagorski.quizzstorm.model.database.Answer;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApiAnswerTest {
    private ApiAnswer apiAnswer;

    @Before
    public void setUp() {
        apiAnswer = new ApiAnswer();
    }

    @After
    public void tearDown() {
        apiAnswer = null;
    }

    @Test
    public void testApiAnswerOrderTransformation1() throws Exception {
        Long testOrderId = 0L;
        apiAnswer.setOrder(testOrderId);
        Answer answer = apiAnswer.transform();
        assertTrue(testOrderId.equals(answer.getOrder()));
    }

    @Test
    public void testApiAnswerOrderTransformation2() throws Exception {
        Long testOrderId = null;
        apiAnswer.setOrder(testOrderId);
        Answer answer = apiAnswer.transform();
        assertNull(answer.getOrder());
    }

    @Test
    public void testApiAnswerOrderTransformation3() throws Exception {
        Long testOrderId = Long.MAX_VALUE;
        apiAnswer.setOrder(testOrderId);
        Answer answer = apiAnswer.transform();
        assertTrue(testOrderId.equals(answer.getOrder()));
    }

    @Test
    public void testApiAnswerTextTransformation1() throws Exception {
        String testText = "AnyString";
        apiAnswer.setText(testText);
        Answer answer = apiAnswer.transform();
        assertTrue(testText.equals(answer.getText()));
    }

    @Test
    public void testApiAnswerTextTransformation2() throws Exception {
        String testText = null;
        apiAnswer.setText(testText);
        Answer answer = apiAnswer.transform();
        assertNull(answer.getText());
    }
}
