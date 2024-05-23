package com.assistant.aiassistant.query_API;

import java.io.Serializable;

public class QueryResolutionResult<T> implements Serializable {
    private T resultData;

    public QueryResolutionResult(T resultData) {
        this.resultData = resultData;
    }

    public T getData() {
        return resultData;
    }

    public void setData(T resultData) {
        this.resultData = resultData;
    }
}
