package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import orsk.compli.dtos.UserDTO;

import java.util.List;
import java.util.Objects;

@Service
public class UserApiClient {

    private final RestTemplate restTemplate;
    private final String mysqlAppBaseUrl = "http://localhost:8080/api/mysql/users";

    @Autowired
    public UserApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserDTO> fetchAllUsers() {
        ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(mysqlAppBaseUrl, UserDTO[].class);
        return List.of(Objects.requireNonNull(response.getBody()));
    }
}