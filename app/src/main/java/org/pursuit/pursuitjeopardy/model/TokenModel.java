package org.pursuit.pursuitjeopardy.model;

/**
 * This model would be used for requesting a token that will track the questions we have received
 * to prevent the API from sending the same question during a session
 */

//TODO: Decide if this project will get big enough to have to use this feature, currently will not be in uses.
public class TokenModel {
    private int response_code;
    private String response_message;
    private String token;

    public int getResponse_code() {
        return response_code;
    }

    public String getResponse_message() {
        return response_message;
    }

    public String getToken() {
        return token;
    }
}
