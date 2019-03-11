package droidguard;

import droidguard.antiabuse.request.AntiabuseRequest;
import droidguard.antiabuse.request.AntiabuseRequestData;
import droidguard.antiabuse.request.HttpClientFactory;
import droidguard.antiabuse.request.ProtocolBufferEntity;
import droidguard.antiabuse.response.AntiabuseResponse;
import io.reactivex.Observable;
import io.reactivex.internal.observers.BlockingBaseObserver;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Example {
    private final static String ANTIABUSE_URL = "https://www.googleapis.com/androidantiabuse/v1/x/create?alt=PROTO&key=AIzaSyBofcZsgLSS7BOnBjZPEkk4rYwzOIz-lTI";
    private final static Logger logger = LoggerFactory.getLogger(Example.class);
    private final static String COMMAND_AUTH = "auth";

    public static void main(String... args) {
        new Example().execute();
    }

    private void execute() {
        makeAntiabuseRequest()
                .flatMap(this::decodeAntiabuseResponse)                
                .subscribe(new BlockingBaseObserver<String>() {
            @Override
            public void onNext(AntiabuseResponse response) {
                logger.info("Request succeed");
            }

            @Override
            public void onError(Throwable e) {
                logger.error("Error occurred", e);
            }
        });
    }    

    Observable<AntiabuseResponse> decodeAntiabuseResponse(byte[] responseData) throws Exception {
        logger.info("Decoding antiabuse response");
        AntiabuseResponse response = AntiabuseResponse.parseResponse(responseData);
        return Observable.just(response);
    }

    Observable<byte[]> makeAntiabuseRequest() {
        try {
            AntiabuseRequestData antiabuseRequestData = AntiabuseRequest.createProtobufRequestData(COMMAND_AUTH);
            logger.info("Sending request to antiabuse service");
            HttpClient httpclient = HttpClientFactory.newHttpClient(droidguard.antiabuse.request.Build.DEVICE, droidguard.antiabuse.request.Build.ID);
            HttpPost post = new HttpPost(ANTIABUSE_URL);
            post.setEntity(new ProtocolBufferEntity(antiabuseRequestData));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpclient.execute(post);
            logger.info("Response status: " + response.getStatusLine());
            byte[] bytes = EntityUtils.toByteArray(response.getEntity());
            return Observable.just(bytes);
        } catch (Exception ex) {
            return Observable.error(ex);
        }
    }
}
