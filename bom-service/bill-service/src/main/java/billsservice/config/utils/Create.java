package billsservice.config.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Create {
    public String createUuid() {
         return String.valueOf(UUID.randomUUID());
    }
}
