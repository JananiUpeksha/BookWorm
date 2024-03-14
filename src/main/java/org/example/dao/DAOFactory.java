package org.example.dao;

import org.example.dao.custom.impl.BooksDAOimpl;
import org.example.dao.custom.impl.BranchDAOimpl;
import org.example.dao.custom.impl.UserDAOimpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return daoFactory == null? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        USER,BOOKS,BRANCHES
    }
    public static SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOimpl();
            case BOOKS:
                return new BooksDAOimpl();
            case BRANCHES:
                return new BranchDAOimpl();
            default:
                return null;
        }
    }
}
