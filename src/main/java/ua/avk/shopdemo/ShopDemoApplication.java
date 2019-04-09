package ua.avk.shopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AutoConfigurationPackage
@SpringBootApplication
public class ShopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopDemoApplication.class, args);
    }

}
