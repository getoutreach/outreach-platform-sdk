Calls
-----

> **EXPERIMENTAL**: This is one of the newest features of the API, if you have specific feature-requests for this endpoint please contact platform@outreach.io

#### Create

> **SCOPE** `create_calls`

Create a call record given the posted JSON payload, owned by the user associated with the OAuth credential.

> **POST** `https://api.outreach.io/1.0/calls`

<pre>
<b>Payload</b>                                     <b>Constraints</b>
<hr/>
{                                          |
  data: {                                  | Required.
    attributes: {                          | Required.
      connection: {                        |
        from:     &lt;String&gt;,                |
        to:       &lt;String&gt;,                |
        outbound: &lt;Boolean&gt;                | Required.
      },                                   |
      purpose: {                           |
        id: &lt;String&gt;                       | Type is implicitly "CallPurpose".
      },                                   |
      disposition: {                       |
        id: &lt;String&gt;                       | Type is implicitly "CallDisposition".
      },                                   |
      prospect: {                          |
        id: &lt;String&gt;                       | Type is impliticly "Prospect".
      },                                   |
      metadata: {                          |
        note: &lt;String&gt;,                    |
      }                                    |
    }                                      |
  }                                        |
}                                          |
</pre>

#### Fetch by ID

> **SCOPE** `read_calls`

Returns a single account (if found), from the given account identifier; where identifier is a Number.

> **GET** `https://api.outreach.io/1.0/calls/<Identifier>`

#### Query by Attributes

> **SCOPE** `read_calls`

Query a set of calls given filters which match a subset of the call's attributes, specifically the user who sourced the call and pagination metadata.

> **GET** `https://api.outreach.io/1.0/calls?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
filter[user/id]=&lt;Number&gt;                   | Optional, default: all users in the org.
filter[metadata/created/before]=&lt;String&gt;   | Optional. Format: "yyyy-MM-dd" or "yyyy-MM-ddTHH:mm".
filter[metadata/created/after]=&lt;String&gt;    | Optional. Format: "yyyy-MM-dd" or "yyyy-MM-ddTHH:mm".
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>
