# Overview #

## Corresponding pages ##

# Details #

## Normal Flow ##

On startup create the following entities:

### Categories ###
> | **Name** |
|:---------|
> | JEE5     |
> | JEE6     |
> | Grails   |
> | Wicket   |
> | GWT      |
> | JSF      |
> | Icefaces |
> | Primefaces |
> | SmartGWT |

### Users ###
| **Username** | **Fullname** | **Password** | **Email** | **Gender** |
|:-------------|:-------------|:-------------|:----------|:-----------|
| abaumgartner | Andreas Baumgartner | test123      | abaumgartner@coolcoders.net | GenderMALE |
| anerlich     | Andreas Nerlich | test123      | anerlich@coolcoders.net | GenderMALE |
| jmihelko     | Josip Mihelko | test123      | jmihelko@coolcoders.net | GenderMALE |
| pschneider-manzell | Peter Schneider-Manzell | test123      | pschneider-manzell@coolcoders.net | GenderMALE |

Create following additional 100 dummy users:
| **Username** | **Fullname** | **Password** | **Email** | **Gender** |
|:-------------|:-------------|:-------------|:----------|:-----------|
| user`<currentNumber>` | Dummy User`<currentNumber>` | test123      | user`<currentNumber>`@coolcoders.net | GenderMALE |

Make following "social graph"
  * abaumgartner follows pschneider-manzell
  * jmihelko follows anerlich
  * anerlich follows abaumgartner
  * pschneider-manzell follows all dummy users user`<currentNumber>`


### Messages ###
Create following dummy messages:

  * 100 messages for user abaumgartner
  * 50 messages for user jmihelko
  * 10 messages for user anerlich

Message content:

`Test message number <message number of current user>`, e.g. `Test message number 1`



## Alternative flows ##

### DB already in filled ###
Don't create dummy entites