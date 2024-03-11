package org.example.bo;

import org.example.dao.DAOFactory;
import org.example.dao.UserDAO;
import org.example.dto.UserDto;
import org.example.entity.Users;

import java.util.List;

public class UserBOimpl implements UserBO{
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean save(Users dto) {
       // return userDAO.save(new User(user.getEmail(), user.getName(), user.getPassword()));
       // return userDAO.save(user);
        try {
            return userDAO.save(new Users(dto.getEmail(), dto.getName(), dto.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        //return userDAO.delete(id);
        try {
            return userDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(UserDto dto) {
        //return userDAO.update(new User(dto.getEmail(),dto.getName(),dto.getPassword()));
        //return userDAO.update(dto);
        try {
            return userDAO.update(new Users(dto.getEmail(), dto.getName(), dto.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;

        }
    }

    @Override
    public Users getUser(int id) {
        //return userDAO.getUser(id);
        try {
            return userDAO.getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Users> getAll() {
        //return userDAO.getAll();
        try {
            return userDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
