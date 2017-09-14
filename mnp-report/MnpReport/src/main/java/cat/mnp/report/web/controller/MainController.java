/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HP-CAT
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping
    public String getHomePage() {
        return "home";
    }

    @RequestMapping(value = "/admin")
    public String getAdminPage() {
        return "admin";
    }

}
