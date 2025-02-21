package prography.table_tennis.controller;

import prography.table_tennis.dto.request.InitializationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prography.table_tennis.service.InitializationService;
import prography.table_tennis.util.ApiResponse;

@RestController
@RequiredArgsConstructor
public class InitializationController {

    private final InitializationService initializationService;

    @PostMapping("/init")
    public ApiResponse<Object> init(@RequestBody InitializationRequest request) {

        initializationService.init(request);

        return new ApiResponse<>();
    }
}
