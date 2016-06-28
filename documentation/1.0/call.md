Calls
-----

> **EXPERIMENTAL**: This is one of the newest features of the API, if you have specific feature-requests for this endpoint please contact platform@outreach.io

#### Fetch by ID

Returns a single account (if found), from the given account identifier; where identifier is a Number.

> **GET** `https://api.outreach.io/1.0/calls/<Identifier>`

#### Query by Attributes

Query a set of calls given filters which match a subset of the call's attributes, specifically the user who sourced the call and pagination metadata.

> **GET** `https://api.outreach.io/1.0/calls?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
filter[user/id]=&lt;Number&gt;                   | Optional, default: all users in the org.
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>
