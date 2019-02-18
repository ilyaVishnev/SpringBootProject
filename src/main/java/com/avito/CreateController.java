package com.avito;

import com.DAO.services.CarService;
import com.DAO.services.HolderService;
import com.cars_annot.Car;
import com.cars_annot.CarForm;
import com.cars_annot.Holder;
import com.cars_annot.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
/*import org.springframework.web.bind.annotation.GetMapping;*/
import org.springframework.web.bind.annotation.ModelAttribute;
/*import org.springframework.web.bind.annotation.PostMapping;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/create")
public class CreateController {

    final Logger logger = Logger.getLogger(CreateController.class.getName());
    @Autowired
    CarService carService;

    @Autowired
    private HolderService holderService;

    private Holder holder;

    @RequestMapping(method = RequestMethod.GET)
     @Secured({"ROLE_USER", "ROLE_ADMIN"})
    protected String goToCreate(HttpSession session) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        holder = holderService.findByLogin(currentUserName);
        session.setAttribute("user", holder);
        session.setAttribute("admin", isAdmin(holder.getRoles()));
        return "createCar";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String saveCar(@ModelAttribute("car") CarForm carForm) {
        Car car = carForm.getCar();
        car.setHolder(holder);
        carService.save(car);
        return "createCar";
    }

    private Boolean isAdmin(List<Role> roles) {
        Boolean admin = false;
        for (Role role : roles) {
            if (role.getRole().equals("ROLE_ADMIN")) {
                admin = true;
            }
        }
        return admin;
    }
}
