package org.tt.indproj.controllers;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tt.indproj.core.IUser;
import org.tt.indproj.core.user.UserManager;
import org.tt.indproj.database_operators.DBBroker;
import org.tt.indproj.utilities.ContentFetcher;

/**
 * The Base Controller, with no initial mapping. One can access the
 * most general webpages from this controller.
 * @author terratenff
 */
@Controller
@RequestMapping(value = "")
public class BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    private final String HOME = "";
    private final String STORY = "/story_selection";
    private final String ABOUT = "/about";
    private final String HELP = "/help";
    private final String RULES = "/rules";
    private final String GUIDELINES = "/guidelines";
    private final String CONTACT = "/contact";
    private final String FAQ = "/faq";
    private final String INSTRUCTIONS = "/instructions";
    private final String FEEDBACK = "/feedback";
    private final String SUPPORT = "/support";
    private final String API = "/api";
    private final String LOGIN = "/login";
    private final String SIGNUP = "/signup";
    private final String LOGOUT = "/logout";

    /**
     * View-function for the home page.
     */
    @RequestMapping(HOME)
    public ModelAndView index(
    		@CookieValue(value = "session_id", defaultValue = "") String idKey,
    		@CookieValue(value = "session_value", defaultValue = "") String idValue,
    		final HttpServletRequest request) {
    	logger.info("Navigated to '" + HOME + "'.");
    	
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
    		for (Cookie cookielist : request.getCookies()) {
    			// Test output
        		logger.info(cookielist.getName() + ": " + cookielist.getValue());
        	}
    	}

        // TODO
    	
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        String[] contents = ContentFetcher.getParagraphs("index.txt");
        mav.addObject("texts", contents);
        mav.addObject("pageContext", 0);
        // mav.addObject("key", value);
        // <p th:text="'Text ' + ${key}"></p>
        return mav;
    }

    /**
     * View-function for the story selection page.
     */
    @RequestMapping(STORY)
    public ModelAndView storySelection() {
    	logger.info("Navigated to '" + STORY + "'.");
    	
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        String[] contents = ContentFetcher.getParagraphs("story_selection.txt");
        mav.addObject("texts", contents);
        mav.addObject("pageContext", 1);
        return mav;
    }

    /**
     * View-function for the about-page.
     */
    @RequestMapping(ABOUT)
    public ModelAndView about() {
    	logger.info("Navigated to '" + ABOUT + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        String[] contents = ContentFetcher.getParagraphs("about.txt");
        mav.addObject("texts", contents);
        mav.addObject("pageContext", 2);
        return mav;
    }

    /**
     * View-function for the help page.
     */
    @RequestMapping(HELP)
    public ModelAndView help() {
    	logger.info("Navigated to '" + HELP + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        String[] contents = ContentFetcher.getParagraphs("help.txt");
        mav.addObject("texts", contents);
        return mav;
    }

    /**
     * View-function for the rules page.
     */
    @RequestMapping(RULES)
    public ModelAndView rules() {
    	logger.info("Navigated to '" + RULES + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        return mav;
    }

    /**
     * View-function for the guidelines page.
     */
    @RequestMapping(GUIDELINES)
    public ModelAndView guidelines() {
    	logger.info("Navigated to '" + GUIDELINES + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        return mav;
    }

    /**
     * View-function for the contact page.
     */
    @RequestMapping(CONTACT)
    public ModelAndView contact() {
    	logger.info("Navigated to '" + CONTACT + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        return mav;
    }

    /**
     * View-function for the FAQ-page.
     */
    @RequestMapping(FAQ)
    public ModelAndView faq() {
    	logger.info("Navigated to '" + FAQ + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        return mav;
    }

    /**
     * View-function for the instructions page.
     */
    @RequestMapping(INSTRUCTIONS)
    public ModelAndView instructions() {
    	logger.info("Navigated to '" + INSTRUCTIONS + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        return mav;
    }

    /**
     * View-function for the feedback page.
     */
    @RequestMapping(FEEDBACK)
    public ModelAndView feedback() {
    	logger.info("Navigated to '" + FEEDBACK + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        return mav;
    }

    /**
     * View-function for the support page.
     */
    @RequestMapping(SUPPORT)
    public ModelAndView support() {
    	logger.info("Navigated to '" + SUPPORT + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        return mav;
    }

    /**
     * View-function for the page that introduces users to the web apps API.
     */
    @RequestMapping(API)
    public ModelAndView api() {
    	logger.info("Navigated to '" + API + "'.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
        
        return mav;
    }
    
    /**
     * View-function for authentication purposes.
     */
    @RequestMapping(value = LOGIN, method = RequestMethod.POST)
    public ModelAndView loginPost(final HttpServletRequest request,
    		@RequestParam("username") final String username,
    		@RequestParam("pwd") final String password,
    		@CookieValue(value = "session_id", defaultValue = "") String idKey,
    		@CookieValue(value = "session_value", defaultValue = "") String idValue,
    		final HttpServletResponse response) {
    	logger.info("Navigated to '" + LOGIN + "'. An attempt to log in was made.");
    	
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        // TODO
    	
    	IUser existingUser = UserManager.getUser(idKey, idValue);
    	if (existingUser.getId() >= 0) {
    		logger.warn("User is already logged in!");
    		return mav;
    	}
        
        IUser user = DBBroker.login(username, password);
        if (user == null) {
        	logger.error("Login failed.");
        } else {
        	Cookie cookie1 = new Cookie("session_id", user.getIdentifierKey());
        	Cookie cookie2 = new Cookie("session_value", user.getIdentifierValue());
        	cookie1.setMaxAge(-1);
        	cookie2.setMaxAge(-1);
        	response.addCookie(cookie1);
        	response.addCookie(cookie2);
        	
        	// TODO
        }
        
        return mav;
    }
    
    /**
     * View-function for authentication purposes.
     */
    @RequestMapping(value = LOGIN, method = RequestMethod.GET)
    public ModelAndView loginGet() {
    	logger.info("Navigated to '" + LOGIN + "'. Login page visited.");
    	
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
    	
        // TODO
        
        return mav;
    }
    
    /**
     * View-function for creating a new account.
     */
    @RequestMapping(value = SIGNUP, method = RequestMethod.POST)
    public ModelAndView signupPost(final HttpServletRequest request,
    		@RequestParam("username") final String username,
    		@RequestParam("pwd") final String password,
    		@RequestParam("pwd_repeat") final String passwordRepeat,
    		final HttpServletResponse response) {
    	logger.info("Navigated to '" + SIGNUP + "'. An attempt to create an account was made.");
        
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
    	
    	IUser user;
    	if (!password.equals(passwordRepeat)) {
    		logger.error("Passwords do not match!");
    	} else {
    		user = UserManager.createUser(username, password);
        	if (user == null) {
        		logger.error("Account with username '" + username + "' already exists.");
        	} else {
        		DBBroker.insertUser(user); // DBBroker assigns User ID.
        		if (user.getId() < 0) {
        			logger.error("User insertion failed. (User ID is negative");
        		} else {
        			logger.info("User insertion succeeded. Authorizing user...");
        			
        			Cookie cookie1 = new Cookie("session_id", user.getIdentifierKey());
                	Cookie cookie2 = new Cookie("session_value", user.getIdentifierValue());
                	cookie1.setMaxAge(-1);
                	cookie2.setMaxAge(-1);
                	response.addCookie(cookie1);
                	response.addCookie(cookie2);
        			
        			// TODO
        		}
        	}
    	}
    	
        return mav;
    }
    
    /**
     * View-function for creating a new account.
     */
    @RequestMapping(value = SIGNUP, method = RequestMethod.GET)
    public ModelAndView signupGet() {
    	logger.info("Navigated to '" + SIGNUP + "'. Signup page visited.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        return mav;
    }
    
    /**
     * View-function for creating a new account.
     */
    @RequestMapping(value = LOGOUT)
    public ModelAndView logout(
    		@CookieValue(value = "session_id", defaultValue = "") String idKey,
    		@CookieValue(value = "session_value", defaultValue = "") String idValue,
    		final HttpServletRequest request, final HttpServletResponse response) {
    	logger.info("Navigated to '" + LOGOUT + "'. An attempt to log out is made.");
    	
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
    	IUser user = UserManager.getUser(idKey, idValue);
    	if (user.getId() < 0) {
    		logger.warn("User is already logged out.");
    	}
    	
    	Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name.equals("session_id") || name.equals("session_value")) {
					cookie.setValue("");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
        
        return mav;
    }
}
