package com.example.spacecode.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.spacecode.dao.*;
import com.example.spacecode.model.*;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.util.ArrayList;
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
    ClassificationDao classificationDao;

    @Autowired
    FamilyDao familyDao;

    @Autowired
    AlgorithmDao algorithmDao;

    @Autowired
    ImplementationDao implementationDao;

    @Autowired
    BenchmarkDao benchmarkDao;

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
                    familyDao.updateByFamily(name, key, familyidNum);
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
                    algorithmDao.updateByAlgorithm(name, key, algoridNum);
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

    public JSONObject getImplementationContent(String key, String language) {
        Algorithm algorithm = algorithmDao.findByIdentitykey(key);
        if (algorithm == null) {
            JSONObject json = new JSONObject();
            json.put("subtitle", "");
            json.put("textbody", "");
            json.put("code", "");
            return json;
        } else {
            Integer algorithmId = algorithm.getIdAlgorithm();
            Implementation implementation = implementationDao.findImplementationByLanguageNameAndFk(language, algorithmId);
            JSONObject json = new JSONObject();
            if (implementation == null) {
                json.put("subtitle", "");
                json.put("textbody", "");
                json.put("code", "");
                return json;
            } else {
                json.put("subtitle", implementation.getSubtitle());
                json.put("textbody", implementation.getDescription());
                json.put("code", implementation.getCode());
                return json;
            }
        }
    }

    public void postImplementationContent(JSONObject json) {
        String key = json.get("key").toString();
        String language = json.get("language").toString();
        String codebody = json.get("codebody").toString();
        Algorithm algorithm = algorithmDao.findByIdentitykey(key);
        if (algorithm != null) {
            Integer algorithmId = algorithm.getIdAlgorithm();
            Implementation implementation = implementationDao.findImplementationByLanguageNameAndFk(language, algorithmId);
            if (implementation == null) {
                implementationDao.save("", "", language, codebody, algorithm.getIdAlgorithm());
            } else {
                implementationDao.updateByImplementationContent(codebody, language, algorithmId);
            }
        }
    }

    public JSONObject getBenchmark(JSONObject json){
        String algorkey = json.get("algorKey").toString();
        Algorithm algorithm = algorithmDao.findByIdentitykey(algorkey);
        ArrayList<Benchmark> benchmarks = new ArrayList<Benchmark>();
        if(algorithm!=null){
            benchmarks = benchmarkDao.findBenchmarksByFk(algorithm.getIdAlgorithm());
        }
        json.put("benchmarks",benchmarks);
        return json;
    }

    public void postBenchmark(JSONObject json) {
        Integer algorithmId = Integer.parseInt(json.get("algorId").toString());
        String username = json.get("username").toString();
        String date = json.get("date").toString();
        String cpu = json.get("cpu").toString();
        String ram = json.get("ram").toString();
        String gpu = json.get("gpu").toString();
        String L1 = json.get("L1").toString();
        String L2 = json.get("L2").toString();
        String L3 = json.get("L3").toString();
        String time = json.get("time").toString();
        String space = json.get("space").toString();
        Integer like = Integer.parseInt(json.get("like").toString());
        Integer star = Integer.parseInt(json.get("star").toString());
        Algorithm algorithm = algorithmDao.findAlgorithmByIdAlgorithm(algorithmId);
        if (algorithm != null) {
           benchmarkDao.save(username,date,cpu,ram,gpu,L1,L2,L3,time,space,like,star,algorithmId);
        }
    }
}
