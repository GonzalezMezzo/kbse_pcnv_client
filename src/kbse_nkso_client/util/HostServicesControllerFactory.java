/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.util;

import java.lang.reflect.Constructor;
import javafx.application.HostServices;
import javafx.util.Callback;

/**
 *
 * @author Philipp
 */
//https://stackoverflow.com/questions/33094981/javafx-8-open-a-link-in-a-browser-without-reference-to-application
public class HostServicesControllerFactory implements Callback<Class<?>,Object> {

    private final HostServices hostServices ;

    public HostServicesControllerFactory(HostServices hostServices) {
        this.hostServices = hostServices ;
    }

    @Override
    public Object call(Class<?> type) {
        try {
            for (Constructor<?> c : type.getConstructors()) {
                if (c.getParameterCount() == 1 && c.getParameterTypes()[0] == HostServices.class) {
                    return c.newInstance(hostServices) ;
                }
            }
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
