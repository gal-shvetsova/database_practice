package model;

import java.util.UUID;

public class User {
    final private Role role;
    final private UUID id;

    public User(Role role, UUID id) {
        this.role = role;
        this.id = id;
    }

    public Role getRole() {
        return role;
    }
}
