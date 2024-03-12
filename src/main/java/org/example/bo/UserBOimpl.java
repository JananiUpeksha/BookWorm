package org.example.bo;

import org.example.dao.BranchDAO;
import org.example.dao.BranchDAOimpl;
import org.example.dao.DAOFactory;
import org.example.dao.UserDAO;
import org.example.dto.UserDto;
import org.example.entity.Branches;
import org.example.entity.Users;

import java.util.List;

public class UserBOimpl implements UserBO{
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    private BranchDAO branchDAO = new BranchDAOimpl();

    @Override
    public boolean save(Users dto) {
       // return userDAO.save(new User(user.getEmail(), user.getName(), user.getPassword()));
       // return userDAO.save(user);
        try {
            return userDAO.save(new Users(dto.getEmail(), dto.getName(), dto.getPassword(), dto.getBranch()));
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

    /*@Override
    public boolean update(UserDto dto) {
        //return userDAO.update(new User(dto.getEmail(),dto.getName(),dto.getPassword()));
        //return userDAO.update(dto);
        try {
            return userDAO.update(new Users(dto.getEmail(), dto.getName(), dto.getPassword(),dto.getBranch()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;

        }
    }*/
   /* @Override
    public boolean update(UserDto dto) {
        try {
            // Retrieve the Branches object corresponding to the branch name
            Branches branch = branchDAO.getBranches(dto.getBranch());

            if (branch == null) {
                // Branch not found, handle the error accordingly
                System.err.println("Branch not found for user update");
                return false;
            }

            // Create a new Users entity with the updated information
            Users user = new Users(dto.getEmail(), dto.getName(), dto.getPassword(), branch);

            // Call the update method of userDAO to update the user
            return userDAO.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }*/

    @Override
    public boolean update(UserDto dto) {
        try {
            // Retrieve the existing user entity by ID
            Users existingUser = userDAO.getUser(dto.getId());

            if (existingUser == null) {
                // User not found, handle the error accordingly
                System.err.println("User not found for update");
                return false;
            }

            // Update the properties of the existing user entity
            existingUser.setEmail(dto.getEmail());
            existingUser.setName(dto.getName());
            existingUser.setPassword(dto.getPassword());

            // Retrieve the Branches object corresponding to the branch name
            Branches branch = branchDAO.getBranches(dto.getBranch());

            if (branch == null) {
                // Branch not found, handle the error accordingly
                System.err.println("Branch not found for user update");
                return false;
            }

            // Set the branch for the existing user entity
            existingUser.setBranch(branch);

            // Call the update method of userDAO to update the user
            return userDAO.update(existingUser);
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
