package com.crm4telecom.ejb;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserManagerLocal {
    
    boolean login(String login,String password);
    List<String> getlogins();
}
