# database-per-tenant

Sample app for the apache/grails-static-website guide [Database per Tenant Multi-Tenancy](https://grails.apache.org/guides/database-per-tenant/8/guide/index.html).

This branch is the Grails 8 companion for **database-per-tenant multi-tenancy**: `DATABASE` mode, `SessionTenantResolver`, separate H2 datasources per tenant (`audi` / `ford`), GORM Data Services with `@CurrentTenant`, and Spock unit + Geb functional tests.

## Layout

| Directory | What it is |
|---|---|
| [`initial/`](initial/) | Grails 8 web starter with multi-tenancy mode, `SessionTenantResolver`, and `audi`/`ford` datasources already configured. Start here and follow the guide. |
| [`complete/`](complete/) | The same starter with `Manufacturer` / `Vehicle` / `Engine` domains, tenant selection UI, `VehicleService`, controllers, views, and tests. |

## Running

Requires JDK 21+.

```bash
cd complete
./gradlew test integrationTest
./gradlew bootRun
```

Then browse to http://localhost:8080/, select a manufacturer, and create vehicles. Switching manufacturers switches the underlying database.

## Branches

| Branch | Grails version |
|---|---|
| `grails8` | Apache Grails 8.0.0-M5 |
| `grails5` | Apache Grails 5 (published guide baseline) |

## Guide prose

Published narrative lives on [grails.apache.org/guides](https://grails.apache.org/guides/) in [apache/grails-static-website](https://github.com/apache/grails-static-website) under `guides/database-per-tenant/`.

## License

Apache License 2.0. See [LICENSE](LICENSE).
