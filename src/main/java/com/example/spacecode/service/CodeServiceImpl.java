package com.example.spacecode.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.spacecode.dao.AlgorithmDao;
import com.example.spacecode.dao.ClassificationDao;
import com.example.spacecode.dao.FamilyDao;
import com.example.spacecode.dao.ImplementationDao;
import com.example.spacecode.model.Algorithm;
import com.example.spacecode.model.Classification;
import com.example.spacecode.model.Family;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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

    @Autowired
    FamilyDao familyDao;

    @Autowired
    AlgorithmDao algorithmDao;

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
        // json.DB: file
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(fileReader);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
        org.json.simple.JSONObject jsonObjectFinal = new org.json.simple.JSONObject();
        jsonObjectFinal.put("tree", jsonObject);
        //json.database:id:key
        List<Classification> classificationList = classificationDao.getAll();
        List<Family> families = familyDao.getAll();
        List<Algorithm> algorithms = algorithmDao.getAll();
        JSONObject jsonObjectDB = new JSONObject();
        for (Classification classification : classificationList) {
            Integer id = classification.getIdClassification();
            jsonObjectDB.put(classification.getIdentitykey(), id.toString());
        }
        for (Family family : families) {
            Integer id = family.getIdFamily();
            jsonObjectDB.put(family.getIdentitykey(), id.toString());
        }
        for (Algorithm algorithm : algorithms) {
            Integer id = algorithm.getIdAlgorithm();
            jsonObjectDB.put(algorithm.getIdentitykey(), id.toString());
        }
        jsonObjectFinal.put("DB", jsonObjectDB);
        return jsonObjectFinal;
    }

    public void postCodeTreeDetail(JSONObject json) {
        System.out.println(json.toJSONString());
        List<Classification> classificationList = classificationDao.getAll();
        List<Family> families = familyDao.getAll();
        List<Algorithm> algorithms = algorithmDao.getAll();
        analyseTree(json, classificationList, families, algorithms);
        for (Classification classification : classificationList) {
            Integer classId = classification.getIdClassification();
            classificationDao.removeByIdClassification(classId);
        }

        for (Family family : families) {
            Integer familyId = family.getIdFamily();
            familyDao.removeByIdFamily(familyId);
        }
        for (Algorithm algorithm : algorithms) {
            Integer algorithmId = algorithm.getIdAlgorithm();
            algorithmDao.removeByIdAlgorithm(algorithmId);
        }
    }

    public void analyseTree(JSONObject json, List<Classification> classificationList, List<Family> families, List<Algorithm> algorithms) {
        JSONArray children = json.getJSONArray("children");
        for (int i = 0; i < children.size(); i++) {
            JSONObject newchildren = children.getJSONObject(i);
            if ((newchildren.get("type").toString()).equals("classification")) {
                String name = newchildren.get("title").toString();
                String key = newchildren.get("key").toString();
                String classid = newchildren.get("dbId").toString();
                if (classid.equals("+1")) {
                    classificationDao.save(name, key, "", "");
                } else {
                    Integer classidNum = Integer.parseInt(classid);
                    classificationDao.updateByClassification(name, key, classidNum);
                    classificationList.remove(classificationDao.findClassificationByIdClassification(classidNum));
                }
            } else if ((newchildren.get("type").toString()).equals("sub_classification")) {
                String name = newchildren.get("title").toString();
                String key = newchildren.get("key").toString();
                String familyid = newchildren.get("dbId").toString();
                if (familyid.equals("+1")) {
                    familyDao.save(name, key, "", "");
                } else {
                    Integer familyidNum = Integer.parseInt(familyid);
                    familyDao.updateByFamily(name,key,familyidNum);
                    families.remove(familyDao.findFamilyByIdFamily(familyidNum));
                }
            } else if ((newchildren.get("type").toString()).equals("algorithm_type")) {
                String name = newchildren.get("title").toString();
                String key = newchildren.get("key").toString();
                String algorid = newchildren.get("dbId").toString();
                if (algorid.equals("+1")) {
                    algorithmDao.save(name, key, "", "");
                } else {
                    Integer algoridNum = Integer.parseInt(algorid);
                    algorithmDao.updateByAlgorithm(name,key,algoridNum);
                    algorithms.remove(algorithmDao.findAlgorithmByIdAlgorithm(algoridNum));
                }
            }
            analyseTree(newchildren, classificationList, families, algorithms);
        }
    }

    public JSONObject getClassificationContent(String key) {
        Classification classification = classificationDao.findByIdentitykey(key);
        if (classification != null) {
            JSONObject json = new JSONObject();
            json.put("subtitle", classification.getSubtitle());
            json.put("textbody", classification.getDesription());
            json.put("type", "classification");
            json.put("title", classification.getName());
            return json;
        } else {
            JSONObject json = new JSONObject();
            json.put("subtitle", "");
            json.put("textbody", "");
            json.put("type", "classification");
            json.put("title", "");
            return json;
        }
    }

    public void postClassificationContent(JSONObject json) {
        String key = json.get("key").toString();
        String subtitle = json.get("subtitle").toString();
        String textbody = json.get("textbody").toString();
        Classification classification = classificationDao.findByIdentitykey(key);
        if (classification != null) {
            classificationDao.updateByClassificationContent(key, textbody, subtitle);
        }
    }

    public JSONObject getFamilyContent(String key) {
        Family family = familyDao.findByIdentitykey(key);
        if (family != null) {
            JSONObject json = new JSONObject();
            json.put("subtitle", family.getSubtitle());
            json.put("textbody", family.getDesription());
            json.put("type", "sub_classification");
            json.put("title", family.getName());
            return json;
        } else {
            JSONObject json = new JSONObject();
            json.put("subtitle", "");
            json.put("textbody", "");
            json.put("type", "sub_classification");
            json.put("title", "");
            return json;
        }
    }

    public void postFamilyContent(JSONObject json) {
        String key = json.get("key").toString();
        String subtitle = json.get("subtitle").toString();
        String textbody = json.get("textbody").toString();
        Family family = familyDao.findByIdentitykey(key);
        if (family != null) {
            familyDao.updateByFamilyContent(key, textbody, subtitle);
        }
    }

    public JSONObject getAlgorithmContent(String key) {
        Algorithm algorithm = algorithmDao.findByIdentitykey(key);
        if (algorithm != null) {
            JSONObject json = new JSONObject();
            json.put("subtitle", algorithm.getSubtitle());
            json.put("textbody", algorithm.getDesription());
            json.put("type", "algorithm_type");
            json.put("title", algorithm.getName());
            return json;
        } else {
            JSONObject json = new JSONObject();
            json.put("subtitle", "");
            json.put("textbody", "");
            json.put("type", "algorithm_type");
            json.put("title", "");
            return json;
        }
    }

    public void postAlgorithmContent(JSONObject json) {
        String key = json.get("key").toString();
        String subtitle = json.get("subtitle").toString();
        String textbody = json.get("textbody").toString();
        Algorithm algorithm = algorithmDao.findByIdentitykey(key);
        if (algorithm != null) {
            algorithmDao.updateByAlgorithmContent(key, textbody, subtitle);
        }
    }
}
