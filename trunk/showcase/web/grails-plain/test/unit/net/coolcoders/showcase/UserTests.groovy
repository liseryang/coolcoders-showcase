package net.coolcoders.showcase

import grails.test.*

class UserTests extends GrailsUnitTestCase {
  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testConstraints() {
    def existingUser = new User(username: "pschneider-manzell", password: "test123", email: "pschneider-manzell@coolcoders.net",gender:Gender.MALE)
    mockForConstraintsTests(User, [existingUser])
    def user = new User()
    assertFalse user.validate()
    assertEquals "nullable", user.errors["username"]
    assertEquals "nullable", user.errors["password"]
    assertEquals "nullable", user.errors["email"]

    user = new User(username:"pschneider-manzell",password:"test123",email:"pschneider-manzell@coolcoders.net",gender:Gender.MALE)
    assertFalse user.validate()
    assertEquals "unique", 	user.errors["username"]
    assertEquals "unique", 	user.errors["email"] 



    user = new User(username: "abaumgartner", password: "test123", email: "abaumgartner@coolcoders.net",gender:Gender.MALE)
    assertTrue("User $user did NOT validate! Errors: ${user.errors}",user.validate())
  }
}
