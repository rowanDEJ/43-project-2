package com.assistant.aiassistant.query_API;
import java.io.Serializable;

public interface QueryResolutionStrategy<T, R> extends Serializable{
    QueryResolutionResult<R> resolve(QueryResolutionForm<T> form);
}
