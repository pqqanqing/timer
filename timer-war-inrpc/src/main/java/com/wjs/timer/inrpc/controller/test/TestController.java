package com.wjs.timer.inrpc.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panqingqing on 16/9/13.
 */
@Controller
@RequestMapping("test.shtml")
public class TestController {

    @ResponseBody
    @RequestMapping(params = "action")
    public List<String> getList(String action) {
        List<String> list = new ArrayList<>();
        list.add(action);
        list.add("test1");
        list.add("test2");
        return list;
    }


}
