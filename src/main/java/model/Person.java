package model;

public class Person {
    final private Integer id;
    final private Role role;
    final private String login;
    final private String password;
    private final String surname;
    private final String name;

    public Person(Integer id, Role role, String login, String password, String surname, String name) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
    }
}
