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

    private User user = new User();

    private List<User> users;

    private User resultUser;

    private UIComponent saveComponent;
    private UIComponent findComponent;
    private UIComponent changeComponent;
    private UIComponent insertAmountComponent;
    private UIComponent changeAmountComponent;

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

    public UIComponent getInsertAmountComponent() {
        return insertAmountComponent;
    }

    public void setInsertAmountComponent(UIComponent insertAmountComponent) {
        this.insertAmountComponent = insertAmountComponent;
    }

    public UIComponent getChangeAmountComponent() {
        return changeAmountComponent;
    }

    public void setChangeAmountComponent(UIComponent changeAmountComponent) {
        this.changeAmountComponent = changeAmountComponent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void insertUser(User user) {
        FacesContext context = FacesContext.getCurrentInstance();

        if(user.getAmount() > 999999999) {
            context.addMessage(insertAmountComponent.getClientId(), new FacesMessage("Amount is invalid"));
        }
        else {
            if(userDao.insertUser(user) == true) {
                //context.addMessage(saveComponent.getClientId(), new FacesMessage("User Inserted Successfully"));
                users.add(user);

            }
            else {
                context.addMessage(saveComponent.getClientId(), new FacesMessage("User is already in the database"));
            }
        }

        cleanUser();

    }

    public void readUser(User user) {
        resultUser = userDao.readUser(user);
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
        cleanUser();
    }

    public void changeUser(User user) {
        FacesContext context = FacesContext.getCurrentInstance();
        String message;

        if(user.getAmount() > 999999999) {
            message = "The amount is invalid";
        }
        else {
            int result = userDao.changeUser(user);
            if(result == 1) {
                message = "Successfully Changed amount";
                for(User tmpUser : users) {
                    if(tmpUser.getUserName().equals(user.getUserName()) ) {
                        tmpUser.setAmount(user.getAmount());
                    }
                }
            }
            else if(result == 2) {
                message = "User does not have write permission";
            }
            else {
                message = "User does not exists";
            }
        }

        context.addMessage(changeComponent.getClientId(), new FacesMessage(message));
        cleanUser();
    }

    private List<User> getUsersFromDB() {
        return userDao.readUsers();
    }

    private void cleanUser() {
        user.setUserName(null);
        user.setAccess(null);
        user.setAmount(null);
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
