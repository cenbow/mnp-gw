/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.web.user;

import cat.mnp.web.dao.UserDao;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author HP-CAT
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao dao;
    private String splitter;

    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    public String getSplitter() {
        return splitter;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException, DataAccessException {

        cat.mnp.web.domain.User u = dao.findById(userId);
        if (u == null) {
            throw new UsernameNotFoundException("user not found");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : u.getRoles().split(splitter)) {
            authorities.add(new SimpleGrantedAuthority(role.trim()));
        }

        User user = new User(u.getUserId(), "", authorities);

        return user;
    }
}
