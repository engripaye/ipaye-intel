# Security model

## Non-negotiable production controls

- Replace bootstrap organization creation with OIDC and verified memberships.
- Hash API keys with a memory-hard KDF and display them once.
- Encrypt sensitive asset content with per-tenant envelope keys.
- Apply PostgreSQL row-level security in addition to application filters.
- Put active network scanners in isolated accounts/VPCs with strict egress.
- Resolve DNS and validate every redirect to prevent SSRF and rebinding.
- Block loopback, link-local, RFC1918, metadata, and control-plane ranges
  unless a customer has explicitly configured a private scanning agent.
- Sign scanner images, generate SBOMs, pin dependencies, and verify provenance.
- Keep raw credentials out of findings, logs, traces, analytics, and support
  tooling. The secret scanner emits only redacted fingerprints.
- Audit all access to assets, scans, findings, integrations, and suppressions.

## Threat boundaries

Asset content is untrusted. Parsers must have size, depth, time, and memory
limits. Scanner workers should run read-only as non-root with no ambient cloud
credentials. Cloud connectors must use customer-scoped, read-only roles with
external IDs and short-lived sessions.

## Responsible operation

Only scan assets and systems the customer is authorized to assess. Passive
configuration analysis is the default. Active checks require explicit scope,
rate limits, customer verification, and a complete audit trail.
