package prography.table_tennis.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeUserResponse {
    private List<FakeUser> fakeUsers;
}