package org.example.bo;

import org.example.dto.UserDto;
import org.example.entity.User;

import java.util.List;

public interface UserBO extends SuperBO{
    public boolean save(UserDto dto);
    public static boolean delete(int id);
    public boolean update(UserDto dto);
    public User getUser(int id);
    public List<User> getAll();

}
