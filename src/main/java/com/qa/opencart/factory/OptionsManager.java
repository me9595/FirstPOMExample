package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties prop;
    private ChromeOptions co;
    private EdgeOptions eo;


    public OptionsManager(Properties prop){
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions(){
        co = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            co.addArguments("--incognito");
        }
        return co;
    }

    public EdgeOptions getEdgeOptions(){
        eo = new EdgeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            eo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            eo.addArguments("--incognito");
        }
        return eo;
    }
}
