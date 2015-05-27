# Message #

## Attributes ##

| **Type** | **Name** | **Primary?** | **Description** |
|:---------|:---------|:-------------|:----------------|
|`String`  |id        |Yes           |UUID             |
|`String`  |content   |No            |Content of the message|
|`Date`    |created   |No            |Date the message was created|
|[AppUser](DomainModelUser.md)|creator   |No            |Creator of this message|

## Constraints ##

| **Attribute** | **Constraint** | **Validation error message** |
|:--------------|:---------------|:-----------------------------|
|id             |Unique          |...                           |
|creator        |not null        |Creator required!             |
|created        |not null        |Creation date required!       |
|content        |not null        |Message required!             |
|content        |max length 140 character|Message too long, only 140 characters allowed!|