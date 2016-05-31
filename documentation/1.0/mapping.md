Mappings
--------

> **EXPERIMENTAL**: This is one of the newest features of the API, if you have specific feature-requests for this endpoint please contact platform@outreach.io

#### Query by Attributes

Query a set of mappings given filters which match a subset of the mapping's attributes.  Filters are conjoint, result set is paginated.

> **GET** `https://api.outreach.io/1.0/mappings?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
filter[plugin/id]=&lt;String&gt;                 | Required.
filter[plugin/type/id]=&lt;String&gt;            | Required.
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>
