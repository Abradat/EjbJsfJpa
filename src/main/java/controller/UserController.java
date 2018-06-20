package controller;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.math.BigInteger;
import java.util.ArrayList;
import model.entity.User;
import model.service.UserDao;

import java.util.HashMap;
import java.util.List;

@ManagedBean(name = "userController")
public class UserController {

    @EJB
    private UserDao userDao;

    private User userForInsert = new User();
    private User userForFind = new User();
    private User userForChange = new User();

    private List<User> users;

    private User resultUser;

    private UIComponent saveComponent;
    private UIComponent findComponent;
    private UIComponent changeComponent;

    @PostConstruct
    public void usersInit() {
        users = getUsersFromDB();
    }

    public UIComponent getSaveComponent() {
        return saveComponent;
    }

    public void setSaveComponent(UIComponent saveComponent) {
        this.saveComponent = saveComponent;
    }

    public UIComponent getFindComponent() {
        return findComponent;
    }

    public void setFindComponent(UIComponent findComponent) {
        this.findComponent = findComponent;
    }

    public UIComponent getChangeComponent() {
        return changeComponent;
    }

    public void setChangeComponent(UIComponent changeComponent) {
        this.changeComponent = changeComponent;
    }
    
    public User getUserForInsert() {
        return userForInsert;
    }

    public void setUserForInsert(User userForInsert) {
        this.userForInsert = userForInsert;
    }

    public User getUserForFind() {
        return userForFind;
    }

    public void setUserForFind(User userForFind) {
        this.userForFind = userForFind;
    }

    public User getUserForChange() {
        return userForChange;
    }

    public void setUserForChange(User userForChange) {
        this.userForChange = userForChange;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getResultUser() {
        return resultUser;
    }


    public void setResultUser(User resultUser) {
        this.resultUser = resultUser;
    }

    public void insertUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String message;
        boolean inserted = true, errorHappened = false;
        try {
            inserted = userDao.insertUser(userForInsert);
        } catch (Exception e) {
            errorHappened = true;
        }
        if(inserted && !errorHappened) {
            //context.addMessage(saveComponent.getClientId(), new FacesMessage("User Inserted Successfully"));
            message = "User inserted successfully";
            User tmpUser = new User(userForInsert.getUserName(), userForInsert.getAmount(), userForInsert.getAccess());
            users.add(tmpUser);
        }
        else {
            if(errorHappened) {
                message = "Fields must not be empty";
            }
            else {
                message = "User is already in the database";
            }
            //context.addMessage(saveComponent.getClientId(), new FacesMessage("User is already in the database"));

            //cleanUser(1);
        }
        context.addMessage(saveComponent.getClientId(), new FacesMessage(message));
        cleanUser(1);

    }

    public void readUser() {
        resultUser = userDao.readUser(userForFind);
        FacesContext context = FacesContext.getCurrentInstance();
        String message;
        if(resultUser != null) {
            if(resultUser.getAccess() == 'r') {
                message = "username is " + resultUser.getUserName() + ", user amount is : " + resultUser.getAmount();
            }
            else {
                message = "User does not have read permission";
            }
        }
        else {
            message = "User not found";
        }
        context.addMessage(findComponent.getClientId(), new FacesMessage(message));
        cleanUser(2);
    }

    public void changeUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String message;
        boolean errorHappened = false;
        int result = -1;
        try {
            result = userDao.changeUser(userForChange);
        }catch (Exception e) {
            errorHappened = true;
        }

        if(result == 1 && !errorHappened) {
            message = "Successfully Changed amount";
            for(User tmpUser : users) {
                if(tmpUser.getUserName().equals(userForChange.getUserName()) ) {
                    tmpUser.setAmount(userForChange.getAmount());
                }
            }
        }
        else if(result == 2 && !errorHappened) {
            message = "User does not have write permission";
        }
        else if(result == 3 && !errorHappened){
            message = "User does not exists";
        }
        else {
            message = "Fields must not be empty !";
        }


        context.addMessage(changeComponent.getClientId(), new FacesMessage(message));
        cleanUser(3);
    }

    private List<User> getUsersFromDB() {
        return userDao.readUsers();
    }

    private void cleanUser(int number) {
        /*user.setUserName(null);
        user.setAccess(null);
        user.setAmount(null);
        */
        if(number == 1) {
            userForInsert.setUserName(null);
            userForInsert.setAccess(null);
            userForInsert.setAmount(null);
        }
        else if(number == 2) {
            userForFind.setUserName(null);
            userForFind.setAccess(null);
            userForFind.setAmount(null);
        }
        else {
            userForChange.setUserName(null);
            userForChange.setAccess(null);
            userForChange.setAmount(null);
        }
    }


    private void redirect(String pageName) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + pageName);
        } catch (Exception e) {
            //LOGGER.error(e);
        }
    }
}
