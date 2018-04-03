package me.dzeparac.dzeparac.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class DzeparacMockInterceptor implements Interceptor {
    private static final String TAG = "DzeparacMock";

    @Override
    public Response intercept(Chain chain) throws IOException {
        String path = chain.request().url().uri().getPath();
        Log.d(TAG, path);

        if( path.equals("/login") )
            return this.mockLoginRequest(chain.request());
        if( path.equals("/user/balance") )
            return this.mockGetBalanceRequest(chain.request());
        if( path.equals("/user/transactions") )
            return this.mockTransactionsRequest(chain.request());
        if( path.equals("/user/wishes") )
            return this.mockWishesRequest(chain.request());

        return chain.proceed(chain.request());
    }

    private Response mockLoginRequest(Request request) {
        return this.createJsonResponseBody(request, 200, "{\"message\": \"ok\"}");
    }

    private Response mockGetBalanceRequest(Request request) {
        return this.createJsonResponseBody(request, 200, "{\"amount\": 42.25}");
    }

    private Response mockTransactionsRequest(Request request) {
        String data = "[{\"Id\":1,\"Name\":\"Transakcija 1\",\"CreatedAt\":\"2017-01-01T15:00:00\",\"Amount\":5,\"Category\":{\"Id\":1,\"Icon\":\"https://image.ibb.co/jfzFv6/toffee.png\",\"Name\":\"Slatkiši\"}},{\"Id\":2,\"Name\":\"Transakcija 2\",\"CreatedAt\":\"2017-02-01T15:00:00\",\"Amount\":15,\"Category\":{\"Id\":1,\"Icon\":\"https://image.ibb.co/jfzFv6/toffee.png\",\"Name\":\"Slatkiši\"}},{\"Id\":3,\"Name\":\"Transakcija 3\",\"CreatedAt\":\"2017-01-01T15:00:00\",\"Amount\":-15,\"Category\":{\"Id\":-1,\"Name\":\"Dobit\",\"Icon\":\"https://image.ibb.co/jfzFv6/toffee.png\"}}]";
        return this.createJsonResponseBody(request, 200, data);
    }

    private Response mockWishesRequest(Request request) {
        String data = "[{\"Id\":1,\"Name\":\"Zelja 1\",\"Amount\":5,\"Category\":{\"Id\":1,\"Icon\":\"https://image.ibb.co/jfzFv6/toffee.png\",\"Name\":\"Slatkiši\"}},{\"Id\":2,\"Name\":\"Zelja 2\",\"Amount\":15,\"Category\":{\"Id\":1,\"Icon\":\"https://image.ibb.co/jfzFv6/toffee.png\",\"Name\":\"Slatkiši\"}}]";
        return this.createJsonResponseBody(request, 200, data);
    }

    private Response createJsonResponseBody(Request request, int statusCode, String data) {
        return new Response.Builder()
                    .code(statusCode)
                    .message(data)
                    .protocol(Protocol.HTTP_1_1)
                    .request(request)
                    .addHeader("Content-Type", "application/json")
                    .body(ResponseBody.create(MediaType.parse("application/json"), data))
                    .build();
    }
}
