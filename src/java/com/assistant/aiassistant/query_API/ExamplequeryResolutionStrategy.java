package com.assistant.aiassistant.query_API;

public class ExamplequeryResolutionStrategy implements QueryResolutionStrategy<String, String>{
    @Override
    public QueryResolutionResult<String> resolve(QueryResolutionForm<String> form) {
        String data = form.getQueryData().toUpperCase();

        return switch (data) {
            case "IK WIL KAAS" ->
                    new QueryResolutionResult<>("Ik wil ook kaas, mijn beste vriend.");
            case "WAT IS 1 PLUS 1" ->
                    new QueryResolutionResult<>("Waarom vraag je dat aan mij, dat kan je zelf best berekenen. Domkop. Ben je nog nooit naar school geweest ofzo?!");
            case "WAT IS DE ZIN VAN HET LEVEN" ->
                    new QueryResolutionResult<>("De zin van het leven is kaas eten.");
            case "IK HEB HULP NODIG" ->
                    new QueryResolutionResult<>("Skill issue");
            default ->
                    new QueryResolutionResult<>("Je query was \"" + data + "\", lengte: " + data.length());
        };
    }
}
