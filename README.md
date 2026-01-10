# Feature-Flagged Conditional Persistence

## The Problem

Many teams dream of trunk-based development and daily releases. They want to merge branches into main every day, ship small and safe releases, and eliminate the staleness, merge conflicts, and mental overhead caused by long-lived feature branches.

But then... a new feature request arrives.

For this feature, you need to create new database tables, define new entities, and establish relationships with existing entities. At that exact moment, all those nice plans are put aside. Feature branches lasting days—or even weeks—are created.

The feature implementation is completed, but another waiting period begins: **you wait for the SRE team to apply the database changes**. Without the tables corresponding to the new entities, you cannot deploy to production. JPA/Hibernate or Spring Data JPA will fail to bootstrap the application due to missing tables or mismatches between entity mappings and the relational model.

In the best case, it takes the SRE team a few more days to apply the necessary DDL changes. Meanwhile, your PR keeps growing, drifting further away from main, turning into a change set full of conflicts. You eventually merge and deploy, praying nothing breaks...

We all know how these "big-bang releases" usually end. You can hear the sound of that explosion from miles away.

## The Solution: Deliver Capabilities, Not Features

Daily release is not just a dream. Iterative and incremental development is absolutely achievable in practice.

But to do that, we need to fundamentally change how we look at sprints. Instead of delivering a "completed feature" at the end of each sprint, we deliver **capabilities**—capabilities that continuously evolve and mature over time.

Two concepts are critical:
- **Task Slicing** - Define tasks as isolated vertical slices
- **Task Ordering** - Order them so deploying each individual task to production is completely safe on its own

## Feature-Flagged Conditional Persistence

The core problem in enterprise applications with JPA/Hibernate and Spring Data is that:
- Application code (entities and repositories)
- Database schema changes

...are often managed by **different teams**, at **different speeds**, with **different priorities**. Database changes by the SRE team frequently block application teams.

**Feature-flagged conditional persistence** solves this by allowing you to:

> Merge code that includes new entity and repository classes—even if the database schema has not yet been changed—into the main branch from day one, and safely deploy it to production.

### How It Works

1. Put all components related to the new persistence model (entities, repositories, service layer) behind a **feature flag**
2. By default, this feature flag is **disabled**
3. The application starts in production, but nothing related to the new persistence model is active
4. The SRE team applies database schema changes according to their own plan
5. Once the database is ready, the feature flag is **enabled**
6. The new capability goes live in production

## Example: Adding Promotions to Orders

Assume we have an existing `Order` model. We want to add a `Promotion` capability—so that a promotion can be applied to an order when certain criteria are met.

### Sprint Breakdown

| Sprint | Goal | Feature Flag | Database |
|--------|------|--------------|----------|
| **Sprint 1** | Develop new persistence capability (entity, repository, service) | OFF | Not ready |
| **Sprint 2** | Apply database schema changes (create `t_promotions`, add `promotion_id` to `t_orders`) | OFF | Ready |
| **Sprint 3** | Activate feature flag in production | ON | Ready |
| **Sprint 4** | Establish relationships between Promotion and Order models | ON | Ready |

> **Note:** These don't have to be separate sprints. They can be different tasks within the same sprint. What matters is that tasks are **isolated**, progress in the **correct order**, and each step can be **safely deployed to production on its own**.

## Database Migrations

This project uses Flyway to manage schema changes independently of application deployments.

### V1: Base Schema
```sql
-- V1__create_orders_table.sql
CREATE SEQUENCE t_orders_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE t_orders (
    id BIGINT NOT NULL PRIMARY KEY,
    total_amount DECIMAL(19, 2) NOT NULL DEFAULT 0
);
```

### V2: New Capability Tables
```sql
-- V2__create_promotions_table.sql
CREATE SEQUENCE t_promotions_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE t_promotions (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    discount_percentage DECIMAL(5, 2) NOT NULL DEFAULT 0,
    start_date DATE,
    end_date DATE
);
```

### V3: Link Capability to Existing Model
```sql
-- V3__add_promotion_id_to_orders.sql
ALTER TABLE t_orders ADD COLUMN promotion_id BIGINT;

ALTER TABLE t_orders ADD CONSTRAINT fk_orders_promotion
    FOREIGN KEY (promotion_id) REFERENCES t_promotions(id);
```

**Critical:** The `promotion_id` column is **nullable**. This allows:
- Existing orders to remain valid (NULL promotion)
- The application to function before and after the relationship is established
- Safe, incremental rollout

## The Correct Ordering

Relationships between entities are **code-level, compile-time contracts**. This is not something you can "kind of manage" at runtime.

Therefore, the correct order is:

```
1. New persistence capability works independently (feature flag OFF)
         ↓
2. Database schema is ready (migrations applied)
         ↓
3. Feature flag is enabled (new capability active)
         ↓
4. Entity relationships are established (full integration)
```

This ordering allows the application to be **deployed to production every single day**, completely independently of database changes.

## Benefits

| Benefit | Description |
|---------|-------------|
| **Reduced friction** | Service teams and SRE teams can plan independently without blocking each other |
| **Daily releases** | Code can be merged and deployed daily, even before database changes |
| **No big-bang releases** | Small, safe, incremental changes instead of risky large deployments |
| **Continuous delivery** | True CI/CD becomes achievable, not just theoretical |
| **Safe rollbacks** | Feature can be disabled instantly without schema rollback |

## Running the Project

```bash
# Build
./gradlew build

# Run tests
./gradlew test

# Start application
./gradlew bootRun
```

Access H2 Console: http://localhost:8080/h2-console

## Technology Stack

- **Kotlin** - Primary language
- **Spring Boot 4.x** - Application framework
- **Spring Data JPA / Hibernate** - Persistence layer
- **Flyway** - Database migration management
- **H2** - Embedded database for development/testing

## Conclusion

The feature-flagged conditional persistence approach:

- ✅ Significantly reduces friction between service teams and SRE teams
- ✅ Enables teams to plan independently without blocking each other
- ✅ Makes daily release and continuous delivery practices truly achievable
- ✅ Prevents large, risky, and stressful big-bang releases

**Daily release is not a dream. It's a practice.**
