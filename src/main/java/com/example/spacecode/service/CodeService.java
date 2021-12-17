package com.example.spacecode.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.spacecode.model.Algorithm;
import com.example.spacecode.model.Classification;
import com.example.spacecode.model.Family;
import com.example.spacecode.model.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface CodeService {

    void postCodeTree(JSONObject json) throws IOException;
    org.json.simple.JSONObject getCodeTree() throws IOException, ParseException;
    void postCodeTreeDetail(JSONObject tree);
    void analyseTree(JSONObject json, List<Classification> classificationList, List<Family> families, List<Algorithm> algorithms);
    void postClassificationContent(JSONObject json);
    JSONObject getClassificationContent(String key);
    JSONObject getFamilyContent(String key);
    void postFamilyContent(JSONObject json);
    JSONObject getAlgorithmContent(String key);
    void postAlgorithmContent(JSONObject json);
    void postImplementationContent(JSONObject json);
    JSONObject getImplementationContent(String key,String language);
    void postBenchmark(JSONObject json);
    JSONArray getUserHistory();
    void setUserHistory(JSONArray json);
    JSONObject getBenchmark(JSONObject json);
    void deleteBenchmark(JSONObject json);
}
