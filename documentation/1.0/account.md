Accounts
--------

> **EXPERIMENTAL**: This is one of the newest features of the API, if you have specific feature-requests for this endpoint please contact platform@outreach.io

#### Create

Create a account given the posted JSON payload, owned by the user associated with the OAuth credential.

> **POST** `https://api.outreach.io/1.0/accounts`

<pre>
<b>Payload</b>                                     <b>Constraints</b>
<hr/>
{                                          |
  data: {                                  | Required.
    attributes: {                          | Required.
      company: {                           |
        name:         &lt;String&gt;,            |
        natural_name: &lt;String&gt;,            |
        domain:       &lt;String&gt;,            |
        website:      &lt;Number&gt;,            |
        type:         &lt;String&gt;,            |
        industry:     &lt;String&gt;             |
      },                                   |
      metadata: {                          |
        description: &lt;String&gt;,             |
        tags: [                            |
          &lt;String&gt;, ...                    |
        ]                                  |
      }                                    |
    }                                      |
  }                                        |
}                                          |
</pre>

#### Fetch by ID

Returns a single account (if found), from the given account identifier; where identifier is a Number.

> **GET** `https://api.outreach.io/1.0/accounts/<Identifier>`

#### Query by Attributes

Query a set of accounts given filters which match a subset of the account's attributes.  Filters are conjoint, result set is paginated and based on ascending order on the record's name attribute.

> **GET** `https://api.outreach.io/1.0/accounts?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
filter[company/name]=&lt;String&gt;              | Optional.
filter[company/domain]=&lt;String&gt;            | Optional.
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>
