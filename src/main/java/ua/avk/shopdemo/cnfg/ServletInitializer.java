package ua.avk.shopdemo.cnfg;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.avk.shopdemo.ShopDemoApplication;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableWebMvc
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShopDemoApplication.class);
    }

}
