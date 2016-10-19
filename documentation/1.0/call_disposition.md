CallDispositions
----------------

> **EXPERIMENTAL**: This is one of the newest features of the API, if you have specific feature-requests for this endpoint please contact platform@outreach.io

#### Query Dispositions

> **SCOPE** `read_call_dispositions`

Query all call dispositions for which the owner of the authorization token has visibility. Results are paginated and ordered by creation time. Querying results by ID is generally unnecessary due to the low cardinality of these records.

> **GET** `https://api.outreach.io/1.0/call_dispositions`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>
