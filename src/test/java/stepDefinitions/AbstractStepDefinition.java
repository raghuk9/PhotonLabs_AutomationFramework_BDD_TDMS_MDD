package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.json.simple.parser.ParseException;

import helpers.PlatformHelper;
import platforms.JPMCPlatform;
import helpers.DataHelper;

public abstract class AbstractStepDefinition {

    private static HashMap<String, String> data;

    private static HashMap<String, String> globalData;

    protected JPMCPlatform platform;

    public AbstractStepDefinition() throws FileNotFoundException, IOException, ParseException {

        data = DataHelper.getCurrentData();

        globalData = DataHelper.getGlobalData();

        platform = PlatformHelper.getCurrentPlatform();

    }

    protected String getDataValue(String key) throws Exception {
        if (data.containsKey(key)) {
            return data.get(key);
        } else if (globalData.containsKey(key)) {
            return globalData.get(key);
        } else {
            throw new DataFormatException("No value found for key: " + key);
        }
    }
}