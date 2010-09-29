package net.coolcoders.showcase.client;

/**
 * Interface to represent the messages contained in resource bundle:
 * 	/home/josip/Work/showcase/web/grails-smartgwt/src/gwt/net/coolcoders/smartgwt/client/ShowcaseMessages.properties'.
 */
public interface ShowcaseMessages extends com.google.gwt.i18n.client.Messages {

  /**
   * Translated "<i>{2}</i> is an invalid email!".
   *
   * @return translated "<i>{2}</i> is an invalid email!"
   */
  @DefaultMessage("<i>{2}</i> is an invalid email!")
  @Key("user.email.email.invalid")
  String user_email_email_invalid(@Optional String arg0,  @Optional String arg1,  String arg2);

  /**
   * Translated "You are now following user {0}".
   *
   * @return translated "You are now following user {0}"
   */
  @DefaultMessage("You are now following user {0}")
  @Key("user.follow.executed")
  String user_follow_executed(String arg0);

  /**
   * Translated "Your password does not match the pattern {3}!".
   *
   * @return translated "Your password does not match the pattern {3}!"
   */
  @DefaultMessage("Your password does not match the pattern {3}!")
  @Key("user.password.matches.invalid")
  String user_password_matches_invalid(@Optional String arg0,  @Optional String arg1,  @Optional String arg2,  String arg3);

  /**
   * Translated "You are not following user {0} anymore".
   *
   * @return translated "You are not following user {0} anymore"
   */
  @DefaultMessage("You are not following user {0} anymore")
  @Key("user.unfollow.executed")
  String user_unfollow_executed(String arg0);
}
