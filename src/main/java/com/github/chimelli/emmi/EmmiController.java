package com.github.chimelli.emmi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EmmiController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PictureRepository pictureRepository;
	
    @RequestMapping("/")
    public String index(Model m) {
    	m.addAttribute("pictures", pictureRepository.findAll());
        return "index";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String uploadPicture(@RequestParam MultipartFile picture, @RequestParam String caption, Model m) throws IOException {
    	pictureRepository.save(new Picture(picture.getBytes(), caption));
    	return "redirect:/";
    }
    
    @RequestMapping("/view/{imageId}")
    public String manna(@PathVariable Long imageId, Model m) {
    	m.addAttribute("imageId", imageId);
    	Picture picture = pictureRepository.findOne(imageId);
    	m.addAttribute("caption", picture.getCaption());
    	m.addAttribute("comments", commentRepository.findByPicture(picture));
    	return "image";
    }
    
    @RequestMapping(value="/view/{imageId}/update", method=RequestMethod.POST)
    public String addComment(@PathVariable Long imageId, @RequestParam String name, @RequestParam String comment, Model m) {
    	Picture picture = pictureRepository.findOne(imageId);
    	commentRepository.save(new Comment(picture, name, comment));
    	return "redirect:/view/" + imageId;
    }
    
    @RequestMapping(value="/img/{imageId}", produces=MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] viewImage(@PathVariable Long imageId) {
    	return pictureRepository.findOne(imageId).getPicture();
    }
    
}