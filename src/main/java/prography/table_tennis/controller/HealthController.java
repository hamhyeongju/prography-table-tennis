package prography.table_tennis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import prography.table_tennis.util.ApiResponse;

@RestController
@Slf4j
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<Object> health() {
        return new ApiResponse<>();
    }
}
