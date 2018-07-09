package kz.epam.intlab.controller;

//@Controller
public class UserController {

//    public static String takeUsernameFromSecurityContext() {
//        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (currentUser.contains("anonymousUser")) {
//            currentUser = "guest";
//        }
//        return currentUser;
//    }
//
//    private Service service;
//
//    @Inject
//    public void setService(Service service) {
//        this.service = service;
//    }
//
//    @Autowired
//    private UserCredentialValidator validator;
//
//    @RequestMapping(value = "openLoginPage", method = RequestMethod.GET)
//    public String openLoginPage() {
//        return "login";
//    }
//
//    @RequestMapping(value = "openRegisterPage", method = RequestMethod.GET)
//    public ModelAndView openRegisterPage() {
//        ModelAndView model = new ModelAndView();
//        model.setViewName("reg");
//        model.addObject("userModel", new UserDTO());
//        return model;
//    }
//
//    @RequestMapping(value = "/addUpdateUser", method = RequestMethod.POST)
//    public ModelAndView addUpdateUser(@ModelAttribute UserDTO userDTO, @RequestParam String rePassword) throws DaoException {
//        List<String> errorMessages = validator.validateUser(userDTO, rePassword);
//        ModelAndView model = new ModelAndView();
//        if (errorMessages.isEmpty()) {
//            service.addUpdateUser(userDTO);
//            model.setViewName("login");
//        } else {
//            model.setViewName("reg");
//            model.addObject("errorMessages", errorMessages);
//            model.addObject("userModel", new UserDTO());
//        }
//        return model;
//    }
}
