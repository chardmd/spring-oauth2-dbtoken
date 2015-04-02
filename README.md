# spring-oauth2-dbtoken
Spring OAuth2 application that generates and revoke tokens in the database. It allows you to protect your Rest-API by token based authentication.

<b>Request a token:</b>

*`/oauth/token?grant_type=client_credentials&client_id=[clientId]&client_secret=[clientSecret]`*

Sample Usage:
  
    localhost:8080/oauth2/oauth/token?grant_type=client_credentials&client_id=admin@test.com
                                                                      &client_secret=admin123

<b>Revoke access token:</b>

*`/rest/api/revoke_token`*

Sample Usage:

    * set Authorization Header: Bearer <accessToken>
    localhost:8080/oauth2/rest/api/revoke_token
