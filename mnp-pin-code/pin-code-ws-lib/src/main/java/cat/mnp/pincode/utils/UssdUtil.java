/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.utils;

import java.util.regex.Pattern;

/**
 *
 * @author HP-CAT
 */
public class UssdUtil {

    public static final String STAR_CODE = "starCode";
    public static final String ITEM_ID = "itemId";
    public static final Pattern USSD_PATTERN = Pattern.compile("^\\*(?<" + STAR_CODE + ">([^\\*]+))\\*(?<" + ITEM_ID + ">([^\\#]+))#$");

}
