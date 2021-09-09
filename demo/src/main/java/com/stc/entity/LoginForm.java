package com.stc.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mahmoud
 * since I've used lombok we can remove getters and setters they're done behind the scenes
 */
@Entity
@Table(name="system_user")
@XmlRootElement
@Data
public class LoginForm {

    private String username;
    private String password;

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginForm() {

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

    @Override
    public String toString() {
        return "LoginForm{" + "username=" + username + ", password=" + password + '}';
    }


}