package com.example.ir;

import com.example.ir.clients.Activator;
import com.example.ir.clients.Client;
import com.example.ir.clients.ClientsRegistrar;
import com.example.ir.clients.EnableClients;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.TypeHint;

@TypeHint (types = ClientsRegistrar.class)
@Log4j2
@EnableClients
@SpringBootApplication
public class IrApplication {

    public static void main(String[] args) {
        SpringApplication.run(IrApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(SimpleClient simpleClient) {
        return args -> simpleClient.activate();
    }

}


@Client
interface SimpleClient {

    @Activator
    void activate();
}