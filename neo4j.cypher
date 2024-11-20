// CREATE CONSTRAINTS FOR UNIQUENESS
CREATE CONSTRAINT ON (p:Product) ASSERT p.product_id IS UNIQUE;
CREATE CONSTRAINT ON (p:Product) ASSERT p.product_name IS UNIQUE;

CREATE CONSTRAINT ON (vc:VectorCategory) ASSERT vc.vector_category_id IS UNIQUE;
CREATE CONSTRAINT ON (vc:VectorCategory) ASSERT vc.category_name IS UNIQUE;

CREATE CONSTRAINT ON (v:AttackVector) ASSERT v.vector_id IS UNIQUE;
CREATE CONSTRAINT ON (v:AttackVector) ASSERT v.name IS UNIQUE;

CREATE CONSTRAINT ON (c:Country) ASSERT c.country_code IS UNIQUE;
CREATE CONSTRAINT ON (c:Country) ASSERT c.country_name IS UNIQUE;

CREATE CONSTRAINT ON (g:Geolocation) ASSERT g.geolocation_id IS UNIQUE;
CREATE CONSTRAINT ON (g:Geolocation) ASSERT g.ip_address IS UNIQUE;

CREATE CONSTRAINT ON (t:Threat) ASSERT t.threat_id IS UNIQUE;
CREATE CONSTRAINT ON (t:Threat) ASSERT t.name IS UNIQUE;

CREATE CONSTRAINT ON (i:Industry) ASSERT i.industry_id IS UNIQUE;
CREATE CONSTRAINT ON (i:Industry) ASSERT i.industry_name IS UNIQUE;

CREATE CONSTRAINT ON (u:User) ASSERT u.id IS UNIQUE;
CREATE CONSTRAINT ON (u:User) ASSERT u.username IS UNIQUE;

CREATE CONSTRAINT ON (vul:Vulnerability) ASSERT vul.vulnerability_id IS UNIQUE;
CREATE CONSTRAINT ON (vul:Vulnerability) ASSERT vul.cve_id IS UNIQUE;

CREATE CONSTRAINT ON (ta:ThreatActor) ASSERT ta.actor_id IS UNIQUE;
CREATE CONSTRAINT ON (ta:ThreatActor) ASSERT ta.name IS UNIQUE;

CREATE CONSTRAINT ON (tc:ThreatCategory) ASSERT tc.category_id IS UNIQUE;
CREATE CONSTRAINT ON (tc:ThreatCategory) ASSERT tc.category_name IS UNIQUE;

CREATE CONSTRAINT ON (tat:ThreatActorType) ASSERT tat.type_id IS UNIQUE;
CREATE CONSTRAINT ON (tat:ThreatActorType) ASSERT tat.type_name IS UNIQUE;

// CREATE NODES
LOAD CSV WITH HEADERS FROM 'file:///affected_products.csv' AS row
CREATE (:Product {product_id: toInteger(row.product_id), product_name: row.product_name, vendor: row.vendor, created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///attack_vector_categories.csv' AS row
CREATE (:VectorCategory {vector_category_id: toInteger(row.vector_category_id), category_name: row.category_name, description: row.description, created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///attack_vectors.csv' AS row
CREATE (:AttackVector {vector_id: toInteger(row.vector_id), name: row.name, description: row.description, severity_level: toInteger(row.severity_level), category: row.category, created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///countries.csv' AS row
CREATE (:Country {country_code: row.country_code, country_name: row.country_name, created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///geolocations.csv' AS row
CREATE (:Geolocation {geolocation_id: toInteger(row.geolocation_id), ip_address: row.ip_address, country: row.country, region: row.region, city: row.city, latitude: toFloat(row.latitude), longitude: toFloat(row.longitude), created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///global_threats.csv' AS row
CREATE (:Threat {threat_id: toInteger(row.threat_id), name: row.name, description: row.description, first_detected: date(row.first_detected), last_updated: date(row.last_updated), severity_level: toInteger(row.severity_level), data_retention_until: datetime(row.data_retention_until), created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///industries.csv' AS row
CREATE (:Industry {industry_id: toInteger(row.industry_id), industry_name: row.industry_name, description: row.description, created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///users.csv' AS row
CREATE (:User {id: toInteger(row.id), username: row.username, password: row.password, consent_to_data_usage: row.consent_to_data_usage = '1', email: row.email, enabled: row.enabled = '1'});

LOAD CSV WITH HEADERS FROM 'file:///vulnerabilities.csv' AS row
CREATE (:Vulnerability {vulnerability_id: toInteger(row.vulnerability_id), cve_id: row.cve_id, description: row.description, published_date: date(row.published_date), severity_score: toFloat(row.severity_score), created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///threat_actors.csv' AS row
CREATE (:ThreatActor {actor_id: toInteger(row.actor_id), name: row.name, description: row.description, origin_country: row.origin_country, first_observed: date(row.first_observed), last_activity: date(row.last_activity), created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///threat_categories.csv' AS row
CREATE (:ThreatCategory {category_id: toInteger(row.category_id), category_name: row.category_name, description: row.description, created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

LOAD CSV WITH HEADERS FROM 'file:///threat_actor_types.csv' AS row
CREATE (:ThreatActorType {type_id: toInteger(row.type_id), type_name: row.type_name, description: row.description, created_at: datetime(row.created_at), updated_at: datetime(row.updated_at)});

// CREATE RELATIONSHIPS
LOAD CSV WITH HEADERS FROM 'file:///attack_vectors.csv' AS row
MATCH (vc:VectorCategory {vector_category_id: toInteger(row.vector_category_id)})
MATCH (v:AttackVector {vector_id: toInteger(row.vector_id)})
CREATE (v)-[:BELONGS_TO]->(vc);

LOAD CSV WITH HEADERS FROM 'file:///geolocations.csv' AS row
MATCH (c:Country {country_code: row.country})
MATCH (g:Geolocation {geolocation_id: toInteger(row.geolocation_id)})
CREATE (g)-[:LOCATED_IN]->(c);

LOAD CSV WITH HEADERS FROM 'file:///threat_actors.csv' AS row
MATCH (tc:ThreatCategory {category_id: toInteger(row.category_id)})
MATCH (ta:ThreatActor {actor_id: toInteger(row.actor_id)})
CREATE (ta)-[:CATEGORIZED_AS]->(tc);

MATCH (c:Country {country_code: row.origin_country})
CREATE (ta)-[:ORIGINATES_FROM]->(c);

MATCH (tat:ThreatActorType {type_id: toInteger(row.type_id)})
CREATE (ta)-[:OF_TYPE]->(tat);
