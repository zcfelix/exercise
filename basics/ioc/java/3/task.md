1. Implement a provider. 
    - throw runtime error when get an instance E: 10min R:
    - get instance correctly E: 10min R:

2. Implement inject constructors 
    - inject the default constructor(no parameters and public)  E: 10min R:
    - inject the annotated constructor
        - when some parameters not found, report error E: 10min R:
        - inject all the parameters  E: 15min R: 
    - report error when inject more than one constructors E: 6min R:
    
3. Implement inject fields 
    - if fields is final, report error  E: 10min R: 
    - not final and valid name, inject the field E: 10min R: 
    

4. Implement inject methods
    - if abstract, report error E: 10min R: 
    - if including type parameters, report error E: 15min R:
    - including 0 actual parameters E: 10min
    - including actual parameters E: 15min
    
5. Qualifier 
    - inject a correct instance withe the right qualifier 
    - throw an error 

6. Named 

7. Singleton 
    - if annotated with singleton, inject the same instance every time. E: 10min
