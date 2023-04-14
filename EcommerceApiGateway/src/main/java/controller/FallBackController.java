package controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/productFallBack")
    public String productFallBack() {
        return "Fetcching product is taking longer than usual please try again later";
    }

    @GetMapping("/orderFallBack")
    public String orderFallBack() {
        return "Fetcching Order is taking longer than usual please try again later";
    }
}
