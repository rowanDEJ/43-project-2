package com.assistant.aiassistant.query_API;

import java.io.Serializable;

public class QueryResolutionForm<T> implements Serializable {
    private T queryData;

    public QueryResolutionForm(T queryData) {
        this.queryData = queryData;
    }

    public T getQueryData() {
        return queryData;
    }

    public void setQueryData(T queryData) {
        this.queryData = queryData;
    }
}
