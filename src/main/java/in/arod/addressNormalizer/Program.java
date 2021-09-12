package in.arod.addressNormalizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Program {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Program.class, args);
        run.start();
    }
}


