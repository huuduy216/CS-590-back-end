package com.example.spacecode.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONReader;
import com.example.spacecode.dao.ClassificationDao;
import com.example.spacecode.dao.ImplementationDao;
import com.example.spacecode.model.Classification;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.io.IOException;


@Service
public class CodeServiceImpl implements CodeService {

    boolean trigger = true;

    //local
    final String testFile = "src\\main\\java\\com\\example\\spacecode\\asset\\test.json";
    final String treeFile = "src\\main\\java\\com\\example\\spacecode\\asset\\tree.json";

    //aws
//    final String testFile = "./test.json";
//    final String treeFile = "./tree.json";
    @Autowired
    ImplementationDao implementationDao;

    @Autowired
    ClassificationDao classificationDao;

    public void postCodeTree(JSONObject json) throws IOException {
        FileWriter file = new FileWriter(testFile, false);
        file.write(json.toJSONString());
        file.flush();
    }

    public org.json.simple.JSONObject getCodeTree() throws IOException, ParseException {
        FileReader fileReader = new FileReader(testFile);
        if (trigger) {
            trigger = false;
            fileReader = new FileReader(treeFile);
        }
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(fileReader);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
        return jsonObject;
    }

    public void postCodeTreeDetail(JSONObject json) {
        List<Classification> classificationList = classificationDao.getAll();
        analyseTree(json, classificationList);
        for (Classification classification : classificationList) {
            Integer classId = classification.getIdClassification();
            classificationDao.removeByIdClassification(classId);
        }
    }

    public void analyseTree(JSONObject json, List<Classification> classificationList) {
        JSONArray children = json.getJSONArray("children");
        for (int i = 0; i < children.size(); i++) {
            JSONObject newchildren = children.getJSONObject(i);
            if ((newchildren.get("type").toString()).equals("classification")) {
                String name = newchildren.get("title").toString();
                String key = newchildren.get("key").toString();
                Classification classification = classificationDao.findByIdentitykey(key);
                if (classification == null) {
                    classificationDao.save(name, key, "", "");
                } else {
                    String subtitle = classification.getSubtitle();
                    String textbody = classification.getDesription();
                    classificationList.remove(classification);
                    classificationDao.removeByIdClassification(classification.getIdClassification());
                    classificationDao.save(name, key, textbody, subtitle);
                }
            }
            analyseTree(newchildren, classificationList);
        }
    }

    public JSONObject getClassificationContent(String key){
        Classification classification = classificationDao.findByIdentitykey(key);
        if(classification!=null){
            JSONObject json = new JSONObject();
            json.put("subtitle",classification.getSubtitle());
            json.put("textbody",classification.getDesription());
            json.put("type","classification");
            json.put("title",classification.getName());
            return json;
        }
        return null;
    }

    public void postClassificationContent(JSONObject json) {
        String key = json.get("key").toString();
        String subtitle = json.get("subtitle").toString();
        String textbody = json.get("textbody").toString();
        Classification classification = classificationDao.findByIdentitykey(key);
        if(classification!=null){
            classificationDao.updateByClassificationContent(key,textbody,subtitle);
        }
    }
}
