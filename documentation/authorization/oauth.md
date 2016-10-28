Authorization
-------------

Currently the Outreach Platform only supports 3rd party OAuth access, which allows you as an application developer to request the ability to perform actions on behalf of a mutual customer.  For more information on OAuth 2.0 (IETF RFC 6749), please read the [specification](https://tools.ietf.org/html/rfc6749). Another helpful document around OAuth2 is from [DigitalOcean](https://www.digitalocean.com/community/tutorials/an-introduction-to-oauth-2) This document provides a high-level view of the authorization flow, as well as the various endpoints which will grant you access to the public-facing API.

### Provision an Application

> **NOTE**: This is currently locked-down for the duration of the BETA period
>           and can only be accessed by an Outreach administrator, please reach
>           out to the platform@outreach.io team if you'd like to request access.

You'll first need to generate a set of application credentials representing you, the relying party.  Send an email to platform@outreach.io with the following information:
* **Application Name** which will be shown to customers during authorization, this should be meaningful and informative.
* **Redirect URI**(s), which are a whitelist of return-to addresses which you own; following the authorization flow your customers will be redirected here.
* **Scope**(s), which are a space delimited list of the following permissions (of which your authorization flow may request a subset of your application's available scopes):
  * create_prospects
  * read_prospects
  * update_prospects
  * read_sequences
  * update_sequences
  * read_tags
  * read_accounts
  * create_accounts
  * read_activities
  * read_mailings
  * read_mappings
  * read_plugins
  * read_users
  * read_calls

Once this is complete you'll be given an application identifier and secret.  No Outreach employee will _ever_ ask you for this information, keep these credentials secure!

### Request an Authorization Code

In order to perform actions on a customer's behalf you'll first need to request permission from that customer.  Simply send them to the Authorization server given the following URL format:

<pre>
https://api.outreach.io/oauth/authorize?client_id=<i>&lt;Application_Identifier&gt;</i>
                                       &redirect_uri=<i>&lt;Application_Redirect_URI&gt;</i>
                                       &response_type=code
                                       &scope=<i>&lt;Permission1 Permission2 ...&gt;</i>
</pre>

> **NOTE**: The redirect URI and scope query-parameter values in the above endpoint must be URL-encoded.

When the customer clicks 'Authorize' they'll be redirected to your Application_Redirect_URI (which must be in the whitelist specified when you created the application) with a _code_ query parameter which contains the authorization code associated with that customer.

> **NOTE**: If you require other query parameters be maintained accross the
>           authorization flow you may optionally include a &state parameter
>           which will be included as part of the query of the redirect URI.

### Exchange the Authorization Code for an Access Token

You can't use an authorization code to make API requests, yet.  Authorization codes are short-lived and not considered to be secure, once you recieve one you'll need to request an access credential given the code and your application's identifier and secret credentials.  Make a request to the following endpoint:

<pre>
POST https://api.outreach.io/oauth/token

<b>parameters</b>:
  client_id=<i>&lt;Application_Identifier&gt;</i>
  client_secret=<i>&lt;Application_Secret&gt;</i>
  redirect_uri=<i>&lt;Application_Redirect_URI&gt;</i>
  grant_type=authorization_code
  code=<i>&lt;Authorization_Code&gt;</i>
</pre>

This will return a json payload with _access\_token_, _refresh\_token_, and _expires\_in_ attributes.  The access token can be used for API requests but will expire after the returned TTL (or if the customer revokes their authorization grant); following which the access token is no longer valid and must be refreshed given the returned refresh token.  In order to generate a new access credential following expiration, you'll need to make a similar request as above but instead of providing the authorization code you provide the refresh token:

<pre>
POST https://api.outreach.io/oauth/token

<b>parameters</b>:
  client_id=<i>&lt;Application_Identifier&gt;</i>
  client_secret=<i>&lt;Application_Secret&gt;</i>
  redirect_uri=<i>&lt;Application_Redirect_URI&gt;</i>
  grant_type=refresh_token
  refresh_token=<i>&lt;Refresh_Token&gt;</i>
</pre>

### Make an Authorized Request

You now have all of the information and credentials necessary to make an authorized API request on behalf of a customer.  Simply make a request to the desired API endpoint and include the following [request header](http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.8):

<pre>
Authorization=Bearer <i>&lt;Access_Token&gt;</i>;
</pre>
