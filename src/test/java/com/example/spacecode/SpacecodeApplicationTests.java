package com.example.spacecode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.spacecode.dao.*;
import com.example.spacecode.golbal.JwtTokenUtil;
import com.example.spacecode.model.Algorithm;
import com.example.spacecode.model.Classification;
import com.example.spacecode.model.Family;
import com.example.spacecode.model.User;
import com.example.spacecode.service.AuthService;
import com.example.spacecode.service.CodeService;
import com.example.spacecode.service.UserService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class SpacecodeApplicationTests {

    @Autowired
    CodeService codeService;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    ClassificationDao classificationDao;

    @Autowired
    FamilyDao familyDao;

    @Autowired
    AlgorithmDao algorithmDao;

    @Autowired
    UserDao userDao;

    @Test
    void testTree() throws IOException, ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        JSONObject child = new JSONObject();
        child.put("type", "classification");
        child.put("title", "title");
        child.put("key", "key");
        child.put("dbId", "1");
        child.put("children", new JSONArray());
        jsonArray.add(child);

        JSONObject child2 = new JSONObject();
        child2.put("type", "sub_classification");
        child2.put("title", "title");
        child2.put("key", "key");
        child2.put("dbId", "1");
        child2.put("children", new JSONArray());
        jsonArray.add(child2);

        JSONObject child3 = new JSONObject();
        child3.put("type", "algorithm_type");
        child3.put("title", "title");
        child3.put("key", "key");
        child3.put("dbId", "1");
        child3.put("children", new JSONArray());
        jsonArray.add(child3);

        jsonObject.put("children", jsonArray);

        codeService.postCodeTree(jsonObject);
        codeService.postCodeTreeDetail(jsonObject);

        algorithmDao.save("search", "key", "searching", "sth");
        familyDao.save("fam","key", "family...", "sth");
        classificationDao.save("classification", "key", "some", "idk");
        List<Classification> classificationList = classificationDao.getAll();
        List<Family> families = familyDao.getAll();
        List<Algorithm> algorithms = algorithmDao.getAll();
        codeService.analyseTree(jsonObject, classificationList, families, algorithms);

        assertNotNull(codeService.getCodeTree());
        assertNotNull(codeService.getAlgorithmContent("key"));
        assertNotNull(codeService.getFamilyContent("key"));
        assertNotNull(codeService.getClassificationContent("key"));
    }

    @Test
    void testClassification() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "k");
        jsonObject.put("subtitle", "title");
        jsonObject.put("textbody", "body");
        codeService.postClassificationContent(jsonObject);
        assertNotNull(codeService.getClassificationContent("key"));
        assertNotNull(codeService.getClassificationContent("k"));
    }

    @Test
    void testFamily() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "k");
        jsonObject.put("subtitle", "title");
        jsonObject.put("textbody", "body");
        codeService.postFamilyContent(jsonObject);
        assertNotNull(codeService.getFamilyContent("key"));
        assertNotNull(codeService.getFamilyContent("k"));
    }

    @Test
    void testAlgo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "k");
        jsonObject.put("subtitle", "title");
        jsonObject.put("textbody", "body");
        codeService.postAlgorithmContent(jsonObject);
        assertNotNull(codeService.getAlgorithmContent("key"));
        assertNotNull(codeService.getAlgorithmContent("k"));
    }

    @Test
    void testImplementation() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "k");
        jsonObject.put("language", "english");
        jsonObject.put("codebody", "body");
        codeService.postImplementationContent(jsonObject);
        assertNotNull(codeService.getImplementationContent("key", "english"));
        assertNotNull(codeService.getImplementationContent("k", "english"));
    }

    @Test
    void testBenchMark() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("algorKey", "k");
        jsonObject.put("algorId", 1);
        jsonObject.put("username", "user");
        jsonObject.put("date", "date");
        jsonObject.put("cpu", "cpu");
        jsonObject.put("algorKey", "k");
        jsonObject.put("ram", "22222");
        jsonObject.put("gpu", "3080");
        jsonObject.put("L1", "L1");
        jsonObject.put("L2", "L2");
        jsonObject.put("L3", "L3");
        jsonObject.put("time", "time");
        jsonObject.put("space", "space");
        jsonObject.put("like", 999);
        jsonObject.put("star", 5);
        jsonObject.put("benchmarkType", "type");
        jsonObject.put("idBenchmark", 999999);
        codeService.deleteBenchmark(jsonObject);
        codeService.postBenchmark(jsonObject);
        assertNotNull(codeService.getBenchmark(jsonObject));
    }

    @Test
    void testAuthentication() {
        User user =  new User("admin2", "password");
        authService.delete("admin2");
        assertEquals(user.getUserName(),authService.register(user).getUserName());
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        User fromDb = userDao.findbyname("admin2");
        String token = jwtTokenUtil.generateToken(fromDb);
        assertNotNull(jwtTokenUtil.getExpirationDateFromToken(token));
        assertEquals(jwtTokenUtil.getUsernameFromToken(token), fromDb.getUserName());
        assertTrue(jwtTokenUtil.validateToken(token, fromDb));
        assertNotNull(authService.login("admin2", "password"));
        assertNotNull(authService.login("admin2", "wrongpassword"));
        assertNotNull(authService.login("nouser", "wrongpassword"));
        authService.delete("admin2");
    }

    @Test
    void testUserService() {
        authService.register(new User("JunitTesting1111","password"));
        assertEquals(userService.loadUserByUsername("JunitTesting1111").getUsername(),"JunitTesting1111");
        authService.delete("JunitTesting1111");
    }

}
