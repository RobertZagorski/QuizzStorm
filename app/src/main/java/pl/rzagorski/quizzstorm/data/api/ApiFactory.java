package pl.rzagorski.quizzstorm.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.rzagorski.quizzstorm.BuildConfig;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Robert Zagórski on 2016-07-21.
 */
public class ApiFactory {

    public static ApiRequests makeApiManager(CallAdapter.Factory callFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiRequests.BASE_URL)
                .client(provideApiClient())
                .addConverterFactory(provideApiConverter())
                .addCallAdapterFactory(callFactory)
                .build();

        return retrofit.create(ApiRequests.class);
    }

    private static OkHttpClient provideApiClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG_BUILD ?
                HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        return okHttpClient;
    }

    private static Converter.Factory provideApiConverter() {
        Gson gson = new GsonBuilder()
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .setDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS")
                .create();
        return GsonConverterFactory.create(gson);
    }
}
