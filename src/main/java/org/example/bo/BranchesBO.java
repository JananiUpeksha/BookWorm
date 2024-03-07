package org.example.bo;

import org.example.entity.Branches;

import java.util.List;

public interface BranchesBO extends SuperBO{
    public boolean save(Branches dto);
    public boolean delete(String branchName);
    public boolean update(Branches dto);
    public Branches getBranches(String branchName);
    public List<Branches> getAll();
}
