package com.example.spacecode.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.spacecode.service.CodeService;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class CodeController {

    @Autowired
    private CodeService codeService;

    // TEST NORMAL POST TREE
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/codetree", method = RequestMethod.POST )
    public String postTreeNormal(@RequestBody JSONObject json) throws IOException {
        codeService.postCodeTree(json);
        codeService.postCodeTreeDetail(json);
        return "post tree success";
    }

    // TEST NORMAL GET TREE
//    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/getcodetree", method = RequestMethod.GET )
    public org.json.simple.JSONObject getTreeNormal() throws IOException, ParseException {
        org.json.simple.JSONObject jsonObject = codeService.getCodeTree();
        return jsonObject;
    }

    // TEST ADMIN GET TREE
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping( value = "/admin/codetree", method = RequestMethod.GET )
    public String getTreeAdmin() {
        return "ROLE_ADMIN success！";
    }
}
