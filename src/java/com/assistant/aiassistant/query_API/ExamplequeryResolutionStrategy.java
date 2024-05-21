package com.assistant.aiassistant.query_API;

public class ExamplequeryResolutionStrategy implements QueryResolutionStrategy<String, String>{
    @Override
    public QueryResolutionResult<String> resolve(QueryResolutionForm<String> form) {
        String data = form.getQueryData();

        if(data.equalsIgnoreCase("ik wil kaas")) {
            return new QueryResolutionResult<String>("Ik wil ook kaas, mijn beste vriend.");
        }

        return new QueryResolutionResult<String>("Je query was \"" + data + "\", lengte: " + data.length());
    }
}
