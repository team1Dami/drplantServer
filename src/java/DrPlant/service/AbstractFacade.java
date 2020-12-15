/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.exceptions.CreateException;
import DrPlant.exceptions.DeleteException;
import DrPlant.exceptions.ReadException;
import DrPlant.exceptions.UpdateException;
import DrPlant.exceptions.UserExistException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author rubir
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) throws CreateException,UserExistException{
        getEntityManager().persist(entity);
    }

    public void edit(T entity) throws UpdateException{
        getEntityManager().merge(entity);
    }

    public void remove(T entity) throws DeleteException {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) throws ReadException {
        return getEntityManager().find(entityClass, id);
    }
    //Method to list every shop in the database
    public List<T> findAllShops() throws ReadException {
        return (List<T>) getEntityManager().createNamedQuery("getAllShops").getResultList();
    }
    //Method to find a single shop inside the database with the name of the shop
    public T findShopName(Object shop_name) throws ReadException {
        return (T) getEntityManager().createNamedQuery("getShopByName").setParameter("shop_name",shop_name).getSingleResult();
    }
    //Method to list every user in the database
    public List<T> findAllUsers() throws ReadException {
        return (List<T>) getEntityManager().createNamedQuery("getAllUsers").getResultList();
    }
    //Method tofind a especific user by the login and the password
    public T findLogin(Object login,Object passwd)throws ReadException {
        return (T) getEntityManager().createNamedQuery("findUserByLoginAndPasswd").setParameter("login",login).setParameter("passwd", passwd).getSingleResult();
    }
}
