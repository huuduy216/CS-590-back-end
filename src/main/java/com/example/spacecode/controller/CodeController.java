package com.example.spacecode.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.spacecode.service.CodeService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = "*")
@RestController
public class CodeController {

    @Autowired
    private CodeService codeService;

    // TEST NORMAL POST TREE
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/codetree", method = RequestMethod.POST )
    public String postTreeNormal(@RequestBody JSONArray json) throws IOException {
        JSONObject tree = json.getJSONObject(0);
        codeService.postCodeTree(tree);
        codeService.postCodeTreeDetail(tree);
        return "post tree success";
    }

    // TEST NORMAL GET TREE
//    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/getcodetree", method = RequestMethod.GET )
    public org.json.simple.JSONObject getTreeNormal() throws IOException, ParseException {
        org.json.simple.JSONObject jsonObject = codeService.getCodeTree();
        return jsonObject;
    }

    //Modify Classification Content
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/classification", method = RequestMethod.POST )
    public String postClassification(@RequestBody JSONObject json) throws IOException {
        codeService.postClassificationContent(json);
        return "post classifiction success";
    }

    @RequestMapping( value="/all/getclassification", method = RequestMethod.POST )
    public JSONObject getClassification(@RequestBody JSONObject json) throws IOException {
        String key = json.get("key").toString();
        return codeService.getClassificationContent(key);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/subclassification", method = RequestMethod.POST )
    public String postSubClassification(@RequestBody JSONObject json) throws IOException {
        codeService.postFamilyContent(json);
        return "post sub_classification success";
    }

    @RequestMapping( value="/all/getsubclassification", method = RequestMethod.POST )
    public JSONObject getSubClassification(@RequestBody JSONObject json) throws IOException {
        String key = json.get("key").toString();
        return codeService.getFamilyContent(key);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/algorithm", method = RequestMethod.POST )
    public String postAlgorithm(@RequestBody JSONObject json) throws IOException {
        codeService.postAlgorithmContent(json);
        return "post algorithm success";
    }

    @RequestMapping( value="/all/getalgorithm", method = RequestMethod.POST )
    public JSONObject getAlgorithm(@RequestBody JSONObject json) throws IOException {
        String key = json.get("key").toString();
        return codeService.getAlgorithmContent(key);
    }

 ////////// TEST ADMIN GET TREE
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping( value = "/admin/codetree", method = RequestMethod.GET )
    public String getTreeAdmin() {
        return "ROLE_ADMIN success！";
    }
}
