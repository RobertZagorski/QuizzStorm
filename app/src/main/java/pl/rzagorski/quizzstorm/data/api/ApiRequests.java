package pl.rzagorski.quizzstorm.data.api;

import pl.rzagorski.quizzstorm.BuildConfig;
import pl.rzagorski.quizzstorm.model.api.ApiQuizExtendedModel;
import pl.rzagorski.quizzstorm.model.api.response.GetQuizListResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * An interface containing methods acceptable by Retrofit2
 *
 * Created by Robert Zagórski on 2016-07-21.
 */
public interface ApiRequests {
    public static final String BASE_URL = BuildConfig.API_ENDPOINT;

    @GET(BuildConfig.API_PATH_QUIZ_LIST)
    public Observable<GetQuizListResponse> getQizList(@Path("startIndex") Long startIndex,
                                                      @Path("endIndex") Long endIndex);

    @GET(BuildConfig.API_PATH_QUIZ_DETAILS)
    public Observable<ApiQuizExtendedModel> getQuizDetails(@Path("quizId") Long quizId);
}
