Pluggins
--------

> **EXPERIMENTAL**: This is one of the newest features of the API, if you have specific feature-requests for this endpoint please contact platform@outreach.io

#### Query by Attributes

> **SCOPE** `read_plugins`

Query a set of plugins given filters which match a subset of the plugin's attributes.  Filters are conjoint, result set is paginated.

> **GET** `https://api.outreach.io/1.0/mappings?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>
