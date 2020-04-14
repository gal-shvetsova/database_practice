package model;

public class Sportsman {
    private final Integer id;
    private String name;
    private String surname;
    private String patronymic;

    public Sportsman(Integer id, String name, String surname, String patronymic) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }


}