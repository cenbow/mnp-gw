/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.web.domain;

/**
 *
 * @author HP-CAT
 */
public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    @Override
    public String toString() {
        return String.format("%s(%s)(%s)", User.class, userId, roles);
    }
}
