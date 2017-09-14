/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.web.dao;

import cat.mnp.web.domain.User;

/**
 *
 * @author HP-CAT
 */
public interface UserDao extends SimpleDao {

    public User findById(String id);
}
