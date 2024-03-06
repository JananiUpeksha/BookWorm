package org.example.bo;

import org.example.dto.UserDto;
import org.example.entity.Users;

import java.util.List;

public interface UserBO extends SuperBO{
    public boolean save(Users dto);
    public  boolean delete(int id);
    public boolean update(UserDto dto);
    public Users getUser(int id);
    public List<Users> getAll();

}
