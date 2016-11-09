Prospects
---------

> **ISSUE**: Not all requried fields are strictly enforced.
>
> **ISSUE**: Error payloads are not yet strictly defined, their payloads differ slightly by error case.

#### Create

> **SCOPE** `create_prospects`

Create a prospect given the posted JSON payload, owned by the user associated with the OAuth credential.

> **POST** `https://api.outreach.io/1.0/prospects`

<pre>
<b>Payload</b>                                     <b>Constraints</b>
<hr/>
{                                          |
  data: {                                  | Required.
    attributes: {                          | Required.
      address: {                           |
        city:    &lt;String&gt;,                 |
        state:   &lt;String&gt;,                 |
        country: &lt;String&gt;,                 |
        street:  [ &lt;String&gt;, &lt;String&gt; ],   | Second element is optional.
        zip:     &lt;Number&gt;                  | 0-99999.
      },                                   |
      company: {                           |
        name:     &lt;String&gt;,                |
        type:     &lt;String&gt;,                |
        industry: &lt;String&gt;,                |
        size:     &lt;Number&gt;,                |
        locality: &lt;String&gt;                 |
      },                                   |
      contact: {                           | Required.
        timezone: &lt;String&gt;,                | Olson timezone <a href="http://www.w3.org/TR/timezone/#tzids">format</a>.
        email: &lt;String&gt;,                   | Required. W3C RFC822 <a href="http://www.w3.org/Protocols/rfc822/#z8">format</a>.
        phone: {                           |
          personal: &lt;String&gt;,              |
          work:     &lt;String&gt;               |
        }                                  |
      },                                   |
      personal: {                          | Required.
        name: {                            | Required.
          first: &lt;String&gt;,                 | Required.
          last:  &lt;String&gt;                  |
        },                                 |
        gender:     &lt;String&gt;,              | "male" or "female".
        occupation: &lt;String&gt;,              |
        title:      &lt;String&gt;               |
      },                                   |
      social: {                            |
        website:  &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>.
        facebook: &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, Facebook domain.
        linkedin: &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, LinkedIn domain.
        plus:     &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, Google Plus domain.
        quora:    &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, Quora domain.
        twitter:  &lt;String&gt;                 | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, Twitter domain.
      },                                   |
      metadata: {                          |
        opted_out: &lt;Boolean&gt;,              |
        source: &lt;String&gt;,                  |
        notes: [                           |
          &lt;String&gt;,                        |
          &lt;String&gt;                         |
        ],                                 |
        tags: [                            |
          &lt;String&gt;, ...                    |
        ],                                 |
        custom: [                          |
          &lt;String&gt;,                        | Maximum 255 characters.
          ...                              | Indices correspond to custom1 through custom15 fields, respectively.
        ]                                  |
      }                                    |
    }                                      |
  }                                        |
}                                          |
</pre>

#### Fetch by ID

> **SCOPE** `read_prospects`

Returns a single prospect (if found), from the given prospect identifier; where identifier is a Number.

> **GET** `https://api.outreach.io/1.0/prospects/<Identifier>`

#### Query by Attributes

> **SCOPE** `read_prospects`

Query a set of prospects given filters which match a subset of the prospect's attributes.  Filters are conjoint, result set is paginated and based on ascending order of the record's last name attribute.

> **GET** `https://api.outreach.io/1.0/prospects?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
filter[personal/name/first]=&lt;String&gt;       | Optional.
filter[personal/name/last]=&lt;String&gt;        | Optional.
filter[contact/email]=&lt;String&gt;             | Optional. ECMA URICompontent <a href="http://www.ecma-international.org/ecma-262/6.0/#sec-encodeuricomponent-uricomponent">encoded</a>.
filter[company/name]=&lt;String&gt;              | Optional.
filter[metadata/updated/before]=&lt;String&gt;   | Optional. Format: "yyyy-MM-dd" or "yyyy-MM-ddTHH:mm".
filter[metadata/updated/after]=&lt;String&gt;    | Optional. Format: "yyyy-MM-dd" or "yyyy-MM-ddTHH:mm".
filter[metadata/opted_out]=&lt;Boolean&gt;       | Optional.
page[number]=&lt;Number&gt;                      | Optional, default: 1.
page[size]=&lt;Number&gt;                        | Optional, default: 50, maximum: 50.
</pre>

### Update

> **SCOPE** `update_prospects`

Modifies a given prospect with a subset of attributes.

> **PATCH** `https://api.outreach.io/1.0/prospects/<Identifier>`

> Payload attributes are a subset of prospect creation, all fields are optional and at least one attribute should be specified.
