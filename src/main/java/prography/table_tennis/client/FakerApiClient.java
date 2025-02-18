package prography.table_tennis.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import prography.table_tennis.client.dto.FakeUserResponse;

@FeignClient(name = "fakerApiClient", url = "https://fakerapi.it/api/v1/users")
public interface FakerApiClient {

    @GetMapping
    FakeUserResponse getUsers(
            @RequestParam("_seed") int seed,
            @RequestParam("_quantity") int quantity,
            @RequestParam(value = "_locale", defaultValue = "ko_KR") String locale
    );
}