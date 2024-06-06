package com.assistant.aiassistant.query_API;

public class ExamplequeryResolutionStrategy implements QueryResolutionStrategy<String, String>{
    @Override
    public QueryResolutionResult<String> resolve(QueryResolutionForm<String> form) {
        String data = form.getQueryData();

        if(data.equalsIgnoreCase("ik wil kaas")) {
            return new QueryResolutionResult<>("Ik wil ook kaas, mijn beste vriend.");
        }

        return new QueryResolutionResult<>("Je query was \"" + data + "\", lengte: " + data.length());
    }
}
