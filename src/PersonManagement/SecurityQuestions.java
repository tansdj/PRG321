/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonManagement;

import java.util.Objects;

/**
 *
 * @author Tanya
 */
public class SecurityQuestions {
    
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
        return "SecurityQuestions{" + "question=" + question + '}';
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

}
