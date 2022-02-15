package com.example.demospringquartz;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JsonPathUnitTest {
    private static String json;
    private static File jsonFile = new File("src/main/resources/json/online_store.json");

    private static String readFile(File file, Charset charset) throws IOException {
        return new String(Files.readAllBytes(file.toPath()), charset);
    }

    @BeforeEach
    public void init() throws IOException {
        json = readFile(jsonFile, StandardCharsets.UTF_8);
    }

    @Test
    public void shouldMatchCountOfObjects() {
        Map<String, String> objectMap = JsonPath.read(json, "$");
        assertEquals(3, objectMap.keySet()
                .size());
    }

    @Test
    public void shouldMatchCountOfArrays() {
        String test = "a";
        switch (test) {
            case "a":
                System.out.println("abc");
                break;
            case "b":
                System.out.println("deg");
                break;
            default:
                System.out.println("warning");
        }
//        String jsonArray5 = JsonPath.read(json, "$.PDS_0105[1]").toString();
        String jsonArray3 = JsonPath.read(json, "$.items.book[*].a.PDS_0005[1]").toString();
//        String jsonArray4 = JsonPath.read(json, "$.items.book[*].a.PDS_0005[1]");
//        String jsonArray2 = JsonPath.read(json, "$.items.book.[*].a[*]");
        String valueAuthor = getStringValueByKeyWithRegExr(jsonArray3, generateRegExrByKey("author"));
        Object jsonArray0 = JsonPath.read(json, "$.items.book[0]");
        Map<String, String> jsonArray1 = JsonPath.read(json, "$.items.book[1]");
        JSONArray jsonArray = JsonPath.read(json, "$.items.book[*]");

        assertEquals(2, jsonArray.size());
    }

    public String getStringValueByKeyWithRegExr(String jsonString, String regExr){
        String value = "";

        Pattern pattern = Pattern.compile(regExr);
        Matcher matcher = pattern.matcher(jsonString);
        while (matcher.find()) {
            value = matcher.group(1);
            break;
        }

        return value;
    }

    public String getStringValueByKeyWithRegExr(String jsonString, String regExr2, int inputIndex){
        String value = "";

        Pattern pattern = Pattern.compile(regExr2);
        Matcher matcher = pattern.matcher(jsonString);
        int index = 1;
        while (matcher.find()) {
            if(inputIndex == index) {
                value = matcher.group(1);
                break;
            }
            index++;
        }

        return value;
    }

    private String generateRegExrByKey1(String key){
        String regexTemplate = "\"%s\".*},";
        return String.format(regexTemplate, key);
    }

    private String generateRegExrByKey(String key){
        String regexTemplate = "\"%s\"\\s*:\\s*\"(.+?)\"";
        return String.format(regexTemplate, key);
    }
}
