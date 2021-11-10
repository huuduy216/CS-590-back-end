package com.example.spacecode.controller;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

    boolean trigger=true;
    // TEST NORMAL POST TREE
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/codetree", method = RequestMethod.POST )
    public String postTreeNormal(@RequestBody JSONObject json) throws IOException {
        FileWriter file = new FileWriter("src\\main\\java\\com\\example\\spacecode\\asset\\test.json",false);
        file.write(json.toJSONString());
        file.flush();
        return "post tree success";
    }

    // TEST NORMAL GET TREE
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/getcodetree", method = RequestMethod.GET )
    public JSONObject getTreeNormal() throws IOException, ParseException {
        FileReader fileReader = new FileReader("src\\main\\java\\com\\example\\spacecode\\asset\\test.json");
        if(trigger){
            trigger=false;
            fileReader= new FileReader("src\\main\\java\\com\\example\\spacecode\\asset\\tree.json");
        }
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    // TEST ADMIN GET TREE
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping( value = "/admin/codetree", method = RequestMethod.GET )
    public String getTreeAdmin() {
        return "ROLE_ADMIN success！";
    }
}
