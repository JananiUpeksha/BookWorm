package org.example.bo;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        User,BOOKS,BRANCHES
    }
    public  static SuperBO getBO(BOType boType){
        switch (boType) {
            case User:
                return new UserBOimpl();
            case BOOKS:
                return new BooksBOimpl();
            case BRANCHES:
                return new BranchesBOimpl();
            default:
                return null;
        }   }
}
