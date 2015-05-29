/**
 * Name      : com.malcolm.qme.rest.api.AtomicTokenGenerator.java
 * Date      : 5/28/15
 * Developer : Malcolm
 * Purpose   : Interface to generate token
 */
package com.malcolm.qme.rest.api;

/**
 * @Author: malcolm
 */
public interface AtomicTokenGenerator {
    /**
     * Generate Password Reset Token
     * @return Long
     */
    Long generateUniqueResetToken();
}
