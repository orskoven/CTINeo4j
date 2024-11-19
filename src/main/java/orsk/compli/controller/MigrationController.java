package orsk.compli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.service.UserMigrationService;

@RestController
@RequestMapping("/migration")
public class MigrationController {

    private final UserMigrationService userMigrationService;

    @Autowired
    public MigrationController(UserMigrationService userMigrationService) {
        this.userMigrationService = userMigrationService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> migrateUsers() {
        userMigrationService.migrateUsers();
        return ResponseEntity.ok("User migration completed!");
    }
}