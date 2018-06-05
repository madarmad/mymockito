# Java test

## Assignment

Implement very simplified version of Mockito <http://site.mockito.org>.

### What to implement
Methods to implement:
* `mock` – creates mocked object (proxy)
* `when` – returns object with one method `thenReturn` which has to 
take the same parameter as `when` method call 
 
### Usage
```java
MyClass m = MyMockito.mock(MyClass.class);
MyMockito.when(m.someMethod()).thenReturn(someValue);
m.someMethod(); // returns `someValue`
```

### How to create mock object
Use library CGLib <http://mvnrepository.com/artifact/cglib/cglib/3.2.6>

```java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(classObject); // parameter of mock method
enhancer.setCallback(new MyMockClassCallback());
return (T) enhancer.create(); // T is template arg of mock method
```

where `MyMockClassCallback` is a class which looks like this:

```java
private static class MyMockClassCallback implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) { 
        return methodProxy.invokeSuper(o, args); 
    }
}
```

### What to be aware of
It is easy to call different method on `o` in `intercept()` which can 
cause `StackOverflowError`.

### How to run tests
```bash
mvn test
```

or simply run from your IDE :\)


Good luck! :\)