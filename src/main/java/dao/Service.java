package dao;

import model.Club;
import model.Person;
import model.Role;

import java.util.List;

public class Service {
    public static Person signIn(String login, String password){
        return PersonDao.getByLoginAndPassword(login, password);
    }

    public static List<Club> getAllClubs(){
        return ClubDao.getAll();
    }
}
