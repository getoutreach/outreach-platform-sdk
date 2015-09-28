Prospects
---------

> **ISSUE**: Currently calls to create prospects won't populate the email field.
>
> **ISSUE**: Not all requried fields are strictly enforced.
>
> **ISSUE**: Error payloads are not yet strictly defined, their payloads differ slightly by error case.
>
> **ISSUE**: Currently when prospects are created they are not assigned an owner.

#### Create

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
        size:     &lt;Number&gt;,                |
        locality: &lt;String&gt;                 |
      },                                   |
      contact: {                           | Required.
        timezone: &lt;String&gt;,                | Olson timezone <a href="http://www.w3.org/TR/timezone/#tzids">format</a>.
        email: {                           | Required.
          personal: &lt;String&gt;,              | W3C RFC822 <a href="http://www.w3.org/Protocols/rfc822/#z8">format</a>.
          work:     &lt;String&gt;               | Required. W3C RFC822 <a href="http://www.w3.org/Protocols/rfc822/#z8">format</a>.
        },                                 |
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
        facebook: &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, Facebook domain.
        linkedin: &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, LinkedIn domain.
        plus:     &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, Google Plus domain.
        quora:    &lt;String&gt;,                | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, Quora domain.
        twitter:  &lt;String&gt;                 | W3C URL <a href="http://www.w3.org/Addressing/URL/url-spec.txt">format</a>, Twitter domain.
      }                                    |
    }                                      |
  }                                        |
}                                          |
</pre>

#### Fetch by ID

Returns a single prospect (if found), from the given prospect identifier; where identifier is a Number.

> **GET** `https://api.outreach.io/1.0/prospects/<Identifier>`

#### Query by Attributes

Query a set of prospects given filters which match a subset of the prospect's attributes.  Filters are conjoint, result set is paginated and based on ascending order of the record's last name attribute.

> **GET** `https://api.outreach.io/1.0/prospects?<Parameters...>`

<pre>
<b>Parameters</b>                                  <b>Constraints</b>
<hr/>
filter[personal/name/first]=&lt;String&gt;       | Optional.
filter[personal/name/last]=&lt;String&gt;        | Optional.
filter[contact/email/work]=&lt;String&gt;        | Optional.
filter[company/name]=&lt;String&gt;              | Optional.
page[number]=&lt;Number&gt;                      | Optional, default: 1.
</pre>

### Update

> Coming soon.