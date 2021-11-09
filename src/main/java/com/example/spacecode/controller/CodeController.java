package com.example.spacecode.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class CodeController {

    // TEST NORMAL
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/codetree", method = RequestMethod.POST )
    public String getTreeNormal(@RequestBody JSONObject json) {
        System.out.println(json);
        return "post tree success";
    }

    // TEST NORMAL GET TREE
//    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
//    @RequestMapping( value="/normal/codetree", method = RequestMethod.POST )
//    public String getTreeNormal(@RequestBody Map<String, String> map ) {
//        System.out.println(map);
//        return "post tree success";
//    }

    // TEST ADMIN GET TREE
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping( value = "/admin/codetree", method = RequestMethod.GET )
    public String getTreeAdmin() {
        return "ROLE_ADMIN success！";
    }
}
