package com.example.jphoneinventoryAPI.Controllers;

import com.example.jphoneinventoryAPI.Models.PhoneDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/phoneInventories")
    public String getPhoneInventories() {
        PhoneDetails details = new PhoneDetails("Apple","IPhone 8", 699, 1);

        return toString(details);
    }

//    @PostMapping("/postPhoneInventories/{make}/{model}/{price}/{quantity}")
//    public String postPhoneInventories(@PathVariable("make") String make,
//                                       @PathVariable("model") String model,
//                                       @PathVariable("price") int price,
//                                       @PathVariable("quantity") int quantity) {
//        PhoneDetails details = new PhoneDetails(make,model, price, quantity);
//
//        return toString(details);
//    }

    @RequestMapping(value="/postPhoneInventories/",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String postPhoneInventories( @RequestBody Map<String, String> body) {
        PhoneDetails details = new PhoneDetails(body.get("Make"),body.get("Model"),Integer.parseInt(body.get("Price")),Integer.parseInt(body.get("Quantity")));
        return toString(details);
    }

        private String toString(PhoneDetails details)
    {
      return "Make:" + details.Make + '\n' + "Model:" + details.Model + '\n' +
           "Price:" + details.Price + '\n' + "Quantity:" + details.Quantity;
    }

}
