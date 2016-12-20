1. Implement a provider. 
    - throw runtime error when get an instance E: 10min R: 
    - get instance correctly E: 10min R:

2. Implement inject constructors 
    - inject the default constructor(no parameters and public) when no other constructors exist  E: 10min R: 5min
    - inject the annotated constructor 
        - when some parameters not found, report error E: 10min R: 8min
        - inject all the parameters  E: 15min R: 40min C: not parameter.getClass(), should parameter.getType()
    - report error when inject more than one constructors E: 6min R: 7min
    
3. Implement inject fields 
    - if fields is final, report error  E: 10min R: 20min 
    - not final and valid name, inject the field E: 10min R: 7min
    

4. Implement inject methods
    - if abstract, report error E: 10min R: 32min C: why test error?
    - if including type parameters, report error E: 15min R: 23min C: not familiar with reflection with generic types
    - including 0 actual parameters E: 10min R: Long time C: do not know how to inject a method
    - including actual parameters E: 15min R: 45min C: long time to think about the use case of method injection

5. Implement binding to a class in injector
    - class not found, throw error E: 5min R:
    - bind and can get instance E: 5min R: 

6. Implement binding a interface to a specific class in injector
    - interface not found, throw error E: 5min R:
    - class not found, throw error E: 5min R:
    - bind and can get instance E: 10min R:
    

7. Named 
    - the named class not found E: 5min R:
    - bind a interface to the specific class with the name E: 10min R:


8. Qualifier 
    - inject a correct instance withe the right qualifier 
    - throw an error 

9. Singleton 
    - if annotated with singleton, inject the same instance every time. E: 10min
