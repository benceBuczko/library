package com.threelittlepigs.codecool.libraryManager.Controllers.Implementations;

public class NonSessionController {

/*

    @Override
    public String logout(Request request, Response response) {
        request.session().removeAttribute("userName");
        request.session().removeAttribute("user_id");
        response.redirect("/");
        return "";
    }

    @Override
    public String registration(Request request, Response response) {
        Map<String, String> regData = JSONUtils.parseJson(request);
        UserService us = new UserServiceJPA();
        if (us.registrateMember(regData)) {
            return "";
        }
        return "failure";
    }

    public boolean ensureUserIsLoggedIn(Request req, Response res) {
        return req.session().attribute("userName") != null;
    }

    @Override
    public ModelAndView renderUserInfo(Request req, Response res, String html) {

        UserService us = new UserServiceJPA();
        Integer id = req.session().attribute("user_id");
        if (id == null) {
            res.redirect("/");
        }
        User user = us.getUserById(id);
        List<Book> rentedBooks = EntityUtility.getEntityManager().createNamedQuery("getRentedBooksByMember", Book.class).setParameter("rentedByMember", user).getResultList();
        List<Book> reservedBooks = EntityUtility.getEntityManager().createNamedQuery("getReservedBooksByMember", Book.class).setParameter("reservedByMember", user).getResultList();
        List<Fine> fines = EntityUtility.getEntityManager().createNamedQuery("getFineByMember", Fine.class).setParameter("memberid", user.getId()).getResultList();

        Map params = new HashMap<>();
        params.put("user", user);
        params.put("rentedBooks", rentedBooks);
        params.put("reservedBooks", reservedBooks);
        params.put("fines", fines);
        params.put("user_id", id);
        params.put("userName", req.session().attribute("userName"));

        return new ModelAndView(params, html);
    }

    */
}
