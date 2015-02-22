package com.jeapie;

import com.jeapie.util.Base64Coder;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Sends events in HTTP request to Jeapie server
 */
class ServerMessage {

    public static final int RETRIES_COUNT = 3;

    public static Result postData(String endpointUrl, String key, String secret, String rawMessage) {
        Status status = Status.FAILED_UNRECOVERABLE;
        final String encodedData = Base64Coder.encodeString(rawMessage);

        final Result baseResult = performRequest(endpointUrl, key, secret, encodedData);
        final Status baseStatus = baseResult.getStatus();
        String response = baseResult.getResponse();
        if (baseStatus == Status.SUCCEEDED) {
            status = Status.SUCCEEDED;
        }

        return new Result(status, response);
    }

    /**
     * Considers *any* response a SUCCESS, callers should check Result.getResponse() for errors
     * and craziness.
     * <p/>
     * Will POST if nameValuePairs is not null.
     */
    private static Result performRequest(String endpointUrl, String key, String secret, String message) {
        Status status = Status.FAILED_UNRECOVERABLE;
        String response = null;
        try {
            // the while(retries) loop is a workaround for a bug in some Android HttpURLConnection
            // libraries- The underlying library will attempt to reuse stale connections,
            // meaning the second (or every other) attempt to connect fails with an EOFException.
            // Apparently this nasty retry logic is the current state of the workaround art.
            int retries = 0;
            boolean succeeded = false;
            while (retries < RETRIES_COUNT && !succeeded) {
                HttpURLConnection connection = null;

                try {
                    final URL url = new URL(String.format(endpointUrl, message));

                    String authString = key + ":" + secret;
                    String authEncBytes = Base64Coder.encodeString(authString);
                    String authStringEnc = authEncBytes;
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
                    connection.setConnectTimeout(10000);
                    connection.connect();
                    int code = connection.getResponseCode();
                    succeeded = true;
                    if (code == 200) {
                        status = Status.SUCCEEDED;
                    }
                } catch (final EOFException e) {
                    retries = retries + 1;
                } finally {
                    if (null != connection)
                        connection.disconnect();
                }
            }// while
        } catch (final MalformedURLException e) {
            String errorMessage = "Cannot iterpret " + endpointUrl + " as a URL";
            status = Status.FAILED_UNRECOVERABLE;
            response = errorMessage;
        } catch (final FileNotFoundException e) {
            String errorMessage = "Wrong key or secret";
            status = Status.FAILED_UNRECOVERABLE;
            response = errorMessage;
        } catch (final IOException e) {
            status = Status.FAILED_RECOVERABLE;
            response = "Network error: " + e.getLocalizedMessage();
        } catch (final OutOfMemoryError e) {
            status = Status.FAILED_UNRECOVERABLE;
            response = e.getLocalizedMessage();
        }

        return new Result(status, response);
    }

    public static enum Status {
        // The post was sent and understood by the service.
        SUCCEEDED,

        // The post couldn't be sent (for example, because there was no connectivity)
        // but might work later.
        FAILED_RECOVERABLE,

        // The post itself is bad/unsendable (for example, too big for system memory)
        // and shouldn't be retried.
        FAILED_UNRECOVERABLE
    }

    public static class Result {
        private final String mResponse;
        private final Status mStatus;

        Result(Status status, String response) {
            mStatus = status;
            mResponse = response;
        }

        public Status getStatus() {
            return mStatus;
        }

        public String getResponse() {
            return mResponse;
        }
    }

}
