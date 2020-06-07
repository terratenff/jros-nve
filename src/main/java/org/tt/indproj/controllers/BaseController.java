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

import org.tt.indproj.database_operators.DBBroker;
import org.tt.indproj.utilities.FileReader;

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

    /**
     * View-function for the home page.
     */
    @RequestMapping(HOME)
    public ModelAndView index(
    		@CookieValue(value = "darkmode", defaultValue = "notfound") String cookie,
    		@CookieValue(value = "story", defaultValue = "nothere") String cookie2,
    		HttpServletRequest request) {
    	logger.info("Navigated to '" + HOME + "'.");
    	logger.info("COOKIE: " + cookie);
    	logger.info("COOKIE2: " + cookie2);
    	
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
    		for (Cookie cookielist : request.getCookies()) {
        		logger.info("LIST COOKIE: " + cookielist.getName());
        	}
    	}

        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        String[] contents = FileReader.getParagraphs("index.txt");
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
    public ModelAndView storySelection(HttpServletResponse response) {
    	logger.info("Navigated to '" + STORY + "'.");
        // TODO
    	response.addCookie(new Cookie("story", "hello"));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        String[] contents = FileReader.getParagraphs("story_selection.txt");
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
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        String[] contents = FileReader.getParagraphs("about.txt");
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
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        String[] contents = FileReader.getParagraphs("help.txt");
        mav.addObject("texts", contents);
        return mav;
    }

    /**
     * View-function for the rules page.
     */
    @RequestMapping(RULES)
    public ModelAndView rules() {
    	logger.info("Navigated to '" + RULES + "'.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    /**
     * View-function for the guidelines page.
     */
    @RequestMapping(GUIDELINES)
    public ModelAndView guidelines() {
    	logger.info("Navigated to '" + GUIDELINES + "'.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    /**
     * View-function for the contact page.
     */
    @RequestMapping(CONTACT)
    public ModelAndView contact() {
    	logger.info("Navigated to '" + CONTACT + "'.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    /**
     * View-function for the FAQ-page.
     */
    @RequestMapping(FAQ)
    public ModelAndView faq() {
    	logger.info("Navigated to '" + FAQ + "'.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    /**
     * View-function for the instructions page.
     */
    @RequestMapping(INSTRUCTIONS)
    public ModelAndView instructions() {
    	logger.info("Navigated to '" + INSTRUCTIONS + "'.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    /**
     * View-function for the feedback page.
     */
    @RequestMapping(FEEDBACK)
    public ModelAndView feedback() {
    	logger.info("Navigated to '" + FEEDBACK + "'.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    /**
     * View-function for the support page.
     */
    @RequestMapping(SUPPORT)
    public ModelAndView support() {
    	logger.info("Navigated to '" + SUPPORT + "'.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    /**
     * View-function for the page that introduces users to the web apps API.
     */
    @RequestMapping(API)
    public ModelAndView api() {
    	logger.info("Navigated to '" + API + "'.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        //DBBroker.createDatabase();
        return mav;
    }
    
    /**
     * View-function for authentication purposes.
     */
    @RequestMapping(value = LOGIN, method = RequestMethod.POST)
    public ModelAndView loginPost(final HttpServletRequest request,
    		@RequestParam("username") final String username,
    		@RequestParam("pwd") final String password) {
    	logger.info("Navigated to '" + LOGIN + "'. An attempt to log in was made.");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        logger.info("User inputs:");
        logger.info(username);
        logger.info(password);
        return mav;
    }
    
    /**
     * View-function for authentication purposes.
     */
    @RequestMapping(value = LOGIN, method = RequestMethod.GET)
    public ModelAndView loginGet() {
    	logger.info("Navigated to '" + LOGIN + "'. Login page visited.");
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        return mav;
    }
}
