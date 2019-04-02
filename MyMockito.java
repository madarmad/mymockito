package cz.cuni.mff.mymockito;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMockito {

    static class Triple {


        Object o;
        Method m;
        Object returnValue;
    }

    static Triple[] triples = new Triple[10];


    static Method lastMethod;
    static Object lastObject;

    public static <T> MyWhen<T> when(T t) {
        return new MyWhen<>(lastObject, lastMethod);
    }

    static class MyWhen<T> {

        public MyWhen(Object o, Method m) {
        }

        public void thenReturn(T t) {
            triples[0] = new Triple();
        }

    }


    static < T > T mock(Class<T> classObject){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classObject); // parameter of mock method
        enhancer.setCallback(new MyMockClassCallback());
        return (T) enhancer.create(); // T is template arg of mock method
    }

    private static class MyMockClassCallback implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable{
            lastMethod = method;
            lastObject = o;

            for (Triple t : triples) {
                if (t.o == o && t.m == method) {
                    return t.returnValue;
                }
            }

            return methodProxy.invokeSuper(o, args);
        }
    }

}
