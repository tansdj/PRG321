/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonManagement;

/**
 *
 * @author Tanya 
 * This enum provides a list of different methods available in the
 * BLL Classes that can be requested by the client, in order to receive the
 * appropriate result from the server.
 */
public enum PersonManagement_Methods {
    DEP_SELECT_ALL(1),
    DEP_DELETE(2),
    DEP_INSERT(3),
    PERSON_SELECT_ALL(4),
    PERSON_SELECT_SPECIFIC(5),
    PERSON_UPDATE(6),
    PERSON_DELETE(7),
    PERSON_INSERT(8),
    SQ_SELECT_ALL(9),
    SQ_UPDATE(10),
    SQ_DELETE(11),
    SQ_INSERT(12),
    USER_SELECT_ALL(13),
    USER_SELECT_PENDING(14),
    USER_SELECT_SPEC(15),
    USER_UPDATE(16),
    USER_DELETE(17),
    USER_INSERT(18),
    USER_TEST_LOGIN(19),
    USER_TEST_EXISTING(20),
    USQ_SELECT_ALL(21),
    USQ_SELECT_SPEC(22),
    USQ_UPDATE(23),
    USQ_DELETE(24),
    USQ_INSERT(25);

    public int methodIdentifier;

    private PersonManagement_Methods(int methodIdentifier) {
        this.methodIdentifier = methodIdentifier;
    }

}
