package net.coolcoders

import net.coolcoders.showcase.Gender
import javax.faces.model.SelectItem
import net.coolcoders.showcase.Category
import net.coolcoders.showcase.AppUser
import javax.faces.application.FacesMessage
import javax.faces.context.FacesContext

class RegisterBean {

    def static scope = 'view'

    def sessionBean
    def genderItems = [new SelectItem(Gender.MALE, "Male"), new SelectItem(Gender.FEMALE, "Female")]
    def categoryItems = []
    def user = new AppUser()


    def init() {
        categoryItems = Category.list()
        if (sessionBean.currentUser) {
            user = AppUser.get(sessionBean.currentUser.id)
        }
    }

    def save() {
        log.error "Entering save()..."
        log.error "User.categories: ${user.categories}"
        if (user.validate()) {
            if (user.id) {
                user = user.attach()
            }
            user = user.save(flush: true)

            sessionBean.currentUser = user
            redirect "/showMessages"
        }
        else {
            log.error "Validation erros: ${user.errors}"
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Could not save user!", "There are validation errors!"));
            render "/register"
        }

    }


}
