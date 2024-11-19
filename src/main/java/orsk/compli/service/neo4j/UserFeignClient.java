package orsk.compli.service.neo4j;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import orsk.compli.dtos.UserDTO;

import java.util.List;

@FeignClient(name = "mysql-service", url = "http://localhost:8080")
public interface UserFeignClient {

    @GetMapping("/api/mysql/users")
    List<UserDTO> fetchAllUsers();
}