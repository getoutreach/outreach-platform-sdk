Outreach Platform API
-----------------

Outreach is a communication workflow solution for enterprise sales teams, which can be used as a standalone application, or in conjunction with [Outreach Everywhere](https://outreach.io/everywhere/) (our Chrome extension) to layer functionality on top of other services, including Salesforce, Linkedin, Gmail and more.

As Outreach customer data is stored independently from other services, customers my choose to exchange data between their Outreach database and external services, including but not limited to:
- Their own proprietary databases
- Third party CRMs (other than SFDC, to which Outreach already provides an integration)
- Third party data sources

The Outreach Platform API is currently in use by individual customers, and our first official integration partner, [Datanyze](http://www.datanyze.com/).

Currently, the Outreach platform API can be used to read/write data on models within Outreach, currently including:
- The prospects model
- The sequences model

This package contains documentation and example code for Outreach's public-facing API. Please take a look around and when you are ready to get started, please request access by filling out our [API Contact Form](http://goo.gl/forms/RWk35DeZAK).

At this time, provisioning an application with an application ID and secret is performed by the Outreach platform team, but will be self provisioned in the future. When you are ready to get started, you will want to provide us with the scopes required, and a redirect URI.  Full documentation for required application attributes can be found [here](https://github.com/getoutreach/outreach-platform-sdk/blob/master/documentation/authorization/oauth.md).

If you would like more information on our technology or how Outreach can integrate with your existing sales infrastructure, please get in touch with our [platform team](mailto:platform@outreach.io).
