package org.example.dao;

import config.FactoryConfiguration;
import org.example.entity.Books;
import org.example.entity.Branches;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BranchDAOimpl implements BranchDAO {
    @Override
    public boolean save(Branches branches) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(branches);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String branchName) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Branches branches = session.get(Branches.class, branchName);
            if (branches != null) {
                session.delete(branches);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Branches branches) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(branches);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Branches getBranches(String branchName) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Branches.class, branchName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Branches> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Branches> query = session.createQuery("FROM Branches ", Branches.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
