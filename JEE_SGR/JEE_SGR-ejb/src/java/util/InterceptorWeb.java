/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author duran
 * @version 1.0
 */
public class InterceptorWeb {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        
        String nameClass = context.getMethod().getDeclaringClass().getName();
        String methodClass = context.getMethod().getName();
        String name = context.getTarget().getClass().getName();
                
        try {
           
            System.out.println("Before: " + nameClass + "  " + name + "  " + methodClass);
         
            return  context.proceed();
        } catch (Exception e) {
            throw e;
        }
    }

}
