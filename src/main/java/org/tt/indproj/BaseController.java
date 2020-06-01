package org.tt.indproj;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

import org.tt.indproj.database_operators.DBBroker;
import org.tt.indproj.utilities.FileReader;

/**
 * The Base Controller, with no initial mapping. One can access the
 * most general webpages from this controller.
 * @author terratenff
 */
@Controller
@RequestMapping("")
public class BaseController {

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

    /**
     * View-function for the home page.
     */
    @RequestMapping(HOME)
    public ModelAndView index() {
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
    public ModelAndView storySelection() {
        // TODO
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
        // TODO
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        //DBBroker.createDatabase();
        return mav;
    }
}
