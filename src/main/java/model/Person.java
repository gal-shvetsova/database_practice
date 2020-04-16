package model;

import java.util.UUID;

public class Person {
    protected final UUID id;
    final private Role role;
    final private String login;
    final private String password;
    protected final String surname;
    protected final String name;

    public Person(UUID id, Role role, String login, String password, String surname, String name) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
    }

    public Person(Person person){
        this(person.id, person.role, person.login, person.password, person.surname, person.name);
    }

    public Role getRole() {
        return role;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
