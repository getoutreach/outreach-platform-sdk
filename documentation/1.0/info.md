Info
----

#### Fetch request metadata

Query metadata associated with authorization token, such as associated customer email, api versioning and request limits.

> **GET** `https://api.outreach.io/1.0/info`

<pre>
<b>Response payload</b>
<hr/>
{
  meta: {
    user: {
      email: &lt;String&gt;
    },
    org: {
      guid: &lt;String&gt;
    },
    api: {
      application: &lt;String&gt;,
      permissions: [ &lt;String&gt;, ... ],
      requests: {
        current: &lt;Number&gt;,
        maximum: &lt;Number&gt;
      },
      versions: {
        self: &lt;String&gt;,
        last: &lt;String&gt;
      }
    }
  }
}
</pre>
