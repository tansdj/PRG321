/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonManagement;

import bc_stationary_bll.Datahandling;
import bc_stationary_dll.Datahandler;
import bc_stationary_dll.TableSpecifiers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya
 */
public class SecurityQuestions implements Datahandling{
    
    private String question;

    public SecurityQuestions(String question) {
        this.question = question;
    }

    public SecurityQuestions() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.question);
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
        final SecurityQuestions other = (SecurityQuestions) obj;
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<SecurityQuestions> select() {
        ArrayList<SecurityQuestions> questions = new ArrayList<SecurityQuestions>();
        try {
            Datahandler dh = new Datahandler();
            ResultSet rs = dh.selectQuery(TableSpecifiers.SEQURITY_QUESTIONS.getTable());
            while(rs.next()){
                questions.add(new SecurityQuestions(rs.getString("Question")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    @Override
    public int update() {
        String[][] questions = new String[][]{{"STRING","Question",this.getQuestion()}};
        Datahandler dh = new Datahandler();
        try {
            return dh.performUpdate(TableSpecifiers.SEQURITY_QUESTIONS.getTable(), questions,
                "`QuestionIDPK` = (SELECT `QuestionIDPK` FROM `"+TableSpecifiers.SEQURITY_QUESTIONS.getTable()+
                "` WHERE `Question` = '"+this.getQuestion()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(SecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int delete() {
       Datahandler dh = new Datahandler();
        try {
            return dh.performDelete(TableSpecifiers.SEQURITY_QUESTIONS.getTable(), "`Question` = '"+this.getQuestion()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(SecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int insert() {
        String[][] questions = new String[][]{{"STRING","Question",this.getQuestion()}};
        Datahandler dh = new Datahandler();
        try {
            return dh.performInsert(TableSpecifiers.SEQURITY_QUESTIONS.getTable(), questions);
        } catch (SQLException ex) {
            Logger.getLogger(SecurityQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
