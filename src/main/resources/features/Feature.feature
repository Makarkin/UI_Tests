Feature: Estimate payment for Google Cloud using.

  Scenario Outline: Estimate payment for Google Cloud using
  GIVEN user is navigate to Google Cloud website.
  WHEN user click on [See all product] button
  THEN user should be navigated to Products Page.
  WHEN user click on [See pricing] button
  THEN user should be navigated on the Pricing Page.
  WHEN user click on [Calculator] button
  THEN user should be navigated on the Calculator Page.
  WHEN user enter the <propertyValue> in <propertyName>.
  THEN user should see .
    Examples:
      | propertyName                | propertyValue                                                   |
      | Number of instances         | 4
      | Operating System / Software | Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS |
      | Machine Class               | Regular                                                         |
      | Machine type                | n1-standard-8 (vCPUs: 8, RAM: 30GB)                             |
      | Local SSD                   | 2x375 GB                                                        |
      | Datacenter location         | Frankfurt (europe-west3)                                        |
      | Committed usage             | 1 Year                                                          |