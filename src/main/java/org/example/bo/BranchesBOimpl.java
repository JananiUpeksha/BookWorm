package org.example.bo;

import org.example.dao.BooksDAO;
import org.example.dao.BranchDAO;
import org.example.dao.DAOFactory;
import org.example.entity.Books;
import org.example.entity.Branches;

import java.util.List;

public class BranchesBOimpl implements BranchesBO{
    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCHES);
    @Override
    public boolean save(Branches dto) {
        try {
            Branches newBranch = new Branches(dto.getBranchName(), dto.getLocation(), dto.getBranchAdmin());
            return branchDAO.save(newBranch);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String branchName) {
        try {
            return branchDAO.delete(branchName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Branches dto) {
        try {
            return branchDAO.update(new Branches(dto.getBranchName(), dto.getLocation(), dto.getBranchAdmin()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Branches getBranches(String branchName) {
        try {
            return branchDAO.getBranches(branchName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Branches> getAll() {
        try {
            return branchDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
