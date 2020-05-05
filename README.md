This is a quick and dirty implementation of token exchange by spring cloud gateway. You can watch more about it here https://www.youtube.com/watch?v=RRMO4oNptoQ

## Flow
1. Authorized user requests a resource on example service through the gateway
2. Gateway takes the token provided by the user and requests a special token from identity service.
3. Gateway replaces token user provider token by identity provided token, and passes the request downstream.
## Authorization
Provided token in Authorization header will be replaced at the gateway by exchanged token

| Provided  | Exchanged  |   
|---------|---------|
| esEKSc  | pG07Ks  |   
| QF6KIz  | EEPoh4  |   
| 8HHt1t  | SImTSO  |   
| BhWimL  | 7fIU4n  |   

## Filter
Entire magic happens inside a ExchangeFilter class.