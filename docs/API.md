# API walkthrough

All tenant endpoints require `X-Organization-Id`.

```bash
# 1. Create project
curl -X POST http://localhost:8080/api/v1/projects \
  -H "X-Organization-Id: $ORG_ID" \
  -H "Content-Type: application/json" \
  -d '{"name":"Payments","slug":"payments"}'

# 2. Register asset
curl -X POST http://localhost:8080/api/v1/projects/$PROJECT_ID/assets \
  -H "X-Organization-Id: $ORG_ID" \
  -H "Content-Type: application/json" \
  --data @contracts/examples/api-spec-asset.json

# 3. Queue scan
curl -X POST http://localhost:8080/api/v1/projects/$PROJECT_ID/scans \
  -H "X-Organization-Id: $ORG_ID" \
  -H "Content-Type: application/json" \
  -d "{\"assetId\":\"$ASSET_ID\"}"

# 4. Read findings
curl http://localhost:8080/api/v1/projects/$PROJECT_ID/findings \
  -H "X-Organization-Id: $ORG_ID"
```

OpenAPI documentation is generated at `/v3/api-docs`.
