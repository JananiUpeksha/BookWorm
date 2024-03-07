package org.example.dao;

import org.example.entity.Books;
import org.example.entity.Branches;

import java.util.List;

public interface BranchDAO extends SuperDAO{
    public boolean save(Branches branches);
    public boolean delete(String branchName);
    public boolean update(Branches branches);
    public Branches getBranches(String branchName);
    public List<Branches> getAll();
}
