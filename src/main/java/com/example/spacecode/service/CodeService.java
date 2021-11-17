package com.example.spacecode.service;

import com.alibaba.fastjson.JSONObject;
import com.example.spacecode.model.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface CodeService {

    void postCodeTree(JSONObject json) throws IOException;
    org.json.simple.JSONObject getCodeTree() throws IOException, ParseException;
    void postCodeTreeDetail(JSONObject json);
}
