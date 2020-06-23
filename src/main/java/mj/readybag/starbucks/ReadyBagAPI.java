package mj.readybag.starbucks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class ReadyBagAPI {
    BagCountService bagCountService;

    public ReadyBagAPI(BagCountService bagCountService) {
        this.bagCountService = bagCountService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model){
        String result = "Hello ready-bag World";
        model.addAttribute("result", result);
        return "hello";
    }

    @RequestMapping(value = "/ready-bag/{id}/{pwd}", method = RequestMethod.GET)
    public String getPinkBagCount(@PathVariable("id") String id,
                                @PathVariable("pwd") String pwd,
                                Model model) throws IOException {
        Info info = bagCountService.getCount(id, pwd);
        model.addAttribute("info", info);
        return "info";
    }
}
