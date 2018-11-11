/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

import java.util.ArrayList;

/**
 *
 * @author Tanya This interface enforces all bll classes to implement the basic
 * CRUD operations needed to maintain data in the database.
 */
public interface Datahandling {

    ArrayList<?> select();

    int update();

    int delete();

    int insert();
}
