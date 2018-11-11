/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

/**
 *
 * @author Tanya 
 * This enum provides a list of different methods available in
 * the BLL Classes that can be requested by the client, in order to receive the
 * appropriate result from the server.
 */
public enum ProductManagement_Methods {

    CAT_SELECT_ALL(26),
    CAT_INSERT(27),
    CAT_DELETE(28),
    MODEL_SELECT_ALL(29),
    MODEL_DELETE(30),
    MODEL_INSERT(31),
    ORDER_SELECT_ALL(32),
    ORDER_SELECT_USER(33),
    ORDER_SELECT_USER_OPEN(34),
    ORDER_UPDATE(35),
    ORDER_DELETE(36),
    ORDER_INSERT(37),
    ORDER_ITEMS_INSERT(38),
    PRODUCT_SELECT_ALL(39),
    PRODUCT_SELECT_SPEC(40),
    PRODUCT_UPDATE(41),
    PRODUCT_DELETE(42),
    PRODUCT_INSERT(43),
    STOCK_SELECT_ALL(44),
    STOCK_SELECT_SPEC(45),
    STOCK_UPDATE(46),
    STOCK_DELETE(47),
    STOCK_INSERT(48),
    UR_SELECT_ALL(49),
    UR_SELECT_BACKORDER(50),
    UR_SELECT_PRODUCT_BACKORDER(51),
    UR_SELECT_SPEC_USER_REQ(52),
    UR_SELECT_PRODUCTS_ONREQ(53),
    UR_UPDATE(54),
    UR_INSERT(55),
    UR_DELETE(56),
    ORDER_SELECTUSERS_WITH_OPEN_ORDERS(57),
    UR_UPDATE_UNPROCESSED(58),
    UR_SELECT_AWAITING_PURCHASE(59),
    UR_UPDATE_AWAITING_PURCHASE(60);

    private int methodIdentifier;

    public void setMethodIdentifier(int methodIdentifier) {
        this.methodIdentifier = methodIdentifier;
    }

    private ProductManagement_Methods(int methodIdentifier) {
        this.methodIdentifier = methodIdentifier;
    }

}
