/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.spring.jms;

import javax.jms.JMSException;

/**
 *
 * @author arto
 */
public interface JmsReader {

    String read() throws JMSException;
    
}
