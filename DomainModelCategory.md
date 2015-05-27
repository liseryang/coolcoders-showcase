# Category #

## Attributes ##

| **Type** | **Name** | **Primary?** | **Description** |
|:---------|:---------|:-------------|:----------------|
|`String`  |id        |Yes           |UUID             |
|`String`  |name      |No            |Used to identify the category|
|`Set<`[User](DomainModelUser.md)`>`|users     |No            |All users using this category|

## Constraints ##

| **Attribute** | **Constraint** | **Validation error message** |
|:--------------|:---------------|:-----------------------------|
|id             |Unique          |...                           |
|name           |Unique          |Category already defined!     |