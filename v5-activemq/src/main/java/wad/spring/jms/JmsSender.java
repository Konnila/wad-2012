/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.spring.jms;

/**
 *
 * @author arto
 */
public interface JmsSender {

    void send(final String message);
    
}
