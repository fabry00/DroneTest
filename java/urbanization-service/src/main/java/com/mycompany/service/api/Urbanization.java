package com.mycompany.service.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Urbanization {
    private long id;

    private String content;

    private Urbanization() {
        // Jackson deserialization
    }

    private Urbanization(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
    
    public static class Builder {
        
        private long id;
        private String content;
        
        public Builder withId(long id) {
            this.id = id;
            return this;
        }
        
        public Builder withContent(String content){
            this.content = content;
            return this;
        }
        
        public Urbanization build() {
            return new Urbanization(this.id, this.content);
        }
    }
}
