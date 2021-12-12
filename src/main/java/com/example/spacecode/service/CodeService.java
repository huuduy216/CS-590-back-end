package com.example.spacecode.service;

import com.alibaba.fastjson.JSONObject;
import com.example.spacecode.model.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface CodeService {

    void postCodeTree(JSONObject json) throws IOException;
    org.json.simple.JSONObject getCodeTree() throws IOException, ParseException;
    void postCodeTreeDetail(JSONObject tree);
    void postClassificationContent(JSONObject json);
    JSONObject getClassificationContent(String key);
    JSONObject getFamilyContent(String key);
    void postFamilyContent(JSONObject json);
    JSONObject getAlgorithmContent(String key);
    void postAlgorithmContent(JSONObject json);
    void postImplementationContent(JSONObject json);
    JSONObject getImplementationContent(String key,String language);
    void postBenchmark(JSONObject json);
    JSONObject getBenchmark(JSONObject json);
}
