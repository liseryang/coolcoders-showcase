import net.coolcoders.showcase.User
import net.coolcoders.showcase.Gender
import net.coolcoders.showcase.Category
import net.coolcoders.showcase.Message

class BootStrap {

  def init = { servletContext ->
    environments {
      production {
        createCategories()
        createUsers()
      }
      development {
        createCategories()
        createUsers()
      }
      test {
        createCategories()
        createUsers()
      }
    }

  }
  def destroy = {
  }

  private void createCategories() {
    if (!Category.count()) {
      Category jee5 = new Category(name: "JEE5").save()
      log.debug("Created category $jee5")

      Category jee6 = new Category(name: "JEE6").save()
      log.debug("Created category $jee6")

      Category grails = new Category(name: "Grails").save()
      log.debug("Created category $grails")

      Category wicket = new Category(name: "Wicket").save()
      log.debug("Created category $wicket")

      Category gwt = new Category(name: "GWT").save()
      log.debug("Created category $gwt")
    }
  }


  private void createUsers() {
    if (!User.count()) {
      User pschneidermanzell = createDummyUser("pschneider-manzell", "Peter Schneider-Manzell", "pschneider-manzell@coolcoders.net", Gender.MALE)
      User abaumgartner = createDummyUser("abaumgartner", "Andreas Baumgartner", "abaumgartner@coolcoders.net", Gender.MALE)
      User jmihelko = createDummyUser("jmihelko", "Josip Mihelko", "jmihelko@coolcoders.net", Gender.MALE)
      User anerlich = createDummyUser("anerlich", "Andreas Baumgartner", "anerlich@coolcoders.net", Gender.MALE)

      abaumgartner.addToFollowing(pschneidermanzell)
      jmihelko.addToFollowing(anerlich)
      anerlich.addToFollowing(abaumgartner)

      for (i in 1..100) {
        User dummyUser = createDummyUser("user$i", "Dummy User$i", "user$i@coolcoders.net", Gender.MALE)
        pschneidermanzell.addToFollowing(dummyUser)
      }


      createDummyMessagesForUser(abaumgartner, 100)
      createDummyMessagesForUser(jmihelko, 50)
      createDummyMessagesForUser(anerlich, 10)
    }

  }


  User createDummyUser(String username, String fullname, String email, Gender gender) {
    User dummyUser = new User(username: username, password: "test123", email: email, gender: gender).save()
    log.debug("Created user $dummyUser")
    return dummyUser
  }

  void createDummyMessagesForUser(User userInstance, int count) {
    log.debug("Creating dummy messages for user $userInstance")
    for (i in 1..count) {
      String messageContent = "Test message number $i"
      Message messageInstance = new Message(content: messageContent, user: userInstance).save()
      userInstance.addToMessages(messageInstance)
      log.debug("Created message $messageInstance")
    }
  }
}
