Sequences
---------

#### Query Sequences

> **SCOPE** `read_sequences`

Query all sequences for which the owner of the authorization token has visibility.  Results are paginated and ordered by ascending name.

> **GET** `https://api.outreach.io/1.0/sequences?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>

<pre>
<b>Response payload</b>
<hr/>
{
  data: [{
    id: &lt;String&gt;,
    type: "sequence",
    attributes: {
      name: &lt;String&gt;
    }
  }, ...],
  meta: {
    page: {
      current: &lt;Number&gt;,
      entries: &lt;Number&gt;,
      maximum: &lt;Number&gt;
    },
    results: {
      total: &lt;Number&gt;
    }
  },
  links: {
    prev: "https://api.outreach.io/1.0/sequences/...",
    next: "https://api.outreach.io/1.0/sequences/..."
  }
}
</pre>

#### Update

> **SCOPE** `update_sequences`

Additively associate existing prospects with an existing sequence.

> **PATCH** `https://api.outreach.io/1.0/sequences/<Identifier>`

<pre>
<b>Input payload</b>                               <b>Constraints</b>
<hr/>
{                                          |
  data: {                                  | Required.
    relationships: {                       | Required.
      prospects: [{                        | Required, currently no upper limit on prospects set size (minimum: single entry).
        data: {                            | Required.
          id: &lt;String&gt;                     | Required, associated type is implicitly "prospect"
        }                                  |
      }, ...]                              |
    }                                      |
  }                                        |
}                                          |
</pre>

<pre>
<b>Response payload</b>
<hr/>
{
  data: {
    id: &lt;String&gt;,
    type: "batch",
    attributes: {
      created: &lt;String&gt;,
      updated: &lt;String&gt;
    }
  },
  links: {
    self: "https://app.outreach.io/..."
  }
}
</pre>
