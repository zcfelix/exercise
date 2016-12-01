# Mobile Recharge API

- post /cards
    - 201   E: 10min R: 15min JerseyTest
    - 400   E: 10min R: 30min Uniqueness Validator
- get  /cards/{cid}
    - 404   E: 10min R: 11min Write wrong method of cardRepository in test 
    - 200   R: 15min R: 65min ApiSupport 
- post /cards/{cid}/packages
    - 201   E: 15min R: 42min Error package of Map
    - 400   E: 15min R: 7min
- get  /cards/{cid}/packages
    - 200   E: 15min R: 13min
