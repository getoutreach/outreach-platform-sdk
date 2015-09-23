Authorization
-------------

Currently the Outreach Platform only supports 3rd party OAuth access, which allows you as an application developer to request the ability to perform actions on behalf of a mutual customer.  For more information on the OAuth 2.0 (IETF RFC 6749), please read the [specification](https://tools.ietf.org/html/rfc6749).  This document provides a high-level view of the authorization flow, as well as the various endpoints which will grant you access to the public-facing API.

### Provision an Application

> **NOTE**: This is currently locked-down for the duration of the BETA period
>           and can only be accessed by an Outreach administrator, please reach
>           out to the sales@ team if you'd like to request access.

You'll first need to generate a set of application credentials representing you, the relying party.  Go to https://api.outreach.io/oauth/applications/new and enter the following information:
* Application name which will be shown to customers during authorization, this should be meaningful and informative.
* Redirect URI(s), which are a whitelist of return-to addresses which you own; following the authorization flow your customers will be redirected here.
* Scopes, which are a space delimited list of the following permissions (of which your authorization flow may request a subset of your application's available scopes):
..* create_prospect
..* read_prospect
..* update_prospect
..* create_sequence
..* read_sequence
..* update_sequence

Once this is complete you'll be given an application identifier and secret.  No Outreach employee will _ever_ ask you for this information, keep these credentials secure!

### Request an Authorization Code

In order to perform actions on a customer's behalf you'll first need to request permission from that customer.  Simply send them to the Authorization server given the following URL format:

     https://api.outreach.io/oauth/authorize?client_id=<_Application\_Identifier_>&redirect_uri=<_Application\_Redirect\_URI_>&response_type=code&scope=<_Permission1_ _Permission2_ _..._>

When the customer clicks 'Authorize' they'll be redirected to your Application_Redirect_URI (which must be in the whitelist specified when you created the application) with a _code_ query parameter which contains the authorization code associated with that customer.

> **NOTE**: If you require other query parameters be maintained accross the
>           authorization flow you may optionally include a &state parameter
>           which will be included as part of the query of the redirect URI.

### Exchange the Authorization Code for an Access Token

You can't use an authorization code to make API requests, yet.  Authorization codes are short-lived and not considered to be secure, once you recieve one you'll need to request an access credential given the code and your application's identifier and secret credentials.  Make a request to the following endpoint:

     POST https://api.outreach.io/oauth/token
     
     client_id=<_Application\_Identifier_>
     client_secret=<_Application\_Secret_>
     redirect_uri=<_Application\_Redirect\_URI_>
     grant_type=authorization\_code
     code=<_Authorization\_Code_>

This will return a json payload with _access\_token_, _refresh\_token_, and _expires_ attributes.  The access token can be used for API requests but will expire after the returned TTL (or if the customer revokes their authorization grant); following which the access token is no longer valid and must be refreshed given the returned refresh token.  In order to generate a new access credential following expiration, you'll need to make a similar request as above but instead of providing the authorization code you provide the refresh token:

     POST https://api.outreach.io/oauth/token
     
     client_id=<_Application\_Identifier_>
     client_secret=<_Application\_Secret_>
     redirect_uri=<_Application\_Redirect\_URI_>
     grant_type=refresh_token
     refresh_token=<_Refresh\_Token_>

### Make an Authenticated Request

You now have all of the information and credentials necessary to make an authenticated API request.  Simply make a request to the desired API endpoint and include the following [request header](http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.8):

     Authorization=Bearer <_Access\_Token_>;
