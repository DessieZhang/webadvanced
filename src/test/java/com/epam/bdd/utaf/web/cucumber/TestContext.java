package com.epam.bdd.utaf.web.cucumber;

import com.epam.bdd.utaf.web.managers.*;

public class TestContext {
//    private PageObjectManager pageObjectManager;
    public ScenarioContext scenarioContext;

    public TestContext(){
//        pageObjectManager = new PageObjectManager();
        scenarioContext = new ScenarioContext();
    }

//    public PageObjectManager getPageObjectManager() {
//        return pageObjectManager;
//    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}