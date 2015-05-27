# [AppUser](DomainModelUser.md) #

## Attributes ##

| **Type** | **Name** | **Primary?** | **Description** |
|:---------|:---------|:-------------|:----------------|
|`String`  |id        |Yes           |UUID             |
|`String`  |username  |No            |Used to identify the user|
|`String`  |fullname  |No            |Real name of the user|
|`String`  |password  |No            |Password of the user (plain text)|
|`String`  |email     |No            |Email of the user|
|`Gender`  |gender    |No            |Gender (Provided by Enum Gender. Possible values: Gender.MALE, Gender.FEMALE)|
|`Date`    |birthday  |No            |Birthday of the User, precision Year, month, day|
|`Set<`[Category](DomainModelCategory.md)`>`|categories|No            |Categories selected by the user to specify his interests|
|`List<`[Message](DomainModelMessage.md)`>`|messages  |No            |Messages created by the user|
|`Set<`[AppUser](DomainModelUser.md)`>`|following |No            |All users this user is following|

## Constraints ##

| **Attribute** | **Constraint** | **Validation error message** |
|:--------------|:---------------|:-----------------------------|
|id             |Unique          |...                           |
|username       |Unique          |Username already in use!      |
|username       |max length 32 character|Username too long, only 32 characters allowed!|
|password       |Must match regular expression `^.*(?=.{6,})(?=.*\\d)(?=.*[a-zA-Z]).*$` |Password must contain at least 1 character and 1 number!|
|email          |Unique          |Email is already in use!      |
|email          |Must be a valid email address|Email xxx is not a valid email!|