package mj.readybag.starbucks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/pinkbag-count/{id}/{pwd}")
    public void getPinkBagCount(@PathVariable("id") String id,
                                @PathVariable("pwd") String pwd) throws IOException {
        bagCountService.getCount(id, pwd);
    }
}
