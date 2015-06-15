package com.avol.api;

import lombok.Getter;

/**
 * Created by Durga on 6/15/2015.
 */

@Getter
public class RequestError {

    private String error;
    private RequestError(){}
    private RequestError(Builder builder){
        this.error = builder.error;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String error;

        public RequestError build(){
            return new RequestError(this);
        }

        public Builder withError(String input){
            this.error = input;
            return this;
        }
    }
}
