Users
-----

> **EXPERIMENTAL**: This is one of the newest features of the API, if you have specific feature-requests for this endpoint please contact platform@outreach.io

#### Query by Attributes

> **SCOPE** `read_users`

Query a set of users given filters which match a subset of the user's attributes.  Filters are conjoint, result set is paginated and based on ascending order of the record's last name attribute.

> **GET** `https://api.outreach.io/1.0/users?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
filter[metadata/first_name]=&lt;String&gt;       | Optional.
filter[metadata/last_name]=&lt;String&gt;        | Optional.
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>
