package com.amu.custommqtt.client;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Value
@Component
public class Dummy {

    String name = "Dummy:" + Instant.now() ;
}
