package com.assistant.aiassistant.query_API;

import com.assistant.aiassistant.AccountManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class ExamplequeryResolutionStrategy implements QueryResolutionStrategy<String, String>{
    public AccountManager accountManager = AccountManager.getInstance();
    public ResourceBundle bundle;

    @Override
    public QueryResolutionResult<String> resolve(QueryResolutionForm<String> form) {
        loadResourceBundle();

        String data = form.getQueryData().toUpperCase();

        return switch (data) {
            case "IK WIL KAAS" ->
                    new QueryResolutionResult<>(bundle.getString("aiIWantCheese"));
            case "I WANT CHEESE" ->
                    new QueryResolutionResult<>(bundle.getString("aiIWantCheese"));
            case "WAT IS 1 PLUS 1" ->
                    new QueryResolutionResult<>(bundle.getString("ai1Plus1"));
            case "WHAT IS 1 PLUS 1" ->
                    new QueryResolutionResult<>(bundle.getString("ai1Plus1"));
            case "WAT IS DE ZIN VAN HET LEVEN" ->
                    new QueryResolutionResult<>(bundle.getString("aiMeaningOfLife"));
            case "WHAT IS THE MEANING OF LIFE" ->
                    new QueryResolutionResult<>(bundle.getString("aiMeaningOfLife"));
            case "IK HEB HULP NODIG" ->
                    new QueryResolutionResult<>(bundle.getString("aiINeedHelp"));
            case "I NEED HELP" ->
                    new QueryResolutionResult<>(bundle.getString("aiINeedHelp"));
            default ->
                    new QueryResolutionResult<>(bundle.getString("aiDefaultP1") + " \"" + data + "\"" + bundle.getString("aiDefaultP2") + " " + data.length());
        };
    }

    public void loadResourceBundle() {
        Locale appLocale = new Locale(accountManager.getActiveUser().getAiLanguage());
        bundle = ResourceBundle.getBundle("MessageBundle", appLocale);
    }


}
