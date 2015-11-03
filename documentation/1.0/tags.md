Tags
----

> **NOTICE**: This endpoint isn't yet supported in production, if you'd like to know when this functionality goes live please contact platform@outreach.io

#### Query Tags (Autocomplete)

Query tags which have the closest match to the given input filter, which the owner of the authorization token has visibility.  Results are not paged, though the maxmimum result-set size is configurable.  The intended use-case here is auto-completing tags to associate with a prospect.  The prospect payload accepts the attribute.name value for tags; and the values are case-sensitive.

> **GET** `https://api.outreach.io/1.0/tags?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
page[size]=&lt;Number&gt;                        | Optional, default: 10, maximum: 25.
</pre>

<pre>
<b>Response payload</b>
<hr/>
{
  data: [{
    id: &lt;String&gt;,
    type: "tag",
    attributes: {
      name: &lt;String&gt;
    }
  }, ...]
}
</pre>
