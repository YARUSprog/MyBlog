
package com.mycompany.myblog.session;

import com.mycompany.myblog.entity.Contacts;
import com.mycompany.myblog.entity.Users;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YARUS
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserManager {
    
    @PersistenceContext(unitName = "com.mycompany_MyBlog_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Resource 
    private SessionContext context;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer addUser(final String login, final String password, final String passwordTwo, final Map<String, String[]> contacts){
        try{
           if(login!=null && password!=null && passwordTwo!=null && password.equals(passwordTwo)){
               List resultList = em.createNamedQuery("Users.findByLogin").setParameter("login", login).getResultList();
               if (resultList.size()==0){
                   Users user = newUser(login, password);
                   newContacts(user, contacts);
                   return 0;              
               }else {return 3;}
           } else{
               return 2;
           }
        } catch(Exception e){
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }
    
    private Users newUser(String login, String password) {
        Users user = new Users(login, password);
        em.persist(user);
        return user;
    }
    
    private void newContacts(Users user, Map<String, String[]> contacts) {
        if (contacts.size()>0){
            for (Map.Entry<String, String[]> entry : contacts.entrySet()) {
                String key = entry.getKey();
                String[] values = entry.getValue();
                for (String value : values) {
                    Contacts contact=new Contacts();
                    contact.setLogin(user);
                    contact.setName(key);
                    contact.setValue(value);
                    em.persist(contact);
                }
            }
        }
    }
}
