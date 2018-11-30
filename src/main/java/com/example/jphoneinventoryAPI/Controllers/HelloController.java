package com.example.jphoneinventoryAPI.Controllers;

import com.example.jphoneinventoryAPI.Models.PhoneDetails;
import com.example.jphoneinventoryAPI.Repository.PhoneDetailsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private PhoneDetailsRepository repository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/phoneInventories")
    public String getPhoneInventories() {
        PhoneDetails details = new PhoneDetails("Apple","IPhone 8", 699, 1);

        return toString(details);
    }

    @RequestMapping(value = "/DbPostPhoneInventories", method = RequestMethod.POST)
      public PhoneDetails createPhoneInventoriesToDB(@Valid @RequestBody PhoneDetails PhoneDetails)
    {
        PhoneDetails.setId(ObjectId.get());
        repository.save(PhoneDetails);

      return PhoneDetails;
    }

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
