package com.techyourchance.unittesting.questions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;

import com.techyourchance.unittesting.networking.StackoverflowApi;
import com.techyourchance.unittesting.networking.questions.FetchQuestionDetailsEndpoint;
import com.techyourchance.unittesting.networking.questions.QuestionSchema;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.regex.Matcher;

@RunWith(MockitoJUnitRunner.class)
public class FetchQuestionDetailsUseCaseTest {

    // region constants ----------------------------------------------------------------------------
    public static final String QUESTION_ID = "questionId";
    public static final String BODY = "body";
    public static final String QUESTION_TILE = "QuestionTile";
    // end-region constants ------------------------------------------------------------------------


    // region helper fields ------------------------------------------------------------------------
//    private EndPointTD endPointTD;
    @Mock
    private FetchQuestionDetailsEndpoint fetchQuestionDetailsEndpoint;
    @Mock
    private FetchQuestionDetailsUseCase.Listener mockListener1;
    @Mock
    private FetchQuestionDetailsUseCase.Listener mockListener2;
    // end-region helper fields --------------------------------------------------------------------

    FetchQuestionDetailsUseCase SUT;

    @Before
    public void setup() throws Exception {
//        endPointTD = new EndPointTD();
        SUT = new FetchQuestionDetailsUseCase(fetchQuestionDetailsEndpoint);
//        SUT = new FetchQuestionDetailsUseCase(endPointTD);
    }

    //  success-> listener notified

    @Test
    public void fetchQuestionDetailsAndNotify_success_Success_notifiyListeners() {
        ArgumentCaptor<QuestionDetails> ac = ArgumentCaptor.forClass(QuestionDetails.class);
        //ARRANGE
        //ACT
        SUT.fetchQuestionDetailsAndNotify(QUESTION_ID);
        //ASSERT
        verify(fetchQuestionDetailsEndpoint).fetchQuestionDetails(QUESTION_ID, any(FetchQuestionDetailsEndpoint.Listener.class));
//        List<QuestionDetails> allValues = ac.getAllValues();

//        MatcherAssert.assertThat(allValues.get(0), isA(QuestionDetails.class));
    }

    //  listener failure

    // region helper methods -----------------------------------------------------------------------
    // end-region helper methods -------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    private static class EndPointTD extends FetchQuestionDetailsEndpoint {

        public boolean failure;
        public EndPointTD() {
            super(null);
        }

        @Override
        public void fetchQuestionDetails(String questionId, Listener listener) {
            if(failure){

            } else {
                listener.onQuestionDetailsFetched(new QuestionSchema(QUESTION_TILE,QUESTION_ID, BODY));
            }
        }
    }
    // end-region helper classes -------------------------------------------------------------------
}