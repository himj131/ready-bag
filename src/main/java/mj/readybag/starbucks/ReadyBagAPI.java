package mj.readybag.starbucks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReadyBagAPI {
    BagCountService bagCountService;

    public ReadyBagAPI(BagCountService bagCountService) {
        this.bagCountService = bagCountService;
    }

    @GetMapping("/hello")
    public String hello(){
        System.out.println("Hello ready-bag World");
        return "Hello ready-bag World";
    }

    @GetMapping("/pinkbag-count/{id}/{pwd}")
    public void getPinkBagCount(@PathVariable("id") String id,
                                @PathVariable("pwd") String pwd) throws IOException {
        bagCountService.getCount(id, pwd);
    }
}
