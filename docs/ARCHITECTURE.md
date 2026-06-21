# Architecture

## Service boundaries

### Control plane (`apps/control-plane`)

The Java service is the system of record and public API. It validates tenant
boundaries, stores scan assets, creates scan jobs transactionally, and serves
results. It must never execute untrusted plugins or make arbitrary outbound
requests.

### Scanner worker (`apps/scanner-worker`)

The Python worker owns detection logic. Workers claim scans with PostgreSQL
row locks, receive a five-minute lease, and can be scaled horizontally. A
stale job may be reclaimed up to three times.

### Data model

- Organization: billing and security boundary.
- Project: groups one product or engineering system.
- Asset: versionable scan input such as an OpenAPI document or cloud snapshot.
- Scan: immutable execution request and lifecycle record.
- Finding: normalized, fingerprinted result from a scan.

Every tenant-owned table carries `organization_id`; every query in the API
includes it. Production should additionally enable PostgreSQL row-level
security as defense in depth.

## Evolution path

1. Add OIDC authentication, API keys, memberships, and RBAC.
2. Store large encrypted artifacts in object storage rather than JSONB.
3. Introduce signed, versioned scan manifests and sandboxed worker pools.
4. Replace database polling with SQS/Kafka when queue throughput justifies it.
5. Add integrations (GitHub, GitLab, Jira, Slack) through an outbox.
6. Add finding lifecycle, suppression policy, SLA, and risk acceptance.
7. Add cloud-provider read-only connectors and incremental inventory.

## Reliability

Job creation is committed in the same transaction as the scan record.
Workers use `FOR UPDATE SKIP LOCKED`, leases, bounded attempts, idempotent
finding fingerprints, and short transactions. Metrics should track queue age,
claim latency, completion duration, retries, failures, and findings by rule.
