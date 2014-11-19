package com.github.chimelli.emmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmmiController {
	
	@Autowired
	private CommentRepository commentRepository;
	
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/manna")
    public String manna(Model m) {
    	m.addAttribute("comments", commentRepository.findAll());
        return "manna";
    }
    
    @RequestMapping(value="/manna", method=RequestMethod.POST)
    public String addComment(@RequestParam String name, @RequestParam String comment, Model m) {
    	commentRepository.save(new Comment(name, comment));
    	m.addAttribute("comments", commentRepository.findAll());
    	return "manna";
    }
    
}