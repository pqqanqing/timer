package com.wjs.timer.inrpc.controller.home;

import com.wjs.common.base.service.BaseService;
import com.wjs.common.base.web.BaseControllerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home.shtml")
public class HomeController extends BaseControllerImpl {

    @Override
    protected String getBasePath() {
        return "home/";
    }

    @Override
    protected String getRedirectPath() {
        return null;
    }

    @Override
    protected BaseService getBaseService() {
        return null;
    }

    @RequestMapping(params = "action=toHome")
    public String toHome(HttpServletRequest request, HttpServletResponse response) {
        return getBasePath() + "home";
    }
}
