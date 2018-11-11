/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonManagement;

import bc_stationary_bll.Datahandling;
import bc_stationary_dll.Datahandler;
import bc_stationary_dll.Datahelper;
import bc_stationary_dll.TableSpecifiers;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya
 * Used to represent a the security question and it's answer for a specific user.
 */
public class UserSecurityQuestions implements Datahandling,Serializable{
    
    private User user;
    private SecurityQuestions question;
    private String answer;

    public UserSecurityQuestions(User user, SecurityQuestions question, String answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }
    public UserSecurityQuestions(User user)
    {
    
        this.user = user;
    }
    
    public UserSecurityQuestions() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = (answer.equals(""))?"N.A":answer;
    }

    public SecurityQuestions getQuestion() {
        return question;
    }

    public void setQuestion(SecurityQuestions question) {
        this.question = (question==null)?new SecurityQuestions():question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = (user==null)?new User():user;
    }

    @Override
    public String toString() {
        return "UserSecurityQuestions{" + "user=" + user + ", question=" + question + ", answer=" + answer + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + Objects.hashCode(this.question);
        hash = 97 * hash + Objects.hashCode(this.answer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserSecurityQuestions other = (UserSecurityQuestions) obj;
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<UserSecurityQuestions> select() {
        ArrayList<UserSecurityQuestions> questions = new ArrayList<UserSecurityQuestions>();
        Datahandler dh = Datahandler.dataInstance;
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.selectUQuestions);
            while(rs.next()){
                questions.add(new UserSecurityQuestions(new User(new Person(),rs.getString("Username"),rs.getString("Password"),rs.getString("AccessLevel"),rs.getString("Status")),
                              new SecurityQuestions(rs.getString("Question")),rs.getString("Answer")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }
    
    public UserSecurityQuestions selectSpecUserQuestions(){
        UserSecurityQuestions questions = new UserSecurityQuestions();
        Datahandler dh = Datahandler.dataInstance;
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.specificUQuestions(this.getUser().getUsername()));
            while(rs.next()){
                questions = (new UserSecurityQuestions(new User(new Person(),rs.getString("Username"),rs.getString("Password"),rs.getString("AccessLevel"),rs.getString("Status")),
                              new SecurityQuestions(rs.getString("Question")),rs.getString("Answer")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    @Override
    public synchronized int update() {
        String[][] qVals = new String[][]{{"STRING","Answer",this.getAnswer()}};
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performUpdate(TableSpecifiers.USER_QUESTIONS.getTable(), qVals,"(`UserIDFK` = (SELECT `UserIDPK` FROM `tbluser` WHERE `Username` = '"+this.getUser().getUsername()+"')) "
                                                                + "AND (`QuestionIDFK` = (SELECT `QuestionIDPK` FROM `tblsecurityquestions` WHERE `Question` = '"+this.getQuestion().getQuestion()+"'))");
        } catch (SQLException ex) {
            Logger.getLogger(UserSecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int delete() {
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performDelete(TableSpecifiers.USER_QUESTIONS.getTable(), "`UserIDFK` = (SELECT `UserIDPK` FROM `tbluser` WHERE `Username` = '"+this.getUser().getUsername()+"') "
                    + "AND `QuestionIDFK` = (SELECT `QuestionIDPK` FROM `tblsecurityquestions` WHERE `Question` = '"+this.getQuestion().getQuestion()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(UserSecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int insert() {
        Datahandler dh = Datahandler.dataInstance;
        int question = 0;
        int user = 0;
        try {
            ResultSet rs = dh.selectQuery(TableSpecifiers.SEQURITY_QUESTIONS.getTable(), "`Question` = '"+this.getQuestion().getQuestion()+"'");
            while(rs.next()){
                question = rs.getInt("QuestionIDPK");
            }
            rs = dh.selectQuery(TableSpecifiers.USER.getTable(), "`Username` = '"+this.getUser().getUsername()+"'");
            while(rs.next()){
                user = rs.getInt("UserIDPK");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[][] qVals = new String[][]{{"INT","UserIDFK",Integer.toString(user)},{"INT","QuestionIDFK",Integer.toString(question)},{"STRING","Answer",this.getAnswer()}};
        try {
            return dh.performInsert(TableSpecifiers.USER_QUESTIONS.getTable(), qVals);
        } catch (SQLException ex) {
            Logger.getLogger(UserSecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
