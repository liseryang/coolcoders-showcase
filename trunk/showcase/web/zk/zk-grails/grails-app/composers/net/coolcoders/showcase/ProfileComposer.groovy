package net.coolcoders.showcase

import org.zkoss.zkgrails.*
import org.zkoss.zk.ui.event.MouseEvent
import org.zkoss.zkplus.databind.DataBinder
import org.zkoss.zk.ui.Executions

class ProfileComposer extends GrailsComposer {

  DataBinder binder = new DataBinder()
  String beanBindingName = "newUser"

  def username
  def fullname
  def email
  def gender
  def password
  def repassword
  def birthday
  def categoryBox
  def categoyCheckboxMap = [:]

  def errorMessages

  def update


  def afterCompose = { window ->

    AppUser userInstance = AppUser.get(Executions.getCurrent().getSession().getAt("currentUser").id)
    userInstance.repassword = userInstance.password
    def categoryList = Category.list()
    categoryBox.append {
      categoryList.each {categoryInstance ->
        def checkBoxInstance = checkbox(id: "category${categoryInstance.id}", label: categoryInstance.name, checked: userInstance.categories.any {it.id == categoryInstance.id})
        categoyCheckboxMap[categoryInstance.id] = checkBoxInstance
      }
    }

    binder.addBinding(username, "value", "${beanBindingName}.username")
    binder.addBinding(fullname, "value", "${beanBindingName}.fullname")
    binder.addBinding(email, "value", "${beanBindingName}.email")
    binder.addBinding(gender, "selectedItem.value", "${beanBindingName}.gender")
    binder.addBinding(password, "value", "${beanBindingName}.password")
    binder.addBinding(repassword, "value", "${beanBindingName}.repassword")
    binder.addBinding(birthday, "value", "${beanBindingName}.birthday")
    binder.bindBean(beanBindingName, userInstance)
    binder.loadAll()
  }


  def onClick_update(MouseEvent me) {
    AppUser userInstance = AppUser.get(Executions.getCurrent().getSession().getAt("currentUser").id)
    binder.bindBean(beanBindingName, userInstance)
    binder.saveAll()
    def selectedCategoryIDs = []
    categoyCheckboxMap.keySet().each { categoryId ->
      def checkBoxInstance = categoyCheckboxMap.get(categoryId)
      if (checkBoxInstance.isChecked()) {
        selectedCategoryIDs.add(categoryId)
      }
    }

    if (!userInstance.validate()) {
      def errorMessageString = ""
      userInstance.errors.allErrors.each { errorInstance ->
        errorMessageString = errorMessageString + "\n" + message(error: errorInstance)
      }
      errorMessages.value = errorMessageString
    }
    else {
      AppUser.withTransaction {
        userInstance.categories = Category.getAll(selectedCategoryIDs)
        userInstance.save()
      }
      Executions.getCurrent().sendRedirect("/")
    }
  }
}
